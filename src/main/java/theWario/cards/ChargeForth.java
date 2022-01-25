package theWario.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.WarioMod;
import theWario.actions.RecursiveMoveAction;
import theWario.squares.EmptySquare;

import java.util.ArrayList;

import static theWario.WarioMod.theBoard;

public class ChargeForth extends AbstractWarioCard {

    public final static String ID = makeID("ChargeForth");

    //stupid intellij stuff SKILL, SELF, COMMON

    public ChargeForth() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        showTileValue = true;
        baseBlock = 1;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        if (WarioMod.theBoard.shouldRender) atb(new RecursiveMoveAction(block));
        if (upgraded) {
            atb(new DrawCardAction(p, 1));
        }
    }

    @Override
    public ArrayList<Integer> showTileAmounts() {
        ArrayList<Integer> bruh = new ArrayList<>();
        boolean wah = true;
        int i = 1;
        while (wah) {
            if (theBoard.squareList.get((theBoard.player.position + i) % theBoard.squareList.size()) instanceof EmptySquare) {
                bruh.add(i);
                i++;
            } else {
                bruh.add(i);
                wah = false;
            }
        }
        return bruh;
    }


    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(1);
        }
    }
}