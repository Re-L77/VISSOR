package productionManager.bd;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class conexProduction {
    // Modelo
    private int idReporte;
    private String fecha;
    private double eficiencia;
    private double scrap;
    private int generadoPor;

    public conexProduction(int idReporte, String fecha, double eficiencia, double scrap, int generadoPor) {
        this.idReporte = idReporte;
        this.fecha = fecha;
        this.eficiencia = eficiencia;
        this.scrap = scrap;
        this.generadoPor = generadoPor;
    }

    public int getIdReporte() { return idReporte; }
    public String getFecha() { return fecha; }
    public double getEficiencia() { return eficiencia; }
    public double getScrap() { return scrap; }
    public int getGeneradoPor() { return generadoPor; }

    // Conexión
    private static final HikariDataSource dataSource = crearDataSource();

    private static HikariDataSource crearDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3308/VISSOR");
        config.setUsername("root");
        config.setPassword("rootpass");
        config.setMaximumPoolSize(5);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(config);
    }

    // Método para obtener los reportes de producción
    public static ObservableList<conexProduction> obtenerReportesProduccion() {
        ObservableList<conexProduction> lista = FXCollections.observableArrayList();
        String query = "SELECT id_reporte, fecha, eficiencia, scrap, generado_por FROM REPORTES_PRODUCCION";
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                conexProduction reporte = new conexProduction(
                    rs.getInt("id_reporte"),
                    rs.getString("fecha"),
                    rs.getDouble("eficiencia"),
                    rs.getDouble("scrap"),
                    rs.getInt("generado_por")
                );
                System.out.println("Reporte: " + reporte.getIdReporte() + ", " + reporte.getFecha() + ", " + reporte.getEficiencia() + ", " + reporte.getScrap() + ", " + reporte.getGeneradoPor());
                lista.add(reporte);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    // Método para obtener reportes filtrados por generado_por (ID)
    public static ObservableList<conexProduction> obtenerReportesPorGeneradoPor(int generadoPorId) {
        ObservableList<conexProduction> lista = FXCollections.observableArrayList();
        String query = "SELECT id_reporte, fecha, eficiencia, scrap, generado_por FROM REPORTES_PRODUCCION WHERE generado_por = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, generadoPorId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    conexProduction reporte = new conexProduction(
                        rs.getInt("id_reporte"),
                        rs.getString("fecha"),
                        rs.getDouble("eficiencia"),
                        rs.getDouble("scrap"),
                        rs.getInt("generado_por")
                    );
                    lista.add(reporte);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
