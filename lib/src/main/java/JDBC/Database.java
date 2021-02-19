package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Database {
    String url;
    String user;
    String password;
    String sql;
    Connection con;
    Statement st;
    // To insert value in QUERY
    PreparedStatement prep_st;

    Database() {
        url = "jdbc:postgresql://localhost:5432/mydatabase";
        user = "admin";
        password = "admin";
        sql = "select * from student";
        try {
            // Register driver
            Class.forName("org.postgresql.Driver");
            // Establish connection
            con = DriverManager.getConnection(url, user, password);
            // Create statement
            st = con.createStatement();
        } catch (ClassNotFoundException e) {
            System.out.println("Driver missing");
        } catch (SQLException e) {
            System.out.println("SQL Initilization Exception");
        }
    }
    public void fetchData() {

        sql = "select * from student";
        
        try {
            // Execute the Query
            ResultSet rs = st.executeQuery(sql);
            // Process result
            while(rs.next()){
                String name = rs.getString("name");
                String address = rs.getString("address");
                System.out.println(name + " : " + address);
            }

        } catch (SQLException e) {
            System.out.println("SQL Query run Exception");
        }
    }
    
    public void insertData(String name, String address) {
        String sql = "insert into student(name, address, updated_on) values (?,?,NOW())";
        int count = 0;
        try {
            prep_st = con.prepareStatement(sql);
            prep_st.setString(1, name);
            prep_st.setString(2, address);
            count = prep_st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL Query run Exception");
        }
        System.out.println(count + " Rows updated");
    }

    public void closeConnection() {
        try {
            // Closing connection
            prep_st.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("SQL Exception in closing");
        }
    }
}
