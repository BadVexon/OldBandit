package theWario.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.squares.EnergySquare;

import static theWario.WarioMod.theBoard;

public class TreasureDig extends AbstractWarioCard {

    public final static String ID = makeID("TreasureDig");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    public TreasureDig() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        ExhaustiveField.ExhaustiveFields.baseExhaustive.set(this, 2);
        ExhaustiveField.ExhaustiveFields.exhaustive.set(this, 2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        transformEmpty(EnergySquare.class);
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }
}