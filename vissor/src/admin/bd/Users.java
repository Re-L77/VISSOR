package admin.bd;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Users {
    private final SimpleIntegerProperty id_usuario;
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty correo;
    private final SimpleStringProperty contrasena;
    private final SimpleIntegerProperty id_rol;
    private final SimpleStringProperty activo; // Puedes usar SimpleBooleanProperty si es un boolean
    private final SimpleStringProperty creado_en; // Puedes usar SimpleBooleanProperty si es un boolean

    public Users(int id_usuario, String nombre, String correo, String contrasena, int id_rol, String activo,
            String creado_en) {
        this.id_usuario = new SimpleIntegerProperty(id_usuario);
        this.nombre = new SimpleStringProperty(nombre);
        this.correo = new SimpleStringProperty(correo);
        this.contrasena = new SimpleStringProperty(contrasena);
        this.id_rol = new SimpleIntegerProperty(id_rol);
        this.activo = new SimpleStringProperty(activo);
        this.creado_en = new SimpleStringProperty(creado_en);
    }

    // Aquí van los getters para todas las propiedades.
    // JavaFX los necesita para enlazar los datos.
    public SimpleIntegerProperty id_usuarioProperty() {
        return id_usuario;
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

    public SimpleIntegerProperty id_rolProperty() {
        return id_rol;
    }

    public SimpleStringProperty activoProperty() {
        return activo;
    }

    public SimpleStringProperty creado_enProperty() {
        return creado_en;
    }
}
