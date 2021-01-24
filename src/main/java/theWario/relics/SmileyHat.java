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

public class SmileyHat extends CustomRelic {

    public static final String ID = WarioMod.makeID("SmileyHat");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("SmileyHat.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Prayer_Bead.png"));

    public SmileyHat() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.FLAT);
    }

    @Override
    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                ArrayList<AbstractSquare> eligible = new ArrayList<>();
                for (AbstractSquare s : theBoard.squareList) {
                    if (s instanceof EmptySquare) {
                        eligible.add(s);
                    }
                }
                if (!eligible.isEmpty()) {
                    AbstractSquare s = eligible.get(AbstractDungeon.cardRandomRng.random(eligible.size() - 1));
                    theBoard.transform(s, theBoard.swapSquare(theBoard.getRandomCommonSquare(), s.x, s.y));
                }
            }
        });
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
