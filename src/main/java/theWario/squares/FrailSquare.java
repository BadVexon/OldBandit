package theWario.squares;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import static theWario.WarioMod.theBoard;

import theWario.BanditBoard;
import theWario.util.TextureLoader;

public class FrailSquare extends AbstractSquare {
    public FrailSquare(int x, int y) {
        super(x, y, "FrailSquare");
        tex = TextureLoader.getTexture("wariomodResources/images/ui/FrailSquare" + BanditBoard.artStyle + ".png");
        goodness = GOODNESS.BAD;
    }

    public void onLanded() {
        att(new ApplyPowerAction(adp(), adp(), new FrailPower(adp(), 2, false), 1));
    }

    public String getBodyText() {
        return TEXT[0];
    }
}
