package theWario.actions;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import theWario.squares.AbstractSquare;

import static theWario.WarioMod.theBoard;

public class AddSquareAction extends AbstractGameAction {
    private final float STARTING_DURATION = 0.05F;
    private Class<? extends AbstractSquare> clz;
    private int x, y;

    public AddSquareAction(Class<? extends AbstractSquare> clz, int x, int y) {
        this.duration = STARTING_DURATION;

        this.clz = clz;
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {
        if (this.duration == STARTING_DURATION) {
            theBoard.squareList.add(theBoard.swapSquare(clz, x, y));
        }
        this.tickDuration();
    }
}