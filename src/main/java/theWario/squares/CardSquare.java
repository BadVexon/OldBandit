package theWario.squares;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import static theWario.WarioMod.theBoard;
import theWario.actions.ReplayThisAction;
import theWario.actions.TransformSquareAction;
import theWario.util.TextureLoader;

public class CardSquare extends AbstractSquare {

    private AbstractCard myCard;

    public CardSquare(int x, int y, AbstractCard c) {
        super(x, y, "CardSquare");
        myCard = c;
        tex = TextureLoader.getTexture("wariomodResources/images/ui/CardSquare" + theBoard.artStyle + ".png");
        goodness = GOODNESS.GOOD;
        explodeOnUse = true;
    }

    public void onLanded() {
        AbstractCard c = myCard.makeStatEquivalentCopy();
        att(new ReplayThisAction(AbstractDungeon.getRandomMonster(), c));
    }

    public String getBodyText() {
        return TEXT[0] + myCard.name + TEXT[1];
    }
}
