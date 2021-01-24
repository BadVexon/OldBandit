package theWario.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theWario.WarioMod;
import theWario.util.TextureLoader;

public class ImprisonedPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = WarioMod.makeID("ImprisonedPower");

    private static final Texture tex84 = TextureLoader.getTexture("wariomodResources/images/powers/Imprison_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("wariomodResources/images/powers/Imprison_power32.png");

    public ImprisonedPower(final int amount) {
        this.name = "Imprisoned";
        this.ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        this.amount = amount;
        this.type = PowerType.DEBUFF;
        this.isTurnBased = false;
        this.canGoNegative = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = "Prevent the next time you would #yMove.";
        } else
            description = "Prevent the next #b" + amount + " times you would #yMove.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new ImprisonedPower(amount);
    }
}