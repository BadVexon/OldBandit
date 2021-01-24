package theWario;

import basemod.BaseMod;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import javassist.CtClass;
import javassist.Modifier;
import javassist.NotFoundException;
import org.clapper.util.classutil.*;
import theWario.relics.*;
import theWario.util.VampiresBanditEvent;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

@SuppressWarnings({"ConstantConditions", "unused", "WeakerAccess"})
@SpireInitializer
public class WarioMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber,
        OnStartBattleSubscriber,
        PostBattleSubscriber {
    public static final String SHOULDER1 = "wariomodResources/images/char/mainChar/shoulder3.png";
    public static final String SHOULDER2 = "wariomodResources/images/char/mainChar/shoulder4.png";
    public static final String CORPSE = "wariomodResources/images/char/mainChar/corpse.png";
    private static final String ATTACK_S_ART = "wariomodResources/images/512/canvas_attack_s.png";
    private static final String SKILL_S_ART = "wariomodResources/images/512/canvas_skill_s.png";
    private static final String POWER_S_ART = "wariomodResources/images/512/canvas_power_s.png";
    private static final String CARD_ENERGY_S = "wariomodResources/images/512/card_default_gray_orb.png";
    private static final String TEXT_ENERGY = "wariomodResources/images/512/card_small_orb.png";
    private static final String ATTACK_L_ART = "wariomodResources/images/1024/canvas_attack.png";
    private static final String SKILL_L_ART = "wariomodResources/images/1024/canvas_skill.png";
    private static final String POWER_L_ART = "wariomodResources/images/1024/canvas_power.png";
    private static final String CARD_ENERGY_L = "wariomodResources/images/1024/card_default_gray_orb.png";
    private static final String CHARSELECT_BUTTON = "wariomodResources/images/charSelect/charButton.png";
    private static final String CHARSELECT_PORTRAIT = "wariomodResources/images/charSelect/charBG.png";
    private static String modID;

    public static Color chemColor = new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1);

    public static BanditBoard theBoard;

    public WarioMod() {

        BaseMod.subscribe(this);

        modID = "wariomod";

        BaseMod.addColor(TheBandit.Enums.BANDIT_WEIRD, chemColor, chemColor, chemColor,
                chemColor, chemColor, chemColor, chemColor,
                ATTACK_S_ART, SKILL_S_ART, POWER_S_ART, CARD_ENERGY_S,
                ATTACK_L_ART, SKILL_L_ART, POWER_L_ART,
                CARD_ENERGY_L, TEXT_ENERGY);

    }

    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }

    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }

    public static String getModID() {
        return modID;
    }

    private static SpireConfig modConfig = null;

    public void receivePostInitialize() {
        BaseMod.addEvent(VampiresBanditEvent.ID, VampiresBanditEvent.class, TheCity.ID);
        theBoard = new BanditBoard();
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        if (AbstractDungeon.player instanceof TheBandit)
            theBoard.init();
    }

    public static void initialize() {
        WarioMod chemistMod = new WarioMod();
    }

    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new TheBandit("the Bandit", TheBandit.Enums.THE_BANDIT),
                CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, TheBandit.Enums.THE_BANDIT);
    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        theBoard.shouldRender = false;
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelicToCustomPool(new TheHat(), TheBandit.Enums.BANDIT_WEIRD);
        BaseMod.addRelicToCustomPool(new DoomHat(), TheBandit.Enums.BANDIT_WEIRD);
        BaseMod.addRelicToCustomPool(new SuperHat(), TheBandit.Enums.BANDIT_WEIRD);
        BaseMod.addRelicToCustomPool(new SmileyHat(), TheBandit.Enums.BANDIT_WEIRD);
        BaseMod.addRelicToCustomPool(new AngryHat(), TheBandit.Enums.BANDIT_WEIRD);
        BaseMod.addRelicToCustomPool(new GlassHat(), TheBandit.Enums.BANDIT_WEIRD);
        BaseMod.addRelicToCustomPool(new PropellerHat(), TheBandit.Enums.BANDIT_WEIRD);
        BaseMod.addRelicToCustomPool(new SunglassHat(), TheBandit.Enums.BANDIT_WEIRD);
    }

    @Override
    public void receiveEditCards() {
        try {
            autoAddCards();
        } catch (URISyntaxException | IllegalAccessException | InstantiationException | NotFoundException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void autoAddCards()
            throws URISyntaxException, IllegalAccessException, InstantiationException, NotFoundException, ClassNotFoundException {
        ClassFinder finder = new ClassFinder();
        URL url = WarioMod.class.getProtectionDomain().getCodeSource().getLocation();
        finder.add(new File(url.toURI()));

        ClassFilter filter =
                new AndClassFilter(
                        new NotClassFilter(new InterfaceOnlyClassFilter()),
                        new NotClassFilter(new AbstractClassFilter()),
                        new ClassModifiersClassFilter(Modifier.PUBLIC),
                        new CardFilter()
                );
        Collection<ClassInfo> foundClasses = new ArrayList<>();
        finder.findClasses(foundClasses, filter);

        for (ClassInfo classInfo : foundClasses) {
            CtClass cls = Loader.getClassPool().get(classInfo.getClassName());
            if (cls.hasAnnotation(CardIgnore.class)) {
                continue;
            }
            boolean isCard = false;
            CtClass superCls = cls;
            while (superCls != null) {
                superCls = superCls.getSuperclass();
                if (superCls == null) {
                    break;
                }
                if (superCls.getName().equals(AbstractCard.class.getName())) {
                    isCard = true;
                    break;
                }
            }
            if (!isCard) {
                continue;
            }
            System.out.println(classInfo.getClassName());
            AbstractCard card = (AbstractCard) Loader.getClassPool().getClassLoader().loadClass(cls.getName()).newInstance();
            BaseMod.addCard(card);
            if (cls.hasAnnotation(CardNoSeen.class)) {
                UnlockTracker.hardUnlockOverride(card.cardID);
            } else {
                UnlockTracker.unlockCard(card.cardID);
            }
        }
    }


    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, getModID() + "Resources/localization/eng/Cardstrings.json");

        BaseMod.loadCustomStringsFile(RelicStrings.class, getModID() + "Resources/localization/eng/Relicstrings.json");

        BaseMod.loadCustomStringsFile(CharacterStrings.class, getModID() + "Resources/localization/eng/Charstrings.json");

        BaseMod.loadCustomStringsFile(UIStrings.class, getModID() + "Resources/localization/eng/Uistrings.json");
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(getModID() + "Resources/localization/eng/Keywordstrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(modID, keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }
}
