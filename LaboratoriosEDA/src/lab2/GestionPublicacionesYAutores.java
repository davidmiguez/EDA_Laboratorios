package lab2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GestionPublicacionesYAutores {
	private MapaPublicaciones mapaP;
	private MapaAutores mapaA;
	
	public GestionPublicacionesYAutores() { //O(n)
		mapaP = new MapaPublicaciones(); //O(1)
		mapaA = new MapaAutores(); //O(1)
		mapaP.cargarFicheroAutoresPorPublicacion("Datuak/Datuak/publications-authors-all-final.txt"); //O(n)
		System.out.println("Cargados los autores por publicación");
		mapaP.cargarFicheroPublicacionesCitadas("Datuak/Datuak/publications-citedPubs-all.txt"); //O(n)
		System.out.println("Cargadas las publicaciones citadas");
		mapaP.cargarPublicacionesDeFichero("Datuak/Datuak/publications-titles-all.txt"); //O(n)
		System.out.println("Cargadas las publicaciones");
		mapaA.cargarFicheroAutores("Datuak/Datuak/authors-name-all.txt"); //O(n)
		System.out.println("Cargados los autores");
	}
	
	public List<Autor> obtenerAutoresDeLaPublicacion(String idP){ //O(n)
	
		OrderedDoubleLinkedList<String> la = mapaP.obtenerAutoresDeLaPublicacion(idP); //O(1)
		List<Autor> lAutores = new ArrayList<>(); //O(1)
		
		Iterator<String> it = la.iterator();
		while(it.hasNext()) {
			String idA = it.next();
			Autor a = mapaA.obtenerAutor(idA); //O(1)
			lAutores.add(a); //O(1)
		}
		return lAutores;
	}
	
	public static void main(String[] args) {
		GestionPublicacionesYAutores gp = new GestionPublicacionesYAutores();
		List<Autor> l = gp.obtenerAutoresDeLaPublicacion("Q24310331");
		if(l == null)
			System.out.println("No hay");
		else
			System.out.println(l);
	}
}
