package lab3;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {

	HashMap<String, Integer> th;
	String[] keys;
	ArrayList<Integer>[] adjList;
	//       nombreA, idA
	HashMap<String, String> mapaNombres;

	public void crearGrafo(MapaAutores lista, MapaPublicaciones mp) {
		// Post: crea el grafo desde la lista de autores
		// Los nodos son nombres de autores

		// Paso 1: llenar th
		// COMPLETAR C�DIGO
		int i=0;
		th = new HashMap<String,Integer>();
		mapaNombres = new HashMap<String,String>();
		for(Autor a: lista.getMapaAutores().values()) {
			if(!th.containsKey(a.getNombre())) {
				th.put(a.getNombre(), i);
				i++;
				mapaNombres.put(a.getNombre(), a.getIdentificador());
				
			}		
		}
		// Paso 2: llenar keys�
		keys = new String[th.size()];
		for (String k : th.keySet()) {
			keys[th.get(k)] = k;
		}
		// Paso 3: llenar adjList�
		// COMPLETAR C�DIGO
		adjList = new ArrayList[th.keySet().size()];
		for(int j=0;j<adjList.length;j++) {
			String nom = keys[j];
			String idA = mapaNombres.get(nom);
			List<Publicacion> lPublicacionesAutor = mp.obtenerPublicacionesAutor(idA);
			for(Publicacion p: lPublicacionesAutor) {
				if(p!=null) {
					List<Publicacion> lPublicacionesCitadas = mp.obtenerListaPublicacionesCitadas(p.getIdentificador());
					for(Publicacion pc: lPublicacionesCitadas) {
						OrderedDoubleLinkedList<String> autoresP = mp.obtenerAutoresDeLaPublicacion(pc.getIdentificador());
						while(!autoresP.isEmpty()) {
							String sIdAutor = autoresP.removeFirst();
							Autor a = lista.obtenerAutor(sIdAutor);
							int pos = th.get(a.getNombre());
							adjList[j].add(pos);
						}
					}
				}
			}
		}
	}

	public void print() {
		for (int i = 0; i < adjList.length; i++) {
			System.out.print("Element: " + i + " " + keys[i] + " --> ");
			for (int k : adjList[i])
				System.out.print(keys[k] + " ### ");

			System.out.println();
		}
	}

	public boolean estanConectadosBIS(String a1, String a2) {
		Queue<Integer> porExaminar = new LinkedList<Integer>();
		long horaInicio = System.currentTimeMillis();
		int pos1 = th.get(a1);
		int pos2 = th.get(a2);
		boolean enc = false;
		boolean[] examinados = new boolean[th.size()];
		// COMPLETAR C�DIGO
		porExaminar.add(pos1);
		while(!enc && !porExaminar.isEmpty()) {
			int pos = porExaminar.remove();
			examinados[pos] = true;
			if(pos == pos2) {
				enc = true;
			}else {
				for(int p: adjList[pos]) {
					if(!examinados[p]) {
						porExaminar.add(p);
					}
				}
				
			}
		}
		long horaFin = System.currentTimeMillis();
		System.out.println("Tiempo de ejecución de estanConectado: "+(horaFin-horaInicio));
		return enc;

	}
	/*public boolean estanConectados(String a1, String a2) {
		Queue<Integer> porExaminar = new LinkedList<Integer>();

		int pos1 = th.get(a1);
		int pos2 = th.get(a2);
		boolean enc = false;
		boolean[] examinados = new boolean[th.size()];
		// COMPLETAR C�DIGO
		porExaminar.add(pos1);
		while(!enc && !porExaminar.isEmpty()) {
			int pos = porExaminar.remove();
			if(pos == pos2) {
				enc = true;
			}else {
				if(!examinados[pos]) {
					examinados[pos] = true;
					for(int p: adjList[pos]) {
						porExaminar.add(p);
					}
				}
			}
		}
		return enc;

	}*/

	public ArrayList<String> estanConectadosAL(String a1, String a2) {

		// COMPLETAR C�DIGO
		HashMap<String, String> mapa = new HashMap<>();
		mapa.put(a1, a1);
		Queue<Integer> porExaminar = new LinkedList<>();
		boolean []examinados = new boolean[th.size()];
		int pos1 = th.get(a1);
		int pos2 = th.get(a2);
		boolean enc = false;
		ArrayList<String> resultado = new ArrayList<>();
		porExaminar.add(pos1);
		while(!enc && !porExaminar.isEmpty()) {
			int pos = porExaminar.remove();
			if(pos == pos2) {
				enc = true;
			}else {
				String np = keys[pos];
				examinados[pos] = true;
				for(int p: adjList[pos]) {
					if(!examinados[p]) {
						porExaminar.add(p);
						String n = keys[p];
						mapa.put(n, np);
					}
				}
			}
 		}
		if(enc) {
			resultado.add(a2);
			String nombre2 = a2;
			String nombre1 = "";
			while(!nombre1.equals(a1)) {
				nombre1 = mapa.get(nombre2);
				resultado.add(nombre1);
				nombre2 = nombre1;
			}
		}
		return resultado;

	}

}
















