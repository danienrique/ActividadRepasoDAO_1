package Modelo;

public class Producto {
	private int id, stock;
	private double precio;
	private String nombre, categoria;
	public Producto() {
		
	}
	public Producto(int ide, String name, double price,int stoc, String category){
		this.id = ide;
		this.stock = stoc;
		this.precio = price;
		this.nombre = name;
		this.categoria = category;
	}
	public Producto(String name, double price,int stoc, String category){
		this.stock = stoc;
		this.precio = price;
		this.nombre = name;
		this.categoria = category;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	@Override
	public String toString() {
		return "Producto [id=" + id + ", stock=" + stock + ", precio=" + precio + ", nombre=" + nombre + ", categoria="
				+ categoria + "]";
	}
	
}
