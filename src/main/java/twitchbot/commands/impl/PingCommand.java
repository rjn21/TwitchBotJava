package twitchbot.commands.impl;

import twitchbot.commands.ChatCommand;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import twitchbot.core.BotClient;

public class PingCommand implements ChatCommand {
    @Override
    public String getName() { return "ping"; }

    @Override
    public void execute(ChannelMessageEvent event, BotClient bot, String[] args) {
        bot.sendMessage(event.getChannel().getName(), "@" + event.getUser().getName() + " Pong! 🏓");
    }


}
