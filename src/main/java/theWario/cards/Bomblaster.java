package theWario.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.WarioMod;
import theWario.squares.KaboomSquare;

public class Bomblaster extends AbstractWarioCard {

    public final static String ID = makeID("Bomblaster");

    //stupid intellij stuff SKILL, SELF, UNCOMMON


    public Bomblaster() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        exhaust = true;
        cardsToPreview = new Kaboom();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new MakeTempCardInDrawPileAction(new Kaboom(), 1, true, true));
        if (WarioMod.theBoard.shouldRender) transformEmpty(KaboomSquare.class, 1);
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