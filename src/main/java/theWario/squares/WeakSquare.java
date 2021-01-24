package theWario.squares;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theWario.util.TextureLoader;

import static theWario.WarioMod.theBoard;

public class WeakSquare extends AbstractSquare {
    public WeakSquare(int x, int y) {
        super(x, y, "WeakSquare");
        tex = TextureLoader.getTexture("wariomodResources/images/ui/WeakSquare" + theBoard.artStyle + ".png");
        goodness = GOODNESS.GOOD;
    }

    public void onLanded() {
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m.isDying && !m.isDead)
                att(new ApplyPowerAction(m, adp(), new WeakPower(m, 1, false), 1));
        }

    }

    public String getBodyText() {
        return TEXT[0];
    }
}
