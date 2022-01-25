package theWario.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.actions.TransformSquareAction;
import theWario.powers.AbandonGamePower;
import theWario.powers.ImprisonedPower;
import theWario.squares.AbstractSquare;
import theWario.squares.EmptySquare;
import theWario.squares.GoSquare;

import java.util.ArrayList;

import static theWario.WarioMod.theBoard;

public class NullJump extends AbstractWarioCard {

    public final static String ID = makeID("NullJump");

    //stupid intellij stuff ATTACK, ENEMY, UNCOMMON

    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 2;

    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 2;

    public NullJump() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        showTileValue = true;
    }

    @Override
    public ArrayList<Integer> showTileAmounts() {
        ArrayList<Integer> bruh = new ArrayList<>();
        bruh.add(5);
        return bruh;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        blck();
        dmg(m,  AbstractGameAction.AttackEffect.SMASH);
        int i = theBoard.player.position;
        move(5);
        if (!AbstractDungeon.player.hasPower(ImprisonedPower.POWER_ID))
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    if (!AbstractDungeon.player.hasPower(AbandonGamePower.POWER_ID))
                        if (i + 5 > theBoard.squareList.size()) {
                            for (AbstractSquare s : theBoard.squareList) {
                                if ((theBoard.squareList.indexOf(s) < theBoard.player.position || (theBoard.squareList.indexOf(s) > i)) && !(s instanceof EmptySquare) && !(s instanceof GoSquare)) {
                                    atb(new TransformSquareAction(s, EmptySquare.class));
                                }
                            }
                        } else
                            for (AbstractSquare s : theBoard.squareList) {
                                if (theBoard.squareList.indexOf(s) > i && theBoard.squareList.indexOf(s) < i + 5 && !(s instanceof EmptySquare) && !(s instanceof GoSquare)) {
                                    atb(new TransformSquareAction(s, EmptySquare.class));
                                }
                            }
                }
            });
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
            upgradeBlock(UPG_BLOCK);
        }
    }
}