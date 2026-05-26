package com.bot.commands;

import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;

public interface ChatCommand {
    /**
     * Executes the command.
     *
     * @param event The original Twitch event (allows replying, reading user details, etc.)
     * @param args  The raw argument string following the command name.
     */
    void execute(ChannelMessageEvent event, String args);
}
