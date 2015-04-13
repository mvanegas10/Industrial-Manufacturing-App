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
				
			String llegadaMaterial = request.getParameter("tipo");
			
			respuesta.write( " <form method=\"POST\" action=\"registroMaterial.htm\">" );
			respuesta.write( " <table align=\"center\" bgcolor=\"#ecf0f1\" width=\"80%\">" );
			respuesta.write( " <tr>" );
			
			if (llegadaMaterial.equals("materiaPrima"))
			{
				respuesta.write( " <td><h3>Fecha</h3></td>" );
				respuesta.write( " <td><h3>Nombre</h3></td>" );
				respuesta.write( " <td><h3>Unidad de Medida</h3></td>" );
				respuesta.write( " <td><h3>Cantidad Inicial</h3></td>" );
				respuesta.write( " </tr>" );
				respuesta.write( " <tr>" );
				respuesta.write( " <td><h4><input size=\"23\" type=\"hidden\" id=\"date\"/ name=\"fecha\"> " );
				respuesta.write( " <script name=\"fecha\" language=\"javascript\">" );
				respuesta.write( " var today = new Date();" );
				respuesta.write( " document.write(today.toDateString());" );
				respuesta.write( " </script></h4></td>" );
				respuesta.write( " <form method=\"POST\" action=\"registroMaterial.htm\">" );
				respuesta.write( " <td><input type=\"text\" name=\"nombre\" size=\"23\" class=\"normal\"></td>" );
				respuesta.write( " <td><input type=\"text\" name=\"unidadMedida\" size=\"23\" class=\"normal\"></td>" );				
				respuesta.write( " <td><input type=\"text\" name=\"cantInicial\" size=\"23\" class=\"normal\"></td>" );


			}
			else
			{
				respuesta.write( " <td><h3>Fecha a Registrar</h3></td>" );
				respuesta.write( " <td><h3>Nombre</h3></td>" );
				respuesta.write( " <td><h3>Cantidad Inicial</h3></td>" );
				respuesta.write( " </tr>" );
				respuesta.write( " <tr>" );
				respuesta.write( " <td><h4><input size=\"23\" type=\"hidden\" id=\"date\"/ name=\"fecha\"> " );
				respuesta.write( " <script name=\"fecha\" language=\"javascript\">" );
				respuesta.write( " var today = new Date();" );
				respuesta.write( " document.write(today.toDateString());" );
				respuesta.write( " </script></h4></td>" );
				respuesta.write( " <form method=\"POST\" action=\"registroMaterial.htm\">" );
				respuesta.write( " <td><input type=\"text\" name=\"nombre\" size=\"23\" class=\"normal\"></td>" );		
				respuesta.write( " <td><input type=\"text\" name=\"cantInicial\" size=\"23\" class=\"normal\"></td>" );

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
