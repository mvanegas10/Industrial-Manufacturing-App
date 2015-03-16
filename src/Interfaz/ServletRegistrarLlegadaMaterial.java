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
			
			respuesta.write( " <form method=\"POST\" action=\"registroLlegadaMaterial.htm\">" );
			respuesta.write( " <table align=\"center\" bgcolor=\"#ecf0f1\" width=\"80%\">" );
			respuesta.write( " <tr>" );
			
			if (llegadaMaterial.equals("materiaPrima"))
			{
				respuesta.write( " <td>Fecha a Registrar</td>" );
				respuesta.write( " <td>Nombre</td>" );
				respuesta.write( " <td>Unidad de Medida</td>" );
				respuesta.write( " <td>Cantidad Inicial</td>" );
				respuesta.write( " </tr>" );
				respuesta.write( " <tr>" );
				respuesta.write( " <input type=\"hidden\" id=\"date\" name=\"fecha\">" );
				respuesta.write( " <script>document.getElementById(\"date\").value = new Date().toJSON().slice(0,10)</script>" );
				respuesta.write( " <form method=\"POST\" action=\"registroMaterial.htm\">" );
				respuesta.write( " <td><input type=\"text\" name=\"nombre\" size=\"33\" class=\"normal\"></td>" );
				respuesta.write( " <td><input type=\"text\" name=\"unidadMedida\" size=\"33\" class=\"normal\"></td>" );				
				respuesta.write( " <td><input type=\"text\" name=\"cantInicial\" size=\"33\" class=\"normal\"></td>" );


			}
			else
			{
				respuesta.write( " <td>Fecha a Registrar</td>" );
				respuesta.write( " <td>Nombre</td>" );
				respuesta.write( " <td>Cantidad Inicial</td>" );
				respuesta.write( " </tr>" );
				respuesta.write( " <tr>" );
				respuesta.write( " <input type=\"hidden\" id=\"date\"/ name=\"fecha\"> " );
				respuesta.write( " <script>document.getElementById(\"date\").value = new Date().toJSON().slice(0,10)</script>" );
				respuesta.write( " <form method=\"POST\" action=\"registroMaterial.htm\">" );
				respuesta.write( " <td><input type=\"text\" name=\"nombre\" size=\"33\" class=\"normal\"></td>" );
				respuesta.write( " <td><input type=\"hidden\" name=\"unidadMedida\" size=\"33\" class=\"normal\"></td>" );	
				respuesta.write( " <td><input type=\"text\" name=\"cantInicial\" size=\"33\" class=\"normal\"></td>" );
			}
			
			respuesta.write( " </form>" );
			respuesta.write( " </tr>" );
			respuesta.write( " </table>" );
			respuesta.write( " <p align=center>" );
			respuesta.write( " <input type=\"submit\" value=\"Registrar\" name=\"B1\" class=\"normal\">" );
			respuesta.write( " <input type=\"reset\" value=\"Borrar\" name=\"B2\" class=\"normal\"></p>" );
			respuesta.write( " </form>" );
		
		}	
		catch (Exception e)
		{
			e.printStackTrace();
		}		
	}

}
