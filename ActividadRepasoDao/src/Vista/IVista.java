package Vista;

import java.util.ArrayList;

import Modelo.Producto;

public interface IVista {
	public void mostrarMenu();
	public int pedirOpcion();
	public int pedirId();
	public String pedirNombre();
	public int pedirStock();
	public double pedirPrecio();
	public String pedirCategoria();
	public void mostrarProductos(ArrayList<Producto> productos);
	public void mostrarProducto(Producto p);
}
