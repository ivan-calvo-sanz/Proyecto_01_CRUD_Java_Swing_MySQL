package DAO;

import Conexion.ConexionMysql;
import Modelo.ProductoModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// ============================================================
// DAO
// La Clase que interacciona con la BBDD
// ============================================================
public class ProductoDAO {

    //private Connection miConexion = ConexionMysql.conectarBBDD();
    private final Connection miConexion;

    public ProductoDAO() {
        this.miConexion = ConexionMysql.conectarBBDD();
    }

    // ---------------- CREATE ----------------
    public boolean registrarProducto(ProductoModelo producto) {

        String sql = "INSERT INTO productos (nombre, codigo, categoria, compra, venta, obsequio, precio, estado) VALUES (?,?,?,?,?,?,?,?)";

        try (PreparedStatement ps = miConexion.prepareStatement(sql)) {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getCodigo());
            ps.setString(3, producto.getCategoria());
            ps.setString(8, producto.getEstado());

            ps.setBoolean(4, producto.isCompra());
            ps.setBoolean(5, producto.isVenta());
            ps.setBoolean(6, producto.isObsequio());

            ps.setDouble(7, producto.getPrecio());

            ps.executeUpdate();
            System.out.println("PRODUCTO insertado correctamente");
        } catch (SQLException ex) {
            System.err.println("Error INSERT: " + ex.getMessage());
            return false;
        }
        return true;
    }

    // ---------------- READ ----------------
    public List<ProductoModelo> listarProducto() {
        List<ProductoModelo> listaProductos = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        try (Statement st = miConexion.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                rs.toString();
                ProductoModelo producto = new ProductoModelo();
                producto.setId(rs.getInt("id"));

                producto.setNombre(rs.getString("nombre"));
                producto.setCodigo(rs.getString("codigo"));
                producto.setCategoria(rs.getString("categoria"));
                producto.setEstado(rs.getString("estado"));

                producto.setCompra(rs.getBoolean("compra"));
                producto.setVenta(rs.getBoolean("venta"));
                producto.setObsequio(rs.getBoolean("obsequio"));

                producto.setPrecio(rs.getDouble("precio"));
                listaProductos.add(producto);
            }
        } catch (SQLException ex) {
            System.err.println("Error SELECT: " + ex.getMessage());
        }
        return listaProductos;
    }

}
