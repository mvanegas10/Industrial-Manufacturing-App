package mundo;

import java.util.ArrayList;
import java.util.List;

import Interfaz.Principal;

public class AplicacionWeb {
	
	//--------------------------------------------------
	// ATRIBUTOS
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
		
		for(int i = 0; i < datosProveedores.size();i+=numeroColumnas){
			
			ArrayList<String> datosVolumenTiempoMateriaPre = crud.darSubTabla("PROVEEMAXVOLUMENTIEMPOMATERIALES","*","TRUE");
			List<String[]> datosVolumenTiempoMateria = new ArrayList<String[]>();
			for(int j = 0; j < datosVolumenTiempoMateriaPre.size();j+=4){
				datosVolumenTiempoMateria.get(j)[0]=datosVolumenTiempoMateriaPre.get(j);
				datosVolumenTiempoMateria.get(j)[1]=datosVolumenTiempoMateriaPre.get(j+1);
				datosVolumenTiempoMateria.get(j)[2]=datosVolumenTiempoMateriaPre.get(j+2);
				datosVolumenTiempoMateria.get(j)[3]=datosVolumenTiempoMateriaPre.get(j+3);
			}
			
			ArrayList<String> datosVolumenTiempoComponentePre = crud.darSubTabla("PROVEEMAXVOLUMENTIEMPOCOMPONENTES","*","TRUE");
			List<String[]> datosVolumenTiempoComponente = new ArrayList<String[]>();
			for(int j = 0; j < datosVolumenTiempoComponentePre.size();j+=4){
				datosVolumenTiempoComponente.get(j)[0]=datosVolumenTiempoComponentePre.get(j);
				datosVolumenTiempoComponente.get(j)[1]=datosVolumenTiempoComponentePre.get(j+1);
				datosVolumenTiempoComponente.get(j)[2]=datosVolumenTiempoComponentePre.get(j+2);
				datosVolumenTiempoComponente.get(j)[3]=datosVolumenTiempoComponentePre.get(j+3);
			}

			Proveedor proveedor = new Proveedor(datosProveedores.get(i),datosProveedores.get(i+1),Integer.parseInt(datosProveedores.get(i+2)),datosProveedores.get(i+3),datosProveedores.get(i+4), datosVolumenTiempoMateria,datosVolumenTiempoComponente,darMateriasPrimasProveedor(datosProveedores.get(i)),darComponentesProveedor(datosProveedores.get(i)));
			proveedores.add(proveedor);
		}
		
		return proveedores;
	}
	
	private ArrayList<MateriaPrima> darMateriasPrimasProveedor(String idProveedor) {
		ArrayList<String> materiasPrimasPre = crud.darSubTabla("PROOVEDORESMATERIAS", "*", "id_proveedor=idProveedor");
		ArrayList<MateriaPrima> materiasPrimas = new ArrayList<MateriaPrima>();
		for(int i = 0; i < materiasPrimasPre.size();i+=MateriaPrima.COLUMNAS.length){
			MateriaPrima materiaPrima = new MateriaPrima(materiasPrimasPre.get(i),materiasPrimasPre.get(i+1),Integer.parseInt(materiasPrimasPre.get(i+2)));
			materiasPrimas.add(materiaPrima);
		}
		return materiasPrimas;
	}

	private ArrayList<Componente> darComponentesProveedor(String idProveedor) {
		ArrayList<String> componentesPre = crud.darSubTabla("PROOVEDORESCOMPONENTES", "*", "id_proveedor=idProveedor");
		ArrayList<Componente> componentes = new ArrayList<Componente>();
		for(int i = 0; i < componentesPre.size();i+=Componente.COLUMNAS.length){
			Componente componente = new Componente(componentesPre.get(i),Integer.parseInt(componentesPre.get(i+1)));
			componentes.add(componente);
		}
		return null;
	}


}
