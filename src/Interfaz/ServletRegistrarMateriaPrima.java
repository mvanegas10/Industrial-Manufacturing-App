package Interfaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mundo.AplicacionWeb;
import mundo.MateriaPrima;

public class ServletRegistrarMateriaPrima extends ServletAbstract {

	@Override
	public String darTituloPagina(HttpServletRequest request) {
		return "Registro de Materia Prima";
	}

	@Override
	public void escribirContenido(HttpServletRequest request,HttpServletResponse response) throws IOException {

		PrintWriter respuesta = response.getWriter( );
		try
		{
			ArrayList<MateriaPrima> materiasPrima = AplicacionWeb.darInstancia().pruebita();
			hayMateriasPrima(respuesta, materiasPrima);
		}
		catch(Exception e)
		{
			noHayMateriasPrima (respuesta);
		}

	}
	
	public void hayMateriasPrima (PrintWriter respuesta, ArrayList<MateriaPrima> materias){
		respuesta.write( "<style>" );
		respuesta.write( "SELECT, INPUT[type=\"text\"] {" );
		respuesta.write( "width: 160px;" );
		respuesta.write( "box-sizing: border-box;" );
		respuesta.write( "}" );
		respuesta.write( "SECTION {" );
		respuesta.write( "padding: 8px;" );
		respuesta.write( "background-color: #f0f0f0;" );
		respuesta.write( "overflow: auto;" );
		respuesta.write( "}" );
		respuesta.write( "SECTION > DIV {" );
		respuesta.write( "float: left;" );
		respuesta.write( "padding: 4px;" );
		respuesta.write( "}" );
		respuesta.write( "SECTION > DIV + DIV {" );
		respuesta.write( "width: 40px;" );
		respuesta.write( "text-align: center;" );
		respuesta.write( "}</style>" );
		respuesta.write( "<section class=\"container\">" );
		respuesta.write( " <div>" );
		respuesta.write( " <select id=\"leftValues\" size=\"5\" multiple></select>" );
		respuesta.write( " </div>" );
		respuesta.write( " <div>" );
		respuesta.write( " <input type=\"button\" id=\"btnLeft\" value=\"&lt;&lt;\" />" );
		respuesta.write( " <input type=\"button\" id=\"btnRight\" value=\"&gt;&gt;\" />" );
		respuesta.write( " </div>" );
		respuesta.write( " <div>" );
		respuesta.write( " <select id=\"rightValues\" size=\"4\" multiple>" );
		for (MateriaPrima materiaPrima : materias) {
			respuesta.write( " <option>" + materiaPrima.getId() + "</option>" );	
		}
		respuesta.write( " <option>1</option>" );
		respuesta.write( " <option>2</option>" );
		respuesta.write( " <option>3</option>" );
		respuesta.write( " </select>" );
		respuesta.write( " <div>" );
		respuesta.write( " <input type=\"text\" id=\"txtRight\" />" );
		respuesta.write( " </div>" );
	}
	
	public void noHayMateriasPrima (PrintWriter respuesta){
        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
        respuesta.write( "<tr>" );
        respuesta.write( "<td><FONT face=\"arial\" size=5 color=#34495e>Error: No hay registrada ninguna materia prima, primero registre materias prima a ProdAndes</FONT></td>" );
        respuesta.write( "</tr>" );
        respuesta.write( "</table>" );
	}

}
