package theWario.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.WarioMod;
import theWario.actions.TargetMoveAction;

import java.util.ArrayList;

public class DiceyMove extends AbstractWarioCard {

    public final static String ID = makeID("DiceyMove");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    public DiceyMove() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        showTileValue = true;
        baseMagicNumber = magicNumber = 2;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
        if (WarioMod.theBoard.shouldRender) atb(new TargetMoveAction(6));
    }

    @Override
    public ArrayList<Integer> showTileAmounts() {
        ArrayList<Integer> bruh = new ArrayList<>();
        bruh.add(1);
        bruh.add(2);
        bruh.add(3);
        bruh.add(4);
        bruh.add(5);
        bruh.add(6);
        return bruh;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }
}