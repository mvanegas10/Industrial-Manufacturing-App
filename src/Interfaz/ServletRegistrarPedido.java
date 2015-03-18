package Interfaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mundo.AplicacionWeb;

public class ServletRegistrarPedido extends ServletAbstract{

	@Override
	public String darTituloPagina(HttpServletRequest request) {
		return "Registro de Pedido Cliente";
	}

	@Override
	public void escribirContenido(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter respuesta = response.getWriter( );
		
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));
		String productoPedido = request.getParameter("productoPedido");
		Date pedido = new Date();
		Date entrega = new Date();
		entrega.setDate(pedido.getDate() + 3);
		entrega.setMonth(pedido.getMonth());
		entrega.setYear(pedido.getYear());
		try
		{
			AplicacionWeb.getInstancia().registrarPedidoCliente(productoPedido, cantidad, pedido, entrega);
	        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
	        respuesta.write( "<tr>" );
	        respuesta.write( "<td><FONT face=\"arial\" size=5 color=#34495e>El pedido se ha diligenciado de manera satisfactoria, la fecha de entrega es el " + entrega + "</FONT></td>" );
	        respuesta.write( "</tr>" );
	        respuesta.write( "</table>" );
		}
		catch (Exception e){
	        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
	        respuesta.write( "<tr>" );
	        respuesta.write( "<td><FONT face=\"arial\" size=5 color=#34495e>El producto no tiene las unidades disponibles que desea.</FONT></td>" );
	        respuesta.write( "</tr>" );
	        respuesta.write( "</table>" );
		}
	}

}
