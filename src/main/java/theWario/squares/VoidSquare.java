package theWario.squares;

import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import static theWario.WarioMod.theBoard;

import theWario.BanditBoard;
import theWario.util.TextureLoader;

public class VoidSquare extends AbstractSquare {
    public VoidSquare(int x, int y) {
        super(x, y, "VoidSquare");
        tex = TextureLoader.getTexture("wariomodResources/images/ui/VoidSquare" + BanditBoard.artStyle + ".png");
        goodness = GOODNESS.BAD;
    }

    public void onLanded() {
        att(new LoseEnergyAction(1));
    }

    public String getBodyText() {
        return TEXT[0];
    }
}
