package ConexionEmpresa;

import java.sql.*;
public class main {
    public static void main(String[] args) {

        conexion conexion = new conexion();
        Connection cn = null;
        Statement stm = null;
        ResultSet rs = null;



        try{
            cn = conexion.getConnection();
            stm = cn.createStatement();


        }catch(SQLException e) {

        }
    }
}