package lab1;
import java.util.ArrayList;

public class Publicacion {
	private String idPublic;
	private String tituloPublic;

	public Publicacion() {
	}
	
	public Publicacion(String idPublic, String tituloPublic) {
		this.idPublic = idPublic;
		this.tituloPublic = tituloPublic;
	}
	
	public String getIdentificador() {
		return idPublic;
	}
	
	public String getTitulo() {
		return tituloPublic;
	}
	
	public void setIdentificador(String idPublic) {
		this.idPublic = idPublic;
	}
	
	public void setTitulo(String tituloPublic) {
		this.tituloPublic = tituloPublic;
	}
	
	
}

