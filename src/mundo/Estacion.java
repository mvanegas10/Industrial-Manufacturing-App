package mundo;

import java.net.IDN;
import java.util.ArrayList;
import java.util.List;

public class Estacion {
	
	public static final String NOMBRE = "estaciones";
	
	public static final String[] COLUMNAS = {"id", "nombre"};
	
	public static final String[] TIPO = {"String", "String"};
	
	public static final String NOMBRE_REGISTRO_ESTACIONES = "registroEstaciones";
	
	public static final String[] COLUMNAS_REGISTRO_ESTACIONES = {"id", "idEstacion","dia","mes"};
	
	public static final String[] TIPO_REGISTRO_ESTACIONES = {"String", "String","int","int"};
	
	//--------------------------------------------------
	// ATRIBUTOS
	//--------------------------------------------------
	
	private String id;
	
	private String nombre;
	
	private ArrayList<Etapa> etapasProduccion;
	
	//--------------------------------------------------
	// CONSTRUCTOR
	//--------------------------------------------------

	public Estacion(String pId, String pNombre) {
		this.id = pId;
		this.nombre = pNombre;
		this.etapasProduccion = new ArrayList<Etapa>();
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

	public ArrayList<Etapa> getEtapasProduccion() {
		return etapasProduccion;
	}

	public void setEtapasProduccion(ArrayList<Etapa> etapasProduccion) {
		this.etapasProduccion = etapasProduccion;
	}
}
