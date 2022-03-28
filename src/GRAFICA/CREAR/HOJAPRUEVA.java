package GRAFICA.CREAR;

import CLASES.COMPONENTE;
import CLASES.material;
import GRAFICA.IMPRIMIR.HOJATRABAJO;
import GRAFICA.PRINCIPAL;
import OTROS.CALCULARMATERIALCOMPLETO;
import REPORTESPDF.PARAMETROSIMPRESION;
import GRAFICA.MUEBLES.*;
import static GRAFICA.PRINCIPAL.jdpEscritorio;
import OTROS.AUXILIAR;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import sql.SQLMUEBLES;

public class HOJAPRUEVA extends javax.swing.JInternalFrame {

    private int x;
    private int y;
    ArrayList<COMPONENTE> lista = new ArrayList();
    static SimpleDateFormat fSalida = new SimpleDateFormat("dd/MM/yyyy");
    TableColumnModel columnModel = null;
    public static DefaultTableModel dftm = new DefaultTableModel() {
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };

    public HOJAPRUEVA() {
        initComponents();
        imgBarraTitulo.setBackgroung("IMAGENES/TEXTURAS/marmol.png");
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        imgCerrar.setBackgroung("IMAGENES/ICONOS/cerrar2.png");
        imgAjuste.trasparente();
        SQLMUEBLES modMuebles = new SQLMUEBLES();
        lista = modMuebles.obtenerPruevas();
        prepararTabla();
        if (lista.size() > 0) {
            llenarTabla();
        }
    }

    public void llenarTabla() {
        borrarTabla();

        for (COMPONENTE com : lista) {
            String fecha;
            try {
                fecha = fSalida.format(com.getFecha());
            } catch (Exception e) {
                fecha = "";
            }
            dftm.addRow(new Object[]{com.getNombre(), com.getModelo(), com.getObservaciones(),
                com.getX(), com.getY(), com.getZ(), fecha});
        }
    }

    public void prepararTabla() {
        jTable1.setModel(dftm);
        columnModel = jTable1.getColumnModel();
        dftm.setColumnIdentifiers(new Object[]{"Nombre", "Modelo", "Observaciones", "X", "Y", "Z", "Actualizacion"});
        columnModel.getColumn(0).setPreferredWidth(156);
        columnModel.getColumn(1).setPreferredWidth(183);
        columnModel.getColumn(2).setPreferredWidth(204);
        columnModel.getColumn(3).setPreferredWidth(36);
        columnModel.getColumn(4).setPreferredWidth(36);
        columnModel.getColumn(5).setPreferredWidth(36);
        columnModel.getColumn(6).setPreferredWidth(79);
    }

    public void borrarTabla() {
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            dftm.removeRow(i);
            i--;
        }
    }

    public void HojaDeTrabajo(int idComp) {
        String ruta = System.getProperty("user.dir") + "/archivos/reportesPdf/HOJADETRABAJO_2.jasper";
        JasperReport jr;
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            jr = (JasperReport) JRLoader.loadObjectFromFile(ruta);
            Map parametros = new HashMap<String, String>();
            parametros.put("idComp", idComp);
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

    public void ReporteCompleto(int idComp, String modelo, String componente) {
        CALCULARMATERIALCOMPLETO cmc = new CALCULARMATERIALCOMPLETO();
        ArrayList<material> lista = cmc.calcularMaterial(idComp, 1);
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
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imgBarraTitulo = new OTROS.IMAGEN();
        jLabel2 = new javax.swing.JLabel();
        imgAjuste = new OTROS.IMAGEN();
        imgCerrar = new OTROS.IMAGEN();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(767, 418));

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
        jLabel2.setText("ELEMENTOS DE MUESTRA");

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
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
                .addGap(18, 18, 18)
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgBarraTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imgBarraTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void imgAjusteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgAjusteMouseClicked
        for (int x = 0; x < jTable1.getColumnCount(); x++) {
            System.out.println(x + " " + jTable1.getColumn(jTable1.getColumnName(x)).getWidth());
        }
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

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {
            int fila = jTable1.getSelectedRow();
            String modelo = dftm.getValueAt(fila, 1).toString();
            String componente = dftm.getValueAt(fila, 0).toString();
            String[] options = {"Hoja de trabajo", "Reporte completo","Modificar"};
            int seleccion = JOptionPane.showOptionDialog(null, "", "",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
            switch (seleccion) {
                case 0://hoja de trabajo
                    HojaDeTrabajo(Integer.valueOf(lista.get(fila).getId()));
                    break;
                case 1://Reporte completo
                    ReporteCompleto(Integer.valueOf(lista.get(fila).getId()), modelo, componente);
                    break;
                case 2://modificar
                    MODIFICARCOMPONENTE mc=new MODIFICARCOMPONENTE(Integer.valueOf(lista.get(fila).getId()));
                    AUXILIAR.verPantalla(jdpEscritorio, mc);
                    break;
            }
        }
        //imprimirPdf(Integer.valueOf(lista.get(fila).getId()));
    }//GEN-LAST:event_jTable1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private OTROS.IMAGEN imgAjuste;
    private OTROS.IMAGEN imgBarraTitulo;
    private OTROS.IMAGEN imgCerrar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
