package theWario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.commons.lang3.ArrayUtils;
import theWario.powers.AbandonGamePower;
import theWario.powers.ImprisonedPower;
import theWario.powers.KeyFinisherPower;
import theWario.squares.*;
import theWario.util.AbstractDrone;
import theWario.util.TextureLoader;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public abstract class RazsBoard {
    public ArrayList<AbstractSquare> squareList = new ArrayList<>();

    public Texture playerPiece = TextureLoader.getTexture("wariomodResources/images/ui/piecePlayer1.png");

    public AbstractDrone player = new AbstractDrone(0, 0, 0);

    public boolean shouldRender = false;

    public static Texture goodOutline = TextureLoader.getTexture("wariomodResources/images/ui/GoodOutline" + 1 + ".png");
    public static Texture neutralOutline = TextureLoader.getTexture("wariomodResources/images/ui/NeutralOutline" + 1 + ".png");
    public static Texture badOutline = TextureLoader.getTexture("wariomodResources/images/ui/BadOutline" + 1 + ".png");
    public static Texture activatesWhenPassedOutline = TextureLoader.getTexture("wariomodResources/images/ui/ActivatesWhenPassedOutline" + 1 + ".png");
    public static Texture deathOutline = TextureLoader.getTexture("wariomodResources/images/ui/DeathOutline" + 1 + ".png");
    public static Texture onOutline = TextureLoader.getTexture("wariomodResources/images/ui/OnOutline" + 1 + ".png");
    public static Texture canPickOutline = TextureLoader.getTexture("wariomodResources/images/ui/SelectableOutline" + 1 + ".png");

    public final float squareOffset = 64F * Settings.scale;

    protected boolean finishedSetup;

    public RazsBoard() {
        this.finishedSetup = false;
    }

    public void init() {
        player.position = 0;

        squareList.clear();
    }

    public AbstractSquare swapSquare(Class<? extends AbstractSquare> square, int x, int y) {
        try {
            return square.getConstructor(int.class, int.class).newInstance(x, y);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return new EmptySquare(x, y);
    }

    public void update() {
        for (AbstractSquare s : squareList) {
            s.hb.update();
        }
        if (rainbow.g < 1 && rainbow.r >= 1 && rainbow.b <= 0) {
            rainbow.g += velocity * Gdx.graphics.getDeltaTime();
            if (rainbow.g > 1.0F) {
                rainbow.g = 1.0F;
            }
        } else if (rainbow.g >= 1 && rainbow.r > 0 && rainbow.b <= 0) {
            rainbow.r -= velocity * Gdx.graphics.getDeltaTime();
            if (rainbow.r < 0.0F) {
                rainbow.r = 0.0F;
            }
        } else if (rainbow.g >= 1 && rainbow.r <= 0 && rainbow.b < 1) {
            rainbow.b += velocity * Gdx.graphics.getDeltaTime();
            if (rainbow.b > 1.0F) {
                rainbow.b = 1.0F;
            }
        } else if (rainbow.g > 0 && rainbow.r <= 0 && rainbow.b >= 1) {
            rainbow.g -= velocity * Gdx.graphics.getDeltaTime();
            if (rainbow.g < 0.0F) {
                rainbow.g = 0.0F;
            }
        } else if (rainbow.g <= 0 && rainbow.r < 1 && rainbow.b >= 1) {
            rainbow.r += velocity * Gdx.graphics.getDeltaTime();
            if (rainbow.r > 1.0F) {
                rainbow.r = 1.0F;
            }
        } else if (rainbow.g <= 0 && rainbow.r >= 1 && rainbow.b > 0) {
            rainbow.b -= velocity * Gdx.graphics.getDeltaTime();
            if (rainbow.b < 0.0F) {
                rainbow.b = 0.0F;
            }
        }
    }

    public void render(SpriteBatch sb) {
        if (shouldRender) {
            sb.setColor(Color.WHITE.cpy());
            for (AbstractSquare s : squareList) {
                if (this.renderSpecificSquare(s)) {
                    s.render(sb);
                    if (s.hb.hovered && !(s instanceof EmptySquare)) {
                        String q = s.getBodyText();
                        if (!(s instanceof GoSquare) && !(s instanceof DrawSquare)) {
                            if (s.triggersWhenPassed) {
                                q = "When landed upon or #gpassed, " + q;
                            } else {
                                q = "When landed upon, " + q;
                            }
                        }
                        if ((float) InputHelper.mX < 1400.0F * Settings.scale) {
                            TipHelper.renderGenericTip(
                                    (float) InputHelper.mX + 60.0F * Settings.scale, (float) InputHelper.mY - 50.0F * Settings.scale,
                                    s.getClass().getSimpleName().replaceAll("([^_])([A-Z])", "$1 $2").replaceAll("Square", "Space"),
                                    q
                            );
                        } else {
                            TipHelper.renderGenericTip((float) InputHelper.mX - 350.0F * Settings.scale, (float) InputHelper.mY - 50.0F * Settings.scale,
                                    s.getClass().getSimpleName().replaceAll("([^_])([A-Z])", "$1 $2").replaceAll("Square", "Space"),
                                    q);
                        }
                    }
                }
            }

            //Draw the numbers on the tiles.
            if (finishedSetup) {
                this.renderBoardNumbers(sb);
            }
            sb.setColor(Color.WHITE);
            sb.draw(playerPiece, player.location.x - ((playerPiece.getWidth() * Settings.scale) / 2F), player.location.y - ((playerPiece.getHeight() * Settings.scale) / 2F), playerPiece.getWidth() / 2F, playerPiece.getHeight() / 2F, playerPiece.getWidth(), playerPiece.getHeight(), Settings.scale, Settings.scale, 0, 0, 0, playerPiece.getWidth(), playerPiece.getHeight(), false, false);
            this.renderExtraPieces();
        }
    }

    protected boolean renderSpecificSquare(AbstractSquare Square) {
        return true;
    }

    protected void renderSquareNumber(SpriteBatch sb, int number, float x, float y, Color color) {
        FontHelper.renderFontCentered(sb, FontHelper.energyNumFontRed, String.valueOf(number), x + 34.0F, y + 32.0F, color);
    }

    protected void renderNumbersFromPosition(SpriteBatch sb, int curPosition, ArrayList<Integer> jump, ArrayList<Integer> takenNumbers, Color endColor) {
        this.renderNumbersFromPosition(sb, curPosition, jump, takenNumbers, endColor, null);
    }

    protected void renderNumbersFromPosition(SpriteBatch sb, int curPosition, ArrayList<Integer> jump, ArrayList<Integer> takenNumbers, Color endColor, Color passingColor) {
        int index;
        AbstractSquare Square;
        for (Integer i : jump) {
            for (int j = 1; j < i; j++) {
                index = (curPosition + j) % squareList.size();
                if (takenNumbers == null || !takenNumbers.contains(index)) {
                    Square = squareList.get(index);
                    this.renderSquareNumber(sb, j, Square.x, Square.y, passingColor);
                }
            }
            index = (curPosition + i) % squareList.size();
            if (takenNumbers != null) {
                takenNumbers.add(index);
            }
            Square = squareList.get(index);
            if (Square instanceof DoomSquare)
                this.renderSquareNumber(sb, i, Square.x, Square.y, rainbow);
            else
                this.renderSquareNumber(sb, i, Square.x, Square.y, endColor);
        }
    }

    protected abstract void renderBoardNumbers(SpriteBatch sb);

    protected void renderExtraPieces() {
    }

    public void transform(AbstractSquare oldSquare, AbstractSquare newSquare) {
        int i = squareList.indexOf(oldSquare);
        transform(i, newSquare);
    }

    public void transform(int index, AbstractSquare newSquare) {
        if (!(squareList.get(index) instanceof GoSquare)) {
            squareList.set(index, newSquare);
            newSquare.splat();
        }
    }

    public void move(AbstractSquare square) {
        move(((squareList.indexOf(square) - player.position) + squareList.size()) % squareList.size());
    }

    public void move(int amount) {
        if (shouldRender) {
            if (AbstractDungeon.player.hasPower(ImprisonedPower.POWER_ID)) {
                AbstractPower p = AbstractDungeon.player.getPower(ImprisonedPower.POWER_ID);
                p.flash();
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p.owner, p.owner, p, 1));
                return;
            } else if (AbstractDungeon.player.hasPower(AbandonGamePower.POWER_ID)) {
                AbstractPower p = AbstractDungeon.player.getPower(AbandonGamePower.POWER_ID);
                p.flash();
                AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(p.owner, amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                return;
            }

            AbstractSquare cursquare;
            int count = 1;
            AbstractPower pow = AbstractDungeon.player.getPower(KeyFinisherPower.POWER_ID);
            if (pow != null) {
                count += pow.amount;
            }

            AbstractDrone[] pieces = getPieces();
            ArrayUtils.reverse(pieces);
            for (AbstractDrone r : pieces) {
                for (int i = 1; i < amount; i++) {
                    //for(int j = 0; j < count; j++) {
                    squareList.get((r.position + i) % squareList.size()).onPassed();
                    //}
                }
                r.position = (r.position + amount) % squareList.size();
                cursquare = squareList.get(r.position);
                if (cursquare.ignoreNextTrigger) {
                    cursquare.ignoreNextTrigger = false;
                }
                else {
                    for (int i = 1; i < count; i++) {
                        cursquare.onLanded();
                    }
                    if (!(cursquare instanceof EmptySquare)) {
                        cursquare.splat();
                    }
                    cursquare.uponLand();
                }

                if (amount != 0) {
                    movePieces(amount, (int) cursquare.hb.cX, (int) cursquare.hb.cY, Math.min(amount / 10F, 0.66F), r);
                }
            }
        }
    }

    public static Color rainbow = new Color(1F, 0, 0, 1F);
    private static final float velocity = 0.5f;

    public AbstractDrone[] getPieces() {
        return new AbstractDrone[]{player};
    }

    protected abstract void movePieces(int jumpdistance, int x, int y, float speed, AbstractDrone piece);
}