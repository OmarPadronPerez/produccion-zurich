package GRAFICA.IMPRIMIR;

import CLASES.COMPONENTE;
import GRAFICA.PRINCIPAL;
import java.awt.MouseInfo;
import java.awt.Point;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import sql.SQLMUEBLES;

public class HOJATRABAJO extends javax.swing.JInternalFrame {

    private int x;
    private int y;
    COMPONENTE objComp = new COMPONENTE();
    ArrayList<COMPONENTE> componentes = new ArrayList();
    SQLMUEBLES modMuebles = new SQLMUEBLES();
    TableColumnModel columnModel = null;
    String modelo = null;
    String componente = null;

    public HOJATRABAJO() {
        componentes = modMuebles.obtenerComponentes("TODO");
        initComponents();
        imgBarraTitulo.setBackgroung("IMAGENES/TEXTURAS/marmol.png");
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        imgCerrar.setBackgroung("IMAGENES/ICONOS/cerrar2.png");
        imgAjuste.trasparente();
        txtId.setVisible(false);
        llenarcbModelo();
    }
    public void llenarcbModelo() {
        ArrayList modelos = modMuebles.ObtenerModelos();
        for (int a = 0; a < modelos.size(); a++) {
            ArrayList mod = (ArrayList) modelos.get(a);
            if(mod.get(2).toString().equals("false")){
                cbModelo.addItem(mod.get(0).toString());
            }
            
        }
    }

    public void llenarcbComponente() {
        cbComponente.removeAllItems();
        for (COMPONENTE com : componentes) {
            if (com.getModelo().equals(cbModelo.getSelectedItem().toString())) {
                if (!com.getPrueva()) {
                    cbComponente.addItem(com.getNombre());
                }
            }
        }
    }

    public void llenarDescripcion() {
         for (COMPONENTE com : componentes) {
            if (com.getModelo().equals(cbModelo.getSelectedItem().toString())) {
                if (com.getNombre().equals(cbComponente.getSelectedItem().toString())) {
                    txtX.setText(String.valueOf(com.getX()));
                    txtY.setText(String.valueOf(com.getY()));
                    txtZ.setText(String.valueOf(com.getZ()));
                    if(com.getObservaciones()!=null){
                        txtDescripcion.setText(com.getObservaciones());
                    }else{
                        txtDescripcion.setText("");
                    }
                    txtId.setText(String.valueOf( com.getId()));
                    break;
                }
            }
        }
    }

    public void imprimir() {
        String ruta = System.getProperty("user.dir") + "/archivos/reportesPdf/HOJADETRABAJO_2.jasper";
        JasperReport jr;
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            jr = (JasperReport) JRLoader.loadObjectFromFile(ruta);
            Map parametros = new HashMap<String, String>();
            parametros.put("idComp", Integer.valueOf( txtId.getText()) );
            parametros.put("servidor",PRINCIPAL.conexiones.getServidor() );
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);
            con.close();
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "Error al crear lista");
            Logger.getLogger(HOJATRABAJO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HOJATRABAJO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        imgBarraTitulo = new OTROS.IMAGEN();
        jLabel2 = new javax.swing.JLabel();
        imgCerrar = new OTROS.IMAGEN();
        imgAjuste = new OTROS.IMAGEN();
        cbModelo = new javax.swing.JComboBox<>();
        cbComponente = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        txtX = new javax.swing.JLabel();
        txtY = new javax.swing.JLabel();
        txtZ = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtId = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

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
        jLabel2.setText("HOJA DE TRABAJO");

        imgCerrar.setMinimumSize(new java.awt.Dimension(26, 26));
        imgCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgCerrarMouseClicked(evt);
            }
        });

        imgAjuste.setMinimumSize(new java.awt.Dimension(26, 26));
        imgAjuste.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgAjusteMouseClicked(evt);
            }
        });

        imgBarraTitulo.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        imgBarraTitulo.setLayer(imgCerrar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        imgBarraTitulo.setLayer(imgAjuste, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout imgBarraTituloLayout = new javax.swing.GroupLayout(imgBarraTitulo);
        imgBarraTitulo.setLayout(imgBarraTituloLayout);
        imgBarraTituloLayout.setHorizontalGroup(
            imgBarraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imgBarraTituloLayout.createSequentialGroup()
                .addComponent(imgAjuste, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(imgCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        imgBarraTituloLayout.setVerticalGroup(
            imgBarraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(imgBarraTituloLayout.createSequentialGroup()
                .addGroup(imgBarraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imgCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgAjuste, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        cbModelo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbModeloItemStateChanged(evt);
            }
        });

        cbComponente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbComponenteItemStateChanged(evt);
            }
        });
        cbComponente.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbComponentePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel1.setText("Modelo");
        jLabel1.setMaximumSize(new java.awt.Dimension(60, 14));

        jLabel3.setText("Componente");

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Descripción");

        txtDescripcion.setEditable(false);
        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(2);
        txtDescripcion.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(txtDescripcion);

        jLabel5.setText("Medidas");

        txtX.setText("000.0");

        txtY.setText("000.0");

        txtZ.setText("000.0");

        jLabel10.setText("X");

        jLabel11.setText("X");

        txtId.setText("jLabel6");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtX)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtY)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtZ)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtId))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cbComponente, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbModelo, javax.swing.GroupLayout.Alignment.LEADING, 0, 222, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(imgBarraTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imgBarraTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbComponente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtY, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10)
                    .addComponent(txtZ, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                    .addComponent(jLabel11)
                    .addComponent(txtId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void imgBarraTituloMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgBarraTituloMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_imgBarraTituloMousePressed

    private void imgBarraTituloMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgBarraTituloMouseDragged
        Point point =MouseInfo.getPointerInfo().getLocation();
        //Point point2=PRINCIPAL.posisionPantalla();
        
        setLocation(point.x - (x + 5), point.y - (y + 50));
    }//GEN-LAST:event_imgBarraTituloMouseDragged

    private void imgCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgCerrarMouseClicked
        componentes.clear();
        this.dispose();
    }//GEN-LAST:event_imgCerrarMouseClicked

    private void cbModeloItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbModeloItemStateChanged
        llenarcbComponente();
        llenarDescripcion();
    }//GEN-LAST:event_cbModeloItemStateChanged

    private void imgAjusteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgAjusteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_imgAjusteMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       imprimir();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbComponenteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbComponenteItemStateChanged
        
    }//GEN-LAST:event_cbComponenteItemStateChanged

    private void cbComponentePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbComponentePopupMenuWillBecomeInvisible
        llenarDescripcion();
    }//GEN-LAST:event_cbComponentePopupMenuWillBecomeInvisible


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox<String> cbComponente;
    public static javax.swing.JComboBox<String> cbModelo;
    private OTROS.IMAGEN imgAjuste;
    private OTROS.IMAGEN imgBarraTitulo;
    private OTROS.IMAGEN imgCerrar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JLabel txtId;
    private javax.swing.JLabel txtX;
    private javax.swing.JLabel txtY;
    private javax.swing.JLabel txtZ;
    // End of variables declaration//GEN-END:variables
}
