package Controlador;

import DAO.ProductoDAO;
import Modelo.ProductoModelo;
import java.util.List;

// ============================================================
// CONTROLADOR
// Es el cerebro de la aplicación.
// El que decide qué pasa cuando el usuario hace algo en la vista.
// Dependiendo del botón pulsado en la VISTA el CONTROLADOR llama al método DAO que necesita pasar por parametro el MODELO
// Se program en sentido inverso Primero el MODELO, luego DAO, el CONTROLADOR y finalmente la VISTA
// ============================================================
public class ProductoController {

    //private ProductoDAO productoDAO = new ProductoDAO();
    private final ProductoDAO productoDAO;

    public ProductoController() {
        this.productoDAO = new ProductoDAO();
    }

    // Controlamos Registrar Producto
    public boolean registrar(ProductoModelo productoModelo) {
        boolean respuesta = this.productoDAO.registrarProducto(productoModelo);
        return respuesta;
    }

    // Controlamos Listar Productos
    public List<ProductoModelo> listar() {
        List<ProductoModelo> listaProductos = this.productoDAO.listarProducto();
        return listaProductos;
    }

}
