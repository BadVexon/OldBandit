package theWario.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.BanditBoard;
import theWario.WarioMod;
import theWario.squares.AbstractSquare;

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
        return bruh;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        blck();
        if (WarioMod.theBoard.getPlayerSquare().goodness == AbstractSquare.GOODNESS.BAD) {
            blck();
        }
        move(magicNumber);
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPG_BLOCK);
        }
    }
}