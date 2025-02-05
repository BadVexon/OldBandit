package theWario.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Enshrine extends AbstractWarioCard {

    public final static String ID = makeID("Enshrine");

    //stupid intellij stuff SKILL, SELF, RARE

    public Enshrine() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
        baseMagicNumber = magicNumber = 2;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        atb(new SelectCardsInHandAction("Choose a card to Enshrine.", (cards) -> {
            for (int i = 0; i < magicNumber; i++) {
                inputCard(cards.get(0));
            }
            addToTop(new ExhaustSpecificCardAction(cards.get(0), AbstractDungeon.player.hand));
        }));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }
}