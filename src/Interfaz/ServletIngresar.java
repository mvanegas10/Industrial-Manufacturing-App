package Interfaz;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mundo.AplicacionWeb;

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
				tipoUsuario = AplicacionWeb.getInstancia().ingresarUsuario(usuario, constrasenia);
				aceptarIngreso(respuesta, usuario, tipo);
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

	public void aceptarIngreso(PrintWriter respuesta, String login, String tipo){
		
        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
        respuesta.write( "<tr>" );
        respuesta.write( "<td><FONT face=\"arial\" size=5 color=#34495e>" + tipo.toUpperCase() + ": " + login + "</FONT></td>" );
        respuesta.write( "</tr>" );
        respuesta.write( "</table>" );
		respuesta.write( "<div></div>" );
        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
        respuesta.write( "<tr>" );
        respuesta.write( "<a href=\"registrarProveedor\"><button>Registrar Proveedor</button></a>" );
        respuesta.write( "</tr>" );
        respuesta.write( "</table>" );
		
	}
	
	public void denegarIngreso(PrintWriter respuesta){
		
        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
        respuesta.write( "<tr>" );
        respuesta.write( "<td><FONT face=\"arial\" size=5 color=#34495e>Error: Usuario o contraseña incorrectos</FONT></td>" );
        respuesta.write( "</tr>" );
        respuesta.write( "</table>" );
		
	}
}
