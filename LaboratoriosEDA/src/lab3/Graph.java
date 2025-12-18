package lab3;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import lab4.MapaAutores;
import lab4.Publicacion;

public class Graph {

	HashMap<String, Integer> th;
	String[] keys;
	ArrayList<Integer>[] adjList;
	HashMap<String, String> mapaNombres;

	public void crearGrafo(MapaAutores mapaA, lab4.MapaPublicaciones mapaP) {
	    int i = 0;
	    th = new HashMap<String, Integer>();
	    mapaNombres = new HashMap<String, String>();
	    for (lab4.Autor a : mapaA.getMapaAutores().values()) {
	        if (!th.containsKey(a.getNombre())) {
	            th.put(a.getNombre(), i);
	            i++;
	            mapaNombres.put(a.getNombre(), a.getIdentificador());
	        }
	    }
	    keys = new String[th.size()];
	    for (String k : th.keySet()) {
	        keys[th.get(k)] = k;
	    }
	    adjList = new ArrayList[th.keySet().size()];
	    
	    //ver cuanto tiempo tarda por cada 1000 autores
	    for (int j = 0; j < adjList.length; j++) {
	        adjList[j] = new ArrayList<Integer>(); 
	        if (j % 1000 == 0) {
	            System.out.println("Procesando autor " + j + " de " + adjList.length + "...");
	        }
	        
	        String nom = keys[j];
	        String idA = mapaNombres.get(nom);
	        
	        List<Publicacion> lPublicacionesAutor = mapaP.obtenerPublicacionesAutor(idA);

	        if (lPublicacionesAutor != null) {
	            for (Publicacion p : lPublicacionesAutor) {
	                if (p != null) {
	                    List<Publicacion> lPublicacionesCitadas = mapaP.obtenerListaPublicacionesCitadas(p.getIdentificador());
	                    if (lPublicacionesCitadas != null) {
	                        for (Publicacion pc : lPublicacionesCitadas) {

	                            if (pc != null) {
	                                lab4.OrderedDoubleLinkedList<String> autoresP = mapaP.obtenerAutoresDeLaPublicacion(pc.getIdentificador());
	                                if (autoresP != null) {
	                                    while (!autoresP.isEmpty()) {
	                                        String sIdAutor = autoresP.removeFirst();
	                                        lab4.Autor a = mapaA.obtenerAutor(sIdAutor);
	                                        if (a != null && th.containsKey(a.getNombre())) {
	                                            int pos = th.get(a.getNombre());
	                                            adjList[j].add(pos);
	                                        }
	                                    }
	                                }
	                            }
	                        }
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
		if (!th.containsKey(a1) || !th.containsKey(a2)) {
	        return false;
	    }
		Queue<Integer> porExaminar = new LinkedList<Integer>();
		long horaInicio = System.currentTimeMillis();
		int pos1 = th.get(a1);
		int pos2 = th.get(a2);
		boolean enc = false;
		boolean[] examinados = new boolean[th.size()];
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
	
	public ArrayList<String> estanConectadosAL(String a1, String a2) {
		if (!th.containsKey(a1) || !th.containsKey(a2)) {
	        return new ArrayList<String>();
	    }

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
















