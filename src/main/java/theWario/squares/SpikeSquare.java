package theWario.squares;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import theWario.util.TextureLoader;

import static theWario.WarioMod.theBoard;

public class SpikeSquare extends AbstractSquare {
    public SpikeSquare(int x, int y) {
        super(x, y, "SpikeSquare");
        tex = TextureLoader.getTexture("wariomodResources/images/ui/SpikeSquare" + theBoard.artStyle + ".png");
        goodness = GOODNESS.BAD;
    }

    public void onLanded() {
        att(new DamageAction(adp(), new DamageInfo(adp(), 6, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    public String getBodyText() {
        return TEXT[0] + 6 + TEXT[1];
    }
}
