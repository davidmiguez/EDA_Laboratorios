package lab4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import lab1.Autor;
import lab1.MapaAutores;
import lab1.MapaPublicaciones;



public class GestionPublicacionesYAutores {
	private MapaPublicaciones mapaP;
	private MapaAutores mapaA;
	
	public GestionPublicacionesYAutores() { //O(n)-> Coste lineal
		mapaP = new MapaPublicaciones(); //O(1)
		mapaA = new MapaAutores(); //O(1)
	}
	
	public List<Autor> obtenerAutoresDeLaPublicacion(String idP){ //O(n)-> Coste lineal
		List<String> la = mapaP.obtenerAutoresPublicacion(idP); //O(1)
		List<Autor> lAut = new ArrayList<>(); //O(1)
		for(String idA: la) { //n x O(1) -> O(n) donde n es el n�mero de elementos de la
			Autor a = mapaA.obtenerAutor(idA); //O(1)
			lAut.add(a); //O(1)
		}
		return lAut;
    } 
	
	
	
	public void cargarDatos() {
        mapaA.cargarFicheroAutores("datos/lab1_datos/authors-name-all.txt");
        mapaP.cargarPublicacionesDeFichero("datos/publications-titles-all.txt");
        mapaP.cargarFicheroAutoresPorPublicacion("datos/publications-authors-all-final.txt");
        mapaP.cargarFicheroPublicacionesCitadas("datos/publications-citedPubs-reducido.txt");
    }
	
	public static void main(String[] args) {
		GestionPublicacionesYAutores gp = new GestionPublicacionesYAutores();
		List<Autor> l = gp.obtenerAutoresDeLaPublicacion("Q24310331");
		if(l == null)
			System.out.println("No hay");
		else
			System.out.println(l);
	}
	
	public MapaPublicaciones getMapaPublicaciones() {
	    return mapaP;
	}

	public MapaAutores getMapaAutores() {
	    return mapaA;
	}
	
}








