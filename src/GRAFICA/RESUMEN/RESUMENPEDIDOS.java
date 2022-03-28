package GRAFICA.RESUMEN;

import CLASES.PEDIDO;
import EXPORTAREXCEL.RESUMENPEDIDOSEXCEL;
import GRAFICA.PRINCIPAL;
import REPORTESPDF.PARAMETROSIMPRESION;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import sql.SQLPEDIDOS;

public class RESUMENPEDIDOS extends javax.swing.JInternalFrame {

    private int x;
    private int y;
    Object[] titulo = new Object[]{"Folio", "Prioridad", "Cliente", "Componente", "Modelo", "Color",
        "Tapizado", "Cant","Entre", "Pedido", "Entrega","Estado"};
    ArrayList<PEDIDO> lista = new ArrayList();
    SimpleDateFormat fSalida = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat fSql = new SimpleDateFormat("yyyy-MM-dd");
    TableColumnModel columnModel = null;
    public static DefaultTableModel dftm = new DefaultTableModel() {
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };

    public RESUMENPEDIDOS() {
        int x = (int) (PRINCIPAL.pantallaAncho * 0.9);
        int y = (int) (PRINCIPAL.pantallaAlto * 0.9);
        initComponents();
        this.setSize(new java.awt.Dimension(x, y));
        imgBarraTitulo.setBackgroung("IMAGENES/TEXTURAS/marmol.png");
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        imgCerrar.setBackgroung("IMAGENES/ICONOS/cerrar2.png");
        imgAjuste.trasparente();
        imgPdf.setBackgroung("IMAGENES/ICONOS/pdf.png");
        imgExcel.setBackgroung("IMAGENES/ICONOS/excel.png");
        crearTabla();
    }

    public void crearTabla() {
        jTable1.setModel(dftm);

        dftm.setColumnIdentifiers(titulo);
        columnModel = jTable1.getColumnModel();
        /*columnModel.getColumn(0).setPreferredWidth(53);
        columnModel.getColumn(1).setPreferredWidth(38);
        columnModel.getColumn(2).setPreferredWidth(88);
        columnModel.getColumn(3).setPreferredWidth(118);
        columnModel.getColumn(4).setPreferredWidth(118);
        columnModel.getColumn(5).setPreferredWidth(99);
        columnModel.getColumn(6).setPreferredWidth(99);
        columnModel.getColumn(7).setPreferredWidth(32);
        columnModel.getColumn(8).setPreferredWidth(32);
        columnModel.getColumn(9).setPreferredWidth(49);
        columnModel.getColumn(10).setPreferredWidth(49);
        columnModel.getColumn(11).setPreferredWidth(49);*/

        columnModel.getColumn(0).setMaxWidth(66);
        columnModel.getColumn(1).setMaxWidth(66);
        columnModel.getColumn(2).setMaxWidth(128);
        columnModel.getColumn(3).setMaxWidth(151);
        columnModel.getColumn(4).setMaxWidth(151);
        columnModel.getColumn(5).setMaxWidth(174);
        columnModel.getColumn(6).setMaxWidth(174);
        columnModel.getColumn(7).setMaxWidth(39);
        columnModel.getColumn(8).setMaxWidth(39);
        columnModel.getColumn(9).setMaxWidth(68);
        columnModel.getColumn(10).setMaxWidth(68);
        columnModel.getColumn(11).setMaxWidth(68);
    }

    public void llenarTabla() {
        borrarTabla();
        String estado=null;
        for (PEDIDO ped : lista) {
            String folio;
            if(ped.getReparacion()){
                folio="REP "+ped.getFolio();
            }else{
                folio=ped.getFolio();
            }
            switch(ped.getEstado()){
                case 0:estado="Cancelado";break;
                case 1:estado="En Proceso";break;
                case 2:estado="Entregado";break;
                case 3:estado="Proc Repa";break;
                case 4:estado="Repa Entre";break;
            }
            dftm.addRow(new Object[]{folio,ped.getPrioridad(), ped.getCliente(), ped.getComponente(),
                ped.getModelo(), ped.getColor(), ped.getTapizado(), ped.getCantidad(),
                ped.getEcho(), fSalida.format(ped.getFechaCreacion()),
                fSalida.format(ped.getFechaEntrega()), estado});

        }
    }

    public void borrarTabla() {
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            dftm.removeRow(i);
            i--;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imgBarraTitulo = new OTROS.IMAGEN();
        jLabel2 = new javax.swing.JLabel();
        imgPdf = new OTROS.IMAGEN();
        imgCerrar = new OTROS.IMAGEN();
        imgExcel = new OTROS.IMAGEN();
        imgAjuste = new OTROS.IMAGEN();
        cbTipo = new javax.swing.JComboBox<>();
        txtFoClie = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        fInicio = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        fFin = new com.toedter.calendar.JDateChooser();
        cbForma = new javax.swing.JComboBox<>();

        setMaximumSize(new java.awt.Dimension(1229, 1000));
        setMinimumSize(new java.awt.Dimension(845, 480));
        setPreferredSize(new java.awt.Dimension(845, 506));

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
        jLabel2.setText("RESUMEN DE PEDIDOS");

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

        imgExcel.setMinimumSize(new java.awt.Dimension(26, 26));
        imgExcel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgExcelMouseClicked(evt);
            }
        });

        imgAjuste.setMinimumSize(new java.awt.Dimension(26, 26));
        imgAjuste.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgAjusteMouseClicked(evt);
            }
        });

        imgBarraTitulo.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        imgBarraTitulo.setLayer(imgPdf, javax.swing.JLayeredPane.DEFAULT_LAYER);
        imgBarraTitulo.setLayer(imgCerrar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        imgBarraTitulo.setLayer(imgExcel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        imgBarraTitulo.setLayer(imgAjuste, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout imgBarraTituloLayout = new javax.swing.GroupLayout(imgBarraTitulo);
        imgBarraTitulo.setLayout(imgBarraTituloLayout);
        imgBarraTituloLayout.setHorizontalGroup(
            imgBarraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imgBarraTituloLayout.createSequentialGroup()
                .addComponent(imgPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imgExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imgAjuste, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imgCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        imgBarraTituloLayout.setVerticalGroup(
            imgBarraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(imgBarraTituloLayout.createSequentialGroup()
                .addGroup(imgBarraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imgPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgAjuste, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        cbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FOLIO", "CLIENTE" }));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel3.setText("Y");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.setPreferredSize(new java.awt.Dimension(7, 14));

        cbForma.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PEDIDO", "ENTREGA" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgBarraTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 809, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbTipo, 0, 98, Short.MAX_VALUE)
                            .addComponent(cbForma, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fFin, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtFoClie))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imgBarraTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(fInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(fFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(cbForma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFoClie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void imgPdfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgPdfMouseClicked
        try {
            if (lista.size() > 0) {
                String rutaArchivo = (new File("")).getAbsolutePath();
                rutaArchivo = rutaArchivo + "\\archivos\\reportesPdf";
                Map parametros = new HashMap();
                parametros.put("inicio", fSalida.format(fInicio.getDate()));
                parametros.put("fin", fSalida.format(fFin.getDate()));
                parametros.put("tipo", cbTipo.getSelectedItem());
                parametros.put("clave", txtFoClie.getText());
                PARAMETROSIMPRESION.impresionReporte(lista, parametros, "PDFResumenPedidos", rutaArchivo);

            } else {
                JOptionPane.showMessageDialog(null, "Lista vacia");
            }
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(rootPane, e);
        }

    }//GEN-LAST:event_imgPdfMouseClicked

    private void imgBarraTituloMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgBarraTituloMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_imgBarraTituloMousePressed

    private void imgBarraTituloMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgBarraTituloMouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - (x + 5), point.y - (y + 50));
    }//GEN-LAST:event_imgBarraTituloMouseDragged

    private void imgCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgCerrarMouseClicked
        lista.clear();
        borrarTabla();
        this.dispose();
    }//GEN-LAST:event_imgCerrarMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String tipo = null;
        String forma=null;
        if (cbForma.getSelectedItem().equals("PEDIDO")) {
            tipo = "fechaCreacion";
            forma="fechaCreacion DESC";
        } else {
            tipo = "fechaEntrega";
            forma="fechaEntrega ASC";
        }
        String sql = "SELECT pedidos.`id` AS id, pedidos.`folio` AS folio, "
                + "pedidos.`cliente` AS cliente, pedidos.`idcomponente` AS idcomponente, "
                + "pedidos.`observaciones` AS pedidos_observaciones,pedidos.`fechaCreacion` AS fechaCreacion, "
                + "pedidos.`fechaEntrega` AS fechaEntrega, pedidos.`cantidad` AS cantidad, "
                + "pedidos.`echo` AS echo, pedidos.`entregado` AS entregado, "
                + "pedidos.`color` AS color,pedidos.`tapizado` AS tapizado, "
                + "pedidos.`prioridad` AS prioridad, componentes.`nombre` AS componente, "
                + "componentes.`modelo` AS modelo,componentes.`medidaX` AS X, "
                + "componentes.`medidaY` AS Y, componentes.`medidaZ` AS Z, "
                + "componentes.`observaciones` AS componentes_observaciones, "
                + "pedidos.`reparacion` AS reparacion,  "
                + "pedidos.`estado` AS estado FROM `componentes` componentes "
                + "INNER JOIN `pedidos` pedidos ON componentes.`id` = pedidos.`idcomponente` "
                + "WHERE ";
        boolean entra = false;
        if (cbTipo.getSelectedItem().equals("FOLIO") && txtFoClie.getText().length() > 0) {
            sql = sql + "folio='" + txtFoClie.getText() + "'";
            entra = true;
        } else {
            if (fInicio.getDate() != null && fFin.getDate() != null) {
                sql = sql + tipo;
                sql = sql + " BETWEEN '" + fSql.format(fInicio.getDate()) + "' AND '" + fSql.format(fFin.getDate()) + "' ";
                if (txtFoClie.getText().length() > 0) {
                    sql = sql + "AND cliente='" + txtFoClie.getText() + "'";
                }
                entra = true;
            } else {
                JOptionPane.showMessageDialog(null, "Es necesario fechas de inicio y final o un folio");
            }
        }
        if (entra) {
            sql = sql + " ORDER BY " + forma + ";";
            SQLPEDIDOS modPed = new SQLPEDIDOS();
            lista = modPed.obtenerPedidosFiltrado(sql);
            llenarTabla();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void imgExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgExcelMouseClicked
        /*for(int x=0;x<jTable1.getColumnCount();x++){
            System.out.println(x+" "+jTable1.getColumn(jTable1.getColumnName(x)).getWidth());
        }*/
        if (lista.size() > 0) {
            RESUMENPEDIDOSEXCEL rpe = new RESUMENPEDIDOSEXCEL();
            rpe.modificarplantilla(titulo, lista);
        } else {
            JOptionPane.showMessageDialog(null, "Lista vacia");
        }

    }//GEN-LAST:event_imgExcelMouseClicked

    private void imgAjusteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgAjusteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_imgAjusteMouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        
    }//GEN-LAST:event_jTable1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbForma;
    private javax.swing.JComboBox<String> cbTipo;
    private com.toedter.calendar.JDateChooser fFin;
    private com.toedter.calendar.JDateChooser fInicio;
    private OTROS.IMAGEN imgAjuste;
    private OTROS.IMAGEN imgBarraTitulo;
    private OTROS.IMAGEN imgCerrar;
    private OTROS.IMAGEN imgExcel;
    private OTROS.IMAGEN imgPdf;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtFoClie;
    // End of variables declaration//GEN-END:variables
}
