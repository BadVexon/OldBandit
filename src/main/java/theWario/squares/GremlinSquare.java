package theWario.squares;

import theWario.actions.SummonGremlinAction;
import theWario.util.TextureLoader;

public class GremlinSquare extends AbstractSquare {
    public GremlinSquare(int x, int y) {
        super(x, y, "GremlinSquare");
        tex = TextureLoader.getTexture("wariomodResources/images/ui/GremlinSquare.png");
        goodness = GOODNESS.BAD;
        explodeOnUse = true;
    }

    public void onLanded() {
        att(new SummonGremlinAction());
    }

    public String getBodyText() {
        return TEXT[0];
    }
}
