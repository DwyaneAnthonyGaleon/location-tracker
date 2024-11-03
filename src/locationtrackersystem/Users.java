package locationtrackersystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Users {
    Scanner scan = new Scanner(System.in);
    Config conf = new Config();

    public void userConfig() {
        int option;
        do {
            System.out.println("\n--- User Management ---");
            System.out.println("1. Add User");
            System.out.println("2. View Users");
            System.out.println("3. Edit User");
            System.out.println("4. Delete User");
            System.out.println("5. Exit");

            try {
                System.out.print("Choose an option: ");
                option = scan.nextInt();
                scan.nextLine();

                switch (option) {
                    case 1: addUser(); break;
                    case 2: viewUsers(); break;
                    case 3: editUser(); break;
                    case 4: deleteUser(); break;
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

    private void addUser() {
        System.out.print("Enter User Name: ");
        String name = scan.nextLine();
        System.out.print("Enter Contact Info: ");
        String contactInfo = scan.nextLine();

        String sql = "INSERT INTO user (name, contact_info) VALUES (?, ?)";
        conf.addRecord(sql, name, contactInfo);
    }

    public void viewUsers() {
        String query = "SELECT * FROM user";
        String[] headers = {"ID", "Name", "Contact Info"};
        String[] columns = {"id", "name", "contact_info"};
        
        conf.viewRecords(query, headers, columns);
    }

    private void editUser() {
        int userId;
        do {
            System.out.print("\nEnter User ID: ");
            userId = scan.nextInt();
            if (!conf.doesIDExist("user", userId)) {
                System.out.println("User ID doesn't exist.");
            }
        } while (!conf.doesIDExist("user", userId));
        scan.nextLine();

        System.out.println("Enter New User Details:");
        System.out.print("New Name: ");
        String name = scan.nextLine();
        System.out.print("New Contact Info: ");
        String contactInfo = scan.nextLine();

        String sql = "UPDATE user SET name = ?, contact_info = ? WHERE id = ?";
        conf.updateRecord(sql, name, contactInfo, userId);
    }

    private void deleteUser() {
        System.out.print("Enter User ID to delete: ");
        int userId = scan.nextInt();
        
        String sql = "DELETE FROM user WHERE id = ?";
        conf.deleteRecord(sql, userId);
    }
}
