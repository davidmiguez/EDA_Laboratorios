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
	
	public MapaAutores() {
		autMapa = new HashMap<>();
	}
	
	public void cargarFicheroAutores(String nombre) {
		try {
			Scanner entrada = new Scanner(new FileReader(nombre));
			String linea = null;
			while(entrada.hasNextLine()) {
				linea = entrada.nextLine();
				String [] datos = linea.split(" # ");
				Autor a = new Autor(datos[0], datos[1]);
				autMapa.put(datos[0], a);
				System.out.println(a.getIdentificador());
			}
			entrada.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void guardarFicheroAutores(String nom) {
		try {
			PrintWriter writer = new PrintWriter(new File(nom));
			for(Autor a: autMapa.values()) {
				writer.println(a.getIdentificador()+" # "+a.getNombre());
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Autor obtenerAutor(String idAutor) {
		return autMapa.get(idAutor);
	}
	
	public void borrarAutor(String idAutor) {
		autMapa.remove(idAutor);
	}
	
	public void aniadirNuevoAutor(String idAutor, String nombreAutor) {
		Autor a = new Autor(idAutor, nombreAutor);
		autMapa.put(idAutor, a);
	}
}




















