package Vista;

import java.util.ArrayList;
import java.util.Scanner;

import Modelo.Producto;

public class VistaConsola implements IVista{
	private static Scanner sc = new Scanner(System.in);
	@Override
	public void mostrarMenu() {
		// TODO Auto-generated method stub
		System.out.println("MENU: ");
		System.out.println("Opcion 1: Mostrar Productos");
		System.out.println("Opcion 2: Mostrar producto Especifico");
		System.out.println("Opcion 3: Insertar producto");
		System.out.println("Opcion 4: Modificar producto");
		System.out.println("Opcion 5: Eliminar producto");
		System.out.println("Opcion 0: Salir");
	}

	@Override
	public int pedirOpcion() {
		int opt = sc.nextInt();
		sc.nextLine();
		if(opt == 0) {
			System.out.println("Saliendo de la aplicacion");
		}
		return opt;
	}

	@Override
	public int pedirId() {
		System.out.println("Indique el id del Producto: ");
		int pdct = sc.nextInt();
		sc.nextLine();
		return pdct;
	}

	@Override
	public String pedirNombre() {
		System.out.println("Indique el nombre");
		String nombre = sc.nextLine();
		return nombre;
	}

	@Override
	public int pedirStock() {
		System.out.println("Indique el stock");
		int stock = sc.nextInt();
		sc.nextLine();
		return stock;
	}

	@Override
	public double pedirPrecio() {
		System.out.println("Indique el precio");
		Double precio = sc.nextDouble();
		sc.nextLine();
		return precio;
	}

	@Override
	public String pedirCategoria() {
		System.out.println("Indique la categoria");
		String categoria = sc.nextLine();
		return categoria;
	}

	@Override
	public void mostrarProductos(ArrayList<Producto> productos) {
		// TODO Auto-generated method stub
		for(Producto p: productos) {
			System.out.println(p.toString());
		}
		
	}
	@Override
	public void mostrarProducto(Producto p) {
		// TODO Auto-generated method stub
		if(p.getNombre().equals(null)) {
			System.out.println("No hay ningun producto asi");
		}else {
			System.out.println(p.toString());
		}
	}

}
