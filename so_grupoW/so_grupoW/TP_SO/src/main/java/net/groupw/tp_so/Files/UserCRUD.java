package net.groupw.tp_so.Files;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import net.groupw.tp_so.Enumerations.LogType;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserCRUD {

    private static ArrayList<Log> logs;

    public UserCRUD(ArrayList<Log> logs) {
        this.logs = logs;
    }

    private static final String FILE_PATH = "src/main/java/net/groupw/tp_so/DB/users.json";
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void createUser(String username, String password, String role, int stationRef) {
        try {
            JsonNode userData = loadUserData(FILE_PATH);

            if (userExists(username, userData)) {
                System.out.println("Username already exists. Choose a different username");


                LogEntry.getInstance().addLog(new Log(LogType.WARNING, "Username already exists. Choose a different username", LocalDateTime.now()));
                return;
            }

            ObjectNode newUser = objectMapper.createObjectNode();
            newUser.put("username", username);
            newUser.put("password", password);
            newUser.put("role", role);
            newUser.put("station_ref", stationRef);

            ((ArrayNode) userData).add(newUser);

            saveUserData(FILE_PATH, userData);

            System.out.println("User created successfully");
            LogEntry.getInstance().addLog(new Log(LogType.INFO, "User " + username + " created Successfully.", LocalDateTime.now()));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateUser(String username, String newUsername, String newPassword, String newRole, int newStationRef) {
        try {
            JsonNode userData = loadUserData(FILE_PATH);

            for (JsonNode user : userData) {
                if (username.equals(user.get("username").asText())) {
                    ((ObjectNode) user).put("username", newUsername);
                    ((ObjectNode) user).put("password", newPassword);
                    ((ObjectNode) user).put("role", newRole);
                    ((ObjectNode) user).put("station_ref", newStationRef);

                    saveUserData(FILE_PATH, userData);

                    System.out.println("User updated Successfully");
                    LogEntry.getInstance().addLog(new Log(LogType.INFO, "User " + username + " updated Successfully." +
                            " Name updated: " + newUsername + " Password updated: " + newPassword + " Role updated: " + newRole + " Station ref updated: " + newStationRef, LocalDateTime.now()));


                    return;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeUser(String username) {
        try {
            JsonNode userData = loadUserData(FILE_PATH);

            for (int i = 0; i < userData.size(); i++) {
                if (username.equals(userData.get(i).get("username").asText())) {
                    ((ArrayNode) userData).remove(i);

                    saveUserData(FILE_PATH, userData);

                    System.out.println("User Removed successfully");
                    LogEntry.getInstance().addLog(new Log(LogType.INFO, "User " + username + " deleted Successfully.", LocalDateTime.now()));

                    return;
                }
            }
            System.out.println("User not found");
            //Adicionar LOGS

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean userExists(String username, JsonNode userData) {
        for (JsonNode user : userData) {
            if (username.equals(user.get("username").asText())) {
                return true;
            }
        }
        return false;
    }

    private static void saveUserData(String filePath, JsonNode userData) throws IOException {
        // Write user data to JSON file using Jackson
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, userData);
        }
    }

    private static JsonNode loadUserData(String filePath) throws IOException {
        // Read user data from JSON file using Jackson
        return objectMapper.readTree(new FileReader(filePath));
    }

    public static String[][] getAllUsersAsArray() {
        try {
            JsonNode userData = loadUserData(FILE_PATH);

            // Create a List to store user information arrays
            ArrayList<String[]> userList = new ArrayList<>();

            for (JsonNode user : userData) {
                String username = user.get("username").asText();
                String password = user.get("password").asText();
                String role = user.get("role").asText();
                int stationRef = user.get("station_ref").asInt();

                // Create an array for each user
                String[] userInfo = {username, password, role, String.valueOf(stationRef)};

                // Add the user array to the list
                userList.add(userInfo);
            }

            // Convert the List to a two-dimensional array
            return userList.toArray(new String[0][]);

        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
            return new String[0][]; // Return an empty array in case of an exception
        }
    }



}
