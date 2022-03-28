package GRAFICA.RESUMEN;

import CLASES.material;
import GRAFICA.PRINCIPAL;
import REPORTESPDF.PARAMETROSIMPRESION;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import sql.SQLALMACEN;

public class RESUMENMOVIMIENTOS extends javax.swing.JInternalFrame {

    private int x;
    private int y;
    SimpleDateFormat fVista = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    SimpleDateFormat fSql = new SimpleDateFormat("yyyy-MM-dd");
    ArrayList<material> lista = null;
    TableColumnModel columnModel = null;
    SQLALMACEN modAlma = new SQLALMACEN();
    public DefaultTableModel dftm = new DefaultTableModel() {
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };

    public RESUMENMOVIMIENTOS() {
        int x = (int) (PRINCIPAL.pantallaAncho * 0.9);
        int y = (int) (PRINCIPAL.pantallaAlto * 0.9);
        initComponents();
        this.setSize(new java.awt.Dimension(x, y));
        Date hoy = new Date();
        cInicio.setDate(hoy);
        cFin.setDate(hoy);

        imgBarraTitulo.setBackgroung("IMAGENES/TEXTURAS/marmol.png");
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        imgCerrar.setBackgroung("IMAGENES/ICONOS/cerrar2.png");
        imgAjuste.setBackgroung("IMAGENES/ICONOS/pdf.png");
        crearTabla();
    }

    public void crearTabla() {
        jTable1.setModel(dftm);
        dftm.setColumnIdentifiers(new Object[]{"Folio","Mov","Cantidad", "Material", "Tipo", "Cliente/Proveedor", "Observaciones", "Auto", "Fecha"});
        columnModel = jTable1.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(41);
        columnModel.getColumn(1).setPreferredWidth(37);
        columnModel.getColumn(2).setPreferredWidth(64);
        columnModel.getColumn(3).setPreferredWidth(120);
        columnModel.getColumn(4).setPreferredWidth(133);
        columnModel.getColumn(5).setPreferredWidth(105);
        columnModel.getColumn(6).setPreferredWidth(150);
        columnModel.getColumn(7).setPreferredWidth(40);
        columnModel.getColumn(8).setPreferredWidth(110);

        columnModel.getColumn(0).setMaxWidth(40);
        columnModel.getColumn(1).setMaxWidth(59);
        columnModel.getColumn(2).setMaxWidth(266);
        columnModel.getColumn(3).setMaxWidth(227);
        columnModel.getColumn(4).setMaxWidth(153);
        columnModel.getColumn(5).setMaxWidth(320);
        columnModel.getColumn(7).setMaxWidth(89);
        columnModel.getColumn(8).setMaxWidth(108);
    }

    public void llenarTabla() {
        borrarTabla();
        for (material mat : lista) {
            dftm.addRow(new Object[]{
                mat.getId(), mat.getPieza(),mat.getCantidad(), mat.getMaterial(), mat.getTipo(),
                mat.getComponente(), mat.getDescripcion(), mat.getModelo(), fVista.format(mat.getFecha())
            });
        }
    }

    public void borrarTabla() {
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            dftm.removeRow(i);
            i--;
        }
    }

    public void pdf() {
        String rutaArchivo = (new File("")).getAbsolutePath();
        rutaArchivo = rutaArchivo + "\\archivos\\reportesPdf";
        Map parametros = new HashMap();
        parametros.put("fInicio", cInicio.getDate());
        parametros.put("fFin", cFin.getDate());
        PARAMETROSIMPRESION.impresionReporte(lista, parametros, "MOVIMIENTOSALMACEN", rutaArchivo);

    }

    public void buscar() {
        String sql = "SELECT movimientosalmacen.`folio` AS folio, "
                + "movimientosalmacen.`tipo` AS tipo, "
                + "movimientosalmacen.`cantidad` AS cantidad, "
                + "movimientosalmacen.`material` AS idMaterial, "
                + "movimientosalmacen.`concepto` AS concepto, "
                + "movimientosalmacen.`cliente` AS cliente, "
                + "movimientosalmacen.`fecha` AS fecha,"
                + " movimientosalmacen.`autorizacion` AS autorizacion, "
                + "almacen.`nombre` AS material, almacen.`tipo` AS Materialtipo, "
                + "usuarios.`id` AS idUsu, usuarios.`nombre` AS nombre, "
                + "usuarios.`apellido` AS apellido FROM "
                + "`movimientosalmacen` movimientosalmacen INNER JOIN `almacen` "
                + "almacen ON movimientosalmacen.`material` = almacen.`id` "
                + "INNER JOIN `usuarios` usuarios ON movimientosalmacen.`autorizacion` = usuarios.`id` "
                + "WHERE";
        if (txtCliente.getText().length() > 0) {
            sql = sql + " material=" + txtCliente.getText() + " AND";
        }
        sql = sql + " fecha BETWEEN '" + fSql.format(cInicio.getDate()) + " 00:01' AND '" + fSql.format(cFin.getDate()) + " 23:59'";
        if (!cbTipo.getSelectedItem().equals("Todas")) {
            System.out.println(cbTipo.getSelectedItem().toString());
            if (cbTipo.getSelectedItem().equals("Entradas")) {
                sql = sql + " AND movimientosalmacen.`tipo`=1";
            } else {
                sql = sql + " AND movimientosalmacen.`tipo`=0";
            }
        }
        sql = sql + " ORDER BY fecha DESC";
        //System.out.println("sql " + sql);
        lista = modAlma.obtenerMovimientos(sql);
        llenarTabla();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imgBarraTitulo = new OTROS.IMAGEN();
        jLabel2 = new javax.swing.JLabel();
        imgAjuste = new OTROS.IMAGEN();
        imgCerrar = new OTROS.IMAGEN();
        jLabel1 = new javax.swing.JLabel();
        cInicio = new com.toedter.calendar.JDateChooser();
        cFin = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cbTipo = new javax.swing.JComboBox<>();

        setMinimumSize(new java.awt.Dimension(879, 475));

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
        jLabel2.setText("RESUMEN DE MOVIMIENTOS DE ALMACEN");

        imgAjuste.setMinimumSize(new java.awt.Dimension(26, 26));
        imgAjuste.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgAjusteMouseClicked(evt);
            }
        });

        imgCerrar.setMinimumSize(new java.awt.Dimension(26, 26));
        imgCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgCerrarMouseClicked(evt);
            }
        });

        imgBarraTitulo.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        imgBarraTitulo.setLayer(imgAjuste, javax.swing.JLayeredPane.DEFAULT_LAYER);
        imgBarraTitulo.setLayer(imgCerrar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout imgBarraTituloLayout = new javax.swing.GroupLayout(imgBarraTitulo);
        imgBarraTitulo.setLayout(imgBarraTituloLayout);
        imgBarraTituloLayout.setHorizontalGroup(
            imgBarraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imgBarraTituloLayout.createSequentialGroup()
                .addComponent(imgAjuste, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(imgCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        imgBarraTituloLayout.setVerticalGroup(
            imgBarraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(imgBarraTituloLayout.createSequentialGroup()
                .addGroup(imgBarraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imgAjuste, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel1.setText("Periodo de");

        jLabel4.setText("Id de articulo");

        txtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClienteKeyReleased(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("A");

        cbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas", "Entradas", "Salidas" }));

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
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1))
                            .addComponent(jLabel4))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCliente)
                            .addComponent(cInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(cFin, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imgBarraTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void imgAjusteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgAjusteMouseClicked
        /*for(int x=0;x<jTable1.getColumnCount();x++){
        System.out.println(x+" "+jTable1.getColumn(jTable1.getColumnName(x)).getWidth());
        }*/
        pdf();
    }//GEN-LAST:event_imgAjusteMouseClicked

    private void imgBarraTituloMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgBarraTituloMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_imgBarraTituloMousePressed

    private void imgBarraTituloMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgBarraTituloMouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - (x + 5), point.y - (y + 50));
    }//GEN-LAST:event_imgBarraTituloMouseDragged

    private void imgCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgCerrarMouseClicked
        this.dispose();
    }//GEN-LAST:event_imgCerrarMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        buscar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyReleased
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            buscar();
       }
       
    }//GEN-LAST:event_txtClienteKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser cFin;
    private com.toedter.calendar.JDateChooser cInicio;
    private javax.swing.JComboBox<String> cbTipo;
    private OTROS.IMAGEN imgAjuste;
    private OTROS.IMAGEN imgBarraTitulo;
    private OTROS.IMAGEN imgCerrar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtCliente;
    // End of variables declaration//GEN-END:variables
}
