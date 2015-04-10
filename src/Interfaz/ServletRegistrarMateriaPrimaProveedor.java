package Interfaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mundo.AplicacionWeb;
import mundo.MateriaPrima;

public class ServletRegistrarMateriaPrimaProveedor extends ServletAbstract {

	@Override
	public String darTituloPagina(HttpServletRequest request) {
		return "Registro de Materia Prima Proveedor";
	}

	@Override
	public void escribirContenido(HttpServletRequest request,HttpServletResponse response) throws IOException {

		PrintWriter respuesta = response.getWriter( );
		String ciudad = request.getParameter("ciudad");
		String direccion = request.getParameter("direccion");
		int telefono = Integer.parseInt(request.getParameter("telefono"));
		String idRepLegal = request.getParameter("idRepLegal");
		String idProveedor = Integer.toString(AplicacionWeb.getInstancia().darContadorId());
		
		try
		{
			AplicacionWeb.getInstancia().registrarProveedor(idProveedor, direccion, telefono, ciudad, idRepLegal);
			ArrayList<MateriaPrima> materiasPrima = AplicacionWeb.getInstancia().darMateriasPrimas();
			hayMateriasPrima(respuesta, materiasPrima, idProveedor);
		}
		catch(Exception e)
		{
			noHayMateriasPrima (respuesta);
		}

	}
	
	public void hayMateriasPrima (PrintWriter respuesta, ArrayList<MateriaPrima> materias, String idProveedor){
		respuesta.write( "<body bgcolor=\"#bdc3c7\">" );
		respuesta.write( "<form method=\"POST\" action=\"registroComponenteProveedor.htm\">" );
		respuesta.write( "<style>" );
		respuesta.write( "SELECT, INPUT[type=\"text\"] {" );
		respuesta.write( "width: 160px;" );
		respuesta.write( "box-sizing: border-box;" );
		respuesta.write( "}" );
		respuesta.write( "SECTION {" );
		respuesta.write( "padding: 8px;" );
		respuesta.write( "background-color: #ecf0f1;" );
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
		respuesta.write( "<section name=\"materiasPrimas\" class=\"container\">" );
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
		respuesta.write( " </select>" );
		respuesta.write( " <div>" );
		respuesta.write( " <input type=\"text\" id=\"txtRight\" />" );
		respuesta.write( " </div>" );
		respuesta.write( " <p align=center> ");
		respuesta.write( " <input type=\"hidden\" value=" + idProveedor + " name=\"idProveedor\" class=\"normal\"> ");
		respuesta.write( " <input type=\"submit\" value=\"Registrar\" name=\"B1\" class=\"normal\"> ");
		respuesta.write( " <input type=\"reset\" value=\"Borrar\" name=\"B2\" class=\"normal\"></p> ");
		respuesta.write( " </section> ");
		respuesta.write( " </form>" );
		respuesta.write( " </body>" );
	}
	
	public void noHayMateriasPrima (PrintWriter respuesta){
        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
        respuesta.write( "<tr>" );
        respuesta.write( "<td><h3>Error: No hay registrada ninguna materia prima, primero registre materias prima a ProdAndes</h3></td>" );
        respuesta.write( "</tr>" );
        respuesta.write( "</table>" );
	}

}
