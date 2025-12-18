package lab4;

import lab3.Graph;

public class Main {
	public static void main(String[] args) {
		MapaPublicaciones mapaP = new MapaPublicaciones();
		mapaP.cargarPublicacionesDeFichero("datos/publications-titles-all.txt");
		mapaP.cargarFicheroAutoresPorPublicacion("datos/publications-authors-all-final.txt");
		mapaP.cargarFicheroPublicacionesCitadas("datos/publications-citedPubs-reducido.txt");
		
		
		MapaAutores mapaA = new MapaAutores();
		mapaA.cargarFicheroAutores("datos/authors-name-all.txt");
		
		Graph g = new Graph();
		g.crearGrafo(mapaA, mapaP);
		System.out.println(g.estanConectadosBIS("", ""));
		System.out.println(g.estanConectadosAL("", ""));
	}
}
