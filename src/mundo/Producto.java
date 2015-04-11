package mundo;

import java.util.ArrayList;

public class Producto {
	
	//--------------------------------------------------
	// ATRIBUTOS
	//--------------------------------------------------

	public static final String NOMBRE = "productos";
	
	public final static String[] COLUMNAS = {"id", "nombre", "precio", "cantidad"};
	
	public final static String[] TIPO = {"String", "String", "int", "int"};
	
	public final static String NOMBRE_RELACION_ETAPA_PRODUCCION = "productosEtapasProduccion";
	
	public final static String[] COLUMNA_RELACION_ETAPA_PRODUCCION = {"id_producto", "idEtapa", "descripcion"};
	
	public final static String[] TIPO_RELACION_ETAPA_PRODUCCION = {"String", "String", "String"};
	
	private String id;

	private String nombre;

	private double precio;
	
	private int numeroEtapas;
	
	private int cantidad;
	
	private ArrayList<Cliente> clientes;
	
	private ArrayList<EtapaProduccion> etapasProduccion;
	
	//---------------------------------------------------
	// CONSTRUCTOR
	//---------------------------------------------------

	public Producto(String id, String nombre, double precio, int pNumeroEtapas, int disponibles,ArrayList<Cliente> pClientes, int cantidad,ArrayList<EtapaProduccion> pEtapasProduccion) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.clientes = pClientes;
		this.cantidad = cantidad;
		this.etapasProduccion = pEtapasProduccion;
		this.numeroEtapas = pNumeroEtapas;
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

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}

	public ArrayList<EtapaProduccion> getEtapasProduccion() {
		return etapasProduccion;
	}

	public void setEtapasProduccion(ArrayList<EtapaProduccion> etapasProduccion) {
		this.etapasProduccion = etapasProduccion;
	}

	public int getNumeroEtapas() {
		return numeroEtapas;
	}

	public void setNumeroEtapas(int numeroEtapas) {
		this.numeroEtapas = numeroEtapas;
	}
	
	
	
}
