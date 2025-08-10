package admin.bd;

import java.sql.*;

public class TestBD {
    // nombre del driver JDBC y URL de base de datos
    static final String DB_URL = "jdbc:mysql://localhost:3306/VISSOR";
    // credenciales de base de datos
    static final String NOMBRE = "root";
    static final String PASSW = "rootpass"; // MAMP le pone por default esta al root

    static Connection con = null;
    static Statement stmtt = null;
    static ResultSet rts = null;

    public static void main(String[] args) {

        try {
            // Paso 3: Abrir una conexión
            System.out.println("Conectando a la base de datos ...");
            con = DriverManager.getConnection(DB_URL, NOMBRE, PASSW);
            // Paso 4: Ejecutar una consulta
            System.out.println("Creando un Statement...");
            stmtt = con.createStatement();
            String sql = "SELECT `id_usuario`, `nombre`, `correo`, `contrasena`, `id_rol`, `activo` FROM `USUARIOS`";
            rts = stmtt.executeQuery(sql);
            // paso 5: extraer datos del ResultSet
            String nombre, correo, contrasena;
            int id_usuario, id_rol;
            boolean activo;
            while (rts.next()) {
                // Retornar datos por nombres de columnas
                id_usuario = rts.getInt("id_usuario");
                nombre = rts.getString("nombre");
                correo = rts.getString("correo");
                contrasena = rts.getString("contrasena");
                id_rol = rts.getInt("id_rol");
                activo = rts.getBoolean("activo");
                // Imprimir valores
                System.out.print("ID USUARIO: " + id_usuario);
                System.out.print(", NOMBRE: " + nombre);
                System.out.print(", CORREO: " + correo);
                System.out.print(", CONTRASENA: " + contrasena);
                System.out.print(", ID ROL: " + id_rol);
                System.out.println(", ACTIVO: " + activo);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            // Step 6: Limpiar el entorno
            try {
                rts.close();
                if (stmtt != null) {
                    stmtt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al intentar liberar recursos");
            }
        }
        System.out.println("Goodbye!");
    }
}