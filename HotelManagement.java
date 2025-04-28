import java.util.ArrayList;
import java.util.Scanner;

class Guest {
    String name;
    int roomNumber;
    int mobilenumber;
    String email;

    Guest(String name, int roomNumber,int mobilenumber, string email) {
        this.name = name;
        this.roomNumber = roomNumber;
        this.mobilenumber = mobilenumber;
        this.email = email;
    }

    public String toString() {
        return "Guest Name: " + name + ", Room Number: " + roomNumber + ", Mobile Number: " + mobilenumber + ", Email: " + email;
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
            System.out.println("3. Update Guest");
            System.out.println("4. Delete Guest");
            System.out.println("6. Exit");
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
                    updateGuest();
                    break;
                case 4:
                    deleteGuest();
                    break;
                case 6:
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
        scanner.nextLine(); // Consume newline
        System.out.println("Enter room number: ");
        int roomNumber = scanner.nextInt();
        System.out.println("Enter Mobile Number:");
        int mobilenumber = scanner.nextInt();
        System.out.println("Enter Email:");
        int mobilenumber = scanner.nextIntLine();
        if(isRoomBooked(roomNumber))
        {
            System.out.println("Room" + roomNumber + " is already booked. please choose another room.");
        }
        else{
        guests.add(new Guest(name, roomNumber, mobilenumber, email));
        System.out.println("Guest added successfully.");
        }
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
    private static void updateGuest(){
        if(guests.isEmpty()){
            System.out.println("No guests available to update.");
            return;
        }
        System.out.println("Enter number of guest to update:");
        int roomNumber = scanner.nextInt();
        Guest foundGuest = null;
        for(Guest g : guests){
            if(g.roomNumber == roomNumber){
                foundGuest = g;
                break;
            }
        }
        if(foundGuest != null){
            System.out.print("Enter new Guest Name : ");
            foundGuest.name = scanner.nextline();
            System.out.println("Enter a Guest Mobile Number: ");
            foundGuest.mobilenumber= scanner.nextline();
            System.out.println("Enter a Guest Email: ");
            foundGuest.email= scanner.nextline();
            System.out.println("Guest Details updates successfully !!);
        }
        else
        {
            System.out.println("Guest not found.");
        }
    }
    private static void deleteGuest()
    {
        if(guests.isEmpty()){
            System.out.println("No guests available to delete.");
            return;
        }
        System.out.println("Enter room number of a Guest to delete:");
        int roomNumber =  scanner.nextInt();
        Guest guestToRemove = null;
        for(Guest g : guests){
            if(g.roomNumber == roomNumber){
                guestToRemove = g;
                break;
            }
        }
        if(guestToRemove != null)
        {
            guests.remove(guestsToRemove);
            System.out.println("Guest deleted Successfully !");
        }
        else{
            System.out.println("Guest not found.");
        }
    }
}
   
