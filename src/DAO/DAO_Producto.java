package DAO;

import Conexion.ConexionMysql;
import Modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// ============================================================
// DAO
// La Clase que interacciona con la BBDD
// ============================================================
public class DAO_Producto {

    private Connection miConexion = ConexionMysql.conectarBBDD();

    // ---------------- CREATE ----------------
    public boolean registrar(Producto producto) {

        String sql = "INSERT INTO producto (nombre, codigo, categoria, compra, venta, obsequio, precio, estado) VALUES (?,?,?,?,?,?,?,?)";

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

}
