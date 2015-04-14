package Interfaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mundo.AplicacionWeb;
import mundo.Pedido;
import mundo.Producto;

public class ServletResultadoBusqueda extends ServletAbstract{

	@Override
	public String darTituloPagina(HttpServletRequest request) {
		return "Resultado de Busqueda";
	}

	@Override
	public void escribirContenido(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter respuesta = response.getWriter( );
		
		String criterio = request.getParameter("criterio");
				
		if (criterio.equals("buscarProductoCliente"))
		{
			String idProducto = request.getParameter("idProducto");
			String nombreProducto = request.getParameter("nombre");
			ArrayList<Producto> rta = new ArrayList<Producto>();
			if (idProducto == null)
			{
				idProducto = request.getParameter("producto");
			}
			if(idProducto == null)
			{
				try
				{
					rta = AplicacionWeb.getInstancia().buscarProducto(nombreProducto);
				}
				catch (Exception e){
					error(respuesta);
				}
			}
			if (rta.isEmpty())
			{
				try
				{
					rta = AplicacionWeb.getInstancia().darProducto(idProducto);
				}
				catch (Exception e){
					error(respuesta);
				}
			}
			
			String login = request.getParameter("login");
			
			respuesta.write( "<table align=\"center\" bgcolor=\"#ecf0f1\" width=50%>" );
			for (int i = 0; i < rta.size(); i++) {
				respuesta.write( "<form method=\"POST\" action=\"registroPedido.htm\">" );
				respuesta.write( "<tr>" );
	        	respuesta.write( "<tr><td><img alt=\"Producto\" src=\"imagenes/producto.jpg\" type=\"image\"></td>" );
	        	respuesta.write( "<td><table align=\"center\" bgcolor=\"#ecf0f1\" width=10%>" );
		        respuesta.write( "<tr><td align=\"left\"><h4><input value=\"Producto: \" name=\"label1\" style=\"border: none;\" type=\"text\"\"></h4></td><input value=" + rta.get(i).getId() + " name=\"idProducto\" type=\"hidden\"><td align=\"right\">" + rta.get(i).getNombre() + "</td></tr>" );
		        respuesta.write( "<tr><td align=\"left\"><h4><input value=\"Precio: \" name=\"label2\" style=\"border: none;\" type=\"text\"\"></h4></td><input value=" + rta.get(i).getNombre() + " name=\"nombre\" type=\"hidden\"><td align=\"right\">" + rta.get(i).getPrecio() + "</td></tr>" );
		        respuesta.write( "<tr><td><table bgcolor=\"#ecf0f1\" width=10%>" );
		        respuesta.write( "<tr><td align=\"left\"><h4>Unidades: </h4></td>" );
		        respuesta.write( "<td align=\"right\">" );
		        respuesta.write( "<select size=\"1\" name=\"cantidad\" class=\"normal\" style=\"border: none;\">" );
		        respuesta.write( "<option value=\"1\">1</option>" );
		        respuesta.write( "<option value=\"2\">2</option>" );
		        respuesta.write( "<option value=\"3\">3</option>" );
		        respuesta.write( "</select>" );
		        respuesta.write( "</td></tr>" );
		        respuesta.write( "</table></td></tr>" );
		        respuesta.write( "<tr><td align=\"right\"><input value=\"Pedir\" size=\"33\" name=\"pedir\" type=\"submit\"\"></td></tr>" );
		        respuesta.write( "<input type=\"hidden\" name=\"login\" value=" + login + ">" );
		        respuesta.write( "<input type=\"hidden\" name=\"precio\" value=" + rta.get(i).getPrecio() + ">" );
		        respuesta.write( "</table></td>" );
		        respuesta.write( "</tr>" );
		        respuesta.write( "</form>" );
	        }
			respuesta.write( "</table>" );
		}
		
		else if (criterio.equals("darPedidos"))
		{
			String login = request.getParameter("login");
			
			ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
	        try 
	        {
	        	pedidos = AplicacionWeb.getInstancia().darPedidosCliente(login);
	        	if (pedidos.size() != 0)
		        {
	        		respuesta.write( "<h4 align=\"center\">Tienes registrados " + pedidos.size() + " pedidos en total:</h4>" );
	        		respuesta.write( "<table align=\"center\" bgcolor=\"#ecf0f1\" width=50%>" );
			        for (Pedido ped : pedidos) {
			        	String producto = AplicacionWeb.getInstancia().darNombreProducto(ped.getProducto());
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
		
		else if (criterio.equals("darClientes"))
		{
			
		}
		
		else if (criterio.equals("darPedidos"))
		{
			
		}
		
		else if(criterio.equals("eliminarPedido"))
		{
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
			        	String producto = AplicacionWeb.getInstancia().darNombreProducto(ped.getProducto());
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
		
		else if (criterio.equals("buscarPedido"))
		{
			boolean pedido1 = false;
			boolean entrega1 = false;
			Date diaSolicitud = new Date();
			Date diaEntrega = new Date();
			try
			{
				int dia1 = Integer.parseInt(request.getParameter("dia1"));
				int mes1 = Integer.parseInt(request.getParameter("mes1"));
				int anio1 = Integer.parseInt(request.getParameter("anio1"));
				diaSolicitud = new Date();
				diaSolicitud.setDate(dia1);
				diaSolicitud.setMonth(mes1);
				diaSolicitud.setYear(anio1);
				pedido1 = true;
			}
			catch(Exception e){
			}
			try{
				int dia2 = Integer.parseInt(request.getParameter("dia2"));
				int mes2 = Integer.parseInt(request.getParameter("mes2"));
				int anio2 = Integer.parseInt(request.getParameter("anio2"));
				diaEntrega = new Date();
				diaEntrega.setDate(dia2);
				diaEntrega.setMonth(mes2);
				diaEntrega.setYear(anio2);
				entrega1 = true;
			}
			catch(Exception e){
				
			}
			try
			{
				ArrayList<Pedido> pedidos = AplicacionWeb.getInstancia().buscarPedidos(diaSolicitud, pedido1, diaEntrega, entrega1);
		        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
		        respuesta.write( "<tr>" );
		        respuesta.write( "<td><h3>Los pedidos encontrados son los siguientes:</h3></td>" );
		        respuesta.write( "</tr>" );
		        respuesta.write( "</table>" );
		        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
		        respuesta.write( "<tr>" );
		        respuesta.write( "<td>Producto</td>" );
		        respuesta.write( "<td>Cantidad</td>" );
		        respuesta.write( "<td>Fecha Pedido</td>" );
		        respuesta.write( "<td>Fecha Entrega</td>" );
		        respuesta.write( "<td>Acciones</td>" );
		        respuesta.write( "</tr>" );
		        for (Pedido pedido : pedidos) {
			        respuesta.write( "<tr>" );
			        respuesta.write( "<td>" + pedido.getProducto() + "</td>" );
			        respuesta.write( "<td>" + pedido.getCantidad() + "</td>" );
			        respuesta.write( "<td>" + pedido.getFechaEntrega() + "</td>" );
			        respuesta.write( "<td>" + pedido.getFechaPedido() + "</td>" );
			        respuesta.write( "<form method=\"POST\" action=\"eliminarPedido.htm\"><td><input type=\"submit\" name=\"eliminar\" value=\"Eliminar Pedido\"><input type=\"hidden\" name=\"pedidoEliminado\" value=" + pedido.getProducto() + "></td></form>" );
			        respuesta.write( "</tr>" );
				}
		        respuesta.write( "</table>" );
			}
			catch (Exception e){
		        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
		        respuesta.write( "<tr>" );
		        respuesta.write( "<td><h3>El pedido buscado no existe</h3></td>" );
		        respuesta.write( "</tr>" );
		        respuesta.write( "</table>" );
			}
		}
		else if (criterio.equals("buscarAdmin"))
		{
			String materialABuscar = request.getParameter("material");
			boolean tipo = false;
			boolean rango = false;
			boolean etapa = false;
			String nombreMaterial = "";
			int menorA = 0;
			int mayorA = 0;
			String etapaProd = "";
			ArrayList<String> rta = new ArrayList<String>();
			
			try
			{
				nombreMaterial = request.getParameter("nombreMaterial");
				tipo = true;
			}
			catch (Exception e){
				
			}
			try{
				mayorA = Integer.parseInt(request.getParameter("mayorA"));
				menorA = Integer.parseInt(request.getParameter("menorA"));
				rango = true;
			}
			catch (Exception e){
				
			}
			try{
				etapaProd = request.getParameter("etapaProd");
				etapa = true;
			}
			catch (Exception e){
				
			}
			if (materialABuscar.equals("producto")){
				try{
					rta = AplicacionWeb.getInstancia().buscarProducto(tipo, nombreMaterial, rango, mayorA, menorA, etapa);	
				}
				catch (Exception e){
			        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
			        respuesta.write( "<tr>" );
			        respuesta.write( "<td><h3>El producto buscado no existe</h3></td>" );
			        respuesta.write( "</tr>" );
			        respuesta.write( "</table>" );
				}
			}
			else if (materialABuscar.equals("materiaPrima")){
				try{
					rta = AplicacionWeb.getInstancia().buscarMateriaPrima(tipo, nombreMaterial, rango, mayorA, menorA);	
				}
				catch (Exception e){
			        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
			        respuesta.write( "<tr>" );
			        respuesta.write( "<td>Nombre " + materialABuscar + "</td>" );
			        respuesta.write( "<td>Cantidad en inventario</td>" );
			        respuesta.write( "</tr>" );
			        for (String string : rta) {
				        respuesta.write( "<tr>" );
				        respuesta.write( "<td>" + nombreMaterial + "</td>" );
				        respuesta.write( "<td>" + string + "</td>" );
				        respuesta.write( "</tr>" );

					}
			        respuesta.write( "</table>" );
				}
			}
			else if (materialABuscar.equals("componente")){
				try{
					rta = AplicacionWeb.getInstancia().buscarComponente(tipo, nombreMaterial, rango, mayorA, menorA);	
				}
				catch (Exception e){
			        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
			        respuesta.write( "<tr>" );
			        respuesta.write( "<td><h3>El producto buscado no existe</h3></td>" );
			        respuesta.write( "</tr>" );
			        respuesta.write( "</table>" );
				}
			}
			
	        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
	        respuesta.write( "<tr>" );
	        respuesta.write( "<td><h3>El producto buscado no existe</h3></td>" );
	        respuesta.write( "</tr>" );
	        respuesta.write( "</table>" );
		}
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
		        i+=4;
	        }
	        respuesta.write( "</table>" );
		}
		catch (Exception e1){
		}
	}

	public void error(PrintWriter respuesta){
		
		respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
	    respuesta.write( "<tr>" );
	    respuesta.write( "<td><h3>Oops! Hubo un error, lo sentimos, vuelve a intentarlo nuevamente.</FONT></td>" );
	    respuesta.write( "</tr>" );
	    respuesta.write( "</table>" );
			
	}
}
