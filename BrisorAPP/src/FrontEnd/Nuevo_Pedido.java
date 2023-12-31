/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd;

import brisorapp_v2.pkg0.AccesoBD;
import brisorapp_v2.pkg0.Menu_Pedido;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author ElRonas-Mobile
 */
public class Nuevo_Pedido extends javax.swing.JFrame {

    /**
     * Creates new form Nuevo_Pedido
     */
    public Nuevo_Pedido() {
        initComponents();
        this.getContentPane().setBackground(Color.GRAY);

    }
    Menu_Pedido MP = new Menu_Pedido();
    private int IDP;
    private double Preciouni;
    private double total;

    public void CantidadCalculo() {
        MP.setCantidad(Integer.parseInt(Cantidad.getText()));
    }

    public void setTotal() throws SQLException {
        AccesoBD BD = new AccesoBD();                                                                   /*1.ta*/
        total=0;                                                                                        /*2.ta*/
        BD.Establecer_conexion();                                                                       /*3.ta*/
        ResultSet RST;                                                                                  
        RST=BD.Consulta("select sum(Precio_total) from tbl_menu_pedido where pedido=" + IDP + ";");     /*4.to*/
        while (RST.next()) {                                                                            /*5.to*/
            total=RST.getDouble(1);                                                                     /*6.ta*/
        }                                                                                               /*7.tc*/
    }
    /*Tiempo Esperado (Tm):
        T = ta + ta + ta + to + to + ta + ta = (4 * ta) + (2 * to)*/
    
    public void obtenerIDPedido() throws SQLException {
        AccesoBD BD = new AccesoBD();                                                               /*1.ta*/
        BD.Establecer_conexion();                                                                   /*2.ta*/
        ResultSet RSE = BD.Consulta("SELECT IDP FROM tbl_pedido ORDER BY IDP DESC LIMIT 1;");       /*3.to*/
        while (RSE.next()) {                                                                        /*4.to*/
            IDP = RSE.getInt(1);                                                                    /*5.ta*/
        }
    }
    /*Tiempo Esperado (Tm):
        T = ta + ta + to + to + ta = 3ta + 2to*/

    public void Obtenerprecio() throws SQLException {
        AccesoBD BD = new AccesoBD();                                                                                                               /*1.ta*/
        BD.Establecer_conexion();                                                                                                                   /*2.ta*/
        ResultSet RSE = BD.Consulta("select Precio_unit from tbl_Menu where IDM = " + Integer.parseInt((String) MenuCB.getSelectedItem()) + ";");   /*3.to*n*/
        while (RSE.next()) {                                                                                                                        /*4.*to*n/
            Preciouni = RSE.getDouble(1);                                                                                                           /*5.ta*/
        }
    }
    /*Mejor Tiempo Esperado (Tm):
        Tm = ta + ta + to * n + to * n + ta + ta = 2ta + 2ta + 2to * n = 4ta + 2to * n

    /*Peor Tiempo Esperado (Tp):
        Tp = ta + ta + to * n + to * n + ta + ta = 2ta + 2ta + 2to * n = 4ta + 2to * n

    /*Tiempo Promedio Esperado (Tu):
        Tu = (Tm+Tp)/2*/

    public void ListarMenu(String Sql) throws SQLException {
        MenuCB.removeAllItems();                                                /*1.ta*/
        ArrayList<Integer> IDM = new ArrayList<>();                             /*2.ta*/
        ArrayList<String> PrecioNomb = new ArrayList<>();                       /*3.ta*/
        DefaultListModel Modelo = new DefaultListModel();                       /*4.ta*/
        AccesoBD BD = new AccesoBD();                                           /*5.ta*/
        BD.Establecer_conexion();                                               /*6.ta*/
        ResultSet RSE = BD.Consulta(Sql);                                       /*7.to*/
        while (RSE.next()) {                                                    /*8.to*n*/
            IDM.add(RSE.getInt(1));                                             /*9.ta*n*/
            PrecioNomb.add(RSE.getString(2) + ": $" + RSE.getDouble(3));        /*10.ta*n*/
        }
        for (int i = 0; i < IDM.size(); i++) {                                  /*11.tc*n*/
            String anidar = "ID: " + IDM.get(i) + " " + PrecioNomb.get(i);      /*12.ta*n*/
            MenuCB.addItem(Integer.toString(IDM.get(i)));                       /*13.ta*n*/
            Modelo.add(i, anidar);                                              /*14.ta*n*/
        }
        LMenu.setModel(Modelo);                                                 /*15.ta*/
        BD.CerrarBD();                                                          /*16.ta*/
    }
    /*Mejor Tiempo Esperado:
        Tm = ta + ta + ta + ta + ta + ta + to + ta * n + ta * n + ta * n + ta * n + ta * n + ta * n + ta * n + ta + ta*/

    /*Peor Tiempo Esperado:
        Tp = ta + ta + ta + ta + ta + ta + to + ta * n + ta * n + ta * n + tc * n + ta * n + ta * n + ta * n + ta + ta*/

    /*Tiempo Promedio Esperado:
        Tu = (Tm + Tw) / 2*/

    public void ListarPedidoTotal() throws SQLException {
        ArrayList<String> CadaUno = new ArrayList<>();                                                                         /*1.ta*/
        DefaultListModel Modelo = new DefaultListModel();                                                                      /*2.ta*/
        AccesoBD BD = new AccesoBD();                                                                                          /*3.ta*/
        System.out.println(IDP + Integer.parseInt((String) MenuCB.getSelectedItem()));                                         /*4.ta*/
        BD.Establecer_conexion();                                                                                              /*5.ta*/
        ResultSet RSE = BD.Consulta("select*from tbl_menu_pedido, tbl_Menu as M where pedido=" + IDP + " and M.IDM=menu;");    /*6.to*/
        while (RSE.next()) {                                                                                                   /*7.to*/
            CadaUno.add(RSE.getString(6) + "   x" + RSE.getInt(3) + "  Total: $" + RSE.getDouble(4));                          /*8.ta*n*/
        }
        for (int i = 0; i < CadaUno.size(); i++) {                                                                             /*9.tc*n*/
            Modelo.add(i, CadaUno.get(i));                                                                                     /*10.ta*n*/
        }
        PedidoL.setModel(Modelo);                                                                                              /*11.ta*/
        BD.CerrarBD();                                                                                                         /*12.ta*/
    }
    /*Mejor Tiempo Esperado (Tm):
        Tm = ta + ta + ta + ta + ta + ta + to + ta * n + ta * n + ta * n + ta * n + ta * n + ta * n + ta * n + ta + ta*/
    /*Peor Tiempo Esperado (Tp):
        Tp = ta + ta + ta + ta + ta + ta + to + ta * n + ta * n + ta * n + tc * n + ta * n + ta * n + ta * n + ta + ta*/
    /*Tiempo Promedio Esperado (Tu):
        Tu = (Tm + Tw) / 2*/

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        MenuCB = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        LMenu = new javax.swing.JList<>();
        Atras = new javax.swing.JButton();
        Aceptar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        Cantidad = new javax.swing.JLabel();
        mas = new javax.swing.JButton();
        menos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        PedidoL = new javax.swing.JList<>();
        Eliminar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        TotalPrecio = new javax.swing.JLabel();
        Fin = new javax.swing.JButton();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Menu de comida");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 170, 30));

        getContentPane().add(MenuCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 130, 30));

        jScrollPane3.setViewportView(LMenu);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 170, 110));

        Atras.setText("<-- Atras");
        Atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AtrasActionPerformed(evt);
            }
        });
        getContentPane().add(Atras, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, -1));

        Aceptar.setText("Agregar");
        Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AceptarActionPerformed(evt);
            }
        });
        getContentPane().add(Aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, -1, 20));

        jLabel3.setText("Cantidad:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 100, -1));

        Cantidad.setText("0");
        getContentPane().add(Cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 30, 20));

        mas.setText("+");
        mas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masActionPerformed(evt);
            }
        });
        getContentPane().add(mas, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, -1, -1));

        menos.setText("-");
        menos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menosActionPerformed(evt);
            }
        });
        getContentPane().add(menos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        jScrollPane1.setViewportView(PedidoL);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 370, 190));

        Eliminar.setText("Eliminar Articulo");
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        getContentPane().add(Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 310, -1, -1));

        jLabel1.setText("Total a pagar:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, -1, 20));
        getContentPane().add(TotalPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 90, 20));

        Fin.setText("Terminar ");
        Fin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FinActionPerformed(evt);
            }
        });
        getContentPane().add(Fin, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 310, -1, -1));

        setSize(new java.awt.Dimension(624, 362));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void masActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masActionPerformed
        MP.setCantidad(MP.getCantidad() + 1);
        Cantidad.setText(Integer.toString(MP.getCantidad()));
    }//GEN-LAST:event_masActionPerformed

    private void menosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menosActionPerformed
        MP.setCantidad(MP.getCantidad() - 1);
        Cantidad.setText(Integer.toString(MP.getCantidad()));

    }//GEN-LAST:event_menosActionPerformed

    private void AtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AtrasActionPerformed
        Pedidos_sel PS = new Pedidos_sel();
        PS.setVisible(true);
        AccesoBD BD = new AccesoBD();
        try {
            BD.Establecer_conexion();
            obtenerIDPedido();
            System.out.println(IDP);
            BD.actualizarBD("delete from tbl_pedido where IDP=" + IDP + ";");
            BD.CerrarBD();
        } catch (SQLException ex) {
            Logger.getLogger(Nuevo_Pedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        setVisible(false);
    }//GEN-LAST:event_AtrasActionPerformed

    private void AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AceptarActionPerformed
        AccesoBD BD = new AccesoBD();
        BD.Establecer_conexion();
        try {
            Obtenerprecio();
            obtenerIDPedido();
            MP.setPrecio_total(Preciouni);
            System.out.println("insert into tbl_menu_pedido(pedido, Menu, cantidad, Precio_total) values(" + IDP + " ," + Integer.parseInt((String) MenuCB.getSelectedItem()) + " ," + MP.getCantidad() + " ," + MP.getPrecio_total() + "));");
            System.out.println(MP.getPrecio_total());
            BD.actualizarBD("insert into tbl_menu_pedido(pedido, Menu, cantidad, Precio_total) values(" + IDP + " ," + Integer.parseInt((String) MenuCB.getSelectedItem()) + " ," + MP.getCantidad() + " ," + MP.getPrecio_total() + ");");
            BD.CerrarBD();
            setTotal();
            TotalPrecio.setText(" $"+Double.toString(total));
            ListarPedidoTotal();
        } catch (SQLException ex) {
            Logger.getLogger(Nuevo_Pedido.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_AceptarActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        AccesoBD BD = new AccesoBD();
        BD.Establecer_conexion();
        try {
            obtenerIDPedido();
            BD.actualizarBD("delete from tbl_menu_pedido where Pedido=" + IDP + " and Menu=" + Integer.parseInt((String) MenuCB.getSelectedItem()) + ";");
            BD.CerrarBD();
            ListarPedidoTotal();
            setTotal();
            TotalPrecio.setText(" $"+Double.toString(total));
        } catch (SQLException ex) {
            Logger.getLogger(Nuevo_Pedido.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_EliminarActionPerformed

    private void FinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FinActionPerformed
        Pedidos_sel PS = new Pedidos_sel();
        PS.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_FinActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Nuevo_Pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Nuevo_Pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Nuevo_Pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Nuevo_Pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Nuevo_Pedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Aceptar;
    private javax.swing.JButton Atras;
    private javax.swing.JLabel Cantidad;
    private javax.swing.JButton Eliminar;
    private javax.swing.JButton Fin;
    private javax.swing.JList<String> LMenu;
    private javax.swing.JComboBox<String> MenuCB;
    private javax.swing.JList<String> PedidoL;
    private javax.swing.JLabel TotalPrecio;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton mas;
    private javax.swing.JButton menos;
    // End of variables declaration//GEN-END:variables
}
