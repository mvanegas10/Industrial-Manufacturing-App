package Interfaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mundo.AplicacionWeb;
import mundo.Componente;
import mundo.MateriaPrima;

public class ServletRegistrarComponenteProveedor extends ServletAbstract {

	@Override
	public String darTituloPagina(HttpServletRequest request) {
		return "Registro de Componente Proveedor";
	}

	@Override
	public void escribirContenido(HttpServletRequest request,HttpServletResponse response) throws IOException {

		PrintWriter respuesta = response.getWriter( );
		
		String idProveedor = request.getParameter("idProveedor");
		String[] materiasPrimas = request.getParameterValues("materiasPrimas");		
		
		List<String[]> datosProveedorMateriaPrima = new ArrayList<String[]>();
		
		for (int i = 0; i < materiasPrimas.length; i++) {
			String[] datos = {idProveedor, materiasPrimas[i]};
			datosProveedorMateriaPrima.add(datos);
		}
		
		try
		{
			AplicacionWeb.getInstancia().registrarProveedorMateriaPrima(datosProveedorMateriaPrima);
			ArrayList<Componente> componentes = AplicacionWeb.getInstancia().darComponentes();
			hayComponentes(respuesta, componentes, idProveedor);
		}
		catch(Exception e)
		{
			noHayComponentes (respuesta);
		}

	}
	
	public void hayComponentes (PrintWriter respuesta, ArrayList<Componente> componentes, String idProveedor){
		respuesta.write( "<body bgcolor=\"#bdc3c7\">" );
		respuesta.write( "<form method=\"POST\" action=\"registroProveedor.htm\">" );
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
		respuesta.write( "<section align=\"center\" name=\"componentes\" class=\"container\">" );
		respuesta.write( " <div>" );
		respuesta.write( " <select id=\"leftValues\" size=\"5\" multiple></select>" );
		respuesta.write( " </div>" );
		respuesta.write( " <div>" );
		respuesta.write( " <input type=\"button\" id=\"btnLeft\" value=\"&lt;&lt;\" />" );
		respuesta.write( " <input type=\"button\" id=\"btnRight\" value=\"&gt;&gt;\" />" );
		respuesta.write( " </div>" );
		respuesta.write( " <div>" );
		respuesta.write( " <select id=\"rightValues\" size=\"4\" multiple>" );
		for (Componente componente : componentes) {
			respuesta.write( " <option>" + componente.getId() + "</option>" );	
		}
		respuesta.write( " </select>" );
		respuesta.write( " <div>" );
		respuesta.write( " <input type=\"text\" id=\"txtRight\" />" );
		respuesta.write( " </div>" );
		respuesta.write( " <p align=center> ");
		respuesta.write( " <input type=\"hidden\" id=\"idProveedor\" value=" + idProveedor + ">" );
		respuesta.write( " <input type=\"submit\" value=\"Registrar\" name=\"B1\" class=\"normal\"> ");
		respuesta.write( " <input type=\"reset\" value=\"Borrar\" name=\"B2\" class=\"normal\"></p> ");
		respuesta.write( " </section> ");
		respuesta.write( " </form>" );
		respuesta.write( " </body>" );
	}
	
	public void noHayComponentes (PrintWriter respuesta){
        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=80%>" );
        respuesta.write( "<tr>" );
        respuesta.write( "<td><h3>Error: No hay registrado ningun componente, primero registre componentes a ProdAndes</h3></td>" );
        respuesta.write( "</tr>" );
        respuesta.write( "</table>" );
	}

}
