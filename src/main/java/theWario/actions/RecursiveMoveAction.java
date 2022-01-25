package theWario.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theWario.squares.EmptySquare;

import static theWario.WarioMod.theBoard;

public class RecursiveMoveAction extends AbstractGameAction {
    private int block;
    public RecursiveMoveAction(int block) {
        this.block = block;
    }

    public void update() {
        if (theBoard.squareList.get(theBoard.player.position + 1) instanceof EmptySquare) {
            AbstractDungeon.actionManager.addToTop(new RecursiveMoveAction(block));
        }
        addToTop(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                theBoard.move(1);
            }
        });
        AbstractDungeon.actionManager.addToTop(new GainBlockAction(AbstractDungeon.player, block));
        isDone = true;
    }
}
