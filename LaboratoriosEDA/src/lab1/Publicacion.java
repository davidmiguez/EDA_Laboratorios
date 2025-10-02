package lab1;
import java.util.ArrayList;

public class Publicacion {  //O(1)-> Coste constante
	private String idPublic;
	private String tituloPublic;

	public Publicacion() {  //O(1)
	}
	
	public Publicacion(String idPublic, String tituloPublic) {  //O(1)-> Coste constante
		this.idPublic = idPublic;
		this.tituloPublic = tituloPublic;
	}
	
	public String getIdentificador() {  //O(1)-> Coste constante
		return idPublic;
	}
	
	public String getTitulo() { //O(1)-> Coste constante
		return tituloPublic;
	}
	
	public void setIdentificador(String idPublic) { //O(1)-> Coste constante
		this.idPublic = idPublic;
	}
	
	public void setTitulo(String tituloPublic) { //O(1)-> Coste constante
		this.tituloPublic = tituloPublic;
	}
	
	
}

