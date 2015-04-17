package Interfaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mundo.AplicacionWeb;
import mundo.Componente;
import mundo.MateriaPrima;

public class ServletRegistrarMaterial extends ServletAbstract{

	@Override
	public String darTituloPagina(HttpServletRequest request) {
		return "Registro Material";
	}

	@Override
	public void escribirContenido(HttpServletRequest request, HttpServletResponse response) throws IOException {

		PrintWriter respuesta = response.getWriter( );
		
		String nombre = request.getParameter("nombre");
		String unidadMedida = request.getParameter("unidadMedida");
		int cantInicial = Integer.parseInt(request.getParameter("cantInicial"));
		String idProveedor = request.getParameter("idProveedor");
		String fecha = "" + request.getParameter("fecha");
		String tipo;
		
		try
		{			
			if (!unidadMedida.equals(""))
			{
				tipo = "Materia Prima";
				AplicacionWeb.getInstancia().registrarMateriaPrima(nombre, unidadMedida, cantInicial);
			}
			else{
				tipo = "Componente";
				AplicacionWeb.getInstancia().registrarComponente(nombre, cantInicial);
			}
			respuesta.write( " <table align=\"center\" bgcolor=\"#ecf0f1\" width=\"50%\">" );
			respuesta.write( " <tr>" );
			respuesta.write( " <td><h3>Se agrego correctamente el material: " + tipo + " - " + nombre + "</h3></td>" );
			respuesta.write( " <tr>" );
			respuesta.write( " </table>" );
		}
		catch (Exception e){
			respuesta.write( " <table align=\"center\" bgcolor=\"#ecf0f1\" width=\"50%\">" );
			respuesta.write( " <tr>" );
			respuesta.write( " <td><h3>No se agrego correctamente el material</h3></td>" );
			respuesta.write( " <tr>" );
			respuesta.write( " </table>" );
		}
	}

}
