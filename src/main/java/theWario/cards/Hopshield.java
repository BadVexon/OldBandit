package theWario.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class Hopshield extends AbstractWarioCard {

    public final static String ID = makeID("Hopshield");

    //stupid intellij stuff SKILL, SELF, COMMON

    private static final int BLOCK = 7;
    private static final int UPG_BLOCK = 2;

    private static final int MAGIC = 5;
    private static final int UPG_MAGIC = 1;

    public Hopshield() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
        showTileValue = true;
    }

    @Override
    public ArrayList<Integer> showTileAmounts() {
        ArrayList<Integer> bruh = new ArrayList<>();
        bruh.add(magicNumber);
        bruh.add(magicNumber + 1);
        return bruh;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        move(magicNumber);
        move(1);
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPG_BLOCK);
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}