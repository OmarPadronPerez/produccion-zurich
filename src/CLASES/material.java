package CLASES;

import java.util.Date;

public class material {

    int id;
    String material;
    String idMaterial;
    String pieza;
    String descripcion;
    String tipo;
    String modelo;
    String componente;
    String parte;
    String unidades;
    String empaque;
    int idcomponente;
    float medidaX;
    float medidaY;
    float medidaZ;
    float cantidad;
    float paquete;
    float cantidaPaquetes;
    float precioUnidad;
    float precioPaquete;
    float minimo;
    Date fecha;
    boolean cnc;
    

    public material() {
        this.unidades=null;
        this.empaque=null;
        this.material = null;
        this.idMaterial = null;
        this.pieza = null;
        this.descripcion = null;
        this.tipo = null;
        this.modelo = null;
        this.componente = null;
        this.idcomponente = -1;
        this.medidaX = -1;
        this.medidaY = -1;
        this.medidaZ = -1;
        this.cantidad = -1;
        this.paquete = -1;
        this.precioUnidad = -1;
        this.precioPaquete = -1;
        this.fecha = null;
        this.parte=null;
        this.minimo=-1;
        this.cnc=false;
    }

    public boolean getCnc() {
        return cnc;
    }

    public void setCnc(boolean cnc) {
        this.cnc = cnc;
    }
    
    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }

    public String getEmpaque() {
        return empaque;
    }

    public void setEmpaque(String empaque) {
        this.empaque = empaque;
    }
    

    public float getMinimo() {
        return minimo;
    }

    public void setMinimo(float minimo) {
        this.minimo = minimo;
    }
    
    public String getParte() {
        return parte;
    }

    public void setParte(String parte) {
        this.parte = parte;
    }
    
    public float getCantidaPaquetes() {
        return cantidaPaquetes;
    }

    public void setCantidaPaquetes(float cantidaPaquetes) {
        this.cantidaPaquetes = cantidaPaquetes;
    }

    public int getIdcomponente() {
        return idcomponente;
    }

    public void setIdcomponente(int idcomponente) {
        this.idcomponente = idcomponente;
    }

    public String getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(String idMaterial) {
        this.idMaterial = idMaterial;
    }

    public float getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(float precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public float getPrecioPaquete() {
        return precioPaquete;
    }

    public void setPrecioPaquete(float precioPaquete) {
        this.precioPaquete = precioPaquete;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getPieza() {
        return pieza;
    }

    public void setPieza(String pieza) {
        this.pieza = pieza;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getComponente() {
        return componente;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    public float getMedidaX() {
        return medidaX;
    }

    public void setMedidaX(float medidaX) {
        this.medidaX = medidaX;
    }

    public float getMedidaY() {
        return medidaY;
    }

    public void setMedidaY(float medidaY) {
        this.medidaY = medidaY;
    }

    public float getMedidaZ() {
        return medidaZ;
    }

    public void setMedidaZ(float medidaZ) {
        this.medidaZ = medidaZ;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public float getPaquete() {
        return paquete;
    }

    public void setPaquete(float paquete) {
        this.paquete = paquete;
    }

}
