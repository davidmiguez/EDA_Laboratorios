package lab1;
import java.util.Scanner;

import java.util.List;

public class Menu
{
    private static Menu miMenu=null;
    private GestionPublicacionesYAutores gestion;
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
            System.out.println("13. Cerrar programa.");  
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
                case 6:
                	mostrarPublicacionesCitadas(sn);
                    break;
               case 7:
            	    mostrarAutoresPublicacion(sn);
                    break;
                case 8:
                    mostrarPublicacionesAutor(sn);
                    break;
                case 9:
                    borrarPublicacion(sn);
                    break;
                case 10:
                	borrarAutor(sn);
                	break;
                case 11:
                	guardarFicheros(sn);
                	break;
                case 12:
                	obtenerPublicacionesOrdenadas(sn);
                	break;
                case 13:
                	System.out.println("Finalizando programa...\n");
                	salir=true;
                	break;
                default:
                    System.out.println("Solo numeros del 0 al 12\n");
            }
        }
    }
    
    private void cargarDatos() { //excepcion por si hay algun error al leer los ficheros
    	try {
            long inicio = System.currentTimeMillis();
            gestion.cargarDatos();
            long fin = System.currentTimeMillis();
            System.out.println("Datos cargados en " + (fin - inicio) + " ms.");
        } catch (Exception e) {
            System.err.println("Error al cargar: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void buscarPublicacion(Scanner sn) {
        System.out.print("Introduce el ID de la publicación: ");
        String id = sn.nextLine();
        Publicacion p = gestion.buscarPublicacion(id);
        if (p != null) {
            System.out.println("Título: " + p.getTitulo());
        } else {
            System.out.println("Publicación no encontrada.");
        }
    }
    
    private void insertarNuevaPublicacion(Scanner sn) {
    	System.out.print("Introduce el ID de la publicación: ");
        String id = sn.nextLine();
        System.out.print("Introduce el titulo de la publicación: ");
        String titulo = sn.nextLine();
        gestion.insertarNuevaPublicacion(id, titulo);
        System.out.println("Publicación añadida.");  
    }
    
    private void aniadirCitaAPublicacion(Scanner sn) {
    	System.out.print("Introduce el ID de la publicación que cita: ");
        String idPub = sn.nextLine();
        System.out.print("Introduce el ID de la publicación citada: ");
        String idCitada = sn.nextLine();
        gestion.aniadirCitaAPublicacion(idPub, idCitada);
        System.out.println("Cita añadida.");
    }
    
    private void aniadirAutorAPublicacion(Scanner sn) {
        System.out.print("Introduce el ID de la publicación: ");
        String idPub = sn.nextLine();
        System.out.print("Introduce el ID del autor: ");
        String idAutor = sn.nextLine();
        gestion.aniadirAutorAPublicacion(idPub, idAutor);
        System.out.println("Autor añadido a la publicación.");
    }
    

    private void aniadirNuevoAutor(Scanner sn) {
        System.out.print("Introduce el ID del autor: ");
        String id = sn.nextLine();
        System.out.print("Introduce el nombre del autor: ");
        String nombre = sn.nextLine();
        gestion.insertarNuevoAutor(id, nombre);
        System.out.println("Autor añadido.");
    }
    
    
    private void mostrarPublicacionesCitadas(Scanner sn) {
        System.out.print("Introduce el ID de la publicación: ");
        String idP = sn.nextLine();

        if (gestion.buscarPublicacion(idP) == null) { //por si no existe
            System.out.println("La publicación con ID '" + idP + "' no existe.");
            return;
        }
        List<Publicacion> citas = gestion.obtenerListaPublicacionesCitadas(idP);
        if (citas.isEmpty()) {
            System.out.println("La publicación no cita a ninguna otra.");
        } else {
            System.out.println("Publicaciones citadas por '" + idP + "':");
            for (Publicacion p : citas) {
                if (p != null) {
                    System.out.println(" - " + p.getIdentificador() + " - " + p.getTitulo());
                } else {
                    System.out.println(" - ID desconocido (la publicación citada no está en la base de datos)");
                }
            }
        }
    }
    
    private void mostrarAutoresPublicacion(Scanner sn) {
    	System.out.print("Introduce el ID de la publicacion: ");
    	String idP = sn.nextLine();
    	List<Autor> idAutores = gestion.obtenerAutoresDeLaPublicacion(idP);
    	if (idAutores.isEmpty()) {
    		System.out.println("La publicación no tiene autores registrados.");
    	}
    	else {
    		System.out.println("Autores de la publicación " + idP + ": ");
    		for (Autor idAutor: idAutores) {
    			System.out.println(idAutor);
    		}
    	}
    }
    
    private void mostrarPublicacionesAutor(Scanner sn) {
		System.out.print("Introduce el ID del autor: ");
		String idA = sn.nextLine();
		List<Publicacion> lPublis = gestion.obtenerPublicacionesAutor(idA);
		if (lPublis.isEmpty()) {
			System.out.println("El autor no tiene publicaciones.");
		}
		else {
			System.out.println("Publicaciones del autor con ID: " + idA);
			for (Publicacion p : lPublis) {
				if(p!=null) {
					System.out.println(" - " + p.getIdentificador() + " - " + p.getTitulo());
				}else {
					System.out.println("Publicacion  no encontrada."); //si no encuentra el id de la publicacion no ponemos el titulo ni nada
				}
			}
			System.out.println("El autor tiene un total de " + lPublis.size() + " publicaciones.");
		}
	}
    
    private void borrarPublicacion(Scanner sn) {
    	System.out.print("Introduce el ID de la publicación que deseas borrar: ");
    	String idP = sn.nextLine();
    	if(gestion.buscarPublicacion(idP)==null) {
    		System.out.println("Publicación no encontrada.");
    	}
    	else {
    		gestion.borrarPublicacion(idP);
    		System.out.println("Publicación borrada correctamente.");
    	}
    }
    
    private void borrarAutor(Scanner sn) {
    	System.out.print("Introduce el ID del autor que deseas borrar: ");
    	String idA = sn.nextLine();
    	if(gestion.obtenerAutor(idA)==null) {
    		System.out.println("Autor no encontrado.");
    	}
    	else {
    		gestion.borrarAutor(idA);
    		System.out.println("Autor borrado correctamente.");
    	}
    }
    
    private void guardarFicheros(Scanner sn) {
    	System.out.println("Introduce la ruta de la carpeta donde quieras guardar los ficheros: ");
    	System.out.println("Ejplo: C:/Users/DavidMiguez/Desktop/datosActualizados ");
    	String pCarpeta = sn.nextLine();
    	try {
    		long inicio = System.currentTimeMillis();
    		gestion.guardarDatos(pCarpeta);
    		long fin = System.currentTimeMillis();
        	System.out.println("Los ficheros guardados correctamente en " + (fin-inicio) + " ms.");
    	} catch (Exception e) {
    		System.err.println("Error al guardar los archivos: " + e.getMessage());
    	}

    }
    
    private void obtenerPublicacionesOrdenadas(Scanner sn) {
    	System.out.println("Ordenando publicaciones por titulo...");
    	long inicio = System.currentTimeMillis();
    	List<Publicacion> lOrdenadas = gestion.obtenerPublicacionesOrdenadas();
    	long fin = System.currentTimeMillis();
    	System.out.println("Las publicaciones se han ordenado en un total de " + (fin-inicio) + " ms.");
    	System.out.println("Total de publicaciones: " + lOrdenadas.size());
    	//preguntar cuantas quiere ver, para no imprimir todas.
    	System.out.println("Cuantas publicaciones desea ver? (limite " + lOrdenadas.size() + " ).");
    	int num = sn.nextInt();
    	if(lOrdenadas.size()<num) {
    		System.out.println("Has superado el limite.");
    	}
    	else {
    		System.out.println("Primeras " + num + " publicaciones ordenadas:");
        	for (int i=0 ; i<num ; i++) {
        		Publicacion p = lOrdenadas.get(i);
        		System.out.println( (i+1) + " - " + p.getTitulo() );  // tiene q ser i+1 porque se inicializa en i=0
        	}
    	}
    	
    }
    
}