package twitchbot.commands;

import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import twitchbot.core.BotClient;

public interface ChatTrigger {
    String getKeyword();
    void execute(ChannelMessageEvent event, BotClient bot);
}
