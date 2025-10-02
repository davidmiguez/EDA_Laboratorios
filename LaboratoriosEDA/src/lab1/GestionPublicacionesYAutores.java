package lab1;
import java.util.ArrayList;
import java.util.List;

public class GestionPublicacionesYAutores {
	private MapaPublicaciones mapaP;
	private MapaAutores mapaA;
	
	public GestionPublicacionesYAutores() { //O(n)-> Coste lineal
		mapaP = new MapaPublicaciones(); //O(1)
		mapaA = new MapaAutores(); //O(1)
		mapaP.cargarFicheroAutoresPorPublicacion("datos/publications-authors-all-final.txt"); //O(n)
		mapaP.cargarFicheroPublicacionesCitadas("datos/publications-citedPubs-reducido.txt"); //O(n)
		mapaP.cargarPublicacionesDeFichero("datos/publications-titles-all.txt"); //O(n)
		mapaA.cargarFicheroAutores("datos/authors-name-all.txt"); //O(n)
	}
	
	public List<Autor> obtenerAutoresDeLaPublicacion(String idP){ //O(n)-> Coste lineal
		List<String> la = mapaP.obtenerAutoresPublicacion(idP); //O(1)
		List<Autor> lAut = new ArrayList<>(); //O(1)
		for(String idA: la) { //n x O(1) -> O(n) donde n es el número de elementos de la
			Autor a = mapaA.obtenerAutor(idA); //O(1)
			lAut.add(a); //O(1)
		}
	
		return lAut;
    } 
}

