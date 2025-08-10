import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class adminController {
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
    void initialize() {
        // Aquí puedes poner la lógica de inicio, por ejemplo,
        // asegurarte de que solo el primer panel es visible al principio.
        // handleUsersButtonAction();
    }
}