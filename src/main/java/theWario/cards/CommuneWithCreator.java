package theWario.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.evacipated.cardcrawl.mod.stslib.relics.SuperRareRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import com.megacrit.cardcrawl.vfx.TextCenteredEffect;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.Channel;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.discordjson.json.gateway.MessageCreate;
import theWario.WarioMod;
import theWario.actions.TypingAction;
import theWario.util.LeerVFX;

import java.util.ArrayList;

public class CommuneWithCreator extends AbstractWarioCard {

    public final static String ID = makeID("CommuneWithCreator");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    public CommuneWithCreator() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        FleetingField.fleeting.set(this, true);
        this.tags.add(CardTags.HEALING);
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        atb(new TypingAction((string) -> {
            for (Guild g : WarioMod.client.getGuilds().toIterable()) {
                if (g.getName().toString().toLowerCase().contains("vex")) {
                    for (Channel c : g.getChannels().toIterable()) {
                        if (c instanceof MessageChannel) {
                            ((MessageChannel) c).createMessage(CardCrawlGame.playerName + " Bandit Appreciation (Can Reply): " + string).block();
                        }
                    }
                }
            }
            WarioMod.client.on(MessageCreateEvent.class).subscribe((event) -> {
                final Message message = event.getMessage();
                String content = message.getContent();
                final MessageChannel channel = message.getChannel().block();
                if (content.startsWith("!reply " + CardCrawlGame.playerName)) {
                    AbstractDungeon.effectList.add(new TextCenteredEffect(content.replaceAll("!reply " + CardCrawlGame.playerName, "")));
                }
            });
        }));
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2F, Settings.HEIGHT / 2F, WarioMod.returnTrueRandomScreenlessRelic());
            }
        });
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    public void upgrade() {
    }
}