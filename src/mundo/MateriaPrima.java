package mundo;

import java.util.ArrayList;

public class MateriaPrima {
	
	//--------------------------------------------------
	// ATRIBUTOS
	//--------------------------------------------------
	
	private String id;
	
	private String unidadMedidad;
	
	private int cantidadInicial;
	
	private ArrayList<Proveedor> proveedores;
	
	//--------------------------------------------------
	// CONSTRUCTOR
	//--------------------------------------------------

	public MateriaPrima(String pId, String pUnidadMedidad, int pCantidadInicial, ArrayList<Proveedor> pProveedores) {
		this.id = pId;
		this.unidadMedidad = pUnidadMedidad;
		this.cantidadInicial = pCantidadInicial;
		this.proveedores = pProveedores;
	}
	
	//--------------------------------------------------
	// GETTERS AND SETTERS
	//--------------------------------------------------

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUnidadMedidad() {
		return unidadMedidad;
	}

	public void setUnidadMedidad(String unidadMedidad) {
		this.unidadMedidad = unidadMedidad;
	}

	public int getCantidadInicial() {
		return cantidadInicial;
	}

	public void setCantidadInicial(int cantidadInicial) {
		this.cantidadInicial = cantidadInicial;
	}

	public ArrayList<Proveedor> getProveedores() {
		return proveedores;
	}

	public void setProveedores(ArrayList<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}

}
