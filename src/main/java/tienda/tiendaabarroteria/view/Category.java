package tienda.tiendaabarroteria.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import tienda.tiendaabarroteria.controller.CategoryController;

public class Category extends javax.swing.JFrame {

    public Category() {
        initComponents();
        setLocationRelativeTo(this);
        CategoryController categoryController = new CategoryController(this);
          this.diseñoTabla();
    }

    //Getters
    public JButton getBtnAgregar() {
        return btnAgregar;
    }

 
    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JTable getTable() {

        return jTable1;
    }

    public JTextField getTextCategoria() {
        return textCategoria;
    }

    public JTextField getTextID() {
        return textID;
    }

    public JTextArea getTxtArea() {
        return txtArea;
    }
  private void diseñoTabla() {
          // Fondo blanco para las celdas
        jTable1.setBackground(Color.WHITE);
        jTable1.setForeground(Color.BLACK);

        // Configurar encabezado
        jTable1.getTableHeader().setBackground(Color.WHITE);
        jTable1.getTableHeader().setForeground(Color.BLACK);
        jTable1.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        // Remover líneas de grid (opcional, para look más limpio)
        jTable1.setShowGrid(true);
        jTable1.setGridColor(new Color(230, 230, 230)); // Grid suave
        
         // Asegurar que las columnas se ajusten bien
        this.jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
        this.jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
        this.jTable1.getColumnModel().getColumn(2).setPreferredWidth(350);
        
  }


    @SuppressWarnings(value = "unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnDesconectar = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        textID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        textCategoria = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 128, 128));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnDesconectar.setBackground(new java.awt.Color(0, 128, 128));
        btnDesconectar.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        btnDesconectar.setForeground(new java.awt.Color(255, 255, 255));
        btnDesconectar.setText("DESCONECTAR");
        btnDesconectar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDesconectarMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDesconectarMouseExited(evt);
            }
        });
        btnDesconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesconectarActionPerformed(evt);
            }
        });
        jPanel2.add(btnDesconectar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 220, 40));

        btnVolver.setBackground(new java.awt.Color(0, 128, 128));
        btnVolver.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/back.png"))); // NOI18N
        btnVolver.setText("VOLVER");
        btnVolver.setHideActionText(true);
        btnVolver.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVolverMouseClicked(evt);
            }
        });
        jPanel2.add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 220, 60));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 220, 700));

        jLabel1.setBackground(new java.awt.Color(0, 128, 128));
        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 128, 128));
        jLabel1.setText("GESTIONAR CATEGORÍAS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, -1, 30));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textID.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N
        textID.setForeground(new java.awt.Color(0, 128, 128));
        textID.setEnabled(false);
        textID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textIDActionPerformed(evt);
            }
        });
        jPanel3.add(textID, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 340, 40));

        jLabel6.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 128, 128));
        jLabel6.setText("ID");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, -1, -1));

        jLabel7.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 128, 128));
        jLabel7.setText("CATEGORÍA NOMBRE");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, -1, -1));

        btnAgregar.setBackground(new java.awt.Color(0, 128, 128));
        btnAgregar.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("Guardar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel3.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 140, 50));

        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 340, 110));

        btnEliminar.setBackground(new java.awt.Color(0, 128, 128));
        btnEliminar.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("ELIMINAR");
        jPanel3.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 220, 140, 50));

        btnLimpiar.setBackground(new java.awt.Color(0, 128, 128));
        btnLimpiar.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLimpiarMouseClicked(evt);
            }
        });
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel3.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 220, 140, 50));

        textCategoria.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N
        textCategoria.setForeground(new java.awt.Color(0, 128, 128));
        jPanel3.add(textCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 340, 40));

        jLabel9.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 128, 128));
        jLabel9.setText("DESCRIPCIÓN");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 990, 280));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "CATEGORÍA NOMBRE", "DESCRIPCIÓN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.setRowHeight(30);
        jTable1.setRowMargin(4);
        jTable1.setSelectionBackground(new java.awt.Color(0, 128, 128));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jTable1);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 730, 300));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 360, 990, 320));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDesconectarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDesconectarMouseClicked
        java.awt.EventQueue.invokeLater(() -> {
            Login login = new Login();
            login.setVisible(true);
            this.dispose();
        });

    }//GEN-LAST:event_btnDesconectarMouseClicked

    private void btnDesconectarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDesconectarMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDesconectarMouseExited

    private void btnDesconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesconectarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDesconectarActionPerformed

    private void btnLimpiarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarMouseClicked

    }//GEN-LAST:event_btnLimpiarMouseClicked

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void textIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textIDActionPerformed

    private void btnVolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVolverMouseClicked

        java.awt.EventQueue.invokeLater(() -> {
            ProductManagement productManagement = new ProductManagement();
            productManagement.setVisible(true);
            this.dispose();
        });
    }//GEN-LAST:event_btnVolverMouseClicked

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnDesconectar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField textCategoria;
    private javax.swing.JTextField textID;
    private javax.swing.JTextArea txtArea;
    // End of variables declaration//GEN-END:variables

}
