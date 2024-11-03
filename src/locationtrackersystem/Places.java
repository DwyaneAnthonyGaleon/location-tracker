package locationtrackersystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Places {
    Scanner scan = new Scanner(System.in);
    Config conf = new Config();

    public void placeConfig() {
        int option;
        do {
            System.out.println("\n--- Place Management ---");
            System.out.println("1. Add Place");
            System.out.println("2. View Places");
            System.out.println("3. Edit Place");
            System.out.println("4. Delete Place");
            System.out.println("5. Exit");

            try {
                System.out.print("Choose an option: ");
                option = scan.nextInt();
                scan.nextLine();

                switch (option) {
                    case 1: 
                        addPlace(); 
                        break;
                    case 2: 
                        viewPlaces(); 
                        break;
                    case 3: 
                        editPlace(); 
                        break;
                    case 4: 
                        deletePlace(); 
                        break;
                    case 5: 
                        System.out.println("Returning to main menu."); 
                        break;
                    default: 
                        System.out.println("Invalid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scan.nextLine(); 
                option = -1;
            }
        } while (option != 5);
    }

    private void addPlace() {
        System.out.print("Enter Place Name: ");
        String name = scan.nextLine();
        System.out.print("Enter Description: ");
        String description = scan.nextLine();

        String sql = "INSERT INTO place (name, description) VALUES (?, ?)";
        conf.addRecord(sql, name, description);
    }

    public void viewPlaces() {
        String query = "SELECT * FROM place";
        String[] headers = {"ID", "Name", "Description"};
        String[] columns = {"id", "name", "description"};
        
        conf.viewRecords(query, headers, columns);
    }

    private void editPlace() {
        int placeId;
        do {
            System.out.print("\nEnter Place ID: ");
            placeId = scan.nextInt();
            if (!conf.doesIDExist("place", placeId)) {
                System.out.println("Place ID doesn't exist.");
            }
        } while (!conf.doesIDExist("place", placeId));
        scan.nextLine();

        System.out.println("Enter New Place Details:");
        System.out.print("New Name: ");
        String name = scan.nextLine();
        System.out.print("New Description: ");
        String description = scan.nextLine();

        String sql = "UPDATE place SET name = ?, description = ? WHERE id = ?";
        conf.updateRecord(sql, name, description, placeId);
    }

    private void deletePlace() {
        System.out.print("Enter Place ID to delete: ");
        int placeId = scan.nextInt();
        
        String sql = "DELETE FROM place WHERE id = ?";
        conf.deleteRecord(sql, placeId);
    }
}

