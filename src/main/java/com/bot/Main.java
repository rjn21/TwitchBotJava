package com.bot;

import com.bot.core.TwitchBotService;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.Scanner;

public class Main {
    static void main() {
        Dotenv dotenv = Dotenv.load();
        String token = "oauth:" + dotenv.get("ACCESS_TOKEN");

        String channelToRead = dotenv.get("TWITCH_CHANNEL");

        TwitchBotService botService = new TwitchBotService();
        botService.start(token, channelToRead);

        Runtime.getRuntime().addShutdownHook(new Thread(botService::stop));

        System.out.println("==================================================");
        System.out.println("Bot läuft im Hintergrund. Drücke ENTER hier in der Konsole, um zu beenden.");
        System.out.println("==================================================");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.trim().equalsIgnoreCase("exit")) {
                break;
            }
        }

        // Wenn du ENTER drückst, springt er hierhin und beendet alles sauber
        System.out.println("Beende das Programm manuell...");
        botService.stop();
    }
}
