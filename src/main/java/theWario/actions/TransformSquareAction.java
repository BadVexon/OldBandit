package theWario.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import theWario.squares.AbstractSquare;

import static theWario.WarioMod.theBoard;

public class TransformSquareAction extends AbstractGameAction {

    public AbstractSquare byeByeSquare;
    public Class swapSquare;

    public TransformSquareAction(AbstractSquare bruh, Class wah) {
        byeByeSquare = bruh;
        swapSquare = wah;
    }

    @Override
    public void update() {
        theBoard.transform(theBoard.squareList.indexOf(byeByeSquare), theBoard.swapSquare(swapSquare, byeByeSquare.x, byeByeSquare.y));
        isDone = true;
    }
}
