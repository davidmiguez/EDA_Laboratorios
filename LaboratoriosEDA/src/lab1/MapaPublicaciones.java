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
	private Map<String, Publicacion> mapaPublicaciones; 
	private Map<String, ArrayList<String>> mapaCitas; 
	private Map<String, ArrayList<String>> mapaAutores; 
														

	public MapaPublicaciones() { //O(1) -> Coste constante
		mapaPublicaciones = new HashMap<>();
		mapaCitas = new HashMap<>();
		mapaAutores = new HashMap<>();
	}

	public void cargarFicheroAutoresPorPublicacion(String nom) { //O(n) -> Coste lineal
		try {
			Scanner entrada = new Scanner(new FileReader(nom)); //O(1)
			String linea; //O(1)
			while (entrada.hasNextLine()) { // n x O(1) -> O(n)  donde n es el número de líneas que tenga el fichero
				linea = entrada.nextLine(); //O(1)
				String[] datos = linea.split(" # "); //O(1)
				String idP = datos[0]; //O(1)
				String idA = datos[1]; //O(1)
				if (!mapaAutores.containsKey(idP)) { //O(1)xO(1) -> O(1)
					mapaAutores.put(idP, new ArrayList<>()); //O(1)
				}
				mapaAutores.get(idP).add(idA); //O(1)
			}
			entrada.close(); //O(1)
		} catch (IOException e) { //O(1)
			e.printStackTrace();
		}
	}
	
	public void guardarFicheroAutoresPorPublicacion(String nom) { //O(n) -> Coste lineal
		try {
			PrintWriter writer = new PrintWriter(new File(nom));  //O(1)
			for(String idP: mapaAutores.keySet()) { //n x O( m) -> O(nxm) -> O(n) donde n es el número de claves del mapaAutores
				for(String idA: mapaAutores.get(idP)) { //m x O(1) -> O(m) donde m es el número de elementos 
					writer.println(idP+" # "+idA);  //O(1)
				}
			}
			writer.flush(); //O(1)
			writer.close(); //O(1)
		} catch (IOException e) { //O(1)
			e.printStackTrace();
		}
	}

	public void cargarFicheroPublicacionesCitadas(String nom) { //O(n) -> Coste lineal
		try {
			Scanner entrada = new Scanner(new FileReader(nom));//O(1)
			String linea;//O(1)
			while (entrada.hasNextLine()) { // n x O(1) -> O(n)  donde n es el número de líneas que tenga el fichero
				linea = entrada.nextLine();//O(1)
				String datos[] = linea.split(" # "); //O(1)
				if (!mapaCitas.containsKey(datos[0])) {//O(1)
					mapaCitas.put(datos[0], new ArrayList<>());//O(1)
				}
				mapaCitas.get(datos[0]).add(datos[1]);//O(1)
			}
			entrada.close();//O(1)
		} catch (IOException e) {//O(1)
			e.printStackTrace();
		}
	}
	
	public void guardarFicheroPublicacionesCitadas(String nom) { //O(n) -> Coste lineal
		try {
			PrintWriter writer = new PrintWriter(new File(nom));//O(1)
			for(String idP: mapaCitas.keySet()) {  //n x O( m) -> O(nxm) -> O(n) donde n es el número de claves del mapaAutores
				for(String idC: mapaCitas.get(idP)) { //m x O(1) -> O(m) donde m es el número de elementos 
					writer.println(idP+" # "+idC);//O(1)
				}
			}
			writer.flush();//O(1)
			writer.close();//O(1)
		} catch (IOException e) {//O(1)
			e.printStackTrace();
		}
	}

	public void cargarPublicacionesDeFichero(String nombre) {//O(n) -> Coste lineal
		try {
			Scanner entrada = new Scanner(new FileReader(nombre));//O(1)
			String linea = null;//O(1)
			while (entrada.hasNextLine()) { // n x O(1) -> O(n)  donde n es el número de líneas que tenga el fichero
				linea = entrada.nextLine(); //O(1)
				String[] datos = linea.split(" # "); //O(1)
				Publicacion p = new Publicacion(datos[0], datos[1]);//O(1)
				mapaPublicaciones.put(datos[0], p);//O(1)
			}
			entrada.close();//O(1)
		} catch (IOException e) {//O(1)
			e.printStackTrace();
		}
	}
	
	public void guardarPublicacionesEnFichero(String nom) { //O(n) -> Coste lineal
		try {
			PrintWriter writer = new PrintWriter(new File(nom)); //O(1)
			for(Publicacion p: mapaPublicaciones.values()) { // n x O(1) -> O(n) donde n es el número de valores del mapaPublicaciones
				writer.println(p.getIdentificador()+" # "+p.getTitulo()); //O(1)
			}
			writer.flush(); //O(1)
			writer.close(); //O(1)
		} catch (IOException e) { //O(1)
			e.printStackTrace();
		}
	}

	public Publicacion buscarPublicacion(String idP) { //O(1) -> Coste constante
		Publicacion p = mapaPublicaciones.get(idP); //O(1)
		return p;
	}

	public void insertarNuevaPublicacion(String idP, String tituloP) { //O(1) -> Coste constante
		Publicacion p = new Publicacion(idP, tituloP); //O(1)
		mapaPublicaciones.put(idP, p); //O(1)
	}

	public void aniadirCitaAPublicacion(String idP, String idPCitada) { //O(1) -> Coste constante
		if (mapaCitas.containsKey(idP)) { //O(1)
			mapaCitas.get(idP).add(idPCitada); //O(1)
		}
		else {
			mapaCitas.put(idP, new ArrayList<>());//O(1)
			mapaCitas.get(idP).add(idPCitada); //O(1)
		}
	}

	public void aniadirAutorAPublicacion(String idP, String idA) { //O(1) -> Coste constante
		if (!mapaAutores.containsKey(idP)) {  //O(1)
			mapaAutores.put(idP, new ArrayList<>());  //O(1)
		}
		mapaAutores.get(idP).add(idA);  //O(1)
	}

	public List<Publicacion> obtenerListaPublicacionesCitadas(String idP) { //O(n) -> Coste lineal
		List<Publicacion> lp = new ArrayList<>(); //O(1)
		ArrayList<String> lCitas = mapaCitas.get(idP);  //O(1)
		for (String id : lCitas) { //n x O(1 ) -> O(n) donde n es número de elementos de lCita
			Publicacion p = mapaPublicaciones.get(id); //O(1)
			lp.add(p); //O(1)
		}
		return lp;
	}

	public List<String> obtenerListaPublicacionesCitadasV2(String idP) { //O(1) -> Coste constante
		ArrayList<String> lCitas = mapaCitas.get(idP); //O(1) 
		return lCitas;
	}

	public List<String> obtenerAutoresPublicacion(String idP){ //O(1) -> Coste constante

		List<String> la = mapaAutores.get(idP);	//O(1) 
		return la;
	}
	
	public void borrarPublicacion(String idP) { //O(1) -> Coste constante
		mapaPublicaciones.remove(idP); //O(1)
	}

	//he hecho que el mergeSort sea una clase distinta para que este mas limpio
	public List<Publicacion> obtenerPublicacionesOrdenadas(){ //O(1) -> Coste constante
		List<Publicacion> publicacionesOrdenadas = new ArrayList<>(mapaPublicaciones.values()); //O(1) 
		//copia para no cargarnos la lista original
		MergeSort.mergeSortPublicaciones(publicacionesOrdenadas);//O(1)
		//llamada a mergeSort con inicio=0 y fin=size-1
		return publicacionesOrdenadas;
	}

	public List<Publicacion> obtenerPublicacionesAutor(String idA){ //O(n) -> Coste lineal
		List<Publicacion> lp = new ArrayList<>(); //O(1)
		for(String idP: mapaAutores.keySet()) { //n x O(1) -> O(n) donde n es el número de claves del mapaAutores

			//por cada idP obtener autores
			List<String> la = mapaAutores.get(idP); //O(1)
			int pos = la.indexOf(idA); //O(1)
			if(pos!= -1) { //O(1)
				Publicacion p = mapaPublicaciones.get(idP); //O(1)
				lp.add(p); //O(1)
			}
		}
		return lp;
	}	
}



