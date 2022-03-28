package sql;

import GRAFICA.PRINCIPAL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLCREAR {
    
    public void AgregarCliente(String nombre) {
        PreparedStatement ps = null;
        String sql = "INSERT INTO zurich.clientes(nombre) VALUES('"+nombre.toUpperCase()+"') ON DUPLICATE KEY UPDATE nombre=VALUES(nombre);";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.execute();
            ps.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("AgregarCliente "+sql);
            System.out.println("AgregarCliente "+e);
            Logger.getLogger(SQLCREAR.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public ArrayList ObtenerClientes(){
        PreparedStatement ps = null;
        ResultSet rs=null;
        ArrayList lista =new ArrayList();
        String sql = "SELECT * FROM zurich.clientes ORDER BY nombre ASC";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                lista.add(rs.getString("nombre"));
            }
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("ObtenerClientes "+sql);
            System.out.println("ObtenerClientes "+e);
            Logger.getLogger(SQLUSUARIOS.class.getName()).log(Level.SEVERE, null, e);
        }
        return lista;
    }
}
