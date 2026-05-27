package com.bot.commands;

import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;

public class HugCommand implements ChatCommand {
    @Override
    public void execute(ChannelMessageEvent event, String args) {
        String sender = event.getUser().getName();
        String receiver = args.trim();

        if (receiver.isEmpty()) {
            event.getTwitchChat().sendMessage(
                    event.getChannel().getName(),
                    sender + "hugs themselves! :)"
            );
            IO.println(sender + "hugs themselves! :)");
        } else {
            if (receiver.startsWith("@")) {
                receiver = receiver.substring(1).trim();
            }
            event.getTwitchChat().sendMessage(
                    event.getChannel().getName(),
                    sender + " hugs " + receiver + " :)"
            );
            IO.println(sender + " hugs " + receiver + " :)");
        }
    }
}
