package mundo;

import java.util.ArrayList;
import java.util.List;

import Interfaz.Principal;

public class AplicacionWeb {
	
	//--------------------------------------------------
	// ATRIBUTOS---xxx
	//--------------------------------------------------
	
	private static CRUD crud;
	
	private static ConexionDAO conexion;
	
	private static AplicacionWeb instancia ;
	
	//--------------------------------------------------
	// CONSTRUCTOR E INSTANCIACION
	//--------------------------------------------------

	public static AplicacionWeb darInstancia() throws Exception{
		if(instancia == null){
			instancia = new AplicacionWeb(); 
		}
		return instancia;
	}
	
	public AplicacionWeb() {
		conexion = new ConexionDAO();
		conexion.iniciarConexion();
		conexion.crearTablas();
		crud = new CRUD(conexion);
	}
	
	//--------------------------------------------------
	// GETTERS AND SETTERS
	//--------------------------------------------------

	public static CRUD getCrud() {
		return crud;
	}

	public static void setCrud(CRUD crud) {
		AplicacionWeb.crud = crud;
	}

	public static ConexionDAO getConexion() {
		return conexion;
	}

	public static void setConexion(ConexionDAO conexion) {
		AplicacionWeb.conexion = conexion;
	}

	public static AplicacionWeb getInstancia() {
		return instancia;
	}

	public static void setInstancia(AplicacionWeb instancia) {
		AplicacionWeb.instancia = instancia;
	}
	
	//--------------------------------------------------
	// METODOS
	//--------------------------------------------------
	
	public void registrarProveedor (String id, String direccion, int telefono, String ciudad, String idRepLegal){
		String[] datos = {id,direccion, Integer.toString(telefono) ,ciudad,idRepLegal};
		try{
			crud.insertarTupla(Proveedor.NOMBRE, Proveedor.COLUMNAS, Proveedor.TIPO, datos);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public ArrayList<Proveedor> darProveedores( ) {
		ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
		int numeroColumnas = Proveedor.COLUMNAS.length;
		
		ArrayList<String> datosProveedores = crud.darTuplas(Proveedor.NOMBRE);
		
		ArrayList<String> datosVolumenTiempoMateriaPre = crud.darTuplas("VolumenesTiemposMaterias");
		List<String[]> datosVolumenTiempoMateria = new ArrayList<String[]>();
		for(int i = 0; i < datosVolumenTiempoMateriaPre.size();i+=4){
			datosVolumenTiempoMateria.get(i)[0]=datosVolumenTiempoMateriaPre.get(i);
			datosVolumenTiempoMateria.get(i)[1]=datosVolumenTiempoMateriaPre.get(i+1);
			datosVolumenTiempoMateria.get(i)[2]=datosVolumenTiempoMateriaPre.get(i+2);
			datosVolumenTiempoMateria.get(i)[3]=datosVolumenTiempoMateriaPre.get(i+3);
		}
		
		ArrayList<String> datosVolumenTiempoComponentePre = crud.darTuplas("PROVEEMAXVOLUMENTIEMPO");
		List<String[]> datosVolumenTiempoComponente = new ArrayList<String[]>();
		for(int i = 0; i < datosVolumenTiempoComponentePre.size();i+=4){
			datosVolumenTiempoComponente.get(i)[0]=datosVolumenTiempoComponentePre.get(i);
			datosVolumenTiempoComponente.get(i)[1]=datosVolumenTiempoComponentePre.get(i+1);
			datosVolumenTiempoComponente.get(i)[2]=datosVolumenTiempoComponentePre.get(i+2);
			datosVolumenTiempoComponente.get(i)[3]=datosVolumenTiempoComponentePre.get(i+3);
		}
		
		for(int i = 0; i < datosProveedores.size();i+=numeroColumnas){
			Proveedor proveedor = new Proveedor(datosProveedores.get(i),datosProveedores.get(i+1),Integer.parseInt(datosProveedores.get(i+2)),datosProveedores.get(i+3),datosProveedores.get(i+4), datosVolumenTiempoMateria,datosVolumenTiempoComponente,darMateriasPrimasProveedor(datosProveedores.get(i)),darComponentesProveedor(datosProveedores.get(i)));
			proveedores.add(proveedor);
		}
		
		return proveedores;
	}

	private ArrayList<Componente> darComponentesProveedor(String idProveedor) {
		
		return null;
	}

	private ArrayList<MateriaPrima> darMateriasPrimasProveedor(String idProveedor) {
		
		return null;
	}

}
