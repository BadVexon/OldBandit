package theWario.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.CardIgnore;
import theWario.TheBandit;
import theWario.WarioMod;
import theWario.actions.DelayedActionAction;
import theWario.actions.TargetMoveAction;

import java.util.ArrayList;


@CardIgnore
public class TrekMove extends AbstractWarioCard {

    public final static String ID = makeID("TrekMove");

    //stupid intellij stuff SKILL, SELF, SPECIAL

    public TrekMove(int value) {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        baseMagicNumber = magicNumber = value;
        isEthereal = true;
        exhaust = true;
        showTileValue = true;
    }

    @Override
    public ArrayList<Integer> showTileAmounts() {
        ArrayList<Integer> bruhList = new ArrayList<>();
            for (int i = 1; i <= magicNumber; i++) {
                bruhList.add(i);
            }
        return bruhList;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        atb(new TargetMoveAction(magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new TrekMove(baseMagicNumber);
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
        }
    }
}