package theWario.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theWario.powers.AbandonGamePower;
import theWario.powers.ImprisonedPower;

import static theWario.WarioMod.theBoard;

public class TargetAnyMoveAction extends AbstractGameAction {
    private MoveAnywhereChoiceAction action;

    public TargetAnyMoveAction() {
    }

    public void update() {
        if (action == null) {
            action = new MoveAnywhereChoiceAction();
        } else {
            if (action.isDone) {
                this.isDone = true;
            }
        }
    }
}
