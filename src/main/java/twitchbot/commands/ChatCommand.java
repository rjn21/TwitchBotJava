package twitchbot.commands;

import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import twitchbot.core.BotClient;

public interface ChatCommand {
    String getName();
    void execute(ChannelMessageEvent event, BotClient bot, String[] args);
}
