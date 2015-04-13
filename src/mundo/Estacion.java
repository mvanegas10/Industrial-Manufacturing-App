package mundo;

import java.net.IDN;
import java.util.ArrayList;
import java.util.List;

public class Estacion {
	
	public static final String NOMBRE = "estaciones";
	
	public static final String[] COLUMNAS = {"id", "nombre"};
	
	public static final String[] TIPO = {"String", "String"};
	
	//--------------------------------------------------
	// ATRIBUTOS
	//--------------------------------------------------
	
	private String id;
	
	private String nombre;
	
	private ArrayList<EtapaProduccion> etapasProduccion;
	
	//--------------------------------------------------
	// CONSTRUCTOR
	//--------------------------------------------------

	public Estacion(String pId, String pNombre) {
		this.id = pId;
		this.nombre = pNombre;
		this.etapasProduccion = new ArrayList<EtapaProduccion>();
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String idNombre) {
		this.nombre = idNombre;
	}

	public ArrayList<EtapaProduccion> getEtapasProduccion() {
		return etapasProduccion;
	}

	public void setEtapasProduccion(ArrayList<EtapaProduccion> etapasProduccion) {
		this.etapasProduccion = etapasProduccion;
	}
}
