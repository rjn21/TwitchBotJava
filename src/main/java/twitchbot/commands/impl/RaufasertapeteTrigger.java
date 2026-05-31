package twitchbot.commands.impl;

import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import twitchbot.commands.ChatTrigger;
import twitchbot.core.BotClient;

public class RaufasertapeteTrigger implements ChatTrigger {


    @Override
    public String getKeyword() {
        return "raufasertapete";
    }

    @Override
    public void execute(ChannelMessageEvent event, BotClient bot) {
        String user = event.getUser().getName();
        bot.sendMessage(
                event.getChannel().getName(),
                "@" + user + " Bernd das Brot wäre stolz auf dich! \uD83C\uDF5E"
        );
    }
}
