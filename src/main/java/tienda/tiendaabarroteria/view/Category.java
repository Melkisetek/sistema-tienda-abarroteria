package tienda.tiendaabarroteria.view;

import javax.swing.UIManager;

public class Category extends javax.swing.JFrame {

    public Category() {
        initComponents();
        setLocationRelativeTo(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnDesconectar = new javax.swing.JButton();
        btnAdminProductos = new javax.swing.JButton();
        btnLimpiar4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        textID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnAgregar1 = new javax.swing.JButton();
        btnEditar1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        textStockMinimo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        textCategoria1 = new javax.swing.JTextField();
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

        btnAdminProductos.setBackground(new java.awt.Color(0, 128, 128));
        btnAdminProductos.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        btnAdminProductos.setForeground(new java.awt.Color(255, 255, 255));
        btnAdminProductos.setText("ADMIN. PRODUCTOS");
        btnAdminProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAdminProductosMouseClicked(evt);
            }
        });
        btnAdminProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminProductosActionPerformed(evt);
            }
        });
        jPanel2.add(btnAdminProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 220, 40));

        btnLimpiar4.setBackground(new java.awt.Color(0, 128, 128));
        btnLimpiar4.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        btnLimpiar4.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar4.setText("REPORTES");
        jPanel2.add(btnLimpiar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 220, 40));

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
        textID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textIDActionPerformed(evt);
            }
        });
        jPanel3.add(textID, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 340, 40));

        jLabel6.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 128, 128));
        jLabel6.setText("ID");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, -1, -1));

        jLabel7.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 128, 128));
        jLabel7.setText("CATEGORÍA NOMBRE");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, -1, -1));

        btnAgregar1.setBackground(new java.awt.Color(0, 128, 128));
        btnAgregar1.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        btnAgregar1.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar1.setText("AGREGAR");
        jPanel3.add(btnAgregar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 140, 50));

        btnEditar1.setBackground(new java.awt.Color(0, 128, 128));
        btnEditar1.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        btnEditar1.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar1.setText("EDITAR");
        btnEditar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditar1ActionPerformed(evt);
            }
        });
        jPanel3.add(btnEditar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 270, 140, 50));

        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, 340, 90));

        btnEliminar.setBackground(new java.awt.Color(0, 128, 128));
        btnEliminar.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("ELIMINAR");
        jPanel3.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 270, 140, 50));

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
        jPanel3.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 270, 140, 50));

        textStockMinimo.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N
        textStockMinimo.setForeground(new java.awt.Color(0, 128, 128));
        jPanel3.add(textStockMinimo, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 340, 40));

        jLabel8.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 128, 128));
        jLabel8.setText("STOCK MÍNIMO");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, -1, -1));

        textCategoria1.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N
        textCategoria1.setForeground(new java.awt.Color(0, 128, 128));
        jPanel3.add(textCategoria1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 340, 40));

        jLabel9.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 128, 128));
        jLabel9.setText("DESCRIPCIÓN");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 990, 340));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "Golosina", "Recitos, galletas, dulces"},
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

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 730, 260));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 410, 990, 270));

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
            Login login = new Login(null, false);
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

    private void btnAdminProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdminProductosMouseClicked
         try {
            // Usar el look and feel del sistema
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

        } catch (Exception e) {
            e.printStackTrace();
        }
          java.awt.EventQueue.invokeLater(() -> {
         ProductManagement.getInstance().setVisible(true);
        this.dispose();
          });
    }//GEN-LAST:event_btnAdminProductosMouseClicked

    private void btnAdminProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminProductosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdminProductosActionPerformed

    private void btnEditar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditar1ActionPerformed

    private void btnLimpiarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarMouseClicked
       
    }//GEN-LAST:event_btnLimpiarMouseClicked

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void textIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textIDActionPerformed

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
            java.util.logging.Logger.getLogger(Category.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Category.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Category.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Category.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Category().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdminProductos;
    private javax.swing.JButton btnAgregar1;
    private javax.swing.JButton btnDesconectar;
    private javax.swing.JButton btnEditar1;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnLimpiar4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField textCategoria1;
    private javax.swing.JTextField textID;
    private javax.swing.JTextField textStockMinimo;
    private javax.swing.JTextArea txtArea;
    // End of variables declaration//GEN-END:variables
}
