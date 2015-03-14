package mundo;

import java.util.ArrayList;

public class Producto {
	
	//--------------------------------------------------
	// ATRIBUTOS
	//--------------------------------------------------

	private String id;

	private String nombre;

	private int precio;
	
	private int numeroEtapas;
	
	private Inventario inventario;
	
	private ArrayList<Cliente> clientes;
	
	private ArrayList<EtapaProduccion> etapasProduccion;
	
	//---------------------------------------------------
	// CONSTRUCTOR
	//---------------------------------------------------

	public Producto(String id, String nombre, int precio, int pNumeroEtapas, int disponibles,ArrayList<Cliente> pClientes, Inventario pInventario,ArrayList<EtapaProduccion> pEtapasProduccion) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.clientes = pClientes;
		this.inventario = pInventario;
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

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public Inventario getInventario() {
		return inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
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
