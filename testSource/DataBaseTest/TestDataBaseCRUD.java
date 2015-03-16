package DataBaseTest;

import mundo.AplicacionWeb;
import mundo.CRUD;
import mundo.ConexionDAO;
import mundo.Usuario;
import junit.framework.TestCase;

public class TestDataBaseCRUD extends TestCase{

	private CRUD crud;
	private ConexionDAO dao;
	
	/**
	 * El setup del escenario 1
	 */
	public void setupEscenario1(){
		dao = new ConexionDAO();
		crud = new CRUD(dao);
	}
	
	/**
	 * El setup del escenario 2
	 */
	public void setupEscenario2(){
		setupEscenario1();
		try
		{
			String[] datos = { "1", "1", "admin"};
			crud.insertarTupla(Usuario.NOMBRE, Usuario.COLUMNAS, Usuario.TIPO, datos);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Prueba del metodo agregar
	 */
	public void testNoAceptaIdDoble(){
		setupEscenario2();
		try
		{
			String[] datos = { "1", "1", "admin"};
			crud.insertarTupla(Usuario.NOMBRE, Usuario.COLUMNAS, Usuario.TIPO, datos);
			fail("No debió haber agregado la tupla");
		}
		catch (Exception e){
			
		}
	}
	
	/**
	 * Prueba del metodo eliminar
	 */
	public void testEliminar(){
		setupEscenario2();
		try
		{
			arbolTrie.eliminar(CAL);
			arbolTrie.eliminar(CASA);
			arbolTrie.eliminar(CASADO);
			arbolTrie.eliminar(CAZADO);
			arbolTrie.eliminar(CAN);
			assertNull("No se debio encontrar el elemento",arbolTrie.buscar(CAL));
			assertNull("No se debio encontrar el elemento",arbolTrie.buscar(CASA));
			assertNull("No se debio encontrar el elemento",arbolTrie.buscar(CASADO));
			assertNull("No se debio encontrar el elemento",arbolTrie.buscar(CAZADO));
			assertNull("No se debio encontrar el elemento",arbolTrie.buscar(CAN));
		}
		catch (Exception e){
			e.printStackTrace();
			fail("Debio haber eliminado correctamente");
		}
	}
	
	/**
	 * Prueba del metodo buscar
	 */
	public void testBuscar(){
		setupEscenario2();
		assertEquals("Se debio haber encontrado el objeto", CAL, arbolTrie.buscar(CAL));
		assertEquals("Se debio haber encontrado el objeto", CASA, arbolTrie.buscar(CASA));
		assertEquals("Se debio haber encontrado el objeto", CASADO, arbolTrie.buscar(CASADO));
		assertEquals("Se debio haber encontrado el objeto", CAZADO, arbolTrie.buscar(CAZADO));
		assertEquals("Se debio haber encontrado el objeto", CAN, arbolTrie.buscar(CAN));
	}
	
	/**
	 * Prueba del metodo buscarPorPrefijo
	 */
	public void testBuscarPorPrefijo(){
		setupEscenario2();
		IListaEncadenada<String> lista = arbolTrie.buscarPorPrefijo(CASA);
		assertEquals("Se debio haber encontrado el objeto", CASA, lista.buscarElemento(CASA));
		assertEquals("Se debio haber encontrado el objeto", CASADO, lista.buscarElemento(CASADO));
	}
}
