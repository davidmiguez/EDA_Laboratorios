package lab3;

public class Main {
	public static void main(String[] args) {
		MapaPublicaciones mapaP = new MapaPublicaciones();
		mapaP.cargarPublicacionesDeFichero("Datuak/Datuak/publicaciones.txt");
		mapaP.cargarFicheroAutoresPorPublicacion("Datuak/Datuak/publications-authors-all-final.txt");
		mapaP.cargarFicheroPublicacionesCitadas("Datuak/Datuak/publications-citedPubs-all.txt");
		
		
		MapaAutores mapaA = new MapaAutores();
		mapaA.cargarFicheroAutores("Datuak/Datuak/authors-name-all.txt");
		
		Graph g = new Graph();
		g.crearGrafo(mapaA, mapaP);
		System.out.println(g.estanConectadosBIS("", ""));
		System.out.println(g.estanConectadosAL("", ""));
	}
}
