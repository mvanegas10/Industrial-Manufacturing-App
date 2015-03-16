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
		
		List<String[]> datosProveedorMateriaPrima = new ArrayList<String[]>() ;
		
		try
		{
			String idProveedor = Integer.toString( AplicacionWeb.getInstancia().darContadorId());
			for (String id : materiasPrimas) {
				String[] datos = {idProveedor, id};
				datosProveedorMateriaPrima.add(datos);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
