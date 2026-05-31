package twitchbot.config;

import io.github.cdimascio.dotenv.Dotenv;

public class BotConfig {
    private String oauthToken;
    private String channelName;
    private String botName;
    @SuppressWarnings("FieldCanBeLocal")
    private final String prefix = "!";

    public BotConfig() {
        loadEnv();
    }

    private void loadEnv() {
        try {
            Dotenv dotenv = Dotenv.load();
            oauthToken = dotenv.get("ACCESS_TOKEN");
            channelName = dotenv.get("CHANNEL_NAME");
            botName = dotenv.get("BOT_NAME");
        } catch (Exception ex) {
            System.err.println("Fehler bei lesen der .env: " + ex);
        }
    }

    public String getOauthToken() {
        return oauthToken;
    }

    public String getChannelName() {
        return channelName;
    }

    public String getBotName() {
        return botName;
    }

    public String getPrefix() {
        return prefix;
    }


}
