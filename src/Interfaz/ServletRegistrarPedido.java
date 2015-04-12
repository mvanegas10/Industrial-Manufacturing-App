package Interfaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mundo.AplicacionWeb;

public class ServletRegistrarPedido extends ServletAbstract{

	@Override
	public String darTituloPagina(HttpServletRequest request) {
		return "Registro de Pedido";
	}

	@Override
	public void escribirContenido(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter respuesta = response.getWriter( );
		
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));
		String productoPedido = request.getParameter("producto");
		String login = request.getParameter("login");
		Calendar temp = Calendar.getInstance();
		temp.setTime(new Date());
		Calendar pedido = Calendar.getInstance();
		Calendar entrega = Calendar.getInstance();
		pedido.setTime(new Date());
		temp.add(Calendar.DATE, 5);
		entrega.setTime(temp.getTime());
		try
		{
			AplicacionWeb.getInstancia().registrarPedidoCliente(login, productoPedido, cantidad, pedido.getTime(), entrega.getTime());
	        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
	        respuesta.write( "<tr>" );
	        respuesta.write( "<form method=\"POST\" action=\"resultadoBusqueda.htm\">" );
	        respuesta.write( "<td><h4>Gracias " + login + " por confiar en nosotros, tu pedido se ha diligenciado de manera satisfactoria y la fecha de entrega es el " + entrega.getTime().toLocaleString() + "</h4></td>" );
	        respuesta.write( "<input value=\"darPedidos\" name=\"criterio\" type=\"hidden\"\">" );
	        respuesta.write( "<input value=\"" + login + "\" name=\"login\" type=\"hidden\"\">" );
	        respuesta.write( "<td><h3><input value=\"Si quieres consultar tus pedidos, haz click aquí\" name=\"darPedidos\" style=\"background: #FFFFFF; border: none;\" type=\"submit\"\"></h3></td>" );
	        respuesta.write( "</form>" );
	        respuesta.write( "</tr>" );
	        respuesta.write( "</table>" );
		}
		catch (Exception e){
	        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
	        respuesta.write( "<tr>" );
	        respuesta.write( "<td><h3>El producto no tiene las unidades disponibles que desea.</h3></td>" );
	        respuesta.write( "</tr>" );
	        respuesta.write( "</table>" );
		}
	}

}
