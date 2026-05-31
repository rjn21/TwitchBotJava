package twitchbot.handlers;
import com.github.philippheuer.events4j.simple.domain.EventSubscriber;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import twitchbot.commands.CommandManager;
import twitchbot.commands.KeywordManager;
import twitchbot.core.BotClient;

public class ChatEventHandler {
    private final BotClient bot;
    private final CommandManager commandManager;
    private final KeywordManager keywordManager;
    public ChatEventHandler(BotClient bot) {
        this.bot = bot;
        this.commandManager = new CommandManager();
        this.keywordManager = new KeywordManager();
    }

    @EventSubscriber
    public void onChannelMessage(ChannelMessageEvent event) {
        if (event.getUser().getName().equalsIgnoreCase(bot.getBotConfig().getBotName())) return;
        String message = event.getMessage();
        String prefix = bot.getBotConfig().getPrefix();

        if (message.startsWith(prefix)) {
            commandManager.handleCommand(event, bot);
        } else {
            keywordManager.checkMessageForKeywords(event, bot);
        }
    }
}
