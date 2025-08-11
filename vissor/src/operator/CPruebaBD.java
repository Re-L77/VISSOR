package operator;

import java.sql.*;
import javax.swing.JOptionPane; 

public class CPruebaBD {
    static final String DB_URL = "jdbc:mysql://localhost/vissor";
    static final String NOMBRE = "root";
    static final String PASSW = "root";

    static Connection con = null;
    static Statement stmtt = null;
    static ResultSet rts = null;

    public static void mostrarInfo() {
        int id_plc;
        String modelo, ubicacion, ip;
        boolean activo;
        
        System.out.println("---------------");
        System.out.println("ID_PLC | MODELO | UBICACION | IP | ACTIVO");
        
        try {
            while (rts.next()) {
                id_plc = rts.getInt("id_plc");
                modelo = rts.getString("modelo");
                ubicacion = rts.getString("ubicacion");
                ip = rts.getString("ip");
                activo = rts.getBoolean("activo");
                
                System.out.print("ID_PLC: " + id_plc);
                System.out.print(", MODELO: " + modelo);
                System.out.print(", UBICACION: " + ubicacion);
                System.out.print(", IP: " + ip);
                System.out.println(", ACTIVO: " + (activo ? "Sí" : "No"));

                
                if (activo) {
                    JOptionPane.showMessageDialog(
                        null, 
                        "Posible falla detectada en el PLC " + id_plc + ". Se requiere una revisión inmediata.",
                        "Alerta de Falla",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("Conectando a la base de datos VISSOR...");
            con = DriverManager.getConnection(DB_URL, NOMBRE, PASSW);
            System.out.println("Creando un Statement...");
            stmtt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT id_plc, modelo, ubicacion, ip, activo FROM PLCS";
            rts = stmtt.executeQuery(sql);

            mostrarInfo();

            rts.close();
            stmtt.close();
            con.close();
            
        } catch (SQLException se) {
            se.printStackTrace();
        }

        System.out.println("Se terminó el programa");
    }
}