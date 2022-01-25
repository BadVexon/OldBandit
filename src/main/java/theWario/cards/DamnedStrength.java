package theWario.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theWario.WarioMod;
import theWario.squares.DoomSquare;

public class DamnedStrength extends AbstractWarioCard {

    public final static String ID = makeID("DamnedStrength");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    public DamnedStrength() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new StrengthPower(p, magicNumber));
        if (WarioMod.theBoard.shouldRender)transformAny(DoomSquare.class, 2);
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}