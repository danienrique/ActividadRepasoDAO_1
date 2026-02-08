package Controlador;

import DAO.DAOProducto;
import Modelo.Producto;
import Vista.IVista;

public class Controlador {
	private DAOProducto DAO;
	private IVista vista;

	public Controlador(DAOProducto dao, IVista vista) {
		this.DAO = dao;
		this.vista = vista;
	}

	public void iniciar() {
		int opcion = -1;
		while (opcion != 0) {
			vista.mostrarMenu();
			opcion = vista.pedirOpcion();
			switch (opcion) {
			case 1: {
				vista.mostrarProductos(DAO.listarProductos());
				break;
			}
			case 2:{
				vista.mostrarProducto(DAO.obtenerProductoId(vista.pedirId()));
				break;
			}
			case 3:{
				Producto p = crearProducto();
				DAO.agregarProducto(p);
				break;
			}
			case 4:{
				vista.mostrarProductos(DAO.listarProductos());
				int id = vista.pedirId();
				vista.mostrarProducto(DAO.obtenerProductoId(id));
				Producto productoModificado = crearProducto();
				productoModificado.setId(id);
				DAO.modificarProducto(productoModificado);
				break;
			}
			case 5:{
				vista.mostrarProductos(DAO.listarProductos());
				int id = vista.pedirId();
				DAO.eliminarProducto(DAO.obtenerProductoId(id));
				break;
			}
			}
		}
	}
	
	public Producto crearProducto() {
		String nombre = vista.pedirNombre();
		double precio = vista.pedirPrecio();
		int stock = vista.pedirStock();
		String categoria = vista.pedirCategoria();
		Producto productoDevolver = new Producto(nombre, precio, stock, categoria);
		return productoDevolver;
	}
}
