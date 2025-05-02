import java.util.ArrayList;
import java.util.Scanner;

public class HotelManagementSystem {
    private static ArrayList<Guest> guests = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Hotel Management System ---");
            System.out.println("1. Add Guest");
            System.out.println("2. View Guests");
            System.out.println("3. Update Guest");
            System.out.println("4. Delete Guest");
            System.out.println("5. Search Guest");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = getIntInput();

            switch (choice) {
                case 1: addGuest(); break;
                case 2: viewGuests(); break;
                case 3: updateGuest(); break;
                case 4: deleteGuest(); break;
                case 5: searchGuest(); break;
                case 6: running = false; System.out.println("Thank you!"); break;
                default: System.out.println("Invalid choice. Try 1-6.");
            }
        }
    }

    private static void addGuest() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter mobile number: ");
        String mobile = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter room number: ");
        int roomNumber = getIntInput();

        if (isRoomBooked(roomNumber)) {
            System.out.println("Room already booked.");
        } else {
            guests.add(new Guest(name, mobile, email, roomNumber));
            System.out.println("Guest added successfully.");
        }
    }

    private static void viewGuests() {
        if (guests.isEmpty()) {
            System.out.println("No guests.");
        } else {
            for (Guest g : guests) {
                System.out.println(g);
            }
        }
    }

    private static void updateGuest() {
        System.out.print("Enter room number to update: ");
        int roomNumber = getIntInput();
        for (Guest g : guests) {
            if (g.roomNumber == roomNumber) {
                System.out.print("New name: ");
                g.name = scanner.nextLine();
                System.out.print("New mobile: ");
                g.mobile = scanner.nextLine();
                System.out.print("New email: ");
                g.email = scanner.nextLine();
                System.out.println("Guest updated.");
                return;
            }
        }
        System.out.println("Guest not found.");
    }

    private static void deleteGuest() {
        System.out.print("Enter room number to delete: ");
        int roomNumber = getIntInput();
        for (Guest g : guests) {
            if (g.roomNumber == roomNumber) {
                guests.remove(g);
                System.out.println("Guest deleted.");
                return;
            }
        }
        System.out.println("Guest not found.");
    }

    private static void searchGuest() {
        System.out.print("Enter guest name to search: ");
        String name = scanner.nextLine().toLowerCase();
        boolean found = false;
        for (Guest g : guests) {
            if (g.name.toLowerCase().contains(name)) {
                System.out.println(g);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Guest not found.");
        }
    }

    private static boolean isRoomBooked(int roomNumber) {
        for (Guest g : guests) {
            if (g.roomNumber == roomNumber) {
                return true;
            }
        }
        return false;
    }

    private static int getIntInput() {
        int input;
        while (true) {
            try {
                input = Integer.parseInt(scanner.nextLine());
                return input;
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid number: ");
            }
        }
    }
}

class Person {
    protected String name;
    protected String mobile;
    protected String email;

    public Person(String name, String mobile, String email) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
    }
}

class Guest extends Person {
    int roomNumber;

    public Guest(String name, String mobile, String email, int roomNumber) {
        super(name, mobile, email);
        this.roomNumber = roomNumber;
    }

    public String toString() {
        return "Name: " + name + ", Room: " + roomNumber +
               ", Mobile: " + mobile + ", Email: " + email;
    }
}
