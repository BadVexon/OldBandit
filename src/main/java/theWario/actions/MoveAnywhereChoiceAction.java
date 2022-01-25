package theWario.actions;

import basemod.BaseMod;
import basemod.interfaces.PostUpdateSubscriber;
import basemod.interfaces.RenderSubscriber;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.GameCursor;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import theWario.WarioMod;
import theWario.squares.AbstractSquare;

import static theWario.WarioMod.theBoard;

public class MoveAnywhereChoiceAction implements RenderSubscriber, PostUpdateSubscriber {

    private AbstractSquare hoveredSquare;
    private boolean isHidden;
    public boolean isDone = true;

    public MoveAnywhereChoiceAction() {
        AbstractDungeon.isScreenUp = true;
        WarioMod.showBanditBoardInScreenUp = true;
        BaseMod.subscribe(this);
        this.isHidden = false;
        for (AbstractSquare s : theBoard.squareList) {
            s.hover = true;
        }
    }

    private void close() {
        AbstractDungeon.isScreenUp = false;
        WarioMod.showBanditBoardInScreenUp = false;
        this.isHidden = true;
        MoveAnywhereChoiceAction vex = this;
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            @Override
            public void update() {
                BaseMod.unsubscribe(vex);
                this.isDone = true;
            }
        });
        this.isDone = true;
        for (AbstractSquare s : theBoard.squareList) {
            s.hover = false;
            s.superHover = false;
        }
    }

    private void updateTargetMode() {
        this.hoveredSquare = null;
        for (AbstractSquare s : theBoard.squareList) {
            if ((s.hb.hovered)) {
                this.hoveredSquare = s;
                break;
            }
        }
        for (AbstractSquare s : theBoard.squareList)
            s.superHover = s == hoveredSquare;


        if (InputHelper.justClickedLeft || InputHelper.justReleasedClickRight) {
            if (this.hoveredSquare != null) {
                int motion = theBoard.squareList.indexOf(hoveredSquare) - theBoard.player.position;
                if (motion < 0) motion += theBoard.squareList.size();
                AbstractDungeon.actionManager.addToBottom(new MoveAction(motion));
                close();
            }
        }
    }

    @Override
    public void receiveRender(SpriteBatch sb) {
        render(sb);
    }

    public void render(SpriteBatch sb) {
        if (!this.isHidden) {
            FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, "Choose a distance to Move.", (float) (Settings.WIDTH / 2), (float) Settings.HEIGHT - 110.0F * Settings.scale, Settings.CREAM_COLOR);// 636
        }
    }


    public void receivePostUpdate() {
        if (!this.isHidden) {
            updateTargetMode();
        }
    }
}