package theWario.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theWario.WarioMod;
import theWario.cards.HatMove;
import theWario.cards.SuperHatMove;
import theWario.util.TextureLoader;

import static theWario.WarioMod.makeRelicOutlinePath;
import static theWario.WarioMod.makeRelicPath;

public class SuperHat extends CustomRelic {

    public static final String ID = WarioMod.makeID("SuperHat");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("SuperHat.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Prayer_Bead.png"));

    public SuperHat() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.FLAT);
    }

    @Override
    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new SuperHatMove()));
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(TheHat.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(TheHat.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(TheHat.ID);
    }

    @Override
    public String getUpdatedDescription()
    {
        // Colorize the starter relic's name
        String name = new TheHat().name;
        StringBuilder sb = new StringBuilder();
        for (String word : name.split(" ")) {
            sb.append("[#").append(WarioMod.chemColor.toString()).append("]").append(word).append("[] ");
        }
        sb.setLength(sb.length()-1);
        sb.append("[#").append(WarioMod.chemColor.toString()).append("]");

        return DESCRIPTIONS[0] + sb.toString() + DESCRIPTIONS[1];
    }
}
