package theWario.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Boomsmack extends AbstractWarioCard {

    public final static String ID = makeID("Boomsmack");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 2;

    public Boomsmack() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        cardsToPreview = new Kaboom();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m,  AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        if (upgraded) {
            atb(new MakeTempCardInDrawPileAction(new Kaboom(), 1, true, true));
        } else {
            genBoom();
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}