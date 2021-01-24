package theWario.squares;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.localization.UIStrings;
import theWario.actions.TransformSquareAction;
import theWario.powers.DonateDrawPower;
import theWario.relics.SunglassHat;
import theWario.util.AbstractDrone;
import theWario.util.DamageCurvy;
import theWario.util.DamageLine;

import static theWario.RazsBoard.*;
import static theWario.WarioMod.theBoard;

public abstract class AbstractSquare {

    protected final UIStrings uiStrings;
    protected final String[] TEXT;

    public AbstractSquare(int x2, int y2, String ID) {
        x = x2;
        y = y2;
        hb = new Hitbox(x2, y2, 64 * Settings.scale, 64 * Settings.scale);
        hb.translate(x2, y2);
        uiStrings = CardCrawlGame.languagePack.getUIString(ID);
        TEXT = uiStrings.TEXT;
    }

    protected int maxLines = 36;
    protected int stride = 360 / maxLines;
    protected float offset = MathUtils.random(-180.0F, 180.0F);


    public boolean triggersWhenPassed = false;

    public boolean explodeOnUse = false;

    public GOODNESS goodness;

    public boolean hover = false;
    public boolean superHover = false;

    public Hitbox hb;

    protected Texture tex;

    public int x;
    public int y;

    public void render(SpriteBatch sb) {
        sb.draw(tex, x, y, tex.getWidth() / 2F, tex.getHeight() / 2F, tex.getWidth(), tex.getHeight(), Settings.scale, Settings.scale, 0, 0, 0, tex.getWidth(), tex.getHeight(), false, false);
        renderOutline(sb);
    }

    public void renderOutline(SpriteBatch sb) {
        if (this.hover)
            FontHelper.renderFontCentered(sb, FontHelper.energyNumFontRed, String.valueOf(((theBoard.squareList.indexOf(this) - theBoard.player.position) + theBoard.squareList.size()) % theBoard.squareList.size()), x + ((64 / 2F)), y + ((64 / 2F)), Color.WHITE);
        if (superHover) {
            sb.draw(canPickOutline, x, y, canPickOutline.getWidth() / 2F, canPickOutline.getHeight() / 2F, canPickOutline.getWidth(), canPickOutline.getHeight(), Settings.scale, Settings.scale, 0, 0, 0, canPickOutline.getWidth(), canPickOutline.getHeight(), false, false);
        } else if (droneOnThis()) {
            sb.draw(onOutline, x, y, onOutline.getWidth() / 2F, onOutline.getHeight() / 2F, onOutline.getWidth(), onOutline.getHeight(), Settings.scale, Settings.scale, 0, 0, 0, onOutline.getWidth(), onOutline.getHeight(), false, false);
        } else if (this.triggersWhenPassed || (this instanceof DrawSquare && adp().hasPower(DonateDrawPower.POWER_ID))) {
            sb.draw(activatesWhenPassedOutline, x, y, activatesWhenPassedOutline.getWidth() / 2F, activatesWhenPassedOutline.getHeight() / 2F, activatesWhenPassedOutline.getWidth(), activatesWhenPassedOutline.getHeight(), Settings.scale, Settings.scale, 0, 0, 0, activatesWhenPassedOutline.getWidth(), activatesWhenPassedOutline.getHeight(), false, false);
        } else if (this instanceof DoomSquare) {
            sb.draw(deathOutline, x, y, deathOutline.getWidth() / 2F, deathOutline.getHeight() / 2F, deathOutline.getWidth(), deathOutline.getHeight(), Settings.scale, Settings.scale, 0, 0, 0, deathOutline.getWidth(), deathOutline.getHeight(), false, false);
        } else if (this.goodness == GOODNESS.GOOD) {
            sb.draw(goodOutline, x, y, goodOutline.getWidth() / 2F, goodOutline.getHeight() / 2F, goodOutline.getWidth(), goodOutline.getHeight(), Settings.scale, Settings.scale, 0, 0, 0, goodOutline.getWidth(), goodOutline.getHeight(), false, false);
        } else if (this.goodness == GOODNESS.BAD) {
            sb.draw(badOutline, x, y, badOutline.getWidth() / 2F, badOutline.getHeight() / 2F, badOutline.getWidth(), badOutline.getHeight(), Settings.scale, Settings.scale, 0, 0, 0, badOutline.getWidth(), badOutline.getHeight(), false, false);
        } else {
            sb.draw(neutralOutline, x, y, neutralOutline.getWidth() / 2F, neutralOutline.getHeight() / 2F, neutralOutline.getWidth(), neutralOutline.getHeight(), Settings.scale, Settings.scale, 0, 0, 0, neutralOutline.getWidth(), neutralOutline.getHeight(), false, false);
        }
    }

    public boolean droneOnThis() {
        for (AbstractDrone r : theBoard.droneList) {
            if (theBoard.squareList.get(r.position) == this)
                return true;
        }
        return false;
    }

    public void uponLand() {
        if (explodeOnUse) {
            if (AbstractDungeon.player.hasRelic(SunglassHat.ID)) {
                if (!AbstractDungeon.player.getRelic(SunglassHat.ID).checkTrigger()) {
                    AbstractDungeon.player.getRelic(SunglassHat.ID).flash();
                    ((SunglassHat) AbstractDungeon.player.getRelic(SunglassHat.ID)).activatedThisCombat = true;
                } else {
                    AbstractDungeon.actionManager.addToTop(new TransformSquareAction(this, EmptySquare.class));
                }
            } else
                AbstractDungeon.actionManager.addToTop(new TransformSquareAction(this, EmptySquare.class));
        }
        onLanded();
    }

    public abstract void onLanded();

    public void onPassed() {
        if (triggersWhenPassed) {
            splat();
            uponLand();
        }
    }

    public abstract String getBodyText();

    protected void att(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToTop(action);
    }

    protected void atb(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToTop(action);
    }

    public AbstractPlayer adp() {
        return AbstractDungeon.player;
    }

    public void splat() {
        for (int i = 0; i < maxLines; i++) {
            AbstractDungeon.effectList.add(new DamageLine(this.hb.cX, this.hb.cY, new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1), ((stride * i) + MathUtils.random(-stride, stride) + offset)));
            if (i % 2 == 0) {
                AbstractDungeon.effectList.add(new DamageCurvy(this.hb.cX, this.hb.cY, new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1)));
            }
        }
    }

    public enum GOODNESS {
        GOOD,
        OKAY,
        BAD
    }
}
