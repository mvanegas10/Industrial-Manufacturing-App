package mundo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
				if(tipos[i].equals("String")){
					statement.setString(i+1, datos[i]);
					System.out.println(i+1 + datos[i]);
				}
				if(tipos[i].equals("int")){
					statement.setInt(i+1, Integer.parseInt(datos[i]));
					System.out.println(i+1 + datos[i]);
				}
				if(tipos[i].equals("Date")){
					statement.setDate(0, Date.valueOf(datos[i]));
					System.out.println(i+1 + datos[i]);
				}
				
			}
			
			System.out.println(sql);
			
			int filasInsertadas = statement.executeUpdate();
			
			if (filasInsertadas > 0) {
			    System.out.println("Nuevo registro fue ingresado con exito a la tabla " + tabla);
			}
		}	
		
	}
	
	public void actualizarTupla (String tabla, String[] columnas,String[] datos, String condicion) throws Exception{
		if (columnas.length == datos.length)
		{
			String consulta = columnas[0] + " = " + datos[0];
			for (int i = 1; i < datos.length; i++) {
				consulta += ", " + columnas[i] + " = " + datos[i];
			}
			try 
			{
				Statement s = conexion.createStatement();
				s.executeQuery ("UPDATE " + tabla + " SET " + consulta + " WHERE " + condicion);
				System.out.println("Se actualiz� la tabla " + tabla);
				s.close();
			} 
			catch (SQLException e) 
			{
				throw new Exception("La tupla con ese id no existe.");
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
			ResultSet rS = s.executeQuery ("SELECT id FROM " + tabla);
			while (rS.next()){
				resultado.add(rS.getString(1));
			}
			s.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return resultado;
	}
	
	public ArrayList<String> darSubTabla (String tabla, String listaColumnas, String condicion) throws Exception{
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
			throw new Exception("No se encntr� el registro en la tabla " + tabla);
		}
		return resultado;
	}
	
	public void poblarTablas(){
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader("data//datosTablas//clientes.csv"));
			String linea = null;
			while((linea = reader.readLine())!=null){
				String[] lineaInsertar = linea.split(",");
				insertarTupla(Cliente.NOMBRE, Cliente.COLUMNAS, Cliente.TIPO,lineaInsertar);
			}
			reader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader("data//datosTablas//componentes.csv"));
			String linea = null;
			while((linea = reader.readLine())!=null){
				String[] lineaInsertar = linea.split(",");
				insertarTupla(Componente.NOMBRE, Componente.COLUMNAS, Componente.TIPO,lineaInsertar);
			}
			reader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader("data//datosTablas//materiasPrimas.csv"));
			String linea = null;
			while((linea = reader.readLine())!=null){
				String[] lineaInsertar = linea.split(",");
				insertarTupla(MateriaPrima.NOMBRE, MateriaPrima.COLUMNAS, MateriaPrima.TIPO,lineaInsertar);
			}
			reader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader("data//datosTablas//productos.csv"));
			String linea = null;
			while((linea = reader.readLine())!=null){
				String[] lineaInsertar = linea.split(",");
				insertarTupla(Producto.NOMBRE, Producto.COLUMNAS, Producto.TIPO,lineaInsertar);
			}
			reader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader("data//datosTablas//productosEtapasProduccion.csv"));
			String linea = null;
			while((linea = reader.readLine())!=null){
				String[] lineaInsertar = linea.split(",");
				insertarTupla(Producto.NOMBRE_RELACION_ETAPA_PRODUCCION, Producto.COLUMNA_RELACION_ETAPA_PRODUCCION, Producto.TIPO_RELACION_ETAPA_PRODUCCION,lineaInsertar);
			}
			reader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader("data//datosTablas//proveedores.csv"));
			String linea = null;
			while((linea = reader.readLine())!=null){
				String[] lineaInsertar = linea.split(",");
				insertarTupla(Proveedor.NOMBRE, Proveedor.COLUMNAS, Proveedor.TIPO,lineaInsertar);
			}
			reader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader("data//datosTablas//proveedoresComponentes.csv"));
			String linea = null;
			while((linea = reader.readLine())!=null){
				String[] lineaInsertar = linea.split(",");
				insertarTupla(Proveedor.NOMBRERELACIONCOMPONENTE, Proveedor.COLUMNASRELACIONCOMPONENTE, Proveedor.TIPORELACIONCOMPONENTE,lineaInsertar);
			}
			reader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader("data//datosTablas//proveedoresMateriasPrimas.csv"));
			String linea = null;
			while((linea = reader.readLine())!=null){
				String[] lineaInsertar = linea.split(",");
				insertarTupla(Proveedor.NOMBRERELACIONMATERIAPRIMA, Proveedor.COLUMNASRELACIONMATERIAPRIMA, Proveedor.TIPORELACIONMATERIAPRIMA,lineaInsertar);
			}
			reader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader("data//datosTablas//usuarios.csv"));
			String linea = null;
			while((linea = reader.readLine())!=null){
				String[] lineaInsertar = linea.split(",");
				insertarTupla(Usuario.NOMBRE, Usuario.COLUMNAS, Usuario.TIPO,lineaInsertar);
			}
			reader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
