package theWario.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theWario.WarioMod;
import theWario.cards.TrekMove;
import theWario.util.TextureLoader;

public class TrekkingPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = WarioMod.makeID("TrekkingPower");

    private static final Texture tex84 = TextureLoader.getTexture("wariomodResources/images/powers/Trek_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("wariomodResources/images/powers/Trek_power32.png");

    public TrekkingPower(final int amount) {
        this.name = "Trekking";
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
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new TrekMove(amount)));
    }

    @Override
    public void updateDescription() {
        description = "At the start of your turn, add a card that moves up to #b" + amount + " Spaces into your hand.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new TrekkingPower(amount);
    }
}