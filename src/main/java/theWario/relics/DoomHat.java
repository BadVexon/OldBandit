package theWario.relics;

import basemod.ReflectionHacks;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.TopPanel;
import theWario.WarioMod;
import theWario.squares.AbstractSquare;
import theWario.squares.DoomSquare;
import theWario.squares.EmptySquare;
import theWario.util.TextureLoader;

import java.util.ArrayList;

import static theWario.WarioMod.*;

public class DoomHat extends CustomRelic {

    public static final String ID = WarioMod.makeID("DoomHat");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("DoomHat.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Prayer_Bead.png"));

    public DoomHat() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.FLAT);
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.energy.energyMaster++;
        ReflectionHacks.setPrivate(AbstractDungeon.topPanel, TopPanel.class, "title", "");
    }

    @Override
    public void onUnequip() {
        AbstractDungeon.player.energy.energyMaster--;
    }

    @Override
    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                ArrayList<AbstractSquare> done = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    ArrayList<AbstractSquare> eligible = new ArrayList<>();
                    for (AbstractSquare s : theBoard.squareList) {
                        if (s instanceof EmptySquare && !done.contains(s) && !(theBoard.swapSquare(DoomSquare.class, 0, 0).getClass().getSimpleName().equals(s.getClass().getSimpleName()))) {
                            eligible.add(s);
                        }
                    }
                    if (!eligible.isEmpty()) {
                        AbstractSquare s = eligible.get(AbstractDungeon.cardRandomRng.random(eligible.size() - 1));
                        done.add(s);
                        theBoard.transform(s, theBoard.swapSquare(DoomSquare.class, s.x, s.y));
                    }
                }
            }
        });
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
