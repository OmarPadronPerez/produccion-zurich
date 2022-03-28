package GRAFICA.ALMACEN.AJUSTES;

import CLASES.material;
import GRAFICA.*;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import sql.SQLALMACEN;

public class CONFIRMARMOVIMIENTO extends javax.swing.JInternalFrame {
    private int x;
    private int y;
    private int origen;
    SQLALMACEN modAlma = new SQLALMACEN();
    ArrayList<material> lista = null;
    SimpleDateFormat fSalida = new SimpleDateFormat("dd MMMM yyyy");
    TableColumnModel columnModel = null;
    public DefaultTableModel dftm = new DefaultTableModel() {
        public boolean isCellEditable(int fila, int columna) {
            if (columna == jTable1.getColumnCount()) {
                return true;
            }
            return false;
        }
    };
    
    
    public CONFIRMARMOVIMIENTO(int origen, ArrayList<material> lista) {
        initComponents();
        this.origen=origen;
        this.lista=lista;
        imgBarraTitulo.setBackgroung("IMAGENES/TEXTURAS/marmol.png");
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        imgSi.setBackgroung("IMAGENES/ICONOS/si.png");
        imgNo.setBackgroung("IMAGENES/ICONOS/no.png");
        crearTabla();
        if (origen == 0) {//es salida
            txtTitulo.setText("REVISION DE SALIDAS DE ALMACEN");
            txtclie.setText("Cliente");
        } else {

            txtTitulo.setText("REVISION DE ENTRADAS A ALMACEN");
            txtclie.setText("Proveedor");
        }
        //jpFondo.setBackground(new Color(255, 255, 204));
        llenarSalida();
        llenarCampos();
    }
    public void llenarSalida() {
        borrarTabla();
        for (material mat : lista) {
            float fin;
            if(origen == 0){
                fin=mat.getCantidad() - mat.getMedidaZ();
            }else{
                fin=mat.getCantidad() + mat.getMedidaZ();
            }
            
            dftm.addRow(new Object[]{mat.getMedidaZ(), mat.getMaterial(), mat.getTipo(), mat.getPrecioPaquete(), mat.getCantidad(), fin/*, fecha*/});
        }
    }
    
    public void llenarCampos() {
        Date hoy = new Date();
        txtFecha.setText(fSalida.format(hoy));
        int folio = modAlma.obtenerFolioAlmacen();
        txtFolio.setText(String.valueOf(folio + 1));
        txtAuto.setText(PRINCIPAL.user.getNombre() + " " + PRINCIPAL.user.getApellido());
    }
    public void crearTabla() {
        columnModel = jTable1.getColumnModel();
        jTable1.setModel(dftm);
        dftm.setColumnIdentifiers(new Object[]{"Cant", "Nombre", "Tipo", "Precio", "Existencias", "Final"/*, "Actualizacion"*/});
        
        columnModel.getColumn(0).setPreferredWidth(66);
        columnModel.getColumn(1).setPreferredWidth(230);
        columnModel.getColumn(2).setPreferredWidth(220);
        columnModel.getColumn(3).setPreferredWidth(84);
        columnModel.getColumn(4).setPreferredWidth(87);
        columnModel.getColumn(5).setPreferredWidth(87);
    }
    public void borrarTabla() {
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            dftm.removeRow(i);
            i--;
        }
    }
    
    
    public void imprimir(int folio) {
        try {
            String ruta=null;
            Connection con = PRINCIPAL.dataSource.getConnection();
            if(origen==0){
                ruta = System.getProperty("user.dir") + "/archivos/reportesPdf/MOVIMIENTOALMACENSALIDA.jasper";
            }else{
                ruta = System.getProperty("user.dir") + "/archivos/reportesPdf/MOVIMIENTOALMACENSENTRADA.jasper";
            }
            JasperReport jr = (JasperReport) JRLoader.loadObjectFromFile(ruta);
            Map parametros = new HashMap<String, String>();
            parametros.put("folio", folio);
            if (origen == 0) {
                parametros.put("titulo", "Salida de almacen");
            } else {
                parametros.put("titulo", "Entrada a almacen");
            }

            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    public void siguiente(){
        if (this.txtCliente.getText().length() > 0) {
            int folio = modAlma.guardarMovimientos(PRINCIPAL.user, this.lista, this.txtCliente.getText().toUpperCase(), this.txtCon.getText().toUpperCase(), this.origen);
            if (folio > 0) {
                txtCliente.setText("");
                imprimir(folio);
                MOVIMIENTOS.cerrar();
                
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Falla al guardar");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Es necesario un cliente");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imgBarraTitulo = new OTROS.IMAGEN();
        txtTitulo = new javax.swing.JLabel();
        imgNo = new OTROS.IMAGEN();
        imgSi = new OTROS.IMAGEN();
        jPanel1 = new javax.swing.JPanel();
        txtclie = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCon = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JLabel();
        txtFolio = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtAuto = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(880, 500));

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
        txtTitulo.setText("PLANTILLA");

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
                .addComponent(txtTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
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

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        txtclie.setText("Cliente");

        txtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClienteKeyReleased(evt);
            }
        });

        jLabel3.setText("Observaciones");

        txtCon.setColumns(20);
        txtCon.setRows(2);
        txtCon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtConKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(txtCon);

        jLabel4.setText("Fecha");

        txtFecha.setText("jLabel5");

        txtFolio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtFolio.setText("XXXX");

        jLabel7.setText("Folio");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jLabel8.setText("Autorizacion");

        txtAuto.setText("jLabel9");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtclie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 222, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtFecha)
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtFolio))
                            .addComponent(jScrollPane1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtAuto)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtclie)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtFecha)
                    .addComponent(txtFolio)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtAuto))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgBarraTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imgBarraTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void imgNoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgNoMouseClicked
        this.dispose();
        /*for(int x=0;x<jTable1.getColumnCount();x++){
            System.out.println(x+" "+jTable1.getColumn(jTable1.getColumnName(x)).getWidth());
        }*/
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

    private void txtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCon.requestFocus();
        }
    }//GEN-LAST:event_txtClienteKeyReleased

    private void txtConKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConKeyReleased
        
        
    }//GEN-LAST:event_txtConKeyReleased

    private void txtConKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConKeyPressed
       if (evt.getKeyCode() ==KeyEvent.VK_ENTER) {
            siguiente();
        }
    }//GEN-LAST:event_txtConKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private OTROS.IMAGEN imgBarraTitulo;
    private OTROS.IMAGEN imgNo;
    private OTROS.IMAGEN imgSi;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel txtAuto;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextArea txtCon;
    private javax.swing.JLabel txtFecha;
    private javax.swing.JLabel txtFolio;
    private javax.swing.JLabel txtTitulo;
    private javax.swing.JLabel txtclie;
    // End of variables declaration//GEN-END:variables
}
