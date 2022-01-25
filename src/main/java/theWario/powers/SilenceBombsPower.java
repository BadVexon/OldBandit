package theWario.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theWario.WarioMod;
import theWario.cards.Kaboom;
import theWario.util.TextureLoader;

public class SilenceBombsPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = WarioMod.makeID("SilenceBombsPower");

    private static final Texture tex84 = TextureLoader.getTexture("wariomodResources/images/powers/SilenceBombs_84.png");
    private static final Texture tex32 = TextureLoader.getTexture("wariomodResources/images/powers/SilenceBombs_32.png");

    public SilenceBombsPower(final int amount) {
        this.name = "Block Bombs";
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
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(owner, owner, amount));
        }
    }

    @Override
    public void updateDescription() {
        description = "Whenever a #yKaboom goes Boom, gain #b" + amount + " #yBlock.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new SilenceBombsPower(amount);
    }
}