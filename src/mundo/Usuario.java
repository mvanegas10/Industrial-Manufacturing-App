package mundo;

public class Usuario {
	
	//--------------------------------------------------
	// ATRIBUTOS
	//--------------------------------------------------
	
	public static final String NOMBRE = "usuarios";
	
	public static final String[] COLUMNAS = {"login", "tipo", "password"};
	
	public static final String[] TIPO = {"String", "String", "String"};
	
	private String login;
	
	private String tipo;
	
	private String password;
	
	//--------------------------------------------------
	// CONSTRUCTOR
	//--------------------------------------------------

	public Usuario(String login, String tipo, String password) {
		super();
		this.tipo = tipo;
		this.login = login;
		this.password = password;
	}
	
	//--------------------------------------------------
	// SETTERS AND GETTERS
	//--------------------------------------------------

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	

}
