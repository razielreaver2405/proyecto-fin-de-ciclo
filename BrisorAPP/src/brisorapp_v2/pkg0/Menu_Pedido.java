/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brisorapp_v2.pkg0;

/**
 *
 * @author ElRonas-Mobile
 */
public class Menu_Pedido {

    private int cantidad;
    private double Precio_total;

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;                          /*1.ta*/
    }
    /*Coste de Tiempo: ta*/
    public void setPrecio_total(double precio) {
        this.Precio_total=precio*getCantidad();            /*1.ta*/
    }
    /*Coste de Tiempo: ta*/
    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio_total() {
        return Precio_total;
    }
    
}
