package mundo;

public class EtapaProduccion {
	
	public static final String NOMBRE = "etapaProduccion";
	
	public static final String[] COLUMNAS = {"id", "numSecuencia", "idProducto", "idSiguiente"};
	
	public static final String[] TIPO = {"String", "int", "String", "String"};
	
	//--------------------------------------------------
	// ATRIBUTOS
	//--------------------------------------------------
	
	private String id;
	
	private int numeroSecuencia;
	
	private String idProducto;
	
	private String idSiguiente;

	//--------------------------------------------------
	// CONSTRUCTOR
	//--------------------------------------------------
	
	public EtapaProduccion(String pId, int pNumeroSecuencia, String pProducto, String pSiguiente) {
		this.id = pId;
		this.numeroSecuencia = pNumeroSecuencia;
		this.idProducto = pProducto;
		this.idSiguiente = pSiguiente;
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

	public int getNumeroSecuencia() {
		return numeroSecuencia;
	}

	public void setNumeroSecuencia(int numeroSecuencia) {
		this.numeroSecuencia = numeroSecuencia;
	}

	public String getProducto() {
		return idProducto;
	}

	public void setProducto(String idProducto) {
		this.idProducto = idProducto;
	}

	public String getSiguiente() {
		return idSiguiente;
	}

	public void setSiguiente(String idSiguiente) {
		this.idSiguiente = idSiguiente;
	}
	
	//--------------------------------------------------
	// MÉTODOS
	//--------------------------------------------------
	
	
	
}
