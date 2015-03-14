package mundo;

import java.util.ArrayList;

public class Cliente {
	
	//--------------------------------------------------
	// ATRIBUTOS
	//--------------------------------------------------
	
	private String id;
	
	private String nombre;
	
	private String direccion;
	
	private int telefono;
	
	private String ciudad;
	
	private boolean juridico;
	
	private String idRepLegal;
	
	private ArrayList<Producto> productos;
	
	//---------------------------------------------------
	// CONSTRUCTOR
	//---------------------------------------------------
	
	public Cliente(String pId, String pNombre, String pDireccion, int pTelefono, String pCiudad, boolean pJuridico, String pIdRepLegal, ArrayList<Producto> pProductos) {
		this.id = pId;
		this.nombre = pNombre;
		this.direccion = pDireccion;
		this.telefono = pTelefono;
		this.ciudad = pCiudad;
		this.juridico = pJuridico;
		this.idRepLegal = pIdRepLegal;
		this.productos = pProductos;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public boolean isJuridico() {
		return juridico;
	}

	public void setJuridico(boolean juridico) {
		this.juridico = juridico;
	}

	public String getIdRepLegal() {
		return idRepLegal;
	}

	public void setIdRepLegal(String idRepLegal) {
		this.idRepLegal = idRepLegal;
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}
	
	
	
}
