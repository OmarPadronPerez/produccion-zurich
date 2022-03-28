package GRAFICA.MUEBLES;

import CLASES.AREAS;
import CLASES.material;
import OTROS.AUXILIAR;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import sql.SQLALMACEN;

public class MODIFICARCOMPONENTEMATERIALES extends javax.swing.JInternalFrame {

    private int x;
    private int y;
    public SQLALMACEN modAlmacen = new SQLALMACEN();
    TableColumnModel columnModel = null;
    public ArrayList<material> matPrima = null;
    int idMaterial = -1;
    String modelo = null;
    String componente = null;
    int folio=-1;
    DefaultTableModel dftm = new DefaultTableModel() {
        public boolean isCellEditable(int fila, int columna) {
            if (columna == jTable1.getColumnCount()) {
                return true;
            }
            return false;
        }
    };

    public MODIFICARCOMPONENTEMATERIALES(String modelo, String componente) {
        llenarMateriales();
        prepararMateriales();
        initComponents();
        crearTabla();
        this.modelo = modelo;
        this.componente = componente;
        //matPrima=modAlmacen.obtenerTodoMateriales();
        imgBarraTitulo.setBackgroung("IMAGENES/TEXTURAS/marmol.png");
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        imgCerrar.setBackgroung("IMAGENES/ICONOS/si.png");
        imgAjuste.trasparente();
        txtTitulo.setText("PIEZAS PARA " + componente + " DE " + modelo);
        llenarCBtipo();
        llenarcbSeccion();
    }

    public void crearTabla() {
        columnModel = jTable1.getColumnModel();
        jTable1.setModel(dftm);
        dftm.setColumnIdentifiers(new Object[]{"Cant", "Pieza", "Descripcion", "Parte", "Material", "X(Cm)", "Y(Cm)", "Z(Cm)", "CNC",""});
        columnModel.getColumn(0).setPreferredWidth(31);
        columnModel.getColumn(1).setPreferredWidth(186);
        //columnModel.getColumn(2).setPreferredWidth(205);
        columnModel.getColumn(2).setMinWidth(200);
        columnModel.getColumn(3).setPreferredWidth(73);
        columnModel.getColumn(4).setPreferredWidth(138);
        columnModel.getColumn(5).setPreferredWidth(45);
        columnModel.getColumn(6).setPreferredWidth(45);
        columnModel.getColumn(7).setPreferredWidth(45);
        columnModel.getColumn(8).setPreferredWidth(39);
        
        columnModel.getColumn(9).setPreferredWidth(0);
        columnModel.getColumn(9).setMaxWidth(0);
        columnModel.getColumn(8).setResizable(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
    }

    public void llenarCBtipo() {
        ArrayList lista = modAlmacen.ObtenerTiposMaterial();
        for (int a = 0; a < lista.size(); a++) {
            ArrayList lista2 = (ArrayList) lista.get(a);
            if (lista2.get(1).equals("Si")) {
                cbTipo.addItem(lista2.get(0).toString());
            }
        }
    }

    public void llenarCBMaterial() {
        cbMaterial.removeAllItems();
        for (int a = 0; a < matPrima.size(); a++) {
            material mat = matPrima.get(a);
            if (mat.getTipo().equals(cbTipo.getSelectedItem()) && mat.getMedidaZ() == 1) {
                cbMaterial.addItem(mat.getMaterial());
            }
        }
    }
    public void llenarcbSeccion(){
        cbSeccion.removeAllItems();
        cbSeccion.addItem("GENERAL");
        ArrayList<material> lista = MODIFICARCOMPONENTE.com.getMateriales();
        for (material mat : lista) {
            boolean encontrado=false;
            for(int i=0;i<cbSeccion.getItemCount();i++){
                if(cbSeccion.getItemAt(i).equals(mat.getParte())){
                    encontrado=true;
                    break;
                }
            }
            if(!encontrado){
                cbSeccion.addItem(mat.getParte());
            }
        }
    }

    public void llenarMateriales() {
        matPrima = modAlmacen.obtenerTodoMateriales();
        ArrayList<AREAS> areas = modAlmacen.obtenerElementosArea();
        for (AREAS area:areas) {
            material mat = new material();
            mat.setMaterial(area.getElemento());
            mat.setIdMaterial(area.getElemento());
            mat.setTipo(area.getArea());
            mat.setMedidaZ(1);
            matPrima.add(mat);
        }
    }

    public void prepararMateriales() {
        ArrayList<material> lista = MODIFICARCOMPONENTE.com.getMateriales();
        for (material mat : lista) {
            for (material prima : matPrima) {
                if (prima.getIdMaterial().equals(mat.getIdMaterial())) {
                    mat.setMaterial(prima.getMaterial());
                    mat.setTipo(prima.getTipo());
                }
            }
        }
    }

    public void llenarId() {
        for (material mat : matPrima) {
            if (mat.getTipo().equals(cbTipo.getSelectedItem())) {
                if (mat.getMaterial().equals(cbMaterial.getSelectedItem())) {
                    txtidMaterial.setText(mat.getIdMaterial());
                    break;
                }
            }
        }
    }

    public void llenarTabla() {
        borrarTabla();
        ArrayList<material> lista = MODIFICARCOMPONENTE.com.getMateriales();

        for (material mat : lista) {
            if (mat.getTipo().equals(cbTipo.getSelectedItem())) {
                String cnc;
                if(mat.getCnc()){
                    cnc="SI";
                }else{
                    cnc="NO";
                }
                dftm.addRow(new Object[]{mat.getCantidad(), mat.getPieza(), mat.getDescripcion(),
                    mat.getParte(),mat.getMaterial(), mat.getMedidaX(), mat.getMedidaY(),
                    mat.getMedidaZ(),cnc,mat.getId()});
            }
        }
    }

    void borrarTabla() {
        for (int i = 0; i < this.jTable1.getRowCount(); i++) {
            this.dftm.removeRow(i);
            i--;
        }
    }

    public void borrar() {
        int fila = jTable1.getSelectedRow();
        if (fila > -1) {
            /*String pieza = dftm.getValueAt(fila, 1).toString();
            String material = dftm.getValueAt(fila, 4).toString();
            float x = Float.valueOf(dftm.getValueAt(fila, 5).toString());
            float y = Float.valueOf(dftm.getValueAt(fila, 6).toString());*/

            ArrayList<material> lista = MODIFICARCOMPONENTE.com.getMateriales();
            for (material mat : lista) {
                if(mat.getId() == Integer.valueOf(dftm.getValueAt(fila, 9).toString())){
                    MODIFICARCOMPONENTE.borrar.add(mat);
                    MODIFICARCOMPONENTE.com.getMateriales().remove(mat);
                    llenarTabla();
                    break;
                }
                /*if (material.equals(mat.getMaterial()) && pieza.equals(mat.getPieza())
                        && x == mat.getMedidaX() && y == mat.getMedidaY()) {
                    MODIFICARCOMPONENTE.borrar.add(mat);
                    MODIFICARCOMPONENTE.com.getMateriales().remove(mat);
                    llenarTabla();
                    break;
                }*/
            }
        }

    }

    public void borrarCampos() {
        txtPieza.setText("");
        txtDescripcion.setText("");
        txtX.setText("");
        txtY.setText("");
        txtZ.setText("");
        txtCan.setText("");
        idMaterial = -1;
        cbCnc.setSelected(false);
    }

    public boolean comprovarCampos() {
        int x = 0;
        if (txtX.getText().length() > 0) {
            if (AUXILIAR.isFloat(txtX.getText())) {
                x++;
                txtX.setBackground(Color.WHITE);
            } else {
                txtX.setBackground(Color.red);
            }
        }

        if (txtY.getText().length() > 0) {
            if (AUXILIAR.isFloat(txtY.getText())) {
                x++;
                txtY.setBackground(Color.WHITE);
            } else {
                txtY.setBackground(Color.red);
            }
        }

        if (txtZ.getText().length() > 0) {
            if (AUXILIAR.isFloat(txtZ.getText())) {
                x++;
                txtZ.setBackground(Color.WHITE);
            } else {
                txtZ.setBackground(Color.red);
            }
        }

        if (txtCan.getText().length() > 0) {
            if (AUXILIAR.isFloat(txtCan.getText())) {
                x++;
                txtCan.setBackground(Color.WHITE);
            } else {
                txtCan.setBackground(Color.red);
            }
        }
        if (x == 4) {
            return true;
        } else {
            return false;
        }
    }

    public void agregar() {
        if (comprovarCampos()) {
            material mat = new material();
            mat.setTipo(cbTipo.getSelectedItem().toString());
            mat.setMaterial(cbMaterial.getSelectedItem().toString());
            if (txtPieza.getText().length() > 0) {
                mat.setPieza(txtPieza.getText().toUpperCase());
            } else {
                mat.setPieza("");
            }
            if (txtDescripcion.getText().length() > 0) {
                mat.setDescripcion(txtDescripcion.getText().toUpperCase());
            } else {
                mat.setDescripcion("");
            }
            mat.setMedidaX(Float.valueOf(txtX.getText()));
            mat.setMedidaY(Float.valueOf(txtY.getText()));
            mat.setMedidaZ(Float.valueOf(txtZ.getText()));
            mat.setCantidad(Float.valueOf(txtCan.getText()));
            mat.setIdMaterial(txtidMaterial.getText());  
            if(idMaterial<0){
                mat.setId(folio);
                folio--;
            }else{
                mat.setId(idMaterial);
            }
            
            mat.setModelo(modelo);
            mat.setComponente(componente);
            mat.setCnc(cbCnc.isSelected());
            mat.setParte(cbSeccion.getSelectedItem().toString());
            MODIFICARCOMPONENTE.com.getMateriales().add(mat);
            borrarCampos();
            txtPieza.requestFocus();
            llenarTabla();
        }
    }
    public boolean checarUnCampo(JTextField campo){
        boolean pasa=false;
        if(campo.getText().length()>0){
            if(AUXILIAR.isFloat(campo.getText())){
                pasa=true;
            }
        }
        if(pasa){
            campo.setBackground(Color.WHITE);
        }else{
             campo.setBackground(Color.red);
        }
        return pasa;
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
        cbTipo = new javax.swing.JComboBox<>();
        cbMaterial = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtidMaterial = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtPieza = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtX = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtCan = new javax.swing.JTextField();
        txtY = new javax.swing.JTextField();
        txtZ = new javax.swing.JTextField();
        cbSeccion = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        cbCnc = new javax.swing.JCheckBox();

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
        txtTitulo.setText("XXXXXXXXXXXXXXXXX");

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

        jLabel1.setText("Tipo de material");

        jLabel2.setText("Material");

        cbTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTipoItemStateChanged(evt);
            }
        });

        cbMaterial.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbMaterialItemStateChanged(evt);
            }
        });

        jLabel3.setText("Id");

        txtidMaterial.setText("XXX");

        jLabel5.setText("Pieza");

        txtPieza.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPiezaKeyReleased(evt);
            }
        });

        jLabel6.setText("Descripción");

        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyReleased(evt);
            }
        });

        jLabel7.setText("Medida");

        txtX.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtXKeyReleased(evt);
            }
        });

        jLabel8.setText("CM");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("X");

        jLabel10.setText("CM");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("X");

        jLabel12.setText("MM");

        jButton2.setText("Borrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Agregar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
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

        jLabel4.setText("Cantidad");

        txtCan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCanKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCanKeyTyped(evt);
            }
        });

        txtY.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtYKeyReleased(evt);
            }
        });

        txtZ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtZKeyReleased(evt);
            }
        });

        cbSeccion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GENERAL" }));

        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel15.setText("Seccion");

        cbCnc.setText("CNC");
        cbCnc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCncActionPerformed(evt);
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jLabel6)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbMaterial, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtidMaterial))
                            .addComponent(txtPieza)
                            .addComponent(cbTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtX, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(txtY, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(txtZ, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(cbCnc)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCan, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtDescripcion))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cbSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imgBarraTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtidMaterial)
                    .addComponent(cbSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtPieza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jButton3)
                    .addComponent(jLabel4)
                    .addComponent(txtCan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCnc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void imgAjusteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgAjusteMouseClicked
        /*for(int x=0;x<jTable1.getColumnCount();x++){
        System.out.println(x+" "+jTable1.getColumn(jTable1.getColumnName(x)).getWidth());
        }*/
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
        MODIFICARCOMPONENTE.cambiarColorBotones();
        this.dispose();
    }//GEN-LAST:event_imgCerrarMouseClicked

    private void cbTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTipoItemStateChanged
        llenarCBMaterial();
        llenarTabla();
    }//GEN-LAST:event_cbTipoItemStateChanged

    private void cbMaterialItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbMaterialItemStateChanged
        llenarId();
    }//GEN-LAST:event_cbMaterialItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        /*for(int x=0;x<jTable1.getColumnCount();x++){
        System.out.println(x+" "+jTable1.getColumn(jTable1.getColumnName(x)).getWidth());
        }*/
        agregar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        borrar();
        borrarCampos();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int fila = jTable1.getSelectedRow();
        if (evt.getClickCount() == 2) {//modificar al hacer doble click
            ArrayList<material> lista = MODIFICARCOMPONENTE.com.getMateriales();
            for (material mat : lista) {
                if (mat.getId() == Integer.valueOf(dftm.getValueAt(fila, 9).toString())) {
                    idMaterial = mat.getId();
                    txtPieza.setText(mat.getPieza());
                    txtDescripcion.setText(mat.getDescripcion());
                    txtX.setText(String.valueOf(mat.getMedidaX()));
                    txtY.setText(String.valueOf(mat.getMedidaY()));
                    txtZ.setText(String.valueOf(mat.getMedidaZ()));
                    txtCan.setText(String.valueOf(mat.getCantidad()));
                    cbTipo.setSelectedItem(mat.getTipo());
                    cbMaterial.setSelectedItem(mat.getMaterial());
                    cbSeccion.setSelectedItem(mat.getParte());
                    cbCnc.setSelected(mat.getCnc());
                    lista.remove(mat);
                    llenarTabla();
                    break;
                }
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void txtCanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCanKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER&&comprovarCampos()
                &&Float.valueOf(txtCan.getText().toString())>0){
            agregar();
        }
    }//GEN-LAST:event_txtCanKeyReleased

    private void txtXKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtXKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER && checarUnCampo(txtX)){
            txtY.requestFocus();
        }
    }//GEN-LAST:event_txtXKeyReleased

    private void txtYKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtYKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER&&checarUnCampo(txtY)){
            txtZ.requestFocus();
        }
    }//GEN-LAST:event_txtYKeyReleased

    private void txtZKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtZKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER&&checarUnCampo(txtZ)){
            txtCan.requestFocus();
        }
        
    }//GEN-LAST:event_txtZKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String entrada = null;
        do {
            entrada = JOptionPane.showInputDialog("Seccion: ");
            entrada = entrada.toUpperCase();
        } while (entrada.equals(""));
        if (entrada != null) {
            entrada=entrada.replaceAll("^\\s*", "");
            entrada=entrada.replaceAll("\\s*$", "");
            entrada=entrada.toUpperCase();
            cbSeccion.addItem(entrada);
            cbSeccion.setSelectedItem(entrada);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtCanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCanKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCanKeyTyped

    private void txtPiezaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPiezaKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            txtDescripcion.requestFocus();
        }
    }//GEN-LAST:event_txtPiezaKeyReleased

    private void txtDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            txtX.requestFocus();
        }
    }//GEN-LAST:event_txtDescripcionKeyReleased

    private void cbCncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCncActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbCnc;
    private javax.swing.JComboBox<String> cbMaterial;
    private javax.swing.JComboBox<String> cbSeccion;
    private javax.swing.JComboBox<String> cbTipo;
    private OTROS.IMAGEN imgAjuste;
    private OTROS.IMAGEN imgBarraTitulo;
    private OTROS.IMAGEN imgCerrar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtCan;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtPieza;
    private javax.swing.JLabel txtTitulo;
    private javax.swing.JTextField txtX;
    private javax.swing.JTextField txtY;
    private javax.swing.JTextField txtZ;
    private javax.swing.JLabel txtidMaterial;
    // End of variables declaration//GEN-END:variables
}
