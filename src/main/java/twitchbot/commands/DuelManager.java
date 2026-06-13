package twitchbot.commands;

import twitchbot.core.BotClient;
import twitchbot.core.requests.PendingRequest;
import twitchbot.core.requests.RequestManager;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DuelManager {
    private final RequestManager<Void> requestManager;
    private final Random random = new Random();

    public DuelManager(ScheduledExecutorService scheduler) {
        this.requestManager = new RequestManager<>(scheduler);
    }

    public void createChallenge(String channel, String challenger, String target, BotClient bot) {
        int minutes = 3;

        Runnable timeoutAction = () -> bot.sendMessage(
                channel,
                "Das Duell zwischen @" + challenger + " und @" + target + " ist abgelaufen."
        );

        PendingRequest<Void> request = new PendingRequest<>(channel, challenger, target, null);

        boolean success = requestManager.createRequest(request, minutes, timeoutAction);

        if (!success) {
            bot.sendMessage(channel, "@" + challenger + ", dieser Nutzer hat bereits eine offene Herausforderung!");
            return;
        }

        bot.sendMessage(
                channel,
                "⚔️ @" + target + "! Du wurdest von @" + challenger + " herausgefordert! Schreibe '!duell accept' zum Annehmen (Zeit: " + minutes + " Min)."
        );
    }

    public void acceptChallenge(String channel, String target, BotClient bot) {
        PendingRequest<Void> challenge = requestManager.acceptRequest(channel, target);

        if (challenge == null) {
            bot.sendMessage(channel, "@" + target + ", du hast aktuell keine offenen Duell-Anfragen.");
            return;
        }

        bot.getScheduler().schedule(() -> {
            boolean challengerWins = random.nextBoolean();

            String winner = challengerWins ? challenge.getSender() : challenge.getTarget();
            String loser = challengerWins ? challenge.getTarget() : challenge.getSender();

            bot.sendMessage(
                    channel,
                    "🏆 ERGEBNIS: Nach einem harten und epischen Kampf triumphiert @" + winner + " über @" + loser + "! 🎉"
            );
        }, 10, TimeUnit.SECONDS);
    }
}
