package theWario.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWario.squares.AbstractSquare;
import theWario.squares.EmptySquare;

import java.util.ArrayList;

import static theWario.WarioMod.theBoard;

public class Purify extends AbstractWarioCard {

    public final static String ID = makeID("Purify");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    private static final int BLOCK = 7;
    private static final int MAGIC = 1;

    public Purify() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
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
                    theBoard.transform(s, new EmptySquare(s.x, s.y));
                }
            }
        });

    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            exhaust = false;
            ExhaustiveField.ExhaustiveFields.baseExhaustive.set(this, 2);
            ExhaustiveField.ExhaustiveFields.exhaustive.set(this, 2);
            ExhaustiveField.ExhaustiveFields.isExhaustiveUpgraded.set(this, true);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}