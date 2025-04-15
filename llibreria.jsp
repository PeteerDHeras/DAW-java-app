<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Libros Disponibles</title>
</head>
<body>

    <h1>Lista de Libros</h1>

    <table border="1">
        <thead>
            <tr>
                <th>Título</th>
                <th>ISBN</th>
                <th>Año de Publicacion</th>
                <th>ID Editorial</th>
            </tr>
        </thead>
        <tbody>
            <%
                String dbUrl = "jdbc:mysql://192.168.1.34:3306/llibres";
                String dbUser = "pdelasheras";
                String dbPassword = "pdelasheras";
                String query = "SELECT titol, isbn, any_publicacio, id_editorial FROM llibres";

                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                try {
                    // Conectar a la base de datos
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(query);

                    // Mostrar los resultados en la tabla
                    while (rs.next()) {
                        String titol = rs.getString("titol");
                        String isbn = rs.getString("isbn");
                        int anyPublicacio = rs.getInt("any_publicacio");
                        int idEditorial = rs.getInt("id_editorial");

                        out.println("<tr>");
                        out.println("<td>" + titol + "</td>");
                        out.println("<td>" + isbn + "</td>");
                        out.println("<td>" + anyPublicacio + "</td>");
                        out.println("<td>" + idEditorial + "</td>");
                        out.println("</tr>");
                    }
                } catch (Exception e) {
                    out.println("<tr><td colspan='4'>Error al conectar con la base de datos: " + e.getMessage() + "</td></tr>");
                } finally {
                    // Cerrar recursos
                    try {
                        if (rs != null) rs.close();
                        if (stmt != null) stmt.close();
                        if (conn != null) conn.close();
                    } catch (SQLException e) {
                        out.println("<tr><td colspan='4'>Error cerrando la conexión: " + e.getMessage() + "</td></tr>");
                    }
                }
            %>
        </tbody>
    </table>

</body>
</html>
