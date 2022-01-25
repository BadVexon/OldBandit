package theWario.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.powers.AbandonGamePower;
import theWario.powers.ImprisonedPower;
import theWario.squares.AbstractSquare;

import java.util.ArrayList;

import static theWario.WarioMod.theBoard;

public class ImFeelingLucky extends AbstractWarioCard {

    public final static String ID = makeID("ImFeelingLucky");

    //stupid intellij stuff ATTACK, ENEMY, UNCOMMON

    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 3;

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 2;

    public ImFeelingLucky() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseBlock = 2;
        baseMagicNumber = magicNumber = MAGIC;
        showTileValue = true;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        dmg(m,  AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (!AbstractDungeon.player.hasPower(ImprisonedPower.POWER_ID) && !AbstractDungeon.player.hasPower(AbandonGamePower.POWER_ID)) {
                    AbstractSquare s = theBoard.squareList.get((theBoard.player.position + magicNumber) % theBoard.squareList.size());
                    if (s.goodness == AbstractSquare.GOODNESS.BAD) {
                        s.ignoreNextTrigger = true;
                        att(new GainEnergyAction(1));
                        att(new GainBlockAction(p, block));
                    }
                }
            }
        });
        move(magicNumber);
    }

    @Override
    public ArrayList<Integer> showTileAmounts() {
        ArrayList<Integer> bruh = new ArrayList<>();
        bruh.add(magicNumber);
        return bruh;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
            upgradeBlock(2);
        }
    }
}