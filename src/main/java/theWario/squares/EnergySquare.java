package theWario.squares;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import static theWario.WarioMod.theBoard;

import theWario.BanditBoard;
import theWario.util.TextureLoader;

public class EnergySquare extends AbstractSquare {
    public EnergySquare(int x, int y) {
        super(x, y, "EnergySquare");
        tex = TextureLoader.getTexture("wariomodResources/images/ui/EnergySquare" + BanditBoard.artStyle + ".png");
        goodness = GOODNESS.GOOD;
    }

    public void onLanded() {
        att(new GainEnergyAction(1));
    }

    public String getBodyText() {
        return TEXT[0];
    }
}
