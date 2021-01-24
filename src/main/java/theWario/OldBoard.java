
/*
package theWario;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import theWario.actions.WeirdMoveGuyAction;
import theWario.powers.KeyFinisherPower;
import theWario.relics.GlassHat;
import theWario.relics.PropellerHat;
import theWario.squares.*;
import theWario.util.AbstractDrone;
import theWario.util.TextureLoader;

import java.awt.*;
import java.util.ArrayList;

import static theWario.WarioMod.renderStuff;
import static theWario.WarioMod.theBoard;

public class theBoard {
    public static ArrayList<AbstractSquare> squareList = new ArrayList<>();

    public static Texture playerPiece = TextureLoader.getTexture("wariomodResources/images/ui/piecePlayer1.png");

    public static Texture dronePiece = TextureLoader.getTexture("wariomodResources/images/ui/pieceDrone.png");


    public int player.position;

    public Point playerLocation = new Point(0, 0);

    public boolean shouldRender = false;

    public ArrayList<AbstractDrone> droneList = new ArrayList<>();

    public static Texture goodOutline = TextureLoader.getTexture("wariomodResources/images/ui/GoodOutline" + 2 + ".png");
    public static Texture neutralOutline = TextureLoader.getTexture("wariomodResources/images/ui/NeutralOutline" + 2 + ".png");
    public static Texture badOutline = TextureLoader.getTexture("wariomodResources/images/ui/BadOutline" + 2 + ".png");
    public static Texture activatesWhenPassedOutline = TextureLoader.getTexture("wariomodResources/images/ui/ActivatesWhenPassedOutline" + 2 + ".png");
    public static Texture deathOutline = TextureLoader.getTexture("wariomodResources/images/ui/DeathOutline" + 2 + ".png");
    public static Texture onOutline = TextureLoader.getTexture("wariomodResources/images/ui/OnOutline" + 2 + ".png");
    public static Texture canPickOutline = TextureLoader.getTexture("wariomodResources/images/ui/SelectableOutline" + 2 + ".png");


    public theBoard() {
    }

    public void init() {
        player.position = 0;
        goodOutline = TextureLoader.getTexture("wariomodResources/images/ui/GoodOutline" + 2 + ".png");
        neutralOutline = TextureLoader.getTexture("wariomodResources/images/ui/NeutralOutline" + 2 + ".png");
        badOutline = TextureLoader.getTexture("wariomodResources/images/ui/BadOutline" + 2 + ".png");
        activatesWhenPassedOutline = TextureLoader.getTexture("wariomodResources/images/ui/ActivatesWhenPassedOutline" + 2 + ".png");
        deathOutline = TextureLoader.getTexture("wariomodResources/images/ui/DeathOutline" + 2 + ".png");
        onOutline = TextureLoader.getTexture("wariomodResources/images/ui/OnOutline" + 2 + ".png");
        canPickOutline = TextureLoader.getTexture("wariomodResources/images/ui/SelectableOutline" + 2 + ".png");
        squareList.clear();
        droneList.clear();
        ArrayList<String> bruh = new ArrayList<>();
        for (int i = 0; i < 12; i++) bruh.add("EMPTY");
        for (int i = 0; i < 6; i++) bruh.add(getRandomCommonSquare());
        for (int i = 0; i < 4; i++) bruh.add(getRandomBadSquare());
        for (int i = 0; i < 1; i++) bruh.add(getRandomRareSquare());
        if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss) {
            int x = (int) (Settings.WIDTH / 3.25);
            int y = (int) (Settings.HEIGHT / 1.75);
            AbstractSquare s = new GoSquare(x, y);
            squareList.add(s);
            playerLocation.move((int) s.hb.cX, (int) s.hb.cY);
            System.out.println(x + ", " + y);
            int kwab = 8;
            if (AbstractDungeon.player.hasRelic(GlassHat.ID))
                kwab -= 2;
            for (int q = 0; q < kwab; q++) {
                x += 64 * Settings.scale;
                squareList.add(swapSquare(bruh.remove(AbstractDungeon.cardRandomRng.random(bruh.size() - 1)), x, y));
            }
            for (int q = 0; q < 4; q++) {
                y += 64 * Settings.scale;
                squareList.add(swapSquare(bruh.remove(AbstractDungeon.cardRandomRng.random(bruh.size() - 1)), x, y));
            }
            for (int q = 0; q < kwab; q++) {
                x = (int) (Math.ceil(x - (64.0F * Settings.scale)));
                squareList.add(swapSquare(bruh.remove(AbstractDungeon.cardRandomRng.random(bruh.size() - 1)), x, y));
            }
            for (int q = 0; q < 3; q++) {
                y = (int) (Math.ceil(y - (64.0F * Settings.scale)));
                squareList.add(swapSquare(bruh.remove(AbstractDungeon.cardRandomRng.random(bruh.size() - 1)), x, y));
            }
        } else {
            int i = 23;
            if (AbstractDungeon.player.hasRelic(GlassHat.ID))
                i -= 4;
            int x = (int) ((Settings.WIDTH - ((64 * Settings.scale) * i)) / 2);
            int y = (Settings.HEIGHT / 2) + (int) ((128 + 64) * Settings.scale);
            AbstractSquare s = new GoSquare(x, y);
            squareList.add(s);
            playerLocation.move((int) s.hb.cX, (int) s.hb.cY);
            for (int q = 0; q < i; q++) {
                x += 64 * Settings.scale;
                squareList.add(swapSquare(bruh.remove(AbstractDungeon.cardRandomRng.random(bruh.size() - 1)), x, y));
            }
        }

        shouldRender = true;
    }

    public static String getRandomCommonSquare() {
        ArrayList<String> listString = new ArrayList<>();
        listString.add("DAMAGE");
        listString.add("BLOCK");
        listString.add("DRAW");
        listString.add("WEAK");
        listString.add("VULNERABLE");
        listString.add("ENERGY");
        listString.add("STRENGTH");
        listString.add("KABOOM");
        listString.add("UPGRADE");
        return listString.get(AbstractDungeon.cardRandomRng.random(listString.size() - 1));
    }

    public static String getRandomBadSquare() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("SPIKE");
        stringList.add("VOID");
        stringList.add("SLIMED");
        stringList.add("FRAIL");
        stringList.add("JAIL");
        stringList.add("GREMLIN");
        return stringList.get(AbstractDungeon.cardRandomRng.random(stringList.size() - 1));
    }

    public static String getRandomRareSquare() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("GHOSTY");
        stringList.add("SEEK");
        stringList.add("STUN");
        stringList.add("ADRENALINE");
        stringList.add("ARTIFACT");
        return stringList.get(AbstractDungeon.cardRandomRng.random(stringList.size() - 1));
    }

    public static AbstractSquare swapSquare(String square, int x, int y) {
        switch (square) {
            case "DAMAGE":
                return new DamageSquare(x, y);
            case "BLOCK":
                return new BlockSquare(x, y);
            case "EMPTY":
                return new EmptySquare(x, y);
            case "DEATH":
                return new DoomSquare(x, y);
            case "SPIKE":
                return new SpikeSquare(x, y);
            case "DRAW":
                return new DrawSquare(x, y);
            case "SLIMED":
                return new SlimySquare(x, y);
            case "VOID":
                return new VoidSquare(x, y);
            case "GHOSTY":
                return new GhostySquare(x, y);
            case "STUN":
                return new StunSquare(x, y);
            case "WEAK":
                return new WeakSquare(x, y);
            case "VULNERABLE":
                return new VulnerableSquare(x, y);
            case "ENERGY":
                return new EnergySquare(x, y);
            case "FRAIL":
                return new FrailSquare(x, y);
            case "STRENGTH":
                return new StrengthSquare(x, y);
            case "SUPERDRAW":
                return new SuperDrawSquare(x, y);
            case "KABOOM":
                return new KaboomSquare(x, y);
            case "GO":
                return new GoSquare(x, y);
            case "JAIL":
                return new JailSquare(x, y);
            case "BUFFER":
                return new BufferSquare(x, y);
            case "ADRENALINE":
                return new AdrenalineSquare(x, y);
            case "ARTIFACT":
                return new ArtifactSquare(x, y);
            case "SEEK":
                return new SeekSquare(x, y);
            case "UPGRADE":
                return new UpgradeSquare(x, y);
            case "GREMLIN":
                return new GremlinSquare(x, y);
            case "FUSED":
                return new FusedSquare(x, y, swapSquare(getRandomCommonSquare(), x, y), swapSquare(getRandomCommonSquare(), x, y));
            case "VOLCANIC":
                return new VolcanoSquare(x, y);
        }
        return new EmptySquare(x, y);
    }

    public void render(SpriteBatch sb) {
        if (shouldRender) {
            sb.setColor(Color.WHITE.cpy());
            for (AbstractSquare s : squareList) {
                s.render(sb);
                if (s.hb.hovered) {
                    String q = s.getBodyText();
                    if (!(s instanceof GoSquare) && s.triggersWhenPassed)
                        q = q.replaceAll("When landed upon", "When landed upon or #gpassed");
                    if ((float) InputHelper.mX < 1400.0F * Settings.scale) {
                        TipHelper.renderGenericTip(
                                (float) InputHelper.mX + 60.0F * Settings.scale, (float) InputHelper.mY - 50.0F * Settings.scale,
                                s.getClass().getSimpleName().replaceAll("([^_])([A-Z])", "$1 $2").replaceAll("Square", "Space"),
                                q // TODO: bruh - Edan
                        );
                    } else {
                        TipHelper.renderGenericTip((float) InputHelper.mX - 350.0F * Settings.scale, (float) InputHelper.mY - 50.0F * Settings.scale,
                                s.getClass().getSimpleName().replaceAll("([^_])([A-Z])", "$1 $2").replaceAll("Square", "Space"),
                                q);
                    }
                }
            }
            sb.draw(playerPiece, playerLocation.x - ((playerPiece.getWidth() * Settings.scale) / 2F), playerLocation.y - ((playerPiece.getHeight() * Settings.scale) / 2F), playerPiece.getWidth() / 2F, playerPiece.getHeight() / 2F, playerPiece.getWidth(), playerPiece.getHeight(), Settings.scale, Settings.scale, 0, 0, 0, playerPiece.getWidth(), playerPiece.getHeight(), false, false);
            if (!droneList.isEmpty()) {
                for (AbstractDrone r : droneList)
                    sb.draw(dronePiece, r.location.x - ((dronePiece.getWidth() * Settings.scale) / 2F), r.location.y - ((dronePiece.getHeight() * Settings.scale) / 2F), dronePiece.getWidth() / 2F, dronePiece.getHeight() / 2F, dronePiece.getWidth(), dronePiece.getHeight(), Settings.scale, Settings.scale, 0, 0, 0, dronePiece.getWidth(), dronePiece.getHeight(), false, false);
            }
        }
    }

    public void transform(AbstractSquare oldSquare, AbstractSquare newSquare) {
        int i = squareList.indexOf(oldSquare);
        transform(i, newSquare);
    }

    public void transform(int index, AbstractSquare newSquare) {
        if (!(squareList.get(index) instanceof GoSquare)) {
            squareList.set(index, newSquare);
            squareList.get(index).splat();
        }
    }

    public void move(int amount) {
        if (!droneList.isEmpty()) {
            int movement;
            int target;
            for (AbstractDrone r : droneList) {
                movement = Integer.signum(amount);
                int bruh2 = r.position;
                target = (r.position + amount + squareList.size()) % squareList.size();
                if (movement != 0)
                    for (r.position = (r.position + movement + squareList.size()) % squareList.size();
                         r.position != target;
                         r.position = (r.position + movement + squareList.size()) % squareList.size()) {
                        if (movement > 0)
                            squareList.get(r.position).onPassed();
                    }
                if (!(squareList.get(r.position) instanceof EmptySquare))
                    squareList.get(r.position).splat();
                squareList.get(r.position).uponLand();
                if (AbstractDungeon.player.hasPower(KeyFinisherPower.POWER_ID) && squareList.get(r.position).goodness == AbstractSquare.GOODNESS.GOOD) {
                    AbstractDungeon.player.getPower(KeyFinisherPower.POWER_ID).flash();
                    squareList.get(r.position).splat();
                    squareList.get(r.position).uponLand();
                }
                if (amount != 0) {
                    if (bruh2 > r.position && !(AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss)) {
                        AbstractDungeon.actionManager.addToTop(new WeirdMoveGuyAction((int) squareList.get(r.position).hb.cX, (int) squareList.get(r.position).hb.cY, Math.min(amount / 10F, 0.66F), r));
                        AbstractDungeon.actionManager.addToTop(new AbstractGameAction() {
                            @Override
                            public void update() {
                                isDone = true;
                                r.location.move(0, r.location.y);
                            }
                        });
                        AbstractDungeon.actionManager.addToTop(new WeirdMoveGuyAction(Settings.WIDTH, (int) squareList.get(r.position).hb.cY, Math.min(amount / 10F, 0.66F), r));
                    } else
                        AbstractDungeon.actionManager.addToTop(new WeirdMoveGuyAction((int) squareList.get(r.position).hb.cX, (int) squareList.get(r.position).hb.cY, Math.min(amount / 10F, 0.66F), r));
                }
            }
        }
        if (!(AbstractDungeon.player instanceof TheBandit) && !WarioMod.renderStuff)
            renderStuff = true;
        int movement = Integer.signum(amount);
        int bruh = player.position;
        int target = (player.position + amount + squareList.size()) % squareList.size();
        if (movement != 0)
            for (player.position = (player.position + movement + squareList.size()) % squareList.size();
                 player.position != target;
                 player.position = (player.position + movement + squareList.size()) % squareList.size()) {
                if (movement > 0) {
                    squareList.get(player.position).onPassed();
                    if (AbstractDungeon.player.hasRelic(PropellerHat.ID) && squareList.get(player.position).goodness == AbstractSquare.GOODNESS.GOOD) {
                        if (!AbstractDungeon.player.getRelic(PropellerHat.ID).checkTrigger()) {
                            ((PropellerHat) AbstractDungeon.player.getRelic(PropellerHat.ID)).activatedThisTurn = true;
                            squareList.get(player.position).uponLand();
                        }
                    }
                }
            }
        if (!(squareList.get(player.position) instanceof EmptySquare))
            squareList.get(player.position).splat();
        squareList.get(player.position).uponLand();
        if (AbstractDungeon.player.hasPower(KeyFinisherPower.POWER_ID) && squareList.get(player.position).goodness == AbstractSquare.GOODNESS.GOOD) {
            AbstractDungeon.player.getPower(KeyFinisherPower.POWER_ID).flash();
            squareList.get(player.position).splat();
            squareList.get(player.position).uponLand();
        }
        if (bruh > player.position && !(AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss)) {
            AbstractDungeon.actionManager.addToTop(new WeirdMoveGuyAction((int) squareList.get(player.position).hb.cX, (int) squareList.get(player.position).hb.cY, Math.min(amount / 10F, 0.66F), null));
            AbstractDungeon.actionManager.addToTop(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    playerLocation.move(0, playerLocation.y);
                }
            });
            AbstractDungeon.actionManager.addToTop(new WeirdMoveGuyAction(Settings.WIDTH, (int) squareList.get(player.position).hb.cY, Math.min(amount / 10F, 0.66F), null));
        } else
            AbstractDungeon.actionManager.addToTop(new WeirdMoveGuyAction((int) squareList.get(player.position).hb.cX, (int) squareList.get(player.position).hb.cY, Math.min(amount / 10F, 0.66F), null));
    }

    public void move(AbstractSquare square) {
        move(((theBoard.squareList.indexOf(square) - theBoard.player.position) + theBoard.squareList.size()) % theBoard.squareList.size());
    }
}
*/