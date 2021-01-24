package theWario;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.vfx.combat.HemokinesisEffect;
import org.apache.commons.lang3.ArrayUtils;
import theWario.actions.AddSquareAction;
import theWario.actions.SetSquareAction;
import theWario.actions.WeirdMoveGuyAction;
import theWario.cards.AbstractWarioCard;
import theWario.powers.ImprisonedPower;
import theWario.relics.GlassHat;
import theWario.squares.*;
import theWario.util.AbstractDrone;
import theWario.util.TextureLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BanditBoard extends RazsBoard {

    public static int artStyle = 0;
    public static boolean LINE_BOARD = false;

    public ArrayList<AbstractDrone> droneList = new ArrayList<>();

    public Texture dronePiece = TextureLoader.getTexture("wariomodResources/images/ui/pieceDrone.png");

    public Map<Integer, Integer> renderNumbers;

    public BanditBoard() {
        this.renderNumbers = new HashMap<>();
    }

    public AbstractSquare getPlayerSquare() {
        return squareList.get(player.position);
    }

    public void init() {
        super.init();

        droneList.clear();
        ArrayList<Class<? extends AbstractSquare>> bruh = new ArrayList<>();
        for (int i = 0; i < 3; i++) bruh.add(getRandomCommonSquare());
        for (int i = 0; i < 3; i++) bruh.add(getRandomBadSquare());

        if (!LINE_BOARD) {
            int kwab = 10;
            if (AbstractDungeon.player.hasRelic(GlassHat.ID))
                kwab -= 2;
            int x = (int) (Settings.WIDTH / 2F - squareOffset * (kwab + 1) / 2F);
            int y = (int) (Settings.HEIGHT / 1.75);
            final AbstractSquare s = new GoSquare(x, y);
            AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                @Override
                public void update() {
                    this.tickDuration();
                    if (this.isDone) {
                        squareList.add(s);
                    }
                }
            });
            player.location.move((int) s.hb.cX, (int) s.hb.cY + Settings.HEIGHT);

            ArrayList<Integer> slots = new ArrayList<>();
            for (int q = 0; q < kwab; q++) {
                x += squareOffset;
                AbstractDungeon.actionManager.addToBottom(new AddSquareAction(EmptySquare.class, x, y));
                slots.add(slots.size() + 1);
            }
            for (int q = 0; q < 4; q++) {
                y += squareOffset;
                AbstractDungeon.actionManager.addToBottom(new AddSquareAction(EmptySquare.class, x, y));
                slots.add(slots.size() + 1);
            }
            for (int q = 0; q < kwab; q++) {
                x = (int) (Math.ceil(x - squareOffset));
                AbstractDungeon.actionManager.addToBottom(new AddSquareAction(EmptySquare.class, x, y));
                slots.add(slots.size() + 1);
            }
            for (int q = 0; q < 3; q++) {
                y = (int) (Math.ceil(y - squareOffset));
                AbstractDungeon.actionManager.addToBottom(new AddSquareAction(EmptySquare.class, x, y));
                slots.add(slots.size() + 1);
            }

            do {
                AbstractDungeon.actionManager.addToBottom(new SetSquareAction(bruh.remove(AbstractDungeon.cardRandomRng.random(bruh.size() - 1)), slots.remove(AbstractDungeon.cardRandomRng.random(slots.size() - 1))));
            } while (!bruh.isEmpty());

            AbstractDungeon.actionManager.addToBottom(new WeirdMoveGuyAction(player.location.x, player.location.y - Settings.HEIGHT, 0.5F, player));
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new HemokinesisEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, player.location.x, player.location.y - Settings.HEIGHT)));
            AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                @Override
                public void update() {
                    finishedSetup = true;
                    this.isDone = true;
                }
            });
        } else {
            int i = 30;
            if (AbstractDungeon.player.hasRelic(GlassHat.ID))
                i -= 5;
            int x = (int) ((Settings.WIDTH - ((64 * Settings.scale) * i)) / 2);
            int y = (Settings.HEIGHT / 2) + (int) ((128 + 64) * Settings.scale);
            final AbstractSquare s = new GoSquare(x, y);
            AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                @Override
                public void update() {
                    this.tickDuration();
                    if (this.isDone) {
                        squareList.add(s);
                    }
                }
            });
            player.location.move((int) s.hb.cX, (int) s.hb.cY + Settings.HEIGHT);

            ArrayList<Integer> slots = new ArrayList<>();
            for (int q = 0; q < i; q++) {
                x += 64 * Settings.scale;
                AbstractDungeon.actionManager.addToBottom(new AddSquareAction(EmptySquare.class, x, y));
                slots.add(slots.size() + 1);
            }

            do {
                AbstractDungeon.actionManager.addToBottom(new SetSquareAction(bruh.remove(AbstractDungeon.cardRandomRng.random(bruh.size() - 1)), slots.remove(AbstractDungeon.cardRandomRng.random(slots.size() - 1))));
            } while (!bruh.isEmpty());

            AbstractDungeon.actionManager.addToBottom(new WeirdMoveGuyAction(player.location.x, player.location.y - Settings.HEIGHT, 0.5F, player));
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new HemokinesisEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, player.location.x, player.location.y - Settings.HEIGHT)));
            AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                @Override
                public void update() {
                    finishedSetup = true;
                    this.isDone = true;
                }
            });
        }


        shouldRender = true;
    }

    public Class<? extends AbstractSquare> getRandomCommonSquare() {
        return this.getRandomCommonSquare(AbstractDungeon.cardRandomRng);
    }

    public Class<? extends AbstractSquare> getRandomCommonSquare(Random rng) {
        ArrayList<Class<? extends AbstractSquare>> list = new ArrayList<>();
        list.add(DamageSquare.class);
        list.add(BlockSquare.class);
        list.add(DrawSquare.class);
        list.add(WeakSquare.class);
        list.add(VulnerableSquare.class);
        list.add(KaboomSquare.class);
        list.add(UpgradeSquare.class);
        return list.get(rng.random(list.size() - 1));
    }

    public Class<? extends AbstractSquare> getRandomBadSquare() {
        return this.getRandomBadSquare(AbstractDungeon.cardRandomRng);
    }

    public Class<? extends AbstractSquare> getRandomBadSquare(Random rng) {
        ArrayList<Class<? extends AbstractSquare>> list = new ArrayList<>();
        list.add(SpikeSquare.class);
        list.add(VoidSquare.class);
        list.add(SlimySquare.class);
        list.add(FrailSquare.class);
        list.add(GremlinSquare.class);
        return list.get(rng.random(list.size() - 1));
    }

    @Override
    public void render(SpriteBatch sb) {
        if (shouldRender) {
            super.render(sb);
            if (!droneList.isEmpty()) {
                for (AbstractDrone r : droneList) {
                    sb.draw(dronePiece, r.location.x - ((dronePiece.getWidth() * Settings.scale) / 2F), r.location.y - ((dronePiece.getHeight() * Settings.scale) / 2F), dronePiece.getWidth() / 2F, dronePiece.getHeight() / 2F, dronePiece.getWidth(), dronePiece.getHeight(), Settings.scale, Settings.scale, 0, 0, 0, dronePiece.getWidth(), dronePiece.getHeight(), false, false);
                }
            }
        }
    }

    @Override
    protected void renderBoardNumbers(SpriteBatch sb) {
        int curOffset = 0;
        ArrayList<Integer> motion = new ArrayList<>();
        if (AbstractDungeon.player.hoveredCard != null && !AbstractDungeon.player.hasPower(ImprisonedPower.POWER_ID)) {
            if (AbstractDungeon.player.hoveredCard instanceof AbstractWarioCard) {
                motion = ((AbstractWarioCard) (AbstractDungeon.player.hoveredCard)).showTileAmounts();
            }
        }
        AbstractDrone[] pieceList = getPieces();
        ArrayUtils.reverse(pieceList);
        ArrayList<Integer> takenNumbers = new ArrayList<>();
        for (final AbstractDrone piece : pieceList) {
            this.renderNumbersFromPosition(sb, piece.position + curOffset, motion, takenNumbers, Color.PURPLE, Color.WHITE);
        }
    }

    @Override
    public AbstractDrone[] getPieces() {
        AbstractDrone[] list = new AbstractDrone[droneList.size() + 1];
        int count = 1;
        for (final AbstractDrone d : droneList) {
            list[count++] = d;
        }
        list[0] = player;
        return list;
    }

    @Override
    protected void movePieces(int jumpdistance, int x, int y, float speed, AbstractDrone piece) {
        AbstractDungeon.actionManager.addToTop(new WeirdMoveGuyAction(x, y, speed, piece));
    }

    public void triggerNextSquares(int amountOfSquares, int amountOfTriggers) {
        AbstractSquare Square;
        for (final AbstractDrone piece : getPieces()) {
            for (int found = 0; found < amountOfSquares; ) {
                for (int i = 1; found < amountOfSquares; i++) {
                    Square = squareList.get((piece.position + i) % squareList.size());
                    if (!(Square instanceof EmptySquare)) {
                        found++;
                        for (int j = 1; j < amountOfTriggers; j++) {
                            Square.onLanded();
                        }
                        Square.uponLand();
                    }
                }
            }
        }
    }
}