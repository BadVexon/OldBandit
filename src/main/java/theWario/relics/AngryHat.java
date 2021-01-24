package theWario.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theWario.WarioMod;
import theWario.squares.AbstractSquare;
import theWario.squares.EmptySquare;
import theWario.util.TextureLoader;

import java.util.ArrayList;

import static theWario.WarioMod.*;

public class AngryHat extends CustomRelic {

    public static final String ID = WarioMod.makeID("AngryHat");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("AngryHat.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Prayer_Bead.png"));

    public AngryHat() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                ArrayList<AbstractSquare> eligible = new ArrayList<>();
                for (AbstractSquare s : theBoard.squareList) {
                    if (s.goodness == AbstractSquare.GOODNESS.BAD) {
                        eligible.add(s);
                    }
                }
                if (!eligible.isEmpty()) {
                    AbstractSquare s = eligible.get(AbstractDungeon.cardRandomRng.random(eligible.size() - 1));
                    theBoard.transform(s, new EmptySquare(s.x, s.y));
                }
            }
        });
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
