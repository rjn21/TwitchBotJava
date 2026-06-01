package twitchbot.core;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import twitchbot.commands.DuelManager;
import twitchbot.config.BotConfig;
import twitchbot.handlers.ChatEventHandler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@SuppressWarnings("ALL")
public class BotClient {
    private TwitchClient twitchClient;
    private ScheduledExecutorService scheduler;
    private BotConfig botConfig;
    private DuelManager duelManager;

    public void start() {
        this.botConfig = new BotConfig();
        OAuth2Credential credential = new OAuth2Credential("twitch", botConfig.getOauthToken());
        this.twitchClient = TwitchClientBuilder.builder()
                .withEnableChat(true)
                .withChatAccount(credential)
                .build();

        this.twitchClient.getChat().joinChannel(botConfig.getChannelName());
        this.scheduler = Executors.newScheduledThreadPool(4);
        this.duelManager = new DuelManager(scheduler);
        ChatEventHandler eventHandler = new ChatEventHandler(this);
        this.twitchClient.getEventManager().getEventHandler(SimpleEventHandler.class).registerListener(eventHandler);

        System.out.println("Bot [" + botConfig.getBotName() + "] wurde erfolgreich gestartet.");
    }

    public void sendMessage(String channel, String message) {
        if (this.twitchClient != null && this.twitchClient.getChat() != null) {
            this.twitchClient.getChat().sendMessage(channel, message);
        }
    }

    public BotConfig getBotConfig() {
        return botConfig;
    }

    public TwitchClient getTwitchClient() {
        return twitchClient;
    }

    public ScheduledExecutorService getScheduler() {
        return this.scheduler;
    }

    public DuelManager getDuelManager() {
        return duelManager;
    }
}
