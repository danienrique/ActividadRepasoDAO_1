package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Modelo.Producto;
import Pool.ConecctionBaseDeDatosMYSQL;

public class DaoProductoImplementadoMYSQL implements DAOProducto {

	@Override
	public boolean agregarProducto(Producto p) {
		// TODO Auto-generated method stub
		String sentencia = ("INSERT INTO productos (nombre, precio, stock, categoria) VALUES (?,?,?,?)");
		try (Connection con = ConecctionBaseDeDatosMYSQL.getConnection();
				PreparedStatement ps = con.prepareStatement(sentencia)){
			ps.setString(1, p.getNombre());
			ps.setDouble(2, p.getPrecio());
			ps.setInt(3, p.getStock());
			ps.setString(4, p.getCategoria());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public ArrayList<Producto> listarProductos() {
		// TODO Auto-generated method stub
		String sentencia = "SELECT id, nombre, precio, stock, categoria FROM productos";
		ArrayList<Producto> productos = new ArrayList<Producto>();
		try (Connection con = ConecctionBaseDeDatosMYSQL.getConnection();
				PreparedStatement ps = con.prepareStatement(sentencia)){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Producto aux = new Producto();
				aux.setId(rs.getInt(1));
				aux.setNombre(rs.getString(2));
				aux.setPrecio(rs.getDouble(3));
				aux.setStock(rs.getInt(4));
				aux.setCategoria(rs.getString(5));
				productos.add(aux);
			}
			
		} catch (Exception e) {
			return null;
		}
		return productos;
	}

	@Override
	public Producto obtenerProductoId(int id) {
		String sentencia = ("SELECT id, nombre, precio, stock, categoria FROM productos WHERE id = ?");
		Producto p = null;
		try (Connection con = ConecctionBaseDeDatosMYSQL.getConnection();
				PreparedStatement ps = con.prepareStatement(sentencia)){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				p = new Producto(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getString(5));
			}
		} catch (Exception e) {
			return null;
		}
		return p;
	}

	@Override
	public boolean modificarProducto(Producto productoModificado) {
		// TODO Auto-generated method stub
		String sentencia = "UPDATE productos SET nombre = ?, precio = ?, stock = ?, categoria = ? WHERE id = ?";
		try (Connection conexion =  ConecctionBaseDeDatosMYSQL.getConnection();
				PreparedStatement ps = conexion.prepareStatement(sentencia)){
			ps.setString(1, productoModificado.getNombre());
			ps.setDouble(2, productoModificado.getPrecio());
			ps.setInt(3, productoModificado.getStock());
			ps.setString(4, productoModificado.getCategoria());
			return ps.executeUpdate()>0;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean eliminarProducto(Producto productoEliminar) {
		String sentencia = "DELETE FROM productos WHERE id = ?";
		try (Connection conexion =  ConecctionBaseDeDatosMYSQL.getConnection();
				PreparedStatement ps = conexion.prepareStatement(sentencia)){
			ps.setInt(1, productoEliminar.getId());
			return ps.executeUpdate()>0;
		} catch (Exception e) {
			return false;
		}
	}

}
