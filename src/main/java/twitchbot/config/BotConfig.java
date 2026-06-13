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
        Dotenv dotenv;
        try {
            dotenv = Dotenv.load();
        } catch (Exception ex) {
            throw new IllegalStateException("Fehler beim Lesen der .env-Datei: " + ex.getMessage(), ex);
        }

        oauthToken = dotenv.get("ACCESS_TOKEN");
        channelName = dotenv.get("CHANNEL_NAME");
        botName = dotenv.get("BOT_NAME");

        requireValue("ACCESS_TOKEN", oauthToken);
        requireValue("CHANNEL_NAME", channelName);
        requireValue("BOT_NAME", botName);
    }

    private void requireValue(String key, String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalStateException(
                    "Pflichtwert '" + key + "' fehlt oder ist leer. Bitte in der .env-Datei setzen."
            );
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
