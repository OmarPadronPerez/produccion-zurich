package sql;

import CLASES.COMPONENTE;
import CLASES.material;
import GRAFICA.PRINCIPAL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLIMPRIMIR {
    
    public COMPONENTE HojadeTrabajo(String sql){
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<material> lista = new ArrayList();
        COMPONENTE com=new COMPONENTE();
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                material mat = new material();
                mat.setId(rs.getInt("id"));
                mat.setModelo(rs.getString("modelo"));
                mat.setComponente(rs.getString("componente"));
                mat.setPieza(rs.getString("pieza"));
                mat.setDescripcion(rs.getString("descripcion"));
                mat.setIdMaterial(rs.getString("idMaterial"));
                mat.setMedidaX(rs.getFloat("medX"));
                mat.setMedidaY(rs.getFloat("medY"));
                mat.setMedidaZ(rs.getFloat("medZ"));
                mat.setCantidad(rs.getFloat("cantidad"));
                mat.setMaterial(rs.getString("material"));
                mat.setTipo(rs.getString("tipo"));
                com.setObservaciones(rs.getString("observaciones"));
                com.setFecha(rs.getDate("actualizacion"));
                com.setX(rs.getFloat("medidaX"));
                com.setY(rs.getFloat("medidaY"));
                com.setZ(rs.getFloat("medidaZ"));
                lista.add(mat);
            }
            com.setMateriales(lista);
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("obtenerMaterialComponente " + sql);
            System.out.println("obtenerMaterialComponente " + e);
            Logger.getLogger(SQLMUEBLES.class.getName()).log(Level.SEVERE, null, e);
        }
        return com;
    }
}
