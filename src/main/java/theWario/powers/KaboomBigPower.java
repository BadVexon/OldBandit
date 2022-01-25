package theWario.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theWario.WarioMod;
import theWario.util.TextureLoader;

public class KaboomBigPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = WarioMod.makeID("KaboomBigPower");

    private static final Texture tex84 = TextureLoader.getTexture("wariomodResources/images/powers/Donate_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("wariomodResources/images/powers/Donate_power32.png");

    public KaboomBigPower(final int amount) {
        this.name = "Bomblasting";
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
            description = "Whenever you land on a #yKaboom #ySpace, gain #b" +amount + " #yStrength for the turn.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new KaboomBigPower(amount);
    }
}