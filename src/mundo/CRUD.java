package mundo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CRUD {

	private Connection conexion;
	
	public CRUD (ConexionDAO dao){
		conexion = dao.darConexion();
	}
	
	/**
	 * @param tabla
	 * @param columnas
	 * @param tipos
	 * @param datos
	 * @throws Exception
	 */
	public void insertarTupla (String tabla, String[] columnas, String[] tipos,String[] datos) throws Exception{
		if (columnas.length == datos.length)
		{   
		    String atributos ="(" + columnas[0];
			String values ="(?";
		    for(int i=1; i< columnas.length;i++){
		    	atributos += (","+columnas[i]);
		    	values+=",?";
		    	if(i+1==columnas.length){
		    		values+=")";
		    		atributos+=")";
		    	}
		    }
			String sql = "INSERT INTO " + tabla + " " + atributos + " VALUES " + values;
			
			PreparedStatement statement = conexion.prepareStatement(sql); 
			for(int i =0; i<columnas.length; i++){
				if(tipos[i]=="String"){
					statement.setString(i+1, datos[i]);
				}
				if(tipos[i]=="int"){
					statement.setBoolean(i+1, Boolean.parseBoolean(datos[i]));
				}
				if(tipos[i]=="boolean"){
					statement.setInt(i+1, Integer.parseInt(datos[i]));
				}
			}
			
			System.out.println(sql);
			
			int filasInsertadas = statement.executeUpdate();
			
			if (filasInsertadas > 0) {
			    System.out.println("Nuevo registro fue ingresado con exito");
			}
		}	
		
	}
	
	public void actualizarTupla (String tabla, String[] columnas,String[] datos) throws Exception{
		if (columnas.length == datos.length)
		{
			String consulta = columnas[0] + " = " + datos[0];
			for (int i = 1; i < datos.length; i++) {
				consulta += ", " + columnas[i] + " = " + datos[i];
			}
			try 
			{
				Statement s = conexion.createStatement();
				s.executeQuery ("UPDATE " + tabla + " SET " + consulta + " ");
				System.out.println("Se actualiz� la tabla " + tabla);
				s.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		else 
			throw new Exception("La cantidad de columnas en la tabla " + tabla + " debe ser igual a la cantidad de datos a actualizar.");
	}
	
	public ArrayList<String> darTuplas (String tabla){
		ArrayList<String> resultado = new ArrayList<String>();
		try
		{
			Statement s = conexion.createStatement();
			ResultSet rS = s.executeQuery ("SELECT * FROM " + tabla);
			int i=1;
			while (rS.next()){
				resultado.add(rS.getString(i));
				i++;
			}
			System.out.println(resultado);
			s.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return resultado;
	}
	
	public ArrayList<String> darSubTabla (String tabla, String listaColumnas, String condicion){
		ArrayList<String> resultado = new ArrayList<String>();
		try
		{
			Statement s = conexion.createStatement();
			ResultSet rS = s.executeQuery ("SELECT " + listaColumnas + " FROM " + tabla + " WHERE " + condicion);
			int i=1;
			while (rS.next()){
				resultado.add(rS.getString(i));
				i++;
			}
			System.out.println(resultado);
			s.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return resultado;
	}
}
