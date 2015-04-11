package Interfaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mundo.AplicacionWeb;
import mundo.Pedido;
import mundo.Producto;

public class ServletProducto extends ServletAbstract{

	@Override
	public String darTituloPagina(HttpServletRequest request) {
		return "Producto";
	}

	@Override
	public void escribirContenido(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter respuesta = response.getWriter( );
		
		String producto = request.getParameter("producto");
		String login = request.getParameter("login");
		ArrayList<String> rta = new ArrayList<String>();
		
		try
		{
			rta = AplicacionWeb.getInstancia().buscarProducto(producto);
		}
		catch (Exception e){
			error(respuesta);
		}
		
		respuesta.write( "<table align=\"center\" bgcolor=\"#ecf0f1\" width=30%>" );
		for (int i = 0; i < rta.size(); i++) {
			respuesta.write( "<form method=\"POST\" action=\"registrarPedido.htm\">" );
			respuesta.write( "<tr>" );
        	respuesta.write( "<tr><td><input alt=\"Producto\" src=\"imagenes/producto.jpg\" type=\"image\" name=\"producto\"></td>" );
        	respuesta.write( "<td><table align=\"center\" bgcolor=\"#ecf0f1\" width=10%>" );
	        respuesta.write( "<tr><td><input value=\"Producto: " + producto + "\" name=\"producto\" style=\"border: none;\" type=\"text\"\"></td></tr>" );
	        respuesta.write( "<tr><td><input value=\"Precio: " + rta.get(i) + "\" name=\"precio\" style=\"border: none;\" type=\"text\"\"></td></tr>" );
	        respuesta.write( "<tr><td><table align=\"left\" bgcolor=\"#ecf0f1\" width=10%>" );
	        respuesta.write( "<tr><td align=\"left\">Unidades: </td>" );
	        respuesta.write( "<td align=\"right\">" );
	        respuesta.write( "<select size=\"1\" name=\"cantidad\" class=\"normal\" style=\"border: none;\">" );
	        respuesta.write( "<option value=\"1\">1</option>" );
	        respuesta.write( "<option value=\"2\">2</option>" );
	        respuesta.write( "<option value=\"3\">3</option>" );
	        respuesta.write( "</select>" );
	        respuesta.write( "</td></tr>" );
	        respuesta.write( "</table></td></tr>" );
	        respuesta.write( "<tr><td><input value=\"Pedir\" size=\"23\" name=\"pedir\" type=\"submit\"\"></td></tr>" );
	        respuesta.write( "<input type=\"hidden\" name=\"login\" value=" + login + ">" );
	        respuesta.write( "</table></td>" );
	        respuesta.write( "</tr>" );
	        respuesta.write( "</form>" );
        }
		respuesta.write( "</table>" );
		
	}
	
	public void error(PrintWriter respuesta){
		
        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
        respuesta.write( "<tr>" );
        respuesta.write( "<td><h3>Oops! Hubo un error, lo sentimos, vuelve a intentarlo nuevamente.</FONT></td>" );
        respuesta.write( "</tr>" );
        respuesta.write( "</table>" );
		
	}

}
