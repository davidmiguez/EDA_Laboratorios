package lab1;
public class Autor {
	private String idAutor;
	private String nombreAutor;

	public Autor() {
	}
	
	public Autor(String idAutor, String nombreAutor) { //max {O(1), O(1)} -> O(1) -> Coste constante

		this.idAutor = idAutor; //O(1)
		this.nombreAutor = nombreAutor; //O(1)
	}
	
	public String getIdentificador() { //O(1) -> Coste constante
		return idAutor;
	}
	
	public String getNombre() { //O(1)-> Coste constante
		return nombreAutor;
	}
	
	public void setIdentificador(String idAutor) { //O(1)-> Coste constante
		this.idAutor = idAutor;
	}
		
	public void setNombre(String nombreAutor) { //O(1)-> Coste constante
		this.nombreAutor = nombreAutor;
	}
	
	
}
