package theWario.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.squares.DamageSquare;

public class SmackSlam extends AbstractWarioCard {

    public final static String ID = makeID("SmackSlam");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    private static final int DAMAGE = 9;
    private static final int UPG_DAMAGE = 4;

    public SmackSlam() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m,  AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        transformEmpty(DamageSquare.class);
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
        }
    }
}