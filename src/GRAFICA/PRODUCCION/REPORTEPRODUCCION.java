package GRAFICA.PRODUCCION;

import CLASES.material;
import java.awt.MouseInfo;
import java.awt.Point;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import sql.SQLPRODUCCION;

public class REPORTEPRODUCCION extends javax.swing.JInternalFrame {
    private int x;
    private int y;
    ArrayList<material> lista=new ArrayList();
    SQLPRODUCCION modProduccion=new SQLPRODUCCION();
    TableColumnModel columnModel = null;
    int dias=0;
    int mesAdias=26;
    int semanaAdias=6;
    DecimalFormat for4 = new DecimalFormat("#,###.##");
    public static DefaultTableModel dftm = new DefaultTableModel() {
        public boolean isCellEditable(int fila, int columna) {
            if (columna == 0) {
                return true;
            }
            return false;
        }
    };
    
    public REPORTEPRODUCCION() {
        initComponents();
        imgBarraTitulo.setBackgroung("IMAGENES/TEXTURAS/marmol.png");
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        imgCerrar.setBackgroung("IMAGENES/ICONOS/cerrar2.png");
        imgAjuste.trasparente();
        txtDias.setVisible(false);
        Date hoy = new Date();
        dcInicio.setMaxSelectableDate(hoy);
        dcFin.setMaxSelectableDate(hoy);
    }
    
    public void borrarTabla() {
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            dftm.removeRow(i);
            i--;
        }
    }
    
    public void crearTabla() {
        jTable1.setModel(dftm);
        columnModel = jTable1.getColumnModel();
        if (dias >= 26) {
            dftm.setColumnIdentifiers(new Object[]{"Material", "Total", "por Dia", "por Semana", "por Mes"});
            columnModel.getColumn(0).setPreferredWidth(499);
            columnModel.getColumn(1).setPreferredWidth(81);
            columnModel.getColumn(2).setPreferredWidth(81);
            columnModel.getColumn(3).setPreferredWidth(81);
            columnModel.getColumn(4).setPreferredWidth(81);
        } else {
            if (dias >= 7) {
                dftm.setColumnIdentifiers(new Object[]{"Material", "Total", "por Dia", "por Semana"});
                columnModel.getColumn(0).setPreferredWidth(551);
                columnModel.getColumn(1).setPreferredWidth(87);
                columnModel.getColumn(2).setPreferredWidth(87);
                columnModel.getColumn(3).setPreferredWidth(87);
            } else {
                dftm.setColumnIdentifiers(new Object[]{"Material", "Total", "por Dia"});
                columnModel.getColumn(0).setPreferredWidth(638);
                columnModel.getColumn(1).setPreferredWidth(87);
                columnModel.getColumn(1).setPreferredWidth(87);
            }
        }
    }

    public void llenarTabla() {
        borrarTabla();
        crearTabla();
        float semanas=dias/semanaAdias;
        float meses=dias/mesAdias;
        txtDias.setText(dias+" Dias tomados en cuenta");
        txtDias.setVisible(true);
        for (material mat : lista) {
            if (dias >= 26) {
                System.out.println("mes");
                //dftm.setColumnIdentifiers(new Object[]{"Material", "por Dia", "por Semana", "por Mes"});
                dftm.addRow(new Object[]{mat.getMaterial(), for4.format(mat.getCantidad()), for4.format(mat.getCantidad() / dias),
                    for4.format(mat.getCantidad() / semanas), for4.format(mat.getCantidad() / meses)});
            } else {
                if (dias >= 7) {
                    //dftm.setColumnIdentifiers(new Object[]{"Material", "por Dia", "por Semana"});
                    dftm.addRow(new Object[]{mat.getMaterial(), for4.format(mat.getCantidad()), for4.format(mat.getCantidad() / dias), for4.format(mat.getCantidad() / semanas)});
                } else {
                    //dftm.setColumnIdentifiers(new Object[]{"Material", "por Dia"});
                    dftm.addRow(new Object[]{mat.getMaterial(), for4.format(mat.getCantidad()), for4.format(mat.getCantidad() / dias)});
                }
            }
        }
    }

    public ArrayList<material> promediar(ArrayList<material> lista) {
        ArrayList<material> listaTem = new ArrayList<>();
        this.dias = numeroDiasEntreDosFechas(dcInicio.getDate(), dcFin.getDate());
        
        for(material mat:lista){
            boolean pasa=false;
            for(material matTem:listaTem){
                if(matTem.getMaterial().equals(mat.getMaterial())){
                    pasa=true;
                    matTem.setCantidad(matTem.getCantidad()+mat.getCantidad());
                    break;
                }
            }
            if(!pasa){
                material tem=new material();
                tem.setMaterial(mat.getMaterial());
                tem.setCantidad(mat.getCantidad());
                listaTem.add(tem);
            }
        }
        /*for(material mat:listaTem){
            System.out.println(mat.getMaterial()+" "+mat.getCantidad());
        }*/
        
        return listaTem;
    }

    public static int numeroDiasEntreDosFechas(Date fecha1, Date fecha2) {
        long startTime = fecha1.getTime();
        long endTime = fecha2.getTime();
        long diffTime = endTime - startTime;
        int dias = (int) TimeUnit.DAYS.convert(diffTime, TimeUnit.MILLISECONDS)+1 ;
        int dia7 = (int) Math.floor(dias / 7);
        if (dia7 > 0) {
            dias = dias - dia7;
        }
        //System.out.println("dias"+dias);
        return dias;
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imgBarraTitulo = new OTROS.IMAGEN();
        jLabel2 = new javax.swing.JLabel();
        imgAjuste = new OTROS.IMAGEN();
        imgCerrar = new OTROS.IMAGEN();
        dcInicio = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        dcFin = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        txtDias = new javax.swing.JLabel();

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
        jLabel2.setText("REPORTE DE CONSUMO");

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

        jLabel1.setText("A");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Calcular");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtDias.setText("Dias a tomar en cuenta");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgBarraTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dcInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dcFin, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(36, 36, 36)
                        .addComponent(txtDias, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                        .addContainerGap(171, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imgBarraTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dcInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dcFin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(txtDias)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void imgAjusteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgAjusteMouseClicked
        for(int x=0;x<jTable1.getColumnCount();x++){
            System.out.println(x+" "+jTable1.getColumn(jTable1.getColumnName(x)).getWidth());
        }
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
        this.dispose();
    }//GEN-LAST:event_imgCerrarMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(dcInicio.getDate()!=null&&dcFin.getDate()!=null){
           ArrayList<material> lista= modProduccion.obtenerMovimientoProduccion(dcInicio.getDate(),dcFin.getDate());
           
           this.lista=promediar(lista);
           llenarTabla();
        }else{
            JOptionPane.showMessageDialog(null, "Es necesario un rango de fechas");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dcFin;
    private com.toedter.calendar.JDateChooser dcInicio;
    private OTROS.IMAGEN imgAjuste;
    private OTROS.IMAGEN imgBarraTitulo;
    private OTROS.IMAGEN imgCerrar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel txtDias;
    // End of variables declaration//GEN-END:variables
}
