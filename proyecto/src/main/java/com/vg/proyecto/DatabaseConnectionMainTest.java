package com.vg.proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectionMainTest {

    private static final String URL = "jdbc:postgresql://isabelle.db.elephantsql.com:5432/wziqcptz";
    private static final String USER = "wziqcptz";
    private static final String PASSWORD = "JrApBL51pq1oyip451VcDVOMFR3587YZ";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Intentar establecer la conexión a la base de datos
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            if (connection != null) {
                System.out.println("Conexión a la base de datos PostgreSQL exitosa.");

                // Crear un Statement para ejecutar la consulta SQL
                statement = connection.createStatement();

                // Definir la consulta SQL para seleccionar todos los registros de la tabla Padre
                String sql = "SELECT id, nombre, apellido, email FROM Padre";

                // Ejecutar la consulta SQL y obtener los resultados
                resultSet = statement.executeQuery(sql);

                // Procesar los resultados
                System.out.println("Registros en la tabla Padre:");
                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String nombre = resultSet.getString("nombre");
                    String apellido = resultSet.getString("apellido");
                    String email = resultSet.getString("email");

                    System.out.println("ID: " + id + ", Nombre: " + nombre + ", Apellido: " + apellido + ", Email: " + email);
                }
            } else {
                System.out.println("Fallo en la conexión a la base de datos PostgreSQL.");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos PostgreSQL: " + e.getMessage());
        } finally {
            // Cerrar ResultSet, Statement y Connection en el orden inverso a su creación
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }
    }
}
