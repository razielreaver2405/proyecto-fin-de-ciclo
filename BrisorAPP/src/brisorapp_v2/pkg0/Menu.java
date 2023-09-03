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
public class Menu {

    private String Nombre_pro;
    private double Precio_unit;

    public void setNombre_pro(String Nombre_pro) {
        this.Nombre_pro = Nombre_pro;
    }

   public void setPrecio_unit(double Precio_unit) {
        this.Precio_unit = Precio_unit;/** 1 ta */
        
        /*Coste de Tiempo: ta*/  
    }

    public String getNombre_pro() {
        return Nombre_pro;
    }

    public double getPrecio_unit() {
        return Precio_unit;
    }
    
}
