package lab4;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MapaAutores {
	//private ArrayList<Autor> listaAutores;
	//private List<Autor> listaAutores;
	private Map<String, Autor> mapaAutores;
	//¿Creamos un mapa que relacione cada autor con sus publicacione?
	
	public MapaAutores() { //O(1)
		//listaAutores = new ArrayList<Autor>();
		mapaAutores = new HashMap<>(); //O(1)
	}
	
	
	public Map<String, Autor> getMapaAutores() {
		return mapaAutores;
	}


	public void cargarFicheroAutores(String nombre) { //O(n)
		try {
			Scanner entrada = new Scanner(new FileReader(nombre)); //O(1)
			String linea = null; //O(1)
			while(entrada.hasNextLine()) { //n x O(1) -> O(n) donde n es el número de líneas del fichero
				linea = entrada.nextLine(); //O(1)
				//linea = "Q95147729 # A Einstein"
				String [] datos = linea.split(" # "); //O(1)
				Autor a = new Autor(datos[0], datos[1]); //O(1)
				//listaAutores.add(a);
				mapaAutores.put(datos[0], a); //O(1)
			}
			entrada.close(); //O(1)
		} catch (IOException e) {
			e.printStackTrace(); //O(1)
		}
	}
	
	public void guardarFicheroAutores(String nom) { //O(n)
		try {
			PrintWriter salida = new PrintWriter(new File(nom)); //O(1)
			//Recorremos la lista de autores
			//for(Autor a: listaAutores){
			for(Autor a: mapaAutores.values()) { //n x O(1) -> O(n) donde n es el número de valores del mapaAutores
				salida.println(a.getIdentificador()+" # "+a.getNombre()); //O(1)
			}
			salida.flush(); //O(1)
			salida.close(); //O(1)
		} catch (IOException e) {
			e.printStackTrace(); //O(1)
		}
	}
	
	//Añadir un nuevo autor (dados su identificador y nombre)
	public void aniadirNuevoAutor(String idA, String nombreA) { //O(1)
		Autor a = new Autor(idA, nombreA); //O(1)
		//listaAutores.add(a);
		mapaAutores.put(idA, a); //O(1)
	}
	
	//Método que devuelve la posición de la lista en la que se encuentra el autor cuyo id se recibe por parámetro
	/*public int buscarAutor(String idA) {
		int pos=0;
		boolean enc = false;
		Autor a = null;
		while(!enc && pos<listaAutores.size()) {
			a = listaAutores.get(pos);
			if(a.getIdentificador().equals(idA)) {
				enc = true;
			}else {
				pos++;
			}
		}
		if(!enc) {
			return -1;
		}else {
			return pos;
		}
	}
	
	//Borrar un autor
	public void borrarAutor(String idA) {
		int pos = buscarAutor(idA);
		if(pos!=-1) {
			listaAutores.remove(pos);
		}
	}*/
	
	public Autor obtenerAutor(String idA) { //O(1)
		return mapaAutores.get(idA); //O(1)
	}
	
	public void borrarAutor(String idA) { //O(1)
		mapaAutores.remove(idA); //O(1)
	}
}




















