package theWario.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.squares.GoSquare;

public class GoAgain extends AbstractWarioCard {

    public final static String ID = makeID("GoAgain");

    //stupid intellij stuff SKILL, SELF, RARE

    public GoAgain() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        transformEmpty(GoSquare.class);
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            isInnate = true;
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}