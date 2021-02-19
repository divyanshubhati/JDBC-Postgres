package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class StudentDAO {
    String url;
    String user;
    String password;
    String sql;
    Connection con;
    Statement st;
    // To insert value in QUERY
    PreparedStatement prep_st;

    StudentDAO() {
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
    public Student getStudentById(int id) {

        sql = "select * from student where id=" + id;
        
        try {
            // Execute the Query
            ResultSet rs = st.executeQuery(sql);
            // Process result
            rs.next();
            int tempId = rs.getInt(1);
            String name = rs.getString(2);
            String address = rs.getString(3);
            Student st = new Student();
            st.setName(name);
            st.setaddress(address);
            st.setId(tempId);
            return st;

        } catch (SQLException e) {
            System.out.println("SQL Query run Exception");
        }
        return null;
    }
    
    public int addStudent(Student s) {
        String name = s.getName();
        String address = s.geAaddress();
        String sql = "insert into student(name, address, updated_on) values (?,?,NOW())";
        int count = 0;
        try {
            // tell database to return the generated keys
            prep_st = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            prep_st.setString(1, name);
            prep_st.setString(2, address);
            count = prep_st.executeUpdate();
            // get the returned keys
            ResultSet generatedKeys = prep_st.getGeneratedKeys();
            System.out.println(count + " Rows updated");
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("SQL Query run Exception");
        }
        return -1;
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
