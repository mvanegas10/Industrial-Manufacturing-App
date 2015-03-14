package mundo;

import java.util.ArrayList;
import java.util.List;

public class EstacionProduccion {
	
	//--------------------------------------------------
	// ATRIBUTOS
	//--------------------------------------------------
	
	private String id;
	
	private EstacionProduccion siguiente;
	
	private ArrayList<EtapaProduccion> etapasProduccion;
	
	private List<String[]> matrizReglasProduccion;
	
	//--------------------------------------------------
	// CONSTRUCTOR
	//--------------------------------------------------

	public EstacionProduccion(String pId, EstacionProduccion pSiguiente, ArrayList<EtapaProduccion> pEtapasProduccion, List<String[]> pMatrizReglasProduccion) {
		this.id = pId;
		this.siguiente = pSiguiente;
		this.etapasProduccion = pEtapasProduccion;
		this.matrizReglasProduccion = pMatrizReglasProduccion;
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

	public EstacionProduccion getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(EstacionProduccion siguiente) {
		this.siguiente = siguiente;
	}

	public ArrayList<EtapaProduccion> getEtapasProduccion() {
		return etapasProduccion;
	}

	public void setEtapasProduccion(ArrayList<EtapaProduccion> etapasProduccion) {
		this.etapasProduccion = etapasProduccion;
	}

	public List<String[]> getMatrizReglasProduccion() {
		return matrizReglasProduccion;
	}

	public void setMatrizReglasProduccion(List<String[]> matrizReglasProduccion) {
		this.matrizReglasProduccion = matrizReglasProduccion;
	} 
	
	
}
