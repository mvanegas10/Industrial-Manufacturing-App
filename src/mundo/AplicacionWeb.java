package mundo;

import java.sql.Statement;
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
	
	private int contadorId;
	
	
	//--------------------------------------------------
	// CONSTRUCTOR E INSTANCIACION
	//--------------------------------------------------

	public static AplicacionWeb getInstancia() {		
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
		contadorId = 1000;
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

	public static void setInstancia(AplicacionWeb instancia) {
		AplicacionWeb.instancia = instancia;
	}
	
	//--------------------------------------------------
	// METODOS
	//--------------------------------------------------
	
	public void poblarTablas(){
		crud.poblarTablas();	
	}
	
	public int darContadorId(){
		return contadorId++;
	}
	
	public void registrarUsuario (String login, String password, String tipo){
		String[] datos = {login, password, tipo};
		try{
			crud.insertarTupla(Usuario.NOMBRE, Usuario.COLUMNAS, Usuario.TIPO, datos);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public String ingresarUsuario (String login, String password) throws Exception{
		ArrayList<String> usuario = crud.darSubTabla(Usuario.NOMBRE, "login, password", login + ", " + password);
		if ( usuario.get(0) != null )
			return usuario.get(0);
		return "";
	}
	
	public void registrarProveedor (String id, String direccion, int telefono, String ciudad, String idRepLegal, List<String[]> datosProveedorMateriaPrima, List<String[]> datosProveedorComponente) {
		String[] datosSimples = {id,direccion, Integer.toString(telefono) ,ciudad,idRepLegal};
		try{
			crud.insertarTupla(Proveedor.NOMBRE, Proveedor.COLUMNAS, Proveedor.TIPO, datosSimples);
			for(int i = 0; i < datosProveedorMateriaPrima.size(); i++){
				String[] datosCompuestosMateria = datosProveedorMateriaPrima.get(i);
				String[] datosCompuestosComponente = datosProveedorComponente.get(i);
				crud.insertarTupla(Proveedor.NOMBRERELACIONMATERIAPRIMA, Proveedor.COLUMNASRELACIONMATERIAPRIMA, Proveedor.TIPORELACIONMATERIAPRIMA, datosCompuestosMateria);
				crud.insertarTupla(Proveedor.NOMBRERELACIONCOMPONENTE, Proveedor.COLUMNASRELACIONCOMPONENTE, Proveedor.TIPORELACIONCOMPONENTE, datosCompuestosComponente);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void registrarMateriaPrima (String id, String unidadMedida, int cantidadInicial) throws Exception{
		String[] datosSimples = {id, unidadMedida, Integer.toString(cantidadInicial)};
		try{
			int cantidadActual= Integer.parseInt((crud.darSubTabla(MateriaPrima.NOMBRE, "cantidadInicial", "id="+id).get(0)));
			String[] columnas = new String[1];
			columnas[0] = "cantidadInicial";
			String[] cantidad = new String[1];
			cantidad[0] = (Integer.toString(cantidadInicial + cantidadActual));
			crud.actualizarTupla(MateriaPrima.NOMBRE,columnas,cantidad, "id="+id);	
		}
		catch(Exception e){
			crud.insertarTupla(MateriaPrima.NOMBRE, MateriaPrima.COLUMNAS, MateriaPrima.TIPO, datosSimples);
		}
	}
	
	public void registrarComponente (String id, int cantidadInicial) throws Exception {
		String[] datosSimples = {id, Integer.toString(cantidadInicial)};
		try{
			int cantidadActual= Integer.parseInt((crud.darSubTabla(Componente.NOMBRE, "cantidadInicial", "id="+id).get(0)));
			String[] columnas = new String[1];
			columnas[0] = "cantidadInicial";
			String[] cantidad = new String[1];
			cantidad[0] = (Integer.toString(cantidadInicial + cantidadActual));
			crud.actualizarTupla(MateriaPrima.NOMBRE,columnas,cantidad, "id="+id);
		}
		catch(Exception e){
			crud.insertarTupla(Componente.NOMBRE, Componente.COLUMNAS, Componente.TIPO, datosSimples);		}
	}
	
	public ArrayList<Proveedor> darProveedores( ) throws Exception {
		ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
		int numeroColumnas = Proveedor.COLUMNAS.length;
		
		ArrayList<String> datosProveedores = crud.darTuplas(Proveedor.NOMBRE);
		
		for(int i = 0; i < datosProveedores.size();i+=numeroColumnas){
			
			ArrayList<String> datosVolumenTiempoMateriaPre = crud.darSubTabla(Proveedor.NOMBRERELACIONMATERIAPRIMA,"id_Materia, volMax, volTiempo","TRUE");
			List<String[]> datosVolumenTiempoMateria = new ArrayList<String[]>();
			for(int j = 0; j < datosVolumenTiempoMateriaPre.size();j+=4){
				datosVolumenTiempoMateria.get(j)[0]=datosVolumenTiempoMateriaPre.get(j);
				datosVolumenTiempoMateria.get(j)[1]=datosVolumenTiempoMateriaPre.get(j+1);
				datosVolumenTiempoMateria.get(j)[2]=datosVolumenTiempoMateriaPre.get(j+2);
				datosVolumenTiempoMateria.get(j)[3]=datosVolumenTiempoMateriaPre.get(j+3);
			}
			
			ArrayList<String> datosVolumenTiempoComponentePre = crud.darSubTabla(Proveedor.NOMBRERELACIONCOMPONENTE,"id_Materia, volMax, volTiempo","TRUE");
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
	
	public ArrayList<MateriaPrima> darMateriasPrimas( ) throws Exception {
		ArrayList<MateriaPrima> materiasPrimas = new ArrayList<MateriaPrima>();
		int numeroColumnas = MateriaPrima.COLUMNAS.length;
		ArrayList<String> datosMateriasPrimas = crud.darTuplas(MateriaPrima.NOMBRE);
		
		for(int i = 0; i < datosMateriasPrimas.size();i+=numeroColumnas){
			MateriaPrima materiaPrima = new MateriaPrima(datosMateriasPrimas.get(i),datosMateriasPrimas.get(i+1),Integer.parseInt(datosMateriasPrimas.get(i+2)));
			materiasPrimas.add(materiaPrima);
		}
		return materiasPrimas;
	}
	
	public ArrayList<Componente> darComponentes( ) throws Exception {
		ArrayList<Componente> componentes = new ArrayList<Componente>();
		int numeroColumnas = Componente.COLUMNAS.length;
		ArrayList<String> datosComponentes = crud.darTuplas(Componente.NOMBRE);
		
		for(int i = 0; i < datosComponentes.size();i+=numeroColumnas){
			Componente componente = new Componente(datosComponentes.get(i),Integer.parseInt(datosComponentes.get(i+1)));
			componentes.add(componente);
		}
		return componentes;
	}
	
	private ArrayList<MateriaPrima> darMateriasPrimasProveedor(String idProveedor) throws Exception{
		ArrayList<String> materiasPrimasPre = crud.darSubTabla("PROOVEDORESMATERIAS", "*", "id_proveedor=idProveedor");
		ArrayList<MateriaPrima> materiasPrimas = new ArrayList<MateriaPrima>();
		for(int i = 0; i < materiasPrimasPre.size();i+=MateriaPrima.COLUMNAS.length){
			MateriaPrima materiaPrima = new MateriaPrima(materiasPrimasPre.get(i),materiasPrimasPre.get(i+1),Integer.parseInt(materiasPrimasPre.get(i+2)));
			materiasPrimas.add(materiaPrima);
		}
		return materiasPrimas;
	}

	private ArrayList<Componente> darComponentesProveedor(String idProveedor) throws Exception {
		ArrayList<String> componentesPre = crud.darSubTabla("PROOVEDORESCOMPONENTES", "*", "id_proveedor=idProveedor");
		ArrayList<Componente> componentes = new ArrayList<Componente>();
		for(int i = 0; i < componentesPre.size();i+=Componente.COLUMNAS.length){
			Componente componente = new Componente(componentesPre.get(i),Integer.parseInt(componentesPre.get(i+1)));
			componentes.add(componente);
		}
		return componentes;
	}
	
	public static void main(String[] args) {
		AplicacionWeb app = getInstancia();
		
	}

}
