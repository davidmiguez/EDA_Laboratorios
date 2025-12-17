package lab4;

public class Main {
	public static void main(String[] args) {
		MapaPublicaciones mp = new MapaPublicaciones();
		mp.cargarPublicacionesDeFichero("Datuak/Datuak/publicaciones.txt");
		mp.cargarFicheroAutoresPorPublicacion("Datuak/Datuak/publications-authors-all-final.txt");
		mp.cargarFicheroPublicacionesCitadas("Datuak/Datuak/publications-citedPubs-all.txt");
		
		
		MapaAutores ma = new MapaAutores();
		ma.cargarFicheroAutores("Datuak/Datuak/authors-name-all.txt");
		
		Graph g = new Graph();
		g.crearGrafo(ma, mp);
		System.out.println(g.estanConectadosBIS("", ""));
		System.out.println(g.estanConectadosAL("", ""));
	}
}
