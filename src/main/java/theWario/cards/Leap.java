package theWario.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class Leap extends AbstractWarioCard {

    public final static String ID = makeID("Leap");

    //stupid intellij stuff ATTACK, ENEMY, BASIC

    private static final int DAMAGE = 10;

    private static final int MAGIC = 10;

    public Leap() {
        super(ID, 2, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
        showTileValue = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m,  AbstractGameAction.AttackEffect.BLUNT_HEAVY);
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
            AlwaysRetainField.alwaysRetain.set(this, true);
            rawDescription= UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}