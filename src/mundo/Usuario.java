package mundo;

public class Usuario {
	
	//--------------------------------------------------
	// ATRIBUTOS
	//--------------------------------------------------
	
	public static final String NOMBRE = "usuarios";
	
	public static final String[] COLUMNAS = {"id", "tipo", "login", "password"};
	
	public static final String[] TIPO = {"String", "String", "String", "String"};
	
	private String id;
	
	private String tipo;
	
	private String login;
	
	private String password;
	
	//--------------------------------------------------
	// CONSTRUCTOR
	//--------------------------------------------------

	public Usuario(String id, String tipo, String login, String password) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.login = login;
		this.password = password;
	}
	
	//--------------------------------------------------
	// SETTERS AND GETTERS
	//--------------------------------------------------

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	

}
