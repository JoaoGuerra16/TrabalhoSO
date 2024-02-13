package net.groupw.tp_so.Files;

public class User {
    private static String username;
    private static String password;
    private static String role;
    private static int station_ref;
    private static Station station;



    public static void LoggedInUser(String user, String pass, String userRole, int ref, Station estacao) {
        username = user;
        password = pass;
        role = userRole;
        station_ref = ref;
        station = estacao;
    }

    public void getUserProfile() {
        System.out.println("================================");
        System.out.println("Username: " + username);
        System.out.println("Role: " + role);
        System.out.println("================================");
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static String getRole() {
        return role;
    }

    public static void setUsername(String username) {
        User.username = username;
    }

    public static void setPassword(String password) {
        User.password = password;
    }

    public static void setRole(String role) {
        User.role = role;
    }

    public static void setStation_ref(int station_ref) {
        User.station_ref = station_ref;
    }

    public static void setStation(Station station) {
        User.station = station;
    }

    public static void userSendMessage(String input) {

        Message message = new Message(username + " sent: "+ input );

        station.stationSendMessage(message);
    }


}
