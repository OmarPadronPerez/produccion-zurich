package GRAFICA.BODEGA;

import CLASES.COMPONENTE;
import OTROS.AUXILIAR;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import sql.SQLBODEGA;
import sql.SQLMUEBLES;

public class ingresarExistencias extends javax.swing.JInternalFrame {

    private int x;
    private int y;
    COMPONENTE com;
    SQLMUEBLES modMuebles = new SQLMUEBLES();

    public ingresarExistencias(COMPONENTE com) {
        initComponents();
        imgBarraTitulo.setBackgroung("IMAGENES/TEXTURAS/marmol.png");
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        imgSi.setBackgroung("IMAGENES/ICONOS/si.png");
        imgNo.setBackgroung("IMAGENES/ICONOS/no.png");//.trasparente();
        this.com = com;
        if (com.getId() > 0) {
            txtCantidad.setText(String.valueOf(com.getCantidad()));
            txtCantidad.enable(false);
            txtNotas.setText(com.getNota());
            txtUbicacion.setText(com.getExplosivo());
        }
        txtModelo.setText(com.getModelo());
        txtComponente.setText(com.getNombre());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imgBarraTitulo = new OTROS.IMAGEN();
        txtTitulo = new javax.swing.JLabel();
        imgNo = new OTROS.IMAGEN();
        imgSi = new OTROS.IMAGEN();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNotas = new javax.swing.JTextArea();
        txtModelo = new javax.swing.JLabel();
        txtComponente = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        txtUbicacion = new javax.swing.JTextField();

        imgBarraTitulo.setBackground(new java.awt.Color(255, 255, 255));
        imgBarraTitulo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                imgBarraTituloMouseDragged(evt);
            }
        });
        imgBarraTitulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                imgBarraTituloMousePressed(evt);
            }
        });

        txtTitulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTitulo.setText("EXISTENCIAS");

        imgNo.setMinimumSize(new java.awt.Dimension(26, 26));
        imgNo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgNoMouseClicked(evt);
            }
        });

        imgSi.setMinimumSize(new java.awt.Dimension(26, 26));
        imgSi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgSiMouseClicked(evt);
            }
        });

        imgBarraTitulo.setLayer(txtTitulo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        imgBarraTitulo.setLayer(imgNo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        imgBarraTitulo.setLayer(imgSi, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout imgBarraTituloLayout = new javax.swing.GroupLayout(imgBarraTitulo);
        imgBarraTitulo.setLayout(imgBarraTituloLayout);
        imgBarraTituloLayout.setHorizontalGroup(
            imgBarraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imgBarraTituloLayout.createSequentialGroup()
                .addComponent(imgNo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(imgSi, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        imgBarraTituloLayout.setVerticalGroup(
            imgBarraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(imgBarraTituloLayout.createSequentialGroup()
                .addGroup(imgBarraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imgNo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgSi, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel1.setText("Modelo");

        jLabel2.setText("Componente");

        jLabel3.setText("Cantidad");

        jLabel4.setText("Ubicacion");

        jLabel5.setText("Notas");

        txtNotas.setColumns(20);
        txtNotas.setRows(2);
        jScrollPane1.setViewportView(txtNotas);

        txtCantidad.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
            }
        });

        txtUbicacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUbicacionKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgBarraTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtModelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtComponente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtUbicacion))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imgBarraTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtComponente, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void imgNoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgNoMouseClicked
        this.dispose();
    }//GEN-LAST:event_imgNoMouseClicked

    private void imgBarraTituloMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgBarraTituloMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_imgBarraTituloMousePressed

    private void imgBarraTituloMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgBarraTituloMouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - (x + 5), point.y - (y + 50));
    }//GEN-LAST:event_imgBarraTituloMouseDragged

    private void imgSiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgSiMouseClicked
        if (AUXILIAR.isInt(txtCantidad.getText())) {
            if (txtUbicacion.getText().length() > 0) {
                SQLBODEGA modBodega = new SQLBODEGA();
                com.setCantidad(Integer.valueOf(txtCantidad.getText()));
                com.setNota(txtNotas.getText().toUpperCase());
                com.setExplosivo(txtUbicacion.getText().toUpperCase());
                if (modBodega.guardarExistencias(com)) {
                    bodegaExistencias.actualizar();
                    this.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Error al guardar", "Error al guardar", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Es nesesario la ubicacion del mueble", "Error al guardar", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "La cantidad no es valida", "Error al guardar", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_imgSiMouseClicked

    private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyPressed
        
    }//GEN-LAST:event_txtCantidadKeyPressed

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            txtUbicacion.requestFocus();
        }
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void txtUbicacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUbicacionKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            txtNotas.requestFocus();
        }
    }//GEN-LAST:event_txtUbicacionKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private OTROS.IMAGEN imgBarraTitulo;
    private OTROS.IMAGEN imgNo;
    private OTROS.IMAGEN imgSi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JLabel txtComponente;
    private javax.swing.JLabel txtModelo;
    private javax.swing.JTextArea txtNotas;
    private javax.swing.JLabel txtTitulo;
    private javax.swing.JTextField txtUbicacion;
    // End of variables declaration//GEN-END:variables
}
