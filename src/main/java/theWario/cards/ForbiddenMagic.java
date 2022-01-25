package theWario.cards;

import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class ForbiddenMagic extends AbstractWarioCard {

    public final static String ID = makeID("ForbiddenMagic");

    //stupid intellij stuff SKILL, ENEMY, RARE

    public ForbiddenMagic() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        showTileValue = true;
    }

    @Override
    public ArrayList<Integer> showTileAmounts() {
        ArrayList<Integer> bruh = new ArrayList<>();
        bruh.add(2);
        return bruh;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, autoWeak(m, 2));
        applyToEnemy(m, autoVuln(m, 2));
        atb(new ExhaustAction(2, false, false, false));
        move(2);
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }
}