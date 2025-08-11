package productionManager.bd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class AccesoSimuladoDAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3308/VISSOR";
    private static final String USER = "root";
    private static final String PASS = "rootpass";

    public static ObservableList<AccesoSimulado> obtenerAccesosSimulados(String filtroUsuario) {
        ObservableList<AccesoSimulado> lista = FXCollections.observableArrayList();
        String query = "SELECT generado_por AS idUsuario, fecha, COUNT(*) AS cantidadReportes FROM REPORTES_PRODUCCION " +
                (filtroUsuario != null && !filtroUsuario.isEmpty() ? "WHERE generado_por = ? " : "") +
                "GROUP BY generado_por, fecha ORDER BY generado_por, fecha";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            if (filtroUsuario != null && !filtroUsuario.isEmpty()) {
                stmt.setInt(1, Integer.parseInt(filtroUsuario));
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    AccesoSimulado acceso = new AccesoSimulado(
                        rs.getInt("idUsuario"),
                        rs.getString("fecha"),
                        rs.getInt("cantidadReportes")
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
