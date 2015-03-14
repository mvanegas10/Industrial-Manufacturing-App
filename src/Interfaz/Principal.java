package Interfaz;

import java.util.ArrayList;

import mundo.AplicacionWeb;
import mundo.CRUD;
import mundo.ConexionDAO;

public class Principal {
	
	//--------------------------------------------------
	// ATRIBUTOS
	//--------------------------------------------------
	
	private static AplicacionWeb aplicacionWeb;
	
	private static CRUD crud;
	
	private static ConexionDAO conexion;
	
	//--------------------------------------------------
	// CONSTRUCTOR
	//--------------------------------------------------
	
	public static void main (String[] args){
		try{
			conexion = new ConexionDAO();
			conexion.iniciarConexion();
			conexion.crearTablas();
			crud = new CRUD(conexion);
			
			//crud.insertarTupla(Proveedor.NOMBRE, Proveedor.COLUMNAS,Proveedor.TIPO, Proveedor.COLUMNAS);
			//crud.darTuplas(Proveedor.NOMBRE);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	//--------------------------------------------------
	// METODOS
	//--------------------------------------------------
	

	
	
}
