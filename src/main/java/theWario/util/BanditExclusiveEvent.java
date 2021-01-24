package theWario.util;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.random.Random;
import javassist.CtBehavior;
import theWario.TheBandit;

import java.util.ArrayList;

@SpirePatch(clz = AbstractDungeon.class, method = "getEvent")
public class BanditExclusiveEvent {

    @SpireInsertPatch(locator = Locator.class, localvars = {"tmp"})
    public static void Insert(Random rng, ArrayList<String> tmp) {
        if (AbstractDungeon.player.chosenClass == TheBandit.Enums.THE_BANDIT) {
            tmp.removeIf(d -> d.equals(Vampires.ID));
        } else {
            tmp.removeIf(d -> d.equals(VampiresBanditEvent.ID));
        }
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher matcher = new Matcher.MethodCallMatcher(ArrayList.class, "isEmpty");
            return LineFinder.findInOrder(ctMethodToPatch, matcher);
        }
    }

}