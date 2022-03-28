package GRAFICA.CREAR;

import CLASES.USUARIO;
import OTROS.AUXILIAR;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import sql.SQLUSUARIOS;

public class VerUsuario extends javax.swing.JInternalFrame {

    private int x = 0;
    private int y = 0;
    SQLUSUARIOS modUsu = new SQLUSUARIOS();
    USUARIO us = null;

    public VerUsuario() {
        initComponents();
        imgBarraTitulo2.setBackgroung("IMAGENES/TEXTURAS/marmol.png");
        imgCerrar.setBackgroung("IMAGENES/ICONOS/cerrar2.png");
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
    }

    public void buscarId() {
        try {
            if (txtId.getText().length() > 0) {
                us = modUsu.BuscarPorId(Integer.valueOf(txtId.getText()));
                if (us != null) {
                    txtNombre.setText(us.getNombre());
                    txtApellido.setText(us.getApellido());
                    if (us.getEstado()) {
                        cbEstado.setSelectedItem("ACTIVO");
                    } else {
                        cbEstado.setSelectedItem("INACTIVO");
                    }
                }
                agregar.setSelected(us.getAgregar());
                almacen.setSelected(us.getAlmacen());
                pedidos.setSelected(us.getPedidos());
                resumen.setSelected(us.getResumen());
                bodega.setSelected(us.getBodega());
                cheCalcular.setSelected(us.getCalcular());
                administrar.setSelected(us.getAdministrar());
            }

        } catch (Exception e) {
        }
    }
    
    void borrarCampos(){
        txtId.setText("");
        cbEstado.setSelectedIndex(0);
        txtNombre.setText("");
        txtApellido.setText("");
        txtCon1.setText("");
        txtCon2.setText("");
        agregar.setSelected(false);
        almacen.setSelected(false);
        cheCalcular.setSelected(false);
        pedidos.setSelected(false);
        resumen.setSelected(false);
        administrar.setSelected(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbEstado = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        agregar = new javax.swing.JCheckBox();
        almacen = new javax.swing.JCheckBox();
        pedidos = new javax.swing.JCheckBox();
        resumen = new javax.swing.JCheckBox();
        cheCalcular = new javax.swing.JCheckBox();
        administrar = new javax.swing.JCheckBox();
        bodega = new javax.swing.JCheckBox();
        produccion = new javax.swing.JCheckBox();
        imgBarraTitulo2 = new OTROS.IMAGEN();
        jLabel11 = new javax.swing.JLabel();
        imgCerrar = new OTROS.IMAGEN();
        txtCon1 = new javax.swing.JPasswordField();
        txtCon2 = new javax.swing.JPasswordField();
        btnBuscar = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(20, 20));
        setPreferredSize(new java.awt.Dimension(524, 344));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        jLabel2.setText("Id");

        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
        });

        jLabel3.setText("Nombre");

        jLabel4.setText("Apellido");

        jLabel5.setText("Estado");

        cbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));

        jLabel6.setText("Contraseña");

        jLabel7.setText("Verificar");

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Permisos"));

        agregar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        agregar.setText("Diseño");

        almacen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        almacen.setText("Almacen");

        pedidos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pedidos.setText("Pedidos");

        resumen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        resumen.setText("Resumen");

        cheCalcular.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cheCalcular.setText("Calcular");
        cheCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cheCalcularActionPerformed(evt);
            }
        });

        administrar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        administrar.setText("Administrar");

        bodega.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bodega.setText("Bodega");

        produccion.setText("Produccion");
        produccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produccionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(resumen)
                    .addComponent(almacen))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(administrar)
                    .addComponent(bodega))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cheCalcular)
                    .addComponent(pedidos))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(agregar)
                    .addComponent(produccion))
                .addGap(79, 79, 79))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(almacen)
                    .addComponent(cheCalcular)
                    .addComponent(bodega)
                    .addComponent(agregar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(administrar)
                    .addComponent(resumen)
                    .addComponent(pedidos)
                    .addComponent(produccion)))
        );

        imgBarraTitulo2.setBackground(new java.awt.Color(255, 255, 255));
        imgBarraTitulo2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                imgBarraTitulo2MouseDragged(evt);
            }
        });
        imgBarraTitulo2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                imgBarraTitulo2MousePressed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Control de cuentas de usuario");

        imgCerrar.setMinimumSize(new java.awt.Dimension(26, 26));
        imgCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgCerrarMouseClicked(evt);
            }
        });

        imgBarraTitulo2.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        imgBarraTitulo2.setLayer(imgCerrar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout imgBarraTitulo2Layout = new javax.swing.GroupLayout(imgBarraTitulo2);
        imgBarraTitulo2.setLayout(imgBarraTitulo2Layout);
        imgBarraTitulo2Layout.setHorizontalGroup(
            imgBarraTitulo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imgBarraTitulo2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(imgCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        imgBarraTitulo2Layout.setVerticalGroup(
            imgBarraTitulo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(imgBarraTitulo2Layout.createSequentialGroup()
                .addComponent(imgCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgBarraTitulo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtId)
                            .addComponent(cbEstado, 0, 170, Short.MAX_VALUE)
                            .addComponent(txtCon1)
                            .addComponent(txtNombre))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCon2)
                                    .addComponent(txtApellido)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnBuscar)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(imgBarraTitulo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(txtCon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCon2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void imgCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgCerrarMouseClicked
        this.dispose();
    }//GEN-LAST:event_imgCerrarMouseClicked

    private void imgBarraTitulo2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgBarraTitulo2MouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x + 3, point.y - y - 45);
    }//GEN-LAST:event_imgBarraTitulo2MouseDragged

    private void imgBarraTitulo2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgBarraTitulo2MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_imgBarraTitulo2MousePressed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarId();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            buscarId();
        }
    }//GEN-LAST:event_txtIdKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (txtNombre.getText().length() > 0) {
            if (txtApellido.getText().length() > 0) {
                if (txtCon1.getText().toString().equals(txtCon2.getText().toString())) {
                    if (txtId.getText().length() > 0) {
                        try {
                            if (AUXILIAR.isInt(txtId.getText())) {
                                us = new USUARIO();
                                us.setId(Integer.valueOf(txtId.getText()));
                                us.setNombre(txtNombre.getText());
                                us.setApellido(txtApellido.getText());
                                if (cbEstado.getSelectedItem().equals("INACTIVO")) {
                                    us.setEstado(false);
                                } else {
                                    us.setEstado(true);
                                }
                                us.setContraseña(txtCon1.getText().toString());
                                us.setAgregar(agregar.isSelected());
                                us.setAlmacen(almacen.isSelected());
                                us.setPedidos(pedidos.isSelected());
                                us.setResumen(resumen.isSelected());
                                us.setCalcular(cheCalcular.isSelected());
                                us.setAdministrar(administrar.isSelected());
                                us.setBodega(bodega.isSelected());
                                us.setProduccion(produccion.isSelected());
                                modUsu.AgregarUsuario(us);
                                borrarCampos();
                                
                            } else {
                                JOptionPane.showMessageDialog(null, "El id no es valido", "Error al guardar", JOptionPane.WARNING_MESSAGE);
                            }

                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "El id tiene que ser un numero", "Error al guardar", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Es necesario una contraseña o no coinciden", "Error al guardar", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Es necesario un apellido", "Error al guardar", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Es necesario un nombre", "Error al guardar", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed

    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged

    }//GEN-LAST:event_formMouseDragged

    private void cheCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cheCalcularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cheCalcularActionPerformed

    private void produccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_produccionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox administrar;
    private javax.swing.JCheckBox agregar;
    private javax.swing.JCheckBox almacen;
    private javax.swing.JCheckBox bodega;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JComboBox<String> cbEstado;
    private javax.swing.JCheckBox cheCalcular;
    private OTROS.IMAGEN imgBarraTitulo2;
    private OTROS.IMAGEN imgCerrar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JCheckBox pedidos;
    private javax.swing.JCheckBox produccion;
    private javax.swing.JCheckBox resumen;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JPasswordField txtCon1;
    private javax.swing.JPasswordField txtCon2;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
