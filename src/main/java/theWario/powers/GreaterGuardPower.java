package theWario.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theWario.WarioMod;
import theWario.util.TextureLoader;

public class GreaterGuardPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = WarioMod.makeID("GreaterGuardPower");

    private static final Texture tex84 = TextureLoader.getTexture("wariomodResources/images/powers/Greater_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("wariomodResources/images/powers/Greater_power32.png");

    public GreaterGuardPower(final int amount) {
        this.name = "Greater Guard";
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
    public void updateDescription() {
        description = "Block Spaces grant #b" + amount + " #yBlock next turn.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new GreaterGuardPower(amount);
    }
}