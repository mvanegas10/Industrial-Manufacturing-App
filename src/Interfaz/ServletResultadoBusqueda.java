package Interfaz;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mundo.AplicacionWeb;

public class ServletResultadoBusqueda extends ServletAbstract{

	@Override
	public String darTituloPagina(HttpServletRequest request) {
		return "Resultado de Busqueda";
	}

	@Override
	public void escribirContenido(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter respuesta = response.getWriter( );
		
		String criterio = request.getParameter("criterio");
		
		if (criterio.equals("buscarProductoCliente"))
		{
			String nombre = request.getParameter("nombre");
			try
			{
				AplicacionWeb.getInstancia().
			}
			catch (Exception e){
		        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
		        respuesta.write( "<tr>" );
		        respuesta.write( "<td><FONT face=\"arial\" size=5 color=#34495e>El producto buscado no esta disponible</FONT></td>" );
		        respuesta.write( "</tr>" );
		        respuesta.write( "</table>" );
				
			}
		}
		
		else if (criterio.equals("buscarPedido"))
		{
			String nombre = request.getParameter("nombre");
			try
			{
				AplicacionWeb.getInstancia().
			}
			catch (Exception e){
		        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
		        respuesta.write( "<tr>" );
		        respuesta.write( "<td><FONT face=\"arial\" size=5 color=#34495e>El pedido buscado no existe</FONT></td>" );
		        respuesta.write( "</tr>" );
		        respuesta.write( "</table>" );
			}
		}
		
	}

}
