package Interfaz;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mundo.AplicacionWeb;

public class ServletIngresar extends ServletAbstract{

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
			String constrasenia = request.getParameter("contrasenia");
			ArrayList<String> productos = new ArrayList<String>();
			String tipo;
			
			try
			{
				productos = AplicacionWeb.getInstancia().darCantidadProductos(100);
			}
			catch (Exception e1){
			}
			
			try
			{
				tipo = AplicacionWeb.getInstancia().ingresarUsuario(usuario, constrasenia);
				aceptarIngreso(respuesta, usuario, tipo, productos);
				
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

	public void aceptarIngreso(PrintWriter respuesta, String login, String tipo, ArrayList<String> productos){
		
		if (tipo.equals("admin"))
		{
			respuesta.write( "<table align=\"center\" bgcolor=\"#ecf0f1\" width=80%>" );
	        respuesta.write( "<tr>" );
	        respuesta.write( "<td><h3> BIENVENIDO " + tipo.toUpperCase() + ": " + login + "</h3></td>" );
	        respuesta.write( "</tr>" );
	        respuesta.write( "<tr></tr>" );
	        respuesta.write( "</table>" );
	        respuesta.write( "<hr>" );
	        respuesta.write( "<tr><table align=\"center\" bgcolor=\"#ecf0f1\" width=80%>" );
	        respuesta.write( "<tr><h3>Seccion Proveedores</h3></tr>" );
	        respuesta.write( "<tr><form method=\"POST\" action=\"registrarProveedor.htm\"><h4 align=left><input type=\"submit\" value=\"Registrar Proveedores\" name=\"regProve\" class=\"normal\" style=\"background: #FFFFFF; border: none; padding-left:5em\"></h4></tr></form>" );
	        respuesta.write( "<tr><form method=\"POST\" action=\"registrarProveedor.htm\"><h4 align=left><input type=\"submit\" value=\"Consultar Proveedores\" name=\"regProve\" class=\"normal\" style=\"background: #FFFFFF; border: none; padding-left:5em\"></h4></tr></form>" );
	        respuesta.write( "<tr></tr>" );
	        respuesta.write( "</table></tr>" );
	        respuesta.write( "<hr>" );
	        respuesta.write( "<tr><table align=\"center\" bgcolor=\"#ecf0f1\" width=80%>" );
	        respuesta.write( "<tr><h3>Seccion Material</h3></tr>" );
	        respuesta.write(" <tr><form method=\"POST\" action=\"registroLlegadaMaterial.htm\">");
	        respuesta.write( "<td><h4 align=left style=\"padding-left:5em;\">Tipo de Material</h4></td>" );
	        respuesta.write(" <td><select name=\"llegadaMaterial\"> class=\"normal\"");
	        respuesta.write(" <option value=\"materiaPrima\">Materia Prima</option> ");
	        respuesta.write(" <option value=\"componente\">Componente</option> ");
	        respuesta.write(" </select></td>");
	        respuesta.write(" <td><input type=\"submit\" value=\"Registrar\" name=\"B1\" class=\"normal\" style=\"background: #FFFFFF; border: none;\"></td>");
	        respuesta.write(" </form></tr>");
	        respuesta.write( "<tr><form method=\"POST\" action=\"registrarProveedor.htm\"><h4 align=left><input type=\"submit\" value=\"Consultar Material\" name=\"regProve\" class=\"normal\" style=\"background: #FFFFFF; border: none; padding-left:5em\"></h4></tr></form>" );
	        respuesta.write( "<tr></tr>" );
	        respuesta.write( "</table></tr>" );
	        respuesta.write( "<hr>" );
	        respuesta.write( "<tr><table align=\"center\" bgcolor=\"#ecf0f1\" width=80%>" );
	        respuesta.write( "<tr><h3>Seccion Productos</h3></tr>" );
	        respuesta.write( "<tr><form method=\"POST\" action=\"registrarProducto.htm\"><h4 align=left><input type=\"submit\" value=\"Registrar Producto\" name=\"regProd\" class=\"normal\" style=\"background: #FFFFFF; border: none; padding-left:5em\"></h4></tr></form>" );
	        respuesta.write( "<tr><form method=\"POST\" action=\"registrarProducto.htm\"><h4 align=left><input type=\"submit\" value=\"Consultar Productos\" name=\"regProve\" class=\"normal\" style=\"background: #FFFFFF; border: none; padding-left:5em\"></h4></tr></form>" );
	        respuesta.write( "<tr></tr>" );
	        respuesta.write( "</table></tr>" );
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
	        respuesta.write( "<td><a href=\"buscarPedido.htm\">Consulta los detalles de tus pedidos</a></td>" );
	        respuesta.write( "</tr>" );
	        respuesta.write( "</table>" );
	        respuesta.write( "<hr>" );
	        respuesta.write( "<table align=\"center\" bgcolor=\"#ecf0f1\" width=80%>" );
	        respuesta.write( "<tr>" );
	        respuesta.write( "<form method=\"POST\" action=\"resultadoBusqueda.htm\">" );
	        respuesta.write( "<td><input style=\"color=#BFBFBF\" type=\"text\" name=\"nombre\" class=\"normal\" value=\"Ingresa el nombre de un producto que estes buscando\" size=\"110\"></td>" );
	        respuesta.write( "<input type=\"hidden\" value=" + login + " name=\"login\" class=\"normal\">" );
	        respuesta.write( "<input type=\"hidden\" value=\"buscarProductoCliente\" name=\"criterio\" class=\"normal\">" );
	        respuesta.write( "<td><input type=\"submit\" value=\"Buscar Productos\" name=\"BusProd\" class=\"normal\"></td></form>" );
	        respuesta.write( "</tr>" );
	        respuesta.write( "</table>" );
	        respuesta.write( "<table align=\"center\" bgcolor=\"#ecf0f1\" width=80%>" );
	        respuesta.write( "<tr>" );
	        respuesta.write( "<td><h3> Productos que podrían interesarte </h3></td>" );
	        respuesta.write( "</tr>" );
	        respuesta.write( "</table>" );
	        respuesta.write( "<table align=\"center\" bgcolor=\"#ecf0f1\" width=80%>" );
	        for (int i = 0; i < productos.size(); i++) {
	        	respuesta.write( "<tr>" );
	        	respuesta.write( "<form method=\"POST\" action=\"resultadoBusqueda.htm\">" );
		        respuesta.write( "<input type=\"hidden\" name=\"criterio\" value=\"buscarProductoCliente\" >" );
		        respuesta.write( "<input type=\"hidden\" name=\"login\" value=" + login + ">" );
	        	respuesta.write( "<td><input alt=\"Producto\" src=\"imagenes/producto.jpg\" type=\"image\" name=\"producto\" value=" + productos.get(i) + "></td>" );
		        respuesta.write( "<td><input value=" + productos.get(i) + " name=\"nombre\" style=\"background: #FFFFFF; border: none;\" type=\"submit\"\"></td>" );
		        respuesta.write( "</form>" );
		        try
		        {
		        	respuesta.write( "<form method=\"POST\" action=\"resultadoBusqueda.htm\">" );
		        	respuesta.write( "<input type=\"hidden\" name=\"criterio\" value=\"buscarProductoCliente\" >" );
			        respuesta.write( "<input type=\"hidden\" name=\"login\" value=" + login + ">" );
		        	respuesta.write( "<td><input alt=\"Producto\" src=\"imagenes/producto.jpg\" type=\"image\" name=\"producto\" value=" + productos.get(i + 1) + "></td>" );
			        respuesta.write( "<td><input value=" + productos.get(i+1) + " name=\"nombre\" style=\"background: #FFFFFF; border: none;\" type=\"submit\"\"></td>" );
			        respuesta.write( "</form>" );
		        }
		        catch(Exception e2){	
		        }
		        try
		        {
		        	respuesta.write( "<form method=\"POST\" action=\"resultadoBusqueda.htm\">" );
		        	respuesta.write( "<input type=\"hidden\" name=\"criterio\" value=\"buscarProductoCliente\" >" );
			        respuesta.write( "<input type=\"hidden\" name=\"login\" value=" + login + ">" );
		        	respuesta.write( "<td><input alt=\"Producto\" src=\"imagenes/producto.jpg\" type=\"image\" name=\"producto\" value=" + productos.get(i + 2) + "></td>" );
			        respuesta.write( "<td><input value=" + productos.get(i+2) + " name=\"nombre\" style=\"background: #FFFFFF; border: none;\" type=\"submit\"\"></td>" );
			        respuesta.write( "</form>" );
		        }
		        catch(Exception e3){	
		        }
		        try
		        {
		        	respuesta.write( "<form method=\"POST\" action=\"resultadoBusqueda.htm\">" );
		        	respuesta.write( "<input type=\"hidden\" name=\"criterio\" value=\"buscarProductoCliente\" >" );
			        respuesta.write( "<input type=\"hidden\" name=\"login\" value=" + login + ">" );
		        	respuesta.write( "<td><input alt=\"Producto\" src=\"imagenes/producto.jpg\" type=\"image\" name=\"producto\" value=" + productos.get(i + 3) + "></td>" );
		        	respuesta.write( "<td><input value=" + productos.get(i+3) + " name=\"nombre\" style=\"background: #FFFFFF; border: none;\" type=\"submit\"\"></td>" );
			        respuesta.write( "</form>" );
		        }
		        catch(Exception e4){	
		        }
		        try
		        {
		        	respuesta.write( "<form method=\"POST\" action=\"resultadoBusqueda.htm\">" );
		        	respuesta.write( "<input type=\"hidden\" name=\"login\" value=" + login + ">" );
			        respuesta.write( "<input type=\"hidden\" name=\"criterio\" value=\"buscarProductoCliente\" >" );
		        	respuesta.write( "<td><input alt=\"Producto\" src=\"imagenes/producto.jpg\" type=\"image\" name=\"producto\" value=" + productos.get(i + 4) + "></td>" );
			        respuesta.write( "<td><input value=" + productos.get(i+4) + " name=\"nombre\" style=\"background: #FFFFFF; border: none;\" type=\"submit\"\"></td>" );
			        respuesta.write( "</form>" );
		        }
		        catch(Exception e5){	
		        }
		        respuesta.write( "</tr>" );
		        i+=5;
	        }
	        respuesta.write( "</table>" );
		}
	}
	
	public void denegarIngreso(PrintWriter respuesta){
		
        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
        respuesta.write( "<tr>" );
        respuesta.write( "<td><h3>Error: Usuario o contrasenia incorrectos</FONT></td>" );
        respuesta.write( "</tr>" );
        respuesta.write( "</table>" );
		
	}
}
