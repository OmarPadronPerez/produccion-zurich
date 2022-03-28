package GRAFICA.MUEBLES;

import CLASES.COMPONENTE;
import CLASES.MODELO;
import GRAFICA.CREAR.VERMODELOS;
import static GRAFICA.PRINCIPAL.jdpEscritorio;
import OTROS.AUXILIAR;
import java.awt.MouseInfo;
import java.awt.Point;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import sql.SQLMUEBLES;

public class VERCOMPONENTES extends javax.swing.JInternalFrame {
    private int x;
    private int y;
    public static SQLMUEBLES modMuebles=new SQLMUEBLES();
    public static ArrayList<COMPONENTE> lista=new ArrayList();
    static SimpleDateFormat fSalida=new SimpleDateFormat("dd/MM/yyyy");
    TableColumnModel columnModel = null;
    static MODELO usaModelo;
    static DefaultTableModel dftm = new DefaultTableModel() {
        public boolean isCellEditable(int fila, int columna) {
            if (columna == jTable1.getColumnCount()) {
                return true;
            }
            return false;
        }
    };
    
    public VERCOMPONENTES(MODELO modelo) {
        initComponents();
        imgBarraTitulo.setBackgroung("IMAGENES/TEXTURAS/marmol.png");
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        imgCerrar.setBackgroung("IMAGENES/ICONOS/cerrar2.png");
        imgAjuste.trasparente();
        txtTitulo.setText("COMPONENTES DE "+modelo.getNombre());
        cbMuestra.setSelected(modelo.getPrueva());
        this.usaModelo=modelo;
        crearTabla();
        llenarLista(usaModelo.getNombre());
        llenarTabla();
    }
    
    public void crearTabla(){
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTable1.setModel(dftm);
        columnModel = jTable1.getColumnModel();
        dftm.setColumnIdentifiers(new Object[]{"Nombre", "Observaciones","X","Y","Z","Actualizacion"});
        columnModel.getColumn(0).setPreferredWidth(152);
        columnModel.getColumn(1).setPreferredWidth(328);
        columnModel.getColumn(2).setPreferredWidth(63);
        columnModel.getColumn(2).setCellRenderer(centerRenderer);
        columnModel.getColumn(3).setPreferredWidth(63);
        columnModel.getColumn(3).setCellRenderer(centerRenderer);
        columnModel.getColumn(4).setPreferredWidth(63);
        columnModel.getColumn(4).setCellRenderer(centerRenderer);
        columnModel.getColumn(5).setPreferredWidth(87);
        columnModel.getColumn(5).setCellRenderer(centerRenderer);
        
    }
    
    public static void llenarLista(String modelo){
        lista=modMuebles.obtenerComponentes(modelo);
    }
    
    public static void llenarTabla(){
        borrarTabla();
        llenarLista(usaModelo.getNombre());
        for(COMPONENTE com:lista){
            String fecha;
            String componente;
            if(com.getPrueva()){
                componente=com.getNombre()+" (MUESTRA)";
            }else{
                componente=com.getNombre();
            }
            try{
                fecha=fSalida.format(com.getFecha());
            } catch (Exception e) {
                fecha="";
            }   
            dftm.addRow(new Object[]{componente,com.getObservaciones(),com.getX(),com.getY(),com.getZ(),fecha});
            //dftm.addRow(new Object[]{com.getNombre(),com.getObservaciones(),"1000.00","1000.00","1000.00",fecha});
        }
    }
    
    static void borrarTabla() {
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            dftm.removeRow(i);
            i--;
        }
    }
    void modificar(){
        int fila=jTable1.getSelectedRow();
        usaModelo.setPrueva(cbMuestra.isSelected());
        if(fila>-1){
            MODIFICARCOMPONENTE mc=new MODIFICARCOMPONENTE(lista.get(fila),usaModelo);
            AUXILIAR.verPantalla(jdpEscritorio, mc);
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imgBarraTitulo = new OTROS.IMAGEN();
        txtTitulo = new javax.swing.JLabel();
        imgAjuste = new OTROS.IMAGEN();
        imgCerrar = new OTROS.IMAGEN();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtModelo = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        cbMuestra = new javax.swing.JCheckBox();

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
        txtTitulo.setText("VERCOMPONENTES");

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

        imgBarraTitulo.setLayer(txtTitulo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        imgBarraTitulo.setLayer(imgAjuste, javax.swing.JLayeredPane.DEFAULT_LAYER);
        imgBarraTitulo.setLayer(imgCerrar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout imgBarraTituloLayout = new javax.swing.GroupLayout(imgBarraTitulo);
        imgBarraTitulo.setLayout(imgBarraTituloLayout);
        imgBarraTituloLayout.setHorizontalGroup(
            imgBarraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imgBarraTituloLayout.createSequentialGroup()
                .addComponent(imgAjuste, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(imgCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        imgBarraTituloLayout.setVerticalGroup(
            imgBarraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(imgBarraTituloLayout.createSequentialGroup()
                .addGroup(imgBarraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imgAjuste, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel1.setText("Modelo");

        jLabel2.setText("Descripcion");

        txtModelo.setDisabledTextColor(new java.awt.Color(0, 0, 0));

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

        jButton1.setText("Nuevo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Modificar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        cbMuestra.setText("MUESTRA");
        cbMuestra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMuestraActionPerformed(evt);
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
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbMuestra)))
                        .addGap(173, 173, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imgBarraTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(cbMuestra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void imgAjusteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgAjusteMouseClicked
        /*for (int x = 0; x < jTable1.getColumnCount(); x++) {
            System.out.println(x + " " + jTable1.getColumn(jTable1.getColumnName(x)).getWidth());
        }*/
    }//GEN-LAST:event_imgAjusteMouseClicked

    private void imgBarraTituloMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgBarraTituloMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_imgBarraTituloMousePressed

    private void imgBarraTituloMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgBarraTituloMouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - (x+5), point.y - (y+50));
    }//GEN-LAST:event_imgBarraTituloMouseDragged

    private void imgCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgCerrarMouseClicked
        VERMODELOS.llenarTabla();
        this.dispose();
    }//GEN-LAST:event_imgCerrarMouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {
            modificar();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (txtModelo.getText().length() > 0) {
            COMPONENTE com = new COMPONENTE();
            if (txtModelo.isEnabled()) {
                usaModelo.setNombre(txtModelo.getText().toUpperCase());
            }
            usaModelo.setDescripcion(txtDescripcion.getText().toUpperCase());
            com.setModelo(usaModelo.getNombre());
            MODIFICARCOMPONENTE mc = new MODIFICARCOMPONENTE(com, usaModelo);
            AUXILIAR.verPantalla(jdpEscritorio, mc);
        }else{
            JOptionPane.showMessageDialog(null, "Es necesario el nombre del modelo");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        modificar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbMuestraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMuestraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbMuestraActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbMuestra;
    private OTROS.IMAGEN imgAjuste;
    private OTROS.IMAGEN imgBarraTitulo;
    private OTROS.IMAGEN imgCerrar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JTextField txtDescripcion;
    public static javax.swing.JTextField txtModelo;
    private javax.swing.JLabel txtTitulo;
    // End of variables declaration//GEN-END:variables
}
