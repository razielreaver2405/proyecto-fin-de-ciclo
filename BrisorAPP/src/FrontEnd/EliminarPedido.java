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
public class EliminarPedido extends javax.swing.JFrame {

    /**
     * Creates new form EliminarPedido
     */
    public EliminarPedido() {
        initComponents();
        this.getContentPane().setBackground(Color.GRAY);
    }
    AccesoBD BD = new AccesoBD();
    private int IDC;

    public void ListarPedidos(int IDC) throws SQLException {
        BD.Establecer_conexion();
        this.IDC = IDC;
        ArrayList<String> PEI = new ArrayList<>();
        DefaultListModel Modelo = new DefaultListModel();
        ResultSet RS = BD.Consulta("select*from tbl_menu_pedido, tbl_pedido, tbl_menu where cliente = " + IDC + " and IDP=pedido;");
        while (RS.next()) {
            PEI.add(RS.getInt(1) + " / " + RS.getString(9) + " / " + RS.getDouble(10) + " / " + RS.getInt(3) + " / " + RS.getDouble(4));
        }
        for (int i = 0; i < PEI.size(); i++) {
            Modelo.add(i, PEI.get(i));
        }
        PedidosList.setModel(Modelo);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        PedidosList = new javax.swing.JList<>();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        PedidoEliminar = new javax.swing.JTextField();
        Eliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PedidosList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(PedidosList);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 620, 240));

        jButton3.setText("X");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Lista de Pedidos");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, 20));

        jLabel2.setText("Pedido  /  Nombre de Producto  /  PrecioUnitario  /  Cantidad  /  Pecio Total");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, 20));

        jLabel3.setText("Ingrese el numero del pedido:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 20));

        PedidoEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PedidoEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(PedidoEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, 120, 20));

        Eliminar.setText("Eliminar");
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        getContentPane().add(Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 370, -1, -1));

        setSize(new java.awt.Dimension(643, 410));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Pedidos_sel PS = new Pedidos_sel();
        PS.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void PedidoEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PedidoEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PedidoEliminarActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        try {
            BD.Establecer_conexion();
            BD.actualizarBD("delete tbl_menu_pedido, tbl_pedido from tbl_menu_pedido join tbl_pedido on tbl_menu_pedido.pedido = tbl_pedido.IDP where tbl_menu_pedido.pedido=" + Integer.parseInt(PedidoEliminar.getText()) + ";");
            ListarPedidos(IDC);
        } catch (SQLException ex) {
            Logger.getLogger(EliminarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_EliminarActionPerformed

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
            java.util.logging.Logger.getLogger(EliminarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EliminarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EliminarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EliminarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EliminarPedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Eliminar;
    private javax.swing.JTextField PedidoEliminar;
    private javax.swing.JList<String> PedidosList;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}