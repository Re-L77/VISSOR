package productionManager.bd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class AccesoHistorialDAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3308/VISSOR";
    private static final String USER = "root";
    private static final String PASS = "rootpass";

    public static ObservableList<AccesoHistorial> obtenerAccesos(String usuario, String fecha) {
        ObservableList<AccesoHistorial> lista = FXCollections.observableArrayList();
        StringBuilder query = new StringBuilder("SELECT usuario, fecha_acceso, accion FROM HISTORIAL_ACCESOS WHERE 1=1");
        if (usuario != null && !usuario.isEmpty()) {
            query.append(" AND usuario LIKE ?");
        }
        if (fecha != null && !fecha.isEmpty()) {
            query.append(" AND DATE(fecha_acceso) = ?");
        }
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query.toString())) {
            int idx = 1;
            if (usuario != null && !usuario.isEmpty()) {
                stmt.setString(idx++, "%" + usuario + "%");
            }
            if (fecha != null && !fecha.isEmpty()) {
                stmt.setString(idx++, fecha);
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    AccesoHistorial acceso = new AccesoHistorial(
                        rs.getString("usuario"),
                        rs.getString("fecha_acceso"),
                        rs.getString("accion")
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
