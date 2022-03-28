package GRAFICA.RESUMEN;

import CLASES.material;
import java.awt.MouseInfo;
import java.awt.Point;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import sql.SQLALMACEN;

public class CONSUMOPORPERIODO extends javax.swing.JInternalFrame {
    private int x;
    private int y;
    SimpleDateFormat fSal = new SimpleDateFormat("yyyy-MM-dd");
    ArrayList<material> ultima=new ArrayList();
    ArrayList<material> almacen=null;
    SQLALMACEN modAlmacen=new SQLALMACEN();
    TableColumnModel columnModel = null;
    
    DefaultTableModel dftm = new DefaultTableModel() {
        public boolean isCellEditable(int fila, int columna) {
            if (columna == jTable1.getColumnCount()) {
                return true;
            }
            return false;
        }
    };
    
    public CONSUMOPORPERIODO() {
        initComponents();
        imgBarraTitulo.setBackgroung("IMAGENES/TEXTURAS/marmol.png");
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        imgCerrar.setBackgroung("IMAGENES/ICONOS/cerrar2.png");
        //imgPdf.setBackgroung("IMAGENES/ICONOS/pdf.png");
        imgPdf.trasparente();
        Date hoy = new Date();
        dcInicio.setDate(hoy);
        dcFin.setDate(hoy);
        almacen=modAlmacen.obtenerTodoMateriales();
        crearTabla();
        llenarCB();
    }
    public void llenarCB() {
        ArrayList cb = modAlmacen.ObtenerTiposMaterial();
        cbTipo.removeAllItems();
        cbTipo.addItem("Todos");
        for (int a = 0; a < cb.size(); a++) {
            ArrayList b = (ArrayList) cb.get(a);
            cbTipo.addItem(b.get(0).toString());
        }
    }
    public void crearTabla(){
        columnModel = jTable1.getColumnModel();
        jTable1.setModel(dftm);
        dftm.setColumnIdentifiers(new Object[]{"Can","", "Material","Tipo",/*"Paquetes",""*/});
        columnModel.getColumn(0).setPreferredWidth(73);
        columnModel.getColumn(1).setPreferredWidth(77);
        columnModel.getColumn(2).setPreferredWidth(312);
        columnModel.getColumn(3).setPreferredWidth(312);
    }
    
    public void crearLista() {
        String sql = null;
        ultima.clear();
        if (dcInicio.getDateFormatString() != null && dcFin.getDateFormatString() != null) {
            sql = "SELECT movimientosalmacen.`folio` AS folio, "
                    + "movimientosalmacen.`tipo` AS tipo, movimientosalmacen.`cantidad` AS cantidad, "
                    + "movimientosalmacen.`material` AS idMaterial, movimientosalmacen.`concepto` AS concepto,"
                    + " movimientosalmacen.`cliente` AS cliente, movimientosalmacen.`fecha` AS fecha, "
                    + "movimientosalmacen.`autorizacion` AS autorizacion, almacen.`nombre` AS material, "
                    + "almacen.`tipo` AS Materialtipo, usuarios.`id` AS idUsu, usuarios.`nombre` AS nombre, "
                    + "usuarios.`apellido` AS apellido FROM `movimientosalmacen` movimientosalmacen "
                    + "INNER JOIN `almacen` almacen ON movimientosalmacen.`material` = almacen.`id` "
                    + "INNER JOIN `usuarios` usuarios ON movimientosalmacen.`autorizacion` = usuarios.`id` "
                    + "WHERE movimientosalmacen.tipo=0 AND FECHA BETWEEN '"
                    + fSal.format(dcInicio.getDate()) + " 00:01' AND '"
                    + fSal.format(dcFin.getDate()) + " 23:59' ORDER BY material ASC;";
            ArrayList<material> lista = modAlmacen.obtenerMovimientos(sql);
            for (material mat : lista) {
                boolean pasa=false;
                for (material ulti : ultima) {
                    if(mat.getIdMaterial().equals(ulti.getIdMaterial())){
                        ulti.setCantidad(ulti.getCantidad()+mat.getCantidad());
                        pasa=true;
                        break;
                    }
                }
                if(!pasa){
                    material tem=new material();
                    for (material aux : almacen) {
                        if(mat.getIdMaterial().equals(aux.getIdMaterial())){
                            tem.setIdMaterial(aux.getIdMaterial());
                            tem.setMaterial(aux.getMaterial());
                            tem.setTipo(aux.getTipo());
                            tem.setCantidad(mat.getCantidad());
                            tem.setCantidaPaquetes(aux.getCantidaPaquetes());
                            tem.setMedidaX(aux.getMedidaX());
                            tem.setMedidaY(aux.getMedidaY());
                            tem.setUnidades(aux.getUnidades());
                            tem.setEmpaque(aux.getEmpaque());
                            tem.setPaquete(aux.getPaquete());
                            ultima.add(tem);
                            break;
                        }
                    }
                }
            }
        }
    }

    public void llenarTabla() {
        //new Object[]{"Can","unidades", "Material","Tipo","Paquetes",""});
        borrarTabla();
        for (material tem : ultima) {
            if (cbTipo.getSelectedItem().equals(tem.getTipo()) || cbTipo.getSelectedItem().equals("Todos")) {
                dftm.addRow(new Object[]{tem.getCantidad(), tem.getUnidades()+"s",tem.getMaterial(), 
                    tem.getTipo()/*,tem.getCantidad()/tem.getPaquete(),tem.getEmpaque()+"s"*/
                });
            }
        }
    }

    void borrarTabla() {
        try {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                dftm.removeRow(i);
                i--;
            }
        } catch (Exception e) {
        }

    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imgBarraTitulo = new OTROS.IMAGEN();
        jLabel2 = new javax.swing.JLabel();
        imgPdf = new OTROS.IMAGEN();
        imgCerrar = new OTROS.IMAGEN();
        jLabel1 = new javax.swing.JLabel();
        cbTipo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        dcInicio = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        dcFin = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

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
        jLabel2.setText("CONSUMOS DE ALMACEN");

        imgPdf.setMinimumSize(new java.awt.Dimension(26, 26));
        imgPdf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgPdfMouseClicked(evt);
            }
        });

        imgCerrar.setMinimumSize(new java.awt.Dimension(26, 26));
        imgCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgCerrarMouseClicked(evt);
            }
        });

        imgBarraTitulo.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        imgBarraTitulo.setLayer(imgPdf, javax.swing.JLayeredPane.DEFAULT_LAYER);
        imgBarraTitulo.setLayer(imgCerrar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout imgBarraTituloLayout = new javax.swing.GroupLayout(imgBarraTitulo);
        imgBarraTitulo.setLayout(imgBarraTituloLayout);
        imgBarraTituloLayout.setHorizontalGroup(
            imgBarraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imgBarraTituloLayout.createSequentialGroup()
                .addComponent(imgPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(imgCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        imgBarraTituloLayout.setVerticalGroup(
            imgBarraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(imgBarraTituloLayout.createSequentialGroup()
                .addGroup(imgBarraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imgPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel1.setText("Tipo");

        cbTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTipoItemStateChanged(evt);
            }
        });

        jLabel3.setText("FECHA DE ");

        jLabel4.setText("A");

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dcInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dcFin, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imgBarraTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(dcInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(dcFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void imgPdfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgPdfMouseClicked
        //this.dispose();
        /*for(int x=0;x<jTable1.getColumnCount();x++){
            System.out.println(x+" "+jTable1.getColumn(jTable1.getColumnName(x)).getWidth());
        }*/
    }//GEN-LAST:event_imgPdfMouseClicked

    private void imgBarraTituloMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgBarraTituloMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_imgBarraTituloMousePressed

    private void imgBarraTituloMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgBarraTituloMouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - (x+5), point.y - (y+50));
    }//GEN-LAST:event_imgBarraTituloMouseDragged

    private void imgCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgCerrarMouseClicked
        borrarTabla();
        ultima.clear();
        this.dispose();
    }//GEN-LAST:event_imgCerrarMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        crearLista();
        llenarTabla();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTipoItemStateChanged
        llenarTabla();
    }//GEN-LAST:event_cbTipoItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbTipo;
    private com.toedter.calendar.JDateChooser dcFin;
    private com.toedter.calendar.JDateChooser dcInicio;
    private OTROS.IMAGEN imgBarraTitulo;
    private OTROS.IMAGEN imgCerrar;
    private OTROS.IMAGEN imgPdf;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
