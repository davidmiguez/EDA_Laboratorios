package lab3;


public class Autor {
	private String identificador;
	private String nombre;

	public Autor() {
	}
	
	public Autor(String identificador, String nombre) { //max {O(1), O(1)} -> O(1) -> Coste constante
		this.identificador = identificador; //O(1)
		this.nombre = nombre; //O(1)
	}
	
	
	public String getIdentificador() {
		return identificador;
	}
	
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
