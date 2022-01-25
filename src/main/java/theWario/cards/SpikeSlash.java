package theWario.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.WarioMod;
import theWario.actions.TransformSquareAction;
import theWario.squares.SpikeSquare;

import java.util.ArrayList;

import static theWario.WarioMod.theBoard;

public class SpikeSlash extends AbstractWarioCard {

    public final static String ID = makeID("SpikeSlash");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    private static final int DAMAGE = 11;
    private static final int UPG_DAMAGE = 4;

    public SpikeSlash() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        showTileValue = true;
    }

    @Override
    public ArrayList<Integer> showTileAmounts() {
        ArrayList<Integer> bruh = new ArrayList<>();
        bruh.add(1);
        return bruh;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        atb(new TransformSquareAction(theBoard.getPlayerSquare(), SpikeSquare.class));
        move(1);
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
        }
    }
}