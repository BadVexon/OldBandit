package theWario.squares;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import static theWario.WarioMod.theBoard;

import theWario.BanditBoard;
import theWario.actions.TransformSquareAction;
import theWario.util.TextureLoader;

public class ArtifactSquare extends AbstractSquare {
    public ArtifactSquare(int x, int y) {
        super(x, y, "ArtifactSquare");
        tex = TextureLoader.getTexture("wariomodResources/images/ui/ArtifactSquare" + BanditBoard.artStyle + ".png");
        goodness = GOODNESS.GOOD;
        explodeOnUse = true;
    }

    public void onLanded() {
        att(new ApplyPowerAction(adp(), adp(), new ArtifactPower(adp(), 1), 1));
    }

    public String getBodyText() {
        return TEXT[0];
    }
}
