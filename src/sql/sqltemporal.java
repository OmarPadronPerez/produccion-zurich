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

public class sqltemporal{
    SQLMUEBLES modMuebles=new SQLMUEBLES();
    ArrayList<COMPONENTE> lista=modMuebles.obtenerComponentes("TODO");
    public void actualizarIdCom(){
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        String sql="select * from zurich.materialusadonuevo";
        try {
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                for(COMPONENTE com:lista){
                    if(com.getModelo().equals(rs.getString("modelo")) &&
                       com.getNombre().equals(rs.getString("componente")) ){
                        sql="UPDATE zurich.materialusadonuevo SET idComponente="+com.getId()+" where id="+rs.getInt("id");
                        ps2 = con.prepareStatement(sql);
                        ps2.execute();
                        ps2.close();
                        System.out.println("guardarComponente1 " + sql);
                        break;
                    }
                }
            }
            ps.close();
            System.out.println("*********************************fin***************************");
        } catch (SQLException ex) {
            System.out.println("guardarComponente1 " + sql);
            System.out.println("guardarComponente1 " + ex);
            Logger.getLogger(sqltemporal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
