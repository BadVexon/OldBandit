package theWario.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import theWario.WarioMod;
import theWario.cards.Kaboom;
import theWario.util.TextureLoader;

public class PoisonBombsPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = WarioMod.makeID("PoisonBombsPower");

    private static final Texture tex84 = TextureLoader.getTexture("wariomodResources/images/powers/PsnBombs_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("wariomodResources/images/powers/PsnBombs_power32.png");

    public PoisonBombsPower(final int amount) {
        this.name = "Poison Bombs";
        this.ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.canGoNegative = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof Kaboom) {
            flash();
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!m.isDead && !m.isDying) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, owner, new PoisonPower(m, owner, amount), amount));
                }
            }
        }
    }

    @Override
    public void updateDescription() {
        description = "Whenever a #yKaboom goes Boom, apply #b" + amount + " #yPoison to ALL enemies.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new PoisonBombsPower(amount);
    }
}