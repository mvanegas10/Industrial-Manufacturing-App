package Interfaz;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ServletAbstract extends HttpServlet{

    /**
     * Maneja un pedido GET de un cliente
     * @param request Pedido del cliente
     * @param response Respuesta
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        procesarPedido( request, response );
    }

    /**
     * Maneja un pedido POST de un cliente
     * @param request Pedido del cliente
     * @param response Respuesta
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        procesarPedido( request, response );
    }

    /**
     * Procesa el pedido de igual manera para todos
     * @param request Pedido del cliente
     * @param response Respuesta
     * @throws IOException Excepcion de error al escribir la respuesta
     */
    private void procesarPedido( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
        //
        // Comienza con el Header del template
        imprimirHeader( request, response );
        //
        // Escribe el contenido de la pagina
        escribirContenido( request, response );
        //
        // Termina con el footer del template
        imprimirFooter( response );

    }

    /**
     * Imprime el Header del disenio de la pagina
     * @param request Pedido del cliente
     * @param response Respuesta
     * @throws IOException Excepcion al imprimir en el resultado
     */
    private void imprimirHeader( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
        //
        // Saca el printer de la repuesta
        PrintWriter respuesta = response.getWriter( );
        //
        // Imprime el header
        respuesta.write( "<html>" );
        respuesta.write( "<head>" );
        respuesta.write( "<a href=\"index.htm\"><title>ProdAndes - Ingresar</title></a>" );
        respuesta.write( "<link rel=\"stylesheet\" href=\"bootstrap.min.css\">" );
        respuesta.write( "</head>" );
        respuesta.write( "<body bgcolor=\"#bdc3c7\">" );
        respuesta.write( "<form method=\"POST\" action=\"ingreso.htm\">" );
        respuesta.write( "<table bgcolor=\"#ecf0f1\" width=100% cellpadding=\"40\">" );
        respuesta.write( "<tr>" );
        respuesta.write( "<td><h1><a href=index.htm>ProdAndes</a></h1></td>" );
        respuesta.write( "<td><h2 style=\"text-align:right\">Ingreso de Usuarios</h2></td>" );
        respuesta.write( "</tr>" );
        respuesta.write( "</table>" );
        respuesta.write( "<hr>" );
    }

    /**
     * Imprime el Footer del disenio de la pagina
     * @param response Respuesta
     * @throws IOException Excepcion al escribir en la respuesta
     */
    private void imprimirFooter( HttpServletResponse response ) throws IOException
    {
        //
        // Saca el writer de la respuesta
        PrintWriter respuesta = response.getWriter( );
        //
        // Imprime el footer

        respuesta.write( "</body>" );
        respuesta.write( "</html>" );
       
    }

    /**
     * Imprime un mensaje de error
     * @param respuesta Respuesta al cliente
     * @param titulo Titulo del error
     * @param mensaje Mensaje del error
     */
    protected void imprimirMensajeError( PrintWriter respuesta, String titulo, String mensaje )
    {
        respuesta.write( "                      <p class=\"error\"><b>Ha ocurrido un error!:<br>\r\n" );
        respuesta.write( "                      </b>" + titulo + "</p><p>" + mensaje + ". </p>\r\n" );
        respuesta.write( "                      <p>Intente la \r\n" );
        respuesta.write( "                      operacion nuevamente. Si el problema persiste, contacte \r\n" );
        respuesta.write( "                      al administrador del sistema.</p>\r\n" );
        respuesta.write( "                      <p><a href=\"index.htm\">Volver a la pagina principal</a>\r\n" );
    }

    /**
     * Imprime un mensaje de error
     * @param respuesta Respuesta al cliente
     * @param titulo Titulo del error
     * @param exception Excepcion de error
     * @param mensaje Mensaje del error
     */
    protected void imprimirMensajeError( PrintWriter respuesta, String titulo, String mensaje, Exception exception )
    {
        respuesta.write( "                      <p class=\"error\"><b>Ha ocurrido un error!:<br>\r\n" );
        respuesta.write( "                      </b>" + titulo + "</p><p>" + mensaje + ". Mas Informacion:<br>" );
        exception.printStackTrace( respuesta );
        respuesta.write( "</p>\r\n" );
        respuesta.write( "                      <p>Intente la \r\n" );
        respuesta.write( "                      operacion nuevamente. Si el problema persiste, contacte \r\n" );
        respuesta.write( "                      al administrador del sistema.</p>\r\n" );
        respuesta.write( "                      <p><a href=\"index.htm\">Volver a la pagina principal</a>\r\n" );
    }

    /**
     * Imprime un mensaje de exito
     * @param respuesta Respuesta al cliente
     * @param titulo Titulo del mensaje
     * @param mensaje Contenido del mensaje
     */
    protected void imprimirMensajeOk( PrintWriter respuesta, String titulo, String mensaje )
    {
        respuesta.write( "                      <p class=\"ok\"><b>Operacion exitosa:<br>\r\n" );
        respuesta.write( "                      </b>" + titulo + "</p><p>" + mensaje + ". </p>\r\n" );
        respuesta.write( "                      <p><a href=\"index.htm\">Volver a la pagina principal</a>\r\n" );
    }

    /**
     * Devuelve el titulo de la pagina para el Header
     * @param request Pedido del cliente
     * @return Titulo de la pagina para el Header
     */
    public abstract String darTituloPagina( HttpServletRequest request );

    /**
     * Escribe el contenido de la pagina
     * @param request Pedido del cliente
     * @param response Respuesta
     * @throws IOException Excepcion de error al escribir la respuesta
     */
    public abstract void escribirContenido( HttpServletRequest request, HttpServletResponse response ) throws IOException;

}