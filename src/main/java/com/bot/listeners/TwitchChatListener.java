package com.bot.listeners;

import com.github.philippheuer.events4j.simple.domain.EventSubscriber;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;

public class TwitchChatListener {
    @EventSubscriber
    public void onChannelMessage(ChannelMessageEvent event) {
        String user = event.getUser().getName();
        String message = event.getMessage();
        String channel = event.getChannel().getName();

        System.out.printf("[%s] %s: %s%n", channel, user, message);
    }
}
