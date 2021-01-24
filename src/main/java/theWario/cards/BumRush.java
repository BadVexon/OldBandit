package theWario.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.WarioMod;
import theWario.squares.AbstractSquare;
import theWario.squares.EmptySquare;

import static theWario.WarioMod.theBoard;

public class BumRush extends AbstractWarioCard {

    public final static String ID = makeID("BumRush");

    //stupid intellij stuff ATTACK, ALL_ENEMY, UNCOMMON

    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 1;

    public BumRush() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        allDmg(AbstractGameAction.AttackEffect.SLASH_HEAVY);
        if (WarioMod.theBoard.shouldRender) atb(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractSquare s = theBoard.squareList.get(((theBoard.player.position - 4) + theBoard.squareList.size()) % theBoard.squareList.size());
                s.uponLand();
                if (!(s instanceof EmptySquare))
                    s.splat();
                isDone = true;
            }
        });
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
        }
    }
}