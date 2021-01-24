package theWario.squares;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAndDeckAction;
import com.megacrit.cardcrawl.cards.status.Slimed;
import static theWario.WarioMod.theBoard;

import theWario.BanditBoard;
import theWario.util.TextureLoader;

public class SlimySquare extends AbstractSquare {
    public SlimySquare(int x, int y) {
        super(x, y, "SlimySquare");
        tex = TextureLoader.getTexture("wariomodResources/images/ui/SlimySquare" + BanditBoard.artStyle + ".png");
        goodness = GOODNESS.BAD;
    }

    public void onLanded() {
        att(new MakeTempCardInDiscardAndDeckAction(new Slimed()));
    }

    public String getBodyText() {
        return TEXT[0] + 1 + TEXT[1];
    }
}
