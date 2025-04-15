import java.sql.*;

/**
 * Clase para establecer una conexión con la base de dades MySQL.
 */
public class Connexio {

    private static final String URL = "jdbc:mysql://192.168.1.34:3306/llibres"; 
    private static final String USER = "pdelasheras";
    private static final String PASSWORD = "pdelasheras";

    /**
     * Establece y devvuelve la conexión a la base de datos
     *
     * @return Devuelve la conexión con la base de datos.
     * @throws SQLException si hi ha un error al carregar el controlador o a l'establir la connexió
     */
    public static Connection getConnexio() throws SQLException {
        try {
            // Carrega el controlador JDBC per a MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Retorna la connexió
            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            // Llança una nova excepció amb un missatge personalitzat
            throw new SQLException("Error en establir la connexió amb la base de dades", e);
        }
    }
}
