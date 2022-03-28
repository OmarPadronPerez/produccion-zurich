package GRAFICA.IMPRIMIR;

import CLASES.COMPONENTE;
import GRAFICA.PRINCIPAL;
import OTROS.archivosexp;
import java.awt.Color;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import sql.SQLMUEBLES;

public class IMPRIMIRTODO extends javax.swing.JInternalFrame {

    private int x;
    private int y;
    String modelo = null;
    String componente = null;
    ArrayList<COMPONENTE> lisCompo = new ArrayList();
    ArrayList<COMPONENTE> lisTemp = new ArrayList();
    SQLMUEBLES modMuebles = new SQLMUEBLES();
    COMPONENTE seleccion = null;

    public IMPRIMIRTODO() {
        lisCompo = modMuebles.obtenerComponentes();
        initComponents();
        txtId.setVisible(false);
        /**
         * ************************************
         */
        imgBarraTitulo.setBackgroung("IMAGENES/TEXTURAS/marmol.png");
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        imgCerrar.setBackgroung("IMAGENES/ICONOS/cerrar2.png");
        imgAjuste.trasparente();

        llenarcbModelo();
        llenarcbComponente();
        llenarCampos(lisTemp.get(0));
    }

    public void llenarcbModelo() {
        ArrayList modelos = modMuebles.ObtenerModelos();
        for (int a = 0; a < modelos.size(); a++) {
            ArrayList mod = (ArrayList) modelos.get(a);
            if (mod.get(2).toString().equals("false")) {
                cbModelo.addItem(mod.get(0).toString());
            }
        }
    }

    public void llenarcbComponente() {
        lisTemp.clear();
        cbComponente.removeAllItems();
        for (COMPONENTE com : lisCompo) {
            if (com.getModelo().equals(cbModelo.getSelectedItem().toString())) {
                if (!com.getPrueva()) {
                    cbComponente.addItem(com.getNombre());
                    lisTemp.add(com);
                }
            }
        }
    }

    public void llenarCampos(COMPONENTE com) {
        System.out.println("llenarCampos " + com.getNombre() + " " + com.getHayMateriales());
        txtX.setText(String.valueOf(com.getX()));
        txtY.setText(String.valueOf(com.getY()));
        txtZ.setText(String.valueOf(com.getZ()));
        if (!com.getObservaciones().equals("") || com.getObservaciones() != null) {
            txtDescripcion.setText(com.getObservaciones());
        } else {
            txtDescripcion.setText("");
        }
        btnHoja.setBackground(Color.RED);
        btnHoja.setForeground(Color.WHITE);
        cambiarColor(btnHoja, com.getHayMateriales());
        cambiarColor(btnExplosivos, com.getExplosivo());
        cambiarColor(btnFotos, com.getFoto());
        cambiarColor(btnCorte, com.getCorte());
        txtId.setText(String.valueOf(com.getId()));
    }

    public void cambiarColor(javax.swing.JButton boton, String checar) {
        if (checar != null) {
            boton.setBackground(Color.green);
            boton.setForeground(Color.BLACK);
        } else {
            boton.setBackground(Color.RED);
            boton.setForeground(Color.WHITE);
        }
    }

    public void imprimirHoja(int id) {
        System.out.println("denrto hoja " + id);
        String ruta = System.getProperty("user.dir") + "/archivos/reportesPdf/HOJADETRABAJO_2.jasper";
        JasperReport jr;
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            jr = (JasperReport) JRLoader.loadObjectFromFile(ruta);
            Map parametros = new HashMap<String, String>();
            parametros.put("idComp", id);
            parametros.put("servidor", PRINCIPAL.conexiones.getServidor());
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

    public boolean abrirArchivo(String ruta) {
        try {
            archivosexp ae = new archivosexp();
            ae.abrirarchivo(ruta);
            return true;
        } catch (Exception e) {
            Logger.getLogger(SQLMUEBLES.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        imgBarraTitulo = new OTROS.IMAGEN();
        jLabel2 = new javax.swing.JLabel();
        imgAjuste = new OTROS.IMAGEN();
        imgCerrar = new OTROS.IMAGEN();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbModelo = new javax.swing.JComboBox<>();
        cbComponente = new javax.swing.JComboBox<>();
        txtX = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtY = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtZ = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        btnHoja = new javax.swing.JButton();
        btnExplosivos = new javax.swing.JButton();
        btnCorte = new javax.swing.JButton();
        btnFotos = new javax.swing.JButton();
        txtId = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
        jLabel2.setText("IMPRIMIR");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

        jLabel1.setText("MODELO");

        jLabel3.setText("COMPONENTE");

        jLabel4.setText("MEDIDAS");

        jLabel5.setText("DESCRIPCIÓN");

        cbModelo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbModeloItemStateChanged(evt);
            }
        });
        cbModelo.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbModeloPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        cbModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbModeloActionPerformed(evt);
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

        txtX.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtX.setText("000");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("X");

        txtY.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtY.setText("000");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("X");

        txtZ.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtZ.setText("000");

        txtDescripcion.setEditable(false);
        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(2);
        txtDescripcion.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(txtDescripcion);

        btnHoja.setText("HOJA DE TRABAJO");
        btnHoja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHojaActionPerformed(evt);
            }
        });

        btnExplosivos.setText("EXPLOSIVOS");
        btnExplosivos.setPreferredSize(new java.awt.Dimension(125, 23));
        btnExplosivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExplosivosActionPerformed(evt);
            }
        });

        btnCorte.setText("CORTES Y ANGULOS");
        btnCorte.setPreferredSize(new java.awt.Dimension(125, 23));
        btnCorte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCorteActionPerformed(evt);
            }
        });

        btnFotos.setText("FOTOS");
        btnFotos.setPreferredSize(new java.awt.Dimension(125, 23));
        btnFotos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFotosActionPerformed(evt);
            }
        });

        txtId.setText("jLabel6");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnHoja)
                        .addGap(14, 14, 14)
                        .addComponent(btnExplosivos, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCorte, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 12, Short.MAX_VALUE)
                        .addComponent(btnFotos, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtX)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtY)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtZ)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtId))
                            .addComponent(cbComponente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addComponent(cbModelo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addComponent(imgBarraTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imgBarraTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbComponente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtX)
                    .addComponent(jLabel7)
                    .addComponent(txtY)
                    .addComponent(jLabel9)
                    .addComponent(txtZ)
                    .addComponent(txtId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHoja)
                    .addComponent(btnExplosivos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCorte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFotos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        this.dispose();
    }//GEN-LAST:event_imgCerrarMouseClicked

    private void btnFotosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFotosActionPerformed
        String ruta;
        int i = cbComponente.getSelectedIndex();
        if (lisTemp.get(i).getFoto() != null) {
            if (lisTemp.get(i).getFoto().length() > 0) {
                try {
                    ruta = "fotos\\" + lisTemp.get(i).getFoto();
                    if (!abrirArchivo(ruta)) {
                        JOptionPane.showMessageDialog(null, "Error al abrir, imagen no encontrada", "Error al abrir", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception e) {
                    System.out.println("falla foto");
                    System.out.println(e);
                }
            }
        }
    }//GEN-LAST:event_btnFotosActionPerformed

    private void btnCorteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorteActionPerformed
        int i = cbComponente.getSelectedIndex();
        try {
            if (lisTemp.get(i).getCorte() != null
                    || lisTemp.get(i).getCorte().length() == 0) {
                if (!abrirArchivo("cortes\\" + lisTemp.get(i).getCorte())) {
                    JOptionPane.showMessageDialog(null, "Error al abrir, imagen no encontrada", "Error al abrir", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (Exception e) {
            System.out.println("falla cortes");
            System.out.println(e);
        }

    }//GEN-LAST:event_btnCorteActionPerformed

    private void cbModeloItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbModeloItemStateChanged
        //llenarcbComponente();
    }//GEN-LAST:event_cbModeloItemStateChanged

    private void cbModeloPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbModeloPopupMenuWillBecomeInvisible
        llenarcbComponente();
        try {
            COMPONENTE tem = lisTemp.get(0);
            System.out.println(tem.getId() + " " + tem.getNombre() + " " + tem.getHayMateriales());
            llenarCampos(tem);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cbModeloPopupMenuWillBecomeInvisible

    private void cbComponentePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbComponentePopupMenuWillBecomeInvisible
        try {
            int i = cbComponente.getSelectedIndex();
            seleccion = lisTemp.get(i);
            llenarCampos(seleccion);
        } catch (Exception e) {
            /**/
        }
    }//GEN-LAST:event_cbComponentePopupMenuWillBecomeInvisible

    private void btnHojaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHojaActionPerformed
        try {
            System.out.println("hoja " + seleccion.getId() + " " + seleccion.getNombre() + " " + seleccion.getHayMateriales());

            if (seleccion.getHayMateriales() != null) {
                System.out.println("if hoja");
                imprimirHoja(seleccion.getId());
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }//GEN-LAST:event_btnHojaActionPerformed

    private void cbComponenteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbComponenteItemStateChanged
        /*try {
            int i = cbComponente.getSelectedIndex();
            llenarCampos(lisTemp.get(i));
        } catch (Exception e) {
        }*/
    }//GEN-LAST:event_cbComponenteItemStateChanged

    private void btnExplosivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExplosivosActionPerformed
        int i = cbComponente.getSelectedIndex();
        try {
            if (lisTemp.get(i).getExplosivo() != null
                    || lisTemp.get(i).getExplosivo().length() == 0) {
                if (!abrirArchivo("explosivos\\" + lisTemp.get(i).getExplosivo())) {
                    JOptionPane.showMessageDialog(null, "Error al abrir, archivo no encontrado", "Error al abrir", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_btnExplosivosActionPerformed

    private void cbModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbModeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbModeloActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCorte;
    private javax.swing.JButton btnExplosivos;
    private javax.swing.JButton btnFotos;
    private javax.swing.JButton btnHoja;
    private javax.swing.JComboBox<String> cbComponente;
    private javax.swing.JComboBox<String> cbModelo;
    private OTROS.IMAGEN imgAjuste;
    private OTROS.IMAGEN imgBarraTitulo;
    private OTROS.IMAGEN imgCerrar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JLabel txtId;
    private javax.swing.JLabel txtX;
    private javax.swing.JLabel txtY;
    private javax.swing.JLabel txtZ;
    // End of variables declaration//GEN-END:variables
}
