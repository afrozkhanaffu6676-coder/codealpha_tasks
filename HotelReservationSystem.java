import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotelReservationSystem {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final List<Room> rooms = new ArrayList<Room>();
    private static final List<Booking> bookings = new ArrayList<Booking>();
    private static int nextBookingId = 1001;

    private static class Room {
        final int number;
        final String category;
        final double pricePerNight;

        Room(int number, String category, double pricePerNight) {
            this.number = number;
            this.category = category;
            this.pricePerNight = pricePerNight;
        }
    }

    private static class Booking {
        final int id;
        final String guestName;
        final Room room;
        final LocalDate checkIn;
        final LocalDate checkOut;
        final int nights;
        final double amountPaid;
        boolean active = true;

        Booking(int id, String guestName, Room room, LocalDate checkIn,
                LocalDate checkOut, int nights, double amountPaid) {
            this.id = id;
            this.guestName = guestName;
            this.room = room;
            this.checkIn = checkIn;
            this.checkOut = checkOut;
            this.nights = nights;
            this.amountPaid = amountPaid;
        }
    }

    public static void main(String[] args) {
        initializeRooms();
        System.out.println("==================================");
        System.out.println("     HOTEL RESERVATION SYSTEM");
        System.out.println("==================================");

        boolean running = true;
        while (running) {
            System.out.println("\\n1. Search available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View booking details");
            System.out.println("4. Cancel a reservation");
            System.out.println("5. View all active bookings");
            System.out.println("6. Exit");
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1: searchRooms(); break;
                case 2: makeReservation(); break;
                case 3: viewBooking(); break;
                case 4: cancelReservation(); break;
                case 5: viewAllBookings(); break;
                case 6:
                    running = false;
                    System.out.println("Thank you for using Hotel Reservation System.");
                    break;
                default: System.out.println("Invalid option. Choose 1 to 6.");
            }
        }
        SCANNER.close();
    }

    private static void initializeRooms() {
        rooms.add(new Room(101, "Standard", 1500));
        rooms.add(new Room(102, "Standard", 1500));
        rooms.add(new Room(103, "Standard", 1500));
        rooms.add(new Room(201, "Deluxe", 2500));
        rooms.add(new Room(202, "Deluxe", 2500));
        rooms.add(new Room(203, "Deluxe", 2500));
        rooms.add(new Room(301, "Suite", 4500));
        rooms.add(new Room(302, "Suite", 4500));
    }

    private static void searchRooms() {
        String category = readCategory();
        LocalDate checkIn = readDate("Enter check-in date (YYYY-MM-DD): ");
        LocalDate checkOut = readDate("Enter check-out date (YYYY-MM-DD): ");
