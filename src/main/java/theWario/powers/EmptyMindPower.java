package theWario.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theWario.WarioMod;
import theWario.squares.EmptySquare;
import theWario.util.TextureLoader;

import static theWario.WarioMod.theBoard;

public class EmptyMindPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = WarioMod.makeID("EmptyMindPower");

    private static final Texture tex84 = TextureLoader.getTexture("wariomodResources/images/powers/Empty_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("wariomodResources/images/powers/Empty_power32.png");

    public EmptyMindPower(final int amount) {
        this.name = "Empty Mind";
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
        if (isPlayer) {
            if (theBoard.squareList.get(theBoard.player.position) instanceof EmptySquare) {
                flash();
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(owner, owner, amount));
            }
        }
    }

    @Override
    public void updateDescription() {
        description = "At the end of your turn, if you're on an Empty Space, gain #b" + amount + " #yBlock.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new EmptyMindPower(amount);
    }
}