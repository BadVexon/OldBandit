package theWario.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.squares.SpikeSquare;

public class PayItForwards extends AbstractWarioCard {

    public final static String ID = makeID("PayItForwards");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    private static final int BLOCK = 14;
    private static final int UPG_BLOCK = 4;

    public PayItForwards() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        exhaust = true;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        blck();
        transformEmpty(SpikeSquare.class, 2);
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPG_BLOCK);
        }
    }
}