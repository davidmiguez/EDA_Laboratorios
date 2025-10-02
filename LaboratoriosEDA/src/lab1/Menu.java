package lab1;
import java.util.Scanner;

public class Menu
{
    private static Menu miMenu=null;
    private GestionPublicacionesYAutores gestion;
    private MapaPublicaciones mapaP;
    private MapaAutores mapaA;
    private Menu(){
    	gestion = new GestionPublicacionesYAutores();
    }

    public static Menu getMenu()
    {
        if(miMenu==null)
        {
        	miMenu=new Menu();
        }
        return miMenu;
    }

    public void mostrarMenu()
    {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion;
        while(!salir)
        {
            System.out.println("\n################################################");
            System.out.println("\t\t Menu Principal - LAB 1 \n");
            System.out.println("0. Cargar todos los datos desde ficheros");
            System.out.println("1. Buscar publicación por ID");
            System.out.println("2. Insertar nueva publicación");
            System.out.println("3. Añadir cita a una publicación");
            System.out.println("4. Añadir nuevo autor");
            System.out.println("5. Añadir autor a una publicación");
            System.out.println("6. Obtener publicaciones citadas por una publicación");
            System.out.println("7. Obtener autores de una publicación");
            System.out.println("8. Obtener publicaciones de un autor");
            System.out.println("9. Borrar una publicación");
            System.out.println("10. Borrar un autor");            
            System.out.println("11. Guardar todo en ficheros actualizados");
            System.out.println("12. Obtener lista de publicaciones ordenadas (por título)");         
            System.out.print("---> ");
            
            opcion=sn.nextInt();
            sn.nextLine();
            
            switch (opcion)
            {
                case 0:
                    cargarDatos();
                    break;
                case 1:
                    buscarPublicacion(sn);
                    break;
                case 2:
                    insertarNuevaPublicacion(sn);
                    break;
                case 3:
                	aniadirCitaAPublicacion(sn);
                    break;
                case 4:
                	aniadirNuevoAutor(sn);
                    break;
                case 5:
                	aniadirAutorAPublicacion(sn);
                    break;
     /*           case 6:
                	obtenerListaPublicacionesCitadas(sn);
                    break;
                case 7:
                    datos.crearFichero();
                    System.out.println("El contenido se ha escrito correctamente");
                    break;
                case 8:
                    for(Actor s:HM_Actor.getListaActoresOrdenada()){System.out.println(s.getNombre());}
                    break;
                case 9:
                    exit=true;
                    break;*/
                default:
                    System.out.println("Solo numeros del 0 al 12\n");
            }
        }
    }
    
    private void cargarDatos() {
    	try {
            long inicio = System.currentTimeMillis();
            gestion.cargarDatos();
            long fin = System.currentTimeMillis();
            System.out.println("✅ Datos cargados en " + (fin - inicio) + " ms.");
        } catch (Exception e) {
            System.err.println("❌ Error al cargar: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void buscarPublicacion(Scanner sn) {
        System.out.print("ID de la publicación: ");
        String id = sn.nextLine();
        Publicacion p = gestion.buscarPublicacion(id);
        if (p != null) {
            System.out.println("Título: " + p.getTitulo());
        } else {
            System.out.println("❌ Publicación no encontrada.");
        }
    }
    
    private void insertarNuevaPublicacion(Scanner sn) {
    	System.out.print("ID de la publicación: ");
        String id = sn.nextLine();
        System.out.print("Titulo de la publicación: ");
        String titulo = sn.nextLine();
        gestion.insertarNuevaPublicacion(id, titulo);
        System.out.println("Publicación añadida.");  
    }
    
    private void aniadirCitaAPublicacion(Scanner sn) {
    	System.out.print("ID de la publicación que cita: ");
        String idPub = sn.nextLine();
        System.out.print("ID de la publicación citada: ");
        String idCitada = sn.nextLine();
        gestion.aniadirCitaAPublicacion(idPub, idCitada);
        System.out.println("✅ Cita añadida.");
    }
    
    private void aniadirAutorAPublicacion(Scanner sn) {
        System.out.print("ID de la publicación: ");
        String idPub = sn.nextLine();
        System.out.print("ID del autor: ");
        String idAutor = sn.nextLine();
        gestion.aniadirAutorAPublicacion(idPub, idAutor);
        System.out.println("✅ Autor vinculado a la publicación.");
    }
    

    private void aniadirNuevoAutor(Scanner sn) {
        System.out.print("ID del autor: ");
        String id = sn.nextLine();
        System.out.print("Nombre del autor: ");
        String nombre = sn.nextLine();
        gestion.insertarNuevoAutor(id, nombre);
        System.out.println("✅ Autor añadido.");
    }
    
    
    
}