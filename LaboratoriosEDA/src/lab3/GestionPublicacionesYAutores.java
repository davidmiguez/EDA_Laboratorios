package lab3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GestionPublicacionesYAutores {
	private MapaPublicaciones mp;
	private MapaAutores ma;
	
	public GestionPublicacionesYAutores() { //O(n)
		mp = new MapaPublicaciones(); //O(1)
		ma = new MapaAutores(); //O(1)
		mp.cargarFicheroAutoresPorPublicacion("Datuak/Datuak/publications-authors-all-final.txt"); //O(n)
		System.out.println("Cargados los autores por publicación");
		mp.cargarFicheroPublicacionesCitadas("Datuak/Datuak/publications-citedPubs-all.txt"); //O(n)
		System.out.println("Cargadas las publicaciones citadas");
		mp.cargarPublicacionesDeFichero("Datuak/Datuak/publications-titles-all.txt"); //O(n)
		System.out.println("Cargadas las publicaciones");
		ma.cargarFicheroAutores("Datuak/Datuak/authors-name-all.txt"); //O(n)
		System.out.println("Cargados los autores");
	}
	
	public List<Autor> obtenerAutoresDeLaPublicacion(String idP){ //O(n)
		//CAMBIO
		//List<String> la = mp.obtenerAutoresDeLaPublicacion(idP); //O(1)
		OrderedDoubleLinkedList<String> la = mp.obtenerAutoresDeLaPublicacion(idP); //O(1)
		List<Autor> lAutores = new ArrayList<>(); //O(1)
		//CAMBIO
		/*for(String idA: la) { //n x O(1) -> O(n) donde n es el número de elementos de la
			Autor a = ma.obtenerAutor(idA); //O(1)
			lAutores.add(a); //O(1)
		}*/
		//CAMBIO
		Iterator<String> it = la.iterator();
		while(it.hasNext()) {
			String idA = it.next();
			Autor a = ma.obtenerAutor(idA); //O(1)
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
