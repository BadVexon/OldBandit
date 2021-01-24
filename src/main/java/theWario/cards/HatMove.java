package theWario.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.CardIgnore;
import theWario.TheBandit;
import theWario.WarioMod;
import theWario.actions.TargetMoveAction;

import java.util.ArrayList;

import static theWario.WarioMod.renderStuff;

@CardIgnore
public class HatMove extends AbstractWarioCard {

    public final static String ID = makeID("HatMove");

    //stupid intellij stuff SKILL, SELF, SPECIAL

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 2;

    public HatMove() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        AlwaysRetainField.alwaysRetain.set(this, true);
        showTileValue = true;
        exhaust = true;
    }

    @Override
    public ArrayList<Integer> showTileAmounts() {
        ArrayList<Integer> bruhList = new ArrayList<>();
        bruhList.add(1);
        bruhList.add(2);
        bruhList.add(3);
        if (upgraded) {
            bruhList.add(4);
            bruhList.add(5);
        }
        return bruhList;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!(AbstractDungeon.player instanceof TheBandit) && !WarioMod.renderStuff)
            renderStuff = true;
        atb(new TargetMoveAction(magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}