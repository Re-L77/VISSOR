package analyst;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class Analyst_Controller {

    // Paneles para mostrar/ocultar
    @FXML public Pane users;       // Panel gráficas
    @FXML public Pane usersAccess; // Panel generación de reportes
    @FXML public Pane usersRoles;  // Panel exportación
    

    @FXML public Button btnReportes;
    // Elementos de la gráfica
    @FXML public LineChart<String, Number> lineChart;

    // Elementos de la tabla para reportes
    @FXML public TableView<ReporteSimulado> tableReportes;
    @FXML public TableColumn<ReporteSimulado, String> colFecha;
    @FXML public TableColumn<ReporteSimulado, Float> colEficiencia;
    @FXML public TableColumn<ReporteSimulado, Float> colScrap;

    // Clase para representar datos simulados en tabla
    public static class ReporteSimulado {
        private final String fecha;
        private final Float eficiencia;
        private final Float scrap;

        public ReporteSimulado(String fecha, Float eficiencia, Float scrap) {
            this.fecha = fecha;
            this.eficiencia = eficiencia;
            this.scrap = scrap;
        }
        public String getFecha() { return fecha; }
        public Float getEficiencia() { return eficiencia; }
        public Float getScrap() { return scrap; }
    }

    @FXML
    public void initialize() {
        configurarTablaReportes();
        mostrarPanelGraficas(null); // Mostrar gráficas al iniciar
    }

    private void configurarTablaReportes() {
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colEficiencia.setCellValueFactory(new PropertyValueFactory<>("eficiencia"));
        colScrap.setCellValueFactory(new PropertyValueFactory<>("scrap"));
    }

    private void cargarGraficaSimulada() {
        lineChart.getData().clear();

        XYChart.Series<String, Number> eficienciaSeries = new XYChart.Series<>();
        eficienciaSeries.setName("Eficiencia");

        XYChart.Series<String, Number> scrapSeries = new XYChart.Series<>();
        scrapSeries.setName("Scrap");

        // Datos simulados
        String[] fechas = {"2025-08-01", "2025-08-02", "2025-08-03", "2025-08-04", "2025-08-05"};
        float[] eficiencias = {90.5f, 92.3f, 89.7f, 94.1f, 91.0f};
        float[] scraps = {2.5f, 1.9f, 2.8f, 1.5f, 2.0f};

        for (int i = 0; i < fechas.length; i++) {
            eficienciaSeries.getData().add(new XYChart.Data<>(fechas[i], eficiencias[i]));
            scrapSeries.getData().add(new XYChart.Data<>(fechas[i], scraps[i]));
        }

        lineChart.getData().addAll(eficienciaSeries, scrapSeries);
    }

    private void cargarDatosReportesSimulados() {
        ObservableList<ReporteSimulado> data = FXCollections.observableArrayList(
            new ReporteSimulado("2025-08-01", 90.5f, 2.5f),
            new ReporteSimulado("2025-08-02", 92.3f, 1.9f),
            new ReporteSimulado("2025-08-03", 89.7f, 2.8f),
            new ReporteSimulado("2025-08-04", 94.1f, 1.5f),
            new ReporteSimulado("2025-08-05", 91.0f, 2.0f)
        );
        tableReportes.setItems(data);
    }

    @FXML
    public void mostrarPanelGraficas(ActionEvent event) {
        users.setVisible(true);
        usersAccess.setVisible(false);
        usersRoles.setVisible(false);
        cargarGraficaSimulada();
    }

    @FXML
    public void abrirGeneracionReportes(ActionEvent event) {
        users.setVisible(false);
        usersAccess.setVisible(true);
        usersRoles.setVisible(false);
        cargarDatosReportesSimulados();
    }

    @FXML
    public void abrirExportacion(ActionEvent event) {
        users.setVisible(false);
        usersAccess.setVisible(false);
        usersRoles.setVisible(true);
    }

    @FXML
    public void generarReporte(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Reporte descargado con éxito.", ButtonType.OK);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @FXML
    public void exportarReporte(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Exportación realizada con éxito.", ButtonType.OK);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
