package theWario.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import theWario.WarioMod;
import theWario.util.TextureLoader;

import static theWario.WarioMod.makeRelicOutlinePath;
import static theWario.WarioMod.makeRelicPath;

public class PropellerHat extends CustomRelic {

    public static final String ID = WarioMod.makeID("PropellerHat");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("PropellerHat.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Propeller_outline.png"));

    public boolean activatedThisTurn = false;

    public PropellerHat() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.FLAT);
    }

    @Override
    public void atTurnStart() {
        activatedThisTurn = false;
    }

    @Override
    public boolean checkTrigger() {
        return activatedThisTurn;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
