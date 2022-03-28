package OTROS;

public class PANTALLACARGA extends javax.swing.JInternalFrame {

    public static boolean continuar = true;

    public PANTALLACARGA() {
        continuar = true;
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        iMAGEN1.setBackgroung("IMAGENES/loading.gif");
        Hilo h = new Hilo();
        h.start();
        this.moveToFront();
        this.toFront();
        this.setLayer(0);
    }

    private void cerrar() {
        continuar = false;
        this.dispose();
    }

    public static void cambiar(boolean con) {
        continuar = con;
    }

    private class Hilo extends Thread {

        int tiempo = 1000;

        public void run() {
            try {
                while (continuar) {
                    //Thread.sleep(tiempo);
                    sleep(tiempo);
                }
                cerrar();
            } catch (Exception ex) {
                System.out.println("cargar" + ex);
                cerrar();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        iMAGEN1 = new OTROS.IMAGEN();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(null);

        iMAGEN1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout iMAGEN1Layout = new javax.swing.GroupLayout(iMAGEN1);
        iMAGEN1.setLayout(iMAGEN1Layout);
        iMAGEN1Layout.setHorizontalGroup(
            iMAGEN1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 426, Short.MAX_VALUE)
        );
        iMAGEN1Layout.setVerticalGroup(
            iMAGEN1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 224, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iMAGEN1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iMAGEN1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private OTROS.IMAGEN iMAGEN1;
    // End of variables declaration//GEN-END:variables
}
