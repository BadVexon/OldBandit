package theWario.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAndDeckAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theWario.WarioMod;
import theWario.cards.Kaboom;
import theWario.util.TextureLoader;

public class BoomFormPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = WarioMod.makeID("BoomFormPower");

    private static final Texture tex84 = TextureLoader.getTexture("wariomodResources/images/powers/Cursed_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("wariomodResources/images/powers/Cursed_power32.png");

    public BoomFormPower(final int amount) {
        this.name = "Boom Form";
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
    public void atEndOfTurn(boolean isPlayer) {
        flash();
        for (int i = 0; i < amount; i++)
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAndDeckAction(new Kaboom()));
    }

    @Override
    public void updateDescription() {
        description = "At the end of your turn, add #b" + amount + " #yKaboom into your draw and discard piles.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new BoomFormPower(amount);
    }
}