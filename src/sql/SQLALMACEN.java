package sql;

import CLASES.AREAS;
import CLASES.USUARIO;
import CLASES.material;
import GRAFICA.PRINCIPAL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLALMACEN {

    public ArrayList ObtenerTiposMaterial() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList lista = new ArrayList();
        String sql = "select * from zurich.tiposmaterial";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            String resul = null;
            while (rs.next()) {
                ArrayList lista2 = new ArrayList();
                if (rs.getInt("visible") == 1) {
                    resul = "Si";
                } else {
                    resul = "No";
                }
                lista2.add(rs.getString("nombre"));
                lista2.add(resul);
                lista.add(lista2);
            }
            rs.close();
            ps.cancel();
            con.close();
        } catch (SQLException e) {
            System.out.println("ObtenerTiposMaterial()" + sql);
            System.out.println("ObtenerTiposMaterial()" + e);
            Logger.getLogger(SQLALMACEN.class.getName()).log(Level.SEVERE, null, e);
        }
        return lista;
    }

    public void GuardarTiposMaterial(String nombre, boolean visible) {
        PreparedStatement ps = null;
        nombre = nombre.replaceAll("^\\s*", "");
        nombre = nombre.replaceAll("\\s*$", "");
        String sql = "INSERT INTO zurich.tiposmaterial(nombre,visible)"
                + "VALUES(?,?) ON DUPLICATE KEY UPDATE visible=VALUES(visible);";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre.toUpperCase());
            ps.setBoolean(2, visible);
            ps.execute();
            con.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("GuardarTiposMaterial " + sql);
            System.out.println("GuardarTiposMaterial " + e);
            Logger.getLogger(SQLALMACEN.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public ArrayList<material> obtenerTodoMateriales() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<material> lista = new ArrayList();
        String sql = "SELECT * from zurich.almacen ORDER BY tipo ASC,nombre ASC;";
        try {
            System.out.println("sql obtenerTodoMateriales() pasa 1");
            PRINCIPAL.validarCon();
            System.out.println("sql obtenerTodoMateriales() pasa 2");
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                material mat = new material();
                mat.setIdMaterial(rs.getString("id"));
                mat.setMaterial(rs.getString("nombre"));
                mat.setTipo(rs.getString("tipo"));
                mat.setPrecioUnidad(-1);
                mat.setPrecioPaquete(rs.getFloat("precio"));
                mat.setFecha(rs.getDate("actualizacion"));
                mat.setCantidad(rs.getFloat("existencias"));
                mat.setMedidaX(rs.getFloat("medX"));
                mat.setMedidaY(rs.getFloat("medY"));
                mat.setPaquete(rs.getFloat("paquete"));
                mat.setMedidaZ(rs.getInt("visible"));
                mat.setMinimo(rs.getFloat("minimo"));
                mat.setUnidades(rs.getString("unidades"));
                mat.setEmpaque(rs.getString("empaque"));
                lista.add(mat);
            }
            ps.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("obtenerTodoMateriales " + sql);
            System.out.println("obtenerTodoMateriales " + e);
            Logger.getLogger(SQLALMACEN.class.getName()).log(Level.SEVERE, null, e);
        }
        return lista;
    }

    public ArrayList<AREAS> obtenerElementosArea() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<AREAS> lista = new ArrayList();
        String sql = "select * from zurich.elementosarea ORDER BY elemento ASC";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                AREAS area = new AREAS();
                area.setArea(rs.getString("area"));
                area.setElemento(rs.getString("elemento"));
                area.setPrecio(rs.getFloat("precioProm"));
                lista.add(area);
            }
            con.close();
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("obtenerElementosArea " + sql);
            System.out.println("obtenerElementosArea " + e);
            Logger.getLogger(SQLALMACEN.class.getName()).log(Level.SEVERE, null, e);
        }
        return lista;
    }

    public ArrayList<material> obtenerMaterialesFiltros() {
        System.out.println("++++++++iniciando sql obtenerMaterialesFiltros()++++++++");
        PreparedStatement ps = null;
        System.out.println("sql pasa 1");
        ResultSet rs = null;
        System.out.println("sql pasa 2");
        ArrayList<material> lista = new ArrayList();
        System.out.println("sql pasa 3");
        String sql = "SELECT * FROM zurich.almacen WHERE visible=1 ORDER BY tipo asc, nombre ASC;";
        
        try {
            System.out.println("entra try");
            PRINCIPAL.validarCon();
            System.out.println("sql pasa 4");
            Connection con = PRINCIPAL.dataSource.getConnection();
            System.out.println("sql pasa 5");
            ps = con.prepareStatement(sql);
            System.out.println("sql pasa 6");
            rs = ps.executeQuery();
            System.out.println("sql pasa 7");
            while (rs.next()) {
                material mat = new material();
                mat.setIdMaterial(String.valueOf(rs.getInt("id")));
                mat.setMaterial(rs.getString("nombre"));
                mat.setTipo(rs.getString("tipo"));
                mat.setPrecioPaquete(rs.getFloat("precio"));
                mat.setFecha(rs.getDate("actualizacion"));
                mat.setCantidad(rs.getFloat("existencias"));
                mat.setMedidaX(rs.getFloat("medX"));
                mat.setMedidaY(rs.getFloat("medY"));
                mat.setPaquete(rs.getFloat("existencias"));
                mat.setMedidaZ(rs.getInt("visible"));
                mat.setUnidades(rs.getString("unidades"));
                mat.setEmpaque(rs.getString("empaque"));
                lista.add(mat);
            }
            
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("obtenerTodoMateriales " + sql);
            System.out.println("obtenerTodoMateriales " + e);
            Logger.getLogger(SQLALMACEN.class.getName()).log(Level.SEVERE, null, e);
        }
        
        System.out.println("++++++++fin obtenerMaterialesFiltros() "+lista.size()+"+++++++++");
        return lista;
    }

    public ArrayList<material> obtenerMaterialesTipo(String tipo) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<material> lista = new ArrayList();
        String sql = "SELECT * FROM zurich.almacen WHERE tipo='" + tipo + "' ORDER BY nombre ASC ;";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                material mat = new material();
                mat.setIdMaterial(String.valueOf(rs.getInt("id")));
                mat.setMaterial(rs.getString("nombre"));
                mat.setTipo(rs.getString("tipo"));
                mat.setPrecioPaquete(rs.getFloat("precio"));
                mat.setFecha(rs.getDate("actualizacion"));
                mat.setCantidad(rs.getFloat("existencias"));
                mat.setMedidaX(rs.getFloat("medX"));
                mat.setMedidaY(rs.getFloat("medY"));
                mat.setPaquete(rs.getFloat("existencias"));
                mat.setMedidaZ(rs.getInt("visible"));
                lista.add(mat);
            }
            con.close();
            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println("obtenerMaterialesTipo " + sql);
            System.out.println("obtenerMaterialesTipo " + e);
            Logger.getLogger(SQLALMACEN.class.getName()).log(Level.SEVERE, null, e);
        }
        return lista;
    }

    public ArrayList<material> obtenerMaterialesAgregarColores() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<material> lista = new ArrayList();
        String sql = "SELECT * FROM zurich.almacen WHERE tipo='pintura' or nombre like '%chapacinta%' ORDER BY nombre ASC;";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                material mat = new material();
                mat.setIdMaterial(String.valueOf(rs.getInt("id")));
                mat.setMaterial(rs.getString("nombre"));
                mat.setTipo(rs.getString("tipo"));
                mat.setPrecioPaquete(rs.getFloat("precio"));
                mat.setFecha(rs.getDate("actualizacion"));
                mat.setCantidad(rs.getFloat("existencias"));
                mat.setMedidaX(rs.getFloat("medX"));
                mat.setMedidaY(rs.getFloat("medY"));
                mat.setPaquete(rs.getFloat("existencias"));
                mat.setMedidaZ(rs.getInt("visible"));
                lista.add(mat);
            }
            con.close();
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("obtenerMaterialesTipo " + sql);
            System.out.println("obtenerMaterialesTipo " + e);
            Logger.getLogger(SQLALMACEN.class.getName()).log(Level.SEVERE, null, e);
        }
        return lista;
    }

    public void GuardarMaterial(material mat) {
        PreparedStatement ps = null;
        String sql = null;
        mat.setMaterial(mat.getMaterial().replaceAll("^\\s*", ""));
        mat.setMaterial(mat.getMaterial().replaceAll("\\s*$", ""));
        mat.setMaterial(mat.getMaterial().toUpperCase());
        if (mat.getId() > 0) {
            sql = "UPDATE zurich.almacen SET precio=?, actualizacion=now(),"
                    + "medX=?,medY=?,paquete=?,visible=?,tipo=?,minimo=?,unidades=?,empaque=? WHERE id=?";
            try {
                PRINCIPAL.validarCon();
                Connection con = PRINCIPAL.dataSource.getConnection();
                ps = con.prepareStatement(sql);
                ps.setFloat(1, mat.getPrecioPaquete());
                ps.setFloat(2, mat.getMedidaX());
                ps.setFloat(3, mat.getMedidaY());
                ps.setFloat(4, mat.getPaquete());
                ps.setFloat(5, mat.getMedidaZ());
                ps.setString(6, mat.getTipo());
                ps.setFloat(7, mat.getMinimo());
                ps.setString(8, mat.getUnidades());
                ps.setString(9, mat.getEmpaque());
                ps.setInt(10, mat.getId());
                ps.execute();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("GuardarMaterial " + sql);
                System.out.println("GuardarMaterial " + e);
                Logger.getLogger(SQLALMACEN.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            sql = "INSERT INTO zurich.almacen(nombre,tipo,precio,actualizacion,medX,medY,"
                    + "paquete,visible,unidades,empaque, minimo) "
                    + "VALUES(?,?,?,now(),?,?,?,?,?,?,?);";
            try {
                PRINCIPAL.validarCon();
                Connection con = PRINCIPAL.dataSource.getConnection();
                ps = con.prepareStatement(sql);
                ps.setString(1, mat.getMaterial());
                ps.setString(2, mat.getTipo());
                ps.setFloat(3, mat.getPrecioPaquete());
                ps.setFloat(4, mat.getMedidaX());
                ps.setFloat(5, mat.getMedidaY());
                ps.setFloat(6, mat.getPaquete());
                ps.setFloat(7, mat.getMedidaZ());
                ps.setString(8, mat.getUnidades());
                ps.setString(9, mat.getEmpaque());
                ps.setFloat(10, mat.getMinimo());
                ps.execute();
                con.close();
                ps.close();
            } catch (SQLException e) {
                System.out.println("GuardarMaterial2 " + sql);
                System.out.println("GuardarMaterial2 " + e);
                Logger.getLogger(SQLALMACEN.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public int obtenerFolioAlmacen() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int folio = 0;
        String sql = "select folio from zurich.movimientosalmacen order by id desc limit 1;";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                folio = rs.getInt("folio");
            }
            con.close();
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("obtenerFolioAlmacen() " + sql);
            System.out.println("obtenerFolioAlmacen() " + e);
            Logger.getLogger(SQLALMACEN.class.getName()).log(Level.SEVERE, null, e);
        }
        return folio;
    }

    public int guardarMovimientos(USUARIO usr, ArrayList<material> lista, String cliente, String concepto, int tipo) {
        PreparedStatement ps = null;
        int folio = obtenerFolioAlmacen() + 1;
        boolean respuesta = false;
        concepto = concepto.replaceAll("^\\s*", "");
        concepto = concepto.replaceAll("\\s*$", "");
        concepto = concepto.toUpperCase();

        cliente = cliente.replaceAll("^\\s*", "");
        cliente = cliente.replaceAll("\\s*$", "");
        cliente = cliente.toUpperCase();

        String sql = "INSERT INTO zurich.movimientosalmacen(folio,tipo,cantidad,material,concepto,cliente,autorizacion,fecha) ";
        for (int i = 0; i < lista.size(); i++) {
            material mat = lista.get(i);
            if (i == 0) {
                sql = sql + "VALUES(" + folio + "," + tipo + "," + mat.getMedidaZ() + "," + mat.getIdMaterial() + ",'" + concepto + "','" + cliente + "'," + usr.getId() + ",now())";
            } else {
                sql = sql + ",(" + folio + "," + tipo + "," + mat.getMedidaZ() + "," + mat.getIdMaterial() + ",'" + concepto + "','" + cliente + "'," + usr.getId() + ",now())";
            }
        }
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.execute();
            ps.close();
            con.close();
            if (!modificarAlmacen(lista, tipo)) {
                folio = -1;
            }
        } catch (SQLException ex) {
            folio = -1;
            System.out.println("guardarMovimientos " + sql);
            System.out.println("guardarMovimientos " + ex);
            Logger.getLogger(SQLALMACEN.class.getName()).log(Level.SEVERE, null, ex);
            respuesta = false;
        }
        return folio;
    }

    public boolean modificarAlmacen(ArrayList<material> lista, int tipo) {
        PreparedStatement ps = null;
        String sql = null;
        boolean respuesta = false;
        for (material mat : lista) {
            if (tipo == 0) {
                sql = "UPDATE zurich.almacen SET existencias=existencias-" + mat.getMedidaZ() + " WHERE id=" + mat.getIdMaterial() + ";";
            } else {
                sql = "UPDATE zurich.almacen SET existencias=existencias+" + mat.getMedidaZ() + " WHERE id=" + mat.getIdMaterial() + ";";
            }
            try {
                PRINCIPAL.validarCon();
                Connection con = PRINCIPAL.dataSource.getConnection();
                ps = con.prepareStatement(sql);
                ps.execute();
                ps.close();
                con.close();
                respuesta = true;
            } catch (SQLException ex) {
                System.out.println("modificarAlmacen " + sql);
                System.out.println("modificarAlmacen " + ex);
                Logger.getLogger(SQLALMACEN.class.getName()).log(Level.SEVERE, null, ex);
                respuesta = false;
            }
        }
        return respuesta;
    }

    public ArrayList<material> obtenerMovimientos(String sql) {
        PreparedStatement ps = null;
        //Connection con = getConexion();
        ResultSet rs = null;
        ArrayList<material> lista = new ArrayList();
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                material mat = new material();
                mat.setId(rs.getInt("folio"));
                mat.setTipo(rs.getString("Materialtipo"));
                mat.setCantidad(rs.getInt("cantidad"));
                mat.setIdMaterial(rs.getString("idMaterial"));
                mat.setDescripcion(rs.getString("concepto"));
                mat.setComponente(rs.getString("cliente"));
                Timestamp timestamp = rs.getTimestamp("fecha");
                if (timestamp != null) {
                    mat.setFecha(new java.util.Date(timestamp.getTime()));
                }
                mat.setMaterial(rs.getString("material"));
                mat.setModelo(rs.getString("idUsu"));
                if (rs.getInt("tipo") == 0) {
                    mat.setPieza("Sal");
                } else {
                    mat.setPieza("Entra");
                }
                lista.add(mat);
            }
            ps.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("obtenerMovimientos " + sql);
            System.out.println("obtenerMovimientos " + ex);
            Logger.getLogger(SQLALMACEN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public int otenerFolioOrdenCompra() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int folio = 0;
        String sql = "select folio from zurich.ordenes_compra order by id desc limit 1;";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getInt("folio") > 0) {
                    folio = rs.getInt("folio");
                }
            } else {
                folio = 0;
            }
            con.close();
            ps.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("obtenerFolioAlmacen() " + sql);
            System.out.println("obtenerFolioAlmacen() " + e);
            Logger.getLogger(SQLALMACEN.class.getName()).log(Level.SEVERE, null, e);
        }
        return folio;
    }

    public int guardarOrdenCompra(ArrayList<material> lista, String proveedor) {
        int folio = otenerFolioOrdenCompra() + 1;
        PreparedStatement ps = null;
        String sql = "INSERT INTO zurich.ordenes_compra"
                + "(folio,cantidad,empaque,material,descripcion,tipo,precioUnitario,"
                + "fecha,autorizacion,proveedor,iva)VALUES";
        for (int a = 0; a < lista.size(); a++) {
            material mat = lista.get(a);
            String material = null;
            String descripcion = null;
            float iva = 0;
            if (mat.getMedidaZ() != 0) {
                iva = (float) (mat.getCantidad() * mat.getPrecioPaquete() * 0.16);
            }
            material = mat.getMaterial().replaceAll("^\\s*", "");
            material = material.replaceAll("\\s*$", "");
            material = material.toUpperCase();
            if (mat.getDescripcion() != null) {
                descripcion = mat.getDescripcion().replaceAll("^\\s*", "");
                descripcion = descripcion.replaceAll("\\s*$", "");
                descripcion = descripcion.toUpperCase();
                if (descripcion.length() > 0) {
                    descripcion = "'" + descripcion + "'";
                }
            }
            if (descripcion.length() == 0) {
                descripcion = "null";
            }

            if (a != 0) {
                sql = sql + ",";
            }
            sql = sql + "(" + folio + "," + mat.getCantidad() + ",'" + mat.getEmpaque()
                    + "','" + material + "'," + descripcion + ",'" + mat.getTipo()
                    + "'," + mat.getPrecioPaquete() + ",now()," + PRINCIPAL.user.getId()
                    + ",'" + proveedor.toUpperCase() + "'," + iva + ")";
        }
        try {
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);

            ps.execute();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("guardarOrden " + sql);
            System.out.println("guardarOrden " + ex);
            Logger.getLogger(SQLALMACEN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return folio;
    }

    public ArrayList<material> obtenerReporteFiltro(String mes) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<material> lista = new ArrayList();
        String sql = "SELECT * FROM zurich.ordenes_compra WHERE fecha BETWEEN '" + mes + "-01 00:00:01' "
                + "AND '" + mes + "-31 23:59:59' ORDER BY fecha DESC;";
        PRINCIPAL.validarCon();
        Connection con;
        try {
            con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            int elementos = 0;
            float total = 0;
            material anterior = new material();
            anterior.setId(-1);
            material actual = null;
            while (rs.next()) {
                actual = new material();
                actual.setId(rs.getInt("folio"));
                actual.setFecha(rs.getDate("fecha"));
                actual.setComponente(rs.getString("proveedor"));

                if (anterior.getId() == -1) {
                    anterior.setId(rs.getInt("folio"));
                }
                if (actual.getId() == anterior.getId()) {
                    elementos++;
                    total = total + rs.getFloat("PrecioUnitario") * rs.getFloat("cantidad");
                    anterior = actual;
                } else {
                    anterior.setCantidad(elementos);
                    anterior.setPrecioPaquete(total);

                    lista.add(anterior);
                    elementos = 1;
                    total = rs.getFloat("PrecioUnitario") * rs.getFloat("cantidad");
                    anterior = actual;
                }
            }
            System.out.println("obtenerReporteFiltro " + sql);
        } catch (SQLException ex) {
            System.out.println("obtenerReporteFiltro " + sql);
            System.out.println("obtenerReporteFiltro " + ex);
            Logger.getLogger(SQLALMACEN.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("obtenerReporteFiltro lista" + lista.size());
        return lista;
    }

    public ArrayList<material> ObtenerGenerales() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<material> lista = new ArrayList();

        String sql = "select * from zurich.elementosarea order by area asc;";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                material mat = new material();
                mat.setMaterial(rs.getString("elemento"));
                mat.setTipo(rs.getString("area"));
                mat.setPrecioUnidad(rs.getFloat("precioProm"));
                lista.add(mat);
            }
            rs.close();
            ps.cancel();
            con.close();
        } catch (SQLException e) {
            System.out.println("ObtenerGenerales()" + sql);
            System.out.println("ObtenerGenerales()" + e);
            Logger.getLogger(SQLALMACEN.class.getName()).log(Level.SEVERE, null, e);
        }
        return lista;
    }

    public boolean guardarGeneral(String elemento, float precio) {
        PreparedStatement ps = null;
        boolean respuesta = false;
        String sql = "UPDATE zurich.elementosarea SET precioProm=" + precio + " WHERE elemento='" + elemento + "';";
        try {
            PRINCIPAL.validarCon();
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.execute();
            ps.close();
            con.close();

            respuesta = true;
        } catch (SQLException ex) {
            System.out.println("guardarGeneral " + sql);
            System.out.println("guardarGeneral " + ex);
            Logger.getLogger(SQLALMACEN.class.getName()).log(Level.SEVERE, null, ex);
            respuesta = false;
        }
        return respuesta;
    }

}
