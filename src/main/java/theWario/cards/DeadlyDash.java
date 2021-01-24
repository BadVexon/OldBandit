package theWario.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.WarioMod;
import theWario.actions.ReplayThisAction;
import theWario.powers.AbandonGamePower;
import theWario.squares.AbstractSquare;

import java.util.ArrayList;

import static theWario.WarioMod.theBoard;

public class DeadlyDash extends AbstractWarioCard {

    public final static String ID = makeID("DeadlyDash");

    //stupid intellij stuff ATTACK, ENEMY, RARE

    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 2;

    public DeadlyDash() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        showTileValue = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m,  AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        AbstractCard q = this;
        if (WarioMod.theBoard.shouldRender) atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (AbstractDungeon.player.hasPower(AbandonGamePower.POWER_ID)) {
                    AbstractDungeon.player.getPower(AbandonGamePower.POWER_ID).flash();
                    AbstractDungeon.actionManager.addToTop(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, 1, DamageInfo.DamageType.THORNS), AttackEffect.BLUNT_LIGHT));
                } else if (theBoard.squareList.get((theBoard.player.position + 1) % theBoard.squareList.size()).goodness == AbstractSquare.GOODNESS.GOOD) {
                    atb(new ReplayThisAction(m, q));
                }
            }
        });
        move(1);
    }

    @Override
    public ArrayList<Integer> showTileAmounts() {
        ArrayList<Integer> bruh = new ArrayList<>();
        boolean wah = true;
        int i = 1;
        while (wah) {
            if (theBoard.squareList.get((theBoard.player.position + i) % theBoard.squareList.size()).goodness == AbstractSquare.GOODNESS.GOOD) {
                bruh.add(i);
                i++;
            } else {
                bruh.add(i);
                wah = false;
            }
            if (i > theBoard.squareList.size())
                wah = false;
        }
        return bruh;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
        }
    }
}