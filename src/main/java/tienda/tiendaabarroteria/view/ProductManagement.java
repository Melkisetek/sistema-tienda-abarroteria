package tienda.tiendaabarroteria.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import tienda.tiendaabarroteria.controller.ProductoController;

public class ProductManagement extends javax.swing.JFrame {

    // *** singleton implementar *** 
    // 1. La instancia única y estática
    private static ProductManagement instance;

    // A. Agrega este atributo
    private final ProductoController controlador;

    // 2. El constructor ahora es PRIVADO (nadie puede hacer 'new' desde fuera)
    private ProductManagement() {
        initComponents();
        setLocationRelativeTo(this);
        // B. Guarda la instancia aquí
        this.controlador = new ProductoController(this);
        miTabla();
    }

    // 3. Método para obtener la instancia única
    public static ProductManagement getInstance() {
        if (instance == null) {
            instance = new ProductManagement();
        }
        return instance;
    }

    // C. Crea un Getter para que Category pueda obtenerlo
    public ProductoController getControlador() {
        return this.controlador;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnCategoria = new javax.swing.JButton();
        btnDesconectar = new javax.swing.JButton();
        btnGestionarVentas = new javax.swing.JButton();
        btnEmpleados = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        categoria = new javax.swing.JComboBox<>();
        precio = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        cantidad = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        stockMinimo = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administrar Productos");
        setFocusTraversalPolicyProvider(true);
        setPreferredSize(new java.awt.Dimension(1224, 680));
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(255, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 128, 128));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCategoria.setBackground(new java.awt.Color(0, 128, 128));
        btnCategoria.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        btnCategoria.setForeground(new java.awt.Color(255, 255, 255));
        btnCategoria.setText("CATEGORIAS");
        btnCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCategoriaMouseClicked(evt);
            }
        });
        btnCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoriaActionPerformed(evt);
            }
        });
        jPanel2.add(btnCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 220, 40));

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

        btnGestionarVentas.setBackground(new java.awt.Color(0, 128, 128));
        btnGestionarVentas.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        btnGestionarVentas.setForeground(new java.awt.Color(255, 255, 255));
        btnGestionarVentas.setText("GESTIONAR VENTAS");
        btnGestionarVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGestionarVentasMouseClicked(evt);
            }
        });
        btnGestionarVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestionarVentasActionPerformed(evt);
            }
        });
        jPanel2.add(btnGestionarVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 220, 40));

        btnEmpleados.setBackground(new java.awt.Color(0, 128, 128));
        btnEmpleados.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        btnEmpleados.setForeground(new java.awt.Color(255, 255, 255));
        btnEmpleados.setText("Empleados");
        btnEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpleadosActionPerformed(evt);
            }
        });
        jPanel2.add(btnEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 220, 40));

        btnReportes.setBackground(new java.awt.Color(0, 128, 128));
        btnReportes.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        btnReportes.setForeground(new java.awt.Color(255, 255, 255));
        btnReportes.setText("REPORTES");
        btnReportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReportesMouseClicked(evt);
            }
        });
        jPanel2.add(btnReportes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 220, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 220, 700));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(0, 128, 128));
        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 128, 128));
        jLabel1.setText("ADMINISTRAR PRODUCTOS");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, -1, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 128, 128));
        jLabel3.setText("NOMBRE");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, -1));

        categoria.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        categoria.setForeground(new java.awt.Color(0, 128, 128));
        categoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "item" }));
        categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriaActionPerformed(evt);
            }
        });
        jPanel4.add(categoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 68, 230, 40));

        precio.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N
        precio.setForeground(new java.awt.Color(0, 128, 128));
        jPanel4.add(precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 68, 230, 40));

        jLabel4.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 128, 128));
        jLabel4.setText("CATEGORIA");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, -1, -1));

        nombre.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N
        nombre.setForeground(new java.awt.Color(0, 128, 128));
        jPanel4.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 230, 40));

        cantidad.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N
        cantidad.setForeground(new java.awt.Color(0, 128, 128));
        jPanel4.add(cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 230, 40));

        jLabel5.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 128, 128));
        jLabel5.setText("STOCK");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, -1, -1));

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
        jPanel4.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 190, 140, 50));

        btnAgregar.setBackground(new java.awt.Color(0, 128, 128));
        btnAgregar.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("AGREGAR");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel4.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 140, 50));

        btnEditar.setBackground(new java.awt.Color(0, 128, 128));
        btnEditar.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("EDITAR");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel4.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 190, 140, 50));

        btnEliminar.setBackground(new java.awt.Color(0, 128, 128));
        btnEliminar.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("ELIMINAR");
        jPanel4.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 190, 140, 50));

        jLabel6.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 128, 128));
        jLabel6.setText("PRECIO");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 80, -1, -1));

        jLabel8.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 128, 128));
        jLabel8.setText("STOCK MÍNIMO");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 140, -1, -1));

        stockMinimo.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N
        stockMinimo.setForeground(new java.awt.Color(0, 128, 128));
        jPanel4.add(stockMinimo, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 130, 230, 40));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 990, 260));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "NOMBRE", "PRECIO", "STOCK", "CATEGORIA", "STOCK MÍNIMO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 1, 22)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 128, 128));
        jLabel2.setText("LISTA DE PRODUCTOS");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(347, 347, 347))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 959, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 990, 370));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, -10, 1020, 700));

        getContentPane().add(jPanel1, new java.awt.GridBagConstraints());

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miTabla() {
        // Fondo blanco para las celdas
        jTable1.setBackground(Color.WHITE);
        jTable1.setForeground(Color.BLACK);

        // Configurar encabezado
        jTable1.getTableHeader().setBackground(Color.WHITE);
        jTable1.getTableHeader().setForeground(Color.BLACK);
        jTable1.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        // Centrar todas las columnas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        // Aplicar a todas las columnas
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        // Obtener el renderer actual del encabezado y centrarlo
        ((DefaultTableCellRenderer) jTable1.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);

        // Remover líneas de grid (opcional, para look más limpio)
        jTable1.setShowGrid(true);
        jTable1.setGridColor(new Color(230, 230, 230)); // Grid suave

        // Altura de filas
        jTable1.setRowHeight(30);

    }

    // Getters 
    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JTextField getCantidad() {
        return cantidad;
    }

    public JComboBox<String> getCategoria() {
        return categoria;
    }

    public JTable getjTable1() {
        return jTable1;
    }

    public JTextField getNombre() {
        return nombre;
    }

    public JTextField getPrecio() {
        return precio;
    }

    public JTextField getStockMinimo() {
        return stockMinimo;
    }

    // limpiar campos
    public void limpiarCampos() {
        nombre.setText("");
        cantidad.setText("");
        precio.setText("");
        stockMinimo.setText("");
    }

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnLimpiarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarMouseClicked
        nombre.setText("");
        cantidad.setText("");
        precio.setText("");
        stockMinimo.setText("");
    }//GEN-LAST:event_btnLimpiarMouseClicked

    private void categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoriaActionPerformed

    private void btnGestionarVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestionarVentasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGestionarVentasActionPerformed

    private void btnGestionarVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGestionarVentasMouseClicked
        java.awt.EventQueue.invokeLater(() -> {
            SalesManagement ventas = new SalesManagement();
            ventas.setVisible(true);
            this.dispose();
        });
    }//GEN-LAST:event_btnGestionarVentasMouseClicked

    private void btnDesconectarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDesconectarMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDesconectarMouseExited

    private void btnDesconectarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDesconectarMouseClicked
        java.awt.EventQueue.invokeLater(() -> {
            Login login = new Login(null, false);
            login.setVisible(true);
            this.dispose();
        });


    }//GEN-LAST:event_btnDesconectarMouseClicked

    private void btnDesconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesconectarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDesconectarActionPerformed

    private void btnCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCategoriaActionPerformed

    private void btnCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCategoriaMouseClicked
        java.awt.EventQueue.invokeLater(() -> {
            Category category = new Category();
            category.setVisible(true);
            this.dispose();
        });
    }//GEN-LAST:event_btnCategoriaMouseClicked

    private void btnEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpleadosActionPerformed
        java.awt.EventQueue.invokeLater(() -> {
            Seller empleados = new Seller();
            empleados.setVisible(true);
            this.dispose();
        });
    }//GEN-LAST:event_btnEmpleadosActionPerformed

    private void btnReportesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportesMouseClicked
        java.awt.EventQueue.invokeLater(() -> {
            Report reporte = new Report();
            reporte.setVisible(true);
            this.dispose();
        });
    }//GEN-LAST:event_btnReportesMouseClicked

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCategoria;
    private javax.swing.JButton btnDesconectar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEmpleados;
    private javax.swing.JButton btnGestionarVentas;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnReportes;
    private javax.swing.JTextField cantidad;
    private javax.swing.JComboBox<String> categoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField precio;
    private javax.swing.JTextField stockMinimo;
    // End of variables declaration//GEN-END:variables
}
