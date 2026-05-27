package com.bot.commands;

import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    private final Map<String, ChatCommand> commands = new HashMap<>();

    public CommandRegistry() {
        registerCommand("ping", new PingCommand());
        registerCommand("hug", new HugCommand());
    }

    public void registerCommand(String commandName, ChatCommand command) {
        commands.put(commandName.toLowerCase(), command);
    }

    public void handle(String commandName, ChannelMessageEvent event, String args) {
        ChatCommand command = commands.get(commandName.toLowerCase());
        if (command != null) {
            IO.println("Command detected: " + commandName);
            command.execute(event, args);
        } else {
            IO.println("Unknown command: " + commandName);
        }
    }
}
