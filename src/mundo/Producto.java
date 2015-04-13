package mundo;

import java.util.ArrayList;

public class Producto {
	
	//--------------------------------------------------
	// ATRIBUTOS
	//--------------------------------------------------

	public static final String NOMBRE = "productos";
	
	public final static String[] COLUMNAS = {"id", "nombre", "precio"};
	
	public final static String[] TIPO = {"String", "String", "int"};
	
	public static final String NOMBRE_REGISTRO_PRODUCTOS = "registrosProductos";
	
	public static final String[] COLUMNAS_REGISTRO_PRODUCTOS = {"id","idEtapa","idRegistroEstacion","idRegistroMateriaPrima","idregistroComponente"};
	
	public static final String[] TIPO_REGISTRO_PRODUCTOS = {"String","String","String","String","String"};
				
	public static final String NOMBRE_INVENTARIO_PRODUCTOS = "inventarioProductos";
	
	public static final String[] COLUMNAS_INVENTARIO_PRODUCTOS = {"idRegistro","idProducto"};
	
	public static final String[] TIPO_INVENTARIO_PRODUCTOS = {"String","String"};

	private String id;

	private String nombre;

	private double precio;
	
	private ArrayList<Etapa> etapasProduccion;
	
	//---------------------------------------------------
	// CONSTRUCTOR
	//---------------------------------------------------

	public Producto(String id, String nombre, double precio, int pNumeroEtapas, int disponibles,ArrayList<Cliente> pClientes, int cantidad,ArrayList<Etapa> pEtapasProduccion) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.etapasProduccion = pEtapasProduccion;
	}

	//---------------------------------------------------
	// GETTERS AND SETTERS
	//---------------------------------------------------

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public ArrayList<Etapa> getEtapasProduccion() {
		return etapasProduccion;
	}

	public void setEtapasProduccion(ArrayList<Etapa> etapasProduccion) {
		this.etapasProduccion = etapasProduccion;
	}
	
	
	
}
