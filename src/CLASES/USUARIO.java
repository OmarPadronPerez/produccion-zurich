package CLASES;

public class USUARIO {
    int id;
    String nombre;
    String apellido;
    String contraseña;
    boolean estado;
    boolean agregar;
    boolean almacen;
    boolean pedidos;
    boolean calcular;
    boolean resumen;
    boolean administrar;
    boolean bodega;
    boolean produccion;
    

    public USUARIO() {
        id=-1;
        agregar=false;
        almacen=false;
        pedidos=false;
        resumen=false;
        calcular=false;
        bodega=false;
        produccion=false;
        this.estado =false;
        this.administrar=false;
    }
    
    public USUARIO(String nombre, String apellido, String contraseña, boolean estado) {
        id=-1;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contraseña = contraseña;
        this.estado =false;
        this.agregar=false;
        this.almacen=false;
        this.pedidos=false;
        this.resumen=false;
        this.calcular=false;
        this.produccion=false;
        this.administrar=false;
    }

    public boolean getProduccion() {
        return produccion;
    }

    public void setProduccion(boolean produccion) {
        this.produccion = produccion;
    }
    
    public boolean getBodega() {
        return bodega;
    }

    public void setBodega(boolean bodega) {
        this.bodega = bodega;
    }

    public boolean getAdministrar() {
        return administrar;
    }

    public void setAdministrar(boolean administrar) {
        this.administrar = administrar;
    }
    
    public boolean getCalcular() {
        return calcular;
    }

    public void setCalcular(boolean calcular) {
        this.calcular = calcular;
    }
    
    public boolean getAgregar() {
        return agregar;
    }

    public void setAgregar(boolean agregar) {
        this.agregar = agregar;
    }

    public boolean getAlmacen() {
        return almacen;
    }

    public void setAlmacen(boolean almacen) {
        this.almacen = almacen;
    }

    public boolean getPedidos() {
        return pedidos;
    }

    public void setPedidos(boolean pedidos) {
        this.pedidos = pedidos;
    }

    public boolean getResumen() {
        return resumen;
    }

    public void setResumen(boolean resumen) {
        this.resumen = resumen;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean getEstado() {
        return estado;
    }

    
    
    
}
