package theWario.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.squares.AbstractSquare;
import theWario.squares.EmptySquare;

import static theWario.WarioMod.theBoard;

public class SimpleShield extends AbstractWarioCard {

    public final static String ID = makeID("SimpleShield");

    //stupid intellij stuff SKILL, SELF, COMMON

    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 3;

    public SimpleShield() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        showTileValue = true;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractSquare s = theBoard.squareList.get(theBoard.player.position);
                s.uponLand();
                if (!(s instanceof EmptySquare))
                    s.splat();
                isDone = true;
            }
        });
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPG_BLOCK);
        }
    }
}