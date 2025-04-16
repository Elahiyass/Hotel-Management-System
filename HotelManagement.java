import java.util.ArrayList;
import java.util.Scanner;

class Guest {
    String name;
    int roomNumber;

    Guest(String name, int roomNumber) {
        this.name = name;
        this.roomNumber = roomNumber;
    }

    public String toString() {
        return "Guest Name: " + name + ", Room Number: " + roomNumber;
    }
}

public class HotelManagement {
    private static ArrayList<Guest> guests = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n---- Hotel Management System ----");
            System.out.println("1. Add Guest");
            System.out.println("2. View Guests");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addGuest();
                    break;
                case 2:
                    viewGuests();
                    break;
                case 3:
                    System.out.println("Exiting... Thank you!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addGuest() {
        System.out.print("Enter guest name: ");
        String name = scanner.nextLine();
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        guests.add(new Guest(name, roomNumber));
        System.out.println("Guest added successfully.");
    }

    private static void viewGuests() {
        if (guests.isEmpty()) {
            System.out.println("No guests added yet.");
        } else {
            System.out.println("\n-- Guest List --");
            for (Guest g : guests) {
                System.out.println(g);
            }
        }
    }
}
