package admin.bd;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.*;

public class Conex {
    public static final HikariDataSource dataSource = crearDataSource();

    public static void insertarUsuario(String nombre, String correo, String contrasena, int id_rol, Boolean activo,
            String creado_en) {
        String sql = "INSERT IGNORE INTO `USUARIOS` (`nombre`, `correo`, `contrasena`, `id_rol`, `activo`, `creado_en`) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            pstmt.setString(2, correo);
            pstmt.setString(3, contrasena);
            pstmt.setInt(4, id_rol);
            pstmt.setBoolean(5, activo);
            pstmt.setString(6, creado_en);

            int filas = pstmt.executeUpdate();
            if (filas == 0) {
                // Tu código para la alerta
                Alert alert = new Alert(AlertType.ERROR);

                // Configurar el título de la ventana
                alert.setTitle("ERROR");

                // Configurar el texto del encabezado (lo que aparece en grande)
                // Puedes dejarlo en null para que solo se muestre el ContentText, o poner un
                // mensaje más claro
                alert.setHeaderText(null);

                // Configurar el texto del contenido (el mensaje detallado)
                alert.setContentText("Datos duplicados: " + "[" + nombre + "] [" + correo + "]");

                // Mostrar la alerta
                alert.showAndWait();
            } else {
                System.out.println("Insertado usuario: " + nombre + " (" + filas + " fila afectada)");
            }

        } catch (SQLException e) {
            System.err.println("Error insertando usuario");
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
