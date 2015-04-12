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
		int precio = Integer.parseInt(request.getParameter("precio"));
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
	        
	        respuesta.write( "<table align=\"center\" bgcolor=\"#ecf0f1\" width=50%>" );
	        respuesta.write( "<form method=\"POST\" action=\"resultadoBusqueda.htm\">" );
	        respuesta.write( "<tr>El siguiente pedido se ha realizado de manera exitosa. La fecha de entrega es: " + entrega.getTime().toLocaleString() + ".</tr>" );
	        respuesta.write( "<tr>" );
	        respuesta.write( "<tr><td><input alt=\"Producto\" src=\"imagenes/producto.jpg\" type=\"image\" name=\"producto\"></td>" );
	        respuesta.write( "<td><table align=\"center\" bgcolor=\"#ecf0f1\" width=10%>" );
	        respuesta.write( "<tr><td align=\"left\"><h4><input value=\"Producto Pedido: \" name=\"label1\" style=\"border: none;\" type=\"text\"\"></h4></td><input value=\"" + productoPedido + "\" name=\"producto\" type=\"hidden\"\"><td align=\"right\">" + productoPedido + "</td></tr>" );
	        respuesta.write( "<tr><td align=\"left\"><h4><input value=\"Costo Total: \" name=\"label2\" style=\"border: none;\" type=\"text\"\"></h4></td><td align=\"right\">" + (precio* cantidad) + "</td></tr>" );
	        respuesta.write( "<tr><td><table bgcolor=\"#ecf0f1\" width=10%>" );
	        respuesta.write( "<tr><td align=\"left\"><h4>Unidades: </h4></td>" );
	        respuesta.write( "<td align=\"right\">" );
	        respuesta.write( "<select size=\"1\" name=\"cantidad\" class=\"normal\" style=\"border: none;\">" );
	        respuesta.write( "<option value=\"1\">1</option>" );
	        respuesta.write( "<option value=\"2\">2</option>" );
	        respuesta.write( "<option value=\"3\">3</option>" );
	        respuesta.write( "</select>" );
	        respuesta.write( "</td></tr>" );
	        respuesta.write( "</table></td></tr>" );
	        respuesta.write( "<tr><td align=\"right\"><input value=\"Pedir\" size=\"33\" name=\"pedir\" type=\"submit\"\"></td></tr>" );
	        respuesta.write( "<input type=\"hidden\" name=\"login\" value=" + login + ">" );
	        respuesta.write( "</table></td>" );
	        respuesta.write( "</tr>" );
	        respuesta.write( "</form>" );
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
