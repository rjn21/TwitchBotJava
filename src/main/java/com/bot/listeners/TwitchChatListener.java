//TwitchChatListener is your application's entry point/adapter for external chat events (ChannelMessageEvent). Its sole
// responsibility is to receive messages, inspect them, and delegate action handling downstream.

package com.bot.listeners;

import com.bot.commands.CommandRegistry;
import com.github.philippheuer.events4j.simple.domain.EventSubscriber;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;

public class TwitchChatListener {
    private static final String COMMAND_PREFIX = "!";
    private final CommandRegistry commandRegistry = new CommandRegistry();

    @SuppressWarnings("unused")
    @EventSubscriber
    public void onChannelMessage(ChannelMessageEvent event) {
        String user = event.getUser().getName();
        String message = event.getMessage();
        String channel = event.getChannel().getName();

        System.out.printf("[%s] %s: %s%n", channel, user, message);

        if (message.startsWith("!")) {
            handleCommand(event, message);
        }
    }

    private void handleCommand(ChannelMessageEvent event, String message) {
        String cleanMessage = message.substring(COMMAND_PREFIX.length()).trim();

        String[] parts = cleanMessage.split("\\s+", 2);
        String commandName = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        commandRegistry.handle(commandName, event, arguments);
    }
}
