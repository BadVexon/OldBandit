package theWario.util;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theWario.TheBandit;
import theWario.squares.AbstractSquare;

import static theWario.WarioMod.renderStuff;
import static theWario.WarioMod.theBoard;

@SpirePatch(
        clz = EnergyPanel.class,
        method = "update"
)
public class DragUpdatePatch {
    public static void Prefix(EnergyPanel __instance) {
        if ((AbstractDungeon.player instanceof TheBandit || renderStuff) && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            for (AbstractSquare s : theBoard.squareList) {
                s.hb.update();
            }
        }
    }
}