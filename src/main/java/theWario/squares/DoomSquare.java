package theWario.squares;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import static theWario.WarioMod.theBoard;
import theWario.actions.YeetPlayerAction;
import theWario.util.DamageCurvy;
import theWario.util.DamageLine;
import theWario.util.TextureLoader;

public class DoomSquare extends AbstractSquare {
    public DoomSquare(int x, int y) {
        super(x, y,"DoomSquare");
        tex = TextureLoader.getTexture("wariomodResources/images/ui/DeathSquare" + theBoard.artStyle + ".png");
        goodness = GOODNESS.BAD;
    }

    public void onLanded() {
        splat();
        splat();
        splat();
        splat();
        splat();
        att(new YeetPlayerAction());
    }

    @Override
    public void splat() {
        for (int i = 0; i < maxLines; i++) {
            AbstractDungeon.effectList.add(new DamageLine(this.hb.cX, this.hb.cY, Color.BLACK.cpy(), ((stride * i) + MathUtils.random(-stride, stride) + offset)));
            if (i % 2 == 0) {
                AbstractDungeon.effectList.add(new DamageCurvy(this.hb.cX, this.hb.cY, Color.BLACK.cpy()));
            }
        }
    }

    public String getBodyText() {
        return TEXT[0];
    }
}
