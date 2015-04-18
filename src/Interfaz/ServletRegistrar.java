package Interfaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.security.util.Password;
import mundo.AplicacionWeb;
import mundo.Producto;

public class ServletRegistrar extends ServletAbstract{

	@Override
	public String darTituloPagina(HttpServletRequest request) {
		return "Seccion Usuarios";
	}

	@Override
	public void escribirContenido(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try
		
		{
			PrintWriter respuesta = response.getWriter( );
				
			String usuario = request.getParameter("usuario");
			String contrasenia = request.getParameter("contrasenia");
			String tipo = request.getParameter("tipo");
			ArrayList<Producto> productos = new ArrayList<Producto>();
			String tipoUsuario;
			
			try
			{
				AplicacionWeb.getInstancia().registrarUsuario(usuario, contrasenia, tipo);
				aceptarIngreso(respuesta, usuario, contrasenia, tipo, productos);
			}
			catch(Exception e)
			{
				System.out.println("Esta bien");
				denegarRegistro(respuesta);
			}
		
		}	
		catch (Exception e)
		{
			System.err.println("Hubo error");
			e.printStackTrace();
		}
		
	}

	public void aceptarIngreso(PrintWriter respuesta, String login, String contrasenia, String tipo, ArrayList<Producto> productos){
		
		if (tipo.equals("admin"))
		{
			respuesta.write( "<table align=\"center\" bgcolor=\"#ecf0f1\" width=\"80%\">" );
			respuesta.write( "<tr>" );
			respuesta.write( "<td><h3>BIENVENIDO " + tipo.toUpperCase() + ": " + login + "</h3></td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "<tr></tr>" );
			respuesta.write( "</table>" );
			
			
			
			respuesta.write( "<table align=\"center\" style=\"border: 2px solid #a1a1a1; padding: 10px 40px;border-radius: 25px;\" width=\"90%\">" );
			respuesta.write( "<tr>" );
			respuesta.write( "<td><table align=\"left\" width=\"30%\">" );
			respuesta.write( "<tr>" );
			respuesta.write( "<td><h3 style=\"padding:0.5em;\"> Proveedores</h3></td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "<tr>" );
			respuesta.write( "<td><form method=\"POST\" action=\"registrarProveedor.htm\"><h4 align=\"left\"><input type=\"submit\" value=\"Registrar Proveedores\" name=\"regProve\" class=\"normal\" style=\"background: #FFF; border: none; padding-left: 5em\"></h4></form></td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "<tr>" );
			respuesta.write( "<td><form method=\"POST\" action=\"resultadoBusqueda.htm\"><h4 align=\"left\"><input value=\"darProveedores\" name=\"criterio\" type=\"hidden\"><input type=\"submit\" value=\"Consultar Proveedores\" name=\"regProve\" class=\"normal\" style=\"background: #FFF; border: none; padding-left: 5em\"></h4></form></td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "<tr></tr>" );
			respuesta.write( "</table>" );
			respuesta.write( "</td>" );

//			Siguiente columna
			
			respuesta.write( "<td><table align=\"center\" width=\"50%\">" );
			respuesta.write( "<tr><form method=\"POST\" action=\"registroLlegadaMaterial.htm\">" );
			respuesta.write( "<tr><td><table align=\"right\" style=\"padding-left: 5em;\" width=\"50%\"><h3 style=\"padding:0.5em;\"> Materiales</h3></td>" );
			respuesta.write( "<td><select style=\"font-size: 16px;\" name=\"tipo\" size=\"1\"  class=\"normal\" \">" );
			respuesta.write( "<option value=\"materiaPrima\">Materia Prima</option>" );
			respuesta.write( "<option value=\"componente\">Componente</option>" );
			respuesta.write( "</select></td></tr></table></td>" );
			respuesta.write( "<tr>" );
			respuesta.write( "<td><h4 align=\"left\"><input value=\"materiaPrima\" name=\"tipo\" type=\"hidden\"><input type=\"submit\" value=\"Registrar Material\" name=\"regMate\" class=\"normal\" style=\"background: #FFF; border: none; padding-left: 5em\"></h4></form></td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "<tr>" );
			respuesta.write( "<td><form method=\"POST\" action=\"resultadoBusqueda.htm\"><h4 align=\"left\"><input value=\"darMaterial\" name=\"criterio\" type=\"hidden\"><h4><input type=\"submit\" value=\"Consultar Material\" name=\"consMate\" class=\"normal\" style=\"background: #FFF; border: none; padding-left: 5em\"></h4></form></td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "<tr></tr>" );
			respuesta.write( "</table>" );
			respuesta.write( "</td>" );


//			Siguiente columna
						
			respuesta.write( "<td><table align=\"right\" width=\"30%\">" );
			respuesta.write( "<tr>" );
			respuesta.write( "<td><h3 style=\"padding:0.5em;\"> Productos</h3></td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "<tr>" );
			respuesta.write( "<td><form method=\"POST\" action=\"registrarProducto.htm\"><h4 align=\"left\"><input type=\"submit\" value=\"Registrar Producto\" name=\"regProd\" class=\"normal\"style=\"background: #FFF; border: none; padding-left: 5em\"></h4></form></td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "<tr>" );
			respuesta.write( "<td><form method=\"POST\" action=\"resultadoBusqueda.htm\"><h4 align=\"left\"><input value=\"darProductos\" name=\"criterio\" type=\"hidden\"><input type=\"submit\" value=\"Consultar Productos\" name=\"consProd\" class=\"normal\"style=\"background: #FFF; border: none; padding-left: 5em\"></h4></form></td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "<tr></tr>" );
			respuesta.write( "</table>" );
			respuesta.write( "</td>" );
			respuesta.write( "</tr>" );
			
//			Siguiente Fila
			
			respuesta.write( "<td>" );
			respuesta.write( "<table align=\"right\" width=\"30%\">" );
			respuesta.write( "<tr>" );
			respuesta.write( "<td><h3> Estaciones</h3></td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "<tr>" );
			respuesta.write( "<td><form method=\"POST\" action=\"registrarEstacion.htm\"><h4 align=\"left\"><input type=\"submit\" value=\"Registrar Estacion\" name=\"regEst\" class=\"normal\"style=\"background: #FFF; border: none; padding-left: 5em\"></h4></form></td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "<tr>" );
			respuesta.write( "<td><form method=\"POST\" action=\"resultadoBusqueda.htm\"><h4 align=\"left\"><input type=\"hidden\" value=\"darEstaciones\" name=\"criterio\"><input type=\"submit\" value=\"Consultar Estaciones\" name=\"consEst\" class=\"normal\"style=\"background: #FFF; border: none; padding-left: 5em\"></h4></form></td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "<tr></tr>" );
			respuesta.write( "</table>" );
			respuesta.write( "</td>" );
			
//			Siguiente columna
						
			respuesta.write( "<td>" );
			respuesta.write( "<td><table align=\"left\" width=\"30%\">" );
			respuesta.write( "<tr>" );
			respuesta.write( "<td><h3 style=\"padding:0.5em;\"> Clientes</h3></td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "<tr>" );
			respuesta.write( "<td><form method=\"POST\" action=\"resultadoBusqueda.htm\"><input value=\"darClientes\" name=\"criterio\" type=\"hidden\"><h4 align=\"left\"><input type=\"submit\" value=\"Consultar Clientes\" name=\"regProve\" class=\"normal\" style=\"background: #FFF; border: none; padding-left: 5em\"></h4></form></td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "<tr></tr>" );
			respuesta.write( "</table>" );
			respuesta.write( "</td>" );
			
//			Siguiente columna
			
			respuesta.write( "<td><table align=\"left\" width=\"50%\">" );
			respuesta.write( "<tr>" );
			respuesta.write( "<td><h3 style=\"padding:0.5em;\"> Pedidos</h3></td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "<tr>" );
			respuesta.write( "<td><form method=\"POST\" action=\"resultadoBusqueda.htm\"><input value=\"consultarPedidos\" name=\"criterio\" type=\"hidden\"><h4 align=\"left\"><input type=\"submit\" value=\"Consultar Pedidos\" name=\"regProve\" class=\"normal\" style=\"background: #FFF; border: none; padding-left: 5em\"></h4></form></td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "<tr></tr>" );
			respuesta.write( "</table>" );
			respuesta.write( "</td>" );
			
			respuesta.write("</tr>");
			respuesta.write( "</table>" );
	        
		}
		else
		{
			respuesta.write( "<table align=\"center\" bgcolor=\"#ecf0f1\" width=80%>" );
	        respuesta.write( "<tr>" );
	        respuesta.write( "<td><h3> BIENVENIDO " + tipo.toUpperCase() + ": " + login + "</h3></td>" );
	        respuesta.write( "</tr>" );
	        respuesta.write( "</table>" );
			respuesta.write( "<div></div>" );
			respuesta.write( "<table align=\"center\" bgcolor=\"#ecf0f1\" width=80%>" );
	        respuesta.write( "<tr>" );
	        respuesta.write( "<td><h4 align=\"center\">Por favor ingresa los siguientes datos para completar tu registro.</h4></td>" );
	        respuesta.write( "</tr>" );
	        respuesta.write( "</table>" );
	        respuesta.write( "<hr>" );
	        
	        respuesta.write( " <form method=\"POST\" action=\"ingreso.htm\"><input type=\"hidden\" value=\"primerLogin\" name=\"primerLogin\"><input type=\"hidden\" value=" + login + " name=\"usuario\" class=\"normal\"><input type=\"hidden\" value=" + contrasenia + " name=\"contrasenia\" class=\"normal\">" );
			respuesta.write( " <table align=\"center\" bgcolor=\"#ecf0f1\" width=\"50%\">" );
			respuesta.write( " <tr>" );
			respuesta.write( " <td><h3>Nombre</h3></td>" );
			respuesta.write( " <td><input type=\"text\" name=\"nombre\" size=\"23\" class=\"normal\"></td>" );
			respuesta.write( " </tr>" );
			respuesta.write( " <tr>" );
			respuesta.write( " <td><h3>Direccion</h3></td>" );
			respuesta.write( " <td><input type=\"text\" name=\"direccion\" size=\"23\" class=\"normal\"></td>" );				
			respuesta.write( " </tr>" );
			respuesta.write( " <tr>" );
			respuesta.write( " <td><h3>Telefono</h3></td>" );
			respuesta.write( " <td><input type=\"text\" name=\"telefono\" size=\"23\" class=\"normal\"></td>" );
			respuesta.write( " </tr>" );
			respuesta.write( " <tr>" );
			respuesta.write( " <td><h3>Ciudad</h3></td>" );
			respuesta.write( " <td><input type=\"text\" name=\"ciudad\" size=\"23\" class=\"normal\"></td>" );
			respuesta.write( " </tr>" );
			respuesta.write( " <tr>" );
			respuesta.write( " <td><h3>Id Representante</h3></td>" );
			respuesta.write( " <td><input type=\"text\" name=\"idRepLegal\" size=\"23\" class=\"normal\"></td>" );
			respuesta.write( " </tr>" );
			respuesta.write( " </table>" );
			respuesta.write( " <p align=center>" );
			respuesta.write( " <input type=\"submit\" value=\"Continuar\" name=\"B1\" class=\"normal\">" );
			respuesta.write( " <input type=\"reset\" value=\"Borrar\" name=\"B2\" class=\"normal\"></p>" );
			respuesta.write( " </form>" );
		}		
	}
	
	public void denegarRegistro(PrintWriter respuesta){
		
        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
        respuesta.write( "<tr>" );
        respuesta.write( "<td><h3>Error: Ya existe un usuario con ese identificador.</h3></td>" );
        respuesta.write( "</tr>" );
        respuesta.write( "</table>" );
		
	}

}
