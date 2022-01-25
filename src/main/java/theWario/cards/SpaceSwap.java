package theWario.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.actions.SetCardTargetCoordinatesAction;
import theWario.actions.SpaceSwapAction;

public class SpaceSwap extends AbstractWarioCard {

    public final static String ID = makeID("SpaceSwap");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    public SpaceSwap() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        exhaust = true;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        att(new SetCardTargetCoordinatesAction(this, Settings.WIDTH / 3, Settings.HEIGHT / 3));
        atb(new SpaceSwapAction());
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