package ConexionEmpresa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        ConectarBBDD conexion = new ConectarBBDD();
        Connection cn = null;
        Statement stm = null;
        ResultSet rs = null;


        try {
            cn = conexion.conectar();
            stm = cn.createStatement();


        } catch (SQLException e) {

        }
    }
}