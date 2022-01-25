package theWario.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.actions.SetCardTargetCoordinatesAction;
import theWario.actions.TargetMoveAction;

import java.util.ArrayList;

public class Step extends AbstractWarioCard {

    public final static String ID = makeID("Step");

    //stupid intellij stuff SKILL, SELF, BASIC

    public Step() {
        super(ID, 0, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        showTileValue = true;
        baseMagicNumber = magicNumber = 2;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        atb(new TargetMoveAction(magicNumber));
    }

    @Override
    public ArrayList<Integer> showTileAmounts() {
        ArrayList<Integer> bruh = new ArrayList<>();
        for (int i = 0; i < magicNumber; i++) {
            bruh.add(i+1);
        }
        return bruh;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
        }
    }
}