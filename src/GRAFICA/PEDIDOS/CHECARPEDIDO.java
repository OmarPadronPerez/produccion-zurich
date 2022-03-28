package GRAFICA.PEDIDOS;

import CLASES.COMPONENTE;
import CLASES.PEDIDO;
import GRAFICA.CALCULAR.CALCULARMATERIALGENERICO;
import static GRAFICA.PEDIDOS.NUEVOPEDIDO.pedido;
import GRAFICA.PRINCIPAL;
import static GRAFICA.PRINCIPAL.jdpEscritorio;
import OTROS.AUXILIAR;
import java.awt.MouseInfo;
import java.awt.Point;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import sql.SQLPEDIDOS;

public class CHECARPEDIDO extends javax.swing.JInternalFrame {
    private int x;
    private int y;
    SimpleDateFormat fSalida=new SimpleDateFormat("dd MMMM yyyy");
    TableColumnModel columnModel = null;
    PEDIDO ped=null;
    public static DefaultTableModel dftm = new DefaultTableModel() {
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    
    public CHECARPEDIDO(PEDIDO ped) {
        initComponents();
        imgBarraTitulo.setBackgroung("IMAGENES/TEXTURAS/marmol.png");
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        imgCerrar.setBackgroung("IMAGENES/ICONOS/si.png");
        imgAjuste.setBackgroung("IMAGENES/ICONOS/no.png");
        
        this.ped=ped;
        llenarCampos();
        crearTabla();
        llenarTabla();
    }
    public void llenarCampos(){
        txtCliente.setText(ped.getCliente());
        txtFPedido.setText(fSalida.format(ped.getFechaCreacion()));
        txtFEntrega.setText(fSalida.format(ped.getFechaEntrega()));
        txtFolio.setText(ped.getFolio());
        txtPrioridad.setText(ped.getPrioridad());
    }
    
    public void crearTabla(){
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTable1.setModel(dftm);
        columnModel = jTable1.getColumnModel();
        dftm.setColumnIdentifiers(new Object[]{"Can", "Componente", "Modelo", "Color", "Tapiz","Notas", "P/U", "Subtotal"});
        columnModel.getColumn(0).setPreferredWidth(37);
        columnModel.getColumn(1).setPreferredWidth(143);
        columnModel.getColumn(2).setPreferredWidth(143);
        columnModel.getColumn(3).setPreferredWidth(142);
        columnModel.getColumn(4).setPreferredWidth(142);
        columnModel.getColumn(5).setPreferredWidth(122);
        columnModel.getColumn(6).setPreferredWidth(67);
        columnModel.getColumn(7).setPreferredWidth(67);
    }
    
    void llenarTabla(){
        float total=0;
        ArrayList<COMPONENTE> lista=ped.getLista();
        for(COMPONENTE com:lista){
            dftm.addRow(new Object[]{com.getCantidad(), com.getNombre(), com.getModelo(),
                com.getEstilo().getNombre(), com.getEstilo().getNombreTapiz(),
                com.getObservaciones(), "$ "+com.getPreUni(), "$ "+com.getCantidad() * com.getPreUni()});
            total=total+com.getCantidad()*com.getPreUni();
        }
        txtTotal.setText(String.valueOf(total));
    }
    
    public void borrarTabla() {
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            dftm.removeRow(i);
            i--;
        }
    }
    
    public void cerrar(){
        borrarTabla();
        this.dispose();
    }
    
    public void imprimir(){
        try {
            Connection con = PRINCIPAL.dataSource.getConnection();
            String ruta=System.getProperty("user.dir")+"/archivos/reportesPdf/NUEVOPEDIDO.jasper";
            JasperReport jr=(JasperReport)JRLoader.loadObjectFromFile(ruta);
            Map parametros=new HashMap<String,String>();
            parametros.put("folio",ped.getFolio());
            JasperPrint jp=JasperFillManager.fillReport(jr,parametros,con);
            JasperViewer jv=new JasperViewer(jp,false);
            jv.setVisible(true);
            con.close();
        } catch (Exception e) {
            System.out.println("checar pedido imprimir"+e);
            JOptionPane.showMessageDialog(rootPane,e);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imgBarraTitulo = new OTROS.IMAGEN();
        jLabel2 = new javax.swing.JLabel();
        imgAjuste = new OTROS.IMAGEN();
        imgCerrar = new OTROS.IMAGEN();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtTotal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JLabel();
        txtFolio = new javax.swing.JLabel();
        txtFPedido = new javax.swing.JLabel();
        txtFEntrega = new javax.swing.JLabel();
        txtPrioridad = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(910, 524));

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
        jLabel2.setText("REVISION DE PEDIDO");

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
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imgCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        imgBarraTituloLayout.setVerticalGroup(
            imgBarraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(imgBarraTituloLayout.createSequentialGroup()
                .addComponent(imgAjuste, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(imgBarraTituloLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(imgCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.setText("Cliente");

        jLabel3.setText("Folio");

        jLabel4.setText("Fecha de pedido");

        jLabel5.setText("Fecha de entrega");

        jLabel7.setText("Prioridad");

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel8.setText("Total");

        txtCliente.setText("jLabel9");

        txtFolio.setText("jLabel10");

        txtFPedido.setText("jLabel11");

        txtFEntrega.setText("jLabel12");

        txtPrioridad.setText("jLabel14");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("Material Requerido");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(txtFEntrega, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jButton2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8)
                            .addGap(18, 18, 18)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addComponent(imgBarraTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imgBarraTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(txtCliente)
                    .addComponent(txtFolio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtFPedido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtFEntrega))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPrioridad))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void imgAjusteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgAjusteMouseClicked
        borrarTabla();
        this.dispose();
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
        NUEVOPEDIDO.abierto=false;
        SQLPEDIDOS modPedidos=new SQLPEDIDOS();
        modPedidos.guardarPedidos(ped);
        imprimir();
        this.dispose();
    }//GEN-LAST:event_imgCerrarMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AGREGARCOMPONENTEPEDIDO acp=new AGREGARCOMPONENTEPEDIDO();
        AUXILIAR.verPantalla(jdpEscritorio, acp);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased

    }//GEN-LAST:event_jTable1MouseReleased

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        
    }//GEN-LAST:event_jTable1KeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        CALCULARMATERIALGENERICO cm = new CALCULARMATERIALGENERICO(pedido,txtFolio.getText());
        AUXILIAR.verPantalla(jdpEscritorio, cm);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private OTROS.IMAGEN imgAjuste;
    private OTROS.IMAGEN imgBarraTitulo;
    private OTROS.IMAGEN imgCerrar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel txtCliente;
    private javax.swing.JLabel txtFEntrega;
    private javax.swing.JLabel txtFPedido;
    private javax.swing.JLabel txtFolio;
    private javax.swing.JLabel txtPrioridad;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
