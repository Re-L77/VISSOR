package admin.bd;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Users {
    private final SimpleIntegerProperty idUsuario;
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty correo;
    private final SimpleStringProperty contrasena;
    private final SimpleIntegerProperty idRol;
    private final SimpleStringProperty activo; // Puedes usar SimpleBooleanProperty si es un boolean

    public Users(int idUsuario, String nombre, String correo, String contrasena, int idRol, String activo) {
        this.idUsuario = new SimpleIntegerProperty(idUsuario);
        this.nombre = new SimpleStringProperty(nombre);
        this.correo = new SimpleStringProperty(correo);
        this.contrasena = new SimpleStringProperty(contrasena);
        this.idRol = new SimpleIntegerProperty(idRol);
        this.activo = new SimpleStringProperty(activo);
    }

    // Aquí van los getters para todas las propiedades.
    // JavaFX los necesita para enlazar los datos.
    public SimpleIntegerProperty idUsuarioProperty() {
        return idUsuario;
    }

    public SimpleStringProperty nombreProperty() {
        return nombre;
    }

    public SimpleStringProperty correoProperty() {
        return correo;
    }

    public SimpleStringProperty contrasenaProperty() {
        return contrasena;
    }

    public SimpleIntegerProperty idRolProperty() {
        return idRol;
    }

    public SimpleStringProperty activoProperty() {
        return activo;
    }
}
