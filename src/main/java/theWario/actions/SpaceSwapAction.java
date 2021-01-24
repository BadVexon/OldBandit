package theWario.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.helpers.Hitbox;
import theWario.squares.AbstractSquare;

import java.util.Collections;

import static theWario.WarioMod.theBoard;

public class SpaceSwapAction extends AbstractGameAction {
    private SwappingSpaceAction action;
    private SwappingSpaceAction action2;
    public AbstractSquare swap1;
    public AbstractSquare swap2;

    public SpaceSwapAction() {
        action = null;
        action2 = null;
    }

    public void update() {
        if (action == null) {
            action = new SwappingSpaceAction(this);
        } else if (action.isDone && action2 == null) {
            action2 = new SwappingSpaceAction(this);
        } else if (action.isDone && action2.isDone) {
            isDone = true;
            Collections.swap(theBoard.squareList, theBoard.squareList.indexOf(swap1), theBoard.squareList.indexOf(swap2));
            int bruh = swap1.x;
            int wah = swap1.y;
            Hitbox buuuuuu = swap1.hb;
            swap1.x = swap2.x;
            swap1.y = swap2.y;
            swap1.hb = swap2.hb;
            swap2.x = bruh;
            swap2.y = wah;
            swap2.hb = buuuuuu;
        }
    }
}
