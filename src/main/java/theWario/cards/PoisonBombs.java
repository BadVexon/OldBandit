package theWario.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.powers.PoisonBombsPower;

public class PoisonBombs extends AbstractWarioCard {

    public final static String ID = makeID("PoisonBombs");

    //stupid intellij stuff POWER, RARE, SELF

    public PoisonBombs() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new MakeTempCardInDrawPileAction(new Kaboom(), 1, true, true));
        if (upgraded)
            genBoom(1);
        applyToSelf(new PoisonBombsPower(3));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}