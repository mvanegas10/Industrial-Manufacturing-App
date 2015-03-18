package Interfaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mundo.AplicacionWeb;

public class ServletEliminarPedido extends ServletAbstract {

	@Override
	public String darTituloPagina(HttpServletRequest request) {
		return "Anulación de pedido";
	}

	@Override
	public void escribirContenido(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter respuesta = response.getWriter( );
		
		String producto = request.getParameter("pedidoEliminado");
		
		try
		{
			ArrayList<String> pedidos = AplicacionWeb.getInstancia().darIdPedido(producto);
			for (String string : pedidos) {
//				AplicacionWeb.getInstancia().
			}
	        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
	        respuesta.write( "<tr>" );
	        respuesta.write( "<td><FONT face=\"arial\" size=5 color=#34495e>Su pedido se ha eliminado de manera correcta.</FONT></td>" );
	        respuesta.write( "</tr>" );
	        respuesta.write( "</table>" );
		}
		catch(Exception e){
	        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
	        respuesta.write( "<tr>" );
	        respuesta.write( "<td><FONT face=\"arial\" size=5 color=#34495e>Hubo un error en el proceso</FONT></td>" );
	        respuesta.write( "</tr>" );
	        respuesta.write( "</table>" );
		}
	}

}
