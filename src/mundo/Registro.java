package mundo;

public class Registro {
	
	public static final String NOMBRE = "registro";
	
	public static final String[] COLUMNAS = {"id", "idEtapa", "idProducto", "cantidad"};
	
	public static final String[] TIPO = {"String", "String", "String", "int"};
	
	private String id;
	
	private String idEtapa;
	
	private String idProducto;
	
	private int cantidad;
	
	public Registro(String id, String idEtapa, String idProducto,
			int cantidad) {
		this.id = id;
		this.idEtapa = idEtapa;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
	}

	public String getId() {
		return id;
	}

	public String getIdEtapa() {
		return idEtapa;
	}

	public String getIdProducto() {
		return idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIdEtapa(String idEtapa) {
		this.idEtapa = idEtapa;
	}

	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	

}
