package theWario.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.powers.AbandonGamePower;
import theWario.powers.ImprisonedPower;
import theWario.squares.AbstractSquare;
import theWario.squares.GoSquare;

import java.util.ArrayList;

import static theWario.WarioMod.theBoard;

public class Game extends AbstractWarioCard {

    public final static String ID = makeID("Game");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 2;

    private static final int MAGIC = 4;
    private static final int UPG_MAGIC = 2;

    public Game() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        showTileValue = true;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        dmg(m,  AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        int i = theBoard.player.position;
        move(magicNumber);
        boolean bruh = false;
        if (!AbstractDungeon.player.hasPower(ImprisonedPower.POWER_ID) && !AbstractDungeon.player.hasPower(AbandonGamePower.POWER_ID)) {
            if (i + magicNumber > theBoard.squareList.size()) {
                for (AbstractSquare s : theBoard.squareList) {
                    if (((theBoard.squareList.indexOf(s) < i + magicNumber || (theBoard.squareList.indexOf(s) > i)) || theBoard.squareList.get(theBoard.player.position) == s) && (s instanceof GoSquare)) {
                        bruh = true;
                    }
                }
            } else
                for (AbstractSquare s : theBoard.squareList) {
                    if (theBoard.squareList.indexOf(s) > i && theBoard.squareList.indexOf(s) <= i + magicNumber && (s instanceof GoSquare)) {
                        bruh = true;
                    }
                }
        }
        if (bruh)
            dmg(m,  AbstractGameAction.AttackEffect.SLASH_VERTICAL);
    }

    @Override
    public ArrayList<Integer> showTileAmounts() {
        ArrayList<Integer> bruh = new ArrayList<>();
        bruh.add(magicNumber);
        return bruh;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
        }
    }
}