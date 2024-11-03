package locationtrackersystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LocationLogs {
    static Config conf = new Config();
    static Scanner scan = new Scanner(System.in);

    public void locationLogConfig() {
        int option;
        do {
            System.out.println("\n--- Location Log Management ---");
            System.out.println("1. Add Location Log");
            System.out.println("2. View Location Logs");
            System.out.println("3. Edit Location Log");
            System.out.println("4. Delete Location Log");
            System.out.println("5. Exit");

            try {
                System.out.print("Choose an option: ");
                option = scan.nextInt();
                scan.nextLine();

                switch (option) {
                    case 1: addLocationLog(); break;
                    case 2: viewLocationLogs(); break;
                    case 3: editLocationLog(); break;
                    case 4: deleteLocationLog(); break;
                    case 5: System.out.println("Returning to main menu."); break;
                    default: System.out.println("Invalid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scan.nextLine();
                option = -1;
            }
        } while (option != 5);
    }

    private void addLocationLog() {
        System.out.println("Enter Location Log Details:");

        int userId;
        do {
            System.out.print("User ID: ");
            userId = scan.nextInt();
            if (!conf.doesIDExist("user", userId)) {
                System.out.println("User ID doesn't exist.");
            }
        } while (!conf.doesIDExist("user", userId));

        int placeId;
        do {
            System.out.print("Place ID: ");
            placeId = scan.nextInt();
            if (!conf.doesIDExist("place", placeId)) {
                System.out.println("Place ID doesn't exist.");
            }
        } while (!conf.doesIDExist("place", placeId));

        scan.nextLine();
        System.out.print("Timestamp (MM-DD-YYY HH:MM): ");
        String timestamp = scan.nextLine();

        String sql = "INSERT INTO location_log (user_id, place_id, timestamp) VALUES (?, ?, ?)";
        conf.addRecord(sql, userId, placeId, timestamp);
    }

    private void editLocationLog() {
        System.out.print("Enter Location Log ID to edit: ");
        int logId = scan.nextInt();
        if (!conf.doesIDExist("location_log", logId)) {
            System.out.println("Location Log ID doesn't exist.");
            return;
        }

        System.out.println("Editing Location Log with ID: " + logId);
        
        int userId;
        do {
            System.out.print("New User ID: ");
            userId = scan.nextInt();
            if (!conf.doesIDExist("user", userId)) {
                System.out.println("User ID doesn't exist.");
            }
        } while (!conf.doesIDExist("user", userId));

        int placeId;
        do {
            System.out.print("New Place ID: ");
            placeId = scan.nextInt();
            if (!conf.doesIDExist("place", placeId)) {
                System.out.println("Place ID doesn't exist.");
            }
        } while (!conf.doesIDExist("place", placeId));

        scan.nextLine();
        System.out.print("New Timestamp (MM-DD-YYY HH:MM): ");
        String timestamp = scan.nextLine();

        String sql = "UPDATE location_log SET user_id = ?, place_id = ?, timestamp = ? WHERE id = ?";
        conf.updateRecord(sql, userId, placeId, timestamp, logId);
        System.out.println("Location Log updated successfully.");
    }

    private void deleteLocationLog() {
        System.out.print("Enter Location Log ID to delete: ");
        int logId = scan.nextInt();
        if (!conf.doesIDExist("location_log", logId)) {
            System.out.println("Location Log ID doesn't exist.");
            return;
        }

        String sql = "DELETE FROM location_log WHERE id = ?";
        conf.deleteRecord(sql, logId);
        System.out.println("Location Log deleted successfully.");
    }

    private void viewLocationLogs() {
        String sql = "SELECT * FROM location_log";
        String[] headers = {"ID", "User ID", "Place ID", "Timestamp"};
        String[] columns = {"id", "user_id", "place_id", "timestamp"};
        conf.viewRecords(sql, headers, columns);
    }
}
