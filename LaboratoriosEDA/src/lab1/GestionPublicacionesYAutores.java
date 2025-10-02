package lab1;
import java.util.ArrayList;
import java.util.List;

public class GestionPublicacionesYAutores {
	private MapaPublicaciones mapaP;
	private MapaAutores mapaA;
	
	public GestionPublicacionesYAutores() {
		mapaP = new MapaPublicaciones();
		mapaA = new MapaAutores();
		mapaP.cargarFicheroAutoresPorPublicacion("datos/publications-authors-all-final.txt");
		mapaP.cargarFicheroPublicacionesCitadas("datos/publications-citedPubs-reducido.txt");
		mapaP.cargarPublicacionesDeFichero("datos/publications-titles-all.txt");
		mapaA.cargarFicheroAutores("datos/authors-name-all.txt");
	}
	
	public List<Autor> obtenerAutoresDeLaPublicacion(String idP){
		List<String> la = mapaP.obtenerAutoresPublicacion(idP);
		List<Autor> lAutores = new ArrayList<>();
		for(String idA: la) {
			Autor a = mapaA.obtenerAutor(idA);
			lAutores.add(a);
		}
		return lAutores;
	}
} //jjoij

