package theWario.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.WarioMod;
import theWario.actions.BigUpgradeAction;

public class BigUpgrade extends AbstractWarioCard {

    public final static String ID = makeID("BigUpgrade");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    public BigUpgrade() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (WarioMod.theBoard.shouldRender) {
            atb(new BigUpgradeAction());
        }
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