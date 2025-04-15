import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Consulta extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Configuración de la base de datos
        String url = "jdbc:mysql://192.168.1.34:3306/llibres";
        String user = "pdelasheras";
        String password = "pdelasheras";

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Conexión y consulta
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM llibres";
            ResultSet rs = stmt.executeQuery(query);

            out.println("<html><body>");
            out.println("<h1>Resultados de la consulta</h1>");
            while (rs.next()) {
                out.println("<p>" + rs.getString(1) + " - " + rs.getString(2) + "</p>");
            }
            out.println("</body></html>");

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            out.println("<html><body>");
            out.println("<h1>Error al ejecutar la consulta</h1>");
            out.println("<p>" + e.getMessage() + "</p>");
            out.println("</body></html>");
        }
    }
}
