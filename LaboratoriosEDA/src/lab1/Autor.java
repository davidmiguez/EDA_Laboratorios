package lab1;
public class Autor {
	private String idAutor;
	private String nombreAutor;

	public Autor() {
	}
	
	public Autor(String idAutor, String nombreAutor) {
		this.idAutor = idAutor;
		this.nombreAutor = nombreAutor;
	}
	
	public String getIdentificador() {
		return idAutor;
	}
	
	public String getNombre() {
		return nombreAutor;
	}
	
	public void setIdentificador(String idAutor) {
		this.idAutor = idAutor;
	}
		
	public void setNombre(String nombreAutor) {
		this.nombreAutor = nombreAutor;
	}
	
	
}
