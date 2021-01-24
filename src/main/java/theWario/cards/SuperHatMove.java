package theWario.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.CardIgnore;
import theWario.TheBandit;
import theWario.WarioMod;
import theWario.actions.TargetAnyMoveAction;

import java.util.ArrayList;

import static theWario.WarioMod.renderStuff;
import static theWario.WarioMod.theBoard;

@CardIgnore
public class SuperHatMove extends AbstractWarioCard {

    public final static String ID = makeID("SuperHatMove");

    //stupid intellij stuff SKILL, SELF, SPECIAL

    public SuperHatMove() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        AlwaysRetainField.alwaysRetain.set(this, true);
        showTileValue = true;
        exhaust = true;
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