package theWario.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.RefundFields;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theWario.actions.GrandJourneyAction;
import theWario.actions.PerformXAction;

import java.util.ArrayList;

public class TheGrandJourney extends AbstractWarioCard {

    public final static String ID = makeID("TheGrandJourney");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public TheGrandJourney() {
        super(ID, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        selfRetain = true;
        showTileValue = true;
        RefundFields.baseRefund.set(this, 1);
        RefundFields.refund.set(this, 1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (energyOnUse < EnergyPanel.totalCount) {
            energyOnUse = EnergyPanel.totalCount;
        }
        GrandJourneyAction action = new GrandJourneyAction(magicNumber);
        atb(new PerformXAction(action, p, energyOnUse, freeToPlayOnce));
    }

    @Override
    public ArrayList<Integer> showTileAmounts() {
        ArrayList<Integer> bruh = new ArrayList<>();
        for (int i = 0; i < EnergyPanel.totalCount + magicNumber; i++) {
            bruh.add(i + 1);
        }
        return bruh;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}