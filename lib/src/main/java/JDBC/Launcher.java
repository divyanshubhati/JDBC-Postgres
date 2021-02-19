package JDBC;

public class Launcher {
    public static void main(String[] args) {
        Database dataBase = new Database();
        dataBase.insertData("Mridul", "Jaipur");
        dataBase.fetchData();
        dataBase.closeConnection();
    }
}
