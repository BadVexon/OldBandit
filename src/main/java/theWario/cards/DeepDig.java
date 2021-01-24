package theWario.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theWario.WarioMod;
import theWario.squares.StrengthSquare;

import static theWario.WarioMod.theBoard;

public class DeepDig extends AbstractWarioCard {
    public final static String ID = makeID("DeepDig");
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public DeepDig() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new StrengthPower(p, -1));
        if (WarioMod.theBoard.shouldRender)transformEmpty(StrengthSquare.class, magicNumber);
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}