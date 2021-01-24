package theWario.actions;

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theWario.BanditBoard;
import theWario.WarioMod;

public class GrandJourneyAction extends AbstractXAction {

    private int bonusAmt;

    public GrandJourneyAction(int bonusAmt) {
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
            for (int i = 0; i < amount; i++) {
                AbstractDungeon.actionManager.addToBottom(new MoveAction(1));
            }
        }

        this.isDone = true;
    }
}