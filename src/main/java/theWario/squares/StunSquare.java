package theWario.squares;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theWario.actions.TransformSquareAction;
import theWario.util.TextureLoader;

public class StunSquare extends AbstractSquare {
    public StunSquare(int x, int y) {
        super(x, y, "StunSquare");
        tex = TextureLoader.getTexture("wariomodResources/images/ui/StunSquare.png");
        goodness = GOODNESS.GOOD;
        explodeOnUse = true;
    }

    public void onLanded() {
        att(new StunMonsterAction(AbstractDungeon.getRandomMonster(), adp()));
    }

    public String getBodyText() {
        return TEXT[0];
    }
}
