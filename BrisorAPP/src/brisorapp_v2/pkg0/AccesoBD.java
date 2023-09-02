/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brisorapp_v2.pkg0;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ElRonas-Mobile
 */
public class AccesoBD {
     private Connection Conectar = null;
    private final String Usuario="root";
    private final String Contrasena="Ronny2003";
    private final String bd = "sodabar_brisor";
    private final String ip = "localhost";
    private final String Puerto = "3306";

    private final String Cadena = "jdbc:mysql://" + ip + ":" + Puerto + "/" + bd;
    public Connection Establecer_conexion() {
        try {                                                                                    
            Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();       /*1.ta*/
            DriverManager.registerDriver(driver);                                                /*2.tc*/
            Conectar = DriverManager.getConnection(Cadena, Usuario, Contrasena);                 /*3.ta*/
            System.out.println("Se conecto correctamente");                                      

        } catch (Exception e) {                                                                  
            System.out.println("No se realizo la conexion, Error: " + e.toString());             /*4.to*/
        }                                                                                        
        return Conectar;
    }
                                                                                                 /*Tiempo Esperado: ta + tc + ta + to = 2ta+tc+to*/
    public void actualizarBD(String Sql) {
        try {
            Statement stm=Conectar.createStatement();                                            /*1.ta*/
            stm.executeUpdate(Sql);                                                              /*2.tc*/
            System.out.println("Transaccion exitosa");
        } catch (Exception e) {
            System.out.println("Error: "+e.toString());
        }
    }
                                                                                                 /*Tiempo Esperado: ta + tc*/
    public ResultSet Consulta(String sql) throws SQLException {
        Statement stm=Conectar.createStatement();                                                /*1.ta*/
        ResultSet cursor;
        cursor=stm.executeQuery(sql);                                                            /*2.ta*/
        return cursor;
    }
                                                                                                 /*Tiempo Esperado: 2ta*/
    
    public void CerrarBD() throws SQLException {
        Conectar.close();
    }
}

/*Tiempo esperado = 2ta+tc+to+ta+tc+2ta = 5ta+2tc+to*/