//Main.java
//This is the starting point of the program. It shows the menu and handles login for Admin or Staff
public class Main {
    public static void main(String[] args) {
        Auth.login(); // Login first
        HotelSystem.run(); // Start hotel system after login
    }
}

//Auth.java
//Handles login with hardcoded users.
import java.util.Scanner;
public class Auth {
    public static String role = "";

    public static void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Login (Admin or Staff)");
        System.out.print("Username: ");
        String user = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();

        if ((user.equals("admin") && pass.equals("admin123"))) {
            role = "Admin";
        } else if (user.equals("staff") && pass.equals("staff123")) {
            role = "Staff";
        } else {
            System.out.println("Invalid login.");
            System.exit(0);
        }

        System.out.println("Logged in as " + role);
    }
}

// HotelSystem.java
//This is the main menu for adding guests, booking rooms, viewing bills, etc.
import java.util.Scanner;

public class HotelSystem {
    public static void run() {
        Scanner sc = new Scanner(System.in);
        BookingManager.loadData();

        while (true) {
            System.out.println("\n--- Hotel Menu ---");
            System.out.println("1. Add Guest");
            System.out.println("2. View Guests");
            System.out.println("3. View Rooms");
            System.out.println("4. Checkout Guest");
            System.out.println("5. Save & Exit");

            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> BookingManager.addGuest();
                case 2 -> BookingManager.viewGuests();
                case 3 -> BookingManager.viewRooms();
                case 4 -> BookingManager.checkoutGuest();
                case 5 -> {
                    BookingManager.saveData();
                    System.out.println("Saved. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}

//Guest.java
//Holds guest details like name, room, and check-in/out dates.

public class Guest {
    String name, mobile, email;
    int roomNumber;
    String checkInDate, checkOutDate;

    public Guest(String name, String mobile, String email, int roomNumber, String in, String out) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.roomNumber = roomNumber;
        this.checkInDate = in;
        this.checkOutDate = out;
    }

    public String toString() {
        return name + " | Room: " + roomNumber + " | " + mobile + " | In: " + checkInDate + " | Out: " + checkOutDate;
    }
}

//Room.java
//Tracks room status (Available, Booked, Maintenance).
public class Room {
    int roomNumber;
    String status; // Available, Booked, Maintenance

    public Room(int roomNumber, String status) {
        this.roomNumber = roomNumber;
        this.status = status;
    }

    public String toString() {
        return "Room " + roomNumber + " - " + status;
    }
}

//BookingManager.java
//Handles booking logic, file saving, room status, and more.
import java.util.*;
import java.io.*;

public class BookingManager {
    static ArrayList<Guest> guests = new ArrayList<>();
    static ArrayList<Room> rooms = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    public static void addGuest() {
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Mobile: ");
        String mobile = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Room number: ");
        int room = Integer.parseInt(sc.nextLine());

        Room r = getRoom(room);
        if (r == null || r.status.equals("Booked")) {
            System.out.println("Room not available.");
            return;
        }

        System.out.print("Check-in date: ");
        String in = sc.nextLine();
        System.out.print("Check-out date: ");
        String out = sc.nextLine();

        Guest g = new Guest(name, mobile, email, room, in, out);
        guests.add(g);
        r.status = "Booked";
        System.out.println("Guest added.");
    }

    public static void viewGuests() {
        for (Guest g : guests) {
            System.out.println(g);
        }
    }

    public static void viewRooms() {
        for (Room r : rooms) {
            System.out.println(r);
        }
    }

    public static void checkoutGuest() {
        System.out.print("Room number to checkout: ");
        int room = Integer.parseInt(sc.nextLine());

        Guest found = null;
        for (Guest g : guests) {
            if (g.roomNumber == room) {
                found = g;
                break;
            }
        }

        if (found != null) {
            double bill = BillingSystem.calculateBill(found);
            System.out.println("Invoice for " + found.name);
            System.out.println("Total Bill: $" + bill);
            guests.remove(found);
            getRoom(room).status = "Available";
        } else {
            System.out.println("Guest not found.");
        }
    }

    public static void loadData() {
        // Add some default rooms
        for (int i = 101; i <= 105; i++) {
            rooms.add(new Room(i, "Available"));
        }
    }

    public static void saveData() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("guests.txt"))) {
            for (Guest g : guests) {
                bw.write(g.name + "," + g.mobile + "," + g.email + "," + g.roomNumber + "," + g.checkInDate + "," + g.checkOutDate);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    public static Room getRoom(int number) {
        for (Room r : rooms) {
            if (r.roomNumber == number)
                return r;
        }
        return null;
    }
}

//BillingSystem.java
//Simple calculator for total charges based on days.
public class BillingSystem {
    public static double calculateBill(Guest g) {
        // For simplicity, assume $100 per night
        return 100.0 * getDays(g.checkInDate, g.checkOutDate);
    }

    public static int getDays(String in, String out) {
        // Assume format: yyyy-mm-dd
        String[] inParts = in.split("-");
        String[] outParts = out.split("-");

        int inDay = Integer.parseInt(inParts[2]);
        int outDay = Integer.parseInt(outParts[2]);

        return outDay - inDay;
    }
}
