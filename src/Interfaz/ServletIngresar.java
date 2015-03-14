package Interfaz;


import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletIngresar extends ServletAbstract{

	@Override
	public String darTituloPagina(HttpServletRequest request) {
		return "Bienvenido";
	}

	@Override
	public void escribirContenido(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try
		
		{
			PrintWriter respuesta = response.getWriter( );
				
			String usuario = request.getParameter("usuario");
			String constrasenia = request.getParameter("contrasenia");
			String tipo = request.getParameter("tipo");
			String tipoUsuario;
			
			try
			{
				tipoUsuario = AplicacionWeb.darInstancia().AtenticarAdminstrador(usuario, constrasenia);
				aceptarIngreso(respuesta);
			}
			catch(Exception e)
			{
				denegarIngreso(respuesta);
			}
		
		}	
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

	public void aceptarIngreso(PrintWriter respuesta){
		
	}
	
	public void aceptarIngreso(PrintWriter respuesta){
		
	}
}
