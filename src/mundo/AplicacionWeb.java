package mundo;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AplicacionWeb {
	
	//--------------------------------------------------
	// ATRIBUTOS
	//--------------------------------------------------
		
	public static final String ID = "generadorId";
	
	public static final String[] COLUMNAS = {"id"};
	
	public static final String[] TIPO = {"String"};
	
	private static CRUD crud;
	
	private static ConexionDAO conexion;
	
	private static AplicacionWeb instancia ;
	
	private String usuarioActual;
	
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
		try
		{
			Statement s = crud.darConexion().createStatement();
			ResultSet rs = s.executeQuery("SELECT MAX(id) FROM generadorId");
			contadorId = rs.getInt(0);
		}
		catch (Exception e){
			contadorId = 1000;
		}
		usuarioActual = "";
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
	
	public static void poblarTablas(){
		crud.poblarTablas();	
	}
	
	public int darContadorId(){
		return ++contadorId;
	}
	
	public void registrarUsuario (String login, String password, String tipo) throws Exception{
		String[] datos = {login, password, tipo};
		crud.insertarTupla(Usuario.NOMBRE, Usuario.COLUMNAS, Usuario.TIPO, datos);
		usuarioActual = login;
	}
	
	public String ingresarUsuario (String login, String password) throws Exception{
		ArrayList<String> usuario = crud.darSubTabla(Usuario.NOMBRE, "tipo", "login = '" + login + "' AND password = '" + password + "'");
		usuarioActual = login;
		if ( usuario.get(0) != null )
			return usuario.get(0);
		return "";
	}
	
	public ArrayList<String> darCantidadProductos (int cantidad) throws Exception{
		ArrayList<String> rta = new ArrayList<String>();
		rta = crud.darSubTabla(Producto.NOMBRE, "nombre", "precio > 0");
		return rta;
	}
	
	public void registrarProveedor (String idProveedor, String direccion, int telefono, String ciudad, String idRepLegal) throws Exception{
		String[] id = {idProveedor};
		String[] datosSimples = {id[0],direccion, Integer.toString(telefono) ,ciudad,idRepLegal};
		crud.insertarTupla(Proveedor.NOMBRE, Proveedor.COLUMNAS, Proveedor.TIPO, datosSimples);
		crud.insertarTupla(ID, COLUMNAS, TIPO, id);
	}
	
	public void registrarProveedorMateriaPrima (List<String[]> datosProveedorMateriaPrima ) throws Exception{
		for(int i = 0; i < datosProveedorMateriaPrima.size(); i++){
			String[] datosCompuestosMateria = datosProveedorMateriaPrima.get(i);
			crud.insertarTupla(Proveedor.NOMBRERELACIONMATERIAPRIMA, Proveedor.COLUMNASRELACIONMATERIAPRIMA, Proveedor.TIPORELACIONMATERIAPRIMA, datosCompuestosMateria);
		}
	}
	
	public void registrarProveedorComponente (List<String[]> datosProveedorComponente) throws Exception{
		for (int i = 0; i < datosProveedorComponente.size(); i++) {
			String[] datosCompuestosComponente = datosProveedorComponente.get(i);
			crud.insertarTupla(Proveedor.NOMBRERELACIONCOMPONENTE, Proveedor.COLUMNASRELACIONCOMPONENTE, Proveedor.TIPORELACIONCOMPONENTE, datosCompuestosComponente);
		}
	}
	
	public void registrarMateriaPrima (String id, String unidadMedida, int cantidadInicial) throws Exception{
		String[] id1 = {id};
		String[] datosSimples = {id, unidadMedida, Integer.toString(cantidadInicial)};
		crud.insertarTupla(ID, COLUMNAS, TIPO, id1);
		try{
			int cantidadActual= Integer.parseInt((crud.darSubTabla(MateriaPrima.NOMBRE, "cantidadInicial", "id="+id).get(0)));
			String[] columnas = new String[1];
			columnas[0] = "cantidadInicial";
			String[] cantidad = new String[1];
			cantidad[0] = (Integer.toString(cantidadInicial + cantidadActual));
			crud.actualizarTupla(MateriaPrima.NOMBRE,columnas,cantidad, "id= '"+id+"'");	
		}
		catch(Exception e){
			crud.insertarTupla(MateriaPrima.NOMBRE, MateriaPrima.COLUMNAS, MateriaPrima.TIPO, datosSimples);
		}
	}
	
	public void registrarComponente (String id, int cantidadInicial) throws Exception {
		String[] id1 = {id};
		String[] datosSimples = {id, Integer.toString(cantidadInicial)};
		crud.insertarTupla(ID, COLUMNAS, TIPO, id1);
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
	
	public void registrarProducto (String id, String nombre, int precio) throws Exception{
		String[] id1 = {id};
		String[] datos = {id, nombre, Integer.toString(precio)};
		crud.insertarTupla(Producto.NOMBRE, Producto.COLUMNAS, Producto.TIPO, datos);
		crud.insertarTupla(ID, COLUMNAS, TIPO, id1);
		System.out.println("Se registro " + datos);
	}
	
	public void registrarProductoEtapasProduccion  (List<String[]> etapasProduccion) throws Exception{
		for (int i = 0; i < etapasProduccion.size(); i++) {
			crud.insertarTupla(Producto.NOMBRE_RELACION_ETAPA_PRODUCCION, Producto.COLUMNA_RELACION_ETAPA_PRODUCCION, Producto.TIPO_RELACION_ETAPA_PRODUCCION, etapasProduccion.get(i));
		}
	}
	
	public void registrarPedidoCliente (String login, String producto, int cantidad, Date pedido, Date entrega) throws Exception{
		ArrayList<String> idProducto = new ArrayList<String>();
		idProducto = crud.darSubTabla(Producto.NOMBRE, "id", " nombre = '" + producto + "' ");
		String sql = "INSERT INTO pedidos (id, idProducto, idCliente, cantidad, diaPedido, mesPedido, diaEntrega, mesEntrega) VALUES ('" + darContadorId() + "','" + idProducto.get(0) + "','" + login + "'," + cantidad + "," + pedido.getDate() + "," + pedido.getMonth() + "," + entrega.getDate() + "," + entrega.getMonth() + ")";
		System.out.println(sql);
		Statement s = crud.darConexion().createStatement();
		s.executeUpdate(sql);
	}
	
	public ArrayList<Pedido> darPedidosCliente(String login) throws Exception{
		ArrayList<Pedido> rta = new ArrayList<Pedido>();
		Statement s = crud.darConexion().createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM " + Pedido.NOMBRE + " WHERE idCliente = '" + login + "'");
		while(rs.next()){
			String id = rs.getString(1);
			String idProducto = rs.getString(2);
			int cantidad = rs.getInt(4);
			int diaPedido = rs.getInt(5);
			int mesPedido = rs.getInt(6);
			int diaEntrega = rs.getInt(7);
			int mesEntrega = rs.getInt(8);
			Date fechaPedido = new Date(2015, mesPedido, diaPedido);
			Date fechaEntrega = new Date(2015, mesEntrega, diaEntrega);
			Pedido pedido = new Pedido(idProducto, login, cantidad, fechaPedido, fechaEntrega);
			rta.add(pedido);
		}
		return rta;
	}
	
	public ArrayList<String> darIdPedido (String producto) throws Exception{
		return crud.darSubTabla(Pedido.NOMBRE, "id", "idCliente = " + usuarioActual + "idProducto = " + producto);
	}
	
	public ArrayList<String> buscarExistenciasMateriaPrima (boolean id, String idDeseado, boolean rango, int mayorA, int menorA) throws Exception{
		if(id && rango){
			return crud.darSubTabla (MateriaPrima.NOMBRE, "cantidadInicial", "id = " + idDeseado + " AND cantidadInicial BETWEEN " + mayorA + " AND " + menorA);				
		}
		else if(id){
			return crud.darSubTabla (MateriaPrima.NOMBRE, "cantidadInicial", "id = " + idDeseado);				
		}
		else if(rango){
			return crud.darSubTabla (MateriaPrima.NOMBRE, "cantidadInicial", "cantidadInicial BETWEEN " + mayorA + " AND " + menorA);				
		}
		return null;
	}
	
	public ArrayList<String> buscarExistenciasProducto (boolean nombre, String nombreDeseado, boolean rango, int mayorA, int menorA, boolean etapa) throws Exception{
		if(nombre && rango && etapa){
			return crud.darSubTabla (Producto.NOMBRE, "cantidad", "nombre = " + nombre + " AND cantidad BETWEEN " + mayorA + " AND " + menorA);				
		}
		else if(nombre && rango){
			return crud.darSubTabla (Producto.NOMBRE, "cantidad", "nombre = " + nombre + " AND cantidad BETWEEN " + mayorA + " AND " + menorA);				
		}
		else if(nombre){
			return crud.darSubTabla (Producto.NOMBRE, "cantidad", "nombre = " + nombre);				
		}
		else if(rango){
			return crud.darSubTabla (Producto.NOMBRE, "cantidad", "cantidad BETWEEN " + mayorA + " AND " + menorA);				
		}
		return null;
	}
	
	public ArrayList<String> buscarExistenciasComponente (boolean id, String idDeseado, boolean rango, int mayorA, int menorA) throws Exception{
		if(id && rango){
			return crud.darSubTabla (Componente.NOMBRE, "cantidadInicial", "id = " + idDeseado + " AND cantidadInicial BETWEEN " + mayorA + " AND " + menorA);				
		}
		else if(id){
			return crud.darSubTabla (Componente.NOMBRE, "cantidadInicial", "id = " + idDeseado);				
		}
		else if(rango){
			return crud.darSubTabla (Componente.NOMBRE, "cantidadInicial", "cantidadInicial BETWEEN " + mayorA + " AND " + menorA);				
		}
		return null;
	}
	
	public ArrayList<String> buscarProducto (String nombre) throws Exception{
		return crud.darSubTabla(Producto.NOMBRE, "precio", "nombre = '" + nombre + "'");
	}

	public ArrayList<Pedido> buscarPedidosCliente (Date pedido, boolean pedido1, Date entrega, boolean entrega1) throws Exception{
		ArrayList<String> prod;
		ArrayList<Pedido> rta = new ArrayList<Pedido>();
		if (pedido1 && entrega1)
		{
			prod = crud.darSubTabla(Pedido.NOMBRE, "idProducto", "idCliente = " + usuarioActual + "diaPedido = " + pedido.getDate() +  "mesPedido = " + pedido.getMonth() + "diaEntrega = " + entrega.getDate() +  "mesEntrega = " + pedido.getMonth());
		}
		else if (pedido1)
		{
			prod = crud.darSubTabla(Pedido.NOMBRE, "idProducto", "idCliente = " + usuarioActual + "diaPedido = " + pedido.getDate() +  "mesPedido = " + pedido.getMonth());
		}
		else
		{
			prod = crud.darSubTabla(Pedido.NOMBRE, "idProducto", "idCliente = " + usuarioActual + "diaEntrega = " + entrega.getDate() +  "mesEntrega = " + pedido.getMonth());
		}
		for (int i = 0; i < prod.size(); i++) {
			Pedido p = new Pedido(usuarioActual, prod.get(i), 2, pedido, entrega);
			rta.add(p);
		}
		return rta;
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
		ArrayList<String> datosMateriasPrimas = crud.darTuplas(MateriaPrima.NOMBRE);
		
		for(int i = 0; i < datosMateriasPrimas.size();i++){
			MateriaPrima materiaPrima = new MateriaPrima(datosMateriasPrimas.get(i),"",2);
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

	public boolean registrarRegistro (String idProducto, int cantidad) throws Exception{
		try
		{
			ArrayList<String> tuplas = crud.darSubTabla(Registro.NOMBRE, "idProducto", "idProducto = " + idProducto);
			for (String string : tuplas) {
				System.out.println(string);
			}
		}
		catch (Exception e)
		{
			ArrayList<String> etapas = crud.darSubTabla(Producto.NOMBRE_RELACION_ETAPA_PRODUCCION, "idEtapa", "id_Producto = '" + idProducto + "'");
			String[] datos =  {Integer.toString(darContadorId()),};
			crud.insertarTupla(Registro.NOMBRE, Registro.COLUMNAS, Registro.TIPO, datos);
		}
		return true;
	}
	
	public static void main(String[] args) {
		AplicacionWeb aplicacionWeb = getInstancia();
		try
		{
			Statement s = crud.darConexion().createStatement();
			s.execute("INSERT INTO pedidos (id, idProducto, idCliente, cantidad, diaPedido, mesPedido, diaEntrega, mesEntrega) VALUES ('10','225','meili',1,1,2,1,2)");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
