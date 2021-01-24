package theWario.powers;

import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.powers.ConservePower;
import theWario.actions.StartNewTurnAction;

import java.util.HashMap;
import java.util.Map;

public class TimeStopPower extends AbstractPower {
    public static final String POWER_ID = "relicPack:TimeStop";

    private Map<AbstractMonster, Float> timeScales;

    public TimeStopPower(AbstractCreature owner, int amount) {
        name = "Cheating Death";
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        type = PowerType.BUFF;
        updateDescription();
        loadRegion("time");
    }

    @Override
    public void updateDescription() {
        if (this.amount == 0) {
            description = "Your jaunt with time has ended.";
        } else if (this.amount == 1) {
            description = "Take #b" + this.amount + " additional turn after this one.";
        } else {
            description = "Take #b" + this.amount + " additional turns after this one.";
        }

    }

    @Override
    public void onInitialApplication() {
        timeScales = new HashMap<>();
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (m.state != null) {
                AnimationState.TrackEntry e = m.state.getCurrent(0);
                if (e != null) {
                    timeScales.put(m, e.getTimeScale());
                    e.setTimeScale(0);
                }
            }
        }
    }

    @Override
    public void onRemove() {
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (m.state != null) {
                AnimationState.TrackEntry e = m.state.getCurrent(0);
                if (e != null) {
                    if (timeScales.containsKey(m)) {
                        e.setTimeScale(timeScales.get(m));
                    }
                }
            }
        }
    }

    @Override
    public void atStartOfTurn() {
        if (amount == 0) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, ID));
        } else {
            reducePower(1);
            updateDescription();
            AbstractDungeon.onModifyPower();
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            if (amount == 0) {
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, ID));
            } else {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new ConservePower(owner, 1), 1));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new BlurPower(owner, 1), 1));
                AbstractDungeon.actionManager.addToBottom(new StartNewTurnAction());
            }
        }
    }

    @Override
    public void update(int slot) {
        super.update(slot);

        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (m.state != null) {
                AnimationState.TrackEntry e = m.state.getCurrent(0);
                if (e != null) {
                    if (!timeScales.containsKey(m)) {
                        timeScales.put(m, e.getTimeScale());
                    }
                    e.setTimeScale(0);
                }
            }
        }
    }
}