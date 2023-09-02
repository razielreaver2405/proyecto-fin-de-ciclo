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
public class Cliente {
    private final int Cedula;
    private final String nombre;
    private final String apellido;

    public Cliente(int Cedula, String nombre, String apellido) {
        this.Cedula = Cedula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getCedula() {
        return Cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
    
}
