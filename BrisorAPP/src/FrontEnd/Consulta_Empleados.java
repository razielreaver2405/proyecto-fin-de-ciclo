/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd;

import brisorapp_v2.pkg0.AccesoBD;
import java.awt.Color;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author ElRonas-Mobile
 */
public class Consulta_Empleados extends javax.swing.JFrame {

    /**
     * Creates new form Consulta_Empleados
     */
    public Consulta_Empleados() {
        initComponents();
        this.getContentPane().setBackground(Color.GRAY);
    }

    public void Listarempleados(String Sql) throws SQLException {
        ArrayList<Integer> Cedula = new ArrayList<>(); /*1.ta*/
        ArrayList<String> NombreCompleto = new ArrayList<>();/*2.ta*/
        DefaultListModel Modelo = new DefaultListModel();/*3.ta*/
        AccesoBD BD = new AccesoBD();/*4.ta*/
        BD.Establecer_conexion();/*5.to*/
        ResultSet RSE = BD.Consulta(Sql);/*6.to*/
        while (RSE.next()) { /*7.n*tc+tc*/
            Cedula.add(RSE.getInt(1));/*8.to*/
            NombreCompleto.add("  " + RSE.getString(2) + " " + RSE.getString(3) + " " + RSE.getString(4) + " " + RSE.getString(5)); /*9.to*/
        }
        for (int i = 0; i < Cedula.size(); i++) {/*11.n*tc+tc*/
            String anidar = "Cedula: " + Cedula.get(i) + "   Nombre:" + NombreCompleto.get(i);/*12.ta*/
            Modelo.add(i, anidar);/*13.to*/
        }
        EmpleadosList.setModel(Modelo);/*15.to*/
        BD.CerrarBD();/*16.to*/
    }
    /*
     Tempo mejor esperado: Tm
    4ta+2to+n*(tc+2to)+tc+n*(ta+tc+to)+tc+2to
    4ta + 2to + n*(tc+2to) + tc + n*(ta+tc+to) + tc + 2to
    = 4ta + tc + tc + 2to + 2to + n*(tc+2to) + n*(ta+tc+to)
    = 4ta + 2tc + 4to + n*(ta+tc+to)
    = 4(ta + 0.5tc + to) + n*(ta+tc+to)
    Tm= 4(ta + 0.5tc + to) + n*(ta + tc + to)
    */

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        EmpleadosList = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        BCedula = new javax.swing.JTextField();
        BBuscar = new javax.swing.JButton();
        Todo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        EmpleadosList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(EmpleadosList);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 620, 240));

        jButton1.setText("X");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Lista de empleados");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, 20));

        jLabel3.setText("Buscar Cedula:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, 20));
        getContentPane().add(BCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 120, 20));

        BBuscar.setText("Buscar");
        BBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(BBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, -1));

        Todo.setText("Mostrar Todo");
        Todo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TodoActionPerformed(evt);
            }
        });
        getContentPane().add(Todo, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 330, -1, -1));

        setSize(new java.awt.Dimension(646, 371));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Empleados_sel ES = new Empleados_sel();
        ES.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void BBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBuscarActionPerformed
        try {
            Listarempleados("select * from tbl_empleado where IDE="+Integer.parseInt(this.BCedula.getText())+";");
        } catch (SQLException ex) {
            Logger.getLogger(Consulta_Empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_BBuscarActionPerformed

    private void TodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TodoActionPerformed
        try {
            Listarempleados("select * from tbl_empleado;");
        } catch (SQLException ex) {
            Logger.getLogger(Consulta_Empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_TodoActionPerformed

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
            java.util.logging.Logger.getLogger(Consulta_Empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Consulta_Empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Consulta_Empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Consulta_Empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Consulta_Empleados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BBuscar;
    private javax.swing.JTextField BCedula;
    private javax.swing.JList<String> EmpleadosList;
    private javax.swing.JButton Todo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
