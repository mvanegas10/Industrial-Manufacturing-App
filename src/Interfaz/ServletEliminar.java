package Interfaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mundo.AplicacionWeb;
import mundo.Componente;
import mundo.MateriaPrima;
import mundo.Pedido;
import mundo.Producto;
import mundo.Proveedor;
import mundo.Usuario;

public class ServletEliminar extends ServletAbstract{

	@Override
	public String darTituloPagina(HttpServletRequest request) {
		return "Eliminar";
	}

	@Override
	public void escribirContenido(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter respuesta = response.getWriter( );
	
		String criterio = request.getParameter("criterio");
				
		if (criterio.equals("eliminarPedido"))
			eliminarPedido(request, respuesta);
		
		else if (criterio.equals("eliminarEstacion"))
			eliminarEstacion(request, respuesta);
		

	}
	
	public void eliminarPedido (HttpServletRequest request, PrintWriter respuesta){
		String login = request.getParameter("login");
		String idPedido = request.getParameter("idPedido");
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
		try
		{
			AplicacionWeb.getInstancia().eliminarPedidoCliente(login, idPedido);
		}
		catch (Exception e)
		{
			error(respuesta);
		}
		try 
        {
        	pedidos = AplicacionWeb.getInstancia().darPedidosCliente(login);
        	if (pedidos.size() != 0)
	        {
        		respuesta.write( "<h4 align=\"center\">Tienes registrados " + pedidos.size() + " pedidos en total:</h4>" );
        		respuesta.write( "<table align=\"center\" bgcolor=\"#ecf0f1\" width=50%>" );
		        for (Pedido ped : pedidos) {
		        	String producto = AplicacionWeb.getInstancia().darNombreProducto(ped.getIdProducto());
		        	respuesta.write( "<form method=\"POST\" action=\"resultadoBusqueda.htm\">" );
		        	respuesta.write( "<tr>" );
			        respuesta.write( "<tr><td><img alt=\"Producto\" src=\"imagenes/producto.jpg\" name=\"producto\"></td>" );
			        respuesta.write( "<td><table align=\"center\" bgcolor=\"#ecf0f1\" width=30%>" );
			        respuesta.write( "<tr><td align=\"left\"><h4><input value=\"Producto Pedido: \" name=\"label1\" style=\"border: none;\" type=\"text\"\"></h4></td><td align=\"right\">" + producto + "</td></tr>" );
			        respuesta.write( "<tr><td align=\"left\"><h4><input value=\"Unidades Pedidas: \" name=\"label2\" style=\"border: none;\" type=\"text\"\"></h4></td><td align=\"right\">" + ped.getCantidad() + "</td></tr>" );
			        respuesta.write( "<tr><td align=\"left\"><h4><input value=\"Fecha Pedido: \" name=\"label2\" style=\"border: none;\" type=\"text\"\"></h4></td><td align=\"right\">" + (ped.getFechaPedido().toLocaleString()).substring(0, 10) + "</td></tr>" );
			        respuesta.write( "<tr><td align=\"left\"><h4><input value=\"Fecha Entrega: \" name=\"label2\" style=\"border: none;\" type=\"text\"\"></h4></td><td align=\"right\" size=\"\">" + (ped.getFechaEntrega().toLocaleString()).substring(0, 10) + "</td></tr>" );
			        respuesta.write( "<tr><td align=\"right\"><input value=" + ped.getId() + " name=\"idPedido\" type=\"hidden\"><input value=" + login + " name=\"login\" type=\"hidden\"><input value=\"eliminarPedido\" name=\"criterio\" type=\"hidden\"><input value=\"Eliminar Pedido\" size=\"53\" name=\"eliminar\" type=\"submit\"></td></tr>" );
			        respuesta.write( "</table></td>" );
			        respuesta.write( "</tr>" );
			        respuesta.write( "<tr></tr>" );
			        respuesta.write( "</form>" );
				}
		        respuesta.write( "</table>" );
	        }
        	else
        		noHayPedidos(login, respuesta);
        }
		catch (Exception e)
        {
        	noHayPedidos(login, respuesta);
        }
	}
	
	public void eliminarEstacion(HttpServletRequest request, PrintWriter respuesta){
		
	}
	
	public void noHayPedidos(String login, PrintWriter respuesta){
		respuesta.write( "<h4 align=\"center\">No has registrado ningún pedido con nosotros, creemos que estos productos que podrían interesarte.</h4>" );
    	ArrayList<Producto> productos = new ArrayList<Producto>();
    	try
		{
			productos = AplicacionWeb.getInstancia().darCantidadProductos(100);
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
		        i+=3;
	        }
	        respuesta.write( "</table>" );
		}
		catch (Exception e1){
		}
	}
		
	public void error(PrintWriter respuesta){
		
		respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
	    respuesta.write( "<tr>" );
	    respuesta.write( "<td><h3>Oops! Hubo un error, lo sentimos. No se pudo eliminar.</h3></td>" );
	    respuesta.write( "</tr>" );
	    respuesta.write( "</table>" );
			
	}
}
