package DAO;

import java.util.ArrayList;

import Modelo.Producto;

public interface DAOProducto {
	public boolean agregarProducto(Producto p);
	public ArrayList<Producto> listarProductos();
	public Producto obtenerProductoId(int id);
	public boolean modificarProducto(Producto p);
	public boolean eliminarProducto(Producto p);
}
