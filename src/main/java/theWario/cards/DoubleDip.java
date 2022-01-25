package theWario.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.squares.AbstractSquare;
import theWario.squares.EmptySquare;

import java.util.ArrayList;

import static theWario.WarioMod.theBoard;

public class DoubleDip extends AbstractWarioCard {

    public final static String ID = makeID("DoubleDip");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    private static final int DAMAGE = 3;
    private static final int UPG_DAMAGE = 2;

    public DoubleDip() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        showTileValue = true;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        dmg(m,  AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        dmg(m,  AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractSquare s = theBoard.squareList.get(((theBoard.player.position + 3) + theBoard.squareList.size()) % theBoard.squareList.size());
                s.uponLand();
                if (!(s instanceof EmptySquare))
                    s.splat();
                isDone = true;
            }
        });
        move(3);
    }

    @Override
    public ArrayList<Integer> showTileAmounts() {
        ArrayList<Integer> bruh = new ArrayList<>();
        bruh.add(3);
        return bruh;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
        }
    }
}