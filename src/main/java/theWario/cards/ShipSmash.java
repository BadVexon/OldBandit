package theWario.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.powers.GhostlyDrainPower;

public class ShipSmash extends AbstractWarioCard {

    public final static String ID = makeID("ShipSmash");

    //stupid intellij stuff RARE, ENEMY, ATTACK

    private static final int DAMAGE = 12;

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 2;

    public ShipSmash() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SMASH);
        applyToSelf(new GhostlyDrainPower(magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(-2);
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}