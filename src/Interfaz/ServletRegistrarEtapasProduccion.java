package Interfaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mundo.AplicacionWeb;
import mundo.Componente;
import mundo.Estacion;
import mundo.MateriaPrima;

public class ServletRegistrarEtapasProduccion extends ServletAbstract{

	@Override
	public String darTituloPagina(HttpServletRequest request) {
		return "Registrar Etapa de Produccion";
	}

	@Override
	public void escribirContenido(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter respuesta = response.getWriter( );
		
		String idProducto = request.getParameter("idProducto");
		
		String idAnterior = request.getParameter("idAnterior");
		
		String nombre = request.getParameter("nombre");
		
		int numeroSecuencia = Integer.parseInt(request.getParameter("numeroSecuencia"));
		
		int duracion = Integer.parseInt(request.getParameter("duracion"));
		
		String idEstacion = request.getParameter("estacion");
		
		String idMateriaPrima = request.getParameter("materiaPrima");
		
		String idComponente = request.getParameter("componente");
		
		String criterio = request.getParameter("criterio");
		
		String idActual;
		
		ArrayList<Estacion> estaciones = new ArrayList<Estacion>();
		
		ArrayList<MateriaPrima> materiasPrimas = new ArrayList<MateriaPrima>();
		
		ArrayList<Componente> componentes = new ArrayList<Componente>();
		
		try
		{
			AplicacionWeb.getInstancia().registrarEtapaProduccion(idAnterior, idProducto, idEstacion, idMateriaPrima, idComponente, duracion, numeroSecuencia, idAnterior);
			if (criterio.equals("continuar"))
			{				
				estaciones = AplicacionWeb.getInstancia().darEstaciones();
				materiasPrimas = AplicacionWeb.getInstancia().darMateriasPrimas();
				componentes = AplicacionWeb.getInstancia().darComponentes();
				idActual = Integer.toString(AplicacionWeb.getInstancia().darContadorId());
				respuesta.write( "<form method=\"POST\" action=\"registroEtapasProduccion.htm\"><input type=\"hidden\" value=" + idProducto + " name=\"idProducto\"><input type=\"hidden\" value=" + idActual + " name=\"idAnterior\">" );
				respuesta.write( "<table align= center bgcolor=\"#ecf0f1\" width=\"45%\">" );
				respuesta.write( "<tr>" );
				respuesta.write( "<td><h4>Nombre Etapa (descripcion): </h4></td>" );
				respuesta.write( "<td><input type=\"text\" name=\"nombre\" size=\"23\" class=\"normal\"></td>" );
				respuesta.write( "</tr>" );
				respuesta.write( "<tr>" );
				respuesta.write( "<td><h4>Numero de Secuencia:</h4></td>" );
				respuesta.write( "<td><input type=\"text\" name=\"numeroSecuencia\" size=\"23\" class=\"normal\" style=\"background: #FFFFFF; border: none;\" value=" + (numeroSecuencia + 1) + " disabled=\"disabled\" ></td>" );
				respuesta.write( "</tr>" );
				respuesta.write( "<tr></tr>" );
				respuesta.write( "<tr>" );
				respuesta.write( "<td><h4>Duracion (en dias):</h4></td>" );
				respuesta.write( "<td><input type=\"text\" name=\"duracion\" size=\"23\" class=\"normal\"></td>" );
				respuesta.write( "</tr>" );
				respuesta.write( "<tr></tr>" );
				respuesta.write( "<tr>" );
				respuesta.write( "<td><h4 align=\"left\">Estacion:</h4></td>" );
				respuesta.write( "<td width=\"100\"><select size=\"1\" name=\"estacion\" style=\"width: 207px\" class=\"normal\">" );
				for (Estacion estacion : estaciones) {
					respuesta.write( "<option value=" + estacion.getId() + ">" + estacion.getId() + "</option>" );
				}
				respuesta.write( "</select>" );
				respuesta.write( "</td>" );
				respuesta.write( "</tr>" );
				respuesta.write( "<tr></tr>" );
				respuesta.write( "<tr>" );
				respuesta.write( "<td><h4 align=\"left\">Materia Prima:</h4></td>" );
				respuesta.write( "<td width=\"100\"><select size=\"1\" name=\"materiaPrima\" style=\"width: 207px\" class=\"normal\">" );
				for (MateriaPrima materiaPrima : materiasPrimas) {
					respuesta.write( "<option value=" + materiaPrima.getId() + ">" + materiaPrima.getId() + "</option>" );
				}
				respuesta.write( "</select>" );
				respuesta.write( "</td>" );
				respuesta.write( "</tr>" );
				respuesta.write( "<tr></tr>" );
				respuesta.write( "<tr>" );
				respuesta.write( "<td><h4 align=\"left\">Componente:</h4></td>" );
				respuesta.write( "<td width=\"100\"><select size=\"1\" name=\"componente\" style=\"width: 207px\" class=\"normal\">" );
				for (Componente componente : componentes) {
					respuesta.write( "<option value=" + componente.getId() + ">" + componente.getId() + "</option>" );
				}
				respuesta.write( "</select>" );
				respuesta.write( "</td>" );
				respuesta.write( "</tr>" );
				respuesta.write( "<tr></tr>" );
				respuesta.write( "</table>" );
				respuesta.write( "<h4 align=\"center\"><input type=\"hidden\" value=\"continuar\" name=\"criterio\" ><input type=\"submit\" value=\"Registrar Siguiente Etapa de Produccion\" size=\"33\" name=\"registrarEP\" class=\"normal\" style=\"background: #FFFFFF; border: none;\"></h4>" );
				respuesta.write( "<h4 align=\"center\"><input type=\"hidden\" value=\"finalizar\" name=\"criterio\" ><input type=\"submit\" value=\"Finalizar Registro Etapas de Produccion\" size=\"33\" name=\"registrarEP\" class=\"normal\" style=\"background: #FFFFFF; border: none;\"></form></h4>" );
				respuesta.write( "<p align=center>" );
				respuesta.write( "<input type=\"reset\" value=\"Borrar\" name=\"B2\" class=\"normal\"></p>" );
			}
			else
			{
				respuesta.write( "<table align=\"center\" bgcolor=\"#ecf0f1\" width=80%>" );
		        respuesta.write( "<tr>" );
		        respuesta.write( "<td><h3>El producto y las etapas de produccion se han registrado correctamente.</h3></td>" );
		        respuesta.write( "</tr>" );
		        respuesta.write( "<tr>" );
		        respuesta.write( "<td><form method=\"POST\" action=\"ingreso.htm\"><input type=\"hidden\" value=\"reingreso\" name=\"reingreso\"><input type=\"submit\" value=\"Volver\" size=\"33\" name=\"reingreso\" class=\"normal\"></form></td>" );
		        respuesta.write( "</tr>" );
		        respuesta.write( "</table>" );
			}
		}
		catch (Exception e){
			respuesta.write( "<table align=\"center\" bgcolor=\"#ecf0f1\" width=80%>" );
	        respuesta.write( "<tr>" );
	        respuesta.write( "<td><h3>Debe ingresar las especificaciones solicitadas para continuar registrando etapas de produccion.</h3></td>" );
	        respuesta.write( "</tr>" );
	        respuesta.write( "</table>" );
		}
	}
}
