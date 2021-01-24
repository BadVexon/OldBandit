package theWario.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.exordium.*;

import java.util.ArrayList;

import static java.lang.Math.min;

public class SummonGremlinAction extends AbstractGameAction {

    public SummonGremlinAction() {
        this.actionType = ActionType.SPECIAL;
    }

    @Override
    public void update() {
        if (!AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            float offsetX = 0;
            float offsetY = 0;
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!m.isDeadOrEscaped()) {
                    offsetX = min(((m.drawX - ((float) Settings.WIDTH * 0.75F)) / Settings.scale), offsetX);
                    offsetY = min(m.drawY, offsetY);
                }
            }

            int bruh = AbstractDungeon.cardRandomRng.random(4);
            switch (bruh) {
                case 0:
                    AbstractDungeon.actionManager.addToTop(new SpawnMonsterAction(new GremlinFat(offsetX - (150F * Settings.scale), offsetY - (10F * Settings.scale)), true));
                    break;
                case 1:
                    AbstractDungeon.actionManager.addToTop(new SpawnMonsterAction(new GremlinThief(offsetX - (150F * Settings.scale), offsetY - (10F * Settings.scale)), true));
                    break;
                case 2:
                    AbstractDungeon.actionManager.addToTop(new SpawnMonsterAction(new GremlinTsundere(offsetX - (150F * Settings.scale), offsetY - (10F * Settings.scale)), true));
                    break;
                case 3:
                    AbstractDungeon.actionManager.addToTop(new SpawnMonsterAction(new GremlinWarrior(offsetX - (150F * Settings.scale), offsetY - (10F * Settings.scale)), true));
                    break;
                case 4:
                    AbstractDungeon.actionManager.addToTop(new SpawnMonsterAction(new GremlinWizard(offsetX - (150F * Settings.scale), offsetY - (10F * Settings.scale)), true));
                    break;
            }
        }
        this.isDone = true;
    }
}