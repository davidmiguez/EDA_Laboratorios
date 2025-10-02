package lab1;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MapaPublicaciones {
	// Mapa con clave identificador de la publicación y valor objeto publicación
	private Map<String, Publicacion> mapaPublicaciones; // Almacena todas las publicaciones identificadas por su
														// identificador
	private Map<String, ArrayList<String>> mapaCitas; // Almacena por cada identificador de publicación una lista con
														// los identificadores de todas las publicaciones citadas
	private Map<String, ArrayList<String>> mapaAutores; // Almacena por cada identificador de publicación una lista con
														// los identificadores de todos los autores que han participado
														// en la publicación

	public MapaPublicaciones() {
		mapaPublicaciones = new HashMap<>();
		mapaCitas = new HashMap<>();
		mapaAutores = new HashMap<>();
	}

	public void cargarFicheroAutoresPorPublicacion(String nom) {
		try {
			Scanner entrada = new Scanner(new FileReader(nom));
			String linea;
			while (entrada.hasNextLine()) {
				linea = entrada.nextLine();
				String[] datos = linea.split(" # ");
				String idP = datos[0];
				String idA = datos[1];
				if (!mapaAutores.containsKey(idP)) {
					mapaAutores.put(idP, new ArrayList<>());
				}
				mapaAutores.get(idP).add(idA);
			}
			entrada.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void guardarFicheroAutoresPorPublicacion(String nom) {
		try {
			PrintWriter writer = new PrintWriter(new File(nom)); //como en el ejemplo
			for(String idP: mapaAutores.keySet()) { //recorrer idP del mapa de autores
				for(String idA: mapaAutores.get(idP)) { //recorrer ids de los autores asociados a idP
					writer.println(idP+" # "+idA);
				}
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cargarFicheroPublicacionesCitadas(String nom) {
		try {
			Scanner entrada = new Scanner(new FileReader(nom));
			String linea;
			while (entrada.hasNextLine()) {
				linea = entrada.nextLine();
				String datos[] = linea.split(" # "); //para que reconozca el separador, y guarda lo de la izq en pos0 y lo de la dcha en pos1
				if (!mapaCitas.containsKey(datos[0])) {
					mapaCitas.put(datos[0], new ArrayList<>());
				}
				mapaCitas.get(datos[0]).add(datos[1]);
			}
			entrada.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void guardarFicheroPublicacionesCitadas(String nom) {
		try {
			PrintWriter writer = new PrintWriter(new File(nom));
			for(String idP: mapaCitas.keySet()) {
				for(String idC: mapaCitas.get(idP)) {
					writer.println(idP+" # "+idC);
				}
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cargarPublicacionesDeFichero(String nombre) {
		try {
			Scanner entrada = new Scanner(new FileReader(nombre));
			String linea = null;
			while (entrada.hasNextLine()) {
				linea = entrada.nextLine();
				String[] datos = linea.split(" # ");
				Publicacion p = new Publicacion(datos[0], datos[1]);
				mapaPublicaciones.put(datos[0], p);
			}
			entrada.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void guardarPublicacionesEnFichero(String nom) {
		try {
			PrintWriter writer = new PrintWriter(new File(nom));
			for(Publicacion p: mapaPublicaciones.values()) { //escribimos todas las publicaciones
				writer.println(p.getIdentificador()+" # "+p.getTitulo());
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Publicacion buscarPublicacion(String idP) {
		Publicacion p = mapaPublicaciones.get(idP);
		return p;
	}

	public void insertarNuevaPublicacion(String idP, String tituloP) {
		Publicacion p = new Publicacion(idP, tituloP);
		mapaPublicaciones.put(idP, p);
	}

	public void aniadirCitaAPublicacion(String idP, String idPCitada) {
		if (mapaCitas.containsKey(idP)) { 
			mapaCitas.get(idP).add(idPCitada); 
		}
		else {
			mapaCitas.put(idP, new ArrayList<>());
			mapaCitas.get(idP).add(idPCitada); 
		}
	}

	public void aniadirAutorAPublicacion(String idP, String idA) {
		if (!mapaAutores.containsKey(idP)) { //mejor comprobar primero si no
			mapaAutores.put(idP, new ArrayList<>());
		}
		mapaAutores.get(idP).add(idA);
	}

	public List<Publicacion> obtenerListaPublicacionesCitadas(String idP) {
		List<Publicacion> lp = new ArrayList<>();
		ArrayList<String> lCitas = mapaCitas.get(idP); //array con los ids de las publicaciones citadas por idP
		for (String id : lCitas) {
			Publicacion p = mapaPublicaciones.get(id);
			lp.add(p);
		}
		return lp;
	}

	// Dada una publicación (identificador), devolver una lista con las
	// publicaciones que cita
	public List<String> obtenerListaPublicacionesCitadasV2(String idP) {
		// Obtengo los identificadores de las publicaciones citadas por idP
		ArrayList<String> lCitas = mapaCitas.get(idP);

		return lCitas;
	}

	public List<String> obtenerAutoresPublicacion(String idP){
		List<String> la = mapaAutores.get(idP);	
		return la;
	}
	
	public void borrarPublicacion(String idP) {
		mapaPublicaciones.remove(idP);
	}

	//he hecho que el mergeSort sea una clase distinta para que este mas limpio
	public List<Publicacion> obtenerPublicacionesOrdenadas(){
		List<Publicacion> publicacionesOrdenadas = new ArrayList<>(mapaPublicaciones.values()); //copia para no cargarnos la lista original
		MergeSort.mergeSortPublicaciones(publicacionesOrdenadas); //llamada a mergeSort con inicio=0 y fin=size-1
		return publicacionesOrdenadas;
	}

	public List<Publicacion> obtenerPublicacionesAutor(String idA){
		List<Publicacion> lp = new ArrayList<>();
		//recorrer publicaciones
		for(String idP: mapaAutores.keySet()) {
			//por cada idP obtener autores
			List<String> la = mapaAutores.get(idP);
			int pos = la.indexOf(idA);
			if(pos!= -1) {
				Publicacion p = mapaPublicaciones.get(idP);
				lp.add(p);
			}
		}
		return lp;
	}	
}



