package theWario.cards;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.WarioMod;

import java.util.ArrayList;

public class Defend extends AbstractWarioCard {
    public final static String ID = makeID("Defend");
    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 3;
    public Defend() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        baseBlock = BLOCK;
        showTileValue = true;
        tags.add(BaseModCardTags.BASIC_DEFEND);
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        if (WarioMod.theBoard.shouldRender)move(2);
    }
    @Override
    public ArrayList<Integer> showTileAmounts() {
        ArrayList<Integer> bruh = new ArrayList<>();
        bruh.add(2);
        return bruh;
    }
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPG_BLOCK);
        }
    }
}