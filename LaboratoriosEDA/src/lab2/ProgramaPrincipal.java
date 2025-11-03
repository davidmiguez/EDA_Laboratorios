package lab2;

import java.util.List;

public class ProgramaPrincipal {
	public static void main(String[] args) {
		//Obtener la fecha actual del sistema
		long milisInicio = System.currentTimeMillis();
		MapaAutores la = new MapaAutores();
		la.cargarFicheroAutores("Datuak/Datuak/authors-name-all.txt");
		long milisFin = System.currentTimeMillis();
		
		MapaPublicaciones mp = new MapaPublicaciones();
		mp.cargarPublicacionesDeFichero("Datuak/Datuak/publicaciones.txt");
		mp.cargarFicheroAutoresPorPublicacion("Datuak/Datuak/publications-authors-all-final.txt");
		mp.cargarFicheroPublicacionesCitadas("Datuak/Datuak/publications-citedPubs-all.txt");
		
		System.out.println("Tiempo de ejecución: "+(milisFin-milisInicio)+" milisegundos");
		
		
	}
}
