package theWario.squares;

import theWario.BanditBoard;
import theWario.util.TextureLoader;

public class EmptySquare extends AbstractSquare {
    public EmptySquare(int x, int y) {
        super(x, y, "EmptySquare");
        tex = TextureLoader.getTexture("wariomodResources/images/ui/EmptySquare" + BanditBoard.artStyle + ".png");
        goodness = GOODNESS.OKAY;
    }

    public void onLanded() {
    }

    public String getBodyText() {
        return TEXT[0];
    }
}
