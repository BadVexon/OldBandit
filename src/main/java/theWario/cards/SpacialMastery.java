package theWario.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.TheBandit;
import theWario.WarioMod;
import theWario.actions.TargetAnyMoveAction;

import java.util.ArrayList;

import static theWario.WarioMod.renderStuff;
import static theWario.WarioMod.theBoard;

public class SpacialMastery extends AbstractWarioCard {

    public final static String ID = makeID("SpacialMastery");

    //stupid intellij stuff SKILL, SELF, RARE

    public SpacialMastery() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
        showTileValue = true;
    }

    @Override
    public ArrayList<Integer> showTileAmounts() {
        ArrayList<Integer> bruhList = new ArrayList<>();
        for (int i = 1; i < theBoard.squareList.size(); i++) {
            bruhList.add(i);
        }
        return bruhList;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!(AbstractDungeon.player instanceof TheBandit) && !WarioMod.renderStuff)
            renderStuff = true;
        atb(new TargetAnyMoveAction());
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            exhaust = false;
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}