package theWario.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.powers.TempestBlastsPower;
import theWario.squares.DamageSquare;

public class TempestBlasts extends AbstractWarioCard {

    public final static String ID = makeID("TempestBlasts");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    public TempestBlasts() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        transformEmpty(DamageSquare.class, 2);
        applyToSelf(new TempestBlastsPower(magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
        }
    }
}