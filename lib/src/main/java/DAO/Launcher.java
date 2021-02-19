package DAO;

import java.util.ArrayList;

public class Launcher {
    public static void main(String[] args) {
        StudentDAO STdao = new StudentDAO();
        Student st = STdao.getStudentById(3);
        st.display();
        Student stud = new Student();
        stud.setName("Shubham");
        stud.setaddress("Nokha");
        stud.setId(STdao.addStudent(stud));
        stud.display();
        STdao.closeConnection();
    }
}
