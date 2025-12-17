package lab4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GestionPublicacionesYAutores {
	private MapaPublicaciones mp;
	private MapaAutores ma;
	
	public GestionPublicacionesYAutores() { //O(n)
		mp = new MapaPublicaciones(); //O(1)
		ma = new MapaAutores(); //O(1)
		mp.cargarFicheroAutoresPorPublicacion("Datuak/Datuak/publications-authors-all-final.txt"); //O(n)
		System.out.println("Cargados los autores por publicación");
		mp.cargarFicheroPublicacionesCitadas("Datuak/Datuak/publications-citedPubs-all.txt"); //O(n)
		System.out.println("Cargadas las publicaciones citadas");
		mp.cargarPublicacionesDeFichero("Datuak/Datuak/publications-titles-all.txt"); //O(n)
		System.out.println("Cargadas las publicaciones");
		ma.cargarFicheroAutores("Datuak/Datuak/authors-name-all.txt"); //O(n)
		System.out.println("Cargados los autores");
	}
	
	public List<Autor> obtenerAutoresDeLaPublicacion(String idP){ //O(n)
		//CAMBIO
		//List<String> la = mp.obtenerAutoresDeLaPublicacion(idP); //O(1)
		OrderedDoubleLinkedList<String> la = mp.obtenerAutoresDeLaPublicacion(idP); //O(1)
		List<Autor> lAutores = new ArrayList<>(); //O(1)
		//CAMBIO
		/*for(String idA: la) { //n x O(1) -> O(n) donde n es el número de elementos de la
			Autor a = ma.obtenerAutor(idA); //O(1)
			lAutores.add(a); //O(1)
		}*/
		//CAMBIO
		Iterator<String> it = la.iterator();
		while(it.hasNext()) {
			String idA = it.next();
			Autor a = ma.obtenerAutor(idA); //O(1)
			lAutores.add(a); //O(1)
		}
		return lAutores;
	}
	
//	public HashMap<String, Double> randomWalkPageRank(){
//		HashMap<String, Double> mapa = new HashMap<>();
//		
//		for(String idA: ma.getMapaAutores().keySet()) {
//			Queue<String> porExaminar = new LinkedList<>();
//			List<String> examinados = new ArrayList<>();
//			porExaminar.add(idA);
//			examinados.add(idA);
//			boolean fin = false;
//			while(!fin && !porExaminar.isEmpty()) {
//				String idautor = porExaminar.remove();
//				List<Publicacion> lp = mp.obtenerPublicacionesAutor(idautor);
//				int i=0;
//				while(!fin && i<lp.size()) {
//					Publicacion p = lp.get(i);
//					List<Publicacion> lpc = mp.obtenerListaPublicacionesCitadas(p.getIdentificador());
//					if(lpc.isEmpty()){
//						fin = true;
//					}else {
//						for(Publicacion publi: lpc) {
//							//Obtenemos la lista de autores de la publicación citada
//							List<String> lista = mp.obtenerAutoresDeLaPublicacion(publi) 
//						}
//					}
//				}
//			}
//		}
//		return mapa;
//	}
	
	public static void main(String[] args) {
		GestionPublicacionesYAutores gp = new GestionPublicacionesYAutores();
		List<Autor> l = gp.obtenerAutoresDeLaPublicacion("Q24310331");
		if(l == null)
			System.out.println("No hay");
		else
			System.out.println(l);
	}
}













