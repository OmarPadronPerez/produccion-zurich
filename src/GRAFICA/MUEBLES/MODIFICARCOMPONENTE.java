package GRAFICA.MUEBLES;

import CLASES.COMPONENTE;
import CLASES.MODELO;
import CLASES.material;
import GRAFICA.PRINCIPAL;
import OTROS.PANTALLACARGA;
import static GRAFICA.PRINCIPAL.jdpEscritorio;
import OTROS.AUXILIAR;
import OTROS.archivosexp;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import sql.SQLMUEBLES;

public class MODIFICARCOMPONENTE extends javax.swing.JInternalFrame {

    private int x;
    private int y;
    String rutaExplo = null;
    String rutaCorte = null;
    String rutaFoto=null;
    public static COMPONENTE com = null;
    public static ArrayList<material> borrar = new ArrayList();
    SQLMUEBLES modMuebles = new SQLMUEBLES();
    MODELO modeloUsado;

    public MODIFICARCOMPONENTE(COMPONENTE com, MODELO modelo) {
        initComponents();
        this.com = com;
        this.modeloUsado = modelo;
        imgBarraTitulo.setBackgroung("IMAGENES/TEXTURAS/marmol.png");
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        imgCerrar.setBackgroung("IMAGENES/ICONOS/cerrar2.png");
        imgAjuste.trasparente();
        llenarCampos();
        cambiarColorBotones();
        txtModelo.setText(com.getModelo());
    }
    
    public MODIFICARCOMPONENTE(int id) {
        initComponents();
        this.com = modMuebles.obtenerComponenteId(id);
        this.modeloUsado = modMuebles.ObtenerModelosClases(com.getModelo());
        imgBarraTitulo.setBackgroung("IMAGENES/TEXTURAS/marmol.png");
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        imgCerrar.setBackgroung("IMAGENES/ICONOS/cerrar2.png");
        imgAjuste.trasparente();
        llenarCampos();
        cambiarColorBotones();
        txtModelo.setText(com.getModelo());
        
    }
    
    public void llenarCampos(){
        if (com.getNombre() != null) {
            txtTitulo.setText(com.getNombre() + " DE " + com.getModelo());
            txtNombre.setText(com.getNombre());
            txtNombre.setEditable(false);
            txtDescripcion.setText(com.getObservaciones());
            txtX.setText(String.valueOf(com.getX()));
            txtY.setText(String.valueOf(com.getY()));
            txtZ.setText(String.valueOf(com.getZ()));
            txtNota.setText(com.getNota());
            com.setMateriales(modMuebles.obtenerMaterialComponente(com.getId()));
            cbPrueva.setSelected(com.getPrueva());
        } else {
            txtTitulo.setText("COMPONENTE NUEVO PARA " + com.getModelo());
        }
    }

    public static void cambiarColorBotones() {
        if (com.getMateriales().size() > 0 && com.getMateriales() != null) {
            btnMaterial.setBackground(Color.GREEN);
            btnMaterial.setForeground(Color.black);
        } else {
            btnMaterial.setBackground(Color.red);
            btnMaterial.setForeground(Color.WHITE);
        }
        if (com.getExplosivo() != null && com.getExplosivo().length() > 0) {
            btnExplosivo.setBackground(Color.GREEN);
            btnExplosivo.setForeground(Color.black);
        } else {
            btnExplosivo.setBackground(Color.red);
            btnExplosivo.setForeground(Color.WHITE);
        }
        if (com.getCorte() != null && com.getCorte().length() > 0) {
            btnCorte.setBackground(Color.GREEN);
            btnCorte.setForeground(Color.black);
        } else {
            btnCorte.setBackground(Color.red);
            btnCorte.setForeground(Color.WHITE);
        }
        if (com.getFoto() != null && com.getFoto().length() > 0) {
            btnFoto.setBackground(Color.GREEN);
            btnFoto.setForeground(Color.black);
        } else {
            btnFoto.setBackground(Color.red);
            btnFoto.setForeground(Color.WHITE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imgBarraTitulo = new OTROS.IMAGEN();
        imgCerrar = new OTROS.IMAGEN();
        txtTitulo = new javax.swing.JLabel();
        imgAjuste = new OTROS.IMAGEN();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        txtX = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtY = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtZ = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnMaterial = new javax.swing.JButton();
        btnExplosivo = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtModelo = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtNota = new javax.swing.JTextField();
        cbPrueva = new javax.swing.JCheckBox();
        btnCorte = new javax.swing.JButton();
        btnFoto = new javax.swing.JButton();

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

        imgCerrar.setMinimumSize(new java.awt.Dimension(26, 26));
        imgCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgCerrarMouseClicked(evt);
            }
        });

        txtTitulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTitulo.setText("xxxxx");

        imgAjuste.setMinimumSize(new java.awt.Dimension(26, 26));
        imgAjuste.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgAjusteMouseClicked(evt);
            }
        });

        imgBarraTitulo.setLayer(imgCerrar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        imgBarraTitulo.setLayer(txtTitulo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        imgBarraTitulo.setLayer(imgAjuste, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout imgBarraTituloLayout = new javax.swing.GroupLayout(imgBarraTitulo);
        imgBarraTitulo.setLayout(imgBarraTituloLayout);
        imgBarraTituloLayout.setHorizontalGroup(
            imgBarraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imgBarraTituloLayout.createSequentialGroup()
                .addComponent(imgCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imgAjuste, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        imgBarraTituloLayout.setVerticalGroup(
            imgBarraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(imgCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(imgAjuste, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel1.setText("Nombre");

        jLabel2.setText("Modelo");

        jLabel3.setText("Descripcion");

        jLabel4.setText("Medidas");

        jLabel5.setText("CM");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("X");

        jLabel7.setText("CM");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setText("X");

        jLabel9.setText("CM");

        btnMaterial.setBackground(new java.awt.Color(204, 0, 51));
        btnMaterial.setForeground(new java.awt.Color(255, 255, 255));
        btnMaterial.setText("Materiales y piezas");
        btnMaterial.setPreferredSize(new java.awt.Dimension(83, 23));
        btnMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaterialActionPerformed(evt);
            }
        });

        btnExplosivo.setBackground(new java.awt.Color(204, 0, 51));
        btnExplosivo.setForeground(new java.awt.Color(255, 255, 255));
        btnExplosivo.setText("Explosivos");
        btnExplosivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExplosivoActionPerformed(evt);
            }
        });

        jButton4.setText("Guardar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        txtModelo.setText("jLabel10");

        jLabel10.setText("Nota");

        cbPrueva.setText("MUESTRA");
        cbPrueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPruevaActionPerformed(evt);
            }
        });

        btnCorte.setBackground(new java.awt.Color(204, 0, 51));
        btnCorte.setForeground(new java.awt.Color(255, 255, 255));
        btnCorte.setText("Cortes y angulos");
        btnCorte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCorteActionPerformed(evt);
            }
        });

        btnFoto.setBackground(new java.awt.Color(204, 0, 51));
        btnFoto.setForeground(new java.awt.Color(255, 255, 255));
        btnFoto.setText("Fotos");
        btnFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFotoActionPerformed(evt);
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
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre)
                            .addComponent(txtDescripcion)
                            .addComponent(txtModelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNota)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cbPrueva)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addComponent(txtX, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel5))
                            .addComponent(btnMaterial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCorte, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(txtY, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(txtZ, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnFoto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnExplosivo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imgBarraTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtModelo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExplosivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFoto)
                    .addComponent(btnCorte))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(cbPrueva))
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
        setLocation(point.x - (x + 5), point.y - (y + 50));
    }//GEN-LAST:event_imgBarraTituloMouseDragged

    private void imgCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgCerrarMouseClicked
        this.dispose();
    }//GEN-LAST:event_imgCerrarMouseClicked

    private void imgAjusteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgAjusteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_imgAjusteMouseClicked

    private void btnMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaterialActionPerformed
        com.setNombre(txtNombre.getText().toUpperCase());
        MODIFICARCOMPONENTEMATERIALES mod = new MODIFICARCOMPONENTEMATERIALES(com.getModelo().toUpperCase(), com.getNombre().toUpperCase());
        AUXILIAR.verPantalla(jdpEscritorio, mod);
    }//GEN-LAST:event_btnMaterialActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        PANTALLACARGA pc = new PANTALLACARGA();
        AUXILIAR.verPantalla(jdpEscritorio, pc);
        pc.moveToFront();
        if (txtNombre.getText().length() > 0) {
            if (com.getMateriales().size() > 0) {
                if (AUXILIAR.isFloat(txtX.getText())
                        && AUXILIAR.isFloat(txtY.getText())
                        && AUXILIAR.isFloat(txtZ.getText())){

                    modMuebles.guardarModelo(modeloUsado);
                    if (rutaExplo != null) {
                        archivosexp arc = new archivosexp();
                        String nombre = txtNombre.getText() + "-" + txtModelo.getText() + ".pdf";
                        String rutaSalida = "\\\\" + PRINCIPAL.conexiones.getServidor() + "\\zurich\\archivos\\explosivos\\" + nombre;
                        if (arc.copiarArchivo(rutaExplo, rutaSalida)) {
                            com.setExplosivo(nombre);
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al guardar explosivo");
                        }
                    }
                    if (rutaCorte != null) {
                        archivosexp arc = new archivosexp();
                        String nombre = txtNombre.getText() + "-" + txtModelo.getText() + ".jpg";
                        String rutaSalida = "\\\\" + PRINCIPAL.conexiones.getServidor() + "\\zurich\\archivos\\cortes\\" + nombre;
                        System.out.println("rutaSalidacorte " + rutaSalida);
                        if (arc.copiarArchivo(rutaCorte, rutaSalida)) {
                            com.setCorte(nombre);
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al guardar el archivo de cotes y angulos");
                        }
                    }
                    if (rutaFoto != null) {
                        archivosexp arc = new archivosexp();
                        String nombre = txtNombre.getText() + "-" + txtModelo.getText() + ".jpg";
                        String rutaSalida = "\\\\" + PRINCIPAL.conexiones.getServidor() + "\\zurich\\archivos\\fotos\\" + nombre;
                        if (arc.copiarArchivo(rutaFoto, rutaSalida)) {
                            com.setFoto(nombre);
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al guardar foto");
                        }
                    }
                    
                    com.setObservaciones(txtDescripcion.getText());
                    com.setX(Float.valueOf(txtX.getText()));
                    com.setY(Float.valueOf(txtY.getText()));
                    com.setZ(Float.valueOf(txtZ.getText()));
                    com.setNota(txtNota.getText().toUpperCase());
                    com.setPrueva(cbPrueva.isSelected());
                    modMuebles.guardarComponente(com);
                    if(com.getId()<0){
                        com.setId(modMuebles.obtenerIdNuevoComponente());
                    }
                    for (material bor : borrar) {
                        if (bor.getId() > 0) {
                            modMuebles.borrarMateriales(bor.getId());
                        }
                    }
                    //modMuebles.guardarMaterialUsado(com.getMateriales(),com);
                    ArrayList<material> lista = com.getMateriales();
                    //System.out.println("lista.size()"+lista.size()+"  com.getId() "+com.getId());
                    int i=0;
                        for (material mat : lista) {
                        modMuebles.guardarMaterialUsado(mat,com.getId());
                        i++;
                    }
                    
                    if(borrar.size()>0){
                        borrar.clear();
                    }
                    
                    //lista.clear();
                    try {
                        VERCOMPONENTES.txtModelo.setText(VERCOMPONENTES.txtModelo.getText().toUpperCase());
                        VERCOMPONENTES.txtModelo.setEnabled(false);
                        VERCOMPONENTES.llenarTabla();
                        pc.continuar = false;
                    } catch (Exception e) {
                    }

                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Es necesario agregar medidas");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Es necesario agregar materiales");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Es necesario un nombre");
        }
        pc.continuar = false;
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnExplosivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExplosivoActionPerformed
        File archivo = null;
        int resultado = 0;

        try {
            JFileChooser selectorArchivos = new JFileChooser();
            selectorArchivos.setFileSelectionMode(0);
            FileNameExtensionFilter filtro = new FileNameExtensionFilter(" Portable Document Format *.PDF", new String[]{"Pdf"});
            selectorArchivos.setFileFilter(filtro);
            resultado = selectorArchivos.showOpenDialog(this);
            archivo = selectorArchivos.getSelectedFile();

            if (archivo != null) {
                rutaExplo = archivo.getAbsolutePath();
                btnExplosivo.setBackground(Color.GREEN);
                btnExplosivo.setForeground(Color.black);
            }

        } catch (Exception e) {
            Logger.getLogger(MODIFICARCOMPONENTE.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("JFileChooser " + e);
        }
    }//GEN-LAST:event_btnExplosivoActionPerformed

    private void cbPruevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPruevaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbPruevaActionPerformed

    private void btnCorteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorteActionPerformed
        File archivo = null;
        int resultado = 0;

        try {
            JFileChooser selectorArchivos = new JFileChooser();
            selectorArchivos.setFileSelectionMode(0);
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.jpg", new String[]{"jpg"});
            selectorArchivos.setFileFilter(filtro);
            resultado = selectorArchivos.showOpenDialog(this);
            archivo = selectorArchivos.getSelectedFile();

            if (archivo != null) {
                rutaCorte = archivo.getAbsolutePath();
                btnCorte.setBackground(Color.GREEN);
                btnCorte.setForeground(Color.black);
            }

        } catch (Exception e) {
            Logger.getLogger(MODIFICARCOMPONENTE.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("JFileChooser " + e);
        }
    }//GEN-LAST:event_btnCorteActionPerformed

    private void btnFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFotoActionPerformed
        File archivo = null;
        int resultado = 0;

        try {
            JFileChooser selectorArchivos = new JFileChooser();
            selectorArchivos.setFileSelectionMode(0);
            //FileNameExtensionFilter filtro = new FileNameExtensionFilter("PNG *.png", new String[]{"png"});
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.jpg", new String[]{"jpg"});
            selectorArchivos.setFileFilter(filtro);
            resultado = selectorArchivos.showOpenDialog(this);
            archivo = selectorArchivos.getSelectedFile();

            if (archivo != null) {
                rutaFoto = archivo.getAbsolutePath();
                btnFoto.setBackground(Color.GREEN);
                btnFoto.setForeground(Color.black);
            }

        } catch (Exception e) {
            Logger.getLogger(MODIFICARCOMPONENTE.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("JFileChooser " + e);
        }
    }//GEN-LAST:event_btnFotoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnCorte;
    public static javax.swing.JButton btnExplosivo;
    public static javax.swing.JButton btnFoto;
    public static javax.swing.JButton btnMaterial;
    private javax.swing.JCheckBox cbPrueva;
    private OTROS.IMAGEN imgAjuste;
    private OTROS.IMAGEN imgBarraTitulo;
    private OTROS.IMAGEN imgCerrar;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JLabel txtModelo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNota;
    private javax.swing.JLabel txtTitulo;
    private javax.swing.JTextField txtX;
    private javax.swing.JTextField txtY;
    private javax.swing.JTextField txtZ;
    // End of variables declaration//GEN-END:variables
}
