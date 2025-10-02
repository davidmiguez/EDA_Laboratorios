package lab1;

import java.util.List;

public class MergeSort{
    //tienen que ser estaticos para poder usarlos
    public static void mergeSortPublicaciones(List<Publicacion> pLista){
        if ( pLista == null || pLista.size() <= 1){
            return;
        }
        mergeSort(pLista,0, pLista.size()-1);
    }

    private static void mergeSort(List<Publicacion> pLista, int pInicio, int pFin) {
		int mitad;
		// el caso base seria pInicio>=pFin, si es el caso, no hace nada
		if(pInicio < pFin) {
			mitad = pInicio + (pFin - pInicio) / 2; //calcula la mitad
			mergeSort(pLista, pInicio, mitad); //primera mitad
			mergeSort(pLista, mitad+1, pFin); //segunda mitad
			merge(pLista, pInicio, mitad, pFin); //juntar las dos
		}
	}

	private static void merge(List<Publicacion> pLista, int pInicio, int pMitad, int pFin) {
		int t1 = pMitad - pInicio+1; //tamano izq
		int t2 = pFin - pMitad; //tamano derecha
		//arrays temporales para no sobreescribir y que se rompa
		Publicacion[] izq = new Publicacion[t1]; //copia los elementos desde inicio hasta mitad
		Publicacion[] der = new Publicacion[t2]; // desde mitad+1 hasta fin
		//anado los datos ordenados a los arrays temporales
		for(int i=0; i<t1 ; i++) {
			izq[i] = pLista.get(pInicio+i);
		}
		for(int j=0; j<t2 ; j++) {
			der[j] = pLista.get(pMitad+1+j);
		}	
		int i=0;
		int j=0;
		int k=pInicio;
		while( i < t1 && j < t2) {
			String tituloIzq = izq[i].getTitulo();
	        String tituloDer = der[j].getTitulo();
	        if (tituloIzq.compareTo(tituloDer) <= 0) {
	            pLista.set(k, izq[i]);
	            i++;
	        } else {
	            pLista.set(k, der[j]);
	            j++;
	        }
	        k++;
	    }
	    //una de las listas se va a terminar antes que la otra, copiamos el sobrante
	    while (i < t1) {
	        pLista.set(k, izq[i]);
	        i++;
	        k++;
	    }
	    while (j < t2) {
	        pLista.set(k, der[j]);
	        j++;
	        k++;
		}
	}
}