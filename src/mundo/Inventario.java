package mundo;

import java.util.ArrayList;
import java.util.List;

public class Inventario {

	//--------------------------------------------------
	// ATRIBUTOS
	//--------------------------------------------------

	private List<String[]> matrizCantProductos; 

	private List<String[]> matrizCantMateriasPrimas; 

	private List<String[]> matrizCantComponentes;
	
	private ArrayList<Producto> productos;
	
	private ArrayList<MateriaPrima> materiasPrimas;
	
	private ArrayList<Componente> componentes;
	
	//--------------------------------------------------
	// CONSTRUCTOR
	//--------------------------------------------------

	public Inventario( ) {
		this.matrizCantProductos = new ArrayList<String[]>();
		this.matrizCantMateriasPrimas = new ArrayList<String[]>();
		this.matrizCantComponentes = new ArrayList<String[]>();
		this.productos = new ArrayList<Producto>();
		this.materiasPrimas = new ArrayList<MateriaPrima>();
	
	}
	
	//--------------------------------------------------
	// GETTERS AND SETTERS
	//--------------------------------------------------
	
	public List<String[]> getMatrizCantProductos() {
		return matrizCantProductos;
	}

	public void setMatrizCantProductos(List<String[]> matrizCantProductos) {
		this.matrizCantProductos = matrizCantProductos;
	}

	public List<String[]> getMatrizCantMateriasPrimas() {
		return matrizCantMateriasPrimas;
	}

	public void setMatrizCantMateriasPrimas(List<String[]> matrizCantMateriasPrimas) {
		this.matrizCantMateriasPrimas = matrizCantMateriasPrimas;
	}

	public List<String[]> getMatrizCantComponentes() {
		return matrizCantComponentes;
	}

	public void setMatrizCantComponentes(List<String[]> matrizCantComponentes) {
		this.matrizCantComponentes = matrizCantComponentes;
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	} 
	
	//--------------------------------------------------
	// MÉTODOS
	//--------------------------------------------------
	
	
	

}
