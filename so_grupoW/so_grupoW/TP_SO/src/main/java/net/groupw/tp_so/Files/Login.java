package net.groupw.tp_so.Files;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.groupw.tp_so.Enumerations.LogType;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class Login {

    private static String FILE_PATH = "src/main/java/net/groupw/tp_so/DB/users.json";

    private static Station station;
    private static ObjectMapper objectMapper = new ObjectMapper();

    public Login(Station station) {
        this.station = station;
    }

    public boolean login (String username, String password) {
        try {
            JsonNode userData = loadUserData(FILE_PATH);
            // Validate user credentials
            if (validateUser(username, password, userData)) {
                LogEntry.getInstance().addLog(new Log(LogType.INFO,"Login realizado com sucesso", LocalDateTime.now()));
                System.out.println("Login successful!");
                return true;
            } else {
                System.out.println("Invalid username or password");
                LogEntry.getInstance().addLog(new Log(LogType.WARNING,"Invalid username or password", LocalDateTime.now()));
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    private static JsonNode loadUserData(String filePath) throws IOException {
        //Read user Data
        return objectMapper.readTree(new FileReader(filePath));
    }

    private static boolean validateUser(String username, String password, JsonNode userData) {

        for(JsonNode user : userData) {
            String storedUsername = user.get("username").asText();
            String storedPassword = user.get("password").asText();
            String storedRole = user.get("role").asText();
            int storedStationRef = user.get("station_ref").asInt();

            // Check if username and password match
            if (username.equals(storedUsername) && password.equals(storedPassword)) {
                User loggedUser = new User();

                updateLoginCountsInUserData(user);

                loggedUser.LoggedInUser(storedUsername, storedPassword, storedRole, storedStationRef, station);
                return true; // Valid credentials
            }
        }
        return false; // Invalid credentials
    }

    private static void updateLoginCountsInUserData(JsonNode user) {
        ObjectNode userNode = (ObjectNode) user;
        LocalDate currentDate = LocalDate.now();
        String currentDateStr = currentDate.toString();

        // Extract or initialize login counts object
        ObjectNode loginCounts = Optional.ofNullable(userNode.get("login_counts"))
                .filter(JsonNode::isObject)
                .map(node -> (ObjectNode) node)
                .orElseGet(() -> {
                    ObjectNode newLoginCounts = objectMapper.createObjectNode();
                    userNode.set("login_counts", newLoginCounts);
                    return newLoginCounts;
                });

        // Get the current login count for the date
        JsonNode currentCountNode = loginCounts.get(currentDateStr);
        int currentCount = (currentCountNode != null && currentCountNode.isNumber()) ? currentCountNode.asInt() : 0;

        // Increment login count for the current date
        loginCounts.put(currentDateStr, currentCount + 1);

        writeUpdatedUserDataToFile(userNode);
    }

    private static void writeUpdatedUserDataToFile(ObjectNode userNode) {
        try {
            // Load the existing user data
            JsonNode userData = loadUserData(FILE_PATH);

            // Find the user in the data and update its information
            if (userData.isArray()) {
                for (JsonNode user : userData) {
                    String username = user.get("username").asText();
                    if (username.equals(userNode.get("username").asText())) {
                        ((ObjectNode) user).set("login_counts", userNode.get("login_counts"));
                        break;
                    }
                }
            }

            // Write the updated user data back to the file
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new FileWriter(FILE_PATH), userData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
