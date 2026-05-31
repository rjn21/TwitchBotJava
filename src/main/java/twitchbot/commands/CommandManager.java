package twitchbot.commands;

import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import twitchbot.commands.impl.HugCommand;
import twitchbot.commands.impl.PingCommand;
import twitchbot.core.BotClient;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private final Map<String, ChatCommand> commands = new HashMap<>();

    public void handleCommand(ChannelMessageEvent event, BotClient bot) {
        String message = event.getMessage();
        String prefix = bot.getBotConfig().getPrefix();

        if (message.startsWith(prefix)) {
            String[] split = message.substring(prefix.length()).split(" ");
            String commandName = split[0].toLowerCase();

            String[] args = new String[split.length - 1];
            System.arraycopy(split, 1, args, 0, args.length);

            ChatCommand command = commands.get(commandName);
            if (command != null) {
                command.execute(event, bot, args);
            }
        }



    }

    public CommandManager() {
        registerCommand(new PingCommand());
        registerCommand(new HugCommand());
    }

    private void registerCommand(ChatCommand command) {
        commands.put(command.getName().toLowerCase(), command);
    }
}
