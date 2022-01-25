package theWario.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Iterator;

public class MagnetBarrier extends AbstractWarioCard {

    public final static String ID = makeID("MagnetBarrier");

    //stupid intellij stuff SKILL, SELF, COMMON

    private static final int BLOCK = 7;
    private static final int UPG_BLOCK = 1;

    private static final int MAGIC = 3;

    public MagnetBarrier() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }



    public void applyPowers() {
        int realBaseBlock = this.baseBlock;
        this.baseBlock += this.magicNumber * AbstractDungeon.actionManager.cardsPlayedThisTurn.stream().filter(c -> c.color == CardColor.COLORLESS).count();
        super.applyPowers();
        this.baseBlock = realBaseBlock;
        this.isBlockModified = this.block != this.baseBlock;
    }


    public void us(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPG_BLOCK);
            upgradeMagicNumber(1);
        }
    }
}