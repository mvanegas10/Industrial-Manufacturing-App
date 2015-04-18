package mundo;

import java.util.ArrayList;

public class MateriaPrima {
	
	//--------------------------------------------------
	// ATRIBUTOS
	//--------------------------------------------------
	
	public static final String NOMBRE = "materiasPrimas";
	
	public static final String[] COLUMNAS = {"id","unidadMedida","cantidadInicial"};
	
	public static final String[] TIPO = {"String","String","int"};
	
	public static final String NOMBRE_REGISTRO_MATERIAS_PRIMAS = "registrosMateriasPrimas";
	
	public static final String[] COLUMNAS_REGISTRO_MATERIAS_PRIMAS = {"id","idMateriaPrima"};
	
	public static final String[] TIPO_REGISTRO_MATERIAS_PRIMAS = {"String","String"};
	
	private String id;
	
	private String unidadMedida;
	
	private int cantidadInicial;
	
	private String tipo;
	
	//--------------------------------------------------
	// CONSTRUCTOR
	//--------------------------------------------------

	public MateriaPrima(String pId, String pUnidadMedidad, int pCantidadInicial) {
		this.id = pId;
		this.unidadMedida = pUnidadMedidad;
		this.cantidadInicial = pCantidadInicial;
		this.tipo = "Materia Prima";
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

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
		
	}

	public int getCantidadInicial() {
		return cantidadInicial;
	}

	public void setCantidadInicial(int cantidadInicial) {
		this.cantidadInicial = cantidadInicial;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String toString(){
		return "id: " + id + ", unidadMedida: " + unidadMedida + ", cantidad; " + cantidadInicial;
	}

}
