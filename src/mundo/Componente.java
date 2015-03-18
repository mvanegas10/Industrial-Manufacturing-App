package mundo;

import java.util.ArrayList;

public class Componente {
	
	//--------------------------------------------------
	// ATRIBUTOS
	//--------------------------------------------------
	
	public static final String NOMBRE = "COMPONENTES";
	
	public static final String[] COLUMNAS = {"id","cantidadInicial"};
	
	public static final String[] TIPO = {"String","int"};
	
	private String id;
	
	private int cantidadInicial;
	
	//--------------------------------------------------
	// CONSTRUCTOR
	//--------------------------------------------------

	public Componente(String pId, int pCantidadInicial) {
		this.id = pId;
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

	public int getCantidadInicial() {
		return cantidadInicial;
	}

	public void setCantidadInicial(int cantidadInicial) {
		this.cantidadInicial = cantidadInicial;
	}
	
}
