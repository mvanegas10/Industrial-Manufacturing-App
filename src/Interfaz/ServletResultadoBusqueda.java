package Interfaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mundo.AplicacionWeb;
import mundo.Producto;

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
				ArrayList<Producto> productos = AplicacionWeb.getInstancia().buscarProducto(nombre);
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
			try
			{
				int dia1 = Integer.parseInt(request.getParameter("dia1"));
				int mes1 = Integer.parseInt(request.getParameter("mes1"));
				int anio1 = Integer.parseInt(request.getParameter("anio1"));
				Date diaSolicitud = new Date();
				diaSolicitud.setDate(dia1);
				diaSolicitud.setMonth(mes1);
				diaSolicitud.setYear(anio1);
			}
			catch(Exception e){
			}
			try{
				int dia2 = Integer.parseInt(request.getParameter("dia2"));
				int mes2 = Integer.parseInt(request.getParameter("mes2"));
				int anio2 = Integer.parseInt(request.getParameter("anio2"));
				Date diaEntrega = new Date();
				diaEntrega.setDate(dia2);
				diaEntrega.setMonth(mes2);
				diaEntrega.setYear(anio2);
			}
			catch(Exception e){
				
			}
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
		else if (criterio.equals("buscarAdmin"))
		{
			String materialABuscar = request.getParameter("material");
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
