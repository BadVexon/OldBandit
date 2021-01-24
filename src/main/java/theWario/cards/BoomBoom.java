package theWario.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAndDeckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BoomBoom extends AbstractWarioCard {

    public final static String ID = makeID("BoomBoom");

    //stupid intellij stuff SKILL, SELF, COMMON

    private static final int MAGIC = 1;

    public BoomBoom() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        cardsToPreview = new Kaboom();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new MakeTempCardInDiscardAndDeckAction(new Kaboom()));
        if (upgraded)
            genBoom();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}