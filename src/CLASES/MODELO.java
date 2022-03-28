package CLASES;

public class MODELO {
    String nombre;
    String descripcion;
    int nuevo;
    boolean prueva;

    
    public MODELO(String nombre, String descripcion, int nuevo,boolean prueva) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nuevo = nuevo;
        this.prueva=prueva;
    }

    public MODELO() {
    }
    

    public boolean getPrueva() {
        return prueva;
    }

    public void setPrueva(boolean prueva) {
        this.prueva = prueva;
    }
    
    public int getNuevo() {
        return nuevo;
    }

    public void setNuevo(int nuevo) {
        this.nuevo = nuevo;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
