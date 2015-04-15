package mundo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Meili
 *
 */
/**
 * @author Meili
 *
 */
/**
 * @author Meili
 *
 */
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
		try
		{
			String[] id = {Integer.toString(++contadorId)};
			crud.insertarTupla(ID, COLUMNAS, TIPO, id);
			return contadorId;
		}
		catch (Exception e)
		{
			return ++contadorId;
		}
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
		int cantidadActual = cantidadInicial;
		try{
			cantidadActual+= Integer.parseInt((crud.darSubTabla(MateriaPrima.NOMBRE, "cantidadInicial", "id='"+id+"'").get(0)));
			String[] columnas = new String[1];
			columnas[0] = "cantidadInicial";
			String[] cantidad = new String[1];
			cantidad[0] = (Integer.toString(cantidadInicial + cantidadActual));
			crud.actualizarTupla(MateriaPrima.NOMBRE,columnas,cantidad, "id= '"+id+"'");
		}
		catch(Exception e){
			crud.insertarTupla(MateriaPrima.NOMBRE, MateriaPrima.COLUMNAS, MateriaPrima.TIPO, datosSimples);
		}
		for (int i = 0; i < cantidadActual; i++) {
			String[] datosRegistro = {Integer.toString(darContadorId()), id};
			crud.insertarTupla(MateriaPrima.NOMBRE_REGISTRO_MATERIAS_PRIMAS, MateriaPrima.COLUMNAS_REGISTRO_MATERIAS_PRIMAS, MateriaPrima.TIPO_REGISTRO_MATERIAS_PRIMAS, datosRegistro);
		}
	}

	/**
	 * @param id
	 * @param cantidadInicial
	 * @throws Exception
	 */
	public void registrarComponente (String id, int cantidadInicial) throws Exception {
		String[] datosSimples = {id, Integer.toString(cantidadInicial)};
		int cantidadActual = cantidadInicial;
		try{
			cantidadActual+= Integer.parseInt((crud.darSubTabla(Componente.NOMBRE, "cantidadInicial", "id="+id).get(0)));
			String[] columnas = new String[1];
			columnas[0] = "cantidadInicial";
			String[] cantidad = new String[1];
			cantidad[0] = (Integer.toString(cantidadInicial + cantidadActual));
			crud.actualizarTupla(MateriaPrima.NOMBRE,columnas,cantidad, "id="+id);
		}
		catch(Exception e){
			crud.insertarTupla(Componente.NOMBRE, Componente.COLUMNAS, Componente.TIPO, datosSimples);		}
		for (int i = 0; i < cantidadActual; i++) {
			String[] datosRegistro = {Integer.toString(darContadorId()), id};
			crud.insertarTupla(MateriaPrima.NOMBRE_REGISTRO_MATERIAS_PRIMAS, MateriaPrima.COLUMNAS_REGISTRO_MATERIAS_PRIMAS, MateriaPrima.TIPO_REGISTRO_MATERIAS_PRIMAS, datosRegistro);
		}
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
	public Date registrarPedido (String login, String idProducto, int cantidad, Date fechaPedido) throws Exception{
		System.out.println(login + " - " + idProducto + " - " + cantidad + " - " + fechaPedido.toLocaleString());
		ArrayList<Etapa> etapas = new ArrayList<Etapa>();
		String idPedido = Integer.toString(darContadorId());
		conexion.setAutoCommitFalso();
		Savepoint save = conexion.darConexion().setSavepoint();
		etapas = obtenerEtapas(idProducto);
		Date fechaEntrega = null;
		String[] datosPedido = {idPedido,login,idProducto,Integer.toString(fechaPedido.getDay()),Integer.toString(fechaPedido.getMonth()),Integer.toString(fechaPedido.getDay()),Integer.toString(fechaPedido.getDay()),Integer.toString(cantidad)};
		crud.insertarTupla(Pedido.NOMBRE, Pedido.COLUMNAS, Pedido.TIPO, datosPedido);
		for(Etapa etapa : etapas){
			try{
				fechaEntrega = verificarExistencias(idProducto,etapa,cantidad,etapas.size(),idPedido);
			}
			catch(Exception e){
				conexion.darConexion().rollback(save);
				e.printStackTrace();
			}
		}
		conexion.darConexion().commit();
		conexion.setAutoCommitVerdadero();
		return fechaEntrega;
	}	
	
	/**
	 * @param idProducto
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Etapa> obtenerEtapas (String idProducto) throws Exception{
		ArrayList<Etapa> etapas = new ArrayList<Etapa>();
		ResultSet rs_etapas = crud.darConexion().createStatement().executeQuery("SELECT * FROM " + Etapa.NOMBRE + " WHERE idProducto = '" + idProducto + "'");
		while(rs_etapas.next()){
			String idEtapa = rs_etapas.getString(1);
			String nombre = rs_etapas.getString(2);
			String idEstacion = rs_etapas.getString(4);
			String idMateriaPrima = rs_etapas.getString(5);
			String idComponente = rs_etapas.getString(6);
			int duracion = rs_etapas.getInt(7);
			int numeroSecuencia = rs_etapas.getInt(8);
			String idAnterior = rs_etapas.getString(9);
			Etapa etapa = new Etapa(idEtapa, nombre, idProducto, idEstacion, idMateriaPrima, idComponente, duracion, numeroSecuencia, idAnterior);
			etapas.add(etapa);
			System.out.println(etapa.toString());
		}
		return etapas;
	}
	
	
	/**
	 * @param idProducto
	 * @param etapa
	 * @param cantidad
	 * @param ultimaEtapa
	 * @param login
	 * @param fechaPedido
	 * @param idPedido
	 * @return
	 * @throws Exception
	 */
	public Date verificarExistencias (String idProducto, Etapa etapa, int cantidad, int ultimaEtapa, String idPedido) throws Exception{
		
		String verificarEstacionesText = "SELECT a.id FROM " + Estacion.NOMBRE_REGISTRO_ESTACIONES + " a WHERE a.idEstacion = '" + etapa.getIdEstacion() + "' AND NOT EXISTS (SELECT b.id FROM " + Producto.NOMBRE_REGISTRO_PRODUCTOS + " b WHERE idRegistroEstacion = a.id) ORDER BY a.dia,a.mes";
		System.out.println(verificarEstacionesText);
		ResultSet rs_verificarEstaciones = crud.darConexion().createStatement().executeQuery(verificarEstacionesText);
		
		String verificarMateriasPrimasText = "SELECT a.id FROM " + MateriaPrima.NOMBRE_REGISTRO_MATERIAS_PRIMAS + " a WHERE a.idMateriaPrima = '" + etapa.getIdMateriaPrima() + "' AND NOT EXISTS (SELECT b.id FROM " + Producto.NOMBRE_REGISTRO_PRODUCTOS + " b WHERE idRegistroMateriaPrima = a.id)";
		System.out.println(verificarMateriasPrimasText);
		ResultSet rs_verificarMateriasPrimas =  crud.darConexion().createStatement().executeQuery(verificarMateriasPrimasText);

		String verificarComponentesText = "SELECT a.id FROM " + Componente.NOMBRE_REGISTRO_COMPONENTES + " a WHERE a.idComponente = '" + etapa.getIdComponente() + "' AND NOT EXISTS (SELECT b.id FROM " + Producto.NOMBRE_REGISTRO_PRODUCTOS + " b WHERE idRegistroComponente = a.id)";
		System.out.println(verificarComponentesText);
		ResultSet rs_verificarComponentes =  crud.darConexion().createStatement().executeQuery(verificarComponentesText);
		
		Date fechaEntrega = null;

		for(int i = 0; i < cantidad; i++){
			rs_verificarEstaciones.next();
			rs_verificarMateriasPrimas.next();
			rs_verificarComponentes.next();
			
			System.out.println(rs_verificarEstaciones.getString(1));
			System.out.println(rs_verificarMateriasPrimas.getString(1));
			System.out.println(rs_verificarComponentes.getString(1));
			
			String idRegProd = Integer.toString(darContadorId());
			String[] datosRegProd = {idRegProd,etapa.getId(),rs_verificarEstaciones.getString(1),rs_verificarMateriasPrimas.getString(1),rs_verificarComponentes.getString(1)};
			crud.insertarTupla(Producto.NOMBRE_REGISTRO_PRODUCTOS, Producto.COLUMNAS_REGISTRO_PRODUCTOS, Producto.TIPO_REGISTRO_PRODUCTOS, datosRegProd);
			if(etapa.getNumeroSecuencia() == ultimaEtapa){
				if(i==cantidad-1){
					String hallarFechaEntregaText = "SELECT a.dia, a.mes FROM " + Estacion.NOMBRE_REGISTRO_ESTACIONES + " a WHERE a.id = '" + rs_verificarEstaciones.getString(1) + "'";
					ResultSet rs_hallarFechaEntrega =  crud.darConexion().createStatement().executeQuery(hallarFechaEntregaText);
					rs_hallarFechaEntrega.next();				
					int diaEntrega = Integer.parseInt(rs_hallarFechaEntrega.getString(1));
					int mesEntrega = Integer.parseInt(rs_hallarFechaEntrega.getString(2));
					Calendar calendario = Calendar.getInstance();
					calendario.setTime(new Date(2015, mesEntrega, diaEntrega));
					calendario.add(Calendar.DATE, 1);
					fechaEntrega = calendario.getTime();
					String actualizarFechaEntrega = "UPDATE pedidos SET diaEntrega = " + Integer.toString(fechaEntrega.getDay()) + ", mesEntrega = " + Integer.toString(fechaEntrega.getMonth()) + " WHERE id = '" + idPedido + "'";
					crud.darConexion().createStatement().executeQuery(actualizarFechaEntrega);
				}
				String[] datosInventario = {idRegProd,etapa.getIdProducto(),idPedido};
				crud.insertarTupla(Producto.NOMBRE_INVENTARIO_PRODUCTOS, Producto.COLUMNAS_INVENTARIO_PRODUCTOS, Producto.TIPO_INVENTARIO_PRODUCTOS, datosInventario);
			}
		}
		return fechaEntrega;
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
	public ArrayList<Producto> buscarProducto (String nombre) throws Exception{
		ArrayList<Producto> rta = new ArrayList<Producto>();
		ResultSet rs = crud.darConexion().createStatement().executeQuery("SELECT DISTINCT * FROM " + Producto.NOMBRE + " WHERE UPPER(nombre) LIKE UPPER('%" + nombre + "%')");
		while(rs.next())
		{
			String id = rs.getString(1);
			String pNombre = rs.getString(2);
			int precio = rs.getInt(3);
			Producto producto = new Producto(id, pNombre, precio);
			rta.add(producto);
		}
		return rta;
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
		System.out.println("El id del producto es: " + id);
		return (crud.darSubTabla(Producto.NOMBRE, "nombre", "id = '" + id + "'")).get(0);
	}
	
	/**
	 * @param cantidad
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Producto> darCantidadProductos (int cantidad) throws Exception{
		ArrayList<Producto> rta = new ArrayList<Producto>();
		ResultSet rs = crud.darConexion().createStatement().executeQuery("SELECT * FROM " + Producto.NOMBRE + "");
		while(rs.next())
		{
			String id = rs.getString(1);
			String nombre = rs.getString(2);
			int precio = rs.getInt(3);
			Producto producto = new Producto(id, nombre, precio);
			rta.add(producto);
		}
		return rta;
	}
	
	/**
	 * 
	 * @return
	 */
	public String darUsuarioActual(){
		return usuarioActual;
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
		String sql = "SELECT * FROM " + Pedido.NOMBRE + " WHERE idUsuario = '" + login + "'";
		System.out.println(sql);
		ResultSet rs = s.executeQuery(sql);
		while(rs.next()){
			String id = rs.getString(1);
			String idProducto = rs.getString(2);
			String idUsuario = rs.getString(3);
			int diaPedido = rs.getInt(4);
			int mesPedido = rs.getInt(5);
			int diaEntrega = rs.getInt(6);
			int mesEntrega = rs.getInt(7);
			int cantidad = rs.getInt(8);
			Date fechaPedido = new Date(2015, mesPedido, diaPedido);
			Date fechaEntrega = new Date(2015, mesEntrega, diaEntrega);
			Pedido pedido = new Pedido(id, idProducto, login, cantidad, fechaPedido, fechaEntrega);
			System.out.println(pedido.toString());
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
	
	public ArrayList<Producto> darProducto(String id) throws Exception{
		ArrayList<Producto> rta = new ArrayList<Producto>();
		String sql = "SELECT * FROM " + Producto.NOMBRE + " WHERE id = '" + id + "'";
		System.out.println(sql);
		ResultSet rs = crud.darConexion().createStatement().executeQuery(sql);
		while(rs.next())
		{
			String nombre = rs.getString(2);
			int precio = rs.getInt(3);
			Producto producto = new Producto(id, nombre, precio);
			rta.add(producto);
		}
		return rta;
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

	public ArrayList<MateriaPrima> darMateriasPrimasProveedor(String idProveedor) throws Exception{
		ArrayList<String> idsMateriasPrimas = crud.darSubTabla(Proveedor.NOMBRE_RELACION_MATERIA_PRIMA, "id_materiaPrima", "id_proveedor = '" + idProveedor + "'");
		ArrayList<MateriaPrima> rta = new ArrayList<MateriaPrima>();
		for (int i = 0; i < idsMateriasPrimas.size(); i++) {
			ResultSet rs = crud.darConexion().createStatement().executeQuery("SELECT * FROM " + MateriaPrima.NOMBRE + " WHERE id = '" + idsMateriasPrimas.get(i) + "'");
			while(rs.next())
			{
				String id = rs.getString(1);
				String unidadMedida = rs.getString(2);
				int cantidad = rs.getInt(3);
				MateriaPrima estacion = new MateriaPrima(id, unidadMedida, cantidad);
				rta.add(estacion);
			}
		}
		return rta;
	}

	/**
	 * @param idProveedor
	 * @return
	 * @throws Exception
	 */
	private ArrayList<Componente> darComponentesProveedor(String idProveedor) throws Exception {
		ArrayList<String> idsComponentes = crud.darSubTabla(Proveedor.NOMBRE_RELACION_COMPONENTE, "id_componente", "id_proveedor = '" + idProveedor + "'");
		ArrayList<Componente> rta = new ArrayList<Componente>();
		for (int i = 0; i < idsComponentes.size(); i++) {
			ResultSet rs = crud.darConexion().createStatement().executeQuery("SELECT * FROM " + Componente.NOMBRE + " WHERE id = '" + idsComponentes.get(i) + "'");
			while(rs.next())
			{
				String id = rs.getString(1);
				int cantidad = rs.getInt(2);
				Componente estacion = new Componente(id, cantidad);
				rta.add(estacion);
			}
		}
		return rta;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Usuario> darClientes() throws Exception{
		ArrayList<Usuario> rta = new ArrayList<Usuario>();
		ResultSet rs = crud.darConexion().createStatement().executeQuery("SELECT * FROM " + Usuario.NOMBRE + " WHERE tipo = 'natural' AND tipo = 'juridica'");
		while(rs.next())
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
		}
		return rta;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Proveedor> darProveedores() throws Exception {
		ArrayList<Proveedor> rta = new ArrayList<Proveedor>();
		ResultSet rs = crud.darConexion().createStatement().executeQuery("SELECT * FROM " + Proveedor.NOMBRE );
		while(rs.next())
		{
			String pId = rs.getString(1);
			String pDireccion = rs.getString(2);
			int pTelefono = Integer.parseInt(rs.getString(3));
			String pCiudad = rs.getString(4);
			String pIdRepLegal = rs.getString(5);
			ArrayList<String> idMateriaPrima = new ArrayList<String>();
			ArrayList<String> idComponente = new ArrayList<String>();
			ArrayList<MateriaPrima> pMateriasPrimas = new ArrayList<MateriaPrima>();
			ArrayList<Componente> pComponentes = new ArrayList<Componente>();
			idMateriaPrima = crud.darTuplas(Proveedor.NOMBRE_RELACION_MATERIA_PRIMA);
			idComponente = crud.darTuplas(Proveedor.NOMBRE_RELACION_COMPONENTE);
			for (String materiaPrima : idMateriaPrima) {
				ResultSet rs_1 = crud.darConexion().createStatement().executeQuery("SELECT * FROM " + MateriaPrima.NOMBRE + " WHERE id = '" + materiaPrima + "'");
				while(rs_1.next())
				{
					String id = rs_1.getString(1);
					String unidadMedida = rs_1.getString(2);
					int cantidadInicial = rs_1.getInt(3);
					MateriaPrima m = new MateriaPrima(id, unidadMedida, cantidadInicial);
					pMateriasPrimas.add(m);
				}
			}
			for (String componente : idComponente) {
				ResultSet rs_1 = crud.darConexion().createStatement().executeQuery("SELECT * FROM " + Componente.NOMBRE + " WHERE id = '" + componente + "'");
				while(rs_1.next())
				{
					String id = rs_1.getString(1);
					int cantidadInicial = rs_1.getInt(2);
					Componente c = new Componente(id, cantidadInicial);
					pComponentes.add(c);
				}
			}
			Proveedor prov = new Proveedor(pId, pDireccion, pTelefono, pCiudad, pIdRepLegal, pMateriasPrimas, pComponentes);
			rta.add(prov);
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
		conexion.setAutoCommitFalso();
		Savepoint save = conexion.darConexion().setSavepoint();
		try
		{
			crud.eliminarTupla(Producto.NOMBRE_INVENTARIO_PRODUCTOS, "idPedido = '" + idPedido + "'");
			crud.eliminarTuplaPorId(Pedido.NOMBRE, idPedido);
		}
		catch(Exception e){
			conexion.darConexion().rollback(save);
			e.printStackTrace();
		}
		conexion.darConexion().commit();
		conexion.setAutoCommitVerdadero();
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
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
