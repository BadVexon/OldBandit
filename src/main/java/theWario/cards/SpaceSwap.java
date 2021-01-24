package theWario.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.actions.SpaceSwapAction;

public class SpaceSwap extends AbstractWarioCard {

    public final static String ID = makeID("SpaceSwap");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    public SpaceSwap() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SpaceSwapAction());
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            exhaust = false;
            ExhaustiveField.ExhaustiveFields.baseExhaustive.set(this, 3);
            ExhaustiveField.ExhaustiveFields.exhaustive.set(this, 3);
            ExhaustiveField.ExhaustiveFields.isExhaustiveUpgraded.set(this, true);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}