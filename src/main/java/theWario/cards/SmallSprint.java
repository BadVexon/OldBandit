package theWario.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class SmallSprint extends AbstractWarioCard {

    public final static String ID = makeID("SmallSprint");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    private static final int DAMAGE = 3;
    private static final int UPG_DAMAGE = 1;

    private static final int BLOCK = 2;
    private static final int UPG_BLOCK = 1;

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public SmallSprint() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
        showTileValue = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        move(magicNumber);
        blck();
        dmg(m,  AbstractGameAction.AttackEffect.BLUNT_LIGHT);
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
            upgradeBlock(UPG_BLOCK);
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}