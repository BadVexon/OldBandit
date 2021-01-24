package theWario.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import theWario.WarioMod;
import theWario.cards.Kaboom;
import theWario.util.TextureLoader;

public class AbandonGamePower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = WarioMod.makeID("AbandonGamePower");

    private static final Texture tex84 = TextureLoader.getTexture("wariomodResources/images/powers/Abandon_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("wariomodResources/images/powers/Abandon_power32.png");

    public AbandonGamePower() {
        this.name = "GAME OVER";
        this.ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        this.amount = -1;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.canGoNegative = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        description = "You can no longer #yMove. Whenever you would #yMove, deal that much damage to a random enemy.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new AbandonGamePower();
    }
}