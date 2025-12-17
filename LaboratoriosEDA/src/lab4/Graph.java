package lab4;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

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
	
	public HashMap<String, Double> randomWalkPageRank(){
		HashMap<String, Double> mapa = new HashMap<>();
		Random r = new Random(System.currentTimeMillis());
		int suma = 0;
		for(String nombre: th.keySet()) {
			mapa.put(nombre, (double)0);
		}
		for(String nombre: th.keySet()) {
			Queue<String> porExaminar = new LinkedList<>();
			List<String> examinados = new ArrayList<>();
			porExaminar.add(nombre);
			examinados.add(nombre);
			boolean fin = false;
			int cont = mapa.get(nombre).intValue();
			cont++;
			suma++;
			mapa.put(nombre, (double)cont);
			
			while(!fin && !porExaminar.isEmpty()) {
				int num = r.nextInt(100); //0-99
				if(num>=85) {
					fin = true;
				}else {
					String n = porExaminar.remove();
					int i = th.get(n); //Para recorrer la lista de adyacencias de la posición i
					if(adjList[i].size()==0) {
						fin = true;
					}else {
						for(int j=0;j<adjList[i].size();j++) {
							String nombready = keys[adjList[i].get(j)];
							if(examinados.contains(nombready)) {
								fin = true;
							}else {
								porExaminar.add(nombready);
								examinados.add(nombready);
								cont = mapa.get(nombready).intValue();
								cont++;
								suma++;
								mapa.put(nombready, (double)cont);
								
							}
						}
					}
				}
			}
			
		}
		
		for(String nombre: mapa.keySet()) {
			double valor = mapa.get(nombre) / suma;
			mapa.put(nombre, valor);
		}
		return mapa;
	}

	public HashMap<String, Double> pageRank(){
		HashMap<String, Double> mapa = new HashMap<>();
		double valor = (double)1/th.keySet().size();
		double d = 0.85;
		for(String n: th.keySet()) {
			mapa.put(n, valor);
		}
		double resul0=1, resul1=0;
		boolean fin = false;
		do{
			for(String n: mapa.keySet()) {
				double suma = 0;
				int i = th.get(n);
				for(int j: adjList[i]) {
					String nombready = keys[j];
					suma = suma + mapa.get(nombready)/adjList[j].size();
				}
				double prob = (1-d)/th.keySet().size() + d* suma;
				resul1 = resul1 + Math.abs(mapa.get(n) - prob);
				mapa.put(n, prob);
			}
			if(Math.abs(resul0-resul1)>=0.0001) {
				resul0 = resul1;
				resul1 = 0;
			}else {
				fin = true;
			}
	
		}while(!fin);
		
		
		return mapa;
	}
	
	
	
	
//	public HashMap<String, Double> calcularPageRank() {
//		boolean trace = false; // tracing the pagerank algorithm
//		boolean damping = true; // tracing the pagerank algorithm
//			
//	       // COMPLETAR CÓDIGO
//		HashMap<String, Double> m = new HashMap<>();
//		int n = enlaces.size();
//		
//		double umbral = 0.0001;
//		double d = 0.85;
//		/*double suma1 = 0;
//		for(String w : enlaces.keySet()) {
//			m.put(w, (double)1/n);
//			suma1 = suma1 + (double)1/n;
//		}*/
//		for(String w : enlaces.keySet()) {
//			m.put(w, (double)1/n);
//		}
//		double suma1 = 1;
//		boolean fin = false;
//		while(!fin) {
//			double suma2 = 0;
//			for(String w: m.keySet()) {
//				//Recorro los enlaces de w
//				double resul = 0;
//				for(String e: enlaces.get(w)) {
//					resul = resul + m.get(e)/enlaces.get(e).size();
//					
//				}
//				resul = (1-d)/n+d*resul;
//				suma2 = suma2 + resul;
//				m.put(w, resul);
//			}
//			
//			if(suma1-suma2<umbral) {
//				fin = true;
//			}else {
//				suma1 = suma2;
//			}
//		}
//		return m;
//
//       }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
















