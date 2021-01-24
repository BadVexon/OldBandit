package theWario.squares;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import static theWario.WarioMod.theBoard;

import theWario.BanditBoard;
import theWario.powers.ImprisonedPower;
import theWario.util.TextureLoader;

import static theWario.WarioMod.theBoard;

public class JailSquare extends AbstractSquare {
    public JailSquare(int x, int y) {
        super(x, y, "JailSquare");
        tex = TextureLoader.getTexture("wariomodResources/images/ui/JailSquare" + BanditBoard.artStyle + ".png");
        goodness = GOODNESS.BAD;
    }

    public void onLanded() {
        att(new ApplyPowerAction(adp(), adp(), new ImprisonedPower(3), 3));
    }

    public String getBodyText() {
        return TEXT[0];
    }
}
