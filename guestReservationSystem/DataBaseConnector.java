package guestReservationSystem;

import java.sql.*;

class DataBaseConnector {
        public void create()  {
        Connection con;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS guestReservationSystem");
            stmt.executeUpdate("USE guestReservationSystem");
            stmt.executeUpdate("CREATE TABLE  Guests( ID INTEGER PRIMARY KEY , name VARCHAR(20),date VARCHAR(12),noOfGuest INTEGER)");
            System.out.println("Done!");
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void insert(String name, String Date, String NoOfGuests){
        Connection con;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("USE guestReservationSystem");
            PreparedStatement pst = con.prepareStatement("Insert into Guests (NAME, DATE,NoOfGuests) values(?,?,?)");
            pst.setString(1, name);
            pst.setString(2, Date);
            pst.setString(3, NoOfGuests);
            System.out.println("done");
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        // new DataBaseConnector().create();
        new DataBaseConnector().insert("teja", "2020-01-01", "2");
    }
    
}
