package theWario.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsCenteredAction;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Iterator;

public class Immortalize extends AbstractWarioCard {

    public final static String ID = makeID("Immortalize");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    private static final int MAGIC = 2;

    public Immortalize() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> selection = generateCardChoices();
        atb(new SelectCardsCenteredAction(selection, "Choose a card to Immortalize.", (cards) -> {
            for (int i = 0; i < magicNumber; i++) {
                inputCardTop(cards.get(0));
            }
        }));
    }

    private ArrayList<AbstractCard> generateCardChoices() {
        ArrayList derp = new ArrayList();

        while(derp.size() != 3) {
            boolean dupe = false;
            int roll = AbstractDungeon.cardRandomRng.random(99);
            CardRarity cardRarity;
            if (roll < 55) {
                cardRarity = CardRarity.COMMON;
            } else if (roll < 85) {
                cardRarity = CardRarity.UNCOMMON;
            } else {
                cardRarity = CardRarity.RARE;
            }

            AbstractCard tmp = CardLibrary.getAnyColorCard(CardType.ATTACK, cardRarity);
            Iterator var6 = derp.iterator();

            while(var6.hasNext()) {
                AbstractCard c = (AbstractCard)var6.next();
                if (c.cardID.equals(tmp.cardID)) {
                    dupe = true;
                    break;
                }
            }

            if (!dupe) {
                derp.add(tmp.makeCopy());
            }
        }

        return derp;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }
}