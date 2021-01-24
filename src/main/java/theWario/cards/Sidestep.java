package theWario.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.TimeWarpTurnEndEffect;
import theWario.powers.TimeStopPower;
import theWario.squares.DoomSquare;

public class Sidestep extends AbstractWarioCard {

    public final static String ID = makeID("Sidestep");

    //stupid intellij stuff SKILL, SELF, RARE


    public Sidestep() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                CardCrawlGame.sound.play("POWER_TIME_WARP", 0.05F);
                AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.GOLD, true));
                AbstractDungeon.topLevelEffectsQueue.add(new TimeWarpTurnEndEffect());
            }
        });
        applyToSelf(new TimeStopPower(p, 1));
        transformAny(DoomSquare.class, magicNumber);
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(-2);
        }
    }
}