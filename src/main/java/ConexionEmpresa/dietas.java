package ConexionEmpresa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class dietas {
        public void crearTablaDietas() {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS Dietas (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "empleado VARCHAR(100), " +
                    "departamento VARCHAR(50), " +
                    "cantidad DECIMAL(10, 2), " +
                    "concepto VARCHAR(255)" +
                    ")";

            try (Connection conn = conexion.getConnection();
                 Statement stmt = conn.createStatement()) {
                stmt.execute(createTableSQL);
                System.out.println("Tabla Dietas creada o ya existe.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void insertarDietas() {
            String insertSQL = "INSERT INTO Dietas (empleado, departamento, cantidad, concepto) VALUES " +
                    "(?, ?, ?, ?)";

            try (Connection conn = conexion.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                conn.setAutoCommit(false);

                pstmt.setString(1, "Juan Pepe");
                pstmt.setString(2, "Informático");
                pstmt.setDouble(3, 22.00);
                pstmt.setString(4, "Transporte");
                pstmt.addBatch();

                pstmt.setString(1, "Pepito Fli");
                pstmt.setString(2, "Ventas");
                pstmt.setDouble(3, 35.00);
                pstmt.setString(4, "Almuerzo");
                pstmt.addBatch();

                pstmt.setString(1, "Luis Santos");
                pstmt.setString(2, "Recursos Humanos");
                pstmt.setDouble(3, 20.00);
                pstmt.setString(4, "Material de Oficina");
                pstmt.addBatch();

                pstmt.setString(1, "Antonia Maritnez");
                pstmt.setString(2, "Informática");
                pstmt.setDouble(3, 40.00);
                pstmt.setString(4, "Cena");
                pstmt.addBatch();

                pstmt.setString(1, "Carlos Moreno");
                pstmt.setString(2, "Ventas");
                pstmt.setDouble(3, 45.00);
                pstmt.setString(4, "Hospedaje");
                pstmt.addBatch();

                pstmt.setString(1, "Elena García");
                pstmt.setString(2, "Recursos Humanos");
                pstmt.setDouble(3, 15.00);
                pstmt.setString(4, "Transporte");
                pstmt.addBatch();

                pstmt.setString(1, "Alex Lopez");
                pstmt.setString(2, "Informática");
                pstmt.setDouble(3, 60.00);
                pstmt.setString(4, "Desayuno");
                pstmt.addBatch();

                pstmt.setString(1, "Laura Navarro");
                pstmt.setString(2, "Ventas");
                pstmt.setDouble(3, 30.00);
                pstmt.setString(4, "Comida");
                pstmt.addBatch();

                pstmt.setString(1, "Antuan Pozuelo");
                pstmt.setString(2, "Informática");
                pstmt.setDouble(3, 33.00);
                pstmt.setString(4, "Hospedaje");
                pstmt.addBatch();

                pstmt.setString(1, "Sara Torres");
                pstmt.setString(2, "Recursos Humanos");
                pstmt.setDouble(3, 21.00);
                pstmt.setString(4, "Cena");
                pstmt.addBatch();

                pstmt.executeBatch();
                conn.commit();
                System.out.println("Datos insertados en la tabla Dietas.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void mostrarDietasInformaticosMayoresDe30() {
            String query = "SELECT * FROM Dietas WHERE departamento = 'Informática' AND cantidad > 30";

            try (Connection conn = conexion.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query);
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String empleado = rs.getString("empleado");
                    String departamento = rs.getString("departamento");
                    double cantidad = rs.getDouble("cantidad");
                    String concepto = rs.getString("concepto");
                    System.out.printf("ID: %d, Empleado: %s, Departamento: %s, Cantidad: %.2f, Concepto: %s%n", id, empleado, departamento, cantidad, concepto);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void incrementarDietasVentas() {
            String updateQuery = "UPDATE Dietas SET cantidad = cantidad * 1.10 WHERE departamento = 'Ventas'";

            try (Connection conn = conexion.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {

                int rowsAffected = pstmt.executeUpdate();
                System.out.printf("Dietas actualizadas: %d%n", rowsAffected);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) {
            dietas manager = new dietas();

            manager.crearTablaDietas();
            manager.insertarDietas();

            System.out.println("Dietas de Informática mayores de 30€:");
            manager.mostrarDietasInformaticosMayoresDe30();

            System.out.println("\nIncrementando dietas del departamento de Ventas en un 10%...");
            manager.incrementarDietasVentas();

            System.out.println("Consulta de dietas actualizadas:");
            manager.mostrarDietasInformaticosMayoresDe30();
        }
    }

}
