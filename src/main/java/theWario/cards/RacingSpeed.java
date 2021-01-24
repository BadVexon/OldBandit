package theWario.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.powers.RacingSpeedPower;

import java.util.ArrayList;

public class RacingSpeed extends AbstractWarioCard {

    public final static String ID = makeID("RacingSpeed");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    private static final int MAGIC = 6;
    private static final int UPG_MAGIC = 3;

    public RacingSpeed() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        showTileValue = true;
    }

    @Override
    public ArrayList<Integer> showTileAmounts() {
        ArrayList<Integer> bruh = new ArrayList<>();
        bruh.add(magicNumber);
        return bruh;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new RacingSpeedPower(1));
        move(magicNumber);
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}