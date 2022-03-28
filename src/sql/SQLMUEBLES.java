package sql;

import CLASES.COMPONENTE;
import CLASES.ESTILOS;
import CLASES.MODELO;
import CLASES.material;
import GRAFICA.PRINCIPAL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLMUEBLES {

    public ArrayList<MODELO> ObtenerModelos() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList lista = new ArrayList();
        String sql = "select * from zurich.modelos ORDER BY nombre ASC";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ArrayList lis2 = new ArrayList();
                lis2.add(rs.getString("nombre"));
                lis2.add(rs.getString("descripcion"));
                lis2.add(rs.getBoolean("prueva"));
                lista.add(lis2);
            }
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("ObtenerModelos " + sql);
            System.out.println("ObtenerModelos " + e);
            Logger.getLogger(SQLMUEBLES.class.getName()).log(Level.SEVERE, null, e);
        }
        return lista;
    }
    public ArrayList<MODELO> ObtenerModelosClases() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<MODELO> lista = new ArrayList();
        String sql = "select * from zurich.modelos ORDER BY nombre ASC";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                MODELO mod=new MODELO();
                mod.setNombre(rs.getString("nombre"));
                mod.setDescripcion(rs.getString("descripcion"));
                mod.setPrueva(rs.getBoolean("prueva"));
                lista.add(mod);
            }
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("ObtenerModelos " + sql);
            System.out.println("ObtenerModelos " + e);
            Logger.getLogger(SQLMUEBLES.class.getName()).log(Level.SEVERE, null, e);
        }
        return lista;
    }
    public MODELO ObtenerModelosClases(String modelo){
        PreparedStatement ps = null;
        ResultSet rs = null;
        MODELO mod=new MODELO();
        String sql = "select * from zurich.modelos where nombre='"+modelo+"';";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                mod.setNombre(rs.getString("nombre"));
                mod.setDescripcion(rs.getString("descripcion"));
                mod.setPrueva(rs.getBoolean("prueva"));
            }
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("ObtenerModelos " + sql);
            System.out.println("ObtenerModelos " + e);
            Logger.getLogger(SQLMUEBLES.class.getName()).log(Level.SEVERE, null, e);
        }
        return mod;
        
    }
    

    public void guardarModelo(MODELO modelo) {
        PreparedStatement ps = null;
        String nombre = modelo.getNombre().replaceAll("^\\s*", "");
        nombre = nombre.replaceAll("\\s*$", "");
        nombre = nombre.toUpperCase();

        String descripcion = modelo.getDescripcion().replaceAll("^\\s*", "");
        descripcion = descripcion.replaceAll("\\s*$", "");
        descripcion = descripcion.toUpperCase();
        String sql = "INSERT INTO zurich.modelos(nombre,descripcion,prueva) VALUES(?,?,?) "
                + "ON DUPLICATE KEY UPDATE descripcion=VALUES(descripcion), prueva=VALUES(prueva);";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.setBoolean(3, modelo.getPrueva());
            ps.execute();
            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("guardarModelo " + sql);
            System.out.println("guardarModelo " + e);
            Logger.getLogger(SQLMUEBLES.class.getName()).log(Level.SEVERE, null, e);
        }

    }
    public ArrayList<COMPONENTE> obtenerComponentes(String modelo) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<COMPONENTE> lista = new ArrayList();
        String sql = null;
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            if (modelo.equals("TODO")) {
                sql = "SELECT * FROM zurich.componentes ORDER BY nombre ASC;";
                ps = con.prepareStatement(sql);
            } else {
                sql = "SELECT * FROM zurich.componentes WHERE modelo=? ORDER BY nombre ASC;";
                ps = con.prepareStatement(sql);
                ps.setString(1, modelo);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                COMPONENTE com = new COMPONENTE();
                com.setId(rs.getInt("id"));
                com.setNombre(rs.getString("nombre"));
                com.setModelo(rs.getString("modelo"));
                com.setX(rs.getFloat("medidaX"));
                com.setY(rs.getFloat("medidaY"));
                com.setZ(rs.getFloat("medidaZ"));
                
                com.setExplosivo(rs.getString("explosivo"));
                com.setCorte(rs.getString("cortes"));
                com.setFoto(rs.getString("foto"));
                
                com.setFecha(rs.getDate("actualizacion"));
                com.setObservaciones(rs.getString("observaciones"));
                com.setNota(rs.getString("notas"));
                com.setPrueva(rs.getBoolean("prueba"));
                lista.add(com);
            }
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("obtenerComponentes " + sql);
            System.out.println("obtenerComponentes " + e);
            Logger.getLogger(SQLMUEBLES.class.getName()).log(Level.SEVERE, null, e);
        }
        return lista;
    }
    public ArrayList<COMPONENTE> obtenerComponentes() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<COMPONENTE> lista = new ArrayList();
        String sql = null;
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
                sql = "SELECT *, "
                        + "if((select count(pieza) FROM zurich.materialusadonuevo "
                                + "where idComponente=componentes.id)>5,\"si\",null) "
                        + "as resultado "
                        + "FROM zurich.componentes;";
                ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                COMPONENTE com = new COMPONENTE();
                com.setId(rs.getInt("id"));
                com.setNombre(rs.getString("nombre"));
                com.setModelo(rs.getString("modelo"));
                com.setX(rs.getFloat("medidaX"));
                com.setY(rs.getFloat("medidaY"));
                com.setZ(rs.getFloat("medidaZ"));

                com.setExplosivo(rs.getString("explosivo"));
                com.setCorte(rs.getString("cortes"));
                com.setFoto(rs.getString("foto"));
                com.setHayMateriales(rs.getString("resultado"));
                com.setFecha(rs.getDate("actualizacion"));
                com.setObservaciones(rs.getString("observaciones"));
                com.setNota(rs.getString("notas"));
                com.setPrueva(rs.getBoolean("prueba"));
                lista.add(com);
            }
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("obtenerComponentes " + sql);
            System.out.println("obtenerComponentes " + e);
            Logger.getLogger(SQLMUEBLES.class.getName()).log(Level.SEVERE, null, e);
        }
        return lista;
    }
    public COMPONENTE obtenerComponenteId(int id){
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM zurich.componentes WHERE id="+id+";";
         COMPONENTE com=null;
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                com=new COMPONENTE();
                com.setId(rs.getInt("id"));
                com.setNombre(rs.getString("nombre"));
                com.setModelo(rs.getString("modelo"));
                com.setX(rs.getFloat("medidaX"));
                com.setY(rs.getFloat("medidaY"));
                com.setZ(rs.getFloat("medidaZ"));
                
                com.setExplosivo(rs.getString("explosivo"));
                com.setCorte(rs.getString("cortes"));
                com.setFoto(rs.getString("foto"));
                
                com.setFecha(rs.getDate("actualizacion"));
                com.setObservaciones(rs.getString("observaciones"));
                com.setNota(rs.getString("notas"));
                com.setPrueva(rs.getBoolean("prueba"));
            }
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("obtenerComponenteId " + sql);
            System.out.println("obtenerComponenteId " + ex);
            Logger.getLogger(SQLMUEBLES.class.getName()).log(Level.SEVERE, null, ex);
        }
        return com;
    }

    public void guardarComponente(COMPONENTE com) {
        PreparedStatement ps = null;
        String sql = null;
        String observaciones = com.getObservaciones().replaceAll("^\\s*", "");
        observaciones = observaciones.replaceAll("\\s*$", "");
        observaciones = observaciones.toUpperCase();

        String nombre = com.getNombre().replaceAll("^\\s*", "");
        nombre = nombre.replaceAll("\\s*$", "");
        nombre = nombre.toUpperCase();

        com.setNota(com.getNota().replaceAll("^\\s*", ""));
        com.setNota(com.getNota().replaceAll("\\s*$", ""));
        com.setNota(com.getNota().toUpperCase());
        if (com.getId() > 0) {//cambiar
            sql = "UPDATE zurich.componentes SET medidaX=?,medidaY=?,medidaZ=?,explosivo=?,cortes=?,actualizacion=NOW(),observaciones=?,notas=?,prueba=?,foto=? WHERE id=?;";
            try {
                PRINCIPAL.validarCon();
                Connection con = PRINCIPAL.dataSource.getConnection();
                ps = con.prepareStatement(sql);
                ps.setFloat(1, com.getX());
                ps.setFloat(2, com.getY());
                ps.setFloat(3, com.getZ());
                ps.setString(4, com.getExplosivo());
                ps.setString(5, com.getCorte());
                ps.setString(6, observaciones);
                ps.setString(7, com.getNota());
                ps.setBoolean(8, com.getPrueva());
                ps.setString(9, com.getFoto());
                ps.setInt(10, com.getId());
                ps.execute();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("guardarComponente1 " + sql);
                System.out.println("guardarComponente1 " + e);
                Logger.getLogger(SQLMUEBLES.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            sql = "INSERT INTO zurich.componentes (nombre,modelo,medidaX,medidaY,medidaZ,explosivo,cortes,actualizacion,observaciones,notas,prueba,foto) values(?,?,?,?,?,?,?,now(),?,?,?,?);";
            try {
                PRINCIPAL.validarCon();
                Connection con = PRINCIPAL.dataSource.getConnection();
                ps = con.prepareStatement(sql);
                ps.setString(1, nombre);
                ps.setString(2, com.getModelo());
                ps.setFloat(3, com.getX());
                ps.setFloat(4, com.getY());
                ps.setFloat(5, com.getZ());
                ps.setString(6, com.getExplosivo());
                ps.setString(7, com.getCorte());
                ps.setString(8, observaciones);
                ps.setString(9, com.getNota());
                ps.setBoolean(10, com.getPrueva());
                ps.setString(11, com.getFoto());
                ps.execute();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("guardarComponente2 " + sql);
                System.out.println("guardarComponente2 " + e);
                Logger.getLogger(SQLMUEBLES.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public int obtenerIdNuevoComponente() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int folio = 0;
        String sql = "select id from zurich.componentes order by id desc limit 1;";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                folio = rs.getInt("id");
            }
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLMUEBLES.class.getName()).log(Level.SEVERE, null, ex);
        }
        return folio;
    }

    public void guardarMaterialUsado(material mat, int idComponente) {
        PreparedStatement ps = null;
        String sql = null;
        
        if (mat.getPieza() != null) {
            mat.setPieza(mat.getPieza().replaceAll("^\\s*", ""));
            mat.setPieza(mat.getPieza().replaceAll("\\s*$", ""));
            mat.setPieza(mat.getPieza().toUpperCase());
            if(mat.getPieza().length() == 0){
                mat.setPieza(null);
            }
        }

        if (mat.getDescripcion() != null) {
            mat.setDescripcion(mat.getDescripcion().replaceAll("^\\s*", ""));
            mat.setDescripcion(mat.getDescripcion().replaceAll("\\s*$", ""));
            mat.setDescripcion(mat.getDescripcion().toUpperCase());
            if (mat.getDescripcion().length() == 0) {
                mat.setDescripcion(null);
            }
        }

        if (mat.getId() > 0) {
            sql = "UPDATE zurich.materialusadonuevo SET pieza=?, descripcion=?, "
                    + "idMaterial=?, medX=?, medY=?, medZ=?, cantidad=?,materialTipo=?"
                    + ",parte=?,cnc=? WHERE id=?";
            try {
                PRINCIPAL.validarCon();
                Connection con = PRINCIPAL.dataSource.getConnection();
                ps = con.prepareStatement(sql);
                ps.setString(1, mat.getPieza());
                ps.setString(2, mat.getDescripcion());
                ps.setString(3, mat.getIdMaterial());
                ps.setFloat(4, mat.getMedidaX());
                ps.setFloat(5, mat.getMedidaY());
                ps.setFloat(6, mat.getMedidaZ());
                ps.setFloat(7, mat.getCantidad());
                ps.setString(8, mat.getTipo());
                if (mat.getParte().equals("GENERAL")) {
                    ps.setString(9, null);
                } else {
                    ps.setString(9, mat.getParte());
                }
                ps.setBoolean(10, mat.getCnc());
                ps.setInt(11, mat.getId());
                ps.execute();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("guardarMaterialUsado1 " + sql);
                System.out.println("guardarMaterialUsado1 " + e);
                Logger.getLogger(SQLMUEBLES.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            sql = "INSERT INTO zurich.materialusadonuevo(componente,modelo,pieza, "
                    + "descripcion,idMaterial, medX,medY,medZ,cantidad,materialTipo,parte,idComponente,cnc) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try {
                PRINCIPAL.validarCon();
                Connection con = PRINCIPAL.dataSource.getConnection();
                ps = con.prepareStatement(sql);
                ps.setString(1, mat.getComponente());
                ps.setString(2, mat.getModelo());
                ps.setString(3, mat.getPieza());
                ps.setString(4, mat.getDescripcion());
                ps.setString(5, mat.getIdMaterial());
                ps.setFloat(6, mat.getMedidaX());
                ps.setFloat(7, mat.getMedidaY());
                ps.setFloat(8, mat.getMedidaZ());
                ps.setFloat(9, mat.getCantidad());
                ps.setString(10, mat.getTipo());
                if (mat.getParte().equals("GENERAL")) {
                    ps.setString(11, null);
                } else {
                    ps.setString(11, mat.getParte());
                }
                if (mat.getIdcomponente() < 0) {
                    ps.setInt(12, idComponente);
                } else {
                    ps.setInt(12, mat.getIdcomponente());
                }
                ps.setBoolean(13, mat.getCnc());
                ps.execute();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("guardarMaterialUsado2 " + sql);
                System.out.println("guardarMaterialUsado2 " + e);
                Logger.getLogger(SQLMUEBLES.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    /*public void guardarMaterialUsado(ArrayList<material> lista, COMPONENTE comp) {
        PreparedStatement ps = null;
        String sqlCom = null;
        for (material mat : lista) {
            String sql = "";
            
            if (mat.getPieza() != null) {
                mat.setPieza(mat.getPieza().replaceAll("^\\s*", ""));
                mat.setPieza(mat.getPieza().replaceAll("\\s*$", ""));
                mat.setPieza(mat.getPieza().toUpperCase());
                
                mat.setPieza("'"+mat.getPieza()+"'");
            }
            if (mat.getPieza().length() ==0||mat.getPieza() == null||mat.getPieza().equals("''") ) {
                mat.setPieza("NULL");
            }

            if (mat.getDescripcion() != null) {
                mat.setDescripcion(mat.getDescripcion().replaceAll("^\\s*", ""));
                mat.setDescripcion(mat.getDescripcion().replaceAll("\\s*$", ""));
                mat.setDescripcion(mat.getDescripcion().toUpperCase());
                mat.setDescripcion("'"+mat.getDescripcion()+"'");
                if (mat.getDescripcion().length() == 0) {
                    mat.setDescripcion("NULL");
                }
            }else{
                mat.setDescripcion("NULL");
            }
            
            if (mat.getId() > 0) {
                sql = sql+"UPDATE zurich.materialusadonuevo SET pieza=" + mat.getPieza() + ", "
                        + "descripcion=" + mat.getDescripcion() + ", idMaterial=" + mat.getIdMaterial() + ", "
                        + "medX=" + mat.getMedidaX() + ", medY=" + mat.getMedidaY() + ", "
                        + "medZ=" + mat.getMedidaZ() + ", cantidad=" + mat.getCantidad()
                        + ",materialTipo='" + mat.getTipo() + "',parte=";
                if (mat.getParte().equals("GENERAL")) {
                    sql = sql + "NULL";
                } else {
                    sql = sql + "'" + mat.getParte() + "'";
                }
                sql = sql + " cnc="+mat.getCnc()+" WHERE id=" + mat.getId() + ";";
                
            } else {//nuevo
                sql = sql+"INSERT INTO zurich.materialusadonuevo("
                        + "componente,modelo,pieza,descripcion,idMaterial, medX,"
                        + "medY,medZ,cantidad,materialTipo,parte,idComponente,cnc) "
                        + "VALUES('" + comp.getNombre() + "','" + comp.getModelo() + "'," + mat.getPieza() + ","
                        + "" + mat.getDescripcion() + "," + mat.getIdMaterial() + ","
                        + "'" + mat.getMedidaX() + "','" + mat.getMedidaY() + "','" + mat.getMedidaZ() + "'"
                        + ",'" + mat.getCantidad() + "','" + mat.getTipo() + "','";
                if (mat.getParte().equals("GENERAL")) {
                    sql = sql + "NULL";
                } else {
                    sql = sql + "'" + mat.getParte() + "'";
                }
                sql = sql + "," + comp.getId() + "', "+mat.getCnc()+ ");";

            }
            sqlCom = sqlCom + "\n" + sql;
        } 

        //System.out.println(sqlCom);
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sqlCom);
            ps.execute();
            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("guardarMaterialUsadoLista " + sqlCom);
            System.out.println("guardarMaterialUsadoLista " + e);
            Logger.getLogger(SQLMUEBLES.class.getName()).log(Level.SEVERE, null, e);
        }
    }*/

    /*public ArrayList<material> obtenerMaterialComponente(int idComponente) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<material> lista = new ArrayList();
        String sql = "SELECT * FROM zurich.materialusadonuevo WHERE idComponente=? ORDER BY pieza ASC, materialTipo ASC;";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idComponente);
            rs = ps.executeQuery();
            while (rs.next()) {
                material mat = new material();
                mat.setId(rs.getInt("id"));
                mat.setModelo(rs.getString("modelo"));
                mat.setComponente(rs.getString("componente"));

                if (rs.getString("pieza") == null) {
                    mat.setPieza("");
                } else {
                    mat.setPieza(rs.getString("pieza"));
                }
                mat.setDescripcion(rs.getString("descripcion"));
                mat.setIdMaterial(rs.getString("idMaterial"));
                mat.setMedidaX(rs.getFloat("medX"));
                mat.setMedidaY(rs.getFloat("medY"));
                mat.setMedidaZ(rs.getFloat("medZ"));
                mat.setCantidad(rs.getFloat("cantidad"));
                mat.setTipo(rs.getString("materialTipo"));
                mat.setIdcomponente(rs.getInt("idComponente"));
                if (rs.getString("parte") != null) {
                    mat.setParte(rs.getString("parte"));
                } else {
                    mat.setParte("GENERAL");
                }
                mat.setCnc(rs.getBoolean("cnc"));
                lista.add(mat);
            }
            ps.close();
            con.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("obtenerMaterialComponente " + sql);
            System.out.println("obtenerMaterialComponente " + e);
            Logger.getLogger(SQLMUEBLES.class.getName()).log(Level.SEVERE, null, e);
        }
        return lista;
    }*/
    public ArrayList<material> obtenerMaterialComponente(int idComponente) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<material> lista = new ArrayList();
        String sql = "SELECT materialusadonuevo.`id` AS id,"
                + "almacen.`nombre` AS material,"
                + "almacen.`tipo` AS tipo,"
                + "materialusadonuevo.`pieza` AS pieza,"
                + "materialusadonuevo.`descripcion` AS descripcion,"
                + "materialusadonuevo.`idMaterial` AS idMaterial,"
                + "materialusadonuevo.`medX` AS medX,"
                + "materialusadonuevo.`medY` AS medY,"
                + "materialusadonuevo.`medZ` AS medZ,"
                + "materialusadonuevo.`cantidad` AS cantidad,"
                + "materialusadonuevo.`parte` AS parte,"
                + "materialusadonuevo.`cnc` AS cnc "
                + "FROM `materialusadonuevo` materialusadonuevo "
                + "INNER JOIN `componentes` componentes ON materialusadonuevo.`idComponente` = componentes.`id`"
                + "INNER JOIN `almacen` almacen ON materialusadonuevo.`idMaterial` = almacen.`id`"
                + "WHERE materialusadonuevo.`idComponente` = "+idComponente+" "
                + "ORDER BY pieza ASC, materialTipo ASC;";
        
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                material mat = new material();
                mat.setId(rs.getInt("id"));
                mat.setIdMaterial(rs.getString("idmaterial"));
                mat.setMaterial(rs.getString("material"));
                mat.setTipo(rs.getString("tipo"));
                mat.setPieza(rs.getString("pieza"));
                mat.setDescripcion(rs.getString("descripcion"));
                mat.setIdMaterial(rs.getString("idmaterial"));
                mat.setMedidaX(rs.getFloat("medX"));
                mat.setMedidaY(rs.getFloat("medY"));
                mat.setMedidaZ(rs.getFloat("medZ"));
                mat.setCantidad(rs.getFloat("cantidad"));
                if (rs.getString("parte") != null) {
                    mat.setParte(rs.getString("parte"));
                } else {
                    mat.setParte("GENERAL");
                }
                mat.setCnc(rs.getBoolean("cnc"));
                lista.add(mat);
            }
            sql = "SELECT materialusadonuevo.`id` AS id,\n"
                    + "materialusadonuevo.`idMaterial` AS idMaterial,\n"
                    + "elementosarea.`area` as tipo,\n"
                    + "materialusadonuevo.`pieza` AS pieza,\n"
                    + "materialusadonuevo.`descripcion` AS descripcion,\n"
                    + "materialusadonuevo.`medX` AS medX,\n"
                    + "materialusadonuevo.`medY` AS medY,\n"
                    + "materialusadonuevo.`medZ` AS medZ,\n"
                    + "materialusadonuevo.`cantidad` AS cantidad,\n"
                    + "materialusadonuevo.`parte` AS parte,\n"
                    + "materialusadonuevo.`cnc` AS cnc\n"
                    + "FROM `materialusadonuevo` materialusadonuevo \n"
                    + "INNER JOIN `elementosarea` elementosarea ON materialusadonuevo.`idMaterial` = elementosarea.`elemento`\n"
                    + "WHERE materialusadonuevo.`idComponente` ="+idComponente+"\n"
                    + "ORDER BY materialusadonuevo.`pieza` ASC;";
                    
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                material mat = new material();
                mat.setId(rs.getInt("id"));
                mat.setIdMaterial(rs.getString("idmaterial"));
                mat.setMaterial(rs.getString("idmaterial"));
                mat.setTipo(rs.getString("tipo"));
                mat.setPieza(rs.getString("pieza"));
                mat.setDescripcion(rs.getString("descripcion"));
                mat.setIdMaterial(rs.getString("idmaterial"));
                mat.setMedidaX(rs.getFloat("medX"));
                mat.setMedidaY(rs.getFloat("medY"));
                mat.setMedidaZ(rs.getFloat("medZ"));
                mat.setCantidad(rs.getFloat("cantidad"));
                if (rs.getString("parte") != null) {
                    mat.setParte(rs.getString("parte"));
                } else {
                    mat.setParte("GENERAL");
                }
                mat.setCnc(rs.getBoolean("cnc"));
                lista.add(mat);
            }
            
            
        } catch (SQLException e) {
            System.out.println("obtenerMaterialComponente " + sql);
            System.out.println("obtenerMaterialComponente " + e);
            Logger.getLogger(SQLMUEBLES.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return lista;
    }

    public ArrayList<COMPONENTE> obtenerRutasExplosivo(String modelo) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<COMPONENTE> lista = new ArrayList();
        String sql = "SELECT * FROM zurich.componentes WHERE explosivo IS not NULL AND modelo=?;";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, modelo);
            rs = ps.executeQuery();
            while (rs.next()) {
                COMPONENTE com = new COMPONENTE();
                com.setNombre(rs.getString("nombre"));
                com.setExplosivo(rs.getString("explosivo"));
                lista.add(com);
            }
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("obtenerRutaExplosivo " + sql);
            System.out.println("obtenerRutaExplosivo " + e);
            Logger.getLogger(SQLMUEBLES.class.getName()).log(Level.SEVERE, null, e);
        }
        return lista;
    }
     public ArrayList<COMPONENTE> obtenerRutasCorte(String modelo) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<COMPONENTE> lista = new ArrayList();
        String sql = "SELECT * FROM zurich.componentes WHERE cortes IS not NULL AND modelo=?";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, modelo);
            rs = ps.executeQuery();
            while (rs.next()) {
                COMPONENTE com = new COMPONENTE();
                com.setNombre(rs.getString("nombre"));
                com.setExplosivo(rs.getString("cortes"));
                lista.add(com);

            }
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("obtenerRutaCorte " + sql);
            System.out.println("obtenerRutaCorte " + e);
            Logger.getLogger(SQLMUEBLES.class.getName()).log(Level.SEVERE, null, e);
        }

        return lista;
    }

    public void borrarMateriales(int id) {
        PreparedStatement ps = null;
        String sql = "DELETE FROM zurich.materialusadonuevo WHERE id=?;";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            ps.close();
            con.close();

        } catch (Exception e) {
            System.out.println("borrarMateriales " + sql);
            System.out.println("borrarMateriales " + e);
            Logger.getLogger(SQLMUEBLES.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /*----------------------------colores----------------------*/
    public ArrayList<ESTILOS> obtenerEstilos() {
        SQLALMACEN modAlmacen = new SQLALMACEN();
        ArrayList<ESTILOS> lista = new ArrayList();
        ArrayList<material> almacen = modAlmacen.obtenerMaterialesAgregarColores();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM zurich.colores;";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ESTILOS es = new ESTILOS();
                es.setNombre(rs.getString("nombre"));
                es.setIntFondo(rs.getInt("idFondo"));
                es.setIntLaca(rs.getInt("idLaca"));
                es.setIntMancha(rs.getInt("idMancha"));
                es.setIntTono(rs.getInt("idTono"));
                es.setIntChapa(rs.getInt("idChapa"));
                lista.add(es);
            }
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("obtenerColores() " + sql);
            System.out.println("obtenerColores() " + e);
            Logger.getLogger(SQLMUEBLES.class.getName()).log(Level.SEVERE, null, e);
        }
        for (ESTILOS es : lista) {
            for (material mat : almacen) {
                if (mat.getIdMaterial().equals(String.valueOf(es.getIntFondo()))) {
                    es.setFondo(mat.getMaterial());
                }
                if (mat.getIdMaterial().equals(String.valueOf(es.getIntLaca()))) {
                    es.setLaca(mat.getMaterial());
                }
                if (mat.getIdMaterial().equals(String.valueOf(es.getIntMancha()))) {
                    es.setMancha(mat.getMaterial());
                }
                if (mat.getIdMaterial().equals(String.valueOf(es.getIntTono()))) {
                    es.setTono(mat.getMaterial());
                }
                if (mat.getIdMaterial().equals(String.valueOf(es.getIntChapa()))) {
                    es.setChapa(mat.getMaterial());
                }
            }
        }
        return lista;
    }

    public ESTILOS obtenerEstilo(String nombre) {
        SQLALMACEN modAlmacen = new SQLALMACEN();
        ArrayList<material> almacen = modAlmacen.obtenerMaterialesAgregarColores();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ESTILOS es = null;
        String sql = "SELECT * FROM zurich.colores where nombre='" + nombre + "';";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                es = new ESTILOS();
                es.setNombre(rs.getString("nombre"));
                es.setIntFondo(rs.getInt("idFondo"));
                es.setIntLaca(rs.getInt("idLaca"));
                es.setIntMancha(rs.getInt("idMancha"));
                es.setIntTono(rs.getInt("idTono"));
                es.setIntChapa(rs.getInt("idChapa"));
            }
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("obtenerColores() " + sql);
            System.out.println("obtenerColores() " + e);
            Logger.getLogger(SQLMUEBLES.class.getName()).log(Level.SEVERE, null, e);
        }

        for (material mat : almacen) {
            if (mat.getIdMaterial().equals(String.valueOf(es.getIntFondo()))) {
                es.setFondo(mat.getMaterial());
            }
            if (mat.getIdMaterial().equals(String.valueOf(es.getIntLaca()))) {
                es.setLaca(mat.getMaterial());
            }
            if (mat.getIdMaterial().equals(String.valueOf(es.getIntMancha()))) {
                es.setMancha(mat.getMaterial());
            }
            if (mat.getIdMaterial().equals(String.valueOf(es.getIntTono()))) {
                es.setTono(mat.getMaterial());
            }
            if (mat.getIdMaterial().equals(String.valueOf(es.getIntChapa()))) {
                es.setChapa(mat.getMaterial());
            }
        }

        return es;
    }

    public void guardarEstilo(ESTILOS es) {
        PreparedStatement ps = null;
        es.setNombre(es.getNombre().replaceAll("^\\s*", ""));
        es.setNombre(es.getNombre().replaceAll("\\s*$", ""));
        es.setNombre(es.getNombre().toUpperCase());
        String sql = "INSERT INTO zurich.colores (nombre,idFondo,idLaca,idMancha,idTono,idChapa) "
                + "VALUES(?,?,?,?,?,?) ON DUPLICATE KEY UPDATE "
                + "idFondo=VALUES(idFondo),idLaca=VALUES(idLaca),"
                + "idMancha=VALUES(idMancha),idTono=VALUES(idTono),idChapa=VALUES(idChapa);";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, es.getNombre().toUpperCase());
            ps.setInt(2, es.getIntFondo());
            ps.setInt(3, es.getIntLaca());
            ps.setInt(4, es.getIntMancha());
            ps.setInt(5, es.getIntTono());
            ps.setInt(6, es.getIntChapa());
            ps.execute();
            ps.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("guardarColores() " + sql);
            System.out.println("guardarColores() " + e);
            Logger.getLogger(SQLMUEBLES.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /*------------------------------------tapizados-----------------------------*/
    public void guardarTapizados(ESTILOS es) {
        PreparedStatement ps = null;
        es.setNombre(es.getNombre().replaceAll("^\\s*", ""));
        es.setNombre(es.getNombre().replaceAll("\\s*$", ""));
        es.setNombre(es.getNombre().toUpperCase());
        String sql = "INSERT INTO zurich.tapizados (nombre,idForro,idHilo,idTela,idVinil) "
                + "VALUES(?,?,?,?,?) ON DUPLICATE KEY UPDATE "
                + "idForro=VALUES(idForro),idHilo=VALUES(idHilo),"
                + "idTela=VALUES(idTela),idVinil=VALUES(idVinil);";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, es.getNombre().toUpperCase());
            ps.setInt(2, es.getIntForro());
            ps.setInt(3, es.getIntHilo());
            ps.setInt(4, es.getIntTela());
            ps.setInt(5, es.getIntVinil());
            ps.execute();
            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println("guardartapizados() " + sql);
            System.out.println("guardartapizados() " + e);
            Logger.getLogger(SQLMUEBLES.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public ArrayList<ESTILOS> obtenerTapizados() {
        SQLALMACEN modAlmacen = new SQLALMACEN();
        ArrayList<ESTILOS> lista = new ArrayList();
        ArrayList<material> almacen = modAlmacen.obtenerTodoMateriales();
        PreparedStatement ps = null;

        ResultSet rs = null;
        String sql = "SELECT * FROM zurich.tapizados;";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ESTILOS es = new ESTILOS();
                es.setNombre(rs.getString("nombre"));
                es.setIntForro(rs.getInt("idForro"));
                es.setIntHilo(rs.getInt("idHilo"));
                es.setIntTela(rs.getInt("idTela"));
                es.setIntVinil(rs.getInt("idvinil"));
                lista.add(es);
            }
            ps.close();
            con.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("obtenerColores() " + sql);
            System.out.println("obtenerColores() " + e);
            Logger.getLogger(SQLMUEBLES.class.getName()).log(Level.SEVERE, null, e);
        }
        for (ESTILOS es : lista) {
            for (material mat : almacen) {
                if (mat.getIdMaterial().equals(String.valueOf(es.getIntForro()))) {
                    es.setForro(mat.getMaterial());
                }
                if (mat.getIdMaterial().equals(String.valueOf(es.getIntHilo()))) {
                    es.setHilo(mat.getMaterial());
                }
                if (mat.getIdMaterial().equals(String.valueOf(es.getIntTela()))) {
                    es.setTela(mat.getMaterial());
                }
                if (mat.getIdMaterial().equals(String.valueOf(es.getIntVinil()))) {
                    es.setVinil(mat.getMaterial());
                }
            }
        }
        return lista;
    }

    public ESTILOS obtenerTapiz(String nombre) {
        SQLALMACEN modAlmacen = new SQLALMACEN();
        ArrayList<material> almacen = modAlmacen.obtenerMaterialesTipo("TAPIZADO");
        PreparedStatement ps = null;
        ResultSet rs = null;
        ESTILOS es = null;
        String sql = "SELECT * FROM zurich.tapizados where nombre='" + nombre + "';";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            es = new ESTILOS();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                es.setNombre(rs.getString("nombre"));
                es.setIntForro(rs.getInt("idForro"));
                es.setIntHilo(rs.getInt("idHilo"));
                es.setIntTela(rs.getInt("idTela"));
                es.setIntVinil(rs.getInt("idVinil"));
            }
            ps.close();
            con.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("obtenerColores() " + sql);
            System.out.println("obtenerColores() " + e);
            Logger.getLogger(SQLMUEBLES.class.getName()).log(Level.SEVERE, null, e);
        }
        for (material mat : almacen) {
            if (mat.getIdMaterial().equals(String.valueOf(es.getIntForro()))) {
                es.setForro(mat.getMaterial());
            }
            if (mat.getIdMaterial().equals(String.valueOf(es.getIntHilo()))) {
                es.setHilo(mat.getMaterial());
            }
            if (mat.getIdMaterial().equals(String.valueOf(es.getIntTela()))) {
                es.setTela(mat.getMaterial());
            }
            if (mat.getIdMaterial().equals(String.valueOf(es.getIntVinil()))) {
                es.setVinil(mat.getMaterial());
            }
        }
        return es;
    }
    
    public ArrayList<COMPONENTE> obtenerPruevas() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<COMPONENTE> lista = new ArrayList();
        String sql = "SELECT * FROM zurich.componentes where prueba=1 ORDER BY modelo ASC, nombre ASC;";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                COMPONENTE com = new COMPONENTE();
                com.setId(rs.getInt("id"));
                com.setNombre(rs.getString("nombre"));
                com.setModelo(rs.getString("modelo"));
                com.setX(rs.getFloat("medidaX"));
                com.setY(rs.getFloat("medidaY"));
                com.setZ(rs.getFloat("medidaZ"));
                com.setExplosivo(rs.getString("explosivo"));
                com.setFecha(rs.getDate("actualizacion"));
                com.setObservaciones(rs.getString("observaciones"));
                com.setNota(rs.getString("notas"));
                com.setPrueva(rs.getBoolean("prueba"));
                lista.add(com);
            }
            ps.close();
            con.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("obtenerComponentes " + sql);
            System.out.println("obtenerComponentes " + e);
            Logger.getLogger(SQLMUEBLES.class.getName()).log(Level.SEVERE, null, e);
        }
        return lista;
    }
    public boolean guardarInventario(COMPONENTE com) {
        PreparedStatement ps = null;
        String sql = "UPDATE zurich.componentes SET tienda=?,piso=?,dañado=? WHERE ID=?";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, com.getTienda());
            ps.setInt(2, com.getPiso());
            ps.setInt(3, com.getDañado());
            ps.setInt(4, com.getId());
            ps.execute();
            ps.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SQLMUEBLES.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("guardarInventario() " + sql);
            System.out.println("guardarInventario() " + ex);
            return false;
        }
    }
}
