/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brisorapp_v2.pkg0;

import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author ElRonas-Mobile
 */
public class Pedido {
    private int IDCliente;

    public int getIDCliente() {
        return IDCliente;
    }

    public void setIDCliente(String apellido, String nombre) throws SQLException {
        AccesoBD BD = new AccesoBD();                                                                                               /*1.ta*/
        ResultSet ID = BD.Consulta("select IDC from tbl_cliente where nombre= '"+nombre+"' and apellido = '"+apellido+"';");        /*2.ta*/
        while(ID.next()){                                                                                                           /*3.tc*/
            IDCliente=ID.getInt(1);                                                                                                 /*4.ta*/
        }
    }
    /*Tiempo Mejor Esperado: ta+ta+tc = 2ta+tc*/
    /*Tiempo Peor Esperado: ta + tc + n*ta*/
    /*Tiempo Promedio: (2ta+tc)+(ta+tc+n*ta)/2*/
}
