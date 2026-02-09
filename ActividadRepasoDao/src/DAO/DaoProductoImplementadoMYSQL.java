package DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Modelo.Producto;
import Pool.ConecctionBaseDeDatosMYSQL;

public class DaoProductoImplementadoMYSQL implements DAOProducto {
    
    // Crear el logger (una línea al principio de la clase)
    private static final Logger logger = LoggerFactory.getLogger(DaoProductoImplementadoMYSQL.class);

    @Override
    public boolean agregarProducto(Producto p) {
        String sentencia = "INSERT INTO productos (nombre, precio, stock, categoria) VALUES (?,?,?,?)";
        
        logger.info("Intentando agregar producto: {}", p.getNombre());
        
        try (Connection con = ConecctionBaseDeDatosMYSQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sentencia)) {
            
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getStock());
            ps.setString(4, p.getCategoria());
            ps.executeUpdate();
            
            logger.info("Producto agregado exitosamente: {}", p.getNombre());
            return true;
            
        } catch (Exception e) {
            logger.error("Error al agregar producto: {}", p.getNombre(), e);
            return false;
        }
    }

    @Override
    public ArrayList<Producto> listarProductos() {
        String sentencia = "SELECT id, nombre, precio, stock, categoria FROM productos";
        ArrayList<Producto> productos = new ArrayList<>();
        
        logger.debug("Ejecutando consulta para listar todos los productos");
        
        try (Connection con = ConecctionBaseDeDatosMYSQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sentencia)) {
            
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
            
            logger.info("Se listaron {} productos", productos.size());
            
        } catch (Exception e) {
            logger.error("Error al listar productos", e);
            return null;
        }
        
        return productos;
    }

    @Override
    public Producto obtenerProductoId(int id) {
        String sentencia = "SELECT id, nombre, precio, stock, categoria FROM productos WHERE id = ?";
        Producto p = null;
        
        logger.debug("Buscando producto con ID: {}", id);
        
        try (Connection con = ConecctionBaseDeDatosMYSQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sentencia)) {
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                p = new Producto(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getString(5));
                logger.info("Producto encontrado: {}", p.getNombre());
            } else {
                logger.warn("No se encontró producto con ID: {}", id);
            }
            
        } catch (Exception e) {
            logger.error("Error al obtener producto con ID: {}", id, e);
            return null;
        }
        
        return p;
    }

    @Override
    public boolean modificarProducto(Producto productoModificado) {
        String sentencia = "UPDATE productos SET nombre = ?, precio = ?, stock = ?, categoria = ? WHERE id = ?";
        
        logger.info("Modificando producto ID: {}", productoModificado.getId());
        
        try (Connection conexion = ConecctionBaseDeDatosMYSQL.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sentencia)) {
            
            ps.setString(1, productoModificado.getNombre());
            ps.setDouble(2, productoModificado.getPrecio());
            ps.setInt(3, productoModificado.getStock());
            ps.setString(4, productoModificado.getCategoria());
            ps.setInt(5, productoModificado.getId());
            
            boolean exito = ps.executeUpdate() > 0;
            
            if(exito) {
                logger.info("Producto modificado exitosamente: {}", productoModificado.getNombre());
            } else {
                logger.warn("No se pudo modificar el producto con ID: {}", productoModificado.getId());
            }
            
            return exito;
            
        } catch (Exception e) {
            logger.error("Error al modificar producto ID: {}", productoModificado.getId(), e);
            return false;
        }
    }

    @Override
    public boolean eliminarProducto(Producto productoEliminar) {
        String sentencia = "DELETE FROM productos WHERE id = ?";
        
        logger.info("Eliminando producto ID: {}", productoEliminar.getId());
        
        try (Connection conexion = ConecctionBaseDeDatosMYSQL.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sentencia)) {
            
            ps.setInt(1, productoEliminar.getId());
            boolean exito = ps.executeUpdate() > 0;
            
            if(exito) {
                logger.info("Producto eliminado: {}", productoEliminar.getNombre());
            } else {
                logger.warn("No se encontró producto para eliminar con ID: {}", productoEliminar.getId());
            }
            
            return exito;
            
        } catch (Exception e) {
            logger.error("Error al eliminar producto ID: {}", productoEliminar.getId(), e);
            return false;
        }
    }
}