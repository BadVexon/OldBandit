package theWario.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Kaboom extends AbstractWarioCard {

    public final static String ID = makeID("Kaboom");

    //stupid intellij stuff ATTACK, ALL_ENEMY, SPECIAL

    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 4;

    public Kaboom() {
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ALL_ENEMY, CardColor.COLORLESS);
        baseDamage = DAMAGE;
        isMultiDamage = true;
        exhaust = true;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.FIRE);
        atb(new DrawCardAction(p, 1));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
        }
    }
}