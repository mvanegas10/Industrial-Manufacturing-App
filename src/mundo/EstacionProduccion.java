package mundo;

import java.util.ArrayList;
import java.util.List;

public class EstacionProduccion {
	
	public static final String NOMBRE = "estacionProduccion";
	
	public static final String[] COLUMNAS = {"id", "idSiguiente"};
	
	public static final String[] TIPO = {"String", "String"};
	
	//--------------------------------------------------
	// ATRIBUTOS
	//--------------------------------------------------
	
	private String id;
	
	private String idSiguiente;
	
	private ArrayList<EstacionProduccion> etapasProduccion;
	
	private List<String[]> matrizReglasProduccion;
	
	//--------------------------------------------------
	// CONSTRUCTOR
	//--------------------------------------------------

	public EstacionProduccion(String pId, String pSiguiente, ArrayList<EstacionProduccion> pEtapasProduccion, List<String[]> pMatrizReglasProduccion) {
		this.id = pId;
		this.idSiguiente = pSiguiente;
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

	public String getSiguiente() {
		return idSiguiente;
	}

	public void setSiguiente(String idSiguiente) {
		this.idSiguiente = idSiguiente;
	}

	public ArrayList<EstacionProduccion> getEtapasProduccion() {
		return etapasProduccion;
	}

	public void setEtapasProduccion(ArrayList<EstacionProduccion> etapasProduccion) {
		this.etapasProduccion = etapasProduccion;
	}

	public List<String[]> getMatrizReglasProduccion() {
		return matrizReglasProduccion;
	}

	public void setMatrizReglasProduccion(List<String[]> matrizReglasProduccion) {
		this.matrizReglasProduccion = matrizReglasProduccion;
	} 
	
	
}
