package theWario.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.squares.AbstractSquare;

import static theWario.WarioMod.theBoard;

public class MassiveParty extends AbstractWarioCard {

    public final static String ID = makeID("MassiveParty");

    //stupid intellij stuff SKILL, SELF, RARE

    public MassiveParty() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (AbstractSquare s : theBoard.squareList) {
                    if (s.goodness == AbstractSquare.GOODNESS.GOOD) {
                        s.splat();
                        s.uponLand();
                    }
                }
            }
        });
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(2);
        }
    }
}