package sql;

import CLASES.COMPONENTE;
import CLASES.PEDIDO;
import GRAFICA.PRINCIPAL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class SQLPEDIDOS {
    
    SimpleDateFormat fSal = new SimpleDateFormat("yyyy-MM-dd");
    
    public boolean checarFolio(String folio) {
        PreparedStatement ps = null;
        String sql = "SELECT id FROM zurich.pedidos WHERE id='" + folio + "'";
        ResultSet rs = null;
        boolean resultado = false;
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                resultado = true;
            } else {
                resultado = false;
            }
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("checarFolio: " + sql);
            System.out.println("checarFolio: " + e);
        }
        return resultado;
    }
    
    public void guardarPedidos(PEDIDO ped) {
        PreparedStatement ps = null;
        ped.setCliente(ped.getCliente().replaceAll("^\\s*", ""));
        ped.setCliente(ped.getCliente().replaceAll("\\s*$", ""));
        ped.setCliente(ped.getCliente().toUpperCase());
        
        String sql = "INSERT INTO zurich.pedidos(folio,cliente,observaciones,fechaCreacion,fechaEntrega,idcomponente,cantidad,color,tapizado,prioridad,total,autorizado,estado,reparacion) VALUES";
        ArrayList<COMPONENTE> lista = ped.getLista();
        for (int a = 0; a < lista.size(); a++) {
            COMPONENTE com = lista.get(a);
            int estado;
            if(ped.getReparacion()){
                estado=3;
            }else{
                estado=1;
            }
            com.setObservaciones(com.getObservaciones().replaceAll("^\\s*", ""));
            com.setObservaciones(com.getObservaciones().replaceAll("\\s*$", ""));
            com.setObservaciones(com.getObservaciones().toUpperCase());
            if (a != 0) {
                sql = sql + ",";
            }
            sql = sql + " ('" + ped.getFolio() + "','" + ped.getCliente() + "','" + com.getObservaciones() + "',CURDATE(),'" + fSal.format(ped.getFechaEntrega()) + "'"
                    + "," + com.getId() + "," + com.getCantidad() + ",'" + com.getEstilo().getNombre() + "','" + com.getEstilo().getNombreTapiz() + "','"
                    + ped.getPrioridad() + "'," + com.getPreUni() + "," + ped.getAutorizado().getId()+  "," + estado +"," +ped.getReparacion()+ ")";
        }
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.execute();
            ps.close();
            con.close();
            //JOptionPane.showMessageDialog(null, "Guardado");
        } catch (SQLException e) {
            System.out.println("guardarPedidos " + sql);
            System.out.println("guardarPedidos " + e);
            Logger.getLogger(SQLPEDIDOS.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Error al guardar\n" + e);
        }
    }
    
    public ArrayList<PEDIDO> obtenerPedidoComponente(String folio,boolean filtro) {
        ArrayList<PEDIDO> lista = new ArrayList();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT pedidos.`id` AS id, pedidos.`folio` AS folio, "
                + "pedidos.`cliente` AS cliente, pedidos.`idcomponente` AS idcomponente, "
                + "pedidos.`observaciones` AS pedidos_observaciones,pedidos.`fechaCreacion` AS fechaCreacion, "
                + "pedidos.`fechaEntrega` AS fechaEntrega, pedidos.`cantidad` AS cantidad, "
                + "pedidos.`echo` AS echo, pedidos.`entregado` AS entregado, "
                + "pedidos.`color` AS color,pedidos.`tapizado` AS tapizado, "
                + "pedidos.`prioridad` AS prioridad, componentes.`nombre` AS componente, "
                + "componentes.`modelo` AS modelo,componentes.`medidaX` AS X, "
                + "componentes.`medidaY` AS Y, componentes.`medidaZ` AS Z, "
                + "componentes.`observaciones` AS componentes_observaciones, "
                +"pedidos.`reparacion` AS reparacion,"
                + "pedidos.`estado` AS estado FROM `componentes` componentes "
                + "INNER JOIN `pedidos` pedidos ON componentes.`id` = pedidos.`idcomponente` "
                + "WHERE pedidos.`folio`= '" + folio+"'";
                if(filtro){
                    sql=sql+" AND pedidos.`estado` !=0";
                }
                sql=sql + " ORDER BY pedidos.`fechaEntrega` ASC";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                PEDIDO ped = new PEDIDO();
                ped.setId(rs.getInt("id"));
                ped.setFolio(rs.getString("folio"));
                ped.setCliente(rs.getString("cliente"));
                ped.setObservaciones_componente(rs.getString("componentes_observaciones"));
                ped.setObservaciones_pedido(rs.getString("pedidos_observaciones"));
                ped.setFechaCreacion(rs.getDate("fechaCreacion"));
                ped.setFechaEntrega(rs.getDate("fechaEntrega"));
                ped.setCantidad(rs.getInt("cantidad"));
                ped.setEcho(rs.getInt("echo"));
                ped.setEstado(rs.getInt("estado"));
                ped.setEntregado(rs.getInt("entregado"));
                ped.setColor(rs.getString("color"));
                ped.setTapizado(rs.getString("Tapizado"));
                ped.setComponente(rs.getString("componente"));
                ped.setModelo(rs.getString("modelo"));
                ped.setIdComponente(rs.getInt("idcomponente"));
                ped.setPrioridad(rs.getString("prioridad"));
                ped.setEstado(rs.getInt("estado"));
                ped.setReparacion(rs.getBoolean("reparacion"));
                lista.add(ped);
            }
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("obtenerPedidoComponente " + sql);
            System.out.println("obtenerPedidoComponente " + e);
            Logger.getLogger(SQLPEDIDOS.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Error al obtener resultados\n" + e);
        }
        return lista;
    }
    
    public void guardarActualizacion(ArrayList<PEDIDO> lista) {
        PreparedStatement ps = null;
        for (int a = 0; a < lista.size(); a++) {
            String sql = "update zurich.pedidos set echo=?, entregado=?,fechaEntrega=?,estado=? WHERE id=?";
            try {
                PRINCIPAL.validarCon();
                Connection con = PRINCIPAL.dataSource.getConnection();
                ps = con.prepareStatement(sql);
                ps.setInt(1, lista.get(a).getEcho());
                ps.setInt(2, lista.get(a).getEntregado());
                ps.setString(3, fSal.format(lista.get(a).getFechaEntrega()));
                ps.setInt(4, lista.get(a).getEstado());
                ps.setInt(5, lista.get(a).getId());
                ps.execute();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(a + "guardarActualizacion " + sql);
                System.out.println(a + "guardarActualizacion " + e);
                Logger.getLogger(SQLMUEBLES.class.getName()).log(Level.SEVERE, null, e);
            }
            
        }
        JOptionPane.showMessageDialog(null, "GUARDADO");
    }
    
    public ArrayList<PEDIDO> obtenerPedidosFiltrado(String sql) {
        ArrayList<PEDIDO> lista = new ArrayList();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                PEDIDO ped = new PEDIDO();
                ped.setId(rs.getInt("id"));
                ped.setFolio(rs.getString("folio"));
                ped.setCliente(rs.getString("cliente"));
                ped.setObservaciones_componente(rs.getString("componentes_observaciones"));
                ped.setObservaciones_pedido(rs.getString("pedidos_observaciones"));
                ped.setFechaCreacion(rs.getDate("fechaCreacion"));
                ped.setFechaEntrega(rs.getDate("fechaEntrega"));
                ped.setCantidad(rs.getInt("cantidad"));
                ped.setEcho(rs.getInt("echo"));
                ped.setEntregado(rs.getInt("entregado"));
                ped.setColor(rs.getString("color"));
                ped.setTapizado(rs.getString("Tapizado"));
                ped.setComponente(rs.getString("componente"));
                ped.setModelo(rs.getString("modelo"));
                ped.setIdComponente(rs.getInt("idcomponente"));
                ped.setPrioridad(rs.getString("prioridad"));
                ped.setEstado(rs.getInt("estado"));
                ped.setReparacion(rs.getBoolean("reparacion"));
                lista.add(ped);
            }
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("obtenerPedidoComponente " + sql);
            System.out.println("obtenerPedidoComponente " + e);
            Logger.getLogger(SQLPEDIDOS.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Error al obtener resultados\n" + e);
        }
        return lista;
    }
    
    public ArrayList<PEDIDO> obtenerPedidosRango(Date inicio, Date fin, String estado) {
        ArrayList<PEDIDO> lista = new ArrayList();
        PreparedStatement ps = null;
        String forma = null;
        ResultSet rs = null;
        String sql = "SELECT pedidos.`id` AS id, pedidos.`folio` AS folio, "
                + "pedidos.`cliente` AS cliente, pedidos.`idcomponente` AS idcomponente, "
                + "pedidos.`observaciones` AS pedidos_observaciones,pedidos.`fechaCreacion` AS fechaCreacion, "
                + "pedidos.`fechaEntrega` AS fechaEntrega, pedidos.`cantidad` AS cantidad, "
                + "pedidos.`echo` AS echo, pedidos.`entregado` AS entregado, "
                + "pedidos.`color` AS color,pedidos.`tapizado` AS tapizado, "
                + "pedidos.`prioridad` AS prioridad, componentes.`nombre` AS componente, "
                + "componentes.`modelo` AS modelo,componentes.`medidaX` AS X, "
                + "componentes.`medidaY` AS Y, componentes.`medidaZ` AS Z, "
                + "componentes.`observaciones` AS componentes_observaciones, "
                + "pedidos.`estado` AS estado FROM `componentes` componentes "
                + "INNER JOIN `pedidos` pedidos ON componentes.`id` = pedidos.`idcomponente` "
                + "WHERE pedidos.`estado`!=0 AND";
        
        if (estado.equals("PEDIDO")) {
            forma = " fechaCreacion ";
        } else {
            forma = " fechaEntrega ";
        }
        sql = sql + forma + " BETWEEN '" + fSal.format(inicio) + "' AND '" + fSal.format(fin) + "' ORDER BY" + forma + ";";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                PEDIDO ped = new PEDIDO();
                ped.setId(rs.getInt("id"));
                ped.setFolio(rs.getString("folio"));
                ped.setCliente(rs.getString("cliente"));
                ped.setFechaCreacion(rs.getDate("fechaCreacion"));
                ped.setFechaEntrega(rs.getDate("fechaEntrega"));
                ped.setCantidad(rs.getInt("cantidad"));
                ped.setEcho(rs.getInt("echo"));
                ped.setEntregado(rs.getInt("entregado"));
                ped.setColor(rs.getString("color"));
                ped.setTapizado(rs.getString("Tapizado"));
                ped.setComponente(rs.getString("componente"));
                ped.setModelo(rs.getString("modelo"));
                ped.setIdComponente(rs.getInt("idcomponente"));
                ped.setPrioridad(rs.getString("prioridad"));
                ped.setEstado(rs.getInt("estado"));
                ped.setReparacion(rs.getBoolean("reparacion"));
                lista.add(ped);
            }
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("obtenerPedidosRango " + sql);
            System.out.println("obtenerPedidosRango " + ex);
            Logger.getLogger(SQLPEDIDOS.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(SQLPEDIDOS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public boolean guardarReparaciones(ArrayList<PEDIDO> lista) {
        PreparedStatement ps = null;
        String sql = "INSERT INTO zurich.pedidos(folio,cliente,observaciones,"
                + "fechaCreacion,fechaEntrega,idcomponente,cantidad,color,"
                + "tapizado,prioridad,total,autorizado,estado) VALUES";
        for (int i = 0; i < lista.size(); i++) {
            PEDIDO ped = lista.get(i);
            if (i != 0) {
                sql = sql + ",";
            }
            sql = sql + "('REP" + ped.getFolio() + "','" + ped.getCliente() + "','" + ped.getObservaciones_pedido()
                    + "','" + fSal.format(ped.getFechaCreacion()) + "','" + fSal.format(ped.getFechaEntrega())
                    + "','" + ped.getIdComponente() + "'," + ped.getCantidad() + ",'" + ped.getColor()
                    + "','" + ped.getTapizado() + "'," + "'Alta'" + "," + "0.0" + "," + PRINCIPAL.user.getId()
                    + "," + "3)";
        }
        sql = sql + ";";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.execute();
            ps.close();
            con.close();
            return true;
        } catch (SQLException e) {
            System.out.println("guardarReparaciones " + sql);
            System.out.println("guardarReparaciones " + e);
            Logger.getLogger(SQLPEDIDOS.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public boolean cancelarPedido(String folio) {
        PreparedStatement ps = null;
        String sql = "update zurich.pedidos set estado=0 WHERE folio=? AND (estado!=2 OR estado!=4);";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, folio);
            ps.execute();
            ps.close();
            con.close();
            return true;
        } catch (SQLException e) {
            System.out.println("guardarReparaciones " + sql);
            System.out.println("guardarReparaciones " + e);
            Logger.getLogger(SQLPEDIDOS.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    
    public ArrayList<PEDIDO> obtenerParaInicio() {
        ArrayList<PEDIDO> lista = new ArrayList();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT pedidos.`id` AS id, pedidos.`folio` AS folio, "
                + "pedidos.`cliente` AS cliente, pedidos.`idcomponente` AS idcomponente, "
                + "pedidos.`observaciones` AS pedidos_observaciones,pedidos.`fechaCreacion` AS fechaCreacion, "
                + "pedidos.`fechaEntrega` AS fechaEntrega, pedidos.`cantidad` AS cantidad, "
                + "pedidos.`echo` AS echo, pedidos.`entregado` AS entregado, "
                + "pedidos.`color` AS color,pedidos.`tapizado` AS tapizado, "
                + "pedidos.`prioridad` AS prioridad, componentes.`nombre` AS componente, "
                + "componentes.`modelo` AS modelo,componentes.`medidaX` AS X, "
                + "componentes.`medidaY` AS Y, componentes.`medidaZ` AS Z, "
                + "componentes.`observaciones` AS componentes_observaciones, "
                +"pedidos.`reparacion` AS reparacion,  "
                + "pedidos.`estado` AS estado FROM `componentes` componentes "
                + "INNER JOIN `pedidos` pedidos ON componentes.`id` = pedidos.`idcomponente` "
                + "WHERE pedidos.`cantidad` > pedidos.`entregado` AND pedidos.`estado` != 0 "
                + "ORDER BY pedidos.`fechaEntrega` ASC";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                PEDIDO ped = new PEDIDO();
                ped.setId(rs.getInt("id"));
                ped.setFolio(rs.getString("folio"));
                ped.setCliente(rs.getString("cliente"));
                ped.setObservaciones_componente(rs.getString("componentes_observaciones"));
                ped.setObservaciones_pedido(rs.getString("pedidos_observaciones"));
                ped.setFechaCreacion(rs.getDate("fechaCreacion"));
                ped.setFechaEntrega(rs.getDate("fechaEntrega"));
                ped.setCantidad(rs.getInt("cantidad"));
                ped.setEcho(rs.getInt("echo"));
                ped.setEntregado(rs.getInt("entregado"));
                ped.setColor(rs.getString("color"));
                ped.setTapizado(rs.getString("Tapizado"));
                ped.setComponente(rs.getString("componente"));
                ped.setModelo(rs.getString("modelo"));
                ped.setIdComponente(rs.getInt("idcomponente"));
                ped.setPrioridad(rs.getString("prioridad"));
                ped.setEstado(rs.getInt("estado"));
                ped.setReparacion(rs.getBoolean("reparacion"));
                lista.add(ped);
            }
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("guardarReparaciones " + sql);
            System.out.println("guardarReparaciones " + e);
            Logger.getLogger(SQLPEDIDOS.class.getName()).log(Level.SEVERE, null, e);
        }
        return lista;
    }
}
