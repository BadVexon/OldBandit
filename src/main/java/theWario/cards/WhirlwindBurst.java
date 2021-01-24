package theWario.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theWario.WarioMod.theBoard;

public class WhirlwindBurst extends AbstractWarioCard {

    public final static String ID = makeID("WhirlwindBurst");

    //stupid intellij stuff SKILL, SELF, COMMON

    private static final int MAGIC = 2;

    public WhirlwindBurst() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        exhaust = true;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        transformEmpty(theBoard.getRandomCommonSquare(), magicNumber);
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            exhaust = false;
            ExhaustiveField.ExhaustiveFields.baseExhaustive.set(this, 2);
            ExhaustiveField.ExhaustiveFields.exhaustive.set(this, 2);
            ExhaustiveField.ExhaustiveFields.isExhaustiveUpgraded.set(this, true);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}