package com.bot.core;

import com.bot.listeners.TwitchChatListener;
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;


public class TwitchBotService {
    private TwitchClient twitchClient;

    public void start(String oAuthToken, String channel) {
        IO.println("Verbindung zu Twitch wird hergestellt");
//        OAuth2Credential credential = new OAuth2Credential("twitch", oAuthToken);
        com.github.twitch4j.TwitchClientBuilder builder = TwitchClientBuilder.builder()
                .withEnableChat(true);

        if (oAuthToken != null && !oAuthToken.isEmpty()) {
            OAuth2Credential credential = new OAuth2Credential("twitch", oAuthToken);
            builder.withChatAccount(credential);
        } else {
            IO.println("Kein Token übergeben. Verbinde anonym...");
        }

        this.twitchClient = builder.build();

        TwitchChatListener chatListener = new TwitchChatListener();
        this.twitchClient.getEventManager()
                .getEventHandler(SimpleEventHandler.class)
                .registerListener(chatListener);

        this.twitchClient.getChat().joinChannel(channel);
        System.out.println("Erfolgreich dem Kanal " + channel + " beigetreten! Lese Chat...");
    }

    public void stop() {
        if (this.twitchClient != null) {
            this.twitchClient.close();
            this.twitchClient = null;
            IO.println("Verbindung zu Twitch sauber geschlossen");
        }
    }
}
