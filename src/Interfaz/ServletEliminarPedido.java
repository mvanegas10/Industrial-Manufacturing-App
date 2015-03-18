package Interfaz;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mundo.AplicacionWeb;

public class ServletEliminarPedido extends ServletAbstract {

	@Override
	public String darTituloPagina(HttpServletRequest request) {
		return "Anulación de pedido";
	}

	@Override
	public void escribirContenido(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter respuesta = response.getWriter( );
		
		String producto = request.getParameter("pedidoEliminado");
		
		try
		{
			AplicacionWeb.getInstancia().
		}
	}

}
