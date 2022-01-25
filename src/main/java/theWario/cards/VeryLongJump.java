package theWario.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class VeryLongJump extends AbstractWarioCard {

    public final static String ID = makeID("VeryLongJump");

    //stupid intellij stuff ATTACK, ALL_ENEMY, RARE

    private static final int DAMAGE = 15;
    private static final int UPG_DAMAGE = 5;

    private static final int MAGIC = 15;
    private static final int UPG_MAGIC = 3;

    public VeryLongJump() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        isMultiDamage = true;
        showTileValue = true;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        move(magicNumber);
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