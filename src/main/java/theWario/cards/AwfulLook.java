package theWario.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.WarioMod;
import theWario.squares.AbstractSquare;
import theWario.squares.EmptySquare;
import theWario.util.LeerVFX;

import static theWario.WarioMod.theBoard;

public class AwfulLook extends AbstractWarioCard {

    public final static String ID = makeID("AwfulLook");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 3;

    public AwfulLook() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new LeerVFX(p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY, p.flipHorizontal)));
        dmg(m,  AbstractGameAction.AttackEffect.FIRE);
        if (WarioMod.theBoard.shouldRender) {
            move(1);
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
        }
    }
}