package locationtrackersystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LocationTrackerSystem {
    static Config conf = new Config();
    static Scanner scan = new Scanner(System.in);
    static Users users = new Users();
    static Places places = new Places();
    static LocationLogs locationLogs = new LocationLogs();

    public static void main(String[] args) {
        int choice;

        do {
            try {
                System.out.println("\n   + Location Tracker System +\n");
                System.out.println("1. Manage Users");
                System.out.println("2. Manage Places");
                System.out.println("3. Manage Location Logs");
                System.out.println("4. Generate Reports");
                System.out.println("5. Exit");
                
                System.out.print("Enter Option: ");
                choice = scan.nextInt();
                scan.nextLine();
                System.out.println("");

                switch (choice) {
                    case 1:  
                        users.userConfig();
                        break;
                    case 2:
                        places.placeConfig();
                        break;
                    case 3:
                        locationLogs.locationLogConfig();
                        break;
                    case 4:
                        generateReports();
                        break;
                    case 5:                      
                        System.out.println("Exiting Location Tracker System.");
                        break;
                    default:
                        System.out.println("Invalid Option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scan.nextLine();
                choice = -1;
            }
        } while (choice != 5);
    }

    static void generateReports() {
        System.out.println("\n\t\t\t\t\t--- REPORTS ---");
        System.out.println("1. User Report");
        System.out.println("2. Place Report");
        System.out.println("3. Location Log Report");
        System.out.print("Choose a report type: ");

        int reportChoice;
        reportChoice = scan.nextInt();
        scan.nextLine();

        switch (reportChoice) {
            case 1:
                System.out.println("\n\t\t\t--- USER REPORT ---");
                String userQuery = "SELECT * FROM user";
                String[] userHeaders = {"ID", "Name", "Contact Info"};
                String[] userColumns = {"id", "name", "contact_info"};
                conf.viewRecords(userQuery, userHeaders, userColumns);
                break;
            case 2:
                System.out.println("\n\t\t\t--- PLACE REPORT ---");
                String placeQuery = "SELECT * FROM place";
                String[] placeHeaders = {"ID", "Name", "Description"};
                String[] placeColumns = {"id", "name", "description"};
                conf.viewRecords(placeQuery, placeHeaders, placeColumns);
                break;
            case 3:
                System.out.println("\n\t\t\t--- LOCATION LOG REPORT ---");
                String logQuery = "SELECT * FROM location_log";
                String[] logHeaders = {"ID", "User ID", "Place ID", "Timestamp"};
                String[] logColumns = {"id", "user_id", "place_id", "timestamp"};
                conf.viewRecords(logQuery, logHeaders, logColumns);
                break;
            default:
                System.out.println("Invalid report option.");
        }
    }
}

