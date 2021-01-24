package theWario.squares;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import theWario.BanditBoard;
import theWario.powers.GreaterGuardPower;
import theWario.util.TextureLoader;

public class BlockSquare extends AbstractSquare {
    public BlockSquare(int x, int y) {
        super(x, y, "BlockSquare");
        tex = TextureLoader.getTexture("wariomodResources/images/ui/BlockSquare" + BanditBoard.artStyle + ".png");
        goodness = GOODNESS.GOOD;
    }

    public void onLanded() {
        if (adp().hasPower(GreaterGuardPower.POWER_ID)) {
            att(new ApplyPowerAction(adp(), adp(), new NextTurnBlockPower(adp(), adp().getPower(GreaterGuardPower.POWER_ID).amount)));
        }
        att(new GainBlockAction(adp(), adp(), 4));
    }

    public String getBodyText() {
        if (adp().hasPower(GreaterGuardPower.POWER_ID)) {
            return TEXT[0] + adp().getPower(GreaterGuardPower.POWER_ID).amount + TEXT[1];
        } else {
            return TEXT[2] + 4 + TEXT[3];
        }
    }
}
