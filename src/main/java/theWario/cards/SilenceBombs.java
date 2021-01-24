package theWario.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAndDeckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.powers.SilenceBombsPower;

public class SilenceBombs extends AbstractWarioCard {

    public final static String ID = makeID("SilenceBombs");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    private static final int MAGIC = 1;

    public SilenceBombs() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded)
            atb(new MakeTempCardInDiscardAndDeckAction(new Kaboom()));
        else
            genBoom(magicNumber);
        applyToSelf(new SilenceBombsPower(2));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}