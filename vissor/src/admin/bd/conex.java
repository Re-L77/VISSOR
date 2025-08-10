package admin.bd;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

public class conex {
    public static final HikariDataSource dataSource = crearDataSource();

    public static void main(String[] args) {

        // Insertar un nuevo usuario
        insertarUsuario("Juan", "juan@example.com", "password123", 1, true);
        // Mostrar tabla USUARIOS
        mostrarTabla("USUARIOS");
        dataSource.close();
        System.out.println("Finalizado.");
    }

    private static void insertarUsuario(String nombre, String correo, String contrasena, int idRol, boolean activo) {
        String sql = "INSERT INTO `USUARIOS` (`id_usuario`, `nombre`, `correo`, `contrasena`, `id_rol`, `activo`) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, 0); // id_usuario (auto_increment)
            pstmt.setString(2, nombre);
            pstmt.setString(3, correo);
            pstmt.setString(4, contrasena);
            pstmt.setInt(5, idRol);
            pstmt.setBoolean(6, activo);

            int filas = pstmt.executeUpdate();
            System.out.println("Insertado usuario: " + nombre + " (" + filas + " fila afectada)");

        } catch (SQLException e) {
            System.err.println("Error insertando usuario");
            e.printStackTrace();
        }
    }

    private static void mostrarTabla(String nombreTabla) {
        String sql = "SELECT * FROM " + nombreTabla;
        try (Connection conn = dataSource.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Contenido de la tabla " + nombreTabla + ":");
            ResultSetMetaData meta = rs.getMetaData();
            int columnas = meta.getColumnCount();

            for (int i = 1; i <= columnas; i++) {
                System.out.print(meta.getColumnName(i) + "\t");
            }
            System.out.println();

            while (rs.next()) {
                for (int i = 1; i <= columnas; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }

        } catch (SQLException e) {
            System.err.println("Error mostrando tabla " + nombreTabla);
            e.printStackTrace();
        }
    }

    private static HikariDataSource crearDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/VISSOR");
        config.setUsername("root");
        config.setPassword("rootpass");
        config.setMaximumPoolSize(5);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(config);
    }
}
