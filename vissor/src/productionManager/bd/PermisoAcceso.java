package productionManager.bd;

public class PermisoAcceso {
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
