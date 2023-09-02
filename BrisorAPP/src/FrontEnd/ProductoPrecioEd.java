/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd;

import brisorapp_v2.pkg0.AccesoBD;
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
public class ProductoPrecioEd extends javax.swing.JFrame {

    /**
     * Creates new form ProductoPrecioEd
     */
    public ProductoPrecioEd() {
        initComponents();
        this.getContentPane().setBackground(Color.GRAY);
    }

    public void ListarMenu(String Sql) throws SQLException {
        MenuCB.removeAllItems();
        ArrayList<Integer> IDM = new ArrayList<>();
        ArrayList<String> PrecioNomb = new ArrayList<>();
        DefaultListModel Modelo = new DefaultListModel();
        AccesoBD BD = new AccesoBD();
        BD.Establecer_conexion();
        ResultSet RSE = BD.Consulta(Sql);
        while (RSE.next()) {
            IDM.add(RSE.getInt(1));
            PrecioNomb.add(RSE.getString(2) + ": $" + RSE.getDouble(3));
        }
        for (int i = 0; i < IDM.size(); i++) {
            String anidar = "ID: " + IDM.get(i) + " " + PrecioNomb.get(i);
            MenuCB.addItem(Integer.toString(IDM.get(i)));
            Modelo.add(i, anidar);
        }
        LMenu.setModel(Modelo);
        BD.CerrarBD();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        MenuCB = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        LMenu = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        precioNuevo = new javax.swing.JTextField();
        Cambiar = new javax.swing.JButton();
        salir = new javax.swing.JButton();

        jToggleButton1.setText("jToggleButton1");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Seleccione el codigo del producto");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 250, 30));

        getContentPane().add(MenuCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 130, 30));

        jScrollPane3.setViewportView(LMenu);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 360, 110));

        jLabel1.setText("Precio Nuevo:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 20));
        getContentPane().add(precioNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 90, -1));

        Cambiar.setText("Cambiar");
        Cambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CambiarActionPerformed(evt);
            }
        });
        getContentPane().add(Cambiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, -1, -1));

        salir.setText("X");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        getContentPane().add(salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, -1, -1));

        setSize(new java.awt.Dimension(402, 294));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void CambiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CambiarActionPerformed
        AccesoBD BD = new AccesoBD();
        try {
            BD.Establecer_conexion();
            BD.actualizarBD("update tbl_Menu set Precio_unit = " + Double.parseDouble(precioNuevo.getText()) + " where IDM=" + Integer.parseInt((String) MenuCB.getSelectedItem()) + ";");
            ListarMenu("select * from tbl_menu;");
            BD.CerrarBD();
        } catch (SQLException ex) {
            Logger.getLogger(ProductoPrecioEd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_CambiarActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        ProductosSel PS = new ProductosSel();
        PS.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_salirActionPerformed

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
            java.util.logging.Logger.getLogger(ProductoPrecioEd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductoPrecioEd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductoPrecioEd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductoPrecioEd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProductoPrecioEd().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cambiar;
    private javax.swing.JList<String> LMenu;
    private javax.swing.JComboBox<String> MenuCB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTextField precioNuevo;
    private javax.swing.JButton salir;
    // End of variables declaration//GEN-END:variables
}