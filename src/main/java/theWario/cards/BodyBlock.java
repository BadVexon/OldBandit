package theWario.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.WarioMod;
import theWario.squares.BlockSquare;

public class BodyBlock extends AbstractWarioCard {

    public final static String ID = makeID("BodyBlock");

    //stupid intellij stuff SKILL, SELF, COMMON

    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 3;

    public BodyBlock() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        if (WarioMod.theBoard.shouldRender)
            transformEmpty(BlockSquare.class);
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPG_BLOCK);
        }
    }
}