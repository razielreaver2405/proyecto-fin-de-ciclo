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
public class Empleado {
    private final int Cedula;
    private final String Nombre1;
    private final String Nombre2;
    private final String ApellidoPaterno;
    private final String ApellidoMaterno;

    public Empleado(int Cedula, String Nombre1, String Nombre2, String ApellidoPaterno, String ApellidoMaterno) {
        this.Cedula = Cedula;                      /*1.ta*/
        this.Nombre1 = Nombre1;                    /*2.ta*/
        this.Nombre2 = Nombre2;                    /*3.ta*/
        this.ApellidoPaterno = ApellidoPaterno;    /*4.ta*/
        this.ApellidoMaterno = ApellidoMaterno;    /*5.ta*/
         /*Coste de Tiempo: 5ta*/
    }

    public int getCedula() {
        return Cedula;
    }

    public String getNombre1() {
        return Nombre1;
    }

    public String getNombre2() {
        return Nombre2;
    }

    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public String getApellidoMaterno() {
        return ApellidoMaterno;
    }
    
}
