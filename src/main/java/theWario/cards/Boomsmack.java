package theWario.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAndDeckAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

public class Boomsmack extends AbstractWarioCard {

    public final static String ID = makeID("Boomsmack");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 2;

    public Boomsmack() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        cardsToPreview = new Kaboom();
        showTileValue = true;
    }

    @Override
    public ArrayList<Integer> showTileAmounts() {
        return new ArrayList<>(Arrays.asList(2));
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        dmg(m,  AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        AbstractCard c = new Kaboom();
        if (upgraded) c.upgrade();
        atb(new MakeTempCardInDiscardAndDeckAction(c));
        move(2);
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
            AbstractCard c = new Kaboom();
            c.upgrade();
            cardsToPreview = c;
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}