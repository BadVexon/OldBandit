package theWario.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theWario.squares.EmptySquare;

import static theWario.WarioMod.theBoard;

public class RecursiveMoveAction extends AbstractGameAction {

    public RecursiveMoveAction() {
    }

    public void update() {
        theBoard.move(1);
        if (theBoard.squareList.get(theBoard.player.position) instanceof EmptySquare) {
            AbstractDungeon.actionManager.addToTop(new RecursiveMoveAction());
        }
        isDone = true;
    }
}
