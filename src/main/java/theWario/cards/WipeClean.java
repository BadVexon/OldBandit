package theWario.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.GraveField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.actions.TransformSquareAction;
import theWario.squares.AbstractSquare;
import theWario.squares.EmptySquare;

import static theWario.WarioMod.theBoard;

public class WipeClean extends AbstractWarioCard {

    public final static String ID = makeID("WipeClean");

    //stupid intellij stuff SKILL, SELF, RARE

    public WipeClean() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        GraveField.grave.set(this, true);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractSquare s : theBoard.squareList) {
            if (s.goodness == AbstractSquare.GOODNESS.BAD) {
                atb(new TransformSquareAction(s, EmptySquare.class));
            }
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(2);
        }
    }
}