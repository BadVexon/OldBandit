package theWario.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Immortalize extends AbstractWarioCard {

    public final static String ID = makeID("Immortalize");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    private static final int MAGIC = 2;

    public Immortalize() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (AbstractDungeon.player.drawPile.size() + AbstractDungeon.player.discardPile.size() <= 1) {
                } else if (p.drawPile.isEmpty() || p.drawPile.size() == 1) {
                    att(new AbstractGameAction() {
                        @Override
                        public void update() {
                            isDone = true;
                            att(new DrawCardAction(p, magicNumber));
                            AbstractCard c = p.drawPile.getTopCard();
                            AbstractCard q = p.drawPile.getNCardFromTop(1);
                            inputCard(c);
                            inputCard(q);
                        }
                    });
                    AbstractDungeon.actionManager.addToTop(new EmptyDeckShuffleAction());// 34
                } else {
                    isDone = true;
                    att(new DrawCardAction(p, magicNumber));
                    AbstractCard c = p.drawPile.getTopCard();
                    AbstractCard q = p.drawPile.getNCardFromTop(1);
                    inputCard(c);
                    inputCard(q);
                }
            }
        });
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