package theWario.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.WarioMod;
import theWario.powers.DonateDrawPower;
import theWario.squares.DrawSquare;

public class DonateDraw extends AbstractWarioCard {

    public final static String ID = makeID("DonateDraw");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    private static final int MAGIC = 1;

    public DonateDraw() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        if (WarioMod.theBoard.shouldRender)transformEmpty(DrawSquare.class, magicNumber);
        applyToSelf(new DonateDrawPower(1));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}