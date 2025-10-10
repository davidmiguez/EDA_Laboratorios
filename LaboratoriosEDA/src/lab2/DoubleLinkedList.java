package lab2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<T> implements ListADT<T> {

	// Atributos
	protected Node<T> last;  // apuntador al �ltimo
	protected String descr;  // descripci�n
	protected int count;

	// Constructor
	public DoubleLinkedList() {
        	last = null;
		descr = "";
		count = 0;
	}
	
	public void setDescr(String nom) {
	  descr = nom;
	}

	public String getDescr() {
	  return descr;
	}

	public T removeFirst() {
	// Elimina el primer elemento de la lista
        // Precondici�n: 
		// COMPLETAR EL CODIGO Y CALCULAR EL COSTE

	}

	public T removeLast() {
	// Elimina el �ltimo elemento de la lista
        // Precondici�n: 
			// COMPLETAR EL CODIGO Y CALCULAR EL COSTE

		   }


	public T remove(T elem) {
	//Elimina un elemento concreto de la lista
		// COMPLETAR EL CODIGO Y CALCULAR EL COSTE
	}
	
	public void removeAll(T elem) {
	//Elimina todas las apariciones de un elemento de la lista
		// COMPLETAR EL CODIGO Y CALCULAR EL COSTE
	}

	public T first() {
	//Da acceso al primer elemento de la lista
	      // COMPLETAR EL CODIGO Y CALCULAR EL COSTE
	}

	public T last() {
	//Da acceso al �ltimo elemento de la lista
	      // COMPLETAR EL CODIGO Y CALCULAR EL COSTE
	}

	public DoubleLinkedList<T> clone(){
		// Devuelve una copia de la lista (no duplica el puntero)
		// COMPLETAR EL CODIGO Y CALCULAR EL COSTE
	} 


	public boolean contains(T elem) {
	//Determina si la lista contiene un elemento concreto
		      if (isEmpty())
		          return false;
		// COMPLETAR EL CODIGO Y CALCULAR EL COSTE
		      
	}

	public T find(T elem) {
	//Determina si la lista contiene un elemento concreto, y develve su referencia, null en caso de que no est�
		// COMPLETAR EL CODIGO Y CALCULAR EL COSTE

	}

	public boolean isEmpty(){ 
	//Determina si la lista est� vac�a
	 // COMPLETAR EL CODIGO Y CALCULAR EL COSTE
}
	
	public int size(){ 
	//Determina el n�mero de elementos de la lista
	 // COMPLETAR EL CODIGO Y CALCULAR EL COSTE
}
	
	/** Return an iterator to the stack that iterates through the items . */ 
	public Iterator<T> iterator() { return new ListIterator(); } 

	   // an iterator, doesn't implement remove() since it's optional 
	   private class ListIterator implements Iterator<T> { 

		// COMPLETAR EL CODIGO Y CALCULAR EL COSTE



	   } // private class
		
		
         public void visualizarNodos() {
			System.out.println(this.toString());
		}

		@Override
		public String toString() {
			String result = new String();
			Iterator<T> it = iterator();
			while (it.hasNext()) {
				T elem = it.next();
				result = result + "[" + elem.toString() + "] \n";
			}	
			return "DoubleLinkedList " + result + "]";
		}

}
