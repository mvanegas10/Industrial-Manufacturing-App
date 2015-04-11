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
			String producto = request.getParameter("nombre");
			if (producto == null)
			{
				producto = request.getParameter("producto");
			}
			String login = request.getParameter("login");
			ArrayList<String> rta = new ArrayList<String>();
			try
			{
				rta = AplicacionWeb.getInstancia().buscarProducto(producto);
			}
			catch (Exception e){
				error(respuesta);
			}
			
			respuesta.write( "<table align=\"center\" bgcolor=\"#ecf0f1\" width=30%>" );
			for (int i = 0; i < rta.size(); i++) {
				respuesta.write( "<form method=\"POST\" action=\"registroPedido.htm\">" );
				respuesta.write( "<tr>" );
	        	respuesta.write( "<tr><td><input alt=\"Producto\" src=\"imagenes/producto.jpg\" type=\"image\" name=\"producto\"></td>" );
	        	respuesta.write( "<td><table align=\"center\" bgcolor=\"#ecf0f1\" width=10%>" );
		        respuesta.write( "<tr><td align=\"left\"><h4><input value=\"Producto: \" name=\"producto\" style=\"border: none;\" type=\"text\"\"></h4></td><td align=\"right\">" + producto + "</td></tr>" );
		        respuesta.write( "<tr><td align=\"left\"><h4><input value=\"Precio: \" name=\"precio\" style=\"border: none;\" type=\"text\"\"></h4></td><td align=\"right\">" + rta.get(i) + "</td></tr>" );
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
		        respuesta.write( "</table></td>" );
		        respuesta.write( "</tr>" );
		        respuesta.write( "</form>" );
	        }
			respuesta.write( "</table>" );
		}
		
		else if (criterio.equals("darPedidos"))
		{
			
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
				ArrayList<Pedido> pedidos = AplicacionWeb.getInstancia().buscarPedidosCliente(diaSolicitud, pedido1, diaEntrega, entrega1);
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
					rta = AplicacionWeb.getInstancia().buscarExistenciasProducto(tipo, nombreMaterial, rango, mayorA, menorA, etapa);	
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
					rta = AplicacionWeb.getInstancia().buscarExistenciasMateriaPrima(tipo, nombreMaterial, rango, mayorA, menorA);	
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
					rta = AplicacionWeb.getInstancia().buscarExistenciasComponente(tipo, nombreMaterial, rango, mayorA, menorA);	
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

		public void error(PrintWriter respuesta){
			
	        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
	        respuesta.write( "<tr>" );
	        respuesta.write( "<td><h3>Oops! Hubo un error, lo sentimos, vuelve a intentarlo nuevamente.</FONT></td>" );
	        respuesta.write( "</tr>" );
	        respuesta.write( "</table>" );
			
		}
}
