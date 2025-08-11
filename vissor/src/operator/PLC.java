package operator;

import javafx.beans.property.*;

public class PLC {
    private final IntegerProperty id_plc;
    private final StringProperty modelo;
    private final StringProperty ubicacion;
    private final StringProperty ip;
    private final BooleanProperty activo;

    public PLC(int id, String modelo, String ubicacion, String ip, boolean activo) {
        this.id_plc = new SimpleIntegerProperty(id);
        this.modelo = new SimpleStringProperty(modelo);
        this.ubicacion = new SimpleStringProperty(ubicacion);
        this.ip = new SimpleStringProperty(ip);
        this.activo = new SimpleBooleanProperty(activo);
    }

    public int getId_plc() { return id_plc.get(); }
    public IntegerProperty id_plcProperty() { return id_plc; }

    public String getModelo() { return modelo.get(); }
    public StringProperty modeloProperty() { return modelo; }

    public String getUbicacion() { return ubicacion.get(); }
    public StringProperty ubicacionProperty() { return ubicacion; }

    public String getIp() { return ip.get(); }
    public StringProperty ipProperty() { return ip; }

    public boolean isActivo() { return activo.get(); }
    public BooleanProperty activoProperty() { return activo; }
}

