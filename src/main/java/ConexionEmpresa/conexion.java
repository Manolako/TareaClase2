package ConexionEmpresa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {

        private static final String URL = "jdbc:mysql://localhost:3309/empresa?allowPublicKeyRetrieval=true";
        private static final String USER = "root";
        private static final String PASSWORD = "1234";

        public static Connection getConnection() {
            Connection conexion = null;
            try {
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión OK");
            } catch (SQLException e) {
                System.out.println("Error en la conexión");
                e.printStackTrace();
            }
            return conexion;
        }
    }


