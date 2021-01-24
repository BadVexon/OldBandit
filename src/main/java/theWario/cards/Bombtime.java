package theWario.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theWario.WarioMod;
import theWario.actions.BombtimeAction;
import theWario.actions.PerformXAction;

import java.util.ArrayList;

public class Bombtime extends AbstractWarioCard {

    public final static String ID = makeID("Bombtime");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    private static final int MAGIC = 0;
    private static final int UPG_MAGIC = 1;

    public Bombtime() {
        super(ID, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        showTileValue = true;
        cardsToPreview = new Kaboom();
    }

    @Override
    public ArrayList<Integer> showTileAmounts() {
        ArrayList<Integer> bruh = new ArrayList<>();
        bruh.add(5);
        return bruh;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (energyOnUse < EnergyPanel.totalCount) {
            energyOnUse = EnergyPanel.totalCount;
        }
        BombtimeAction r = new BombtimeAction(magicNumber);
        if (WarioMod.theBoard.shouldRender) move(5);
        atb(new PerformXAction(r, p, energyOnUse, freeToPlayOnce));
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