package theWario.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theWario.squares.AbstractSquare;

import static theWario.WarioMod.theBoard;

public class FabulousPrizesAction extends AbstractXAction {

    private int bonusAmt;

    public FabulousPrizesAction(int bonusAmt) {
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
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 3));
            }
            int bmafd = amount;
            AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    for (int i = 1; i < bmafd + 1; i++) {
                        AbstractSquare s = theBoard.squareList.get((theBoard.player.position + i) % theBoard.squareList.size());
                        //theBoard.transform(s, theBoard.swapSquare(theBoard.getRandomRareSquare(), s.x, s.y));
                    }
                }
            });
        }

        this.isDone = true;
    }
}