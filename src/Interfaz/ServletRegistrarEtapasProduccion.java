package Interfaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mundo.AplicacionWeb;

public class ServletRegistrarEtapasProduccion extends ServletAbstract{

	@Override
	public String darTituloPagina(HttpServletRequest request) {
		return "Registrar Etapa de Produccion";
	}

	@Override
	public void escribirContenido(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter respuesta = response.getWriter( );
		
		String nombre = request.getParameter("nombre");
		int precio = Integer.parseInt(request.getParameter("precio"));
		String idProducto = Integer.toString(AplicacionWeb.getInstancia().darContadorId());
		
		try
		{
			AplicacionWeb.getInstancia().registrarProducto(idProducto, nombre, precio);

			respuesta.write( "<form method=\"POST\" action=\"registroProducto.htm\">" );
			respuesta.write( "<table bgcolor=\"#bdc3c7\" width=100%>" );
			respuesta.write( "<tr>" );
			respuesta.write( "<td></td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "</table>" );
			respuesta.write( "<table align= center bgcolor=\"#ecf0f1\" width=\"50%\">" );
			respuesta.write( "<tr>" );
			respuesta.write( "<td>Numero Secuencia</td>" );
			respuesta.write( "<td>Nombre</td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "<tr>" );
			respuesta.write( "<td><input type=\"text\" size=\"10\" name=\"numSecuencia1\" class=\"normal\"></td>" );		
			respuesta.write( "<td><input type=\"text\" size=\"80\" name=\"descripcion1\" class=\"normal\"></td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "<tr>" );
			respuesta.write( "<td><input type=\"text\" size=\"10\" name=\"numSecuencia2\" class=\"normal\"></td>" );
			respuesta.write( "<td><input type=\"text\" size=\"80\" name=\"descripcion2\" class=\"normal\"></td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "<tr>" );
			respuesta.write( "<td><input type=\"text\" size=\"10\" name=\"numSecuencia3\" class=\"normal\"></td>" );
			respuesta.write( "<td><input type=\"text\" size=\"80\" name=\"descripcion3\" class=\"normal\"></td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "<tr>" );
			respuesta.write( "<td><input type=\"text\" size=\"10\" name=\"numSecuencia4\" class=\"normal\"></td>" );
			respuesta.write( "<td><input type=\"text\" size=\"80\" name=\"descripcion4\" class=\"normal\"></td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "<tr>" );
			respuesta.write( "<td><input type=\"text\" size=\"10\" name=\"numSecuencia5\" class=\"normal\"></td>" );
			respuesta.write( "<td><input type=\"text\" size=\"80\" name=\"descripcion5\" class=\"normal\"></td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "<tr>" );
			respuesta.write( "<td><input type=\"text\" size=\"10\" name=\"numSecuencia6\" class=\"normal\"></td>" );
			respuesta.write( "<td><input type=\"text\" size=\"80\" name=\"descripcion6\" class=\"normal\"></td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "<tr>" );
			respuesta.write( "<td><input type=\"text\" size=\"10\" name=\"numSecuencia7\" class=\"normal\"></td>" );
			respuesta.write( "<td><input type=\"text\" size=\"80\" name=\"descripcion7\" class=\"normal\"></td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "<tr>" );
			respuesta.write( "<td><input type=\"text\" size=\"10\" name=\"numSecuencia8\" class=\"normal\"></td>" );		
			respuesta.write( "<td><input type=\"text\" size=\"80\" name=\"descripcion8\" class=\"normal\"></td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "<tr>" );
			respuesta.write( "<td><input type=\"text\" size=\"10\" name=\"numSecuencia9\" class=\"normal\"></td>" );		
			respuesta.write( "<td><input type=\"text\" size=\"80\" name=\"descripcion9\" class=\"normal\"></td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "<tr>" );
			respuesta.write( "<td><input type=\"text\" size=\"10\" name=\"numSecuencia10\" class=\"normal\"></td>" );
			respuesta.write( "<td><input type=\"text\" size=\"80\" name=\"descripcion10\" class=\"normal\"></td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "</table>" );
			respuesta.write( "<p align=center>" );
			respuesta.write( "<input type=\"submit\" value=\"Finalizar Cadena Produccion\" name=\"B1\" class=\"normal\">" );
			respuesta.write( "<input type=\"reset\" value=\"Borrar\" name=\"B2\" class=\"normal\"></p>" );
			respuesta.write( "<input type=\"hidden\" value=" + idProducto + " name=\"idProducto\" class=\"normal\"></p>" );
			respuesta.write( "</form>" );
		}
		catch (Exception e)
		{
	        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
	        respuesta.write( "<tr>" );
	        respuesta.write( "<td><h3>El producto ya se encuentra registrado</h3></td>" );
	        respuesta.write( "</tr>" );
	        respuesta.write( "</table>" );
		}
	}

}
