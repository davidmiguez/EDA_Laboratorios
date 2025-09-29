package lab1;
import java.util.ArrayList;
import java.util.List;

public class GestionPublicacionesYAutores {
	private MapaPublicaciones mapaP;
	private MapaAutores mapaA;
	
	public GestionPublicacionesYAutores() {
		mapaP = new MapaPublicaciones();
		mapaA = new MapaAutores();
		mapaP.cargarFicheroAutoresPorPublicacion("Datuak/Datuak/publications-authors-all-final.txt");
		mapaP.cargarFicheroPublicacionesCitadas("Datuak/Datuak/publications-citedPubs-all.txt");
		mapaP.cargarPublicacionesDeFichero("Datuak/Datuak/publications-titles-all.txt");
		mapaA.cargarFicheroAutores("Datuak/Datuak/authors-name-all.txt");
	}
	
	public List<Autor> obtenerAutoresDeLaPublicacion(String idP){
		List<String> la = mapaP.obtenerAutoresDeLaPublicacion(idP);
		List<Autor> lAutores = new ArrayList<>();
		for(String idA: la) {
			Autor a = mapaA.obtenerAutor(idA);
			lAutores.add(a);
		}
		return lAutores;
	}
}

