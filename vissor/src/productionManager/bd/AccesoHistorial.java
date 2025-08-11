package productionManager.bd;

public class AccesoHistorial {
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
