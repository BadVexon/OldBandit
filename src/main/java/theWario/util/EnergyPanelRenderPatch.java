package theWario.util;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theWario.TheBandit;
import theWario.WarioMod;

import static theWario.WarioMod.theBoard;

@SpirePatch(
        clz = EnergyPanel.class,
        method = "render",
        paramtypes = {"com.badlogic.gdx.graphics.g2d.SpriteBatch"}
)
public class EnergyPanelRenderPatch {
    public static void Prefix(EnergyPanel __instance, SpriteBatch sb) {
        if (AbstractDungeon.player instanceof TheBandit && theBoard != null && !AbstractDungeon.isScreenUp) {
            theBoard.render(sb);
        }
    }
}
