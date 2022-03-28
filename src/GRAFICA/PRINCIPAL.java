package GRAFICA;

import CLASES.USUARIO;
import GRAFICA.ALMACEN.AJUSTES.MOVIMIENTOS;
import GRAFICA.PEDIDOS.ACTUALIZARPEDIDO;
import GRAFICA.ALMACEN.EXISTENCIAS;
import GRAFICA.ALMACEN.GENERALES;
import GRAFICA.ALMACEN.ORDENCOMPRA.NUEVACOMPRA;
import GRAFICA.ALMACEN.ORDENCOMPRA.REIMPRESION;
import GRAFICA.ALMACEN.TIPOSMATERIAL;
import GRAFICA.BODEGA.bodegaExistencias;
import GRAFICA.CALCULAR.CALCULARPEDIDO;
import GRAFICA.CALCULAR.COTIZACION;
import GRAFICA.CALCULAR.MATERIALPERIODO;
import GRAFICA.CREAR.CLIENTES;
import GRAFICA.CREAR.COLORES;
import GRAFICA.CREAR.TAPIZADO;
import GRAFICA.CREAR.VERMODELOS;
import GRAFICA.CREAR.VerUsuario;
import GRAFICA.CALCULAR.MATERIALREQUERIDOCOMPONENTE;
import GRAFICA.CREAR.HOJAPRUEVA;
import GRAFICA.IMPRIMIR.HOJATRABAJO;
import GRAFICA.IMPRIMIR.IMPRIMIRTODO;
import GRAFICA.PEDIDOS.ACTIVOS;
import GRAFICA.PEDIDOS.NUEVOPEDIDO;
import GRAFICA.PRODUCCION.MOVIMIENTOSPRODUC;
import GRAFICA.PRODUCCION.REPORTEPRODUCCION;
import GRAFICA.RESUMEN.CONSUMOPORPERIODO;
import GRAFICA.RESUMEN.RESUMENMOVIMIENTOS;
import GRAFICA.RESUMEN.RESUMENPEDIDOS;
import OTROS.AUXILIAR;
import OTROS.RELOG;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.SQLException;
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
import org.apache.commons.dbcp2.BasicDataSource;

public class PRINCIPAL extends javax.swing.JFrame {

    public static USUARIO user = null;
    public static int pantallaAncho = 0;
    public static int pantallaAlto = 0;
    public static BasicDataSource dataSource = null;
    public static POOLCONEXIONES conexiones;
    
    public boolean continuar=false;
    

    public PRINCIPAL() {
        initComponents();
        continuar=true;
        txtEstado.setVisible(false);
        conexiones = new POOLCONEXIONES();
        dataSource = conexiones.hacerpool();
        setTitle("Control  ||  Muebleria Zurich");
        setExtendedState(6);
        this.pnlMavs.setBackgroung("IMAGENES/fondo.jpg");
        setIconImage(getIconImage());
        RELOG hilo = new RELOG(this.txtHora);
        hilo.start();
        
        this.txtFecha.setText(RELOG.fecha(1));
        pantallaAncho = jdpEscritorio.getWidth();
        pantallaAlto = jdpEscritorio.getHeight();
        menuAdministrar.setVisible(false);
        
    }

    public static void validarCon() {
        //POOLCONEXIONES conexiones = new POOLCONEXIONES();
        try {
            System.out.println("validarCon() try");
            Connection con = dataSource.getConnection();
            System.out.println("validarCon() con");
            //System.out.println(con.isValid(5000));
            boolean resp = conexiones.validarConeccion(con);
            if (!resp) {
                conexiones.hacerpool();
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("validarCon() "+e);
            conexiones.hacerpool();
        }
    }
    
    public static void iniciar() {
        if (user != null) {
            menuAgregar.setEnabled(user.getAgregar());
            menuAlmacen.setEnabled(user.getAlmacen());
            pedidos(user.getPedidos());
            menuResumen.setEnabled(user.getResumen());
            menuCalcular.setEnabled(user.getCalcular());
            menuAdministrar.setVisible(user.getAdministrar());
            menuAdministrar.setEnabled(user.getAdministrar());
            menuProduccion.setEnabled(user.getProduccion());
            txtUsuario.setText(user.getNombre() + " " + user.getApellido());
            menuSesion.setVisible(false);
            menuBodega.setEnabled(user.getBodega());
        }
    }
    
    public static void pedidos(boolean pedidos) {
        for (int i = 1; i < menuPedidos.getItemCount(); i++) {
            menuPedidos.getItem(i).setEnabled(pedidos);
        }
    }
    public static int getX(java.awt.event.MouseEvent e) {
        return e.getX();
    }

    public static int getY(java.awt.event.MouseEvent e) {
        return e.getY();
    }

    public Image getIconImage() {
        return Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("IMAGENES/zicon.png"));
    }

    public void imprimirInventario() {
        try {
            validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            String ruta = System.getProperty("user.dir") + "/archivos/reportesPdf/INVENTARIO.jasper";
            JasperReport jr = (JasperReport) JRLoader.loadObjectFromFile(ruta);
            Map parametros = new HashMap<String, String>();
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jdpEscritorio = new javax.swing.JDesktopPane();
        pnlMavs = new OTROS.IMAGEN();
        txtHora = new javax.swing.JLabel();
        txtFecha = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtEstado = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuAlmacen = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenuItem29 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        menuBodega = new javax.swing.JMenu();
        jMenuItem28 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        menuCalcular = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        menuAgregar = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem25 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuPedidos = new javax.swing.JMenu();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem30 = new javax.swing.JMenuItem();
        menuResumen = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        menuProduccion = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();
        menuAdministrar = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuSesion = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(865, 650));

        pnlMavs.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pnlMavs.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                pnlMavsComponentResized(evt);
            }
        });
        pnlMavs.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                pnlMavsPropertyChange(evt);
            }
        });

        txtHora.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtHora.setForeground(new java.awt.Color(255, 255, 255));
        txtHora.setText("00:00 XX");

        txtFecha.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtFecha.setForeground(new java.awt.Color(255, 255, 255));
        txtFecha.setText("XX/XXXXXX/XXXX");

        txtUsuario.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(255, 255, 255));
        txtUsuario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("4.85");

        txtEstado.setForeground(new java.awt.Color(255, 255, 255));
        txtEstado.setText("servidor secundario");

        pnlMavs.setLayer(txtHora, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pnlMavs.setLayer(txtFecha, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pnlMavs.setLayer(txtUsuario, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pnlMavs.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pnlMavs.setLayer(txtEstado, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pnlMavsLayout = new javax.swing.GroupLayout(pnlMavs);
        pnlMavs.setLayout(pnlMavsLayout);
        pnlMavsLayout.setHorizontalGroup(
            pnlMavsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMavsLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlMavsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMavsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMavsLayout.createSequentialGroup()
                        .addComponent(txtFecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtHora)
                        .addGap(0, 581, Short.MAX_VALUE))
                    .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlMavsLayout.setVerticalGroup(
            pnlMavsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMavsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMavsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHora)
                    .addComponent(txtFecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 480, Short.MAX_VALUE)
                .addGroup(pnlMavsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jdpEscritorio.setLayer(pnlMavs, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jdpEscritorioLayout = new javax.swing.GroupLayout(jdpEscritorio);
        jdpEscritorio.setLayout(jdpEscritorioLayout);
        jdpEscritorioLayout.setHorizontalGroup(
            jdpEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMavs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jdpEscritorioLayout.setVerticalGroup(
            jdpEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMavs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        menuAlmacen.setText("Almacen");
        menuAlmacen.setEnabled(false);

        jMenu1.setText("Ajustes");

        jMenuItem16.setText("Entrada");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem16);

        jMenuItem17.setText("Salida");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem17);

        menuAlmacen.add(jMenu1);

        jMenuItem2.setText("Existencias");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuAlmacen.add(jMenuItem2);

        jMenu4.setText("Orden de compra");

        jMenuItem27.setText("Nueva ");
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem27);

        jMenuItem29.setText("Reimpresion");
        jMenuItem29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem29ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem29);

        menuAlmacen.add(jMenu4);
        menuAlmacen.add(jSeparator2);

        jMenuItem3.setText("Tipos de material");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        menuAlmacen.add(jMenuItem3);

        jMenuItem14.setText("Precios generales");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        menuAlmacen.add(jMenuItem14);

        jMenuBar1.add(menuAlmacen);

        menuBodega.setText("Bodega");
        menuBodega.setEnabled(false);

        jMenuItem28.setText("Existencias");
        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        menuBodega.add(jMenuItem28);

        jMenuItem4.setText("Inventario");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        menuBodega.add(jMenuItem4);

        jMenuBar1.add(menuBodega);

        menuCalcular.setText("Calcular");
        menuCalcular.setEnabled(false);

        jMenuItem6.setText("Componente");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        menuCalcular.add(jMenuItem6);

        jMenuItem8.setText("Cotización");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        menuCalcular.add(jMenuItem8);

        jMenuItem7.setText("Material por pedido");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        menuCalcular.add(jMenuItem7);

        jMenuItem20.setText("Material por período");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        menuCalcular.add(jMenuItem20);

        jMenuItem21.setText("Existencias de almacen");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        menuCalcular.add(jMenuItem21);

        jMenuBar1.add(menuCalcular);

        menuAgregar.setText("Diseño");
        menuAgregar.setEnabled(false);

        jMenuItem5.setText("Modificar");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        menuAgregar.add(jMenuItem5);

        jMenuItem25.setText("Muestras");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        menuAgregar.add(jMenuItem25);
        menuAgregar.add(jSeparator1);

        jMenuItem9.setText("Colores");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        menuAgregar.add(jMenuItem9);

        jMenuItem10.setText("Tapizados");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        menuAgregar.add(jMenuItem10);

        jMenuBar1.add(menuAgregar);

        jMenu3.setText("Imprimir");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        menuPedidos.setText("Pedidos");

        jMenuItem24.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem24.setText("Activos");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        menuPedidos.add(jMenuItem24);

        jMenuItem13.setText("Actualizar");
        jMenuItem13.setEnabled(false);
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        menuPedidos.add(jMenuItem13);

        jMenuItem11.setText("Nuevo");
        jMenuItem11.setEnabled(false);
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        menuPedidos.add(jMenuItem11);

        jMenuItem30.setText("Agregar clientes");
        jMenuItem30.setEnabled(false);
        jMenuItem30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem30ActionPerformed(evt);
            }
        });
        menuPedidos.add(jMenuItem30);

        jMenuBar1.add(menuPedidos);

        menuResumen.setText("Reportes");
        menuResumen.setEnabled(false);

        jMenu2.setText("Almacen");

        jMenuItem18.setText("Inventario");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem18);

        jMenuItem19.setText("Movimientos");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem19);

        jMenuItem22.setText("Consumo por periodo");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem22);

        menuResumen.add(jMenu2);

        jMenuItem12.setText("Pedidos");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        menuResumen.add(jMenuItem12);

        jMenuBar1.add(menuResumen);

        menuProduccion.setText("Produccion");
        menuProduccion.setEnabled(false);

        jMenu6.setText("Tableros");

        jMenuItem23.setText("Consumos");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem23);

        jMenuItem26.setText("Resumen");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem26);

        menuProduccion.add(jMenu6);

        jMenuBar1.add(menuProduccion);

        menuAdministrar.setText("Administrar");
        menuAdministrar.setEnabled(false);

        jMenuItem1.setText("Usuario");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuAdministrar.add(jMenuItem1);

        jMenuBar1.add(menuAdministrar);

        menuSesion.setText("Iniciar sesion");
        menuSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSesionMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuSesion);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdpEscritorio)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdpEscritorio)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        VerUsuario vu = new VerUsuario();
        AUXILIAR.verPantalla(jdpEscritorio, vu);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void menuSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSesionMouseClicked
        /*plantilla vu=new plantilla();
        AUXILIAR.verPantalla(jdpEscritorio, vu);*/
        /*for(int x=0;x<jTablePedidos.getColumnCount();x++){
            System.out.println(x+" "+jTablePedidos.getColumn(jTablePedidos.getColumnName(x)).getWidth());
        }*/
        CARGARUSUARIO ca = new CARGARUSUARIO();
        AUXILIAR.verPantalla(jdpEscritorio, ca);
    }//GEN-LAST:event_menuSesionMouseClicked

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        EXISTENCIAS al = new EXISTENCIAS(1);
        AUXILIAR.verPantalla(jdpEscritorio, al);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        TIPOSMATERIAL tm = new TIPOSMATERIAL();
        AUXILIAR.verPantalla(jdpEscritorio, tm);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void pnlMavsPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_pnlMavsPropertyChange

    }//GEN-LAST:event_pnlMavsPropertyChange

    private void pnlMavsComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlMavsComponentResized
        pantallaAncho = jdpEscritorio.getWidth();
        pantallaAlto = jdpEscritorio.getHeight();
    }//GEN-LAST:event_pnlMavsComponentResized

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        VERMODELOS vm = new VERMODELOS();
        AUXILIAR.verPantalla(jdpEscritorio, vm);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        MATERIALREQUERIDOCOMPONENTE mrc = new MATERIALREQUERIDOCOMPONENTE();
        AUXILIAR.verPantalla(jdpEscritorio, mrc);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        COLORES col = new COLORES();
        AUXILIAR.verPantalla(jdpEscritorio, col);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        TAPIZADO tap = new TAPIZADO();
        AUXILIAR.verPantalla(jdpEscritorio, tap);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        NUEVOPEDIDO np = new NUEVOPEDIDO();
        AUXILIAR.verPantalla(jdpEscritorio, np);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        ACTUALIZARPEDIDO ap = new ACTUALIZARPEDIDO();
        AUXILIAR.verPantalla(jdpEscritorio, ap);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        RESUMENPEDIDOS rp = new RESUMENPEDIDOS();
        AUXILIAR.verPantalla(jdpEscritorio, rp);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        COTIZACION cot = new COTIZACION();
        AUXILIAR.verPantalla(jdpEscritorio, cot);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        MOVIMIENTOS ent = new MOVIMIENTOS(1);
        AUXILIAR.verPantalla(jdpEscritorio, ent);
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        MOVIMIENTOS ent = new MOVIMIENTOS(0);
        AUXILIAR.verPantalla(jdpEscritorio, ent);
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        imprimirInventario();
        /*FILTROINVENTARIO fi=new FILTROINVENTARIO();
        AUXILIAR.verPantalla(jdpEscritorio, fi);*/
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        RESUMENMOVIMIENTOS rm = new RESUMENMOVIMIENTOS();
        AUXILIAR.verPantalla(jdpEscritorio, rm);
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        CALCULARPEDIDO cp = new CALCULARPEDIDO();
        AUXILIAR.verPantalla(jdpEscritorio, cp);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        MATERIALPERIODO mp = new MATERIALPERIODO();
        AUXILIAR.verPantalla(jdpEscritorio, mp);
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        EXISTENCIAS al = new EXISTENCIAS(0);
        AUXILIAR.verPantalla(jdpEscritorio, al);
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        CONSUMOPORPERIODO cpp = new CONSUMOPORPERIODO();
        AUXILIAR.verPantalla(jdpEscritorio, cpp);
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        ACTIVOS ac=new ACTIVOS();
        AUXILIAR.verPantalla(jdpEscritorio, ac);
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
        HOJAPRUEVA HP=new HOJAPRUEVA();
        AUXILIAR.verPantalla(jdpEscritorio, HP);
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
        NUEVACOMPRA nc=new NUEVACOMPRA();
        AUXILIAR.verPantalla(jdpEscritorio, nc);
    }//GEN-LAST:event_jMenuItem27ActionPerformed

    private void jMenuItem29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem29ActionPerformed
        REIMPRESION ri=new REIMPRESION();
        AUXILIAR.verPantalla(jdpEscritorio, ri);
    }//GEN-LAST:event_jMenuItem29ActionPerformed

    private void jMenuItem30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem30ActionPerformed
        CLIENTES cl = new CLIENTES();
        AUXILIAR.verPantalla(jdpEscritorio, cl);
    }//GEN-LAST:event_jMenuItem30ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        String ruta = System.getProperty("user.dir") + "/archivos/reportesPdf/BODEGAEXISTENCIAS.jasper";
        /*String[] options = {"Completo", "Resumen"};
        int seleccion = JOptionPane.showOptionDialog(null, "Tipo de reporte", "reporte",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if(seleccion >= 0) {
            String ruta = null;
            switch (seleccion) {
                case 0:
                    ruta = System.getProperty("user.dir") + "/archivos/reportesPdf/BODEGACOMPLETO.jasper";
                    break;
                case 1:
                    ruta = System.getProperty("user.dir") + "/archivos/reportesPdf/BODEGAEXISTENCIAS.jasper";
                    break;

            }*/
            JasperReport jr;

                try {
                    PRINCIPAL.validarCon();
                    Connection con = PRINCIPAL.dataSource.getConnection();
                    jr = (JasperReport) JRLoader.loadObjectFromFile(ruta);
                    Map parametros = new HashMap<String, String>();
                    JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con);
                    JasperViewer jv = new JasperViewer(jp, false);
                    jv.setVisible(true);
                    con.close();
                } catch (JRException ex) {
                    JOptionPane.showMessageDialog(null, "Error al crear reporte");
                    System.out.println("reporte "+ex);
                    Logger.getLogger(HOJATRABAJO.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    System.out.println("reporte "+ex);
                    Logger.getLogger(HOJATRABAJO.class.getName()).log(Level.SEVERE, null, ex);
                }
        //}
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed
       bodegaExistencias be=new  bodegaExistencias();
       AUXILIAR.verPantalla(jdpEscritorio, be);
       /*HOJATRABAJO ht=new HOJATRABAJO();
       AUXILIAR.verPantalla(jdpEscritorio, ht);*/
    }//GEN-LAST:event_jMenuItem28ActionPerformed

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        IMPRIMIRTODO it=new IMPRIMIRTODO();
        AUXILIAR.verPantalla(jdpEscritorio, it);
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        GENERALES gn=new GENERALES();
        AUXILIAR.verPantalla(jdpEscritorio, gn);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        MOVIMIENTOSPRODUC mp=new MOVIMIENTOSPRODUC(1);
       AUXILIAR.verPantalla(jdpEscritorio, mp);
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        REPORTEPRODUCCION rp=new REPORTEPRODUCCION();
        AUXILIAR.verPantalla(jdpEscritorio, rp);
    }//GEN-LAST:event_jMenuItem26ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    public static javax.swing.JDesktopPane jdpEscritorio;
    public static javax.swing.JMenu menuAdministrar;
    public static javax.swing.JMenu menuAgregar;
    public static javax.swing.JMenu menuAlmacen;
    public static javax.swing.JMenu menuBodega;
    public static javax.swing.JMenu menuCalcular;
    public static javax.swing.JMenu menuPedidos;
    public static javax.swing.JMenu menuProduccion;
    public static javax.swing.JMenu menuResumen;
    public static javax.swing.JMenu menuSesion;
    private OTROS.IMAGEN pnlMavs;
    public static javax.swing.JLabel txtEstado;
    private javax.swing.JLabel txtFecha;
    private javax.swing.JLabel txtHora;
    public static javax.swing.JLabel txtUsuario;
    // End of variables declaration//GEN-END:variables
}
