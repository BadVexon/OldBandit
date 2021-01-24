package theWario.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.TheBandit;
import theWario.WarioMod;
import theWario.actions.DelayedActionAction;
import theWario.actions.TargetMoveAction;

import java.util.ArrayList;

import static theWario.WarioMod.renderStuff;

public class Step extends AbstractWarioCard {

    public final static String ID = makeID("Step");

    //stupid intellij stuff SKILL, SELF, BASIC

    public Step() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        showTileValue = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!(AbstractDungeon.player instanceof TheBandit) && !WarioMod.renderStuff)
            renderStuff = true;
        atb(new TargetMoveAction(2));
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
            upgradeBaseCost(0);
        }
    }
}