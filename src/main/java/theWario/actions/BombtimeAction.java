package theWario.actions;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theWario.cards.Kaboom;

public class  BombtimeAction extends AbstractXAction {

    private int bonusAmt;

    public BombtimeAction(int bonusAmt) {
        this.bonusAmt = bonusAmt;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
    }

    @Override
    public void initialize(int totalAmount) {
        super.initialize(totalAmount);
        this.amount += bonusAmt;
    }

    public void update() {
        if (amount > 0) {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Kaboom(), amount));
        }

        this.isDone = true;
    }
}