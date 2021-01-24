package theWario.squares;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theWario.util.TextureLoader;

public class StrengthSquare extends AbstractSquare {
    public StrengthSquare(int x, int y) {
        super(x, y, "StrengthSquare");
        tex = TextureLoader.getTexture("wariomodResources/images/ui/StrengthSquare.png");
        goodness = GOODNESS.GOOD;
    }

    public void onLanded() {
        att(new ApplyPowerAction(adp(), adp(), new StrengthPower(adp(), 1), 1));
    }

    public String getBodyText() {
        return TEXT[0];
    }
}
