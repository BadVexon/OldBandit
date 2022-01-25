package theWario.squares;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAndDeckAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theWario.BanditBoard;
import theWario.cards.Kaboom;
import theWario.powers.KaboomBigPower;
import theWario.util.TextureLoader;

public class KaboomSquare extends AbstractSquare {

    public KaboomSquare(int x, int y) {
        super(x, y, "KaboomSquare");
        tex = TextureLoader.getTexture("wariomodResources/images/ui/KaboomSquare" + BanditBoard.artStyle + ".png");
        goodness = GOODNESS.GOOD;
    }

    public void onLanded() {
        if (AbstractDungeon.player.hasPower(KaboomBigPower.POWER_ID)) {
            att(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LoseStrengthPower(AbstractDungeon.player, AbstractDungeon.player.getPower(KaboomBigPower.POWER_ID).amount), AbstractDungeon.player.getPower(KaboomBigPower.POWER_ID).amount));
            att(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, AbstractDungeon.player.getPower(KaboomBigPower.POWER_ID).amount), AbstractDungeon.player.getPower(KaboomBigPower.POWER_ID).amount));
        }
        att(new MakeTempCardInDiscardAndDeckAction(new Kaboom()));
    }

    public String getBodyText() {
        if (AbstractDungeon.player.hasPower(KaboomBigPower.POWER_ID)) {
            return TEXT[0] + 1 + TEXT[2] + AbstractDungeon.player.getPower(KaboomBigPower.POWER_ID).amount + TEXT[3];
        } else {
            return TEXT[0] + 1 + TEXT[1];
        }
    }
}
