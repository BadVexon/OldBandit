package theWario.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theWario.squares.AbstractSquare;
import theWario.squares.StrengthSquare;

import java.util.ArrayList;

import static theWario.WarioMod.theBoard;

public class GodlyGift extends AbstractWarioCard implements StartupCard {

    public final static String ID = makeID("GodlyGift");

    //stupid intellij stuff SKILL, NONE, RARE

    public GodlyGift() {
        super(ID, -2, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public boolean atBattleStartPreDraw() {
        if (upgraded) {
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    ArrayList<AbstractSquare> eligible = new ArrayList<>();
                    for (AbstractSquare s : theBoard.squareList) {
                        if (s.goodness == AbstractSquare.GOODNESS.BAD) {
                            eligible.add(s);
                        }
                    }
                    if (!eligible.isEmpty()) {
                        AbstractSquare s = eligible.get(AbstractDungeon.cardRandomRng.random(eligible.size() - 1));
                        theBoard.transform(s, theBoard.swapSquare(StrengthSquare.class, s.x, s.y));
                    }
                }
            });
        } else {
            transformEmpty(StrengthSquare.class);
        }
        return true;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = "I can't play this card, pal.";
        return false;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}