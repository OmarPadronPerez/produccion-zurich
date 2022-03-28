package sql;

import CLASES.USUARIO;
import GRAFICA.PRINCIPAL;
import OTROS.CONTRASEÑA;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class SQLUSUARIOS {

    public void AgregarUsuario(USUARIO us) {
        PreparedStatement ps = null;
        us.setNombre(us.getNombre().replaceAll("^\\s*", ""));
        us.setNombre(us.getNombre().replaceAll("\\s*$", ""));
        us.setNombre(us.getNombre().toUpperCase());
        us.setApellido(us.getApellido().replaceAll("^\\s*", ""));
        us.setApellido(us.getApellido().replaceAll("\\s*$", ""));
        us.setApellido(us.getApellido().toUpperCase());
        String sql = "INSERT INTO zurich.usuarios(id,nombre,apellido,contraseña,estado,agregar,almacen,pedidos,resumen,calcular,administrar,bodega,produccion)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE contraseña=VALUES(contraseña), estado=VALUES(estado),"
                + "agregar=VALUES(agregar),almacen=VALUES(almacen),pedidos=VALUES(almacen),pedidos=VALUES(pedidos), "
                + "resumen=VALUES(resumen),calcular=VALUES(calcular),administrar=VALUES(administrar),bodega=VALUES(bodega),"
                + "produccion=VALUES(produccion);";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, us.getId());
            ps.setString(2, us.getNombre());
            ps.setString(3, us.getApellido());
            String contra = CONTRASEÑA.Encriptar(us.getContraseña());
            ps.setString(4, contra);
            ps.setBoolean(5, us.getEstado());
            ps.setBoolean(6, us.getAgregar());
            ps.setBoolean(7, us.getAlmacen());
            ps.setBoolean(8, us.getPedidos());
            ps.setBoolean(9, us.getResumen());
            ps.setBoolean(10, us.getCalcular());
            ps.setBoolean(11, us.getAdministrar());
            ps.setBoolean(12, us.getBodega());
            ps.setBoolean(13, us.getProduccion());
            ps.execute();
            ps.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Usuario guardado", "Guardado", JOptionPane.PLAIN_MESSAGE);
        } catch (HeadlessException | SQLException e) {
            System.out.println("AgregarUsuario " + sql);
            System.out.println("AgregarUsuario " + e);
            Logger.getLogger(SQLUSUARIOS.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public USUARIO BuscarPorId(int id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        USUARIO us = null;
        String sql = "SELECT * FROM zurich.usuarios WHERE id=" + id;
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                us = new USUARIO();
                us.setId(id);
                us.setNombre(rs.getString("nombre").toUpperCase());
                us.setApellido(rs.getString("apellido").toUpperCase());
                us.setContraseña(rs.getString("contraseña"));
                if (rs.getInt("estado") == 0) {
                    us.setEstado(false);
                } else {
                    us.setEstado(true);
                }
                us.setAgregar(rs.getBoolean("agregar"));
                us.setAlmacen(rs.getBoolean("almacen"));
                us.setPedidos(rs.getBoolean("pedidos"));
                us.setResumen(rs.getBoolean("resumen"));
                us.setCalcular(rs.getBoolean("calcular"));
                us.setBodega(rs.getBoolean("bodega"));
                us.setProduccion(rs.getBoolean("produccion"));
                us.setAdministrar(rs.getBoolean("administrar"));
            }
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("usuarioPorId " + sql);
            System.out.println("usuarioPorId " + e);
            Logger.getLogger(SQLUSUARIOS.class.getName()).log(Level.SEVERE, null, e);
        }
        return us;
    }
}
