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
     * @param request Pedido del cliente   hhh
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
        respuesta.write( " <form id=\"ddlogin\" action=\"/login.forum\" method=\"post\">" );
        respuesta.write( " <fieldset>" );
        respuesta.write( " <dl>" );
        respuesta.write( " <dt><label for=\"username\">Username:</label></dt>" );
        respuesta.write( " <dd><input type=\"text\" tabindex=\"1\" name=\"username\" id=\"username\" size=\"20\" maxlength=\"40\" value=\"\" class=\"inputbox\" /></dd>" );
        respuesta.write( " </dl>" );
        respuesta.write( " <dl>" );
        respuesta.write( " <dt><label for=\"password\">Password:</label></dt>" );
        respuesta.write( " <dd><input type=\"password\" tabindex=\"2\" id=\"password\" name=\"password\" size=\"20\" maxlength=\"25\" class=\"inputbox\" /></dd>" );
        respuesta.write( " </dl>" );
        respuesta.write( " <dl>" );
        respuesta.write( " <dd><label for=\"autologin\"><input type=\"checkbox\" name=\"autologin\" id=\"autologin\" tabindex=\"4\" class=\"checkbox\" checked=\"checked\" /> Remember me</label></dd>" );
        respuesta.write( " </dl>" );
        respuesta.write( " <dl>" );
        respuesta.write( " <dd><input type=\"submit\" name=\"login\" tabindex=\"6\" value=\"Log In\" class=\"button2\" /></dd>" );
        respuesta.write( " </dl>" );
        respuesta.write( " </fieldset>" );
        respuesta.write( " </form>" );
        respuesta.write( "</head>" );
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
     * Devuelve el nombre de la imagen para el titulo de la pagina en el Header
     * @param request Pedido del cliente
     * @return Nombre de la imagen para el titulo de la pagina en el Header
     */
    public abstract String darImagenTitulo( HttpServletRequest request );

    /**
     * Escribe el contenido de la pagina
     * @param request Pedido del cliente
     * @param response Respuesta
     * @throws IOException Excepcion de error al escribir la respuesta
     */
    public abstract void escribirContenido( HttpServletRequest request, HttpServletResponse response ) throws IOException;

}