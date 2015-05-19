package mundo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.*;
import javax.sql.*;
import oracle.jdbc.*;
import oracle.jdbc.pool.*;
import oracle.jdbc.xa.OracleXid;
import oracle.jdbc.xa.OracleXAException;
import oracle.jdbc.xa.client.*;
import javax.transaction.xa.*;



public class ConexionDAO
{	

	/**
	 * Conexi�n
	 */
	private Connection conexion1;
	private Connection conexion2;
	
	/**
	 * Usuario
	 */
	private String usuario1;
	private String usuario2;
	
	/**
	 * Constrase�a
	 */
	private String contrasenia1;
	private String contrasenia2;
	
	/**
	 * URL
	 */
	private String url1;
	private String url2;
	
	/**
	 * Conexiones XA
	 */
	private XAConnection pc1;
	private XAConnection pc2;
	
	/**
	 * Recursos XA
	 */
	private XAResource oxar1;
	private XAResource oxar2;
	
	
	/**
	 * Class name
	 */
	public final static String CLASS_NAME = "oracle.jdbc.driver.OracleDriver";
	
	/**
	 * Constructor de la clase
	 */
	public ConexionDAO (){
		
		usuario1 = "ISIS2304311510";
		contrasenia1 = "tquz5";
		url1 = "jdbc:oracle:thin:@prod.oracle.virtual.uniandes.edu.co:1531:prod";
		
		usuario2 ="ISIS2304401510";
		contrasenia2="sveinlg";
		url2 = "jdbc:oracle:thin:@prod.oracle.virtual.uniandes.edu.co:1531:prod";
	}
	
	/**
	 * Constructor de la clase
	 */
	public ConexionDAO (String url1, String usuario1, String contrasenia1, String url2, String usuario2, String contrasenia2){
		
		this.usuario1 = usuario1;
		this.contrasenia1 = contrasenia1;
		this.url1 = url1;
		
		this.usuario2 = usuario2;
		this.contrasenia2 = contrasenia2;
		this.url2 = url2;
	}
	
	/**
	 * Retorna la conexion1
	 * @return la conexion1
	 */
	public Connection darConexion1(){
		return conexion1;
	}
	
	/**
	 * Retorna la conexion2
	 * @return la conexion2
	 */
	public Connection darConexion2(){
		return conexion2;
	}
	
	/**
	 * Inicia la conexi�n con la base de datos
	 */
	public void iniciarConexion (){
		try
		{
			Class.forName(CLASS_NAME);
			
	        OracleXADataSource oxds1 = new OracleXADataSource();
	        oxds1.setURL(url1);
	        oxds1.setUser(usuario1);
	        oxds1.setPassword(contrasenia1);
	        
	        OracleXADataSource oxds2 = new OracleXADataSource();
	        oxds2.setURL(url2);
	        oxds2.setUser(usuario2);
	        oxds2.setPassword(contrasenia2);
			
	        pc1  = oxds1.getXAConnection();
	        pc2  = oxds2.getXAConnection();
	        
	        conexion1 = pc1.getConnection();
	        conexion2 = pc2.getConnection();
	        
	        oxar1 = pc1.getXAResource();
	        oxar2 = pc2.getXAResource();
	        
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Xid[] iniciarTransaccionDistribuida() throws XAException{
		Xid[] xids = new Xid[2]; 
		try{
	        Xid xid1 = createXid(1);
	        xids[0] = xid1;
	        Xid xid2 = createXid(2);
	        xids[1] = xid2;
	        
	        oxar1.start (xid1, XAResource.TMNOFLAGS);
	        oxar2.start (xid2, XAResource.TMNOFLAGS);
	        
	        return xids;
	        
		}
		catch (XAException e) {
			e.printStackTrace();
		}
		return xids;

	}
	
	public void finalizarTransaccionDistribuida(Xid xid1, Xid xid2) throws XAException{
		try{
	        oxar1.end(xid1, XAResource.TMSUCCESS);
	        oxar2.end(xid2, XAResource.TMSUCCESS);

	        int prp1 =  oxar1.prepare (xid1);
	        int prp2 =  oxar2.prepare (xid2);
	        
	        boolean do_commit = true;

	        if (!((prp1 == XAResource.XA_OK) || (prp1 == XAResource.XA_RDONLY)))
	           do_commit = false;

	        if (!((prp2 == XAResource.XA_OK) || (prp2 == XAResource.XA_RDONLY)))
	           do_commit = false;

	       System.out.println("do_commit is " + do_commit);
	       System.out.println("Is oxar1 same as oxar2 ? " + oxar1.isSameRM(oxar2));

	        if (prp1 == XAResource.XA_OK)
	          if (do_commit)
	             oxar1.commit (xid1, false);
	          else
	             oxar1.rollback (xid1);

	        if (prp2 == XAResource.XA_OK)
	          if (do_commit)
	             oxar2.commit (xid2, false);
	          else
	             oxar2.rollback (xid2);
	        
		}
		catch (XAException e) {
			e.printStackTrace();
		}

	}
	
    static Xid createXid(int bids) throws XAException
    {
    	byte gID[] = new byte[5];
    	gID[0] = 0;
    	gID[1] = 0;
    	gID[2] = 0;
    	gID[3] = 0;
    	gID[4] = 0;
    	
    	Xid id = new OracleXid(bids,gID,gID);
    	return id;
    	
    }

	/**
	 * Cierra la conexi�n con la base de datos
	 */
    public void cerrarConexion() throws Exception{        
		try {
			conexion1.close();
			conexion1 = null;
		} catch (SQLException exception) {
			throw new Exception("Error al intentar cerrar la conexi�n.");
		}
    }
    
    /**
     * Da el nivel de aislamiento implementado
     */
    public int darNivelAislamiento(){
    	try{
        	return conexion1.getTransactionIsolation();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
		return 0;
    }
    
    /**
     * Cambia la propiedad de autocommit de la conexión a falso
     */
    public void setAutoCommitFalso(){
    	try{
        	conexion1.setAutoCommit(false);
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    /**
     * Cambia la propiedad de autocommit de la conexión a verdadero
     */
    public void setAutoCommitVerdadero(){
    	try{
        	conexion1.setAutoCommit(true);
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    }
    

	/**
	 * Crea las tablas
	 */
	public void crearTablas (){
		try 
		{
			// TABLA 1: PRODUCTOS ------------------------------------------------------------
		
			Statement s = conexion1.createStatement( );
			
			boolean crearTabla = false;
			
			try
			{
				// Verificar si ya existe la tabla
				s.executeQuery( "SELECT * FROM productos" );
			}
			catch( SQLException se )
			{
				// La excepci�n se lanza si la tabla no existe
				crearTabla = true;
			}
			
			// Se crea una nueva tabla vac�a
			if( crearTabla )
			{
				s.execute( "CREATE TABLE productos (id varchar(32), nombre varchar(32), precio int, PRIMARY KEY (id))" );
				System.out.println("Se cre� la tabla productos");
			}
			else
			System.out.println("La tabla productos ya existe");
			s.close();
			
			
			// TABLA 2: PROVEEDORES ------------------------------------------------------------

			crearTabla = false;

			s = conexion1.createStatement( );
			try
			{
				// Verificar si ya existe la tabla
				s.executeQuery( "SELECT * FROM proveedores" );
			}
			catch( SQLException se )
			{
				// La excepci�n se lanza si la tabla no existe
				crearTabla = true;
			}

			// Se crea una nueva tabla vac�a
			if( crearTabla )
			{
				s.execute( "CREATE TABLE proveedores (id varchar(32), direccion varchar(32), telefono varchar(32), ciudad varchar(32), idRepLegal varchar(32), PRIMARY KEY (id))" );
				System.out.println("Se cre� la tabla proveedores");
			}
			else
			System.out.println("La tabla proveedores ya existe");
			s.close();
			
			
			// TABLA 3: USUARIOS ------------------------------------------------------------

			crearTabla = false;

			s = conexion1.createStatement( );
			try
			{
				// Verificar si ya existe la tabla
				s.executeQuery( "SELECT * FROM usuarios" );
			}
			catch( SQLException se )
			{
				// La excepci�n se lanza si la tabla no existe
				crearTabla = true;
			}

			// Se crea una nueva tabla vac�a
			if( crearTabla )
			{
				s.execute( "CREATE TABLE usuarios (login varchar(32), password varchar(32), tipo varchar(32), nombre varchar(32), direccion varchar(32), telefono int, ciudad varchar(32), idRepLegal varchar(32), PRIMARY KEY (login))" );
				System.out.println("Se cre� la tabla usuarios");
			}
			else
			System.out.println("La tabla usuarios ya existe");
			s.close();
			
			
			// TABLA 4: MATERIAS PRIMAS ------------------------------------------------------------

			s = conexion1.createStatement( );
			try
			{
				// Verificar si ya existe la tabla
				s.executeQuery( "SELECT * FROM materiasPrimas" );
			}
			catch( SQLException se )
			{
				// La excepci�n se lanza si la tabla no existe
				crearTabla = true;
			}

			// Se crea una nueva tabla vac�a
			if( crearTabla )
			{
				s.execute( "CREATE TABLE materiasPrimas (id varchar(32), unidadMedida varchar(32), cantidadInicial int, PRIMARY KEY (id))" );
				System.out.println("Se cre� la tabla materiasPrima");
			}
			else
			System.out.println("La tabla materiasPrimas ya existe");
			s.close();
			
			
			// TABLA 5: COMPONENTES  ------------------------------------------------------------
			
			crearTabla = false;

			s = conexion1.createStatement( );
			try
			{
				// Verificar si ya existe la tabla
				s.executeQuery( "SELECT * FROM componentes" );
			}
			catch( SQLException se )
			{
				// La excepci�n se lanza si la tabla no existe
				crearTabla = true;
			}

			// Se crea una nueva tabla vac�a
			if( crearTabla )
			{
				s.execute( "CREATE TABLE componentes (id varchar(32), unidadMedida varchar(32), cantidadInicial int, PRIMARY KEY (id))" );
				System.out.println("Se cre� la tabla componentes");
			}
			else
			System.out.println("La tabla componentes ya existe");
			s.close();
			
			
			// TABLA 6: PROVEEDORES MATERIAS PRIMAS ------------------------------------------------------------
			
			crearTabla = false;
			
			s = conexion1.createStatement( );
			try
			{
				// Verificar si ya existe la tabla
				s.executeQuery( "SELECT * FROM ProveedoresMateriasPrimas" );
			}
			catch( SQLException se )
			{
				// La excepci�n se lanza si la tabla no existe
				crearTabla = true;
			}

			// Se crea una nueva tabla vac�a
			if( crearTabla )
			{
				s.execute( "CREATE TABLE ProveedoresMateriasPrimas (id_proveedor varchar(32), id_materiaPrima varchar(32), PRIMARY KEY (id_proveedor,id_materiaPrima), CONSTRAINT fk_idProveedorMateriaPrima FOREIGN KEY (id_proveedor) REFERENCES proveedores(id), CONSTRAINT fk_idMateriasPrimasProveedor FOREIGN KEY (id_materiaPrima) REFERENCES materiasPrimas(id))" );
				System.out.println("Se cre� la tabla ProveedoresMateriasPrimas");
			}
			else
			System.out.println("La tabla componentes ProveedoresMateriasPrimas");
			s.close();
			
			
			// TABLA 7: PROVEEDORES COMPONENTES ------------------------------------------------------------

			crearTabla = false;
			
			s = conexion1.createStatement( );
			try
			{
				// Verificar si ya existe la tabla
				s.executeQuery( "SELECT * FROM ProveedoresComponentes" );
			}
			catch( SQLException se )
			{
				// La excepci�n se lanza si la tabla no existe
				crearTabla = true;
			}

			// Se crea una nueva tabla vac�a
			if( crearTabla )
			{
				s.execute( "CREATE TABLE ProveedoresComponentes (id_proveedor varchar(32), id_componente varchar(32), PRIMARY KEY (id_proveedor,id_componente), CONSTRAINT fk_idProveCompo FOREIGN KEY (id_proveedor) REFERENCES proveedores(id), CONSTRAINT fk_idComponentesProveedor FOREIGN KEY (id_componente) REFERENCES componentes(id))" );
				System.out.println("Se cre� la tabla ProveedoresComponentes");
			}
			else
			System.out.println("La tabla ProveedoresComponentes ya existe");
			s.close();
			
			
			// TABLA 8: GENERADORID  ------------------------------------------------------------

			crearTabla = false;
			
			s = conexion1.createStatement( );
			try
			{
				// Verificar si ya existe la tabla
				s.executeQuery( "SELECT * FROM generadorId" );
			}
			catch( SQLException se )
			{
				// La excepci�n se lanza si la tabla no existe
				crearTabla = true;
			}

			// Se crea una nueva tabla vac�a
			if( crearTabla )
			{
				s.execute( "CREATE TABLE generadorId (id varchar(32), PRIMARY KEY (id))" );
				System.out.println("Se cre� la tabla generadorId");
			}
			else
				System.out.println("La tabla generadorId ya existe");
			s.close();
			
			
			// TABLA 9: ESTACIONES ------------------------------------------------------------

			crearTabla = false;
			
			s = conexion1.createStatement( );
			try
			{
				// Verificar si ya existe la tabla
				s.executeQuery( "SELECT * FROM estaciones" );
			}
			catch( SQLException se )
			{
				// La excepci�n se lanza si la tabla no existe
				crearTabla = true;
			}

			// Se crea una nueva tabla vac�a
			if( crearTabla )
			{
				s.execute( "CREATE TABLE estaciones (id varchar(32), nombre varchar(32), tipo varchar(32), activada int, PRIMARY KEY (id))" );
				System.out.println("Se cre� la tabla estaciones");
			}
			else
			System.out.println("La tabla estaciones ya existe");
			s.close();
			
			
			// TABLA 10: REGISTROS ESTACIONES ------------------------------------------------------------

			crearTabla = false;
			
			s = conexion1.createStatement( );
			try
			{
				// Verificar si ya existe la tabla
				s.executeQuery( "SELECT * FROM registrosEstaciones" );
			}
			catch( SQLException se )
			{
				// La excepci�n se lanza si la tabla no existe
				crearTabla = true;
			}

			// Se crea una nueva tabla vac�a
			if( crearTabla )
			{
				s.execute( "CREATE TABLE registrosEstaciones (id varchar(32), idEstacion varchar(32), dia int, mes int, PRIMARY KEY (id), CONSTRAINT fk_idEstacionRegistro FOREIGN KEY (idEstacion) REFERENCES estaciones(id), CONSTRAINT unq_EtapaRegistroEstacion UNIQUE (idEstacion,dia,mes))" );
				System.out.println("Se cre� la tabla registrosEstaciones");
			}
			else
			System.out.println("La tabla registrosEstaciones ya existe");
			s.close();
			
			
			// TABLA 11: REGISTROS MATERIAS PRIMAS ------------------------------------------------------------

			crearTabla = false;
			
			s = conexion1.createStatement( );
			try
			{
				// Verificar si ya existe la tabla
				s.executeQuery( "SELECT * FROM registrosMateriasPrimas" );
			}
			catch( SQLException se )
			{
				// La excepci�n se lanza si la tabla no existe
				crearTabla = true;
			}

			// Se crea una nueva tabla vac�a
			if( crearTabla )
			{
				s.execute( "CREATE TABLE registrosMateriasPrimas (id varchar(32), idMateriaPrima varchar(32), PRIMARY KEY (id), CONSTRAINT fk_idMateriaPrimaRegistro FOREIGN KEY (idMateriaPrima) REFERENCES materiasPrimas(id))" );
				System.out.println("Se cre� la tabla registrosMateriasPrimas");
			}
			else
			System.out.println("La tabla registrosMateriasPrimas ya existe");
			s.close();
			
			
			// TABLA 12: REGISTROS COMPONENTES ------------------------------------------------------------

			crearTabla = false;
			
			s = conexion1.createStatement( );
			try
			{
				// Verificar si ya existe la tabla
				s.executeQuery( "SELECT * FROM registrosComponentes" );
			}
			catch( SQLException se )
			{
				// La excepci�n se lanza si la tabla no existe
				crearTabla = true;
			}

			// Se crea una nueva tabla vac�a
			if( crearTabla )
			{
				s.execute( "CREATE TABLE registrosComponentes (id varchar(32), idComponente varchar(32), PRIMARY KEY (id), CONSTRAINT fk_idComponenteRegistro FOREIGN KEY (idComponente) REFERENCES componentes(id))" );
				System.out.println("Se cre� la tabla registrosComponentes");
			}
			else
			System.out.println("La tabla registrosComponentes ya existe");
			s.close();
			
			
			// TABLA 13: ETAPAS ------------------------------------------------------------

			crearTabla = false;
			
			s = conexion1.createStatement( );
			try
			{
				// Verificar si ya existe la tabla
				s.executeQuery( "SELECT * FROM etapas" );
			}
			catch( SQLException se )
			{
				// La excepci�n se lanza si la tabla no existe
				crearTabla = true;
			}

			// Se crea una nueva tabla vac�a
	
			if( crearTabla )
			{
				s.execute( "CREATE TABLE etapas (id varchar(32),nombre varchar(32), idProducto varchar(32), idEstacion varchar(32), idMateriaPrima varchar(32), idComponente varchar(32), duracion int, numeroSecuencia int, idAnterior varchar(32), PRIMARY KEY (id), CONSTRAINT fk_idProductoEtapa FOREIGN KEY (idProducto) REFERENCES productos(id), CONSTRAINT fk_idEstacionEtapa FOREIGN KEY (idEstacion) REFERENCES estaciones(id), CONSTRAINT fk_idMateriaPrimaEtapa FOREIGN KEY (idMateriaPrima) REFERENCES materiasPrimas(id), CONSTRAINT fk_idComponenteEtapa FOREIGN KEY (idComponente) REFERENCES componentes(id), CONSTRAINT unq_ofertaUnicaEtapa UNIQUE (idProducto,numeroSecuencia))" );
				System.out.println("Se cre� la tabla etapas");
			}
			else
			System.out.println("La tabla etapas ya existe");
			s.close();
			
			
			// TABLA 14: PEDIDOS ------------------------------------------------------------
			
			crearTabla = false;
			
			s = conexion1.createStatement( );
			try
			{
				// Verificar si ya existe la tabla
				s.executeQuery( "SELECT * FROM pedidos" );
			}
			catch( SQLException se )
			{
				// La excepci�n se lanza si la tabla no existe
				crearTabla = true;
			}

			// Se crea una nueva tabla vac�a
			if( crearTabla )
			{
				s.execute( "CREATE TABLE pedidos (id varchar(32), idProducto varchar(32), idUsuario varchar(32), diaPedido int, mesPedido int, diaEntrega int, mesEntrega int, cantidad int, PRIMARY KEY (id),CONSTRAINT fk_idCliPed FOREIGN KEY (idUsuario) REFERENCES usuarios(login))" );
				System.out.println("Se cre� la tabla pedidos");
			}
			else
			System.out.println("La tabla pedidos ya existe");
			s.close();	
			
			crearTabla = false;
			
			s = conexion1.createStatement( );
			try
			{
				// Verificar si ya existe la tabla
				s.executeQuery( "SELECT * FROM inventarioProductos" );
			}
			catch( SQLException se )
			{
				// La excepci�n se lanza si la tabla no existe
				crearTabla = true;
			}

			// Se crea una nueva tabla vac�a
			if( crearTabla )
			{
				s.execute( "CREATE TABLE inventarioProductos (id varchar(32), idProducto varchar(32), idPedido varchar(32), PRIMARY KEY (id), CONSTRAINT fk_idProductoInventario FOREIGN KEY (idProducto) REFERENCES productos(id),CONSTRAINT fk_idPedidoInven FOREIGN KEY (idPedido) REFERENCES pedidos(id))" );
				System.out.println("Se cre� la tabla inventarioProductos");
			}
			else
			System.out.println("La tabla inventarioProductos ya existe");
			s.close();
		
			
			// TABLA 15: INVENTARIO PRODUCTOS ------------------------------------------------------------

			crearTabla = false;
			
			s = conexion1.createStatement( );
			try
			{
				// Verificar si ya existe la tabla
				s.executeQuery( "SELECT * FROM inventarioProductos" );
			}
			catch( SQLException se )
			{
				// La excepci�n se lanza si la tabla no existe
				crearTabla = true;
			}

			// Se crea una nueva tabla vac�a
			if( crearTabla )
			{
				s.execute( "CREATE TABLE inventarioProductos (id varchar(32), idProducto varchar(32), idPedido varchar(32), PRIMARY KEY (id), CONSTRAINT fk_idProductoInventario FOREIGN KEY (idProducto) REFERENCES productos(id),CONSTRAINT fk_idPedidoInven FOREIGN KEY (idPedido) REFERENCES pedidos(id))" );
				System.out.println("Se cre� la tabla inventarioProductos");
			}
			else
			System.out.println("La tabla inventarioProductos ya existe");
			s.close();	
			

			// TABLA 16: REGISTROS PRODUCTOS ------------------------------------------------------------
			
			crearTabla = false;
			
			s = conexion1.createStatement( );
			try
			{
				// Verificar si ya existe la tabla
				s.executeQuery( "SELECT * FROM registrosProductos" );
			}
			catch( SQLException se )
			{
				// La excepci�n se lanza si la tabla no existe
				crearTabla = true;
			}

			// Se crea una nueva tabla vac�a
			if( crearTabla )
			{
				s.execute( "CREATE TABLE registrosProductos (id varchar(32), idEtapa varchar(32), idInventario varchar(32), idRegistroEstacion varchar(32), idRegistroMateriaPrima varchar(32), idRegistroComponente varchar(32), PRIMARY KEY (id), CONSTRAINT fk_idEtapaReg FOREIGN KEY (idEtapa) REFERENCES etapas(id), CONSTRAINT fk_idInventarioReg FOREIGN KEY (idInventario) REFERENCES inventarioProductos(id), CONSTRAINT fk_idRegistroEstacion FOREIGN KEY (idRegistroEstacion) REFERENCES registrosEstaciones(id), CONSTRAINT fk_idRegistrosMateriaPrima FOREIGN KEY (idRegistroMateriaPrima) REFERENCES registrosMateriasPrimas(id), CONSTRAINT fk_idRegistroComponente FOREIGN KEY (idRegistroComponente) REFERENCES registrosComponentes(id),CONSTRAINT unq_registroEstacion UNIQUE (idRegistroEstacion),CONSTRAINT unq_registroMateriaPrima UNIQUE (idRegistroMateriaPrima),CONSTRAINT unq_registroComponente UNIQUE (idRegistroComponente))" );
				System.out.println("Se cre� la tabla registrosProductos");
			}
			else
			System.out.println("La tabla registrosProductos ya existe");
			s.close();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
			
	}
}