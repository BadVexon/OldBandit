package theWario.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.GraveField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.powers.BoomFormPower;

public class BoomForm extends AbstractWarioCard {

    public final static String ID = makeID("BoomForm");

    //stupid intellij stuff POWER, SELF, RARE

    public BoomForm() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        GraveField.grave.set(this, true);
        baseMagicNumber = magicNumber = 1;
        cardsToPreview = new Kaboom();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new BoomFormPower(magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }
}