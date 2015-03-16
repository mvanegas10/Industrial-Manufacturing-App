package Interfaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mundo.AplicacionWeb;

public class ServletRegistrarProveedor extends ServletAbstract{

	@Override
	public String darTituloPagina(HttpServletRequest request) {
		return "Registro de Proveedores al Sistema";
	}

	@Override
	public void escribirContenido(HttpServletRequest request,HttpServletResponse response) throws IOException {

		PrintWriter respuesta = response.getWriter( );
		String ciudad = request.getParameter("ciudad");
		String direccion = request.getParameter("direccion");
		int telefono = Integer.parseInt(request.getParameter("telefono"));
		String idRepLegal = request.getParameter("idRepLegal");
		String[] materiasPrimas = request.getParameterValues("materiasPrimas");
		String[] componentes = request.getParameterValues("componentes");
		
		List<String[]> datosProveedorMateriaPrima = new ArrayList<String[]>() ;
		List<String[]> datosProveedorComponente = new ArrayList<String[]>() ;
		
		try
		{
			String idProveedor = Integer.toString( AplicacionWeb.getInstancia().darContadorId());
			for (String id : materiasPrimas) {
				String[] datos = {idProveedor, id};
				datosProveedorMateriaPrima.add(datos);
			}

			for (String id : componentes) {
				String[] datos = {idProveedor, id};
				datosProveedorComponente.add(datos);
			}
			
			AplicacionWeb.getInstancia().registrarProveedor(idProveedor, direccion, telefono, ciudad, idRepLegal, datosProveedorMateriaPrima, datosProveedorComponente);
	        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
	        respuesta.write( "<tr>" );
	        respuesta.write( "<td><FONT face=\"arial\" size=5 color=#34495e>Se pudo agregó el proveedor con el id " + idProveedor + " de manera correcta</FONT></td>" );
	        respuesta.write( "</tr>" );
	        respuesta.write( "</table>" );
		}
		catch (Exception e)
		{
	        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
	        respuesta.write( "<tr>" );
	        respuesta.write( "<td><FONT face=\"arial\" size=5 color=#34495e>No se pudo registrar el proveedor</FONT></td>" );
	        respuesta.write( "</tr>" );
	        respuesta.write( "</table>" );
		}
	}

}
