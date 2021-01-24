package theWario.cards;

import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.squares.DoomSquare;

public class HastyRush extends AbstractWarioCard {

    public final static String ID = makeID("HastyRush");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;

    public HastyRush() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (AbstractDungeon.cardRandomRng.random(49) == 0) {
            atb(new TalkAction(true, "bruh", 2.0f, 2.0f));
        }
        atb(new DrawCardAction(p, magicNumber));
        transformAny(DoomSquare.class, 2);
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}