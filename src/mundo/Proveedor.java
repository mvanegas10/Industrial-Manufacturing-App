package mundo;

import java.util.ArrayList;
import java.util.List;

public class Proveedor{
	
	//--------------------------------------------------
	// ATRIBUTOS
	//--------------------------------------------------
	
	public static final String NOMBRE = "Proveedores";
	
	public static final String[] COLUMNAS = {"id","direccion","telefono","ciudad","idRepLegal"};
	
	public static final String[] TIPO = {"String","String","int","String","String"};
	
	public static final String NOMBRERELACIONMATERIAPRIMA = "ProveedoresMateriaPrima";
	
	public static final String[] COLUMNASRELACIONMATERIAPRIMA = {"id_proveedor","id_materiaPrima","volMax","tiempoMax"};
	
	public static final String[] TIPORELACIONMATERIAPRIMA = {"String","String","int","int"};
	
	
	private String id;
	
	private String direccion;
	
	private int telefono;
	
	private String ciudad;
	
	private String idRepLegal;
	
	private List<String[]> matrizVolTiempoMat;
	
	private List<String[]> matrizVolTiempoComp;
	
	private ArrayList<MateriaPrima> materiasPrimas;
	
	private ArrayList<Componente> componentes;
	
	
	
	//--------------------------------------------------
	// CONSTRUCTOR
	//--------------------------------------------------

	public Proveedor(String pId, String pDireccion, int pTelefono, String pCiudad, String pIdRepLegal, List<String[]> pMatrizVolTiempoMat, List<String[]> pMatrizVolTiempoComp, ArrayList<MateriaPrima> pMateriasPrimas, ArrayList<Componente> pComponentes) {
		this.id = pId;
		this.direccion = pDireccion;
		this.telefono = pTelefono;
		this.ciudad = pCiudad;
		this.idRepLegal = pIdRepLegal;
		this.matrizVolTiempoMat = pMatrizVolTiempoMat;
		this.matrizVolTiempoComp = pMatrizVolTiempoComp;
		this.materiasPrimas = pMateriasPrimas;
		this.componentes = pComponentes;	
	}
	
	//--------------------------------------------------
	// GETTERS AND SETTERS
	//--------------------------------------------------

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getIdRepLegal() {
		return idRepLegal;
	}

	public void setIdRepLegal(String idRepLegal) {
		this.idRepLegal = idRepLegal;
	}

	public List<String[]> getMatrizVolTiempoMat() {
		return matrizVolTiempoMat;
	}

	public void setMatrizVolTiempoMat(List<String[]> matrizVolTiempoMat) {
		this.matrizVolTiempoMat = matrizVolTiempoMat;
	}

	public List<String[]> getMatrizVolTiempoComp() {
		return matrizVolTiempoComp;
	}

	public void setMatrizVolTiempoComp(List<String[]> matrizVolTiempoComp) {
		this.matrizVolTiempoComp = matrizVolTiempoComp;
	}

	public ArrayList<MateriaPrima> getMateriasPrimas() {
		return materiasPrimas;
	}

	public void setMateriasPrimas(ArrayList<MateriaPrima> materiasPrimas) {
		this.materiasPrimas = materiasPrimas;
	}

	public ArrayList<Componente> getComponentes() {
		return componentes;
	}

	public void setComponentes(ArrayList<Componente> componentes) {
		this.componentes = componentes;
	}
	
}
