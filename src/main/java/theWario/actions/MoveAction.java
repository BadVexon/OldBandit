package theWario.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import static theWario.WarioMod.theBoard;

public class MoveAction extends AbstractGameAction {

    private int amt;

    public MoveAction(int amount) {
        amt = amount;
    }

    public void update() {
        theBoard.move(amt);
        isDone = true;
    }
}
