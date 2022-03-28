package GRAFICA.RESUMEN;

import GRAFICA.PRINCIPAL;
import OTROS.Render;
import java.awt.MouseInfo;
import java.awt.Point;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import sql.SQLALMACEN;

public class FILTROINVENTARIO extends javax.swing.JInternalFrame {

    private int x;
    private int y;
    ArrayList lista = new ArrayList();
    SQLALMACEN modAlma = new SQLALMACEN();
    TableColumnModel columnModel = null;
    public DefaultTableModel dftm = new DefaultTableModel() {

        Class[] types = new Class[]{
            java.lang.Boolean.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
        };

        public Class getColumnClass(int columnIndex) {
            return types[columnIndex];
        }

        public boolean isCellEditable(int fila, int columna) {
            if (columna == 0 || columna == 2) {
                return true;
            }
            return false;
        }
    };

    public FILTROINVENTARIO() {
        initComponents();
        imgBarraTitulo.setBackgroung("IMAGENES/TEXTURAS/marmol.png");
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        imgCerrar.setBackgroung("IMAGENES/ICONOS/si.png");
        imgAjuste.setBackgroung("IMAGENES/ICONOS/no.png");

        crearTabla();
        crearLista();
        llenarTabla();
    }

    public void crearLista() {
        lista = modAlma.ObtenerTiposMaterial();
        for (int i = 0; i < lista.size(); i++) {
            ArrayList lis = (ArrayList) lista.get(i);
            lis.set(1, true);
        }
    }

    public void crearTabla() {
        jTable1.setDefaultRenderer(Object.class, new Render());
        jTable1.setModel(dftm);
        dftm.setColumnIdentifiers(new Object[]{"fila1", "fila2", "fila3", "fila4"});
        jTable1.setTableHeader(null);
        columnModel = jTable1.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(23);
        columnModel.getColumn(1).setPreferredWidth(246);
        columnModel.getColumn(2).setPreferredWidth(23);
        columnModel.getColumn(3).setPreferredWidth(246);
    }
    public void recojerLista(){
        int fila=-1;
        
        for(int a=0;a<lista.size();a++){
            ArrayList lis = (ArrayList) lista.get(a);
            if(a%2==0){
                fila++;
                lis.set(1,dftm.getValueAt(fila, 0));
            }else{
                lis.set(1,dftm.getValueAt(fila, 2));
            }
            //System.out.println(lis.get(0).toString()+" "+lis.get(1).toString());
        }
    }

    public void llenarTabla() {
        borrarTabla();
        for (int x = 0; x < lista.size(); x++) {
            Object[] agregar = new Object[4];
            ArrayList lis1 = (ArrayList) lista.get(x);
            agregar[0] = lis1.get(1);
            agregar[1] = lis1.get(0);
            if (x + 1 < lista.size()) {
                x++;
                ArrayList lis2 = (ArrayList) lista.get(x);
                agregar[2] = lis2.get(1);
                agregar[3] = lis2.get(0);
            }
            dftm.addRow(agregar);
        }
    }

    public void borrarTabla() {
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            dftm.removeRow(i);
            i--;
        }
    }
    
    public void imprimirInventario(String sql){
        try {
            Connection con = PRINCIPAL.dataSource.getConnection();
            String ruta=System.getProperty("user.dir")+"/archivos/reportesPdf/INVENTARIO.jasper";
            JasperReport jr=(JasperReport)JRLoader.loadObjectFromFile(ruta);
            Map parametros=new HashMap<String,String>();
            parametros.put("sql", sql);
            JasperPrint jp=JasperFillManager.fillReport(jr,parametros,con);
            JasperViewer jv=new JasperViewer(jp,false);
            jv.setVisible(true);
            con.close();
            
        } catch (Exception e) {
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

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
        jLabel2.setText("REPORTE DE EXISTENCIAS");

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

        jTable1.setBackground(new java.awt.Color(240, 240, 240));
        jTable1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setGridColor(new java.awt.Color(240, 240, 240));
        jTable1.setSelectionBackground(new java.awt.Color(240, 240, 240));
        jTable1.setSelectionForeground(new java.awt.Color(0, 0, 0));
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imgBarraTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
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
        String sql = "SELECT "
                + "almacen.`id` AS almacen_id, "
                + "almacen.`nombre` AS almacen_nombre, "
                + "almacen.`tipo` AS almacen_tipo, "
                + "almacen.`precio` AS almacen_precio, "
                + "almacen.`actualizacion` AS almacen_actualizacion, "
                + "almacen.`existencias` AS almacen_existencias "
                + "FROM `almacen` almacen WHERE";
        for (int a = 0; a < lista.size(); a++) {
            ArrayList lis = (ArrayList) lista.get(a);
            if (lis.get(1).toString().equals("true")) {
                if (a == 0) {
                    sql = sql + " tipo='" + lis.get(0) + "'";
                }else{
                    sql = sql + " OR tipo='" + lis.get(0) + "'";
                }
            }
        }
        sql = sql + " ORDER BY almacen.`tipo` ASC";
        System.out.println("sql "+sql);
        imprimirInventario(sql);
    }//GEN-LAST:event_imgCerrarMouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        recojerLista();
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
