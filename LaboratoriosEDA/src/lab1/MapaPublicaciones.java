package lab1;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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
	//prueba 
	public void guardarFicheroAutoresPorPublicacion(String nom) {
		try {
			PrintWriter writer = new PrintWriter(new File(nom)); //Abrimos el fichero para escritura
			//Recorremos el mapa que relaciona los autores con las publicaciones
			for(String idP: mapaAutores.keySet()) { //Recorremos las claves (id de publicación) del mapa de autores
				for(String idA: mapaAutores.get(idP)) { //Recorremos los identificadores de los autores asociados a idP
					writer.println(idP+" # "+idA);
				}
			}
			writer.flush();
			writer.close();
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
				// linea = "Q43261508 # Q79773097"
				String datos[] = linea.split(" # ");
				// datos[0] = "Q43261508"
				// datos[1] = "Q79773097"
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
			//Recorremos las claves del mapa de citas
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
				// linea = "Q36934323 # Quality of life in MAP.3 (Mammary Prevention 3): a
				// randomized, placebocontrolled trial evaluating exemestane for prevention of
				// breast cancer"
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
			//Recorremos los valores del mapa
			//En este caso los valores son todas las publicaciones, que es lo que queremos escribir en el fichero
			for(Publicacion p: mapaPublicaciones.values()) {
				writer.println(p.getIdentificador()+" # "+p.getTitulo());
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Buscar una publicación dado su identificador
	public Publicacion buscarPublicacion(String idP) {
		Publicacion p = mapaPublicaciones.get(idP);
		return p;
	}

	// Insertar una nueva publicación (dados su identificador y título)
	public void insertarNuevaPublicacion(String idP, String tituloP) {
		Publicacion p = new Publicacion(idP, tituloP);
		mapaPublicaciones.put(idP, p);
	}

	// Añadir una cita a una publicación (dados sus identificadores)
	public void aniadirCitaAPublicacion(String idP, String idPCitada) {
		/*
		 * if (mapaCitas.containsKey(idP)) { mapaCitas.get(idP).add(idPCitada); } else {
		 * mapaCitas.put(idP, new ArrayList<>()); mapaCitas.get(idP).add(idPCitada); }
		 */

		if (!mapaCitas.containsKey(idP)) {
			mapaCitas.put(idP, new ArrayList<>());
		}
		mapaCitas.get(idP).add(idPCitada);

	}

	// Añadir un autor a una publicación (dados sus identificadores)
	public void aniadirAutorAPublicacion(String idP, String idA) {
		if (!mapaAutores.containsKey(idP)) {
			mapaAutores.put(idP, new ArrayList<>());
		}
		mapaAutores.get(idP).add(idA);
	}

	// Dada una publicación (identificador), devolver una lista con las
	// publicaciones que cita
	public List<Publicacion> obtenerListaPublicacionesCitadas(String idP) {
		List<Publicacion> lp = new ArrayList<>();

		// Obtengo los identificadores de las publicaciones citadas por idP
		ArrayList<String> lCitas = mapaCitas.get(idP);
		// Recorremos todas las citas
		for (String id : lCitas) { // Por cada identificador que haya en la lista de citas
			Publicacion p = mapaPublicaciones.get(id); // Obtengo el objeto Publicacion asociado al id
			lp.add(p); // Añado el objeto Publicacin a la lista que vamos a devolver
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
	
	//Dada una publicación, devolver una lista con sus autores
	public List<String> obtenerAutoresDeLaPublicacion(String idP){
		//Obtengo la lista de identificadores de autor asociada a la publicación idP
		List<String> la = mapaAutores.get(idP);
		
		return la;
	}
	
	public void borrarPublicacion(String idP) {
		mapaPublicaciones.remove(idP);
	}
	
	
	//mergeSort:
	private void merge(List<Publicacion> pLista, int pInicio, int pMitad, int pFin) {
		int t1 = pMitad - pInicio+1; //tamano izq
		int t2 = pFin - pMitad; //tamano derecha
		//arrays temporales para no sobreescribir y que se rompa
		Publicacion[] izq = new Publicacion[t1]; //copia los elementos desde inicio hasta mitad
		Publicacion[] der = new Publicacion[t2]; // desde mitad+1 hasta fin
		//anado los datos ordenados a los arrays temporales
		for(int i=0; i<t1 ; i++) {
			izq[i] = pLista.get(pInicio+i);
		}
		for(int j=0; j<t2 ; j++) {
			der[j] = pLista.get(pMitad+1+j);
		}
		
		int i=0;
		int j=0;
		int k=pInicio;
		while( i < t1 && j < t2) {
			String idIzq = izq[i].getIdentificador();
	        String idDer = der[j].getIdentificador();
	        if (idIzq.compareTo(idDer) <= 0) {
	            pLista.set(k, izq[i]);
	            i++;
	        } else {
	            pLista.set(k, der[j]);
	            j++;
	        }
	        k++;
	    }
	    //una de las listas se va a terminar antes que la otra, copiamos el sobrante
	    while (i < t1) {
	        pLista.set(k, izq[i]);
	        i++;
	        k++;
	    }
	    while (j < t2) {
	        pLista.set(k, der[j]);
	        j++;
	        k++;
		}
	}
	
	private void mergeSort(List<Publicacion> pLista, int pInicio, int pFin) {
		int mitad;
		// el caso base seria pInicio>=pFin, si es el caso, no hace nada
		if(pInicio < pFin) {
			mitad = pInicio + (pFin - pInicio) / 2; //calcula la mitad
			mergeSort(pLista, pInicio, mitad); //primera mitad
			mergeSort(pLista, mitad+1, pFin); //segunda mitad
			merge(pLista, pInicio, mitad, pFin); //juntar las dos
		}
	}
	

	public List<Publicacion> obtenerPublicacionesOrdenadas(){
		List<Publicacion> publicacionesOrdenadas = new ArrayList<>(mapaPublicaciones.values());
		mergeSort(publicacionesOrdenadas, 0, publicacionesOrdenadas.size()-1); //llamada a mergeSort con inicio=0 y fin=size-1
		return publicacionesOrdenadas;
	}

/*
	public List<Publicacion> obtenerPublicacionesOrdenadas(){
		List<Publicacion> publicacionesOrdenadas = new LinkedList<>(); // lista vacia para el resultado final ordenado
		
		//Recorro los valores del mapa de Publicaciones
		for(Publicacion p: mapaPublicaciones.values()) {
			if(publicacionesOrdenadas.size()==0) { //si esta vacia se a�ade la primera
				publicacionesOrdenadas.add(p);
			}else {

				int pi = 0; 
				int pf = publicacionesOrdenadas.size()-1, mitad=0;
				boolean enc = false;
				while(pi<=pf && !enc) {
					mitad = (pi+pf)/2;
					//Obtengo la publicación que se encuentra en la posición mitad
					Publicacion publicacion = publicacionesOrdenadas.get(mitad);
					if(publicacion.getIdentificador().compareTo(p.getIdentificador())<0) {
						pi = mitad + 1;
					}else { //El id de la publicación es mayor que el id de la publicación que quiero insertar
						if(mitad==0 || publicacionesOrdenadas.get(mitad-1).getIdentificador().compareTo(p.getIdentificador())<0) {
							enc = true;
						}else {
							pf = mitad - 1;
						}
					}
				}
				if(!enc) {
					publicacionesOrdenadas.add(p);
				}else {
					publicacionesOrdenadas.add(mitad, p);
				}
			}
		}
		return publicacionesOrdenadas;
	}
*/
	// cadena1.compareTo(cadena2)
	// Devuelve <0 si cadena1 < cadena2
	// Devuelve >0 si cadena1 > cadena2
	// Devuelve =0 si cadena1 == cadena2
	
	//Dado un autor, devolver una lista con sus publicaciones
	public List<Publicacion> obtenerPublicacionesAutor(String idA){
		List<Publicacion> lp = new ArrayList<>();
		//Recorremos todas las claves del mapaAutores, las claves son los ids de la publicaciones
		for(String idP: mapaAutores.keySet()) {
			//Por idP obtengo su lista de autores
			List<String> la = mapaAutores.get(idP);
			int pos = la.indexOf(idA);
			if(pos != -1) { //Hemos encontrado al autor entre los autores de la publicación
				Publicacion p = mapaPublicaciones.get(idP);
				lp.add(p);
			}
		}
		return lp;
	}
		
}

























