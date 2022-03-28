package GRAFICA.ALMACEN.AJUSTES;

import CLASES.material;
import GRAFICA.ALMACEN.CellRenderExistencias;
import GRAFICA.PRINCIPAL;
import static GRAFICA.PRINCIPAL.jdpEscritorio;
import OTROS.AUXILIAR;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import sql.SQLALMACEN;

public class MOVIMIENTOS extends javax.swing.JInternalFrame {

    private int x;
    private int y;
    int origen = -1;
    public static SQLALMACEN modAlmacen = new SQLALMACEN();
    //public static ArrayList<material> entrada = null;
    static ArrayList<material> mosEntra = null;
    public static ArrayList<material> salida = new ArrayList();
    TableColumnModel columnModel = null;
    public static CellRenderExistencias colorFilar = new CellRenderExistencias();
    public static DefaultTableModel dftmE = new DefaultTableModel() {
        public boolean isCellEditable(int fila, int columna) {
            if (columna == jTEntrada.getColumnCount()) {
                return true;
            }
            return false;
        }
    };
    public static DefaultTableModel dftmS = new DefaultTableModel() {
        public boolean isCellEditable(int fila, int columna) {
            if (columna == 0) {
                return true;
            }
            return false;
        }
    };

    public MOVIMIENTOS(int origen) {
        this.origen = origen;
        mosEntra/*entrada*/ = modAlmacen.obtenerTodoMateriales();
        initComponents();
        imgBarraTitulo.setBackgroung("IMAGENES/TEXTURAS/marmol.png");
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        imgAceptar.setBackgroung("IMAGENES/ICONOS/sig.png");
        imgCerrar.setBackgroung("IMAGENES/ICONOS/cerrar2.png");
        llenarCB();
        crearTabla(jTEntrada, dftmE);
        crearTabla2(jTSalida, dftmS);
        //mosEntra = entrada;
        llenarEntrada();
        if (origen == 0) {//es salida
            jpFondo.setBackground(new Color(204, 255, 204));
            txtTitulo.setText("SALIDAS DE ALMACEN");
        } else {
            jpFondo.setBackground(new Color(255, 204, 255));
            txtTitulo.setText("ENTRADAS A ALMACEN");
        }
        txtBuscar.requestFocus();
    }

    public void setCellRender(JTable table) {
        Enumeration<TableColumn> en = table.getColumnModel().getColumns();
        while (en.hasMoreElements()) {
            TableColumn tc = en.nextElement();
            tc.setCellRenderer(new CellRenderExistencias());

        }
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

    public static void cerrar() {
        cbTipos.setSelectedIndex(0);
        salida.clear();
        mosEntra/*entrada*/ = modAlmacen.obtenerTodoMateriales();
        //mosEntra = entrada;
        borrarTabla(jTSalida, dftmS);
        llenarEntrada();

    }

    public static void borrarTabla(javax.swing.JTable tabla, DefaultTableModel dftm) {
        for (int i = 0; i < tabla.getRowCount(); i++) {
            dftm.removeRow(i);
            i--;
        }
    }

    public void crearTabla(javax.swing.JTable tabla, DefaultTableModel dftm) {

        tabla.setModel(dftm);
        dftm.setColumnIdentifiers(new Object[]{"Id", "Nombre", "Tipo", "Precio", "Existencias", "Minimo"/*, "Actualizacion"*/});
        columnModel = tabla.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(45);
        columnModel.getColumn(1).setPreferredWidth(460);
        columnModel.getColumn(2).setPreferredWidth(170);
        columnModel.getColumn(3).setPreferredWidth(80);
        columnModel.getColumn(4).setPreferredWidth(80);
        jTEntrada.setDefaultRenderer(jTEntrada.getColumnClass(0), colorFilar);
    }

    public void crearTabla2(javax.swing.JTable tabla, DefaultTableModel dftm) {
        tabla.setModel(dftm);
        dftm.setColumnIdentifiers(new Object[]{"Cant","", "Nombre", "Tipo", "Precio"/*, "Existencias", "Actualizacion"*/});
        columnModel = tabla.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(45);
        columnModel.getColumn(1).setPreferredWidth(68);
        columnModel.getColumn(2).setPreferredWidth(454);
        columnModel.getColumn(3).setPreferredWidth(181);
        columnModel.getColumn(4).setPreferredWidth(90);
    }

    public static void llenarEntrada() {
        try {
            borrarTabla(jTEntrada, dftmE);
            for (material mat : mosEntra) {//{"Id", "Nombre", "Tipo", "Precio", "Existencias", "Minimo"/
                if ((mat.getTipo().equals(cbTipos.getSelectedItem()) || cbTipos.getSelectedItem().equals("TODOS"))
                        && (mat.getMaterial().contains(txtBuscar.getText().toUpperCase()) || txtBuscar.getText().length() == 0)) {
                    
                    dftmE.addRow(new Object[]{mat.getIdMaterial(),mat.getMaterial(), 
                        mat.getTipo(), "$ " + mat.getPrecioPaquete(), 
                        mat.getCantidad(), mat.getMinimo()/*, fecha*/});
                }
            }
        } catch (Exception e) {
        }
    }

    public static void llenarSalida() {
        try {
            borrarTabla(jTSalida, dftmS);
            for (material mat : salida) {
                String unidad=null;
                    if(mat.getUnidades().equals("Unidad")){
                        unidad="Unidades";
                    }else{
                        unidad=mat.getUnidades()+"s";
                    }
                dftmS.addRow(new Object[]{mat.getMedidaZ(),unidad, mat.getMaterial(), 
                    mat.getTipo(), "$ " + mat.getPrecioPaquete()/*, mat.getCantidad()/*, fecha*/});
                
            }
        } catch (Exception e) {
        }
    }

    public void agregar() {
        int fila = jTEntrada.getSelectedRow();
        if (fila > -1) {
            String id = jTEntrada.getValueAt(fila, 0).toString();
            for (material mat : mosEntra/*entrada*/) {
                if (mat.getIdMaterial().equals(id)) {
                    /*if (origen == 1) {
                        CALCULARAGREGAR ca = new CALCULARAGREGAR(mat);
                        AUXILIAR.verPantalla(jdpEscritorio, ca);
                    } else {*/
                        float can = -1;
                        String respuesta = null;
                        try {
                            do {
                                respuesta = JOptionPane.showInputDialog("Cantidad");
                                if (AUXILIAR.isFloat(respuesta)) {
                                    can = Float.valueOf(respuesta);
                                }

                            } while (can <= 0);
                        } catch (Exception e) {
                        }

                        if (can > 0) {
                            mat.setMedidaZ(can);
                            txtBuscar.setText("");
                            salida.add(mat);
                            txtBuscar.requestFocus();
                        }
                        
                    //}
                    break;
                }
            }
        }
        llenarSalida();
    }

    public void borrar() {
        int fila = jTSalida.getSelectedRow();
        if (fila > -1) {
            salida.remove(fila);
            llenarSalida();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpFondo = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTSalida = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTEntrada = new javax.swing.JTable();
        cbTipos = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        imgBarraTitulo = new OTROS.IMAGEN();
        txtTitulo = new javax.swing.JLabel();
        imgCerrar = new OTROS.IMAGEN();
        imgAceptar = new OTROS.IMAGEN();

        jpFondo.setBackground(new java.awt.Color(204, 255, 255));

        jTSalida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTSalida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTSalidaMouseClicked(evt);
            }
        });
        jTSalida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTSalidaKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTSalida);

        jButton2.setText("Agregar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Borrar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTEntrada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTEntrada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTEntradaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTEntrada);

        cbTipos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTiposItemStateChanged(evt);
            }
        });

        jLabel1.setText("Tipo");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jpFondoLayout = new javax.swing.GroupLayout(jpFondo);
        jpFondo.setLayout(jpFondoLayout);
        jpFondoLayout.setHorizontalGroup(
            jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addGroup(jpFondoLayout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(jpFondoLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTipos, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscar)))
                .addContainerGap())
        );
        jpFondoLayout.setVerticalGroup(
            jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTipos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addContainerGap())
        );

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
        txtTitulo.setText("PLANTILLA");

        imgCerrar.setMinimumSize(new java.awt.Dimension(26, 26));
        imgCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgCerrarMouseClicked(evt);
            }
        });

        imgAceptar.setMinimumSize(new java.awt.Dimension(26, 26));
        imgAceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgAceptarMouseClicked(evt);
            }
        });

        imgBarraTitulo.setLayer(txtTitulo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        imgBarraTitulo.setLayer(imgCerrar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        imgBarraTitulo.setLayer(imgAceptar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout imgBarraTituloLayout = new javax.swing.GroupLayout(imgBarraTitulo);
        imgBarraTitulo.setLayout(imgBarraTituloLayout);
        imgBarraTituloLayout.setHorizontalGroup(
            imgBarraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imgBarraTituloLayout.createSequentialGroup()
                .addComponent(imgCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imgAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        imgBarraTituloLayout.setVerticalGroup(
            imgBarraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(imgBarraTituloLayout.createSequentialGroup()
                .addGroup(imgBarraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imgCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(imgBarraTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imgBarraTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void imgCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgCerrarMouseClicked
        /*for(int x=0;x<jTSalida.getColumnCount();x++){
            System.out.println(x+" "+jTSalida.getColumn(jTSalida.getColumnName(x)).getWidth());
        }*/
        borrarTabla(jTSalida, dftmS);
        borrarTabla(jTEntrada, dftmE);
        salida.clear();
        this.dispose();
    }//GEN-LAST:event_imgCerrarMouseClicked

    private void imgBarraTituloMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgBarraTituloMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_imgBarraTituloMousePressed

    private void imgBarraTituloMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgBarraTituloMouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - (x + 5), point.y - (y + 50));
    }//GEN-LAST:event_imgBarraTituloMouseDragged

    private void imgAceptarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgAceptarMouseClicked
        if (salida.size() > 0) {
            CONFIRMARMOVIMIENTO cm = new CONFIRMARMOVIMIENTO(origen, salida);
            AUXILIAR.verPantalla(PRINCIPAL.jdpEscritorio, cm);
        } else {

            JOptionPane.showMessageDialog(null, "Es necesario un elemento en la lista");
        }
    }//GEN-LAST:event_imgAceptarMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        agregar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbTiposItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTiposItemStateChanged
        llenarEntrada();
        llenarSalida();
    }//GEN-LAST:event_cbTiposItemStateChanged

    private void jTEntradaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTEntradaMouseClicked
        if (evt.getClickCount() == 2) {
            agregar();
        }
    }//GEN-LAST:event_jTEntradaMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        borrar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTSalidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTSalidaMouseClicked

    }//GEN-LAST:event_jTSalidaMouseClicked

    private void jTSalidaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTSalidaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int fila = jTSalida.getSelectedRow();
            salida.get(fila).setMedidaZ(Float.valueOf(dftmS.getValueAt(fila, 0).toString()));
        }
    }//GEN-LAST:event_jTSalidaKeyReleased

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        cbTipos.setSelectedItem("TODOS");
        llenarEntrada();
    }//GEN-LAST:event_txtBuscarKeyReleased

    /*
    private void imgActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgAjuste1MouseClicked
        llenarEntrada();
    }//GEN-LAST:event_imgAjuste1MouseClicked
*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox<String> cbTipos;
    private OTROS.IMAGEN imgAceptar;
    private OTROS.IMAGEN imgBarraTitulo;
    private OTROS.IMAGEN imgCerrar;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JTable jTEntrada;
    public static javax.swing.JTable jTSalida;
    private javax.swing.JPanel jpFondo;
    public static javax.swing.JTextField txtBuscar;
    private javax.swing.JLabel txtTitulo;
    // End of variables declaration//GEN-END:variables
}
