package analyst;

import java.time.LocalDate;
import javafx.beans.property.*;

public class ReporteProduccion {
    private ObjectProperty<LocalDate> fecha;
    private FloatProperty eficiencia;
    private FloatProperty scrap;

    public ReporteProduccion(LocalDate fecha, float eficiencia, float scrap) {
        this.fecha = new SimpleObjectProperty<>(fecha);
        this.eficiencia = new SimpleFloatProperty(eficiencia);
        this.scrap = new SimpleFloatProperty(scrap);
    }

    public LocalDate getFecha() { return fecha.get(); }
    public void setFecha(LocalDate fecha) { this.fecha.set(fecha); }
    public ObjectProperty<LocalDate> fechaProperty() { return fecha; }

    public float getEficiencia() { return eficiencia.get(); }
    public void setEficiencia(float eficiencia) { this.eficiencia.set(eficiencia); }
    public FloatProperty eficienciaProperty() { return eficiencia; }

    public float getScrap() { return scrap.get(); }
    public void setScrap(float scrap) { this.scrap.set(scrap); }
    public FloatProperty scrapProperty() { return scrap; }
}
