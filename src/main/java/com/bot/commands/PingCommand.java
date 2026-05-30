package com.bot.commands;

import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;

public class PingCommand implements ChatCommand {
    @Override
    public void execute(ChannelMessageEvent event, String args) {
        IO.println("Pong! \uD83C\uDFD3");
        event.getTwitchChat().sendMessage(event.getChannel().getName(), "Pong! 🏓");
    }
}
