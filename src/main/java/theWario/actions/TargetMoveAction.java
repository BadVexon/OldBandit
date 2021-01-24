package theWario.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class TargetMoveAction extends AbstractGameAction {
    private MoveChoiceAction action;

    public TargetMoveAction(int amount2) {
        action = null;
        amount = amount2;
    }

    public void update() {
        if (action == null) {
            action = new MoveChoiceAction(amount);
        } else if (action.isDone) {
            this.isDone = true;
        }
    }
}
