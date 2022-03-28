package GRAFICA.ALMACEN.ORDENCOMPRA;

import CLASES.material;
import GRAFICA.PRINCIPAL;
import OTROS.AUXILIAR;
import java.awt.MouseInfo;
import java.awt.Point;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import sql.SQLALMACEN;

public class CONFIRMARCOMPRA extends javax.swing.JInternalFrame {
    private int x;
    private int y;
    ArrayList<material> lista;
    DecimalFormat for2 = new DecimalFormat("#,###.##");
    TableColumnModel columnModel = null;
    DefaultTableModel dftm = new DefaultTableModel() {
        public boolean isCellEditable(int fila, int columna) {
            if (columna == 0||columna == 3) {
                return true;
            }
            return false;
        }
    };
    
    public CONFIRMARCOMPRA(ArrayList<material> lista) {
        initComponents();
        this.lista=lista;
        imgBarraTitulo.setBackgroung("IMAGENES/TEXTURAS/marmol.png");
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        imgCerrar.setBackgroung("IMAGENES/ICONOS/si.png");
        imgAjuste.setBackgroung("IMAGENES/ICONOS/cerrar2.png");
        crearTabla();
        llenarTabla();
        
    }
    
    public void crearTabla() {
        jTable1.setModel(dftm);
        dftm.setColumnIdentifiers(new Object[]{"Cant", "", "Material", "Descripcion", "Tipo", "P/U","SubTotal","IVA", "Total"});
        columnModel = jTable1.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(35);
        columnModel.getColumn(1).setPreferredWidth(57);
        columnModel.getColumn(2).setPreferredWidth(142);
        columnModel.getColumn(3).setPreferredWidth(217);
        columnModel.getColumn(4).setPreferredWidth(104);
        columnModel.getColumn(5).setPreferredWidth(50);
        columnModel.getColumn(6).setPreferredWidth(54);
        columnModel.getColumn(7).setPreferredWidth(50);
        columnModel.getColumn(8).setPreferredWidth(49);
    }
    
    public void llenarTabla() {
        float total = 0;
        float ivatot=0;
        borrarTabla();
        for (material mat : lista) {
            float subT =0;
            float iva=0;
            if(mat.getMedidaZ()>0){
                iva=(float) ((mat.getCantidad()*mat.getPrecioPaquete())*0.16);
            }
            subT=mat.getCantidad()*mat.getPrecioPaquete()+iva;
                
            total = subT + total;
            ivatot=ivatot+iva;
            String subTS = for2.format(subT);
            //dftm.setColumnIdentifiers(new Object[]{"Cant", "", "Material", "Descripcion", "Tipo", "P/U","SubTotal","IVA", "Total"});
            dftm.addRow(new Object[]{mat.getCantidad(), mat.getEmpaque(), mat.getMaterial(),
                mat.getDescripcion(), mat.getTipo(), mat.getPrecioPaquete(),
                mat.getPrecioPaquete()*mat.getCantidad(),for2.format(iva) ,subTS});
        }
        txtIva.setText(for2.format(ivatot));
        txtTotal.setText(for2.format(total));
    }
    
    void borrarTabla() {
        for (int i = 0; i < this.jTable1.getRowCount(); i++) {
            this.dftm.removeRow(i);
            i--;
        }
    }
    

    public void hacerpdf(int folio){
        String ruta = System.getProperty("user.dir") + "/archivos/reportesPdf/ORDENDECOMPRA.jasper";
        JasperReport jr;
        
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            jr = (JasperReport) JRLoader.loadObjectFromFile(ruta);
            Map parametros = new HashMap<String, String>();
            parametros.put("folio", folio );
            parametros.put("reimpresion", false);
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);
            con.close();
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "Error al crear lista");
            
            Logger.getLogger(CONFIRMARCOMPRA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CONFIRMARCOMPRA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imgBarraTitulo = new OTROS.IMAGEN();
        jLabel2 = new javax.swing.JLabel();
        imgAjuste = new OTROS.IMAGEN();
        imgCerrar = new OTROS.IMAGEN();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtProveedor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtIva = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(858, 503));

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
        jLabel2.setText("CONFIRMAR ORDEN DE COMPRA");

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
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Total");

        txtTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        jLabel3.setText("Proveedor");

        jLabel4.setText("IVA");

        txtIva.setEditable(false);
        txtIva.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgBarraTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtProveedor))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(txtIva))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imgBarraTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void imgAjusteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgAjusteMouseClicked
        this.dispose();
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
    if (txtProveedor.getText().length() > 0) {
            SQLALMACEN modAlmacen = new SQLALMACEN();
            int folio = modAlmacen.guardarOrdenCompra(lista, txtProveedor.getText().toUpperCase());
            hacerpdf(folio);
            System.out.println("folio" + folio);
            NUEVACOMPRA.seguir=false;
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Es necesario un proveedor", "Error al guardar", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_imgCerrarMouseClicked

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private OTROS.IMAGEN imgAjuste;
    private OTROS.IMAGEN imgBarraTitulo;
    private OTROS.IMAGEN imgCerrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtIva;
    private javax.swing.JTextField txtProveedor;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
