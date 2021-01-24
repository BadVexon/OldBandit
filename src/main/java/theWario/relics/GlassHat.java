package theWario.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import theWario.WarioMod;
import theWario.util.TextureLoader;

import static theWario.WarioMod.makeRelicOutlinePath;
import static theWario.WarioMod.makeRelicPath;

public class GlassHat extends CustomRelic {

    public static final String ID = WarioMod.makeID("GlassHat");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("GlassHat.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Prayer_Bead.png"));

    public GlassHat() {
        super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
