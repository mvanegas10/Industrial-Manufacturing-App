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
		
		if (tipo == "admin")
		{
	        respuesta.write( "<table align=\"center\" bgcolor=\"#ecf0f1\" width=80%>" );
	        respuesta.write( "<tr>" );
	        respuesta.write( "<td><FONT face=\"arial\" size=5 color=#34495e>" + tipo.toUpperCase() + ": " + login + "</FONT></td>" );
	        respuesta.write( "</tr>" );
	        respuesta.write( "</table>" );
			respuesta.write( "<div></div>" );
	        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
	        respuesta.write( "<tr>" );
	        respuesta.write( "<td><form method=\"POST\" action=\"registrarProveedor.htm\"><input type=\"submit\" value=\"Registrar Proveedor\" name=\"registrar\" class=\"normal\"></form></td>" );
	        respuesta.write( "</tr>" );
	        respuesta.write( "<tr>" );
	        respuesta.write(" <td><form method=\"POST\" action=\"registroLlegadaMaterial.htm\">Registrar Llegada de Material: ");
	        respuesta.write(" <select size=\"1\" name=\"llegadaMaterial\" class=\"normal\"> ");
	        respuesta.write(" <option value=\"materiaPrima\">Materia Prima</option> ");
	        respuesta.write(" <option value=\"componente\">Componente</option> ");
	        respuesta.write(" </select> ");
	        respuesta.write(" </form>");
	        respuesta.write( "</tr>" );
	        respuesta.write( "<tr>" );
	        respuesta.write( "<td>Registrar Producto</td>" );
	        respuesta.write( "<td><form method=\"POST\" action=\"registrarProducto.htm\"><input type=\"submit\" value=\"Registrar Producto\" name=\"regProd\" class=\"normal\"></form></td>" );
	        respuesta.write( "<td>.</td>" );
	        respuesta.write( "</tr>" );
	        respuesta.write( "</table>" );
		}
		else
		{
	        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
	        respuesta.write( "<tr>" );
	        respuesta.write( "<td><FONT face=\"arial\" size=5 color=#34495e>" + tipo.toUpperCase() + ": " + login + "</FONT></td>" );
	        respuesta.write( "</tr>" );
	        respuesta.write( "</table>" );
			respuesta.write( "<div></div>" );
	        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
	        respuesta.write( "<tr>" );
	        respuesta.write( "<td>Pagina en construccion, esperala.</td>" );
	        respuesta.write( "</tr>" );
	        respuesta.write( "</table>" );
		}
	}
	
	public void denegarIngreso(PrintWriter respuesta){
		
        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
        respuesta.write( "<tr>" );
        respuesta.write( "<td><FONT face=\"arial\" size=5 color=#34495e>Error: Usuario o contrase�a incorrectos</FONT></td>" );
        respuesta.write( "</tr>" );
        respuesta.write( "</table>" );
		
	}
}
