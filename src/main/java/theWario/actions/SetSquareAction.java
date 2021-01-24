package theWario.actions;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import theWario.squares.AbstractSquare;

import static theWario.WarioMod.theBoard;

public class SetSquareAction extends AbstractGameAction {
    private final float STARTING_DURATION = 0.1F;
    private Class<? extends AbstractSquare> clz;
    private int index;

    public SetSquareAction(Class<? extends AbstractSquare> clz, int index) {
        this.duration = STARTING_DURATION;

        this.clz = clz;
        this.index = index;
    }

    @Override
    public void update() {
        if (duration == STARTING_DURATION) {
            AbstractSquare square = theBoard.squareList.get(index);
            theBoard.transform(index, theBoard.swapSquare(clz, square.x, square.y));
        }
        this.tickDuration();
    }
}