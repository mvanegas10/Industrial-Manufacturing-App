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
		String idProveedor = request.getParameter("idProveedor");
		String[] componentes = request.getParameterValues("componentes");
		
		List<String[]> datosProveedorComponente = new ArrayList<String[]>() ;
		
		try
		{
			for (String id : componentes) {
				String[] datos = {idProveedor, id};
				datosProveedorComponente.add(datos);
			}
			
			AplicacionWeb.getInstancia().registrarProveedorComponente(datosProveedorComponente);
	        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
	        respuesta.write( "<tr>" );
	        respuesta.write( "<td><h3>Se pudo agregar el proveedor con el id " + idProveedor + " de manera correcta</h3></td>" );
	        respuesta.write( "</tr>" );
	        respuesta.write( "</table>" );
		}
		catch (Exception e)
		{
	        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
	        respuesta.write( "<tr>" );
	        respuesta.write( "<td><h3>No se pudo registrar el proveedor</h3></td>" );
	        respuesta.write( "</tr>" );
	        respuesta.write( "</table>" );
		}
	}

}
