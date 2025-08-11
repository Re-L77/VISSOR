package productionManager.bd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class PermisoAccesoDAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3308/VISSOR";
    private static final String USER = "root";
    private static final String PASS = "rootpass";

    public static ObservableList<PermisoAcceso> obtenerPermisos(String usuario, String permiso) {
        ObservableList<PermisoAcceso> lista = FXCollections.observableArrayList();
        StringBuilder query = new StringBuilder(
            "SELECT u.nombre AS usuario, r.nombre AS permiso, p.recurso " +
            "FROM PERMISOS p " +
            "JOIN ROLES r ON p.id_rol = r.id_rol " +
            "JOIN USUARIOS u ON u.id_rol = r.id_rol WHERE 1=1"
        );
        if (usuario != null && !usuario.isEmpty()) {
            query.append(" AND u.nombre LIKE ?");
        }
        if (permiso != null && !permiso.isEmpty()) {
            query.append(" AND r.nombre LIKE ?");
        }
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query.toString())) {
            int idx = 1;
            if (usuario != null && !usuario.isEmpty()) {
                stmt.setString(idx++, "%" + usuario + "%");
            }
            if (permiso != null && !permiso.isEmpty()) {
                stmt.setString(idx++, "%" + permiso + "%");
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PermisoAcceso acceso = new PermisoAcceso(
                        rs.getString("usuario"),
                        rs.getString("permiso"),
                        rs.getString("recurso")
                    );
                    lista.add(acceso);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
