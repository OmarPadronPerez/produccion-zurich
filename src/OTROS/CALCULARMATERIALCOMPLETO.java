package OTROS;

import CLASES.AREAS;
import CLASES.ESTILOS;
import CLASES.material;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import sql.SQLALMACEN;
import sql.SQLMUEBLES;

public class CALCULARMATERIALCOMPLETO {

    ArrayList<material> listaMat = new ArrayList();
    ArrayList<material> listaPrecios = new ArrayList();
    ArrayList<AREAS> listaAreas = new ArrayList();
    ArrayList<material> lisFinal = new ArrayList();
    SQLMUEBLES modMuebles = new SQLMUEBLES();
    SQLALMACEN modAlmacen = new SQLALMACEN();
    float desperdicio = (float) 1.25;
    int cantidad = 0;
    DecimalFormat for2 = new DecimalFormat("#,###.##");
    public static ESTILOS estilo = null;

    public ArrayList<material> calcularMaterial(int idComponente, int cantidad) {
        ArrayList<material> lis = modMuebles.obtenerMaterialComponente(idComponente);
        listaPrecios = modAlmacen.obtenerTodoMateriales();
        listaAreas = modAlmacen.obtenerElementosArea();
        this.cantidad = cantidad;
        calcularMDF(lis);
        calcularMadera(lis);
        calcularOtros(lis);
        calcularAreas(lis);
        ordenarTipos();
        return lisFinal;
    }

    public Float cmAm2(float a, float b) {
        return (a * b) / 10000;
    }

    public Float cmAm3(float a, float b, float c) {
        return (a * b * c) / 1000000;
    }

    public void calcularMDF(ArrayList<material> lis) {

        for (material mat : lis) {//mdf
            if (mat.getTipo().equals("MDF") || mat.getTipo().equals("AGLOMERADO")) {
                /*if(mat.getTipo().equals("AGLOMERADO")){
                    mat.setTipo("MDF");
                }*/
                float m2 = cmAm2(mat.getMedidaX(), mat.getMedidaY());
                m2 = (float) (m2 * desperdicio * mat.getCantidad() * cantidad);

                mat.setPaquete(m2);
                if (mat.getDescripcion() == null) {

                    mat.setDescripcion(".");
                }
                if (mat.getPieza() == null) {
                    mat.setPieza(".");
                }

                for (material precio : listaPrecios) {
                    if (mat.getIdMaterial().equals(precio.getIdMaterial())) {
                        float m2mat = precio.getMedidaX() * precio.getMedidaY();
                        mat.setPrecioUnidad(precio.getPrecioPaquete() / m2mat);
                        mat.setMaterial(precio.getMaterial());
                        lisFinal.add(mat);
                        break;
                    }
                }
            }
        }
    }

    public void calcularMadera(ArrayList<material> lis) {
        for (material mat : lis) {
            if (mat.getTipo().equals("MADERA")) {

                float m2 = 0;
                float ancho = mat.getMedidaZ();
                /*if (mat.getIdMaterial().equals("42") || mat.getIdMaterial().equals("43")) {
                    ancho = (float) 0.875;
                }
                if (mat.getIdMaterial().equals("41")) {
                    ancho = (float) 1.25;
                }*/
                m2 = (float) (((mat.getMedidaX() / 30.48 * mat.getMedidaY() / 2.45 * ancho) / 12) * mat.getCantidad()) * cantidad * desperdicio;
                mat.setPaquete(m2);
                if (mat.getDescripcion() == null) {
                    mat.setDescripcion(".");
                }
                if (mat.getPieza() == null) {
                    mat.setPieza(".");
                }
                for (material precio : listaPrecios) {
                    if (mat.getIdMaterial().equals(precio.getIdMaterial())) {
                        float m2mat = precio.getMedidaX() * precio.getMedidaY();
                        mat.setPrecioUnidad(precio.getPrecioPaquete() / m2mat);
                        mat.setMaterial(precio.getMaterial());
                        lisFinal.add(mat);
                        break;
                    }
                }
            }
        }
    }

    public void calcularOtros(ArrayList<material> lis) {
        for (material mat : lis) {
            if (!mat.getTipo().equals("MADERA")
                    && !mat.getTipo().equals("MDF")
                    && !mat.getTipo().equals("AGLOMERADO")
                    && !mat.getTipo().equals("PINTURA")
                    && AUXILIAR.isInt(mat.getIdMaterial())) {
                mat.setPaquete(mat.getCantidad() * desperdicio * cantidad);
                /*if(mat.getMedidaX()<=0||mat.getMedidaY()<=0){
                    
                }*/
                for (material precio : listaPrecios) {
                    if (mat.getIdMaterial().equals(precio.getIdMaterial())) {
                        float m2 = 0;
                        if (mat.getPieza() != null && mat.getPieza().length() > 1) {
                            if (mat.getDescripcion() == null) {
                                mat.setDescripcion(".");
                            }
                        }
                        if (mat.getMedidaX() == 0 || mat.getMedidaY() == 0) {
                            m2 = mat.getCantidad() * desperdicio;
                            mat.setCantidad(m2);
                        } else {
                            if (mat.getMedidaZ() != 0) {
                                m2 = cmAm3(mat.getMedidaX(), mat.getMedidaY(), mat.getMedidaZ()) * mat.getCantidad() * desperdicio;
                            } else {
                                m2 = cmAm2(mat.getMedidaX(), mat.getMedidaY()) * mat.getCantidad() * desperdicio;
                            }

                        }
                        //
                        mat.setPaquete(m2);
                        if (precio.getPrecioPaquete() > 0) {
                            mat.setPrecioUnidad(precio.getPrecioPaquete() / precio.getPaquete());
                        } else {
                            mat.setPrecioUnidad(0);
                        }
                        mat.setMaterial(precio.getMaterial());
                        lisFinal.add(mat);
                        break;
                    }
                }

            }
        }
    }

    public void calcularAreas(ArrayList<material> lis) {
        for (material mat : lis) {
            if (!AUXILIAR.isInt(mat.getIdMaterial()) || mat.getTipo().equals("PINTURA")) {
                float m2 = 0;
                if (mat.getMedidaX() == 0 || mat.getMedidaY() == 0) {
                    System.out.println("todo 0 " + mat.getIdMaterial());
                    m2 = mat.getCantidad() * desperdicio;
                    mat.setCantidad(m2);
                } else {
                    /*if (mat.getMedidaZ() > 0) {
                        m2 = cmAm3(mat.getMedidaX(), mat.getMedidaY(), mat.getMedidaZ()) * mat.getCantidad() * desperdicio;
                    } else {*/
                    m2 = cmAm2(mat.getMedidaX(), mat.getMedidaY()) * mat.getCantidad() * desperdicio;
                    //}
                }
                if (!AUXILIAR.isInt(mat.getIdMaterial())) {
                    mat.setMaterial(mat.getIdMaterial());
                }

                mat.setPieza(null);
                mat.setDescripcion(null);
                mat.setPaquete(m2);
                for (AREAS areas : listaAreas) {
                    if (mat.getIdMaterial().equals(areas.getElemento())) {
                        mat.setPrecioUnidad(areas.getPrecio());
                        mat.setTipo(areas.getArea());
                        lisFinal.add(mat);
                        break;
                    }
                }
            }
        }
    }

    public void ordenarTipos() {
        Collections.sort(lisFinal, new Comparator<material>() {
            public int compare(material obj1, material obj2) {
                return obj1.getTipo().compareTo(obj2.getTipo());
            }
        });
        ArrayList<material> lisTem = new ArrayList();
        for (int i = 0; i < lisFinal.size(); i++) {
            if (lisFinal.get(i).getTipo().equals("AGLOMERADO")) {
                lisTem.add(lisFinal.get(i));
                lisFinal.remove(i);
                i--;
            }
        }
        for (int i = 0; i < lisFinal.size(); i++) {
            if (lisFinal.get(i).getTipo().equals("MDF")) {
                lisTem.add(lisFinal.get(i));
                lisFinal.remove(i);
                i--;
            }
        }
        for (int i = 0; i < lisFinal.size(); i++) {
            if (lisFinal.get(i).getTipo().equals("MADERA")) {
                lisTem.add(lisFinal.get(i));
                lisFinal.remove(i);
                i--;
            }
        }
        for (int i = 0; i < lisFinal.size(); i++) {
            lisTem.add(lisFinal.get(i));
            lisFinal.remove(i);
            i--;
        }
        lisFinal = lisTem;
    }
}
