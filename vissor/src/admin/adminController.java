package admin;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

// import com.mysql.cj.xdevapi.Statement;
import java.sql.Statement;

import admin.bd.Users;
import admin.bd.conex;
import analyst.Analyst;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import maintenanceManager.maintenanceManager;
import maintenanceTechnician.maintenanceTechnician;
import operator.operator;
import productionManager.productionManager;

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
    private MenuButton menuButton;

    // insert
    @FXML
    private TextField name;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private MenuButton role;

    @FXML
    private CheckBox active;

    @FXML
    private DatePicker creation;

    @FXML
    private Button testButton;

    @FXML
    private TableView<Users> tableViewUsuarios;

    @FXML
    private TableColumn<Users, Integer> id_usuario;
    @FXML
    private TableColumn<Users, String> nombre;
    @FXML
    private TableColumn<Users, String> correo;
    @FXML
    private TableColumn<Users, String> contrasena;
    @FXML
    private TableColumn<Users, Integer> id_rol;
    @FXML
    private TableColumn<Users, String> activo;
    @FXML
    private TableColumn<Users, String> creado_en;

    @FXML
    void initialize() {
        // Aquí puedes poner la lógica de inicio, por ejemplo,
        // asegurarte de que solo el primer panel es visible al principio.
        users.setVisible(true);
        usersAccess.setVisible(false);
        usersRoles.setVisible(false);

        id_usuario.setCellValueFactory(new PropertyValueFactory<>("id_usuario"));
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        correo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        contrasena.setCellValueFactory(new PropertyValueFactory<>("contrasena"));
        id_rol.setCellValueFactory(new PropertyValueFactory<>("id_rol"));
        activo.setCellValueFactory(new PropertyValueFactory<>("activo"));
        creado_en.setCellValueFactory(new PropertyValueFactory<>("creado_en"));

        cargarUsuarios();
    }

    @FXML
    private void insertarUsuario(ActionEvent event) {
        // Aquí puedes implementar la lógica para insertar un nuevo usuario
        // Por ejemplo, abrir un diálogo para ingresar los datos del usuario
        // y luego llamar al método insertarUsuario de la clase Conex.
        // Conex.insertarUsuario(nombre, correo, contrasena, id_rol, activo, creado_en);
        cargarUsuarios(); // Recargar la lista de usuarios después de insertar
    }

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
    private void cambiarRol(ActionEvent event) {
        MenuItem item = (MenuItem) event.getSource();
        role.setText(item.getText());
    }

    private void cargarUsuarios() {
        ObservableList<Users> listaUsuarios = FXCollections.observableArrayList();
        String sql = "SELECT * FROM USUARIOS";

        try (Connection conn = conex.dataSource.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Users usuario = new Users(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("contrasena"),
                        rs.getInt("id_rol"),
                        rs.getString("activo"),
                        rs.getString("creado_en"));
                listaUsuarios.add(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Asigna la lista de usuarios a la TableView
        tableViewUsuarios.setItems(listaUsuarios);
    }

    private int getIdRolFromText(String rolText) {
        switch (rolText) {
            case "Admin":
                return 1;
            case "Production Manager":
                return 3;
            case "Maintenance Manager":
                return 4;
            case "Analyst":
                return 5;
            case "Maintenance Technician":
                return 2;
            case "Operator":
                return 6;
            default:
                return 1; // o algún valor por defecto
        }
    }
}