package GRAFICA.CALCULAR;

import CLASES.COMPONENTE;
import CLASES.material;
import EXPORTAREXCEL.MATERIALNECESARIOEXCEL;
import OTROS.CALCULARMATERIAL;
import REPORTESPDF.PARAMETROSIMPRESION;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import sql.SQLALMACEN;

public class CALCULARMATERIALGENERICO extends javax.swing.JInternalFrame {
    private int x;
    private int y;
    int seleccion=1;
    SQLALMACEN modAlmacen= new SQLALMACEN();
    ArrayList<COMPONENTE> pedido=null;
    ArrayList<material> listaMaterial=null;
    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
    TableColumnModel columnModel = null;
    DecimalFormat for4 = new DecimalFormat("#,###.####");
    DecimalFormat for2 = new DecimalFormat("#,###.##");
    String titulo;
    DefaultTableModel dftm = new DefaultTableModel() {
        public boolean isCellEditable(int fila, int columna) {
            if (columna == jTable1.getColumnCount()) {
                return true;
            }
            return false;
        }
    };
    
    public CALCULARMATERIALGENERICO(ArrayList<COMPONENTE> lista, String titulo) {
        pedido=lista;
        initComponents();
        imgBarraTitulo.setBackgroung("IMAGENES/TEXTURAS/marmol.png");
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        imgCerrar.setBackgroung("IMAGENES/ICONOS/cerrar2.png");
        imgPdf.setBackgroung("IMAGENES/ICONOS/pdf.png");
        imgExcel.setBackgroung("IMAGENES/ICONOS/excel.png");
        imgPdf.trasparente();
        this.titulo=titulo;
        llenarcbMaterial();
        crearTabla();
        cambiarTabla();
        listaMaterial=new ArrayList();
        CALCULARMATERIAL cm =new CALCULARMATERIAL();
        listaMaterial = cm.calcularMaterial(pedido,true);
        cm.limpiar();
        llenarTabla();
    }
    public void crearTabla(){
         jTable1.setModel(dftm);
        columnModel = jTable1.getColumnModel();
        dftm.setColumnIdentifiers(new Object[]{""});
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
    }
    
    public void cambiarTabla(){
        switch(seleccion){
            case 1:
                dftm.setColumnIdentifiers(new Object[]{"Material","Tipo","Cantidad","P/U","Total"});
            break;
            
            case 2:
                dftm.setColumnIdentifiers(new Object[]{"Material","Tipo","Paquete","P/U","Total"});
            break;
        }
    }
    public void llenarcbMaterial() {
        ArrayList tiposMaterial=modAlmacen.ObtenerTiposMaterial();
        cbMaterial.removeAllItems();
        cbMaterial.addItem("TODOS");
        for (int a = 0; a < tiposMaterial.size(); a++) {
            ArrayList lis2 = (ArrayList) tiposMaterial.get(a);
            cbMaterial.addItem(lis2.get(0).toString());
        }
    }
    
    public void llenarTabla() {
        float tTotal = 0;
        cambiarTabla();
        columnModel.getColumn(2).setCellRenderer(rightRenderer);
        borrarTabla();
        for (material mat : listaMaterial) {
            if (mat.getTipo().equals(cbMaterial.getSelectedItem()) || cbMaterial.getSelectedItem().equals("TODOS")) {
                float pu = Float.valueOf(for4.format(mat.getPrecioUnidad()));
                String puS = "$ " + for4.format(pu);
                float total = pu * mat.getCantidad();
                String totalS = "$ " + for2.format(total);
                switch (seleccion) {
                    case 1://"Material","Tipo","Cantidad","P/U","Total"
                        dftm.addRow(new Object[]{mat.getMaterial(), mat.getTipo(),
                            for4.format(mat.getCantidad()), puS, totalS});
                        break;
                    case 2://"Material","Tipo","Paquete","P/U","Total"
                        puS = "$ " + for4.format(mat.getPrecioPaquete());
                        float paquete = (mat.getCantidad() / mat.getPaquete()) ;
                        dftm.addRow(new Object[]{mat.getMaterial(), mat.getTipo(), for4.format(paquete), puS, totalS});
                        break;
                    //}
                }
                tTotal = total + tTotal;
            }
        }

        txtTotal.setText("$ " + for2.format(tTotal));
    }
    void borrarTabla() {
        for (int i = 0; i < this.jTable1.getRowCount(); i++) {
            this.dftm.removeRow(i);
            i--;
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imgBarraTitulo = new OTROS.IMAGEN();
        jLabel2 = new javax.swing.JLabel();
        imgCerrar = new OTROS.IMAGEN();
        imgExcel = new OTROS.IMAGEN();
        imgPdf = new OTROS.IMAGEN();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnForma = new javax.swing.JButton();
        cbMaterial = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();

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
        jLabel2.setText("MATERIAL PARA PRODUCCION");

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

        imgPdf.setMinimumSize(new java.awt.Dimension(26, 26));
        imgPdf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgPdfMouseClicked(evt);
            }
        });

        imgBarraTitulo.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        imgBarraTitulo.setLayer(imgCerrar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        imgBarraTitulo.setLayer(imgExcel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        imgBarraTitulo.setLayer(imgPdf, javax.swing.JLayeredPane.DEFAULT_LAYER);

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
                .addComponent(imgCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        imgBarraTituloLayout.setVerticalGroup(
            imgBarraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(imgBarraTituloLayout.createSequentialGroup()
                .addGroup(imgBarraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imgCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btnForma.setText("Por paquete");
        btnForma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFormaActionPerformed(evt);
            }
        });

        cbMaterial.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbMaterialItemStateChanged(evt);
            }
        });

        jLabel1.setText("Material");

        jLabel3.setText("Total");

        txtTotal.setEditable(false);
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(cbMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnForma, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(imgBarraTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imgBarraTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnForma)
                    .addComponent(jLabel3)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void imgBarraTituloMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgBarraTituloMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_imgBarraTituloMousePressed

    private void imgBarraTituloMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgBarraTituloMouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - (x+5), point.y - (y+50));
    }//GEN-LAST:event_imgBarraTituloMouseDragged

    private void imgCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgCerrarMouseClicked
        
        this.dispose();
    }//GEN-LAST:event_imgCerrarMouseClicked

    private void btnFormaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFormaActionPerformed
         seleccion++;
        if(seleccion>2){
            seleccion=1;
        }
        switch(seleccion){
            case 1:
                btnForma.setText("Por paquete");
            break;
            
            case 2:
                btnForma.setText("Por cantidad");
            break;
        }
        cambiarTabla();
        llenarTabla();
    }//GEN-LAST:event_btnFormaActionPerformed

    private void imgExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgExcelMouseClicked
       MATERIALNECESARIOEXCEL mne=new MATERIALNECESARIOEXCEL();
       mne.modificarplantilla(new Object[]{"Material","Tipo","Cantidad","Paquete","P/U","Total"}, listaMaterial, null, null);
    }//GEN-LAST:event_imgExcelMouseClicked

    private void imgAjusteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgAjuste2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_imgAjuste2MouseClicked

    private void cbMaterialItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbMaterialItemStateChanged
        try {
            llenarTabla();
        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_cbMaterialItemStateChanged

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1KeyReleased

    private void imgPdfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgAjusteMouseClicked
       try {
            if (listaMaterial.size() > 0) {
                ArrayList<material> tem=listaMaterial;
                Collections.sort(tem, new Comparator<material>() {
                    public int compare(material obj1, material obj2) {
                        return obj1.getTipo().compareTo(obj2.getTipo());
                    }
                });
                for(material mat:tem){
                    float paquete = (mat.getCantidad() / mat.getPaquete()) ;
                    mat.setCantidaPaquetes(paquete);
                }
                String rutaArchivo = (new File("")).getAbsolutePath();
                rutaArchivo = rutaArchivo + "\\archivos\\reportesPdf";
                Map parametros = new HashMap();
                
                parametros.put("titulo", titulo);
                PARAMETROSIMPRESION.impresionReporte(listaMaterial, parametros, "MATERIALREQUERIDOPDF", rutaArchivo);

            } else {
                JOptionPane.showMessageDialog(null, "Lista vacia");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_imgAjusteMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnForma;
    private javax.swing.JComboBox<String> cbMaterial;
    private OTROS.IMAGEN imgBarraTitulo;
    private OTROS.IMAGEN imgCerrar;
    private OTROS.IMAGEN imgExcel;
    private OTROS.IMAGEN imgPdf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
