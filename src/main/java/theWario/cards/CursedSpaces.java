package theWario.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.WarioMod;
import theWario.squares.FrailSquare;
import theWario.squares.VulnerableSquare;
import theWario.squares.WeakSquare;

public class CursedSpaces extends AbstractWarioCard {

    public final static String ID = makeID("CursedSpaces");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    public CursedSpaces() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (WarioMod.theBoard.shouldRender)transformEmpty(WeakSquare.class);
        if (WarioMod.theBoard.shouldRender)transformEmpty(VulnerableSquare.class);
        if (WarioMod.theBoard.shouldRender)transformEmpty(FrailSquare.class);
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }
}