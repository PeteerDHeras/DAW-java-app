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

/**
 * Servlet que realiza una consulta a una base de datos MySQL y muestra los resultados en HTML.
 */
public class Consulta extends HttpServlet {

    /**
     * Maneja las solicitudes HTTP GET.
     *
     * @param request  el objeto HttpServletRequest que contiene la solicitud del cliente
     * @param response el objeto HttpServletResponse que se usa para responder al cliente
     * @throws ServletException si ocurre un error específico del servlet
     * @throws IOException      si ocurre un error de entrada/salida
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Configuración de la base de datos
        String url = "jdbc:mysql://192.168.1.166:3306/llibres";
        String user = "pdelasheras";
        String password = "pdelasheras";

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Conexión y consulta
        try {
            // Establece la conexión con la base de datos
            Connection conn = DriverManager.getConnection(url, user, password);

            // Crea una declaración SQL
            Statement stmt = conn.createStatement();

            // Ejecuta la consulta SELECT
            String query = "SELECT * FROM llibres";
            ResultSet rs = stmt.executeQuery(query);

            // Construye la respuesta HTML con los resultados
            out.println("<html><body>");
            out.println("<h1>Resultados de la consulta</h1>");
            while (rs.next()) {
                out.println("<p>" + rs.getString(1) + " - " + rs.getString(2) + "</p>");
            }
            out.println("</body></html>");

            // Cierra los recursos
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            // Muestra el mensaje de error en caso de excepción
            out.println("<html><body>");
            out.println("<h1>Error al ejecutar la consulta</h1>");
            out.println("<p>" + e.getMessage() + "</p>");
            out.println("</body></html>");
        }
    }
}
