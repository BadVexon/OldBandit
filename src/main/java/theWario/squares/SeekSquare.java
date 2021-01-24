package theWario.squares;

import com.megacrit.cardcrawl.actions.defect.SeekAction;
import static theWario.WarioMod.theBoard;

import theWario.BanditBoard;
import theWario.util.TextureLoader;

public class SeekSquare extends AbstractSquare {
    public SeekSquare(int x, int y) {
        super(x, y, "SeekSquare");
        tex = TextureLoader.getTexture("wariomodResources/images/ui/SeekSquare" + BanditBoard.artStyle + ".png");
        goodness = GOODNESS.GOOD;
        explodeOnUse = true;
    }

    public void onLanded() {
        att(new SeekAction(1));
    }

    public String getBodyText() {
        return TEXT[0];
    }
}
