package net.groupw.tp_so.Files;

import net.groupw.tp_so.Enumerations.LogType;
import net.groupw.tp_so.Interface.ChatCallback;
import net.groupw.tp_so.Interface.LogCallback;
import net.groupw.tp_so.Interface.SimulationCallback;
import java.util.Random;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Satellite {
    private static volatile Satellite INSTANCE = new Satellite();
    private Middleware middleware;
    private static ArrayList<Log> logs ;
    private static ChatCallback chatCallback;
    private static SimulationCallback simulationCallback;

    private static Message response;

    public static Satellite getInstance() {
        return INSTANCE;
    }

    public void setChatCallback(ChatCallback chatCallback) {
        this.chatCallback = chatCallback;
    }

    public void setSimulationCallback(SimulationCallback simulationCallback) {
        this.simulationCallback = simulationCallback;
    }
    public static void receiveMessageFromMiddleware(Message message) {
        LogEntry.getInstance().addLog(new Log(LogType.INFO,Thread.currentThread().getName() + " Satellite received a message from Middleware: " + message, LocalDateTime.now()));
        response = simulateSatelliteProcessing(String.valueOf(message));
    }

    public Satellite () {
logs = new ArrayList<>();
    }

    public Satellite(ArrayList<Log> logs ){
        this.logs = logs;
    }

    public static void setMiddleware(Middleware middleware) {
        middleware = middleware;
    }

    public static Message sendMessageToMiddleware() {
        // Simulate some processing in the satellite
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Create a response message and send it to Middleware

        LogEntry.getInstance().addLog(new Log(LogType.INFO, Thread.currentThread().getName() + response, LocalDateTime.now()));

        // Notify the Chat class when a message is sent
        if (chatCallback != null) {
            chatCallback.onMessageReceived(response.getContent(), "Satellite");
        }
        if (simulationCallback != null) {
            simulationCallback.onSimulatedMessage(response.getContent(), "Satellite");
        }

        return response;
    }

    private static Message simulateSatelliteProcessing(String message) {

        String[] patterns = {
                ".*\\bstatus\\b.*",
                ".*\\brequest\\b.*",
                ".*\\bupdates\\b.*",
                ".*\\bsystemhealth\\b.*",
                ".*\\bweather\\b.*",

        };

        String[] responses = {
                generateRandomStatus(),
                "Received your request. Processing...",
                "No critical updates to report at this time.",
                "System health is optimal.",
                generateRandomTemperature(),
        };



        for (int i = 0; i < patterns.length; i++) {
            if (message.matches(patterns[i])) {
                // If there's a match, return the corresponding response
                return new Message(responses[i]);
            }
        }

        return new Message("Sorry, I didn't understand that. Can you please rephrase or provide more context?");
    }

    private static  synchronized String generateRandomTemperature() {
        Random random = new Random();
        int temperature = random.nextInt(101) - 200;
        return temperature + "° C";
    }

    private static  synchronized String generateRandomStatus() {
        Random random = new Random();
        int statusOp = random.nextInt(1);

        switch (statusOp){
            case 0 :
                return "Satellite Status: Nominal.\n" +
                        "All systems are functioning within normal parameters.";


            case 1:
                return "Satellite Status: Critical.\n" +
                        "- System malfunction detected in subsystem A.\n" +
                        "- Communication failure with ground control.\n" +
                        "- Critical temperature threshold exceeded.\n" +
                        "Immediate attention is required to resolve these issues.";
            default:
                return "Error";

        }
    }
}
