package GRAFICA.ALMACEN;

import CLASES.material;
import GRAFICA.PRINCIPAL;
import static GRAFICA.PRINCIPAL.jdpEscritorio;
import OTROS.AUXILIAR;
import OTROS.CellRenderer;
import java.awt.MouseInfo;
import java.awt.Point;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.*;
import sql.SQLALMACEN;

public class EXISTENCIAS extends javax.swing.JInternalFrame {

    private int x;
    private int y;
    DateFormat fMuestra = new SimpleDateFormat("dd/MM/yyy");
    public static SQLALMACEN modAlmacen = new SQLALMACEN();
    public static ArrayList lista = new ArrayList();
    TableColumnModel columnModel = null;
    public static int origen;
    public static CellRenderer colorFilar = new CellRenderer();
    public static DefaultTableModel dftm = new DefaultTableModel() {
        public boolean isCellEditable(int fila, int columna) {
            if (columna == jTable1.getColumnCount()) {
                return true;
            }
            return false;
        }
    };

    public EXISTENCIAS(int origen) {
        this.origen=origen;
        initComponents();
        crearTabla();
        //calcularPantalla();
        imgBarraTitulo.setBackgroung("IMAGENES/TEXTURAS/marmol.png");
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        imgCerrar.setBackgroung("IMAGENES/ICONOS/cerrar2.png");
        imgActualizar.setBackgroung("IMAGENES/ICONOS/actualizar.png");
        llenarCB();
        lista = modAlmacen.obtenerTodoMateriales();
        llenarTabla();
        if(origen==0){
            btnModificar.setVisible(false);
            btnAgregar.setVisible(false);
        }
        else{
            btnModificar.setVisible(true);
            btnAgregar.setVisible(true);
        }
    }

    public void setCellRender(JTable table) {
        Enumeration<TableColumn> en = table.getColumnModel().getColumns();
        while (en.hasMoreElements()) {
            TableColumn tc = en.nextElement();
            tc.setCellRenderer(new CellRenderer());

        }
    }

    public void calcularPantalla() {
        int tamañoX = PRINCIPAL.pantallaAncho;
        tamañoX = (int) Math.ceil(tamañoX * 0.90);
        int tamañoY = PRINCIPAL.pantallaAlto;
        tamañoY = (int) Math.ceil(tamañoY * 0.90);
        this.setSize(tamañoX, tamañoY);
    }

    public void llenarCB() {
        ArrayList cb = modAlmacen.ObtenerTiposMaterial();
        cbTipos.removeAllItems();
        cbTipos.addItem("TODOS");
        for (int a = 0; a < cb.size(); a++) {
            ArrayList b = (ArrayList) cb.get(a);
            cbTipos.addItem(b.get(0).toString());
        }
    }

    public void crearTabla() {
        DefaultTableCellRenderer centerRenderer = new CellRenderer();
        DefaultTableCellRenderer rightRenderer = new CellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        columnModel = jTable1.getColumnModel();
        jTable1.setModel(dftm);
        if(origen==1){
            dftm.setColumnIdentifiers(new Object[]{"Id", "Nombre", "Tipo", "Precio", "Existen", "Minimo", "Visible"/*, "Actualizacion"*/});
            columnModel.getColumn(0).setPreferredWidth(38);
            columnModel.getColumn(1).setPreferredWidth(321);
            columnModel.getColumn(2).setPreferredWidth(157);
            columnModel.getColumn(3).setPreferredWidth(52);
            columnModel.getColumn(3).setCellRenderer(rightRenderer);
            columnModel.getColumn(4).setPreferredWidth(54);
            columnModel.getColumn(4).setCellRenderer(rightRenderer);
            columnModel.getColumn(5).setPreferredWidth(52);
            columnModel.getColumn(5).setCellRenderer(rightRenderer);
            columnModel.getColumn(6).setPreferredWidth(52);
            columnModel.getColumn(6).setCellRenderer(centerRenderer);
        }else{
            dftm.setColumnIdentifiers(new Object[]{"Id", "Nombre", "Tipo", "Precio", "Existen"});
            columnModel.getColumn(0).setPreferredWidth(38);
            columnModel.getColumn(1).setPreferredWidth(321);
            columnModel.getColumn(2).setPreferredWidth(157);
            columnModel.getColumn(3).setPreferredWidth(52);
            columnModel.getColumn(3).setCellRenderer(rightRenderer);
            columnModel.getColumn(4).setPreferredWidth(54);
            columnModel.getColumn(4).setCellRenderer(rightRenderer);
        }
        jTable1.setDefaultRenderer(jTable1.getColumnClass(0), colorFilar);
    }

    public static void llenarTabla() {
        borrarTabla();
        for (int x = 0; x < lista.size(); x++) {
            material mat = (material) lista.get(x);
            if (mat.getTipo().equals(cbTipos.getSelectedItem()) || cbTipos.getSelectedItem().equals("TODOS")) {
                if (mat.getMaterial().contains(txtBuscar.getText().toUpperCase())
                        || txtBuscar.getText().length() == 0) {
                    String visible = null;
                    if (mat.getMedidaZ() == 1) {
                        visible = "Si";
                    } else {
                        visible = "No";
                    }
                    //fecha = fMuestra.format(mat.getFecha());
                    if(origen==1){
                       dftm.addRow(new Object[]{mat.getIdMaterial(), mat.getMaterial(),
                        mat.getTipo(), String.valueOf(mat.getPrecioPaquete()), String.valueOf(mat.getCantidad()), String.valueOf(mat.getMinimo()), visible/*, fecha*/}); 
                    }else{//{"Id", "Nombre", "Tipo", "Precio", "Existen"});
                        dftm.addRow(new Object[]{mat.getIdMaterial(), mat.getMaterial(),
                        mat.getTipo(), String.valueOf(mat.getPrecioPaquete()), String.valueOf(mat.getCantidad())}); 
                    }
                    

                }
            }
        }
    }

    public static void borrarTabla() {
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            dftm.removeRow(i);
            i--;
        }
    }

    public void modificar() {
        int fila = jTable1.getSelectedRow();
        if (fila > -1) {
            int sel = Integer.valueOf(dftm.getValueAt(fila, 0).toString());
            for (int i = 0; i < lista.size(); i++) {
                material mat = (material) lista.get(i);
                if (Integer.valueOf(mat.getIdMaterial()) == sel) {
                    AGREGARMATERIAL am = new AGREGARMATERIAL(mat);
                    AUXILIAR.verPantalla(jdpEscritorio, am);
                    break;
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbTipos = new javax.swing.JComboBox<>();
        txtBuscar = new javax.swing.JTextField();
        btnModificar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        imgBarraTitulo = new OTROS.IMAGEN();
        jLabel2 = new javax.swing.JLabel();
        imgActualizar = new OTROS.IMAGEN();
        imgCerrar = new OTROS.IMAGEN();

        cbTipos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTiposItemStateChanged(evt);
            }
        });

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
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
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

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
        jLabel2.setText("EXISTENCIAS DE ALMACEN");

        imgActualizar.setMinimumSize(new java.awt.Dimension(26, 26));
        imgActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgActualizarMouseClicked(evt);
            }
        });

        imgCerrar.setMinimumSize(new java.awt.Dimension(26, 26));
        imgCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgCerrarMouseClicked(evt);
            }
        });

        imgBarraTitulo.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        imgBarraTitulo.setLayer(imgActualizar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        imgBarraTitulo.setLayer(imgCerrar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout imgBarraTituloLayout = new javax.swing.GroupLayout(imgBarraTitulo);
        imgBarraTitulo.setLayout(imgBarraTituloLayout);
        imgBarraTituloLayout.setHorizontalGroup(
            imgBarraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imgBarraTituloLayout.createSequentialGroup()
                .addComponent(imgActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(imgCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        imgBarraTituloLayout.setVerticalGroup(
            imgBarraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(imgBarraTituloLayout.createSequentialGroup()
                .addGroup(imgBarraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imgActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbTipos, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnModificar)
                        .addGap(18, 18, 18)
                        .addComponent(btnAgregar)))
                .addContainerGap())
            .addComponent(imgBarraTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imgBarraTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTipos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar)
                    .addComponent(btnAgregar))
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void imgCerrar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgCerrar1MouseClicked
        this.dispose();
    }//GEN-LAST:event_imgCerrar1MouseClicked

    private void imgBarraTituloMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgBarraTituloMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_imgBarraTituloMousePressed

    private void imgBarraTituloMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgBarraTituloMouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - (x + 5), point.y - (y + 50));
    }//GEN-LAST:event_imgBarraTituloMouseDragged

    private void imgActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgActualizarMouseClicked
        lista = modAlmacen.obtenerTodoMateriales();
        llenarTabla();
        
    }//GEN-LAST:event_imgActualizarMouseClicked

    private void cbTiposItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTiposItemStateChanged
        llenarTabla();
    }//GEN-LAST:event_cbTiposItemStateChanged

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        cbTipos.setSelectedItem("TODOS");
        llenarTabla();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        AGREGARMATERIAL am = new AGREGARMATERIAL(null);
        AUXILIAR.verPantalla(jdpEscritorio, am);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        modificar();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1KeyPressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2&& origen==1) {
            modificar();
        }
    }//GEN-LAST:event_jTable1MouseClicked
    /*
    private void imgActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgAjusteMouseClicked
        this.dispose();
    }//GEN-LAST:event_imgAjusteMouseClicked
*/
    private void imgCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgCerrarMouseClicked
        this.dispose();
    }//GEN-LAST:event_imgCerrarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnModificar;
    public static javax.swing.JComboBox<String> cbTipos;
    private OTROS.IMAGEN imgActualizar;
    private OTROS.IMAGEN imgBarraTitulo;
    private OTROS.IMAGEN imgCerrar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
