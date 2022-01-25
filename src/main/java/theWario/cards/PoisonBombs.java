package theWario.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAndDeckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.powers.PoisonBombsPower;

public class PoisonBombs extends AbstractWarioCard {

    public final static String ID = makeID("PoisonBombs");

    //stupid intellij stuff POWER, RARE, SELF

    public PoisonBombs() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        atb(new MakeTempCardInDiscardAndDeckAction(new Kaboom()));
        applyToSelf(new PoisonBombsPower(magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }
}