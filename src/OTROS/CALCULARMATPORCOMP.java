package OTROS;

import CLASES.COMPONENTE;
import CLASES.material;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import sql.SQLALMACEN;
import sql.SQLMUEBLES;

public class CALCULARMATPORCOMP {

    ArrayList pedido = new ArrayList();
    ArrayList matFinal = new ArrayList();
    List<List<String>> lisFin = null;
    SQLALMACEN modAlmacen = new SQLALMACEN();
    SQLMUEBLES modMuebles = new SQLMUEBLES();
    int tipo = 0;
    DecimalFormat for4 = new DecimalFormat("####.####");
    DecimalFormat for2 = new DecimalFormat("####.##");
    float desperdicio=(float) 1.25;
    public List<List<String>> calcular(ArrayList<COMPONENTE> pedido, int tipo) {
        System.out.println("*******iniciando calcular "+tipo+"*******");
        ArrayList<material> almacen = modAlmacen.obtenerMaterialesFiltros();
        System.out.println("almacen "+almacen.size());
        this.tipo = tipo;
        lisFin = new ArrayList<List<String>>();

        ArrayList lisTem = new ArrayList();
        
        lisTem.add("Componente");
        lisFin.add(lisTem);
        lisTem = new ArrayList();
        
        lisTem.add("Cant");
        lisFin.add(lisTem);
        
        System.out.println("lisTem "+lisTem.size());
        
        for (material mat : almacen) {
            ArrayList lisTem2 = new ArrayList();
            lisTem2.add(mat.getMaterial());
            lisFin.add(lisTem2);
        }
        /*
        System.out.println("almacen "+almacen.size());
        System.out.println("lisFin alma "+lisFin.size());
        System.out.println("Pedido "+pedido.size());*/

        for (COMPONENTE ped : pedido) {
            ArrayList<COMPONENTE> lisComTem = new ArrayList<>();
            lisComTem.add(ped);
            System.out.println("-------ped "+ped.getNombre());
            CALCULARMATERIAL cm = new CALCULARMATERIAL();
            System.out.println(" pedido pasa 1");
            ArrayList<material> listaMat = cm.calcularMaterial(lisComTem, false);
            System.out.println(" pedido pasa 2");
            //System.out.println("listaMat "+listaMat.size());
            
           
            lisFin.get(0).add(ped.getModelo() + " " + ped.getNombre());
            lisFin.get(1).add(String.valueOf(ped.getCantidad()));
            int a;
            for (a = 0; a < almacen.size(); a++) {
                material alma = almacen.get(a);
                float agregar = 0;

                for (int b = 0; b < listaMat.size(); b++) {
                    
                    if (alma.getIdMaterial().equals(listaMat.get(b).getIdMaterial())) {
                        if (tipo == 1) {
                            agregar = listaMat.get(b).getCantidad() / listaMat.get(b).getPaquete();
                            
                        } else {
                            agregar = listaMat.get(b).getCantidad();
                        }
                        break;
                    }
                }
                //lisFin.get(a + 2).add(String.valueOf(agregar));
                lisFin.get(a + 2).add(for2.format(agregar*ped.getCantidad()));
            }
        }
        System.out.println("*******terminando calcular*******");
        //chcecar();
        limpiar();
        return lisFin;
    }

    public void chcecar() {
        for (int a = 0; a < lisFin.size(); a++) {
            List<String> lis1 = lisFin.get(a);
            for (int b = 1; b < lis1.size(); b++) {
                System.out.print("/" + lis1.get(b) + "/");
            }
            System.out.print("\n");
        }
    }
    
    public void limpiar() {
        for (int a = 0; a < lisFin.size(); a++) {
            float suma = 0;
            for (int b = 0; b < lisFin.get(a).size(); b++) {
                if (AUXILIAR.isFloat(lisFin.get(a).get(b))) {
                    suma = suma + Float.valueOf(lisFin.get(a).get(b));
                }
            }
            if (suma == 0 && a > 1) {
                lisFin.remove(a);
                a--;
            }
        }
    }
    

    public void limpiar2() {
        for (int a = 0; a < lisFin.get(0).size(); a++) {
            float suma = 0;
            int b = 0;
            for (b = 0; b < lisFin.size(); b++) {
                if (AUXILIAR.isFloat(lisFin.get(b).get(a))) {
                    suma = suma + Float.valueOf(lisFin.get(b).get(a));
                }
            }
            if (suma == 0 && a > 1) {
                for (b = 0; b < lisFin.size(); b++) {
                    lisFin.get(b).remove(a);
                }
                a--;
            }
            b = 0;
        }
    }
}
