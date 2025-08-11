package analyst;

import javafx.beans.property.*;

public class Reporte {
    private final StringProperty fecha;
    private final FloatProperty eficiencia;
    private final FloatProperty scrap;

    public Reporte(String fecha, float eficiencia, float scrap) {
        this.fecha = new SimpleStringProperty(fecha);
        this.eficiencia = new SimpleFloatProperty(eficiencia);
        this.scrap = new SimpleFloatProperty(scrap);
    }

    public StringProperty fechaProperty() { return fecha; }
    public FloatProperty eficienciaProperty() { return eficiencia; }
    public FloatProperty scrapProperty() { return scrap; }
}
