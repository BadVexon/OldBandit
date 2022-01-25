package theWario.actions;

import basemod.BaseMod;
import basemod.interfaces.PostUpdateSubscriber;
import basemod.interfaces.RenderSubscriber;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import theWario.WarioMod;
import theWario.squares.AbstractSquare;

import static theWario.WarioMod.theBoard;

public class MoveChoiceAction implements RenderSubscriber, PostUpdateSubscriber {

    private AbstractSquare hoveredSquare;
    private Vector2[] points = new Vector2[10];
    private boolean isHidden;
    public boolean isDone = true;
    private int amount;

    public MoveChoiceAction(int range) {
        AbstractDungeon.isScreenUp = true;
        WarioMod.showBanditBoardInScreenUp = true;
        amount = range;
        BaseMod.subscribe(this);
        this.isHidden = false;
        for (int i = 0; i < this.points.length; i++) {
            this.points[i] = new Vector2();
        }
        for (AbstractSquare s : theBoard.squareList) {
            if (((theBoard.squareList.indexOf(s) - theBoard.player.position) + theBoard.squareList.size()) % theBoard.squareList.size() <= amount && !(theBoard.player.position == theBoard.squareList.indexOf(s))) {
                s.hover = true;
            }
        }
    }

    private void close() {
        AbstractDungeon.isScreenUp = false;
        WarioMod.showBanditBoardInScreenUp = false;
        this.isHidden = true;
        MoveChoiceAction vex = this;
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
            if ((s.hb.hovered) && ((theBoard.squareList.indexOf(s) - theBoard.player.position) + theBoard.squareList.size()) % theBoard.squareList.size() <= amount && !(theBoard.player.position == theBoard.squareList.indexOf(s))) {
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