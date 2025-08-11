package productionManager.bd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.*;

public class ProductionAll {
    public static class AccesoHistorial {
        private String usuario;
        private String fechaAcceso;
        private String accionAcceso;
        public AccesoHistorial(String usuario, String fechaAcceso, String accionAcceso) {
            this.usuario = usuario;
            this.fechaAcceso = fechaAcceso;
            this.accionAcceso = accionAcceso;
        }
        public String getUsuario() { return usuario; }
        public void setUsuario(String usuario) { this.usuario = usuario; }
        public String getFechaAcceso() { return fechaAcceso; }
        public void setFechaAcceso(String fechaAcceso) { this.fechaAcceso = fechaAcceso; }
        public String getAccionAcceso() { return accionAcceso; }
        public void setAccionAcceso(String accionAcceso) { this.accionAcceso = accionAcceso; }
    }

    public static class AccesoSimulado {
        private int idUsuario;
        private String fecha;
        private int cantidadReportes;
        public AccesoSimulado(int idUsuario, String fecha, int cantidadReportes) {
            this.idUsuario = idUsuario;
            this.fecha = fecha;
            this.cantidadReportes = cantidadReportes;
        }
        public int getIdUsuario() { return idUsuario; }
        public String getFecha() { return fecha; }
        public int getCantidadReportes() { return cantidadReportes; }
    }

    public static class PermisoAcceso {
        private String usuario;
        private String permiso;
        private String recurso;
        public PermisoAcceso(String usuario, String permiso, String recurso) {
            this.usuario = usuario;
            this.permiso = permiso;
            this.recurso = recurso;
        }
        public String getUsuario() { return usuario; }
        public void setUsuario(String usuario) { this.usuario = usuario; }
        public String getPermiso() { return permiso; }
        public void setPermiso(String permiso) { this.permiso = permiso; }
        public String getRecurso() { return recurso; }
        public void setRecurso(String recurso) { this.recurso = recurso; }
    }

    public static class conexProduction {
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
    }

    private static final String JDBC_URL = "jdbc:mysql://localhost:3308/VISSOR";
    private static final String USER = "root";
    private static final String PASS = "rootpass";
    private static final HikariDataSource dataSource = crearDataSource();
    private static HikariDataSource crearDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(JDBC_URL);
        config.setUsername(USER);
        config.setPassword(PASS);
        config.setMaximumPoolSize(5);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(config);
    }

    public static ObservableList<AccesoHistorial> obtenerAccesos(String usuario, String fecha) {
        ObservableList<AccesoHistorial> lista = FXCollections.observableArrayList();
        StringBuilder query = new StringBuilder("SELECT usuario, fecha_acceso, accion FROM HISTORIAL_ACCESOS WHERE 1=1");
        if (usuario != null && !usuario.isEmpty()) {
            query.append(" AND usuario LIKE ?");
        }
        if (fecha != null && !fecha.isEmpty()) {
            query.append(" AND DATE(fecha_acceso) = ?");
        }
        try (Connection conn = dataSource.getConnection();
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

    public static ObservableList<AccesoSimulado> obtenerAccesosSimulados(String filtroUsuario) {
        ObservableList<AccesoSimulado> lista = FXCollections.observableArrayList();
        String query = "SELECT generado_por AS idUsuario, fecha, COUNT(*) AS cantidadReportes FROM REPORTES_PRODUCCION " +
                (filtroUsuario != null && !filtroUsuario.isEmpty() ? "WHERE generado_por = ? " : "") +
                "GROUP BY generado_por, fecha ORDER BY generado_por, fecha";
        try (Connection conn = dataSource.getConnection();
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

    // PermisoAccesoDAO
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
        try (Connection conn = dataSource.getConnection();
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
                lista.add(reporte);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
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
    public static ObservableList<conexProduction> obtenerReportesPorFecha(String fecha) {
        ObservableList<conexProduction> lista = FXCollections.observableArrayList();
        String query = "SELECT id_reporte, fecha, eficiencia, scrap, generado_por FROM REPORTES_PRODUCCION WHERE fecha >= ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, fecha);
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
