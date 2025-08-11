package productionManager.bd;

public class AccesoSimulado {
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
