package lab1;
import java.util.ArrayList;
import java.util.List;

public class GestionPublicacionesYAutores {
	private MapaPublicaciones mapaP;
	private MapaAutores mapaA;
	
	public GestionPublicacionesYAutores() {
		mapaP = new MapaPublicaciones();
		mapaA = new MapaAutores();
		mapaP.cargarFicheroAutoresPorPublicacion("C:\\Users\\David Miguez\\Downloads\\Datuak\\Datuak\\publications-authors-all-final.txt");
		mapaP.cargarFicheroPublicacionesCitadas("C:\\Users\\David Miguez\\Downloads\\Datuak\\Datuak\\publications-citedPubs-all.txt");
		mapaP.cargarPublicacionesDeFichero("C:\\Users\\David Miguez\\Downloads\\Datuak\\Datuak\\publications-titles-all.txt");
		mapaA.cargarFicheroAutores("C:\\Users\\David Miguez\\Downloads\\Datuak\\Datuak\\authors-name-all.txt");
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
} //jjoij

