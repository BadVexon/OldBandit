package theWario.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class OctoJump extends AbstractWarioCard {

    public final static String ID = makeID("OctoJump");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 2;

    private static final int MAGIC = 8;
    private static final int UPG_MAGIC = 2;

    public OctoJump() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
        shuffleBackIntoDrawPile = true;
        showTileValue = true;
    }

    @Override
    public ArrayList<Integer> showTileAmounts() {
        ArrayList<Integer> bruh = new ArrayList<>();
        bruh.add(magicNumber);
        return bruh;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        blck();
        move(magicNumber);
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
            selfRetain = true;
        }
    }
}