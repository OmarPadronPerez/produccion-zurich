package sql;

import CLASES.COMPONENTE;
import GRAFICA.PRINCIPAL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLBODEGA {

    public ArrayList<COMPONENTE> obtenerExistencias(int id) {
        ArrayList<COMPONENTE> lista = new ArrayList<COMPONENTE>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT "
                + "existencias_muebles.`id` AS id, "
                + "existencias_muebles.`idcomponente` AS idcomponente, "
                + "existencias_muebles.`cantidad` AS cantidad, "
                + "existencias_muebles.`notas` AS notas, "
                + "existencias_muebles.`ubicacion` AS ubicacion, "
                + "existencias_muebles.`fecha` AS fecha, "
                + "componentes.`id` AS idComp, "
                + "componentes.`nombre` AS componente_nombre, "
                + "componentes.`modelo` AS componente_modelo "
                + "FROM `componentes` componentes INNER JOIN `existencias_muebles` existencias_muebles "
                + "ON componentes.`id` = existencias_muebles.`idcomponente` "
                + "where idComponente=" + id + " ORDER BY ubicacion asc, fecha asc;";

        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                COMPONENTE com = new COMPONENTE();
                com.setId(rs.getInt("id"));
                com.setObservaciones(rs.getString("idcomponente"));
                com.setNota(rs.getString("notas"));
                com.setCantidad(rs.getInt("cantidad"));
                com.setNombre(rs.getString("componente_nombre"));
                com.setModelo(rs.getString("componente_modelo"));
                com.setExplosivo(rs.getString("ubicacion"));
                com.setFecha(rs.getDate("fecha"));
                lista.add(com);
            }
            rs.close();
            ps.cancel();
            con.close();
        } catch (SQLException ex) {
            System.out.println("obtenerExistencias" + sql);
            System.out.println("obtenerExistencias" + ex);
            Logger.getLogger(SQLBODEGA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public boolean guardarExistencias(COMPONENTE com) {
        PreparedStatement ps = null;
        String sql = null;
        if (com.getId() > 0) {
            sql = "update existencias_muebles set ubicacion='" + com.getExplosivo()
                    + "',notas='" + com.getNota() + "' where id=" + com.getId() + ";";
        } else {
            sql = "INSERT INTO existencias_muebles(idComponente, cantidad, notas, ubicacion) "
                    + "values(" + com.getObservaciones() + "," + com.getCantidad()
                    + ",'" + com.getNota() + "','" + com.getExplosivo() + "');";
        }
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.execute();
            ps.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("guardarExistencias" + sql);
            System.out.println("guardarExistencias" + ex);
            Logger.getLogger(SQLBODEGA.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean borrarExistencias(COMPONENTE com) {
        PreparedStatement ps = null;
        String sql = "DELETE FROM zurich.existencias_muebles WHERE id=" + com.getId() + ";";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.execute();
            ps.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("guardarExistencias" + sql);
            System.out.println("guardarExistencias" + ex);
            Logger.getLogger(SQLBODEGA.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
