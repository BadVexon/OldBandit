package theWario.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.WarioMod;
import theWario.powers.KaboomBigPower;
import theWario.squares.KaboomSquare;

public class Bomblaster extends AbstractWarioCard {

    public final static String ID = makeID("Bomblaster");

    //stupid intellij stuff SKILL, SELF, UNCOMMON


    public Bomblaster() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        if (WarioMod.theBoard.shouldRender) transformEmpty(KaboomSquare.class);
        if (WarioMod.theBoard.shouldRender) transformEmpty(KaboomSquare.class);
        applyToSelf(new KaboomBigPower(magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }
}