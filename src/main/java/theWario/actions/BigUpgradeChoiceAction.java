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

public class BigUpgradeChoiceAction implements RenderSubscriber, PostUpdateSubscriber {

    private AbstractSquare hoveredSquare;
    private Vector2 controlPoint;
    private Vector2 origin;
    private float arrowScale;
    private float arrowScaleTimer;
    private Vector2[] points = new Vector2[20];
    private boolean isHidden;
    public boolean isDone = true;

    public BigUpgradeChoiceAction() {
        AbstractDungeon.isScreenUp = true;
        this.origin = new Vector2(theBoard.player.location.x, theBoard.player.location.y);
        BaseMod.subscribe(this);
        this.isHidden = false;
        for (int i = 0; i < this.points.length; i++) {
            this.points[i] = new Vector2();
        }
    }

    private void close() {
        AbstractDungeon.isScreenUp = false;
        this.isHidden = true;
        BigUpgradeChoiceAction vex = this;
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
            if ((s.hb.hovered) && !s.triggersWhenPassed) {
                this.hoveredSquare = s;
                break;
            }
        }
        for (AbstractSquare s : theBoard.squareList)
            s.superHover = s == hoveredSquare;


        if (InputHelper.justClickedLeft || InputHelper.justReleasedClickRight) {
            if (this.hoveredSquare != null) {
                AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                    @Override
                    public void update() {
                        hoveredSquare.triggersWhenPassed = true;
                        isDone = true;
                    }
                });
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
            FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, "Choose a Space to upgrade.", (float) (Settings.WIDTH / 2), (float) Settings.HEIGHT - 110.0F * Settings.scale, Settings.CREAM_COLOR);// 636
        }
    }

    public void receivePostUpdate() {
        if (!this.isHidden) {
            updateTargetMode();
        }
    }
}