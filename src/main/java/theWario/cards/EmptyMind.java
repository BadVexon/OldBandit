package theWario.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.CardIgnore;
import theWario.powers.EmptyMindPower;

import java.util.ArrayList;

public class EmptyMind extends AbstractWarioCard {

    public final static String ID = makeID("EmptyMind");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    private static final int MAGIC = 4;
    private static final int UPG_MAGIC = 1;

    public EmptyMind() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        showTileValue = true;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new EmptyMindPower(magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}