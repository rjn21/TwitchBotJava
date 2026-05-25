package com.bot;

import com.bot.core.TwitchBotService;

import java.util.Scanner;

public class Main {
    static void main() {
        String token = null;
        String channelToRead = "le_gennex";
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
