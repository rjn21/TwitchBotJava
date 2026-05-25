package com.bot;

import com.bot.core.TwitchBotService;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.Scanner;

public class Main {
    static void main() {
//        le Test
        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("TWITCH_OAUTH_TOKEN");
        String channelToRead = dotenv.get("TWITCH_CHANNEL");

        TwitchBotService botService = new TwitchBotService();
        botService.start(token, channelToRead);

        Runtime.getRuntime().addShutdownHook(new Thread(botService::stop));

        System.out.println("==================================================");
        System.out.println("Bot läuft im Hintergrund. Drücke ENTER hier in der Konsole, um zu beenden.");
        System.out.println("==================================================");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); // Hier bleibt das Programm "stehen" und wartet auf dich.

        // Wenn du ENTER drückst, springt er hierhin und beendet alles sauber
        System.out.println("Beende das Programm manuell...");
        botService.stop();
    }
}
