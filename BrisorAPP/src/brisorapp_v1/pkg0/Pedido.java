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
        AccesoBD BD = new AccesoBD();
        ResultSet ID = BD.Consulta("select IDC from tbl_cliente where nombre= '"+nombre+"' and apellido = '"+apellido+"';");
        while(ID.next()){
            IDCliente=ID.getInt(1);
        }
    }
    
}
