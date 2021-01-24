package theWario.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.WarioMod;
import theWario.actions.BigUpgradeAction;

import java.util.ArrayList;

public class BlastDash extends AbstractWarioCard {

    public final static String ID = makeID("BlastDash");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    private static final int DAMAGE = 12;
    private static final int UPG_DAMAGE = 3;

    private static final int MAGIC = 7;
    private static final int UPG_MAGIC = 1;

    public BlastDash() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        cardsToPreview = new Kaboom();
        showTileValue = true;
    }

    @Override
    public ArrayList<Integer> showTileAmounts() {
        ArrayList<Integer> bruh = new ArrayList<>();
        bruh.add(magicNumber);
        return bruh;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        if (WarioMod.theBoard.shouldRender) {
            move(magicNumber);
        }
        genBoom();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}