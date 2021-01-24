package theWario.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.powers.EnergizedBombsPower;

public class EnergizedBombs extends AbstractWarioCard {

    public final static String ID = makeID("EnergizedBombs");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    public EnergizedBombs() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new EnergizedBombsPower(1));
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