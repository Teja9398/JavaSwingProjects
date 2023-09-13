package guestReservationSystem; 

import java.util.*;
import java.sql.*;

 class ReservationSystem {
    public boolean makeReservation(String name, String date, int numberOfGuests) {
        boolean reservation = false;
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
            Statement stmt = con.createStatement();
            stmt.execute("USE guestreservationsystem;");
            if( stmt.executeUpdate("INSERT INTO guests (name,date,noOfGuest) values (\""+name+"\",\""+ date+"\",\""+numberOfGuests+"\");")>0){
                reservation = true;
            }
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return reservation;
    }

    public void getReservations() {
        ResultSet rs = null;
       try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
            Statement stmt = con.createStatement();
            stmt.execute("USE guestreservationsystem;");
            rs = stmt.executeQuery("SELECT * FROM guests");
            while(rs.next()){
                System.out.println(rs.getInt(1)+"\t"+ rs.getString(2)+"\t"+ rs.getString(3)+"\t"+ rs.getString(4));
            }
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void getReservationById(int id) {
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
            Statement stmt = con.createStatement();
            stmt.executeQuery("select * from  guestreservationsystem where id =" +Integer.toString(id));
            ResultSet rs = stmt.getResultSet();
            if(rs.next()){
                System.out.println(rs.getInt(1)+" "+ rs.getString(2)+" "+ rs.getString(3)+" "+ rs.getString(4));
            }
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public boolean cancelReservation(int id) {
        boolean canceled = false;
         try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
            Statement stmt = con.createStatement();
            stmt.execute("USE guestreservationsystem;");
            if( stmt.executeUpdate("DELETE FROM guests WHERE Id = "+Integer.toString(id))>0){
                canceled = true;
            }
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return canceled;
    }

}


 public class ReservationSystemUI {
    private ReservationSystem reservationSystem = new ReservationSystem();

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Make a reservation");
            System.out.println("2. View all reservations");
            System.out.println("3. Cancel a reservation");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Date: ");
                    String date = scanner.nextLine();
                    System.out.print("Number of guests: ");
                    int numberOfGuests = scanner.nextInt();
                    scanner.nextLine();
                    if(reservationSystem.makeReservation(name, date, numberOfGuests)){
                        System.out.println("Reservation Successful");
                    }else{
                        System.out.println("Reservation Unsuccessful");
                    }
                    break;
                case 2:
                    System.out.println("Reservations:");
                    System.out.println("ID\tName\tDate\t\tpeople");
                    reservationSystem.getReservations();
                    break;
                case 3:
                    System.out.print("Reservation ID to cancel: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    if (reservationSystem.cancelReservation(id)) {
                        System.out.println("Reservation canceled");
                    } else {
                        System.out.println("Reservation not found");
                    }
                    break;
                case 4:
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice");
            }

            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        ReservationSystemUI obj = new ReservationSystemUI();
        obj.start();
    }
}