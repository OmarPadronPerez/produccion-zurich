package sql;

import CLASES.material;
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

public class SQLPRODUCCION {
    SimpleDateFormat fSalida = new SimpleDateFormat("yyyy-MM-dd");
    public ArrayList<material> obtenerMateriales(){
        String sql="SELECT * FROM zurich.tableros ORDER BY material ASC;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<material> lista=new ArrayList<>();
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                material mat=new material();
                mat.setMaterial(rs.getString("material"));
                mat.setCantidad(rs.getFloat("existencias"));
                lista.add(mat);
            }/**/
        } catch (SQLException e) {
            System.out.println("obtenerMateriales() " + sql);
            System.out.println("obtenerMateriales() " + e);
            Logger.getLogger(SQLUSUARIOS.class.getName()).log(Level.SEVERE, null, e);
        }
        return lista;
    }
    public boolean guardarMovimientoProduccion(ArrayList<material> lista) {
        String sql = "INSERT INTO zurich.movimiento_tableros(material,cantidad)";
        PreparedStatement ps = null;
        boolean respuesta = false;
        int contador=0;
        for (int i = 0; i < lista.size(); i++) {
            material mat = lista.get(i);
            if (mat.getMedidaZ() > 0) {
                if (contador == 0) {
                    sql = sql + "VALUES";
                } else {
                    sql = sql + ",";
                }
                float diferencia = mat.getMedidaZ();
                sql = sql + "('" + mat.getMaterial() + "'," + diferencia + ")";
                contador++;
            }
        }
        sql = sql + ";";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.execute();
            ps.close();
            con.close();
            respuesta = true;
        } catch (SQLException ex) {
            respuesta = false;
            System.out.println("guardarMovimientoProduccion " + sql);
            System.out.println("guardarMovimientoProduccion " + ex);
            Logger.getLogger(SQLPRODUCCION.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    /*public boolean actualizarExistencias(ArrayList<material> lista, int origen) {
        String sql=null;
        PreparedStatement ps = null;
        boolean respuesta = false;
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            for (material mat : lista) {
                
                if (mat.getMedidaZ() > 0) {
                    float nuevo = mat.getMedidaZ();
                    if (origen == 1) {
                        nuevo = -mat.getMedidaZ();
                    }
                    sql = "UPDATE zurich.tableros SET existencias=existencias+" + nuevo + " where material='" + mat.getMaterial() + "';";
                    ps = con.prepareStatement(sql);
                    ps.execute();
                }
            }
            ps.close();
            con.close();
            respuesta = true;

        } catch (SQLException ex) {
            respuesta = false;
            System.out.println("actualizarExistencias: " + sql);
            System.out.println("actualizarExistencias: " + ex);
            Logger.getLogger(SQLPRODUCCION.class.getName()).log(Level.SEVERE, null, ex);
        }

        return respuesta;
    }*/
    public ArrayList<material> obtenerMovimientoProduccion(Date inicio, Date fin){
        String sql="SELECT * FROM zurich.movimiento_tableros "
                + "WHERE fecha BETWEEN '"+fSalida.format(inicio)+"' AND '"+fSalida.format(fin)+"' "
                + "ORDER BY material;";
        ArrayList<material> lista=new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                material mat=new material();
                mat.setMaterial(rs.getString("material"));
                mat.setCantidad(rs.getFloat("cantidad"));
                mat.setFecha(rs.getDate("fecha"));
                lista.add(mat);
            }
        } catch (SQLException ex) {
            System.out.println("obtenerMovimientoProduccion: " + sql);
            System.out.println("obtenerMovimientoProduccion: " + ex);
            Logger.getLogger(SQLPRODUCCION.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
