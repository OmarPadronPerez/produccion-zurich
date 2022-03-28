package CLASES;

import java.util.ArrayList;
import java.util.Date;

public class PEDIDO {
    int id;
    String folio;
    String cliente;
    String observaciones_pedido;
    String observaciones_componente;
    float total;
    Date fechaEntrega;
    Date fechaCreacion;
    int cantidad;
    int echo;
    int entregado;
    int idPedido;
    int idComponente;
    String color;
    String tapizado;
    String prioridad;
    USUARIO autorizado;
    String modelo;
    String componente;
    ArrayList<COMPONENTE> lista;
    boolean reparacion;
    int estado;/* 0 cancelado *** 1 pedido *** 2 entregado ****3 reparacion***** 4 reparacion Entregada */
    
    

    public PEDIDO() {
    }

    public boolean getReparacion() {
        return reparacion;
    }

    public void setReparacion(boolean reparacion) {
        this.reparacion = reparacion;
    }
    
    public int getIdComponente() {
        return idComponente;
    }

    public void setIdComponente(int idComponente) {
        this.idComponente = idComponente;
    }
    
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
    
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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
    
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTapizado() {
        return tapizado;
    }

    public void setTapizado(String tapizado) {
        this.tapizado = tapizado;
    }
    
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }
    
    public ArrayList<COMPONENTE> getLista() {
        return lista;
    }

    public void setLista(ArrayList<COMPONENTE> lista) {
        this.lista = lista;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getObservaciones_pedido() {
        return observaciones_pedido;
    }

    public void setObservaciones_pedido(String observaciones_pedido) {
        this.observaciones_pedido = observaciones_pedido;
    }

    public String getObservaciones_componente() {
        return observaciones_componente;
    }

    public void setObservaciones_componente(String observaciones_componente) {
        this.observaciones_componente = observaciones_componente;
    }
    
    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getEcho() {
        return echo;
    }

    public void setEcho(int echo) {
        this.echo = echo;
    }

    public int getEntregado() {
        return entregado;
    }

    public void setEntregado(int entregado) {
        this.entregado = entregado;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public USUARIO getAutorizado() {
        return autorizado;
    }

    public void setAutorizado(USUARIO autorizado) {
        this.autorizado = autorizado;
    }

    
    
}
