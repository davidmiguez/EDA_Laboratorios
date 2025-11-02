package lab2;

import java.util.ArrayList;

public class Publicacion {
	private String identificador;
	private String titulo;
	//private ArrayList<Publicacion> publicacionesCitadas;

	public Publicacion() { //O(1)
	}
	
	public Publicacion(String identificador, String titulo) { //O(1)
		this.identificador = identificador;
		this.titulo = titulo;
	}
	
	public String getIdentificador() { //O(1)
		return identificador;
	}
	
	public void setIdentificador(String identificador) { //O(1)
		this.identificador = identificador;
	}
	
	public String getTitulo() { //O(1)
		return titulo;
	}
	
	public void setTitulo(String titulo) { //O(1)
		this.titulo = titulo;
	}
	
	
}
