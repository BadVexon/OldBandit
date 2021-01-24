package theWario.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theWario.TheBandit;
import theWario.WarioMod;
import theWario.cards.HatMove;
import theWario.util.TextureLoader;

import static theWario.WarioMod.makeRelicOutlinePath;
import static theWario.WarioMod.makeRelicPath;

public class TheHat extends CustomRelic {

    public static final String ID = WarioMod.makeID("TheHat");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("BentBrush.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Prayer_Bead.png"));

    public TheHat() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.FLAT);
    }

    @Override
    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new HatMove()));
    }

    @Override
    public void onUnequip() {
        if (AbstractDungeon.player instanceof TheBandit) {
            AbstractDungeon.player.shoulderImg = TextureLoader.getTexture("wariomodResources/images/char/mainChar/shoulder.png");
            AbstractDungeon.player.shoulder2Img= TextureLoader.getTexture("wariomodResources/images/char/mainChar/shoulder2.png");
        }
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
