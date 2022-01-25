package theWario.squares;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.SweepingBeamEffect;
import static theWario.WarioMod.theBoard;

import theWario.BanditBoard;
import theWario.powers.KeyFinisherPower;
import theWario.powers.RacingSpeedPower;
import theWario.util.TextureLoader;

public class GoSquare extends AbstractSquare {
    private static final int DAMAGE = 10;

    public GoSquare(int x, int y) {
        super(x, y, "GoSquare");
        tex = TextureLoader.getTexture("wariomodResources/images/ui/GoSquare" + BanditBoard.artStyle + ".png");
        goodness = GOODNESS.GOOD;
        triggersWhenPassed = true;
    }

    public void onLanded() {
        if (adp().hasPower(RacingSpeedPower.POWER_ID)) {
            att(new ApplyPowerAction(adp(), adp(), new DexterityPower(adp(), adp().getPower(RacingSpeedPower.POWER_ID).amount), adp().getPower(RacingSpeedPower.POWER_ID).amount));
            att(new ApplyPowerAction(adp(), adp(), new StrengthPower(adp(), adp().getPower(RacingSpeedPower.POWER_ID).amount), adp().getPower(RacingSpeedPower.POWER_ID).amount));
        }
        int bruh = DAMAGE;
        splat();
        att(new DamageAllEnemiesAction(adp(), DamageInfo.createDamageMatrix(bruh, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
        att(new VFXAction(adp(), new SweepingBeamEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, AbstractDungeon.player.flipHorizontal), 0.4F));// 44
        att(new SFXAction("ATTACK_DEFECT_BEAM"));// 43
    }

    public String getBodyText() {
        int bruh = DAMAGE;
        if (adp().hasPower(RacingSpeedPower.POWER_ID)) {
            int x = adp().getPower(RacingSpeedPower.POWER_ID).amount;
            return TEXT[0] + bruh + TEXT[1] + x + TEXT[2] + x + TEXT[3];
        } else
            return TEXT[0] + bruh + TEXT[4];
    }
}
