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
import theWario.squares.AbstractSquare;

import static theWario.WarioMod.theBoard;

public class SwappingSpaceAction implements RenderSubscriber, PostUpdateSubscriber {

    private AbstractSquare hoveredSquare;
    private boolean isHidden;
    public boolean isDone = true;
    private SpaceSwapAction myBuddy;

    public SwappingSpaceAction(SpaceSwapAction budf) {
        AbstractDungeon.isScreenUp = true;
        BaseMod.subscribe(this);
        this.isHidden = false;
        myBuddy = budf;
    }

    private void close() {
        AbstractDungeon.isScreenUp = false;
        this.isHidden = true;
        SwappingSpaceAction vex = this;
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
                if (myBuddy.swap1 == null) {
                    myBuddy.swap1 = this.hoveredSquare;
                } else {
                    myBuddy.swap2 = this.hoveredSquare;
                }
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
            if (myBuddy.swap1 == null) {
                FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, "Choose the first Space to swap.", (float) (Settings.WIDTH / 2), (float) Settings.HEIGHT - 110.0F * Settings.scale, Settings.CREAM_COLOR);// 636
            } else {
                FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, "Choose the second Space to swap.", (float) (Settings.WIDTH / 2), (float) Settings.HEIGHT - 110.0F * Settings.scale, Settings.CREAM_COLOR);// 636
            }
        }
    }

    private void drawCurvedLine(SpriteBatch sb, Vector2 start, Vector2 end, Vector2 control) {
    }

    public void receivePostUpdate() {
        if (!this.isHidden) {
            updateTargetMode();
        }
    }
}