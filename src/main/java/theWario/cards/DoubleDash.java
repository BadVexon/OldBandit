package theWario.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.WarioMod;

import java.util.ArrayList;

public class DoubleDash extends AbstractWarioCard {

    public final static String ID = makeID("DoubleDash");

    //stupid intellij stuff ATTACK, ENEMY, COMMOn

    private static final int DAMAGE = 4;
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public DoubleDash() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        showTileValue = true;
    }

    @Override
    public ArrayList<Integer> showTileAmounts() {
        ArrayList<Integer> bruh = new ArrayList<>();
        bruh.add(1);
        bruh.add(2);
        if (upgraded) {
            bruh.add(3);
        }
        return bruh;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            dmg(m,  AbstractGameAction.AttackEffect.BLUNT_LIGHT);
            if (WarioMod.theBoard.shouldRender)move(1);
        }
    }

    public void upgrade() {
        if (!upgraded) {
            name = "Triple Dash";
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}