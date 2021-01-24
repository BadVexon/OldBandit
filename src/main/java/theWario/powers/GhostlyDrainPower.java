package theWario.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theWario.WarioMod;
import theWario.util.TextureLoader;

public class GhostlyDrainPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = WarioMod.makeID("GhostlyDrainPower");

    private static final Texture tex84 = TextureLoader.getTexture("wariomodResources/images/powers/Ghostly_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("wariomodResources/images/powers/Ghostly_power32.png");

    public GhostlyDrainPower(final int amount) {
        this.name = "Ghostly Drain";
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
    public void atStartOfTurnPostDraw() {
        flash();
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m.isDying && !m.isDead) {
                AbstractDungeon.actionManager.addToBottom(new LoseHPAction(m, owner, amount));
            }
        }
    }

    @Override
    public void updateDescription() {
        description = "At the start of your turn, ALL enemies lose #b" + amount + " HP.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new GhostlyDrainPower(amount);
    }
}