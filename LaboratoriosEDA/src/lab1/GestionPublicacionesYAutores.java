package lab1;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GestionPublicacionesYAutores {
	private MapaPublicaciones mapaP;
	private MapaAutores mapaA;
	
	public GestionPublicacionesYAutores() { //O(n)-> Coste lineal
		mapaP = new MapaPublicaciones(); //O(1)
		mapaA = new MapaAutores(); //O(1)
	}
	
	public List<Autor> obtenerAutoresDeLaPublicacion(String idP){ //O(n)-> Coste lineal
		List<String> la = mapaP.obtenerAutoresPublicacion(idP); //O(1)
		List<Autor> lAut = new ArrayList<>(); //O(1)
		for(String idA: la) { //n x O(1) -> O(n) donde n es el número de elementos de la
			Autor a = mapaA.obtenerAutor(idA); //O(1)
			lAut.add(a); //O(1)
		}
		return lAut;
    } 
	
	
	
	public void cargarDatos() {
        mapaA.cargarFicheroAutores("datos/authors-name-all.txt");
        mapaP.cargarPublicacionesDeFichero("datos/publications-titles-all.txt");
        mapaP.cargarFicheroAutoresPorPublicacion("datos/publications-authors-all-final.txt");
        mapaP.cargarFicheroPublicacionesCitadas("datos/publications-citedPubs-reducido.txt");
    }
	
	public Publicacion buscarPublicacion(String id) {
        return mapaP.buscarPublicacion(id);
    }

    public void insertarNuevaPublicacion(String id, String titulo) {
        mapaP.insertarNuevaPublicacion(id, titulo);
    }

    public void aniadirCitaAPublicacion(String idPub, String idCitada) {
        mapaP.aniadirCitaAPublicacion(idPub, idCitada);
    }

    public void aniadirAutorAPublicacion(String idPub, String idAutor) {
        mapaP.aniadirAutorAPublicacion(idPub, idAutor);
    }

    public void insertarNuevoAutor(String id, String nombre) {
        mapaA.aniadirNuevoAutor(id, nombre);
    }

    public List<String> obtenerAutoresPublicacion(String idP) {
        return mapaP.obtenerAutoresPublicacion(idP);
    }
    
    public List<Publicacion> obtenerListaPublicacionesCitadas(String idP) {
        return mapaP.obtenerListaPublicacionesCitadas(idP);
    }
    
    public List<String> obtenerListaPublicacionesCitadasV2(String idP) {
        return mapaP.obtenerListaPublicacionesCitadasV2(idP);
    }

    public List<Publicacion> obtenerPublicacionesAutor(String idAutor) {
        return mapaP.obtenerPublicacionesAutor(idAutor);
    }

    public List<Publicacion> obtenerPublicacionesOrdenadas() {
        return mapaP.obtenerPublicacionesOrdenadas();
    }

    public void borrarPublicacion(String id) {
        mapaP.borrarPublicacion(id);
    }

    public void borrarAutor(String id) {
        mapaA.borrarAutor(id);
    }
    
    public Autor obtenerAutor(String pIdA) {
    	return mapaA.obtenerAutor(pIdA);
    }

    public void guardarDatos(String pCarpeta) {
    	String dir = pCarpeta;
    	if (!pCarpeta.endsWith("/") && !pCarpeta.endsWith("\\")) {//asegurar que la ruta al menos sea con el formato correcto
            dir = pCarpeta + "/";
        }
    	File directorio = new File(dir);
    	directorio.mkdirs();
        mapaP.guardarPublicacionesEnFichero(dir+"salida-publicaciones.txt");
        mapaP.guardarFicheroAutoresPorPublicacion(dir+"salida-autores-pub.txt");
        mapaP.guardarFicheroPublicacionesCitadas(dir+"salida-citas.txt");
        mapaA.guardarFicheroAutores(dir+"salida-autores.txt");
    }
	
}
