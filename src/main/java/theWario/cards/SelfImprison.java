package theWario.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.powers.ImprisonedPower;

public class SelfImprison extends AbstractWarioCard {

    public final static String ID = makeID("SelfImprison");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 2;

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = -1;

    public SelfImprison() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF_AND_ENEMY);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, autoWeak(m, 1));
        blck();
        applyToSelf(new ImprisonedPower(magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPG_BLOCK);
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}