package theWario.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.squares.DoomSquare;

public class f extends AbstractWarioCard {

    public final static String ID = makeID("f");

    //stupid intellij stuff ATTACK, ENEMY, UNCOMMON

    private static final int DAMAGE = 9;
    private static final int UPG_DAMAGE = 2;

    public f() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseBlock = 9;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        dmg(m,  AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        transformAny(DoomSquare.class, 2);
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
            upgradeBlock(2);
        }
    }
}