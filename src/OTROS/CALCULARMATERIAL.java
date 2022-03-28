package OTROS;

import CLASES.COMPONENTE;
import CLASES.ESTILOS;
import CLASES.material;
import GRAFICA.CALCULAR.LISTAERROR;
import static GRAFICA.PRINCIPAL.jdpEscritorio;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import sql.SQLALMACEN;
import sql.SQLMUEBLES;

public class CALCULARMATERIAL {

    ArrayList<material> listaMat = new ArrayList();
    ArrayList<material> listaPrecios = new ArrayList();
    ArrayList<material> errores = new ArrayList();
    SQLMUEBLES modMuebles = new SQLMUEBLES();
    SQLALMACEN modAlmacen = new SQLALMACEN();
    float desperdicio = (float) 1.25;
    public static ESTILOS estilo = null;
    boolean advertencia;
    static DecimalFormat for2 = new DecimalFormat("#,###.##");

    public ArrayList<material> calcularMaterial(ArrayList<COMPONENTE> lista, boolean advertencia) {
        this.advertencia = advertencia;
        this.estilo = estilo;
        System.out.println("calcularMaterial pasa 1");
        listaPrecios = modAlmacen.obtenerTodoMateriales();
        System.out.println("calcularMaterial pasa 2");
        crearLista(lista);
        
        ordenarTipos();
        return listaMat;
    }

    public void limpiar() {
        this.estilo = null;
        listaPrecios = null;
        listaMat = null;
    }

    public void ordenarTipos() {
        Collections.sort(listaMat, new Comparator<material>() {
            @Override
            public int compare(material obj1, material obj2) {
                return obj1.getTipo().compareTo(obj2.getTipo());
            }
        });
    }

    public void prepararLista(ArrayList<material> lis) {
        for (material mat : lis) {
            if (!AUXILIAR.isInt(mat.getIdMaterial())) {
                if (mat.getIdMaterial().equals("FONDO")) {
                    mat.setIdMaterial(String.valueOf(estilo.getIntFondo()));
                    //mat.setTipo("PINTURA");
                }
                if (mat.getIdMaterial().equals("LACA")) {
                    mat.setIdMaterial(String.valueOf(estilo.getIntLaca()));
                    //mat.setTipo("PINTURA");
                }
                if (mat.getIdMaterial().equals("MANCHA")) {
                    mat.setIdMaterial(String.valueOf(estilo.getIntMancha()));
                    //mat.setTipo("PINTURA");
                }
                if (mat.getIdMaterial().equals("TONO")) {
                    mat.setIdMaterial(String.valueOf(estilo.getIntTono()));
                    //mat.setTipo("PINTURA");
                }
                if (mat.getIdMaterial().equals("HILO")) {
                    mat.setIdMaterial(String.valueOf(estilo.getIntHilo()));
                    //mat.setTipo("PINTURA");
                }
                if (mat.getIdMaterial().equals("FORRO")) {
                    mat.setIdMaterial(String.valueOf(estilo.getIntForro()));
                    //mat.setTipo("PINTURA");
                }
                if (mat.getIdMaterial().equals("TELA")) {
                    mat.setIdMaterial(String.valueOf(estilo.getIntTela()));
                    //mat.setTipo("PINTURA");
                }
                if (mat.getIdMaterial().equals("VINIL")) {
                    mat.setIdMaterial(String.valueOf(estilo.getIntVinil()));
                    //mat.setTipo("PINTURA");
                }
                if (mat.getIdMaterial().equals("CHAPACINTA")) {
                    mat.setIdMaterial(String.valueOf(estilo.getIntChapa()));
                    //mat.setTipo("PINTURA");
                }
                if (mat.getIdMaterial().equals("VIDRIO")) {
                    compararListaMedidas(mat);
                }
                if (mat.getIdMaterial().equals("LUNA")) {
                    compararListaMedidas(mat);
                }
            }
        }
    }

    public void compararListaMedidas(material mat) {
        for (material lis : listaPrecios) {
            if (lis.getMaterial().contains(mat.getIdMaterial())) {
                float x=Float.valueOf(for2.format(lis.getMedidaX()*100));
                float y=Float.valueOf(for2.format(lis.getMedidaY()*100));
                System.out.println("+++++++++++");
                System.out.println(mat.getMaterial()+"X "+x+"/"+x);
                System.out.println(mat.getMaterial()+"Y "+y+"/"+y);
                if (mat.getMedidaX() == x
                        && mat.getMedidaY() ==y) {
                    mat.setIdMaterial(lis.getIdMaterial());
                    /*mat.setMedidaX(mat.getMedidaX()/100);
                    mat.setMedidaY(mat.getMedidaY()/100);
                    System.out.println(cmAm2(mat.getMedidaX(),mat.getMedidaY()));*/
                }
            }
        }
    }

    public void crearLista(ArrayList<COMPONENTE> lista) {
        for (COMPONENTE com : lista) {
            com.setPreUni(0);
            float precioUni = 0;
            estilo = com.getEstilo();
            ArrayList<material> lis = modMuebles.obtenerMaterialComponente(com.getId());

            prepararLista(lis);
            com.setMateriales(lis);
            sinId(lis);
            for (material mat : lis) {
                boolean pasa = false;
                for (material matLista : listaMat) {
                    if (matLista.getIdMaterial().equals(mat.getIdMaterial())) {
                        float can = calCantidad(mat);

                        precioUni = precioUni + (can * matLista.getPrecioUnidad());
                        matLista.setCantidad(matLista.getCantidad() + can);

                        pasa = true;
                        break;
                    }
                }
                if (!pasa) {
                    for (material matAlma : listaPrecios) {
                        if (matAlma.getIdMaterial().equals(mat.getIdMaterial())) {
                            material tem = new material();
                            tem.setIdMaterial(matAlma.getIdMaterial());
                            tem.setMaterial(matAlma.getMaterial());
                            tem.setTipo(matAlma.getTipo());
                            tem.setPrecioPaquete(matAlma.getPrecioPaquete());
                            tem.setEmpaque(matAlma.getEmpaque());
                            tem.setUnidades(matAlma.getUnidades());
                            /*obtener paquetes*/
                            float paquete = matAlma.getMedidaX() * matAlma.getMedidaY();
                            if (paquete <= 0) {
                                paquete = matAlma.getPaquete();
                            }
                            tem.setPaquete(paquete);
                            /*-------------*/
 /*obtener precios unitario*/
                            float pu = tem.getPrecioPaquete() / paquete;
                            tem.setPrecioUnidad(pu);
                            /*-------------*/
                            tem.setMedidaX(matAlma.getMedidaX());
                            tem.setMedidaY(matAlma.getMedidaY());
                            float can = calCantidad(mat);

                            tem.setCantidad(can);

                            precioUni = com.getPreUni() + precioUni;
                            com.setPreUni(can * tem.getPrecioUnidad());
                            /*calcular el preci por unidad*/
                            listaMat.add(tem);
                        }
                    }
                }
            }
            com.setPreUni(precioUni);
            precioUni = 0;
            sinPrecio(listaMat);
        }

        /*acomodar en orden alfabetico*/
        Collections.sort(listaMat, new Comparator<material>() {
            public int compare(material obj1, material obj2) {
                return obj1.getMaterial().compareTo(obj2.getMaterial());
            }
        });
        if (errores.size() > 0 && advertencia) {
            //System.out.println(errores.size());
            LISTAERROR le = new LISTAERROR(errores);
            AUXILIAR.verPantalla(jdpEscritorio, le);
        }
    }

    public void sinId(ArrayList<material> lista) {
        for (material com : lista) {
            if (!AUXILIAR.isInt(com.getIdMaterial()) || com.getIdMaterial().equals("0")) {
                material mat = new material();
                mat = com;
                mat.setMaterial(com.getPieza());
                if (mat.getMaterial() == null || mat.getMaterial().equals(" ") || mat.getMaterial().length() < 1) {
                    mat.setMaterial(com.getIdMaterial());
                }
                mat.setTipo("Material no encontrado");
                mat.setPrecioPaquete(0);
                errores.add(mat);
            }
        }
    }

    public void sinPrecio(ArrayList<material> lista) {
        for (material com : lista) {
            if (com.getPrecioPaquete() <= 0) {
                material mat = new material();
                mat = com;
                mat.setPieza(com.getMaterial());
                mat.setTipo("Precio invalido");
                mat.setPrecioPaquete(0);
                mat.setPrecioUnidad(0);
                errores.add(mat);
            }
        }
    }

    public void calcularCosUni(ArrayList<COMPONENTE> lista) {
        for (COMPONENTE com : lista) {
            ArrayList<material> lismat = com.getMateriales();
            for (material mat : lismat) {
                float subtotal = mat.getCantidad() * mat.getPrecioUnidad();
                com.setPreUni(subtotal);
            }
        }
    }

    public Float cmAm2(float a, float b) {
        return (a * b) / 10000;
    }

    public float calCantidad(material mat) {
        float can = 0;

        if (mat.getTipo().equals("MADERA")) {
            float ancho = mat.getMedidaZ();
            /*if (mat.getIdMaterial().equals("42") || mat.getIdMaterial().equals("43")) {
                ancho = (float) 0.875;
            }
            if (mat.getIdMaterial().equals("41")) {
                ancho = (float) 1.25;
            }*/
            can = (float) (((mat.getMedidaX() / 30.48 * mat.getMedidaY() / 2.45 * ancho) / 12) * mat.getCantidad()) * desperdicio;

        } else {
            can = cmAm2(mat.getMedidaX(), mat.getMedidaY());
            if (can <= 0) {
                can = mat.getCantidad();
                can = can * desperdicio;
                /*if(mat.getTipo().equals("MDF")){
                    can=(float) (can/2.98);
                    System.out.println(mat.getPieza()+" "+can);
                }*/
            } else {
                if((mat.getTipo().equals("VIDRIO Y CRISTAL"))){
                    can = can * mat.getCantidad();
                }else{
                    can = can * desperdicio * mat.getCantidad();
                }
                
            }
        }
        return can;
    }
}
