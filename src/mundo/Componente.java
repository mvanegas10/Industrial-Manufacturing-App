package mundo;

import java.util.ArrayList;

public class Componente{
	
	//--------------------------------------------------
	// ATRIBUTOS
	//--------------------------------------------------
	
	public static final String NOMBRE = "COMPONENTES";
	
	public static final String[] COLUMNAS = {"id","unidadMedida","cantidadInicial"};
	
	public static final String[] TIPO = {"String","String","int"};
	
	public static final String NOMBRE_REGISTRO_COMPONENTES = "registrosComponentes";
	
	public static final String[] COLUMNAS_REGISTRO_COMPONENTES = {"id","idComponente"};
	
	public static final String[] TIPO_REGISTRO_COMPONENTES = {"String","String"};
	
	private String id;
	
	private String unidadMedida;
	
	private int cantidadInicial;
	
	private String tipo;
	
	//--------------------------------------------------
	// CONSTRUCTOR
	//--------------------------------------------------

	public Componente(String pId, String unidadMedida, int pCantidadInicial) {
		id = pId;
		unidadMedida = unidadMedida;
		cantidadInicial = pCantidadInicial;
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

	public int getCantidadInicial() {
		return cantidadInicial;
	}

	public void setCantidadInicial(int cantidadInicial) {
		this.cantidadInicial = cantidadInicial;
	}
	
	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String toString(){
		return "id: " + id + ", cantidad; " + cantidadInicial;
	}
}
