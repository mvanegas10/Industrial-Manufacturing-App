package mundo;

import java.util.Date;

public class Pedido {
	
	public static final String NOMBRE = "pedidos";
	
	public static final String[] COLUMNAS = {"id", "idProducto", "idCliente", "cantidad", "diaPedido", "mesPedido"};
	
	public static final String[] TIPO = {"String", "String", "String","int", "int", "int"};
	
	private String id;

	private String idProducto;
	
	private String idCliente;
	
	private int cantidad;
	
	private Date fechaPedido;
	
	private Date fechaEntrega;

	/**
	 * @param producto
	 * @param cantidad
	 * @param fechaPedido
	 * @param fechaEntrega
	 */
	public Pedido(String id, String producto, String idCliente, int cantidad, Date fechaPedido, Date fechaEntrega) {
		this.id = id;
		this.idProducto = producto;
		this.idCliente = idCliente;
		this.cantidad = cantidad;
		this.fechaPedido = fechaPedido;
		this.fechaEntrega = fechaEntrega;
	}

	public String getId() {
		return id;
	}
	
	public String getProducto() {
		return idProducto;
	}
	
	public String getIdCliente() {
		return idCliente;
	}

	public int getCantidad() {
		return cantidad;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	
	public void setProducto(String producto) {
		this.idProducto = producto;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	
	
	
}
