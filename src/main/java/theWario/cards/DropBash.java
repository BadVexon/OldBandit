package theWario.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class DropBash extends AbstractWarioCard {

    public final static String ID = makeID("DropBash");

    //stupid intellij stuff ATTACK, ENEMY, UNCOMMON

    private static final int DAMAGE = 12;
    private static final int UPG_DAMAGE = 3;

    private static final int BLOCK = 6;
    private static final int UPG_BLOCK = 2;

    private static final int MAGIC = 6;
    private static final int UPG_MAGIC = 2;

    public DropBash() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
        showTileValue = true;
    }

    @Override
    public ArrayList<Integer> showTileAmounts() {
        ArrayList<Integer> myList = new ArrayList<>();
        myList.add(magicNumber);
        return myList;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        blck();
        move(magicNumber);
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