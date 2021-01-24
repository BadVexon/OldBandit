package theWario.squares;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import theWario.BanditBoard;
import theWario.util.TextureLoader;

public class GhostySquare extends AbstractSquare {
    public GhostySquare(int x, int y) {
        super(x, y, "GhostySquare");
        tex = TextureLoader.getTexture("wariomodResources/images/ui/GhostySquare" + BanditBoard.artStyle + ".png");
        goodness = GOODNESS.GOOD;
        explodeOnUse = true;
    }

    public void onLanded() {
        att(new ApplyPowerAction(adp(), adp(), new IntangiblePlayerPower(adp(), 1), 1));
    }

    public String getBodyText() {
        return TEXT[0];
    }
}
