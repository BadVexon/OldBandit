package theWario.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theWario.TheBandit;
import theWario.WarioMod;
import theWario.actions.MoveAction;
import theWario.squares.AbstractSquare;
import theWario.squares.CardSquare;
import theWario.squares.EmptySquare;
import theWario.squares.GoSquare;

import java.util.ArrayList;

import static theWario.WarioMod.*;

public abstract class AbstractWarioCard extends CustomCard {

    protected final CardStrings cardStrings;
    protected final String NAME;
    protected final String DESCRIPTION;
    protected final String UPGRADE_DESCRIPTION;
    protected final String[] EXTENDED_DESCRIPTION;

    public boolean showTileValue = false;

    public ArrayList<Integer> showTileAmounts() {
        return new ArrayList<>();
    }

    public AbstractWarioCard(final String id, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
        super(id, "ERROR", getCorrectPlaceholderImage(id),
                cost, "ERROR", type, TheBandit.Enums.BANDIT_WEIRD, rarity, target);
        cardStrings = CardCrawlGame.languagePack.getCardStrings(id);
        name = NAME = cardStrings.NAME;
        originalName = NAME;
        rawDescription = DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
        initializeTitle();
        initializeDescription();
    }

    public AbstractWarioCard(final String id, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color) {
        super(id, "ERROR", getCorrectPlaceholderImage(id),
                cost, "ERROR", type, color, rarity, target);
        cardStrings = CardCrawlGame.languagePack.getCardStrings(id);
        name = NAME = cardStrings.NAME;
        originalName = NAME;
        rawDescription = DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
        initializeTitle();
        initializeDescription();
    }

    private static String getCorrectPlaceholderImage(String id) {
        return makeCardPath(id.replaceAll((WarioMod.getModID() + ":"), "")) + ".png";
    }

    public static String makeID(String blah) {
        return getModID() + ":" + blah;
    }

    protected void atb(AbstractGameAction action) {
        addToBot(action);
    }

    protected void att(AbstractGameAction action) {
        addToTop(action);
    }

    protected DamageInfo makeInfo() {
        return makeInfo(damageTypeForTurn);
    }

    private DamageInfo makeInfo(DamageInfo.DamageType type) {
        return new DamageInfo(AbstractDungeon.player, damage, type);
    }

    public void dmg(AbstractMonster m, AbstractGameAction.AttackEffect fx) {
        atb(new DamageAction(m, makeInfo(), fx));
    }

    public void allDmg(AbstractGameAction.AttackEffect fx) {
        atb(new DamageAllEnemiesAction(AbstractDungeon.player, multiDamage, damageTypeForTurn, fx));
    }

    public void blck() {
        atb(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, block));
    }

    private void makeInHand(AbstractCard c, int i) {
        atb(new MakeTempCardInHandAction(c, i));
    }

    void move(int amount) {
        atb(new MoveAction(amount));
    }

    public void makeInHand(AbstractCard c) {
        makeInHand(c, 1);
    }

    void shuffleIn(AbstractCard c, int i) {
        atb(new MakeTempCardInDrawPileAction(c, i, false, true));
    }

    public void shuffleIn(AbstractCard c) {
        shuffleIn(c, 1);
    }

    public ArrayList<AbstractMonster> monsterList() {
        return AbstractDungeon.getMonsters().monsters;
    }

    public void applyToEnemy(AbstractMonster m, AbstractPower po) {
        atb(new ApplyPowerAction(m, AbstractDungeon.player, po, po.amount));
    }

    public void applyToSelf(AbstractPower po) {
        atb(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, po, po.amount));
    }

    public void transformEmpty(Class swapSquare) {
        transformEmpty(swapSquare, 1);
    }

    public void inputCard(AbstractCard c) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                ArrayList<AbstractSquare> eligible = new ArrayList<>();
                for (AbstractSquare s : theBoard.squareList) {
                    if (s instanceof EmptySquare) {
                        eligible.add(s);
                    }
                }
                if (!eligible.isEmpty()) {
                    AbstractSquare s = eligible.get(AbstractDungeon.cardRandomRng.random(eligible.size() - 1));
                    theBoard.transform(s, new CardSquare(s.x, s.y, c));
                }
            }
        });
    }

    public void transformEmpty(Class swapSquare, int aruh) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                ArrayList<AbstractSquare> done = new ArrayList<>();
                for (int i = 0; i < aruh; i++) {
                    ArrayList<AbstractSquare> eligible = new ArrayList<>();
                    for (AbstractSquare s : theBoard.squareList) {
                        if (s instanceof EmptySquare && !done.contains(s) && !(theBoard.swapSquare(swapSquare, 0, 0).getClass().getSimpleName().equals(s.getClass().getSimpleName()))) {
                            eligible.add(s);
                        }
                    }
                    if (!eligible.isEmpty()) {
                        AbstractSquare s = eligible.get(AbstractDungeon.cardRandomRng.random(eligible.size() - 1));
                        done.add(s);
                        theBoard.transform(s, theBoard.swapSquare(swapSquare, s.x, s.y));
                    }
                }
            }
        });
    }

    public void transformAny(Class swapSquare) {
        transformAny(swapSquare, 1);
    }

    public void transformAny(Class swapSquare, int aruh) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                ArrayList<AbstractSquare> done = new ArrayList<>();
                for (int i = 0; i < aruh; i++) {
                    ArrayList<AbstractSquare> eligible = new ArrayList<>(theBoard.squareList);
                    eligible.removeIf(s -> s instanceof GoSquare || done.contains(s) || (theBoard.swapSquare(swapSquare, 0, 0).getClass().getSimpleName().equals(s.getClass().getSimpleName())));
                    if (!eligible.isEmpty()) {
                        AbstractSquare s = eligible.get(AbstractDungeon.cardRandomRng.random(eligible.size() - 1));
                        done.add(s);
                        theBoard.transform(s, theBoard.swapSquare(swapSquare, s.x, s.y));
                    }
                }
            }
        });
    }

    public void genBoom(int amount) {
        atb(new MakeTempCardInDiscardAction(new Kaboom(), amount));
    }

    public void genBoom() {
        genBoom(1);
    }

    WeakPower autoWeak(AbstractMonster m, int i) {
        return new WeakPower(m, i, false);
    }

    VulnerablePower autoVuln(AbstractMonster m, int i) {
        return new VulnerablePower(m, i, false);
    }
}