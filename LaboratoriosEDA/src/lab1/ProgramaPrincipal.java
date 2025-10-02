package lab1;
import java.util.List;

public class ProgramaPrincipal {
	public static void main(String[] args) {
		long milisInicio = System.currentTimeMillis();
		MapaAutores la = new MapaAutores();
		la.cargarFicheroAutores("datos/authors-name-all.txt");
		long milisFin = System.currentTimeMillis();
		
		MapaPublicaciones mp = new MapaPublicaciones();
		mp.cargarPublicacionesDeFichero("datos/publications-titles-all.txt");
	//	mp.cargarPublicacionesDeFichero("Datuak/Datuak/publicaciones.txt");
		List<Publicacion> po = mp.obtenerPublicacionesOrdenadas();
		System.out.println("Publicaciones ordenadas");
		System.out.println(po.get(0).getIdentificador());
		System.out.println(po.get(po.size()-1).getIdentificador());
		System.out.println("Tiempo de ejecuciÃ³n: "+(milisFin-milisInicio)+" milisegundos");
		
		
	}
}
//