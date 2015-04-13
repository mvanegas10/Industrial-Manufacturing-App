package mundo;

import java.util.ArrayList;

public class Producto {
	
	//--------------------------------------------------
	// ATRIBUTOS
	//--------------------------------------------------

	public static final String NOMBRE = "productos";
	
	public final static String[] COLUMNAS = {"id", "nombre", "precio"};
	
	public final static String[] TIPO = {"String", "String", "int"};
				
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
