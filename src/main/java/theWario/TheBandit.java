package theWario;

import basemod.abstracts.CustomEnergyOrb;
import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.powers.DoubleTapPower;
import com.megacrit.cardcrawl.relics.Necronomicon;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import theWario.cards.*;
import theWario.powers.AbandonGamePower;
import theWario.powers.ImprisonedPower;
import theWario.relics.DoomHat;
import theWario.relics.TheHat;
import theWario.squares.AbstractSquare;
import theWario.squares.JailSquare;
import theWario.util.AbstractDrone;

import java.util.ArrayList;
import java.util.List;

import static theWario.TheBandit.Enums.BANDIT_WEIRD;
import static theWario.WarioMod.*;

public class TheBandit extends CustomPlayer {
    private static final String[] orbTextures = {
            "wariomodResources/images/char/mainChar/orb/layer1.png",
            "wariomodResources/images/char/mainChar/orb/layer2.png",
            "wariomodResources/images/char/mainChar/orb/layer3.png",
            "wariomodResources/images/char/mainChar/orb/layer4.png",
            "wariomodResources/images/char/mainChar/orb/layer5.png",
            "wariomodResources/images/char/mainChar/orb/layer6.png",
            "wariomodResources/images/char/mainChar/orb/layer1d.png",
            "wariomodResources/images/char/mainChar/orb/layer2d.png",
            "wariomodResources/images/char/mainChar/orb/layer3d.png",
            "wariomodResources/images/char/mainChar/orb/layer4d.png",
            "wariomodResources/images/char/mainChar/orb/layer5d.png",};
    private static final String ID = makeID("theWario");
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    private static final String[] NAMES = characterStrings.NAMES;
    private static final String[] TEXT = characterStrings.TEXT;


    public TheBandit(String name, PlayerClass setClass) {
        super(name, setClass, new CustomEnergyOrb(orbTextures, "wariomodResources/images/char/mainChar/orb/vfx.png", null), new SpriterAnimation(
                "wariomodResources/images/char/mainChar/bandit_resized.scml"));
        initializeClass(null,
                SHOULDER1,
                SHOULDER2,
                CORPSE,
                getLoadout(), 20.0F, -10.0F, 166.0F, 327.0F, new EnergyManager(3));


        dialogX = (drawX + 0.0F * Settings.scale);
        dialogY = (drawY + 240.0F * Settings.scale);
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                80, 80, 0, 99, 5, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            retVal.add(Strike.ID);
        }
        for (int i = 0; i < 4; i++) {
            retVal.add(Defend.ID);
        }
        retVal.add(Step.ID);
        retVal.add(Leap.ID);
        return retVal;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(TheHat.ID);
        return retVal;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("UNLOCK_PING", MathUtils.random(-0.2F, 0.2F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "UNLOCK_PING";
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 8;
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return BANDIT_WEIRD;
    }

    @Override
    public Color getCardTrailColor() {
        return chemColor.cpy();
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new Step();
    }

    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return NAMES[1];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new TheBandit(name, chosenClass);
    }

    @Override
    public Color getCardRenderColor() {
        return chemColor.cpy();
    }

    @Override
    public Color getSlashAttackColor() {
        return chemColor.cpy();
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.FIRE,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.FIRE};
    }

    @Override
    public List<CutscenePanel> getCutscenePanels() {
        List<CutscenePanel> panels = new ArrayList<>();
        if (AbstractDungeon.player.hasRelic(DoomHat.ID))
            panels.add(new CutscenePanel("wariomodResources/images/charSelect/thingy.png"));
        else
            panels.add(new CutscenePanel("wariomodResources/images/charSelect/thingy2.png"));
        return panels;
    }

    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }

    @Override
    public String getVampireText() {
        return TEXT[2];
    }

    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass THE_BANDIT;
        @SpireEnum(name = "BANDIT_WEIRD_COLOR")
        public static AbstractCard.CardColor BANDIT_WEIRD;
        @SpireEnum(name = "BANDIT_WEIRD_COLOR")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }
}
