package theWario.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.powers.KeyFinisherPower;

public class KeyFinisher extends AbstractWarioCard {

    public final static String ID = makeID("KeyFinisher");

    //stupid intellij stuff POWER, SELF, RARE

    public KeyFinisher() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new KeyFinisherPower(1));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(2);
        }
    }
}