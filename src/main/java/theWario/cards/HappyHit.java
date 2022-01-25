package theWario.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.squares.AbstractSquare;

import static theWario.WarioMod.theBoard;

public class HappyHit extends AbstractWarioCard {

    public final static String ID = makeID("HappyHit");

    //stupid intellij stuff ATTACK, ENEMY, UNCOMMON

    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 2;

    private static final int MAGIC = 1;

    public HappyHit() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        dmg(m,  AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        int waluigi = magicNumber;
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                int bruh = 0;
                for (int i = (theBoard.player.position + 1) % theBoard.squareList.size(); i != theBoard.player.position; i++) {
                    i %= theBoard.squareList.size();
                    if (theBoard.squareList.get(i).goodness == AbstractSquare.GOODNESS.GOOD) {
                        bruh++;
                        AbstractSquare s = theBoard.squareList.get(i);
                        s.uponLand();
                        s.splat();
                    }
                    if (bruh == waluigi)
                        break;
                }
            }
        });
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
            upgradeMagicNumber(1);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}