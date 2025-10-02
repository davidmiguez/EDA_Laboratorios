package lab1;

import java.util.List;

public class MergeSort{
    //tienen que ser estaticos para poder usarlos
    public static void mergeSortPublicaciones(List<Publicacion> pLista){ //O(1) -> Coste constante
        if ( pLista == null || pLista.size() <= 1){ //O(1)
            return;
        }
        mergeSort(pLista,0, pLista.size()-1);//O(1)
    }

    private static void mergeSort(List<Publicacion> pLista, int pInicio, int pFin) { //O(1) -> Coste constante
		int mitad; //O(1)
		// el caso base seria pInicio>=pFin, si es el caso, no hace nada
		if(pInicio < pFin) {//O(1)
			mitad = pInicio + (pFin - pInicio) / 2; //O(1)
			//calcula la mitad
			mergeSort(pLista, pInicio, mitad); //O(1)
			//primera mitad
			mergeSort(pLista, mitad+1, pFin); //O(1)
			//segunda mitad
			merge(pLista, pInicio, mitad, pFin); //O(1)
			//juntar las dos
		}
	}

	private static void merge(List<Publicacion> pLista, int pInicio, int pMitad, int pFin) {//O(n) -> Coste lineal
		int t1 = pMitad - pInicio+1; //O(1)
		//tamano izq
		int t2 = pFin - pMitad;//O(1)
		//tamano derecha
		//arrays temporales para no sobreescribir y que se rompa
		Publicacion[] izq = new Publicacion[t1]; //O(1)
		//copia los elementos desde inicio hasta mitad
		Publicacion[] der = new Publicacion[t2]; //O(1)
		// desde mitad+1 hasta fin
		//anado los datos ordenados a los arrays temporales
		for(int i=0; i<t1 ; i++) { //n x O(1) -> O(n)
			izq[i] = pLista.get(pInicio+i);//O(1)
		}
		for(int j=0; j<t2 ; j++) { //n x O(1) -> O(n)
			der[j] = pLista.get(pMitad+1+j);//O(1)
		}	
		int i=0; //O(1)
		int j=0;//O(1)
		int k=pInicio;//O(1)
		while( i < t1 && j < t2) { // n x O(1) -> O(n)
			String tituloIzq = izq[i].getTitulo();//O(1)
	        String tituloDer = der[j].getTitulo();//O(1)
	        if (tituloIzq.compareTo(tituloDer) <= 0) {//O(1)
	            pLista.set(k, izq[i]);//O(1)
	            i++;//O(1)
	        } else {//O(1)
	            pLista.set(k, der[j]);//O(1)
	            j++;//O(1)
	        }
	        k++;//O(1)
	    }
	    //una de las listas se va a terminar antes que la otra, copiamos el sobrante
	    while (i < t1) { // n x O(1) -> O(n)  
	        pLista.set(k, izq[i]);//O(1)
	        i++;//O(1)
	        k++;//O(1)
	    }
	    while (j < t2) {
	        pLista.set(k, der[j]);//O(1)
	        j++;//O(1)
	        k++;//O(1)
		}
	}
}