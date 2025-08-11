package productionManager;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import admin.admin;
import analyst.Analyst;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import productionManager.bd.conexProduction;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import maintenanceManager.maintenanceManager;
import maintenanceTechnician.maintenanceTechnician;
import operator.operator;
import productionManager.bd.AccesoSimulado;
import productionManager.bd.AccesoSimuladoDAO;
import javafx.scene.control.cell.PropertyValueFactory;

public class productionManagerController {
    @FXML
    private TableColumn<conexProduction, Integer> colIdFiltrado;
    @FXML
    private TableColumn<conexProduction, String> colFechaFiltrado;
    @FXML
    private TableColumn<conexProduction, Double> colEficienciaFiltrado;
    @FXML
    private Pane users;
    @FXML
    private TableView<conexProduction> tablaReportes;
    @FXML
    private TableColumn<conexProduction, Integer> colId;
    @FXML
    private TableColumn<conexProduction, String> colFecha;
    @FXML
    private TableColumn<conexProduction, Double> colEficiencia;
    @FXML
    private TableColumn<conexProduction, Double> colScrap;
    @FXML
    private TableColumn<conexProduction, Integer> colGeneradoPor;
    @FXML
    private TextField txtGeneradoPor;
    @FXML
    private Button btnFiltrar;
    @FXML
    private TableView<conexProduction> tablaFiltross;
    @FXML
    private TableColumn<conexProduction, Double> colScrapFiltrado;
    @FXML
    private TableColumn<conexProduction, Integer> colGeneradoPorFiltrado;
    @FXML
    private Pane usersRoles;
    @FXML
    private Pane usersAccess;
    @FXML
    private MenuButton filtersMenuButton;
    @FXML
    private TableView<AccesoSimulado> tablaAccesosSimulados;
    @FXML
    private TableColumn<AccesoSimulado, Integer> colUsuarioAcceso;
    @FXML
    private TableColumn<AccesoSimulado, String> colFechaAcceso;
    @FXML
    private TableColumn<AccesoSimulado, Integer> colCantidadReportes;
    @FXML
    private TextField txtFiltroUsuarioAcceso;
    @FXML
    private Button btnFiltrarUsuarioAcceso;

    @FXML
    private void filtrarPorGeneradoPor(ActionEvent event) {
        String idText = txtGeneradoPor.getText();
        if (idText == null || idText.isEmpty()) {
            tablaFiltross.setItems(FXCollections.observableArrayList());
            return;
        }
        try {
            int id = Integer.parseInt(idText);
            ObservableList<conexProduction> filteredList = conexProduction.obtenerReportesPorGeneradoPor(id);
            tablaFiltross.setItems(filteredList);
        } catch (NumberFormatException e) {
            tablaFiltross.setItems(FXCollections.observableArrayList());
        }
    }

    @FXML
    private void mostrarUsers(ActionEvent event) {
        // Oculta todos los paneles
        users.setVisible(false);
        usersRoles.setVisible(false);
        usersAccess.setVisible(false);

        // Y luego muestra el que te interesa
        users.setVisible(true);
    }

    @FXML
    private void mostrarUsersRoles(ActionEvent event) {
        // Oculta todos los paneles
        users.setVisible(false);
        usersRoles.setVisible(false);
        usersAccess.setVisible(false);

        // Y luego muestra el que te interesa
        usersRoles.setVisible(true);
    }

    @FXML
    private void mostrarUsersAccess(ActionEvent event) {
        // Oculta todos los paneles
        users.setVisible(false);
        usersAccess.setVisible(false);
        usersRoles.setVisible(false);

        // Y luego muestra el que te interesa
        usersAccess.setVisible(true);
    }

    @FXML
    private void cambiarPagina(ActionEvent event) {
        if (event.getSource() instanceof javafx.scene.control.MenuItem) {
            javafx.scene.control.MenuItem item = (javafx.scene.control.MenuItem) event.getSource();
            String menuText = item.getText();
            System.out.println("Opción seleccionada: " + menuText);

            // Cierra la ventana actual
            if (users != null && users.getScene() != null) {
                Stage stage = (Stage) users.getScene().getWindow();
                stage.close();
            }

            switch (menuText) {
                case "Admin":
                    admin App = new admin();
                    App.start(new Stage());
                    break;
                case "Maintenance Manager":
                    maintenanceManager maintenanceManager = new maintenanceManager();
                    maintenanceManager.start(new Stage());
                    break;
                case "Analyst":
                    Analyst analyst = new Analyst();
                    analyst.start(new Stage());
                    break;
                case "Maintenance Technician":
                    maintenanceTechnician maintenanceTechnician = new maintenanceTechnician();
                    maintenanceTechnician.start(new Stage());
                    break;
                case "Operator":
                    operator operator = new operator();
                    operator.start(new Stage());
                    break;
                default:
                    // Acción por defecto si no coincide ningún caso
                    System.out.println("No se encontró ninguna coincidencia para: " + menuText);
                    break;
            }
        }
    }

    @FXML
    private void handleFilterAction(ActionEvent event) {
        if (event.getSource() instanceof MenuItem) {
            MenuItem item = (MenuItem) event.getSource();
            String filter = item.getText();
            ObservableList<conexProduction> originalList = conexProduction.obtenerReportesProduccion();
            ObservableList<conexProduction> filteredList = FXCollections.observableArrayList();
            switch (filter) {
                case "Eficiencia > 80%":
                case "Eficiencia &gt; 80%":
                    for (conexProduction r : originalList) {
                        if (r.getEficiencia() > 80.0) filteredList.add(r);
                    }
                    break;
                case "Eficiencia < 80%":
                case "Eficiencia &lt; 80%":
                    for (conexProduction r : originalList) {
                        if (r.getEficiencia() < 80.0) filteredList.add(r);
                    }
                    break;
                case "Scrap < 10%":
                case "Scrap &lt; 10%":
                    for (conexProduction r : originalList) {
                        if (r.getScrap() < 10.0) filteredList.add(r);
                    }
                    break;
                case "Generado por ID = 1":
                    for (conexProduction r : originalList) {
                        if (r.getGeneradoPor() == 1) filteredList.add(r);
                    }
                    break;
                case "Todos":
                    filteredList = originalList;
                    break;
                default:
                    filteredList = originalList;
            }
            // Mostrar los resultados filtrados en la tablaFiltross
            if (tablaFiltross != null) {
                tablaFiltross.setItems(filteredList);
            }
        }
    }

    @FXML
    private void filtrarUsuarioAcceso(ActionEvent event) {
        String usuarioText = txtFiltroUsuarioAcceso.getText();
        ObservableList<AccesoSimulado> lista = AccesoSimuladoDAO.obtenerAccesosSimulados(usuarioText);
        tablaAccesosSimulados.setItems(lista);
    }

    @FXML
    public void initialize() {
        // Configurar columnas del TableView principal
        if (colId != null) colId.setCellValueFactory(new PropertyValueFactory<>("idReporte"));
        if (colFecha != null) colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        if (colEficiencia != null) colEficiencia.setCellValueFactory(new PropertyValueFactory<>("eficiencia"));
        if (colScrap != null) colScrap.setCellValueFactory(new PropertyValueFactory<>("scrap"));
        if (colGeneradoPor != null) colGeneradoPor.setCellValueFactory(new PropertyValueFactory<>("generadoPor"));

        // Configurar columnas de la tabla filtrada
        if (colIdFiltrado != null) colIdFiltrado.setCellValueFactory(new PropertyValueFactory<>("idReporte"));
        if (colFechaFiltrado != null) colFechaFiltrado.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        if (colEficienciaFiltrado != null) colEficienciaFiltrado.setCellValueFactory(new PropertyValueFactory<>("eficiencia"));
        if (colScrapFiltrado != null) colScrapFiltrado.setCellValueFactory(new PropertyValueFactory<>("scrap"));
        if (colGeneradoPorFiltrado != null) colGeneradoPorFiltrado.setCellValueFactory(new PropertyValueFactory<>("generadoPor"));

        // Llenar la tabla principal con todos los datos al iniciar
        if (tablaReportes != null) {
            tablaReportes.setItems(conexProduction.obtenerReportesProduccion());
        }
        // Asignar eventos a los filtros si existen
        if (filtersMenuButton != null) {
            for (MenuItem item : filtersMenuButton.getItems()) {
                item.setOnAction(this::handleFilterAction);
            }
        }
        if (colUsuarioAcceso != null) colUsuarioAcceso.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        if (colFechaAcceso != null) colFechaAcceso.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        if (colCantidadReportes != null) colCantidadReportes.setCellValueFactory(new PropertyValueFactory<>("cantidadReportes"));
        if (tablaAccesosSimulados != null) {
            tablaAccesosSimulados.setItems(AccesoSimuladoDAO.obtenerAccesosSimulados(""));
        }
    }
}