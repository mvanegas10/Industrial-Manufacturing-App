package mundo;

import java.util.Date;

public class Pedido {
	
	public static final String NOMBRE = "pedidos";
	
	public static final String[] COLUMNAS = {"id", "idProducto", "cantidad", "fechaPedido", "fechaEntrega"};
	
	public static final String[] TIPO = {"String", "String", "int", "Date", "Date"};
	
	private String id;

	private Producto idProducto;
	
	private int cantidad;
	
	private Date fechaPedido;
	
	private Date fechaEntrega;

	/**
	 * @param producto
	 * @param cantidad
	 * @param fechaPedido
	 * @param fechaEntrega
	 */
	public Pedido(Producto producto, int cantidad, Date fechaPedido, Date fechaEntrega) {
		this.idProducto = producto;
		this.cantidad = cantidad;
		this.fechaPedido = fechaPedido;
		this.fechaEntrega = fechaEntrega;
	}

	public Producto getProducto() {
		return idProducto;
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

	public void setProducto(Producto producto) {
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
