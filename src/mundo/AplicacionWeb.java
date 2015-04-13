package mundo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Meili
 *
 */
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
		poblarTablas();
		try
		{
			Statement s = crud.darConexion().createStatement();
			String sql = "SELECT MAX(id) FROM generadorId";
			System.out.println(sql);
			ResultSet rs = s.executeQuery(sql);
			while(rs.next())
			{
				contadorId = Integer.parseInt(rs.getString(1));
				System.out.println("El id actual es: " + contadorId);
			}
		}
		catch (Exception e){
			contadorId = 1004;
		}
		usuarioActual = "";
	}

	//--------------------------------------------------
	// GETTERS AND SETTERS
	//--------------------------------------------------

	public int darContadorId(){
		return ++contadorId;
	}

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
	// METODOS AUXILIARES
	//--------------------------------------------------
	
	/**
	 * 
	 */
	public static void poblarTablas(){
		crud.poblarTablas();	
	}

	//--------------------------------------------------
	// METODOS CREAR
	//--------------------------------------------------
	
	/**
	 * @param login
	 * @param password
	 * @param tipo
	 * @throws Exception
	 */
	public void registrarUsuario (String login, String password, String tipo) throws Exception{
		//		columnas de Usuario: login, password, tipo, nombre, direccion, telefono, ciudad, idRepLegal
		String[] datos = {login, password, tipo, "", "", Integer.toString(0), "", ""}; 
		crud.insertarTupla(Usuario.NOMBRE, Usuario.COLUMNAS, Usuario.TIPO, datos);
		usuarioActual = login;

	}
	
	/**
	 * @param idProveedor
	 * @param direccion
	 * @param telefono
	 * @param ciudad
	 * @param idRepLegal
	 * @throws Exception
	 */
	public void registrarProveedor (String idProveedor, String direccion, int telefono, String ciudad, String idRepLegal) throws Exception{
		String[] id = {idProveedor};
		String[] datosSimples = {id[0],direccion, Integer.toString(telefono) ,ciudad,idRepLegal};
		crud.insertarTupla(Proveedor.NOMBRE, Proveedor.COLUMNAS, Proveedor.TIPO, datosSimples);
		crud.insertarTupla(ID, COLUMNAS, TIPO, id);
	}

	/**
	 * @param datosProveedorMateriaPrima
	 * @throws Exception
	 */
	public void registrarMateriaPrima (List<String[]> datosProveedorMateriaPrima ) throws Exception{
		for(int i = 0; i < datosProveedorMateriaPrima.size(); i++){
			String[] datosCompuestosMateria = datosProveedorMateriaPrima.get(i);
			crud.insertarTupla(Proveedor.NOMBRE_RELACION_MATERIA_PRIMA, Proveedor.COLUMNAS_RELACION_MATERIA_PRIMA, Proveedor.TIPO_RELACION_MATERIA_PRIMA, datosCompuestosMateria);
		}
	}

	/**
	 * @param datosProveedorComponente
	 * @throws Exception
	 */
	public void registrarComponente (List<String[]> datosProveedorComponente) throws Exception{
		for (int i = 0; i < datosProveedorComponente.size(); i++) {
			String[] datosCompuestosComponente = datosProveedorComponente.get(i);
			crud.insertarTupla(Proveedor.NOMBRE_RELACION_COMPONENTE, Proveedor.COLUMNAS_RELACION_COMPONENTE, Proveedor.TIPO_RELACION_COMPONENTE, datosCompuestosComponente);
		}
	}

	/**
	 * @param id
	 * @param unidadMedida
	 * @param cantidadInicial
	 * @throws Exception
	 */
	public void registrarMateriaPrima (String id, String unidadMedida, int cantidadInicial) throws Exception{
		String[] datosSimples = {id, unidadMedida, Integer.toString(cantidadInicial)};
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

	/**
	 * @param id
	 * @param cantidadInicial
	 * @throws Exception
	 */
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

	/**
	 * @param id
	 * @param nombre
	 * @param precio
	 * @throws Exception
	 */
	public void registrarProducto (String id, String nombre, int precio) throws Exception{
		String[] id1 = {id};
		String[] datos = {id, nombre, Integer.toString(precio)};
		crud.insertarTupla(Producto.NOMBRE, Producto.COLUMNAS, Producto.TIPO, datos);
		crud.insertarTupla(ID, COLUMNAS, TIPO, id1);
		System.out.println("Se registro " + datos);
	}

	/**
	 * @param id
	 * @param idProducto
	 * @param idEstacion
	 * @param idMateriaPrima
	 * @param idComponente
	 * @param duracion
	 * @param numeroSecuencia
	 * @param idAnterior
	 * @throws Exception
	 */
	public void registrarEtapaProduccion  (String id, String idProducto, String idEstacion, String idMateriaPrima, String idComponente, int duracion, int numeroSecuencia, String idAnterior) throws Exception{
		String[] datos = {id, idProducto, idEstacion, idMateriaPrima, idComponente, Integer.toString(duracion), Integer.toString(numeroSecuencia), idAnterior};
		crud.insertarTupla(Etapa.NOMBRE, Etapa.COLUMNAS, Etapa.TIPO, datos);
	}

	/**
	 * @param login
	 * @param producto
	 * @param cantidad
	 * @param pedido
	 * @param entrega
	 * @throws Exception
	 */
	public void registrarPedido (String login, String producto, int cantidad, Date pedido, Date entrega) throws Exception{
		String idProducto;
		int duracionProduccion = 0;
		ArrayList<Etapa> etapas = new ArrayList<Etapa>();
		ArrayList<Integer> idsGenerados = new ArrayList<Integer>();
		idsGenerados.add(darContadorId());
		
		idProducto = (crud.darSubTabla(Producto.NOMBRE, "id", " nombre = '" + producto + "' ")).get(0);
		
		ResultSet rs_etapas = crud.darConexion().createStatement().executeQuery("SELECT * FROM " + Etapa.NOMBRE + " WHERE idProducto = '" + idProducto + "'");
		while(rs_etapas.next()){
			String idEtapa = rs_etapas.getString(1);
			String idEstacion = rs_etapas.getString(3);
			String idMateriaPrima = rs_etapas.getString(4);
			String idComponente = rs_etapas.getString(5);
			int duracion = rs_etapas.getInt(6);
			int numeroSecuencia = rs_etapas.getInt(7);
			String idAnterior = rs_etapas.getString(8);
			duracionProduccion+=duracion;
			Etapa etapa = new Etapa(idEtapa, idProducto, idEstacion, idMateriaPrima, idComponente, duracion, numeroSecuencia, idAnterior);
			etapas.add(etapa);
		}
		
		for (int i = 0; i < etapas.size(); i++) {
			idsGenerados.add(darContadorId());
			String[] datosRegistro = {Integer.toString(idsGenerados.get(1+i)), etapas.get(i).getId(), idProducto};
//			String[] datosInventario = {Integer.toString(idsGenerados.get(1+i)), idProducto};
//			crud.insertarTupla(Producto.NOMBRE_REGISTRO_PRODUCTOS, Producto.COLUMNAS_REGISTRO_PRODUCTOS, Producto.TIPO_REGISTRO_PRODUCTOS, datosRegistro);
			crud.insertarTupla(Producto.NOMBRE_INVENTARIO_PRODUCTOS, Producto.COLUMNAS_INVENTARIO_PRODUCTOS, Producto.TIPO_INVENTARIO_PRODUCTOS, datosRegistro);
		}
		
//		String sql = "INSERT INTO pedidos (id, idProducto, idCliente, cantidad, diaPedido, mesPedido, diaEntrega, mesEntrega) VALUES ('" + Integer.toString(idsGenerados.get(0)) + "','" + idProducto + "','" + login + "'," + cantidad + "," + pedido.getDate() + "," + pedido.getMonth() + "," + entrega.getDate() + "," + entrega.getMonth() + ")";
//		System.out.println(sql);
//		for (Integer id : idsGenerados) {
//			String[] pId = {Integer.toString(id)};
//			crud.insertarTupla(ID, COLUMNAS, TIPO, pId);
//		}
//		Statement s = crud.darConexion().createStatement();
//		s.executeUpdate(sql);
	}
	
	/**
	 * @param login
	 * @param producto
	 * @param cantidad
	 * @param pedido
	 * @param entrega
	 * @throws Exception
	 */
	public void insertarPedido (String login, String nombreProducto, int cantidad, Date pedido, Date entrega) throws Exception{
		String idProducto;
		ArrayList<Etapa> etapas = new ArrayList<Etapa>();
		int duracion = 0;
		conexion.setAutoCommitFalso();
		idProducto = obtenerIdProducto(nombreProducto);
		etapas = obtenerEtapas(idProducto);
		for(Etapa etapa : etapas){
			verificarExistencias(etapa,cantidad);
		}
	}
	
	public String obtenerIdProducto (String producto) throws Exception{
		return crud.darSubTabla(Producto.NOMBRE, "id", " nombre = '" + producto + "' ").get(0);
	}
	
	public ArrayList<Etapa> obtenerEtapas (String idProducto) throws Exception{
		ArrayList<Etapa> etapas = new ArrayList<Etapa>();
		ResultSet rs_etapas = crud.darConexion().createStatement().executeQuery("SELECT * FROM " + Etapa.NOMBRE + " WHERE idProducto = '" + idProducto + "'");
		while(rs_etapas.next()){
			String idEtapa = rs_etapas.getString(1);
			String idEstacion = rs_etapas.getString(3);
			String idMateriaPrima = rs_etapas.getString(4);
			String idComponente = rs_etapas.getString(5);
			int duracion = rs_etapas.getInt(6);
			int numeroSecuencia = rs_etapas.getInt(7);
			String idAnterior = rs_etapas.getString(8);
			Etapa etapa = new Etapa(idEtapa, idProducto, idEstacion, idMateriaPrima, idComponente, duracion, numeroSecuencia, idAnterior);
			etapas.add(etapa);
		}
		return etapas;
	}
	
	public void verificarExistencias (Etapa etapa, int cantidad) throws Exception{
		PreparedStatement verificarEstaciones = null;
		PreparedStatement verificarMateriasPrimas = null;
		PreparedStatement verificarComponentes = null;
		PreparedStatement insertarRegistro = null;
		
		String verificarEstacionesText = "SELECT a.id FROM " + Estacion.NOMBRE_REGISTRO_ESTACIONES + " a WHERE a.idEstacion = " + etapa.getIdEstacion() + "AND NOT EXISTS (SELECT b.id FROM " + Producto.NOMBRE_REGISTRO_PRODUCTOS + " b WHERE idRegistroEstacion = a.id ORDER BY a.dia,a.mes";
		ResultSet rs_verificarEstaciones = verificarEstaciones.executeQuery(verificarEstacionesText);
		
		String verificarMateriasPrimasText = "SELECT a.id FROM " + MateriaPrima.NOMBRE_REGISTRO_MATERIAS_PRIMAS + " a WHERE a.idMateriaPrima = " + etapa.getIdMateriaPrima() + "AND NOT EXISTS (SELECT b.id FROM " + Producto.NOMBRE_REGISTRO_PRODUCTOS + " b WHERE idRegistroMateriaPrima = a.id";
		ResultSet rs_verificarMateriasPrimas = verificarMateriasPrimas.executeQuery(verificarMateriasPrimasText);

		String verificarComponentesText = "SELECT a.id FROM " + Componente.NOMBRE_REGISTRO_COMPONENTES + " a WHERE a.idComponente = " + etapa.getIdComponente() + "AND NOT EXISTS (SELECT b.id FROM " + Producto.NOMBRE_REGISTRO_PRODUCTOS + " b WHERE idRegistroComponente = a.id";
		ResultSet rs_verificarComponentes = verificarComponentes.executeQuery(verificarComponentesText);

		for(int i = 0; i < cantidad; i++){
			String[] datos = {Integer.toString(i+1000),etapa.getId(),rs_verificarEstaciones.getString(1),rs_verificarMateriasPrimas.getString(1),rs_verificarComponentes.getString(1)};
			crud.insertarTupla(Producto.NOMBRE_REGISTRO_PRODUCTOS, Producto.COLUMNAS_REGISTRO_PRODUCTOS, Producto.TIPO_REGISTRO_PRODUCTOS, datos);
			rs_verificarEstaciones.next();
			rs_verificarMateriasPrimas.next();
			rs_verificarComponentes.next();
		}
	}
	
	
	
	//--------------------------------------------------
	// METODOS DAR Y BUSCAR
	//--------------------------------------------------

	/**
	 * @param login
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public String buscarUsuario (String login, String password) throws Exception{
		ArrayList<String> usuario = crud.darSubTabla(Usuario.NOMBRE, "tipo", "login = '" + login + "' AND password = '" + password + "'");
		usuarioActual = login;
		if ( usuario.get(0) != null )
			return usuario.get(0);
		return "";
	}
	
	/**
	 * @param id
	 * @param idDeseado
	 * @param rango
	 * @param mayorA
	 * @param menorA
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String> buscarMateriaPrima (boolean id, String idDeseado, boolean rango, int mayorA, int menorA) throws Exception{
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

	/**
	 * @param nombre
	 * @param nombreDeseado
	 * @param rango
	 * @param mayorA
	 * @param menorA
	 * @param etapa
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String> buscarProducto (boolean nombre, String nombreDeseado, boolean rango, int mayorA, int menorA, boolean etapa) throws Exception{
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

	/**
	 * @param id
	 * @param idDeseado
	 * @param rango
	 * @param mayorA
	 * @param menorA
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String> buscarComponente (boolean id, String idDeseado, boolean rango, int mayorA, int menorA) throws Exception{
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

	/**
	 * @param nombre
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String> buscarProducto (String nombre) throws Exception{
		return crud.darSubTabla(Producto.NOMBRE, "precio", "nombre = '" + nombre + "'");
	}

	/**
	 * @param pedido
	 * @param pedido1
	 * @param entrega
	 * @param entrega1
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Pedido> buscarPedidos (Date pedido, boolean pedido1, Date entrega, boolean entrega1) throws Exception{
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
			//			Pedido p = new Pedido(usuarioActual, prod.get(i), 2, pedido, entrega);
			//			rta.add(p);
		}
		return rta;
	}
	
	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String darNombreProducto (String id) throws Exception{
		return (crud.darSubTabla(Producto.NOMBRE, "nombre", "id = '" + id + "'")).get(0);
	}
	
	/**
	 * @param cantidad
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String> darCantidadProductos (int cantidad) throws Exception{
		ArrayList<String> rta = new ArrayList<String>();
		rta = crud.darSubTabla(Producto.NOMBRE, "nombre", "precio > 0");
		return rta;
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Estacion> darEstaciones() throws Exception{
		ArrayList<Estacion> rta = new ArrayList<Estacion>();
		ResultSet rs = crud.darConexion().createStatement().executeQuery("SELECT * FROM " + Estacion.NOMBRE + "");
		while(rs.next())
		{
			String id = rs.getString(1);
			String nombre = rs.getString(2);
			Estacion estacion = new Estacion(id, nombre);
			rta.add(estacion);
		}
		return rta;
	}

	/**
	 * @param login
	 * @return
	 * @throws Exception
	 */
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
			Pedido pedido = new Pedido(id, idProducto, login, cantidad, fechaPedido, fechaEntrega);
			rta.add(pedido);
		}
		return rta;
	}

	/**
	 * @param producto
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String> darIdPedido (String producto) throws Exception{
		return crud.darSubTabla(Pedido.NOMBRE, "id", "idCliente = " + usuarioActual + "idProducto = " + producto);
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList<MateriaPrima> darMateriasPrimas( ) throws Exception {
		ArrayList<MateriaPrima> rta = new ArrayList<MateriaPrima>();
		ResultSet rs = crud.darConexion().createStatement().executeQuery("SELECT * FROM " + MateriaPrima.NOMBRE + "");
		while(rs.next())
		{
			String id = rs.getString(1);
			String unidadMedida = rs.getString(2);
			int cantidad = rs.getInt(3);
			MateriaPrima estacion = new MateriaPrima(id, unidadMedida, cantidad);
			rta.add(estacion);
		}
		return rta;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Componente> darComponentes( ) throws Exception {
		ArrayList<Componente> rta = new ArrayList<Componente>();
		ResultSet rs = crud.darConexion().createStatement().executeQuery("SELECT * FROM " + Componente.NOMBRE + "");
		while(rs.next())
		{
			String id = rs.getString(1);
			int cantidad = rs.getInt(2);
			Componente estacion = new Componente(id, cantidad);
			rta.add(estacion);
		}
		return rta;
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

	/**
	 * @param idProveedor
	 * @return
	 * @throws Exception
	 */
	private ArrayList<Componente> darComponentesProveedor(String idProveedor) throws Exception {
		ArrayList<String> componentesPre = crud.darSubTabla("PROOVEDORESCOMPONENTES", "*", "id_proveedor=idProveedor");
		ArrayList<Componente> componentes = new ArrayList<Componente>();
		for(int i = 0; i < componentesPre.size();i+=Componente.COLUMNAS.length){
			Componente componente = new Componente(componentesPre.get(i),Integer.parseInt(componentesPre.get(i+1)));
			componentes.add(componente);
		}
		return componentes;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Usuario> darClientes() throws Exception{
		ArrayList<Usuario> rta = new ArrayList<Usuario>();
		ArrayList<String> datos = new ArrayList<String>();
		int contador = 0;
		ResultSet rs = crud.darConexion().createStatement().executeQuery("SELECT * FROM " + Usuario.NOMBRE + " WHERE tipo = 'natural' AND tipo = 'juridica'");
		while(rs.next() && contador < 30)
		{
			String login = rs.getString(1);
			String tipo = rs.getString(3);
			String nombre = rs.getString(4);
			String direccion = rs.getString(5);
			int telefono = Integer.parseInt(rs.getString(6));
			String ciudad = rs.getString(7);
			String idRepLegal = rs.getString(8);
			Usuario user = new Usuario(login, tipo, "", nombre, direccion, telefono, ciudad, idRepLegal);
			rta.add(user);
			contador++;
		}
		return rta;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Proveedor> darProveedores() throws Exception {
		ArrayList<Proveedor> rta = new ArrayList<Proveedor>();
		ArrayList<String> datos = new ArrayList<String>();
		int contador = 0;
		ResultSet rs = crud.darConexion().createStatement().executeQuery("SELECT * FROM " + Proveedor.NOMBRE );
		while(rs.next() && contador < 30)
		{
			String pId = rs.getString(1);
			String pDireccion = rs.getString(2);
			int pTelefono = Integer.parseInt(rs.getString(3));
			String pCiudad = rs.getString(4);
			String pIdRepLegal = rs.getString(5);
			List<String[]> pMatrizVolTiempoMat = new ArrayList<>();
			List<String[]> pMatrizVolTiempoComp = new ArrayList<>();
			ArrayList<String> idMateriaPrima = new ArrayList<String>();
			ArrayList<String> idComponente = new ArrayList<String>();
			ArrayList<MateriaPrima> pMateriasPrimas = new ArrayList<MateriaPrima>();
			ArrayList<Componente> pComponentes = new ArrayList<Componente>();
			idMateriaPrima = crud.darTuplas(Proveedor.NOMBRE_RELACION_MATERIA_PRIMA);
			idComponente = crud.darTuplas(Proveedor.NOMBRE_RELACION_COMPONENTE);
			for (String materiaPrima : idMateriaPrima) {
				ResultSet rs_1 = crud.darConexion().createStatement().executeQuery("SELECT * FROM " + MateriaPrima.NOMBRE + " WHERE id = '" + materiaPrima + "'");

			}
			for (String componente : idComponente) {

			}
			Proveedor prov = new Proveedor(pId, pDireccion, pTelefono, pCiudad, pIdRepLegal, pMatrizVolTiempoMat, pMatrizVolTiempoComp, pMateriasPrimas, pComponentes);
			rta.add(prov);
			contador++;
		}
		return rta;
	}
	
	//--------------------------------------------------
	// METODOS ELIMINAR
	//--------------------------------------------------
	
	/**
	 * @param login
	 * @param idPedido
	 * @throws Exception
	 */
	public void eliminarPedidoCliente(String login, String idPedido) throws Exception{
		crud.eliminarTuplaPorId(Pedido.NOMBRE, idPedido);
	}
	
	//--------------------------------------------------
	// MAIN
	//--------------------------------------------------

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AplicacionWeb aplicacionWeb = getInstancia();
		try
		{
			System.out.println(conexion.darNivelAislamiento());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
