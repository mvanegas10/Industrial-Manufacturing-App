package Interfaz;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mundo.AplicacionWeb;

public class ServletRegistrarLlegadaMaterial extends ServletAbstract{

	@Override
	public String darTituloPagina(HttpServletRequest request) {
		return "Registro Llegada Material";
	}

	@Override
	public void escribirContenido(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try
		
		{
			PrintWriter respuesta = response.getWriter( );
				
			String llegadaMaterial = request.getParameter("llegadaMaterial");
			
			if (llegadaMaterial.equals("materiaPrima"))
			{
				respuesta.write( " <form method=\"POST\" action=\"registroLlegadaMaterial.htm\">" );
				respuesta.write( " <table align= center bgcolor=\"#ecf0f1\" width=\"80%\">" );
				respuesta.write( " <tr>" );
				respuesta.write( " <td>Nombre</td>" );
				respuesta.write( " <td>Unidad de Medida</td>" );
				respuesta.write( " <td>Cantidad Inicial</td>" );
				respuesta.write( " </tr>" );
				respuesta.write( " <tr>" );
				respuesta.write( " <form method=\"POST\" action=\"registroMateriaPrimaProveedor.htm\">" );
				respuesta.write( " <td><input type=\"text\" name=\"nombre\" size=\"33\" class=\"normal\"></td>" );
				respuesta.write( " <td><input type=\"text\" name=\"unidadMedida\" size=\"33\" class=\"normal\"></td>" );				
				respuesta.write( " <td><input type=\"text\" name=\"cantInicial\" size=\"33\" class=\"normal\"></td>" );
				respuesta.write( " </form>" );
				respuesta.write( " </tr>" );
				respuesta.write( " </table>" );
				respuesta.write( " <p align=center>" );
				respuesta.write( " <input type=\"submit\" value=\"Registrar\" name=\"B1\" class=\"normal\">" );
				respuesta.write( " <input type=\"reset\" value=\"Borrar\" name=\"B2\" class=\"normal\"></p>" );
				respuesta.write( " </form>" );
			}
			else
			{
				
			}
		
		}	
		catch (Exception e)
		{
			e.printStackTrace();
		}		
	}

}
