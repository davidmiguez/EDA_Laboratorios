package lab3;

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

	private Map<String, Autor> autMapa;

	
	public MapaAutores() { //O(1)
		//listaAutores = new ArrayList<Autor>();
		autMapa = new HashMap<>(); //O(1)
	}
	
	public void cargarFicheroAutores(String nombre) { //O(n)
		try {
			Scanner entrada = new Scanner(new FileReader(nombre)); //O(1)
			String linea = null; //O(1)
			while(entrada.hasNextLine()) { //n x O(1) -> O(n) donde n es el número de líneas del fichero
				linea = entrada.nextLine(); //O(1)
			
				String [] datos = linea.split(" # "); //O(1)
				Autor a = new Autor(datos[0], datos[1]); //O(1)
				
				autMapa.put(datos[0], a); //O(1)
			}
			entrada.close(); //O(1)
		} catch (IOException e) {
			e.printStackTrace(); //O(1)
		}
	}
	
	public void guardarFicheroAutores(String nom) { //O(n)
		try {
			PrintWriter salida = new PrintWriter(new File(nom)); //O(1)
			
			for(Autor a: autMapa.values()) { //n x O(1) -> O(n) donde n es el número de valores del mapaAutores
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
		autMapa.put(idA, a); //O(1)
	}

	
	public Autor obtenerAutor(String idA) { //O(1)
		return autMapa.get(idA); //O(1)
	}
	
	public void borrarAutor(String idA) { //O(1)
		autMapa.remove(idA); //O(1)
	}
}



















