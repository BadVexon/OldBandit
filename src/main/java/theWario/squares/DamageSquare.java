package theWario.squares;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import static theWario.WarioMod.theBoard;
import theWario.powers.TempestBlastsPower;
import theWario.util.TextureLoader;

public class DamageSquare extends AbstractSquare {
    public DamageSquare(int x, int y) {
        super(x, y, "DamageSquare");
        tex = TextureLoader.getTexture("wariomodResources/images/ui/DamageSquare" + theBoard.artStyle + ".png");
        goodness = GOODNESS.GOOD;
    }

    public void onLanded() {
        int blah = 5;
        if (adp().hasPower(TempestBlastsPower.POWER_ID)) {
            blah += adp().getPower(TempestBlastsPower.POWER_ID).amount;
        }
        att(new DamageAllEnemiesAction(adp(), DamageInfo.createDamageMatrix(blah, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
        att(new VFXAction(new WhirlwindEffect(), 0.0F));
        att(new SFXAction("ATTACK_WHIRLWIND"));
    }

    public String getBodyText() {
        int blah = 5;
        if (adp().hasPower(TempestBlastsPower.POWER_ID)) {
            blah += adp().getPower(TempestBlastsPower.POWER_ID).amount;
        }
        return TEXT[0] + blah + TEXT[1];
    }
}
