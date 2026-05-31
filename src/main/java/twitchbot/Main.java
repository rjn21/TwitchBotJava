package twitchbot;

import twitchbot.core.BotClient;

public class Main {
    static void main() {
        BotClient bot = new BotClient();
        bot.start();
    }
}
