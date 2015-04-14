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
				aceptarIngreso(respuesta, usuario, tipo, productos);
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

	public void aceptarIngreso(PrintWriter respuesta, String login, String tipo, ArrayList<Producto> productos){
		
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
			respuesta.write( "<td><form method=\"POST\" action=\"registrarProveedor.htm\"><h4 align=\"left\"><input type=\"submit\" value=\"Consultar Proveedores\" name=\"regProve\" class=\"normal\" style=\"background: #FFF; border: none; padding-left: 5em\"></h4></form></td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "<tr></tr>" );
			respuesta.write( "</table>" );
			respuesta.write( "</td>" );

//			Siguiente columna
			
			respuesta.write( "<td><table align=\"center\" width=\"50%\">" );
			respuesta.write( "<tr>" );
			respuesta.write( "<td><table align=\"right\" style=\"padding-left: 5em;\" width=\"50%\"><h3 style=\"padding:0.5em;\"> Materiales</h3></td>" );
			respuesta.write( "<td><select style=\"font-size: 14px;\" name=\"tipo\" size=\"1\"  class=\"normal\" \">" );
			respuesta.write( "<option value=\"materiaPrima\">Materia Prima</option>" );
			respuesta.write( "<option value=\"componente\">Componente</option>" );
			respuesta.write( "</select></table></td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "<tr>" );
			respuesta.write( "<td><form method=\"POST\" action=\"registroLlegadaMaterial.htm\"><h4 ><input value=\"materiaPrima\" name=\"tipo\"><input type=\"submit\" value=\"Registrar Material\" name=\"regMate\" class=\"normal\" style=\"background: #FFF; border: none; padding-left: 5em\"></h4></form></td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "<tr>" );
			respuesta.write( "<td><form method=\"POST\" action=\"resultadoBusqueda.htm\"><h4><input value=\"componente\" name=\"tipo\"><input type=\"submit\" value=\"Consultar Material\" name=\"consMate\" class=\"normal\" style=\"background: #FFF; border: none; padding-left: 5em\"></h4></form></td>" );
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
			respuesta.write( "<td><form method=\"POST\" action=\"registrarProducto.htm\"><h4 align=\"left\"><input type=\"submit\" value=\"Consultar Productos\" name=\"consProd\" class=\"normal\"style=\"background: #FFF; border: none; padding-left: 5em\"></h4></form></td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "<tr></tr>" );
			respuesta.write( "</table>" );
			respuesta.write( "</td>" );
			respuesta.write( "</tr>" );
			
//			Siguiente Fila
			
			respuesta.write( "<tr>" );
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
			respuesta.write( "<td><form method=\"POST\" action=\"resultadoBusqueda.htm\"><input value=\"darPedidos\" name=\"criterio\" type=\"hidden\"><h4 align=\"left\"><input type=\"submit\" value=\"Consultar Pedidos\" name=\"regProve\" class=\"normal\" style=\"background: #FFF; border: none; padding-left: 5em\"></h4></form></td>" );
			respuesta.write( "</tr>" );
			respuesta.write( "<tr></tr>" );
			respuesta.write( "</table>" );
			respuesta.write( "</td>" );
			
//			Siguiente columna
			
			respuesta.write( "<td>" );
			respuesta.write( "<table align=\"right\" width=\"30%\">" );
//			respuesta.write( "<tr>" );
//			respuesta.write( "<td><h3 style=\"padding:0.5em;\"> Productos</h3></td>" );
//			respuesta.write( "</tr>" );
//			respuesta.write( "<tr>" );
//			respuesta.write( "<td><form method=\"POST\" action=\"registrarProducto.htm\"><h4 align=\"left\"><input type=\"submit\" value=\"Registrar Producto\" name=\"regProd\" class=\"normal\"style=\"background: #FFF; border: none; padding-left: 5em\"></h4></form></td>" );
//			respuesta.write( "</tr>" );
//			respuesta.write( "<tr>" );
//			respuesta.write( "<td><form method=\"POST\" action=\"registrarProducto.htm\"><h4 align=\"left\"><input type=\"submit\" value=\"Consultar Productos\" name=\"consProd\" class=\"normal\"style=\"background: #FFF; border: none; padding-left: 5em\"></h4></form></td>" );
//			respuesta.write( "</tr>" );
//			respuesta.write( "<tr></tr>" );
			respuesta.write( "</table>" );
			respuesta.write( "</td>" );
			respuesta.write( "</tr>" );
			
			respuesta.write( "</table>" );
	        
		}
		else
		{
			try
			{
				productos = AplicacionWeb.getInstancia().darCantidadProductos(100);
				

				respuesta.write( "<table align=\"center\" bgcolor=\"#ecf0f1\" width=80%>" );
		        respuesta.write( "<tr>" );
		        respuesta.write( "<td><h3> BIENVENIDO " + tipo.toUpperCase() + ": " + login + "</h3></td>" );
		        respuesta.write( "</tr>" );
		        respuesta.write( "</table>" );
				respuesta.write( "<div></div>" );
				respuesta.write( "<table align=\"center\" bgcolor=\"#ecf0f1\" width=80%>" );
		        respuesta.write( "<tr>" );
		        respuesta.write( "<td><form method=\"POST\" action=\"resultadoBusqueda.htm\"><input type=\"hidden\" value=" + login + " name=\"login\" class=\"normal\"><input type=\"hidden\" value=\"darPedidos\" name=\"criterio\" class=\"normal\"><h4 align=\"left\"><input type=\"submit\" value=\"Consulta los detalles de tus pedidos\" name=\"regProve\" class=\"normal\" style=\"background: #FFF; border: none; padding-left: 5em\"></h4></form></td>" );
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
			        respuesta.write( "<input type=\"hidden\" name=\"idProducto\" value=" + productos.get(i).getId() + " >" );
			        respuesta.write( "<input type=\"hidden\" name=\"login\" value=" + login + ">" );
		        	respuesta.write( "<td><input alt=\"Producto\" src=\"imagenes/producto.jpg\" type=\"image\" name=\"producto\" value=" + productos.get(i).getId() + "></td>" );
			        respuesta.write( "<td><input value=" + productos.get(i).getNombre() + " name=\"nombre\" style=\"background: #FFFFFF; border: none;\" type=\"submit\"\"></td>" );
			        respuesta.write( "</form>" );
			        try
			        {
			        	respuesta.write( "<form method=\"POST\" action=\"resultadoBusqueda.htm\">" );
			        	respuesta.write( "<input type=\"hidden\" name=\"criterio\" value=\"buscarProductoCliente\" >" );
			        	respuesta.write( "<input type=\"hidden\" name=\"idProducto\" value=" + productos.get(i+1).getId() + " >" );
				        respuesta.write( "<input type=\"hidden\" name=\"login\" value=" + login + ">" );
			        	respuesta.write( "<td><input alt=\"Producto\" src=\"imagenes/producto.jpg\" type=\"image\" name=\"producto\" value=" + productos.get(i + 1).getId() + "></td>" );
				        respuesta.write( "<td><input value=" + productos.get(i+1).getNombre() + " name=\"idProducto\" style=\"background: #FFFFFF; border: none;\" type=\"submit\"\"></td>" );
				        respuesta.write( "</form>" );
			        }
			        catch(Exception e2){	
			        }
			        try
			        {
			        	respuesta.write( "<form method=\"POST\" action=\"resultadoBusqueda.htm\">" );
			        	respuesta.write( "<input type=\"hidden\" name=\"criterio\" value=\"buscarProductoCliente\" >" );
			        	respuesta.write( "<input type=\"hidden\" name=\"idProducto\" value=" + productos.get(i+2).getId() + " >" );
				        respuesta.write( "<input type=\"hidden\" name=\"login\" value=" + login + ">" );
			        	respuesta.write( "<td><input alt=\"Producto\" src=\"imagenes/producto.jpg\" type=\"image\" name=\"producto\" value=" + productos.get(i + 2).getId() + "></td>" );
				        respuesta.write( "<td><input value=" + productos.get(i+2).getNombre() + " name=\"idProducto\" style=\"background: #FFFFFF; border: none;\" type=\"submit\"\"></td>" );
				        respuesta.write( "</form>" );
			        }
			        catch(Exception e3){	
			        }
			        try
			        {
			        	respuesta.write( "<form method=\"POST\" action=\"resultadoBusqueda.htm\">" );
			        	respuesta.write( "<input type=\"hidden\" name=\"criterio\" value=\"buscarProductoCliente\" >" );
			        	respuesta.write( "<input type=\"hidden\" name=\"idProducto\" value=" + productos.get(i+3).getId() + " >" );
				        respuesta.write( "<input type=\"hidden\" name=\"login\" value=" + login + ">" );
			        	respuesta.write( "<td><input alt=\"Producto\" src=\"imagenes/producto.jpg\" type=\"image\" name=\"producto\" value=" + productos.get(i + 3).getId() + "></td>" );
			        	respuesta.write( "<td><input value=" + productos.get(i+3).getNombre() + " name=\"idProducto\" style=\"background: #FFFFFF; border: none;\" type=\"submit\"\"></td>" );
				        respuesta.write( "</form>" );
			        }
			        catch(Exception e4){	
			        }
			        respuesta.write( "</tr>" );
			        i+=4;
		        }
		        respuesta.write( "</table>" );
			}
			catch (Exception e1){
			}
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
