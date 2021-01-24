package theWario.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BoomAndDoom extends AbstractWarioCard {

    public final static String ID = makeID("BoomAndDoom");

    //stupid intellij stuff SKILL, RARE, 

    private static final int MAGIC = 3;

    public BoomAndDoom() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        baseMagicNumber = magicNumber = MAGIC;
        AbstractCard q = new Kaboom();
        q.upgrade();
        cardsToPreview = q;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded) {
            atb(new MakeTempCardInDrawPileAction(new Kaboom(), 1, true, true));
        }
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (AbstractCard c : p.drawPile.group) {
                    if (c instanceof Kaboom) {
                        c.upgrade();
                    }
                }
                for (AbstractCard c : p.hand.group) {
                    if (c instanceof Kaboom) {
                        c.upgrade();
                        c.superFlash();
                    }
                }
                for (AbstractCard c : p.discardPile.group) {
                    if (c instanceof Kaboom) {
                        c.upgrade();
                    }
                }
            }
        });
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}