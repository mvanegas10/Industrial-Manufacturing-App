package mundo;

import java.util.ArrayList;

public class Componente {
	
	//--------------------------------------------------
	// ATRIBUTOS
	//--------------------------------------------------
	
	private String id;
	
	private int cantidadInicial;
	
	private ArrayList<Proveedor> proveedores;
	
	//--------------------------------------------------
	// CONSTRUCTOR
	//--------------------------------------------------

	public Componente(String pId, int pCantidadInicial, ArrayList<Proveedor> pProveedores) {
		this.id = pId;
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
