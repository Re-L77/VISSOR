package analyst;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class controladorjava {

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    public void initialize() {
        // Crear series de datos
        XYChart.Series<String, Number> eficienciaSeries = new XYChart.Series<>();
        eficienciaSeries.setName("Eficiencia");

        XYChart.Series<String, Number> scrapSeries = new XYChart.Series<>();
        scrapSeries.setName("Scrap");

        // Datos simulados (fechas y valores)
        eficienciaSeries.getData().add(new XYChart.Data<>("2025-08-01", 75));
        eficienciaSeries.getData().add(new XYChart.Data<>("2025-08-02", 80));
        eficienciaSeries.getData().add(new XYChart.Data<>("2025-08-03", 78));
        eficienciaSeries.getData().add(new XYChart.Data<>("2025-08-04", 85));
        eficienciaSeries.getData().add(new XYChart.Data<>("2025-08-05", 90));

        scrapSeries.getData().add(new XYChart.Data<>("2025-08-01", 10));
        scrapSeries.getData().add(new XYChart.Data<>("2025-08-02", 12));
        scrapSeries.getData().add(new XYChart.Data<>("2025-08-03", 8));
        scrapSeries.getData().add(new XYChart.Data<>("2025-08-04", 9));
        scrapSeries.getData().add(new XYChart.Data<>("2025-08-05", 7));

        // Añadir las series a la gráfica
        lineChart.getData().addAll(eficienciaSeries, scrapSeries);
    }
}
