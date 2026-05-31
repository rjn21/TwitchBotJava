package twitchbot.commands.impl;

import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import twitchbot.commands.ChatCommand;
import twitchbot.core.BotClient;

public class HugCommand implements ChatCommand {
    @Override
    public String getName() {
        return "hug";
    }

    @Override
    public void execute(ChannelMessageEvent event, BotClient bot, String[] args) {
        String sender = event.getUser().getName();
        String receiver = args.length > 0 ? String.join(" ", args).trim() : "";

        if (receiver.isEmpty()) {
            String message = sender + " hugs themselves! :)";
            bot.sendMessage(event.getChannel().getName(), message);
            System.out.println(message);
        } else {
            if (receiver.startsWith("@")) {
                receiver = receiver.substring(1).trim();
            }
            String message = sender + " hugs " + receiver + " :)";
            bot.sendMessage(event.getChannel().getName(), message);
            System.out.println(message);
        }
    }
}
