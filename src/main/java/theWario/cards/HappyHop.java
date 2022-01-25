package theWario.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AutoplayField;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class HappyHop extends AbstractWarioCard {

    public final static String ID = makeID("HappyHop");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    public HappyHop() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        baseBlock = 4;
        AutoplayField.autoplay.set(this, true);
        showTileValue = true;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        blck();
        move(3);
        atb(new DrawCardAction(p, magicNumber));
    }

    @Override
    public ArrayList<Integer> showTileAmounts() {
        ArrayList<Integer> bruh = new ArrayList<>();
        bruh.add(3);
        return bruh;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(2);
        }
    }
}