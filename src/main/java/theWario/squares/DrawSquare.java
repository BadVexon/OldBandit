package theWario.squares;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import static theWario.WarioMod.theBoard;
import theWario.powers.DonateDrawPower;
import theWario.util.TextureLoader;

public class DrawSquare extends AbstractSquare {
    public DrawSquare(int x, int y) {
        super(x, y, "DrawSquare");
        tex = TextureLoader.getTexture("wariomodResources/images/ui/DrawSquare" + theBoard.artStyle + ".png");
        goodness = GOODNESS.GOOD;
    }

    @Override
    public void onPassed() {
        super.onPassed();
        if (adp().hasPower(DonateDrawPower.POWER_ID)) {
            adp().getPower(DonateDrawPower.POWER_ID).flash();
            att(new DrawCardAction(adp(), adp().getPower(DonateDrawPower.POWER_ID).amount));
        }
    }

    public void onLanded() {
        att(new DrawCardAction(adp(), 2));
    }

    public String getBodyText() {
        String s = TEXT[0] + 2 + TEXT[1];
        if (adp().hasPower(DonateDrawPower.POWER_ID)) {
            int x = adp().getPower(DonateDrawPower.POWER_ID).amount;
            if (x == 1)
                s = s + TEXT[2];
            else
                s = s + TEXT[3] + x + TEXT[1];
        }
        return s;
    }
}
