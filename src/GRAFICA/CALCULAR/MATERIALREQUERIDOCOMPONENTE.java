package GRAFICA.CALCULAR;

import CLASES.COMPONENTE;
import CLASES.ESTILOS;
import CLASES.material;
import EXPORTAREXCEL.MATERIALNECESARIOEXCEL;
import OTROS.AUXILIAR;
import OTROS.CALCULARMATERIAL;
import OTROS.CALCULARMATERIALCOMPLETO;
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
import sql.SQLMUEBLES;

public class MATERIALREQUERIDOCOMPONENTE extends javax.swing.JInternalFrame {

    private int x;
    private int y;
    ArrayList<COMPONENTE> lisCom;
    ArrayList<material> listaMaterial;
    ArrayList lisModelos;
    ArrayList tiposMaterial;

    TableColumnModel columnModel = null;
    int seleccion = 1;
    int idcomponente = -1;
    SQLMUEBLES modMuebles = new SQLMUEBLES();
    SQLALMACEN modAlmacen = new SQLALMACEN();
    CALCULARMATERIAL cm = new CALCULARMATERIAL();
    DecimalFormat for4 = new DecimalFormat("#,###.####");
    DecimalFormat for2 = new DecimalFormat("#,###.##");
    String modelo=null;
    String componente=null;
    ArrayList<ESTILOS> estilos;
    ArrayList<ESTILOS> tapizados;
    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
    DefaultTableModel dftm = new DefaultTableModel() {
        public boolean isCellEditable(int fila, int columna) {
            if (columna == jTable1.getColumnCount()) {
                return true;
            }
            return false;
        }
    };

    public MATERIALREQUERIDOCOMPONENTE() {
        initComponents();
        /*parametros de ventana*/
        imgBarraTitulo.setBackgroung("IMAGENES/TEXTURAS/marmol.png");
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        imgCerrar.setBackgroung("IMAGENES/ICONOS/cerrar2.png");
        imgPdf.setBackgroung("IMAGENES/ICONOS/pdf.png");
        imgExcel.setBackgroung("IMAGENES/ICONOS/excel.png");
        imgAjuste.trasparente();
        /*listas*/
        lisModelos = modMuebles.ObtenerModelos();
        lisCom = modMuebles.obtenerComponentes("TODO");
        tiposMaterial = modAlmacen.ObtenerTiposMaterial();
        tapizados = modMuebles.obtenerTapizados();
        estilos = modMuebles.obtenerEstilos();

        /*elementos de ventana*/
        llenarCbModelo();
        llenarcbMaterial();
        llenarcbPintura();
        llenarcbTapizado();
        crearTabla();

    }

    public void crearTabla() {
        jTable1.setModel(dftm);

        dftm.setColumnIdentifiers(new Object[]{""});
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        columnModel = jTable1.getColumnModel();
    }

    public void cambiarTabla() {

        switch (seleccion) {
            case 1:
                dftm.setColumnIdentifiers(new Object[]{"Material", "Tipo", "Cantidad", "Unides", "P/U", "Total"});
                break;
            case 2:
                dftm.setColumnIdentifiers(new Object[]{"Material", "Tipo", "Paquete", "Empaque", "P/U", "Total"});
                break;
        }
        columnModel.getColumn(0).setPreferredWidth(427);
        columnModel.getColumn(1).setPreferredWidth(159);
        columnModel.getColumn(2).setPreferredWidth(66);
        columnModel.getColumn(3).setPreferredWidth(78);
        columnModel.getColumn(4).setPreferredWidth(78);
    }

    public void llenarCbModelo() {
        cbModelo.removeAllItems();
        for (int a = 0; a < lisModelos.size(); a++) {
            ArrayList lista = (ArrayList) lisModelos.get(a);
            if (lista.get(2).toString().equals("false")) {
                cbModelo.addItem(lista.get(0).toString());
            }
        }
    }

    public void llenarCbComp() {
        cbComp.removeAllItems();
        for (COMPONENTE com : lisCom) {
            if (com.getModelo().equals(cbModelo.getSelectedItem())&&!com.getPrueva()) {
                cbComp.addItem(com.getNombre());
            }
        }
    }

    public void llenarcbMaterial() {
        cbMaterial.removeAllItems();
        cbMaterial.addItem("TODOS");
        for (int a = 0; a < tiposMaterial.size(); a++) {
            ArrayList lis2 = (ArrayList) tiposMaterial.get(a);
            cbMaterial.addItem(lis2.get(0).toString());
        }
        cbMaterial.addItem("Precio invalido");

    }

    public void llenarcbPintura() {
        cbPintura.removeAllItems();
        for (ESTILOS est : estilos) {
            cbPintura.addItem(est.getNombre());
        }
    }

    public void llenarcbTapizado() {
        cbTapizado.removeAllItems();
        cbTapizado.addItem("");
        for (ESTILOS tap : tapizados) {
            cbTapizado.addItem(tap.getNombre());
        }
    }

    public void llenarTabla() {
        float tTotal = 0;
        cambiarTabla();
        columnModel.getColumn(2).setCellRenderer(rightRenderer);
        borrarTabla();
        for (material mat : listaMaterial) {
            if (mat.getTipo().equals(cbMaterial.getSelectedItem()) || cbMaterial.getSelectedItem().equals("TODOS")) {
                float total = Float.valueOf(mat.getPrecioUnidad()) * mat.getCantidad() * Integer.valueOf(txtCantidad.getText());
                String totalS = "$ " + for2.format(total);
                mat.setCantidaPaquetes((mat.getCantidad() / mat.getPaquete()) * Integer.valueOf(txtCantidad.getText()));
                switch (seleccion) {
                    case 1://"Material","Tipo","Cantidad","P/U","Total"
                        dftm.addRow(new Object[]{mat.getMaterial(), mat.getTipo(),
                            for4.format(mat.getCantidad() * Integer.valueOf(txtCantidad.getText())),
                            mat.getUnidades(), "$ " + for4.format(mat.getPrecioUnidad()), totalS});
                        break;
                    case 2://"Material","Tipo","Paquete","P/U","Total"
                        dftm.addRow(new Object[]{mat.getMaterial(), mat.getTipo(),
                            for4.format(mat.getCantidaPaquetes()), mat.getEmpaque(),
                            "$ " + for4.format(mat.getPrecioPaquete()), totalS});
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

    public void ordenarTipos() {
        Collections.sort(listaMaterial, new Comparator<material>() {
            public int compare(material obj1, material obj2) {
                return obj1.getTipo().compareTo(obj2.getTipo());
            }
        });
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
        cbComp = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        txtCantidad = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cbModelo = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnForma = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cbMaterial = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        Pintura = new javax.swing.JLabel();
        cbPintura = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbTapizado = new javax.swing.JComboBox<>();

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
        jLabel2.setText("Calcular material necesario");

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

        jButton1.setText("Calcular");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtCantidad.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCantidad.setText("1");
        txtCantidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCantidadMouseClicked(evt);
            }
        });
        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
            }
        });

        jLabel1.setText("Cantidad");

        cbModelo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbModeloItemStateChanged(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        btnForma.setText("Por Paquete");
        btnForma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFormaActionPerformed(evt);
            }
        });

        jLabel3.setText("Material");

        cbMaterial.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbMaterialItemStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Total");

        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setText("0");

        Pintura.setText("Pintura");

        jLabel5.setText("Tapizado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgBarraTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(Pintura)
                                .addGap(22, 22, 22)
                                .addComponent(cbPintura, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbTapizado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbComp, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(cbMaterial, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnForma, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTotal))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imgBarraTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Pintura)
                    .addComponent(cbPintura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cbTapizado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnForma)
                    .addComponent(jLabel4)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void imgPdfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgPdfMouseClicked

        if (listaMaterial!=null||idcomponente>0) {
            String[] options = {"Completo", "Resumen"};
            int seleccion = JOptionPane.showOptionDialog(null, "Tipo de reporte", "reporte",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            switch (seleccion) {
                case 0://completo
                    CALCULARMATERIALCOMPLETO cmc = new CALCULARMATERIALCOMPLETO();
                    ArrayList<material> lista = cmc.calcularMaterial(idcomponente,Integer.valueOf(txtCantidad.getText()));
                    
                    try {
                        String rutaArchivo = (new File("")).getAbsolutePath();
                        rutaArchivo = rutaArchivo + "\\archivos\\reportesPdf";
                        Map parametros = new HashMap();
                        parametros.put("modelo", modelo);
                        parametros.put("componente", componente);
                        PARAMETROSIMPRESION.impresionReporte(lista, parametros, "MATERIALCOMPLETOCOMPONENTEchico", rutaArchivo);

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(rootPane, e);
                    }
                    break;
                case 1:
                    ordenarTipos();
                    try {
                        String rutaArchivo = (new File("")).getAbsolutePath();
                        rutaArchivo = rutaArchivo + "\\archivos\\reportesPdf";
                        Map parametros = new HashMap();
                        parametros.put("cantidad", Integer.valueOf(txtCantidad.getText()));
                        parametros.put("titulo", modelo);
                        parametros.put("componente", componente);
                        PARAMETROSIMPRESION.impresionReporte(listaMaterial, parametros, "MATERIALREQUERIDOPDF", rutaArchivo);

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(rootPane, e);
                    }
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Lista vacia");
        }
        /*for(int x=0;x<jTable1.getColumnCount();x++){
        System.out.println(x+" "+jTable1.getColumn(jTable1.getColumnName(x)).getWidth());
        }*/
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
        this.dispose();
    }//GEN-LAST:event_imgCerrarMouseClicked

    private void cbModeloItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbModeloItemStateChanged
        llenarCbComp();
    }//GEN-LAST:event_cbModeloItemStateChanged

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed

    }//GEN-LAST:event_txtCantidadActionPerformed

    private void btnFormaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFormaActionPerformed
        seleccion++;
        if (seleccion > 2) {
            seleccion = 1;
        }
        switch (seleccion) {
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

    private void txtCantidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCantidadMouseClicked
        txtCantidad.setText("");
    }//GEN-LAST:event_txtCantidadMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ESTILOS est = new ESTILOS();
        for (ESTILOS esti : estilos) {
            if (esti.getNombre().equals(cbPintura.getSelectedItem().toString())) {
                est = esti;
                break;
            }
        }
        if (cbTapizado.getSelectedItem().toString().length() > 0) {
            for (ESTILOS esti : tapizados) {
                if (esti.getNombre().equals(cbTapizado.getSelectedItem().toString())) {
                    est.setIntFondo(esti.getIntFondo());
                    est.setIntHilo(esti.getIntHilo());
                    est.setIntTela(esti.getIntTela());
                    est.setIntVinil(esti.getIntVinil());
                    break;
                }
            }
        }
        COMPONENTE com = new COMPONENTE();
        for (COMPONENTE com2 : lisCom) {
            if (com2.getModelo().equals(cbModelo.getSelectedItem())) {
                if (com2.getNombre().equals(cbComp.getSelectedItem())) {
                    com = com2;
                    modelo=cbModelo.getSelectedItem().toString();
                    componente=cbComp.getSelectedItem().toString();
                    idcomponente = com2.getId();
                }
            }
        }

        com.setEstilo(est);
        ArrayList<COMPONENTE> lista = new ArrayList();
        lista.add(com);
        listaMaterial = new ArrayList();
        CALCULARMATERIAL cm = new CALCULARMATERIAL();
        listaMaterial = cm.calcularMaterial(lista,true);
        llenarTabla();
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbMaterialItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbMaterialItemStateChanged
        try {
            llenarTabla();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cbMaterialItemStateChanged

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        if (AUXILIAR.isInt(txtCantidad.getText())) {
            if (Integer.valueOf(txtCantidad.getText()) > 0) {
                llenarTabla();
            }
        }
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void imgExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgExcelMouseClicked
        if (listaMaterial.size() > 0) {
            Object[] titulo = new Object[]{"Material", "Tipo", "Cantidad", "Paquete", "P/U", "Total"};
            MATERIALNECESARIOEXCEL mre = new MATERIALNECESARIOEXCEL();
            mre.modificarplantilla(titulo, listaMaterial, cbComp.getSelectedItem().toString(), cbModelo.getSelectedItem().toString());
        } else {
            JOptionPane.showMessageDialog(null, "Lista vacia");
        }

    }//GEN-LAST:event_imgExcelMouseClicked

    private void imgAjusteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgAjusteMouseClicked
       for(int x=0;x<jTable1.getColumnCount();x++){
            System.out.println(x+" "+jTable1.getColumn(jTable1.getColumnName(x)).getWidth());
        }
    }//GEN-LAST:event_imgAjusteMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Pintura;
    private javax.swing.JButton btnForma;
    private javax.swing.JComboBox<String> cbComp;
    private javax.swing.JComboBox<String> cbMaterial;
    private javax.swing.JComboBox<String> cbModelo;
    private javax.swing.JComboBox<String> cbPintura;
    private javax.swing.JComboBox<String> cbTapizado;
    private OTROS.IMAGEN imgAjuste;
    private OTROS.IMAGEN imgBarraTitulo;
    private OTROS.IMAGEN imgCerrar;
    private OTROS.IMAGEN imgExcel;
    private OTROS.IMAGEN imgPdf;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
