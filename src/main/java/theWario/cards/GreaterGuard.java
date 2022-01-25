package theWario.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.powers.GreaterGuardPower;
import theWario.squares.BlockSquare;

public class GreaterGuard extends AbstractWarioCard {

    public final static String ID = makeID("GreaterGuard");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public GreaterGuard() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        transformEmpty(BlockSquare.class, 2);
        applyToSelf(new GreaterGuardPower(magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}