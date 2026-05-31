package twitchbot.commands;

import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import twitchbot.commands.impl.RaufasertapeteTrigger;
import twitchbot.core.BotClient;

import java.util.ArrayList;
import java.util.List;

public class KeywordManager {
    private final List<ChatTrigger> triggers = new ArrayList<>();

    public KeywordManager() {
        this.triggers.add(new RaufasertapeteTrigger());
    }

    public void checkMessageForKeywords(ChannelMessageEvent event, BotClient bot) {
        String lowerCaseMessage = event.getMessage().toLowerCase();

        for (ChatTrigger trigger : triggers) {
            if (lowerCaseMessage.contains(trigger.getKeyword())) {
                trigger.execute(event, bot);
                break;
            }
        }
    }
}
