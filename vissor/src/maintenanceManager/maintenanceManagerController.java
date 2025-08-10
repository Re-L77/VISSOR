package maintenanceManager;

import java.net.URL;
import java.util.ResourceBundle;

import admin.admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import productionManager.productionManager;

public class maintenanceManagerController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane users;
    @FXML
    private Pane usersAccess;
    @FXML
    private Pane usersRoles;
    @FXML
    private MenuButton menuButton;

    @FXML
    private void mostrarUsers(ActionEvent event) {
        // Oculta todos los paneles
        users.setVisible(false);
        usersAccess.setVisible(false);
        usersRoles.setVisible(false);

        // Y luego muestra el que te interesa
        users.setVisible(true);
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
    private void mostrarUsersRoles(ActionEvent event) {
        // Oculta todos los paneles
        users.setVisible(false);
        usersAccess.setVisible(false);
        usersRoles.setVisible(false);

        // Y luego muestra el que te interesa
        usersRoles.setVisible(true);
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
                case "Production Manager":
                    productionManager productionManager = new productionManager();
                    productionManager.start(new Stage());
                    break;
                // case "Maintenance Manager":
                // maintenanceManager maintenanceManager = new maintenanceManager();
                // maintenanceManager.start(new Stage());
                // break;
                // case "Analyst":
                // analyst analyst = new analyst();
                // analyst.start(new Stage());
                // break;
                // case "Maintenance Technician":
                // maintenanceTechnician maintenanceTechnician = new maintenanceTechnician();
                // maintenanceTechnician.start(new Stage());
                // break;
                // case "Operator":
                // operator operator = new operator();
                // operator.start(new Stage());
                // break;
                default:
                    // Acción por defecto si no coincide ningún caso
                    System.out.println("No se encontró ninguna coincidencia para: " + menuText);
                    break;
            }
        }
    }

    @FXML
    void initialize() {
        // Aquí puedes poner la lógica de inicio, por ejemplo,
        // asegurarte de que solo el primer panel es visible al principio.
        // handleUsersButtonAction();
    }
}