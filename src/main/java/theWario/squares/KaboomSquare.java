package theWario.squares;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAndDeckAction;
import static theWario.WarioMod.theBoard;

import theWario.BanditBoard;
import theWario.cards.Kaboom;
import theWario.util.TextureLoader;

public class KaboomSquare extends AbstractSquare {

    public KaboomSquare(int x, int y) {
        super(x, y, "KaboomSquare");
        tex = TextureLoader.getTexture("wariomodResources/images/ui/KaboomSquare" + BanditBoard.artStyle + ".png");
        goodness = GOODNESS.GOOD;
    }

    public void onLanded() {
        att(new MakeTempCardInDiscardAndDeckAction(new Kaboom()));
    }

    public String getBodyText() {
        return TEXT[0] + 1 + TEXT[1];
    }
}
