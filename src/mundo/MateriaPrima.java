package mundo;

import java.util.ArrayList;

public class MateriaPrima {
	
	//--------------------------------------------------
	// ATRIBUTOS
	//--------------------------------------------------
	
	public static final String NOMBRE = "materiasPrimas";
	
	public static final String[] COLUMNAS = {"id","unidadMedida","cantidadInicial"};
	
	public static final String[] TIPO = {"String","String","int"};
	
	private String id;
	
	private String unidadMedidad;
	
	private int cantidadInicial;
	
	//--------------------------------------------------
	// CONSTRUCTOR
	//--------------------------------------------------

	public MateriaPrima(String pId, String pUnidadMedidad, int pCantidadInicial) {
		this.id = pId;
		this.unidadMedidad = pUnidadMedidad;
		this.cantidadInicial = pCantidadInicial;
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

}
