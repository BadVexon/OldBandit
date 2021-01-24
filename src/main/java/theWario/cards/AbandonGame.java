package theWario.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.powers.AbandonGamePower;

public class AbandonGame extends AbstractWarioCard {

    public final static String ID = makeID("AbandonGame");

    //stupid intellij stuff POWER, SELF, RARE

    public AbandonGame() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new AbandonGamePower());
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            isEthereal = false;
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}