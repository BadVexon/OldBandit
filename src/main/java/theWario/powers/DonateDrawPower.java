package theWario.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theWario.WarioMod;
import theWario.util.TextureLoader;

public class DonateDrawPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = WarioMod.makeID("DonateDrawPower");

    private static final Texture tex84 = TextureLoader.getTexture("wariomodResources/images/powers/Donate_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("wariomodResources/images/powers/Donate_power32.png");

    public DonateDrawPower(final int amount) {
        this.name = "Donated Draw";
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
        if (amount == 1)
            description = "Whenever you pass a #yDraw #ySpace, draw #b" + amount + " card.";
        else
            description = "Whenever you pass a #yDraw #ySpace, draw #b" + amount + " cards.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new DonateDrawPower(amount);
    }
}