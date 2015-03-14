package mundo;

public class EtapaProduccion {
	
	//--------------------------------------------------
	// ATRIBUTOS
	//--------------------------------------------------
	
	private String id;
	
	private int numeroSecuencia;
	
	private Producto producto;
	
	private EtapaProduccion siguiente;

	//--------------------------------------------------
	// CONSTRUCTOR
	//--------------------------------------------------
	
	public EtapaProduccion(String pId, int pNumeroSecuencia, Producto pProducto, EtapaProduccion pSiguiente) {
		this.id = pId;
		this.numeroSecuencia = pNumeroSecuencia;
		this.producto = pProducto;
		this.siguiente = pSiguiente;
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

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public EtapaProduccion getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(EtapaProduccion siguiente) {
		this.siguiente = siguiente;
	}
	
	//--------------------------------------------------
	// MÉTODOS
	//--------------------------------------------------
	
	
	
}
