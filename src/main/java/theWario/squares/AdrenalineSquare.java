package theWario.squares;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.AdrenalineEffect;
import static theWario.WarioMod.theBoard;
import theWario.actions.TransformSquareAction;
import theWario.util.TextureLoader;

public class AdrenalineSquare extends AbstractSquare {
    public AdrenalineSquare(int x, int y) {
        super(x, y, "AdrenalineSquare");
        tex = TextureLoader.getTexture("wariomodResources/images/ui/AdrenalineSquare" + theBoard.artStyle + ".png");
        goodness = GOODNESS.GOOD;
        explodeOnUse = true;
    }

    public void onLanded() {
        att(new DrawCardAction(adp(), 2));
        att(new GainEnergyAction(1));
        att(new VFXAction(new AdrenalineEffect()));
    }

    public String getBodyText() {
        return TEXT[0];
    }
}
