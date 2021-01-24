package theWario.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import theWario.WarioMod;
import theWario.util.TextureLoader;

import static theWario.WarioMod.makeRelicOutlinePath;
import static theWario.WarioMod.makeRelicPath;

public class SunglassHat extends CustomRelic {

    public static final String ID = WarioMod.makeID("SunglassHat");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("SunglassHat.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Prayer_Bead.png"));

    public boolean activatedThisCombat = false;

    public SunglassHat() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    public void atBattleStart() {
        activatedThisCombat = false;
    }

    @Override
    public boolean checkTrigger() {
        return activatedThisCombat;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
