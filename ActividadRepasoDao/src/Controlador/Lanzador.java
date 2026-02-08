package Controlador;

import DAO.DAOProducto;
import DAO.DaoProductoImplementadoMYSQL;
import Vista.IVista;
import Vista.VistaConsola;

public class Lanzador {
	public static void main(String[] args) {
		DAOProducto dao = new DaoProductoImplementadoMYSQL();
		IVista vista = new VistaConsola();
		Controlador controler = new Controlador(dao, vista);
		controler.iniciar();
	}
}
