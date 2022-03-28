package CLASES;

import java.util.ArrayList;
import java.util.Date;

public class COMPONENTE {

    int id;
    String nombre;
    String modelo;
    float x;
    float y;
    float z;
    int cantidad;
    String explosivo;
    String corte;
    String foto;
    Date fecha;
    String observaciones;
    ArrayList<material> materiales;
    String hayMateriales;
    float preUni;
    ESTILOS estilo;
    String color;
    String tapizado;
    String nota;
    boolean prueva;

    int tienda;
    int piso;
    int dañado;

    public COMPONENTE() {
        id = -1;
        nombre = null;
        modelo = null;
        x = -1;
        y = -1;
        z = -1;
        explosivo = null;
        foto=null;
        fecha = null;
        observaciones = null;
        materiales = new ArrayList();
        preUni = -1;
        color = null;
        estilo = null;
        nota = null;
        prueva = false;
        hayMateriales=null;
    }

    public String getHayMateriales() {
        return hayMateriales;
    }

    public void setHayMateriales(String hayMateriales) {
        this.hayMateriales = hayMateriales;
    }
    
    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    public int getTienda() {
        return tienda;
    }

    public void setTienda(int tienda) {
        this.tienda = tienda;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public int getDañado() {
        return dañado;
    }

    public void setDañado(int dañado) {
        this.dañado = dañado;
    }

    public String getCorte() {
        return corte;
    }

    public void setCorte(String corte) {
        this.corte = corte;
    }

    public boolean getPrueva() {
        return prueva;
    }

    public void setPrueva(boolean prueva) {
        this.prueva = prueva;
    }

    public COMPONENTE(String nombre, String modelo) {
        this.nombre = nombre;
        this.modelo = modelo;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getTapizado() {
        return tapizado;
    }

    public void setTapizado(String tapizado) {
        this.tapizado = tapizado;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public ESTILOS getEstilo() {
        return estilo;
    }

    public void setEstilo(ESTILOS estilo) {
        this.estilo = estilo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public String getExplosivo() {
        return explosivo;
    }

    public void setExplosivo(String explosivo) {
        this.explosivo = explosivo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public ArrayList<material> getMateriales() {
        return materiales;
    }

    public void setMateriales(ArrayList<material> materiales) {
        this.materiales = materiales;
    }

    public float getPreUni() {
        return preUni;
    }

    public void setPreUni(float preUni) {
        this.preUni = preUni;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
