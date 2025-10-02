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

public class MapaAutores {
	private Map<String, Autor> autMapa;
	
	public MapaAutores() { //O(1)
		autMapa = new HashMap<>();
	}
	
	public void cargarFicheroAutores(String nombre) { //O(n)-> Coste lineal
		try {
			Scanner entrada = new Scanner(new FileReader(nombre)); //O(1)
			String linea = null; //O(1)
			while(entrada.hasNextLine()) { //n x O(1) -> O(n) donde n es el número de líneas del fichero
				linea = entrada.nextLine(); //O(1)
				String [] datos = linea.split(" # "); //O(1)
				Autor a = new Autor(datos[0], datos[1]); //O(1)
				autMapa.put(datos[0], a); //O(1)
				System.out.println(a.getIdentificador()); //O(1)
			}
			entrada.close(); //O(1)
		} catch (IOException e) {
			e.printStackTrace(); //O(1)
		}
	}
	
	public void guardarFicheroAutores(String nom) { //O(n)-> Coste lineal
		try {
			PrintWriter writer = new PrintWriter(new File(nom)); //O(1)
			for(Autor a: autMapa.values()) { //n x O(1) -> O(n) donde n es el número de valores del mapaAutores

				writer.println(a.getIdentificador()+" # "+a.getNombre()); //O(1)
			}
			writer.flush(); //O(1)
			writer.close(); //O(1)
		} catch (IOException e) {
			e.printStackTrace(); //O(1)
		}
	}
	
	public Autor obtenerAutor(String idAutor) { //O(1)-> Coste constante
		return autMapa.get(idAutor);
	}
	
	public void borrarAutor(String idAutor) { //O(1)-> Coste constante
		autMapa.remove(idAutor);
	}
	
	public void aniadirNuevoAutor(String idAutor, String nombreAutor) { //O(1)-> Coste constante
		Autor a = new Autor(idAutor, nombreAutor); //O(1)
		autMapa.put(idAutor, a); //O(1)
	}
}




















