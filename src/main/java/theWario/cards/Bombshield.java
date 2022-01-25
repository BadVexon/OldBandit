package theWario.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.WarioMod;

import java.util.ArrayList;

public class Bombshield extends AbstractWarioCard {

    public final static String ID = makeID("Bombshield");

    //stupid intellij stuff SKILL, SELF, COMMON

    private static final int BLOCK = 6;
    private static final int UPG_BLOCK = 3;

    private static final int MAGIC = 1;

    public Bombshield() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
        showTileValue = true;
        cardsToPreview = new Kaboom();
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        blck();
        genBoom();
        addToBot(new DrawCardAction(magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPG_BLOCK);
            upgradeMagicNumber(1);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}