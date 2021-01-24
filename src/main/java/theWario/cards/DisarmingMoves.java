package theWario.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.WarioMod;

import java.util.ArrayList;

public class DisarmingMoves extends AbstractWarioCard {

    public final static String ID = makeID("DisarmingMoves");

    //stupid intellij stuff SKILL, ENEMY, COMMON

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public DisarmingMoves() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        showTileValue = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, autoVuln(m, magicNumber));
        applyToEnemy(m, autoWeak(m, magicNumber));
        if (WarioMod.theBoard.shouldRender)move(1);
        if (WarioMod.theBoard.shouldRender)move(1);
    }

    @Override
    public ArrayList<Integer> showTileAmounts() {
        ArrayList<Integer> bruh = new ArrayList<>();
        bruh.add(1);
        bruh.add(2);
        return bruh;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}