package theWario.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import theWario.util.AbstractDrone;

import static theWario.WarioMod.theBoard;

public class WeirdMoveGuyAction extends AbstractGameAction {
    float startX;
    float startY;
    float endX;
    float endY;

    float cur;
    float speed;

    private AbstractDrone drone2;

    public WeirdMoveGuyAction(float x, float y, float speed, AbstractDrone drone) {
        this.endX = x;
        this.endY = y;

        this.speed = speed;
        this.cur = 0;

        if (drone != null)
            drone2 = drone;
    }

    public void update() {
        if (startX == 0.0F) {
            if (drone2 == null) {
                startX = theBoard.player.location.x;
                startY = theBoard.player.location.y;
            } else {
                startX = drone2.location.x;
                startY = drone2.location.y;
            }

        }
        cur += Gdx.graphics.getDeltaTime();

        if (cur > speed)
            cur = speed;

        if (drone2 != null) {
            /*
            drone2.location.x = (int) (Math.ceil(Interpolation.linear.apply(startX, endX, cur / speed)));
            drone2.location.y = (int) (Math.ceil(Interpolation.linear.apply(startY, endY, cur / speed)));
            */
            drone2.location.x = (int) Interpolation.linear.apply(startX, endX, cur / speed);
            drone2.location.y = (int) Interpolation.linear.apply(startY, endY, cur / speed);
        } else {
            /*
            theBoard.player.location.x = (int) (Math.ceil(Interpolation.linear.apply(startX, endX, cur / speed)));
            theBoard.player.location.y = (int) (Math.ceil(Interpolation.linear.apply(startY, endY, cur / speed)));
           */
            theBoard.player.location.x = (int) Interpolation.linear.apply(startX, endX, cur / speed);
            theBoard.player.location.y = (int) Interpolation.linear.apply(startY, endY, cur / speed);
        }
        if (cur == speed) {
            this.isDone = true;
        }
    }
}