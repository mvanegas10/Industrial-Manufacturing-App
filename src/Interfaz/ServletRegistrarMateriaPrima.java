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
			ArrayList<MateriaPrima> materiasPrima = AplicacionWeb.darInstancia().darMateriasPrimas();
			hayMateriasPrima(respuesta, materiasPrima);
		}
		catch(Exception e)
		{
			noHayMateriasPrima (respuesta);
		}

	}
	
	public void hayMateriasPrima (PrintWriter respuesta, ArrayList<MateriaPrima> materias){
		
	}
	
	public void noHayMateriasPrima (PrintWriter respuesta){
        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
        respuesta.write( "<tr>" );
        respuesta.write( "<td><FONT face=\"arial\" size=5 color=#34495e>Error: No hay registrada ninguna materia prima, primero registre materias prima a ProdAndes</FONT></td>" );
        respuesta.write( "</tr>" );
        respuesta.write( "</table>" );
	}

}
