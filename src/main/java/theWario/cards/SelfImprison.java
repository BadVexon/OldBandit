package theWario.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.WarioMod;
import theWario.actions.WeirdMoveGuyAction;
import theWario.powers.ImprisonedPower;
import theWario.squares.AbstractSquare;
import theWario.squares.JailSquare;

public class SelfImprison extends AbstractWarioCard {

    public final static String ID = makeID("SelfImprison");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 2;

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public SelfImprison() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF_AND_ENEMY);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, autoWeak(m, magicNumber));
        blck();
        for (AbstractSquare s : WarioMod.theBoard.squareList) {
            if (s instanceof JailSquare) {
                addToBot(new WeirdMoveGuyAction(s.hb.cX, s.hb.cY, 0.5F, WarioMod.theBoard.player));
                addToBot(new AbstractGameAction() {
                    @Override
                    public void update() {
                        isDone = true;
                        WarioMod.theBoard.player.position = 11;
                    }
                });
                applyToSelf(new ImprisonedPower(3));
            }
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPG_BLOCK);
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}