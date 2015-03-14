package mundo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class ConexionDAO
{	

	/**
	 * Conexi�n
	 */
	private Connection conexion;
	
	/**
	 * Usuario
	 */
	private String usuario;
	
	/**
	 * Constrase�a
	 */
	private String contrasenia;
	
	/**
	 * URL
	 */
	private String url;
	
	/**
	 * Class name
	 */
	public final static String CLASS_NAME = "oracle.jdbc.driver.OracleDriver";
	
	/**
	 * Constructor de la clase
	 */
	public ConexionDAO (){
		
		usuario = "ISIS2304461510";
		contrasenia = "cjdsault";
		url = "jdbc:oracle:thin:@prod.oracle.virtual.uniandes.edu.co:1531:prod";
	}
	
	/**
	 * Constructor de la clase
	 */
	public ConexionDAO (String url, String usuario, String contrasenia){
		
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.url = url;
	}
	
	/**
	 * Retorna la conexion
	 * @return la conexion
	 */
	public Connection darConexion(){
		return conexion;
	}
	
	/**
	 * Inicia la conexi�n con la base de datos
	 */
	public void iniciarConexion (){
		try
		{
			Class.forName(CLASS_NAME);
			conexion = DriverManager.getConnection(url, usuario, contrasenia);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Cierra la conexi�n con la base de datos
	 */
    public void cerrarConexion() throws Exception{        
		try {
			conexion.close();
			conexion = null;
		} catch (SQLException exception) {
			throw new Exception("Error al intentar cerrar la conexi�n.");
		}
    }

	/**
	 * Crea las tablas
	 */
	public void crearTablas (){
		try 
		{
			Statement s = conexion.createStatement( );

			boolean crearTabla = false;
			try
			{
				// Verificar si ya existe la tabla
				s.executeQuery( "SELECT * FROM clientes" );
			}
			catch( SQLException se )
			{
				// La excepci�n se lanza si la tabla no existe
				crearTabla = true;
			}

			// Se crea una nueva tabla vac�a
			if( crearTabla )
			{
				s.execute( "CREATE TABLE clientes (id varchar(32), nombre varchar(32), direccion varchar(32), telefono int, juridico boolean, ciudad varchar(32), idRepLegal varchar(32), PRIMARY KEY (id))" );
				System.out.println("Se cre� la tabla clientes");
			}
			else
				System.out.println("La tabla clientes ya existe");
			s.close();

			Statement s1 = conexion.createStatement( );
			try
			{
				// Verificar si ya existe la tabla
				s1.executeQuery( "SELECT * FROM productos" );
			}
			catch( SQLException se )
			{
				// La excepci�n se lanza si la tabla no existe
				crearTabla = true;
			}

			// Se crea una nueva tabla vac�a
			if( crearTabla )
			{
				s1.execute( "CREATE TABLE productos (id varchar(32), nombre varchar(32), precio int, PRIMARY KEY (id))" );
				System.out.println("Se cre� la tabla productos");
			}
			else
				System.out.println("La tabla productos ya existe");
			s1.close();
			crearTabla = false;

			Statement s2 = conexion.createStatement( );
			try
			{
				// Verificar si ya existe la tabla
				s2.executeQuery( "SELECT * FROM proveedores" );
			}
			catch( SQLException se )
			{
				// La excepci�n se lanza si la tabla no existe
				crearTabla = true;
			}

			// Se crea una nueva tabla vac�a
			if( crearTabla )
			{
				s2.execute( "CREATE TABLE proveedores (id varchar(32), direccion varchar(32), telefono int, ciudad varchar(32), idRepLegal varchar(32), PRIMARY KEY (id))" );
				System.out.println("Se cre� la tabla proveedores");
			}
			else
				System.out.println("La tabla proveedores ya existe");
			s2.close();
			crearTabla = false;

			Statement s3 = conexion.createStatement( );
			try
			{
				// Verificar si ya existe la tabla
				s3.executeQuery( "SELECT * FROM usuarios" );
			}
			catch( SQLException se )
			{
				// La excepci�n se lanza si la tabla no existe
				crearTabla = true;
			}

			// Se crea una nueva tabla vac�a
			if( crearTabla )
			{
				s3.execute( "CREATE TABLE usuarios (login varchar(32), tipo varchar(32), precio int, disponibles int, PRIMARY KEY (login))" );
				System.out.println("Se cre� la tabla usuarios");
			}
			else
				System.out.println("La tabla usuarios ya existe");
			s3.close();
			crearTabla = false;

			Statement s4 = conexion.createStatement( );
			try
			{
				// Verificar si ya existe la tabla
				s4.executeQuery( "SELECT * FROM productos" );
			}
			catch( SQLException se )
			{
				// La excepci�n se lanza si la tabla no existe
				crearTabla = true;
			}
			// Se crea una nueva tabla vac�a
			if( crearTabla )
			{
				s4.execute( "CREATE TABLE productos (id varchar(32), nombre varchar(32), precio int, disponibles int, PRIMARY KEY (id))" );
				System.out.println("Se cre� la tabla productos");
			}
			else
				System.out.println("La tabla productos ya existe");
			s4.close();
			crearTabla = false;

			Statement s5 = conexion.createStatement( );
			try
			{
				// Verificar si ya existe la tabla
				s5.executeQuery( "SELECT * FROM materiasPrimas" );
			}
			catch( SQLException se )
			{
				// La excepci�n se lanza si la tabla no existe
				crearTabla = true;
			}

			// Se crea una nueva tabla vac�a
			if( crearTabla )
			{
				s5.execute( "CREATE TABLE materiasPrimas (id varchar(32), unidadMedida varchar(32), cantInicial int, PRIMARY KEY (id))" );
				System.out.println("Se cre� la tabla materiasPrima");
			}
			else
				System.out.println("La tabla materiasPrimas ya existe");
			s5.close();
			crearTabla = false;

			Statement s6 = conexion.createStatement( );
			try
			{
				// Verificar si ya existe la tabla
				s6.executeQuery( "SELECT * FROM componentes" );
			}
			catch( SQLException se )
			{
				// La excepci�n se lanza si la tabla no existe
				crearTabla = true;
			}

			// Se crea una nueva tabla vac�a
			if( crearTabla )
			{
				s6.execute( "CREATE TABLE componentes (id varchar(32), cantInicial int, PRIMARY KEY (id))" );
				System.out.println("Se cre� la tabla componentes");
			}
			else
				System.out.println("La tabla componentes ya existe");
			s6.close();
			crearTabla = false;
			
			Statement s7 = conexion.createStatement( );
			try
			{
				// Verificar si ya existe la tabla
				s7.executeQuery( "SELECT * FROM ProveedoresMateriasPrimas" );
			}
			catch( SQLException se )
			{
				// La excepci�n se lanza si la tabla no existe
				crearTabla = true;
			}

			// Se crea una nueva tabla vac�a
			if( crearTabla )
			{
				s7.execute( "CREATE TABLE ProveedoresMateriasPrimas (id_proveedor varchar(32), id_materiaPrima varchar(32), volMax int, tiempoMax int, PRIMARY KEY (id_proveedor,id_materiaPrima))" );
				System.out.println("Se cre� la tabla ProveedoresMateriasPrimas");
			}
			else
				System.out.println("La tabla componentes ProveedoresMateriasPrimas");
			s7.close();
			crearTabla = false;
			
			Statement s8 = conexion.createStatement( );
			try
			{
				// Verificar si ya existe la tabla
				s8.executeQuery( "SELECT * FROM ProveedoresComponentes" );
			}
			catch( SQLException se )
			{
				// La excepci�n se lanza si la tabla no existe
				crearTabla = true;
			}

			// Se crea una nueva tabla vac�a
			if( crearTabla )
			{
				s8.execute( "CREATE TABLE ProveedoresComponentes (id_proveedor varchar(32), id_componente varchar(32), volMax int, tiempoMax int, PRIMARY KEY (id_proveedor,id_componente)))" );
				System.out.println("Se cre� la tabla ProveedoresComponentes");
			}
			else
				System.out.println("La tabla ProveedoresComponentes ya existe");
			s8.close();
			crearTabla = false;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}