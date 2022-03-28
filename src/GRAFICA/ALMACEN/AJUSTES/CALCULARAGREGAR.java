package GRAFICA.ALMACEN.AJUSTES;

import CLASES.material;
import OTROS.AUXILIAR;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;

public class CALCULARAGREGAR extends javax.swing.JInternalFrame {
    private int x;
    private int y;
    material mat=null;
            
    public CALCULARAGREGAR(material mat) {
        initComponents();
        imgBarraTitulo.setBackgroung("IMAGENES/TEXTURAS/marmol.png");
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        imgSi.setBackgroung("IMAGENES/ICONOS/si.png");
        imgNo.setBackgroung("IMAGENES/ICONOS/cerrar2.png");
        this.mat=mat;
        txtMaterial.setText(mat.getMaterial());
        txtTipo.setText(mat.getTipo());
        txtExistencias.setText(String.valueOf(mat.getCantidad()));
        txtEmpaque.setText(mat.getEmpaque()+"s");
        txtUnidad.setText(mat.getUnidades()+"s");
        
    }
    public void siguiente(){
        float mult=0;
        if(revisar()){
            if(txtCanEmp.getText().length() > 0){
                mult=Float.valueOf(txtCanEmp.getText());
            }
            if(txtCanUni.getText().length()>0){
                if(mult>0){
                    mult = mult * Float.valueOf(txtCanUni.getText());
                } else {
                    mult = Float.valueOf(txtCanUni.getText());
                }
            }

        }
        if (mult > 0) {
            mat.setMedidaZ(mult);
            MOVIMIENTOS.salida.add(mat);
            MOVIMIENTOS.llenarSalida();
            this.dispose();
        }
        
    }
    public boolean revisar(){
        int i=0;
        if (txtCanEmp.getText().length() > 0) {
            if (AUXILIAR.isFloat(txtCanEmp.getText()) &&
                    Float.valueOf(txtCanEmp.getText())>0) {
                txtCanEmp.setBackground(Color.WHITE);
                txtCanEmp.setForeground(Color.BLACK);
                i++;
            } else {
                txtCanEmp.setBackground(Color.red);
                txtCanEmp.setForeground(Color.WHITE);
            }
        }else{
            i++;
        }
        if (txtCanUni.getText().length() > 0) {
            if (AUXILIAR.isFloat(txtCanUni.getText())) {
                txtCanUni.setBackground(Color.WHITE);
                txtCanUni.setForeground(Color.BLACK);
                i++;
            } else {
                txtCanUni.setBackground(Color.red);
                txtCanUni.setForeground(Color.WHITE);
            }
        }else{
            i++;
        }
        
        if(i==2){
            return true;
        }else{
            return false;
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imgBarraTitulo = new OTROS.IMAGEN();
        jLabel2 = new javax.swing.JLabel();
        imgNo = new OTROS.IMAGEN();
        imgSi = new OTROS.IMAGEN();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtEmpaque = new javax.swing.JLabel();
        txtCanEmp = new javax.swing.JTextField();
        txtCanUni = new javax.swing.JTextField();
        txtUnidad = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMaterial = new javax.swing.JLabel();
        txtTipo = new javax.swing.JLabel();
        txtExistencias = new javax.swing.JLabel();

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

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Entrada de material");

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

        imgBarraTitulo.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        imgBarraTitulo.setLayer(imgNo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        imgBarraTitulo.setLayer(imgSi, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout imgBarraTituloLayout = new javax.swing.GroupLayout(imgBarraTitulo);
        imgBarraTitulo.setLayout(imgBarraTituloLayout);
        imgBarraTituloLayout.setHorizontalGroup(
            imgBarraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imgBarraTituloLayout.createSequentialGroup()
                .addComponent(imgNo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(imgSi, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        imgBarraTituloLayout.setVerticalGroup(
            imgBarraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imgBarraTituloLayout.createSequentialGroup()
                .addGroup(imgBarraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgNo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgSi, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel1.setText("Material");

        jLabel3.setText("Tipo");

        jLabel4.setText("Existencias");

        txtEmpaque.setText("txtEmpaque");

        txtCanEmp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCanEmpKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCanEmpKeyReleased(evt);
            }
        });

        txtCanUni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCanUniKeyReleased(evt);
            }
        });

        txtUnidad.setText("txtUnidad");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("de");

        txtMaterial.setText("txtMaterial");

        txtTipo.setText("txtTipo");

        txtExistencias.setText("txtExistencias");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgBarraTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCanUni, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCanEmp)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaterial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtExistencias, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmpaque, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 132, Short.MAX_VALUE))
                    .addComponent(txtUnidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imgBarraTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaterial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtExistencias))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCanEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmpaque))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCanUni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUnidad))
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
        setLocation(point.x - (x+5), point.y - (y+50));
    }//GEN-LAST:event_imgBarraTituloMouseDragged

    private void imgSiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgSiMouseClicked
        siguiente();
    }//GEN-LAST:event_imgSiMouseClicked

    private void txtCanUniKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCanUniKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            siguiente();
        }
    }//GEN-LAST:event_txtCanUniKeyReleased

    private void txtCanEmpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCanEmpKeyPressed
        
    }//GEN-LAST:event_txtCanEmpKeyPressed

    private void txtCanEmpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCanEmpKeyReleased
        revisar();
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            txtCanUni.requestFocus();
        }
    }//GEN-LAST:event_txtCanEmpKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private OTROS.IMAGEN imgBarraTitulo;
    private OTROS.IMAGEN imgNo;
    private OTROS.IMAGEN imgSi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtCanEmp;
    private javax.swing.JTextField txtCanUni;
    private javax.swing.JLabel txtEmpaque;
    private javax.swing.JLabel txtExistencias;
    private javax.swing.JLabel txtMaterial;
    private javax.swing.JLabel txtTipo;
    private javax.swing.JLabel txtUnidad;
    // End of variables declaration//GEN-END:variables
}
