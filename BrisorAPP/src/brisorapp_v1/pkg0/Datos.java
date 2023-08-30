/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brisorapp_v1.pkg0;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author ElRonas-Mobile
 */
public class Datos {

    private String Nombre_cliente;
    private String Nombre_Producto;
    private Double Precio_unitario;
    private Double Precio_total = 0.0;
    private int catidad;
    private int IDMenu;

    private Base_conexion_metodos BD = new Base_conexion_metodos();
    private Scanner SC = new Scanner(System.in);

    public int getIDMenu(String Nombre) throws SQLException {
        ResultSet rsm;
        rsm = BD.Consulta("select IDM from tbl_menu where Nombre_pro='" + Nombre + "';");
        IDMenu = rsm.getInt(1);
        return IDMenu;
    }

    public void setpedido() throws SQLException {
        BD.Establecer_conexion();
        ResultSet rsp;
        ResultSet rsp2;
        Map<Integer, Double> PrecioUni = new HashMap<>();
        PrecioUni.clear();
        boolean sino = false;
        System.out.println("Ingrese el nombre del cliente");
        this.Nombre_cliente = SC.next();
        BD.actualizarBD("insert into tbl_Pedido(Cliente_Nomb, Fecha) values('" + this.Nombre_cliente + "', default);");
        do{
        System.out.println("Seleccione que desea ordenar");
        rsp=BD.Consulta("select * from tbl_menu;");
        while (rsp.next()) {            
            System.out.println(rsp.getInt(1)+"."+rsp.getString(2)+"  $"+rsp.getDouble(3));
            PrecioUni.put(rsp.getInt(1), rsp.getDouble(3));
        }
        int op=SC.nextInt();
        do{
        if (PrecioUni.containsKey(op)) {
            System.out.println("Bien");
            System.out.println("Cuantos va a ordenar");
            System.out.println("Bien");
            this.catidad=SC.nextInt();
            System.out.println("Bien");
            this.Precio_total=this.Precio_total+ (PrecioUni.get(op)*this.catidad);
            System.out.println("Bien");
            
            rsp2=BD.Consulta("select max(IDP) from tbl_pedido;");
            int ID = 0;
            while (rsp2.next()) {                
                ID=rsp2.getInt(1);
            }
            System.out.println("Bien");
            BD.actualizarBD("insert into tbl_Menu_Pedido(pedido, Menu, cantidad, precio_total) values("+ID+", "+op+", "+this.catidad+", "+this.Precio_total+");");
        }else{
            System.out.println("Esto no es una opcion"); 
        }
        }while(PrecioUni.containsKey(op)==true);
            System.out.println("¿Desea pedir algo mas? \n"
                    + "1.Si \n"
                    + "2.No");
            int opc=SC.nextInt();
            switch (opc) {
                case 1:
                    sino=true;
                    break;
                case 2:
                    sino=false;
                    break;
                default:
                    System.out.println(opc+" No es una opcion");
                    break;
            }
        }while(sino==true);
        BD.CerrarBD();
    }

    public void setProducto() {
        BD.Establecer_conexion();
        BD.actualizarBD("insert into tbl_Menu(Nombre_pro, Precio_unit) values('"+this.Nombre_Producto+"', "+this.Precio_unitario+");");
    }

}
