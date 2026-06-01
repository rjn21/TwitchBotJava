package twitchbot.commands.impl;

import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import twitchbot.commands.ChatCommand;
import twitchbot.core.BotClient;

public class DuelCommand implements ChatCommand {
    @Override
    public String getName() {
        return "duell";
    }

    @Override
    public void execute(ChannelMessageEvent event, BotClient bot, String[] args) {
        String channel = event.getChannel().getName();
        String sender = event.getUser().getName();

        if (args.length == 0) {
            bot.sendMessage(channel, "@" + sender + ", Nutzung: !duell <Name> ODER !duell accept");
            return;
        }

        if (args[0].equalsIgnoreCase("accept")) {
            bot.getDuelManager().acceptChallenge(channel, sender, bot);
            return;
        }

        String target = args[0].startsWith("@") ? args[0].substring(1) : args[0];
        if (target.equalsIgnoreCase(sender)) {
            bot.sendMessage(channel, "@" + sender + ", du kannst dich nicht selbst herausfordern!");
            return;
        }

        bot.getDuelManager().createChallenge(channel, sender, target, bot);
    }
}
