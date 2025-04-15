import java.sql.*;

public class Connexio {

    private static final String URL = "jdbc:mysql://192.168.1.34:3306/llibres"; 
    private static final String USER = "pdelasheras";
    private static final String PASSWORD = "pdelasheras";

    public static Connection getConnexio() throws SQLException {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver"); 
            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Error en establir la connexió amb la base de dades", e);
        }
    }
}
