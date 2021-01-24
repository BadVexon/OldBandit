package theWario.squares;

import com.megacrit.cardcrawl.actions.common.UpgradeRandomCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import static theWario.WarioMod.theBoard;

import theWario.BanditBoard;
import theWario.util.TextureLoader;

public class UpgradeSquare extends AbstractSquare {
    public UpgradeSquare(int x, int y) {
        super(x, y, "UpgradeSquare");
        tex = TextureLoader.getTexture("wariomodResources/images/ui/UpgradeSquare" + BanditBoard.artStyle + ".png");
        goodness = GOODNESS.GOOD;
    }

    public void onLanded() {
        AbstractDungeon.actionManager.addToBottom(new UpgradeRandomCardAction());
    }

    public String getBodyText() {
        return TEXT[0];
    }
}
