package Interfaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mundo.AplicacionWeb;

public class ServletRegistrarProducto extends ServletAbstract{

	@Override
	public String darTituloPagina(HttpServletRequest request) {
		return "Registro Producto";
	}

	@Override
	public void escribirContenido(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter respuesta = response.getWriter( );
		
		String idProducto = request.getParameter("idProducto");
		
		int contador = 0;
		
		List<String[]> datosEtapaProduccion = new ArrayList<String[]>();
		
		try {
			int numSecuencia1 = Integer.parseInt(request.getParameter("numSecuencia1"));
			String descripcion1 = request.getParameter("descripcion1");
			String[] d1 = {idProducto, Integer.toString(numSecuencia1), descripcion1};
			contador++;
			datosEtapaProduccion.add(d1);
			
		}
		catch(Exception e){
	        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
	        respuesta.write( "<tr>" );
	        respuesta.write( "<td><FONT face=\"arial\" size=5 color=#34495e>Ingrese por lo menos una etapa</FONT></td>" );
	        respuesta.write( "</tr>" );
	        respuesta.write( "</table>" );
		}
		try{
			int numSecuencia2 = Integer.parseInt(request.getParameter("numSecuencia2"));
			String descripcion2 = request.getParameter("descripcion2");
			String[] d2 = {idProducto, Integer.toString(numSecuencia2), descripcion2};
			contador++;
			datosEtapaProduccion.add(d2);
		}
		catch (Exception e){	
		}
		try{
			int numSecuencia3 = Integer.parseInt(request.getParameter("numSecuencia3"));
			String descripcion3 = request.getParameter("descripcion3");
			String[] d3 = {idProducto, Integer.toString(numSecuencia3), descripcion3};
			contador++;
			datosEtapaProduccion.add(d3);
		}
		catch (Exception e){	
		}
		try{
			int numSecuencia4 = Integer.parseInt(request.getParameter("numSecuencia4"));
			String descripcion4 = request.getParameter("descripcion4");
			String[] d4 = {idProducto, Integer.toString(numSecuencia4), descripcion4};
			contador++;
			datosEtapaProduccion.add(d4);
		}
		catch (Exception e){	
		}
		try{
			int numSecuencia5 = Integer.parseInt(request.getParameter("numSecuencia5"));
			String descripcion5 = request.getParameter("descripcion5");
			String[] d5 = {idProducto, Integer.toString(numSecuencia5), descripcion5};
			contador++;
			datosEtapaProduccion.add(d5);
		}
		catch (Exception e){	
		}
		try{
			int numSecuencia6 = Integer.parseInt(request.getParameter("numSecuencia6"));
			String descripcion6 = request.getParameter("descripcion6");
			String[] d6 = {idProducto, Integer.toString(numSecuencia6), descripcion6};
			contador++;
			datosEtapaProduccion.add(d6);
		}
		catch (Exception e){	
		}
		try{
			int numSecuencia7 = Integer.parseInt(request.getParameter("numSecuencia7"));
			String descripcion7 = request.getParameter("descripcion7");
			String[] d7 = {idProducto, Integer.toString(numSecuencia7), descripcion7};
			contador++;
			datosEtapaProduccion.add(d7);
		}
		catch (Exception e){	
		}
		try{
			int numSecuencia8 = Integer.parseInt(request.getParameter("numSecuencia8"));
			String descripcion8 = request.getParameter("descripcion8");
			String[] d8 = {idProducto, Integer.toString(numSecuencia8), descripcion8};
			contador++;
			datosEtapaProduccion.add(d8);
		}
		catch (Exception e){	
		}
		try{
			int numSecuencia9 = Integer.parseInt(request.getParameter("numSecuencia9"));
			String descripcion9 = request.getParameter("descripcion9");
			String[] d9 = {idProducto, Integer.toString(numSecuencia9), descripcion9};
			contador++;
			datosEtapaProduccion.add(d9);
		}
		catch (Exception e){	
		}
		try{
			int numSecuencia10 = Integer.parseInt(request.getParameter("numSecuencia10"));
			String descripcion10 = request.getParameter("descripcion10");
			String[] d10 = {idProducto, Integer.toString(numSecuencia10), descripcion10};
			contador++;
			datosEtapaProduccion.add(d10);
		}
		catch (Exception e){	
		}
		try
		{
			AplicacionWeb.getInstancia().registrarProductoEtapasProduccion(datosEtapaProduccion);
	        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
	        respuesta.write( "<tr>" );
	        respuesta.write( "<td><FONT face=\"arial\" size=5 color=#34495e>Su producto y las etapas de produccion se han registrado de manera exitosa</FONT></td>" );
	        respuesta.write( "</tr>" );
	        respuesta.write( "</table>" );
		}
		catch (Exception e)
		{
	        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
	        respuesta.write( "<tr>" );
	        respuesta.write( "<td><FONT face=\"arial\" size=5 color=#34495e>Hubo un error al registrar el producto y sus etapas de produccion</FONT></td>" );
	        respuesta.write( "</tr>" );
	        respuesta.write( "</table>" );
		}
	}

}
