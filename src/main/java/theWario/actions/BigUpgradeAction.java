package theWario.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theWario.powers.AbandonGamePower;
import theWario.powers.ImprisonedPower;

public class BigUpgradeAction extends AbstractGameAction {
    private BigUpgradeChoiceAction action;

    public BigUpgradeAction() {
    }

    public void update() {
        if (action == null) {
            action = new BigUpgradeChoiceAction();
        } else if (action.isDone) {
            this.isDone = true;
        }
    }
}
