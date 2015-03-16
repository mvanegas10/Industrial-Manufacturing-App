package Interfaz;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.security.util.Password;
import mundo.AplicacionWeb;

public class ServletRegistrar extends ServletAbstract{

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
			String contrasenia = request.getParameter("contrasenia");
			String tipo = request.getParameter("tipo");
			String tipoUsuario;
			
			try
			{
				AplicacionWeb.getInstancia().registrarUsuario(usuario, contrasenia, tipo);
				aceptarIngreso(respuesta, usuario, tipo);
			}
			catch(Exception e)
			{
				denegarRegistro(respuesta);
			}
		
		}	
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

	public void aceptarIngreso(PrintWriter respuesta, String login, String tipo){
		
		if (tipo.equals("admin"))
		{
	        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
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
	        respuesta.write(" </form></td> ");
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
	        respuesta.write( "<td>Página en construcción, espérala.</td>" );
	        respuesta.write( "</tr>" );
	        respuesta.write( "</table>" );
		}
		
	}
	
	public void denegarRegistro(PrintWriter respuesta){
		
        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
        respuesta.write( "<tr>" );
        respuesta.write( "<td><FONT face=\"arial\" size=5 color=#34495e>Error: Ya existe un usuario con ese login</FONT></td>" );
        respuesta.write( "</tr>" );
        respuesta.write( "</table>" );
		
	}

}
