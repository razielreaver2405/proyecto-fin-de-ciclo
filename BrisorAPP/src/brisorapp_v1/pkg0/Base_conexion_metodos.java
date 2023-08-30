/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brisorapp_v1.pkg0;


import java.sql.*;

/**
 *
 * @author ElRonas-Mobile
 */
public class Base_conexion_metodos {
    private Connection Conectar = null;
    private String Usuario;
    private String Contrasena;
    private final String bd = "sodabar_brisor";
    private final String ip = "localhost";
    private final String Puerto = "3306";

    private final String Cadena = "jdbc:mysql://" + ip + ":" + Puerto + "/" + bd;
    public Connection Establecer_conexion() {
        try {
            Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
            DriverManager.registerDriver(driver);
            Conectar = DriverManager.getConnection(Cadena, Usuario, Contrasena);
            System.out.println("Se conecto correctamente");

        } catch (Exception e) {
            System.out.println("No se realizo la conexion, Error: " + e.toString());
        }
        return Conectar;
    }

    public void actualizarBD(String Sql) {
        try {
            Statement stm=Conectar.createStatement();
            stm.executeUpdate(Sql);
            System.out.println("Transaccion exitosa");
        } catch (Exception e) {
            System.out.println("Error: "+e.toString());
        }
    }

    public ResultSet Consulta(String sql) throws SQLException {
        Statement stm=Conectar.createStatement();
        ResultSet cursor;
        cursor=stm.executeQuery(sql);
        return cursor;
    }

    public void CerrarBD() throws SQLException {
        Conectar.close();
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }
    
    
}
