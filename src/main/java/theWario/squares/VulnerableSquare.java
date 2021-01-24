package theWario.squares;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theWario.util.TextureLoader;

import static theWario.WarioMod.theBoard;

public class VulnerableSquare extends AbstractSquare {
    public VulnerableSquare(int x, int y) {
        super(x, y, "VulnerableSquare");
        tex = TextureLoader.getTexture("wariomodResources/images/ui/VulnerableSquare" + theBoard.artStyle + ".png");
        goodness = GOODNESS.GOOD;
    }

    public void onLanded() {
        AbstractMonster m = AbstractDungeon.getRandomMonster();
        att(new ApplyPowerAction(m, adp(), new VulnerablePower(m, 2, false), 2));
    }

    public String getBodyText() {
        return TEXT[0];
    }
}
