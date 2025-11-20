package lab3;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

public class MapaPublicaciones {
	// Mapa con clave identificador de la publicación y valor objeto publicación
	private Map<String, Publicacion> mapaPublicaciones; // Almacena todas las publicaciones identificadas por su
														// identificador
	private Map<String, UnorderedDoubleLinkedList<String>> mapaCitas; // Almacena por cada identificador de publicación una lista con
														// los identificadores de todas las publicaciones citadas
	private Map<String, OrderedDoubleLinkedList<String>> mapaAutores; // Almacena por cada identificador de publicación una lista con
														// los identificadores de todos los autores que han participado
														// en la publicación

	public MapaPublicaciones() { // O(1) -> Coste constante
		mapaPublicaciones = new HashMap<>();
		mapaCitas = new HashMap<>();
		mapaAutores = new HashMap<>();
	}

	public void cargarFicheroAutoresPorPublicacion(String nom) { // O(n) -> Coste lineal
		try {
			Scanner entrada = new Scanner(new FileReader(nom)); // O(1)
			String linea = null; // O(1)
			while (entrada.hasNextLine()) { // n x O(1) -> O(n) donde n es el número de líneas que tenga el fichero
				linea = entrada.nextLine(); // O(1)
				String[] datos = linea.split(" # "); // O(1)
				String idP = datos[0]; // O(1)
				if (datos.length == 2) {
					String idA = datos[1]; // O(1)
		
					if (!mapaAutores.containsKey(idP)) { // O(1)xO(1) -> O(1)
						
						mapaAutores.put(idP, new OrderedDoubleLinkedList<>()); // O(1)
					}
					mapaAutores.get(idP).add(idA); // O(1)
				} else {
					if (!mapaAutores.containsKey(idP)) { // O(1)xO(1) -> O(1)
						
						mapaAutores.put(idP, new OrderedDoubleLinkedList<>()); // O(1)
					}
				}
			}
			entrada.close(); // O(1)
		} catch (IOException e) {
			e.printStackTrace(); // O(1)
		}
	}

	public void guardarFicheroAutoresPorPublicacion(String nom) { // O(n) -> Coste lineal
		try {
			// O(1)
			PrintWriter salida = new PrintWriter(new File(nom)); // Abrimos el fichero para escritura
			// Recorremos el mapa que relaciona los autores con las publicaciones
			// for: n x O( m) -> O(nxm) -> O(n) donde n es el número de claves del
			
			for (String idP : mapaAutores.keySet()) { // Recorremos las claves (id de publicación) del mapa de autores
				
				Iterator<String> it = mapaAutores.get(idP).iterator();
				while(it.hasNext()) {
					String idA = it.next();
					salida.println(idP + " # " + idA); // O(1)
				}
			}
			salida.flush();
			salida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// nom: Nombre del fichero que guarda la información
	public void cargarFicheroPublicacionesCitadas(String nom) {
		try {
			Scanner entrada = new Scanner(new FileReader(nom));
			String linea;
			while (entrada.hasNextLine()) { // Mientras haya líneas que leer
				linea = entrada.nextLine(); // Lee una línea del fichero y avanza a la siguiente
				String datos[] = linea.split(" # ");
			
				if (!mapaCitas.containsKey(datos[0])) {
					mapaCitas.put(datos[0], new UnorderedDoubleLinkedList<>());
				}
				
				mapaCitas.get(datos[0]).addToFront(datos[1]);
			}
			entrada.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void guardarFicheroPublicacionesCitadas(String nom) {
		try {
			PrintWriter salida = new PrintWriter(new File(nom));
			// Recorremos las claves del mapa de citas
			for (String idP : mapaCitas.keySet()) {
				
				Iterator<String> it = mapaCitas.get(idP).iterator();
				while(it.hasNext()) {
					String idC = it.next();
					salida.println(idP + " # " + idC);
				}
			}
			salida.flush();
			salida.close();
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

	public void guardarPublicacionesEnFichero(String nom) { // O(n) -> Coste lineal
		try {
			PrintWriter salida = new PrintWriter(new File(nom)); // O(1)
			// Recorremos los valores del mapa
			// En este caso los valores son todas las publicaciones, que es lo que queremos
			// escribir en el fichero
			for (Publicacion p : mapaPublicaciones.values()) { // n x O(1) -> O(n) donde n es el número de valores del
																// mapaPublicaciones
				salida.println(p.getIdentificador() + " # " + p.getTitulo()); // O(1)
			}
			salida.flush(); // O(1)
			salida.close(); // O(1)
		} catch (IOException e) {
			e.printStackTrace(); // O(1)
		}
	}

	// Buscar una publicación dado su identificador
	public Publicacion buscarPublicacion(String idP) { // O(1) -> Coste constante
		Publicacion p = mapaPublicaciones.get(idP); // O(1)
		return p;
	}

	// Insertar una nueva publicación (dados su identificador y título)
	public void insertarNuevaPublicacion(String idP, String tituloP) { // O(1) -> Coste constante
		Publicacion p = new Publicacion(idP, tituloP); // O(1)
		mapaPublicaciones.put(idP, p); // O(1)
	}

	// Añadir una cita a una publicación (dados sus identificadores)
	public void aniadirCitaAPublicacion(String idP, String idPCitada) { // O(1) -> Coste constante
		

		if (!mapaCitas.containsKey(idP)) { // O(1)
			mapaCitas.put(idP, new UnorderedDoubleLinkedList<>()); // O(1)
		}
		
		//mapaCitas.get(idP).add(idPCitada); // O(1)
		mapaCitas.get(idP).addToFront(idPCitada);
	}

	// Añadir un autor a una publicación (dados sus identificadores) //O(1) -> Coste
	// constante
	public void aniadirAutorAPublicacion(String idP, String idA) {
		if (!mapaAutores.containsKey(idP)) { // O(1)
		
			mapaAutores.put(idP, new OrderedDoubleLinkedList<>()); // O(1)
		}
		mapaAutores.get(idP).add(idA); // O(1)
	}

	// Dada una publicación (identificador), devolver una lista con las
	// publicaciones que cita
	public List<Publicacion> obtenerListaPublicacionesCitadas(String idP) { // O(n) -> Coste lineal
		List<Publicacion> lp = new ArrayList<>(); // O(1)

		
		Iterator<String> it = mapaCitas.get(idP).iterator();
		while(it.hasNext()) {
			String id = it.next();
			Publicacion p = mapaPublicaciones.get(id);
			lp.add(p);
		}

		return lp;
	}

	public UnorderedDoubleLinkedList<String> obtenerListaPublicacionesCitadasV2(String idP) { // O(1) -> Coste constante
		// Obtengo los identificadores de las publicaciones citadas por idP
		UnorderedDoubleLinkedList<String> lCitas = mapaCitas.get(idP); // O(1)

		return lCitas;
	}

	public OrderedDoubleLinkedList<String> obtenerAutoresDeLaPublicacion(String idP) { // O(1) -> Coste constante
		// Obtengo la lista de identificadores de autor asociada a la publicación idP
		OrderedDoubleLinkedList<String> la = mapaAutores.get(idP); // O(1)

		return la;
	}

	// Borrar una publicación
	public void borrarPublicacion(String idP) { // O(1) -> Coste constante
		mapaPublicaciones.remove(idP); // O(1)
	}

//	Obtener una lista de publicaciones ordenada alfabéticamente (esta operación no debe
//	modificar la lista de publicaciones, sino que debe devolver una nueva lista ordenada, de tipo
//	ArrayList o LinkedList). Se debe implementar un algoritmo de ordenación, es decir, no se
//	puede llamar a una función estándar de ordenación ya implementada.
	public List<Publicacion> obtenerPublicacionesOrdenadas() { // Coste logarítmico
		List<Publicacion> publicacionesOrdenadas = new LinkedList<>();

		// Recorro los valores del mapa de Publicaciones
		for (Publicacion p : mapaPublicaciones.values()) { // n x O(log m) -> donde n es el número de valores que haya
															// en el mapaPublicaciones
			// Cada publicación la tenemos que guardar de forma ordenada en
			// publicacionesOrdenadas
			if (publicacionesOrdenadas.size() == 0) { // O(1)
				publicacionesOrdenadas.add(p); // O(1)
			} else {
				int pi = 0, pf = publicacionesOrdenadas.size() - 1, mitad = 0; // O(1)
				boolean enc = false;// O(1)
				while (pi <= pf && !enc) { // m x O( 1) -> O(log m) donde m es el número de veces que entra al while
					mitad = (pi + pf) / 2;
					// Obtengo la publicación que se encuentra en la posición mitad
					Publicacion publicacion = publicacionesOrdenadas.get(mitad); // O(1)
					if (publicacion.getIdentificador().compareTo(p.getIdentificador()) < 0) { // O(1)
						pi = mitad + 1; // O(1)
					} else { // El id de la publicación es mayor que el id de la publicación que quiero
								// insertar
						if (mitad == 0 || publicacionesOrdenadas.get(mitad - 1).getIdentificador()
								.compareTo(p.getIdentificador()) < 0) { // O(1)
							enc = true; // O(1)
						} else {
							pf = mitad - 1;// O(1)
						}
					}
				}
				if (!enc) {
					publicacionesOrdenadas.add(p);
				} else {
					publicacionesOrdenadas.add(mitad, p);
				}
			}
		}
		return publicacionesOrdenadas;
	}

	public List<Publicacion> obtenerPublicacionesOrdenadasBis() {
		Comparator<Publicacion> c = new Comparator<Publicacion>() {

			@Override
			public int compare(Publicacion o1, Publicacion o2) {
				return o1.getIdentificador().compareTo(o2.getIdentificador());
			}
		};
		TreeSet<Publicacion> ts = new TreeSet<>(c);
		for (Publicacion p : mapaPublicaciones.values()) {
			ts.add(p);
		}
		return new ArrayList<>(ts);
	}
	
	public List<Publicacion> obtenerPublicacionesOrdenadasBis2() {
		return mergeSort(new ArrayList(mapaPublicaciones.values()));
	}

	public List<Publicacion> mergeSort(List<Publicacion> lista) {
		if (lista.size() <= 1) {
			return lista;
		}

		int mitad = lista.size() / 2;
		List<Publicacion> izquierda = mergeSort(new ArrayList<>(lista.subList(0, mitad)));
		List<Publicacion> derecha = mergeSort(new ArrayList<>(lista.subList(mitad, lista.size())));

		return merge(izquierda, derecha);
	}

	private static List<Publicacion> merge(List<Publicacion> izquierda, List<Publicacion> derecha) {
		List<Publicacion> resultado = new ArrayList<>();
		int i = 0, j = 0;

		while (i < izquierda.size() && j < derecha.size()) {
			// Comparar los IDs como Strings
			if (izquierda.get(i).getIdentificador().compareTo(derecha.get(j).getIdentificador()) <= 0) {
				resultado.add(izquierda.get(i));
				i++;
			} else {
				resultado.add(derecha.get(j));
				j++;
			}
		}

		// Agregar lo que quede
		while (i < izquierda.size()) {
			resultado.add(izquierda.get(i));
			i++;
		}
		while (j < derecha.size()) {
			resultado.add(derecha.get(j));
			j++;
		}

		return resultado;
	}

	// cadena1.compareTo(cadena2)
	// Devuelve <0 si cadena1 < cadena2
	// Devuelve >0 si cadena1 > cadena2
	// Devuelve =0 si cadena1 == cadena2

	// Dado un autor, devolver una lista con sus publicaciones
	public List<Publicacion> obtenerPublicacionesAutor(String idA) { // O(n) -> Coste lineal
		List<Publicacion> lp = new ArrayList<>(); // O(1)
		// Recorremos todas las claves del mapaAutores, las claves son los ids de la
		// publicaciones
		for (String idP : mapaAutores.keySet()) { // n x O(1) -> O(n) donde n es el número de claves del mapaAutores
			
			OrderedDoubleLinkedList<String> la = mapaAutores.get(idP);
			if(la.contains(idA)) {
				Publicacion p = mapaPublicaciones.get(idP); // O(1)
				lp.add(p); // O(1)
			}
		}
		return lp;
	}

	public int obtenerNumeroClavesMapaPublicaciones() {
		return mapaAutores.keySet().size();
	}

}
