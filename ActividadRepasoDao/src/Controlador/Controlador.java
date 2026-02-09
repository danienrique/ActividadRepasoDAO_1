package Controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import DAO.DAOProducto;
import Modelo.Producto;
import Vista.IVista;

public class Controlador {
    private static final Logger logger = LoggerFactory.getLogger(Controlador.class);
    
    private DAOProducto DAO;
    private IVista vista;

    public Controlador(DAOProducto dao, IVista vista) {
        this.DAO = dao;
        this.vista = vista;
        logger.info("Controlador inicializado");
    }

    public void iniciar() {
        logger.info("Aplicación iniciada");
        int opcion = -1;
        
        while (opcion != 0) {
            vista.mostrarMenu();
            opcion = vista.pedirOpcion();
            
            logger.debug("Opción seleccionada: {}", opcion);
            
            switch (opcion) {
                case 1: {
                    vista.mostrarProductos(DAO.listarProductos());
                    break;
                }
                case 2: {
                    vista.mostrarProducto(DAO.obtenerProductoId(vista.pedirId()));
                    break;
                }
                case 3: {
                    DAO.agregarProducto(crearProducto());
                    break;
                }
                case 4: {
                    vista.mostrarProductos(DAO.listarProductos());
                    int id = vista.pedirId();
                    vista.mostrarProducto(DAO.obtenerProductoId(id));
                    Producto productoModificado = crearProducto();
                    productoModificado.setId(id);
                    DAO.modificarProducto(productoModificado);
                    break;
                }
                case 5: {
                    vista.mostrarProductos(DAO.listarProductos());
                    int id = vista.pedirId();
                    DAO.eliminarProducto(DAO.obtenerProductoId(id));
                    break;
                }
                case 0: {
                    logger.info("Aplicación finalizada por el usuario");
                    break;
                }
                default: {
                    logger.warn("Opción inválida seleccionada: {}", opcion);
                }
            }
        }
    }

    public Producto crearProducto() {
        logger.debug("Solicitando datos para crear producto");
        String nombre = vista.pedirNombre();
        double precio = vista.pedirPrecio();
        int stock = vista.pedirStock();
        String categoria = vista.pedirCategoria();
        return new Producto(nombre, precio, stock, categoria);
    }
}