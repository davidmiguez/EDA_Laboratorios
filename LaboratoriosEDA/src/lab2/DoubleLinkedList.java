package lab2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<T> implements ListADT<T> {

	// Atributos
	protected Node<T> last; // apuntador al  ltimo
	protected String descr; // descripci n
	protected int count;

	// Constructor
	public DoubleLinkedList() { 
		last = null;
		descr = "";
		count = 0;
	}

	public void setDescr(String nom) { //O(1)-> Coste constante
		descr = nom;
	}

	public String getDescr() { //O(1)-> Coste constante
		return descr;
	}

	public T removeFirst() { //O(1)-> Coste constante
		T data = null;
		if(last!=null) {
			if(last.next == last) {
				data = last.data;
				last = null;
			}else {
				data = last.next.data;
				last.next.prev = last;
				last.next = last.next.next;
			}
			count--;
		}
		return data;
	}

	public T removeLast() { //O(1)-> Coste constante
		// Pre:
		// Post: elimina el ultimo elemento de la lista
		T data = null;
		if (last == null) {
			return null;
		}
		data = last.data;
		
		if(count==1) { //si la lista tiene 1 elemento
			last = null;
		}else {
			last.prev.next = last.next;
			last.next.prev = last.prev;
			last = last.prev;
		}
		count--;
		return data;
	}

	public T remove(T elem) { //O(n)-> Coste lineal  
		//Pre: T puede o no estar en la lista. La lista puede estar vacia.
		// Post: Elimina un elemento concreto de la lista
		if(last==null) {
			return null;
		}
		Node<T> actual=last.next;//first
		T data = null;
		
		while(actual != last && !actual.data.equals(elem)) { //n x O(1) -> O(n) donde n es el n�mero de elementos de la lista hasta encontrar el elemento
			actual=actual.next;
		}
		boolean enc = actual.data.equals(elem);
		if(enc) {
			data = actual.data;
			if(count==1) { //si T es el unico elemento de la lista
				last = null;
			}else { //mas de un elemento
				actual.prev.next = actual.next;
				actual.next.prev = actual.prev;
				
				if(actual==last) { //si T era el ultimo
					last = actual.prev;
				}
			}
			count--;
		}
		return data;
		
	}

	public void removeAll(T elem) { //O(n)-> Coste lineal 
		// Pre:
		// Post: elimina todas las apariciones del elemento T de la lista.
		if(last == null) {
			return;
		}
		Node<T> actual = last.next;
		Node<T> aux;
		boolean vuelta = false;
		
		while(last != null && !vuelta) {  //n x O(1) -> O(n) donde n es tamaño de la lista.
			if(actual.data.equals(elem)) {
				if(actual.next == actual) { //si hay un solo nodo
					last=null;
					count = 0;
					return;
				}
				aux = actual.next;
				actual.prev.next = actual.next;
				actual.next.prev = actual.prev;
				
				if(actual == last) { //si T era last
					last=actual.prev;
				}
				count--;
				actual = aux;
			}else { //si actual no es T, avanzar al siguiente
				actual = actual.next;
			}
			
			if(actual == last.next) {
				vuelta = true;
			}
		}
		
	}

	public T first() { //O(1)-> Coste constante
		// Da acceso al primer elemento de la lista
		T first = null;
		if(last != null) {
			first = last.next.data;
		}
		return first;
	}

	public T last() {//O(1)-> Coste constante
		// Da acceso al ultimo elemento de la lista
		T ult = null;
		if(last != null) {
			ult = last.data;
		}
		return ult;
	}

	public DoubleLinkedList<T> clone() {//O(n)-> Coste lineal 
		// Pre:
		// Post: devuelve una copia de la lista, la lista original no se modifica
		if(last == null) {
			return new DoubleLinkedList<T>();
		}
		
		Node<T> actual = last.next;
		DoubleLinkedList<T> l = new DoubleLinkedList<T>();
		l.last = null;
		
		Node<T> nuevo = new Node<T>(actual.data);
		l.last=nuevo;
		nuevo.next = nuevo;
	    nuevo.prev = nuevo;
	    l.count = 1;
	    
	    actual = actual.next;
	    while(actual!=this.last.next) { //n x O(1) -> O(n) donde n es tamaño de la lista.
	    	nuevo = new Node<T>(actual.data);
	    	nuevo.prev = l.last;
	    	l.last.next = nuevo;
	    	
	    	nuevo.next = l.last.next;
	    	l.last.next.prev = nuevo; //first.prev = nuevo
	    	
	    	l.last = nuevo;
	    	l.count++;
	    	
	    	actual = actual.next;
	    }
	    return l;
	}

	public boolean contains(T elem) { //O(n)-> Coste lineal 
		// Pre:
		// Post: determina si la lista contiene un elemento concreto
		if (isEmpty())
			return false;
		if(find(elem)==null)
			return false;
		return true;

	}

	public T find(T elem) { //O(n)-> Coste lineal 
		// Post: determina si la lista contiene un elemento T y develve su referencia,
		// devuelve null en caso de que T no este en la lista.

		T data = null;
		boolean enc = false;
		Node<T> actual = last.next;
		do {
			if(actual.data.equals(elem)) {
				enc = true;
			}else {
				actual = actual.next;
			}
		}while(!enc && actual.prev != last); //n x O(1) -> O(n) donde n es tamaño de la lista.
		if(enc) {
			data = actual.data;
		}
		return data;
	}

	public boolean isEmpty() {  //O(1)-> Coste constante
		// determina si la lista esta vacia
		return last==null;
	}

	public int size() {  //O(1)-> Coste constante
		// determina el tamaño de lal lista
		return count;
	}

	
	//ITERATOR
	public Iterator<T> iterator() {  //O(1)-> Coste constante
		return new ListIterator();
	}

	private class ListIterator implements Iterator<T> {  //O(1)-> Coste constante
		Node<T> actual;
		int cont;
		public ListIterator() {
			cont = 0;
			if(last == null) {
				actual = null;
			}else {
				actual = last.next;
			}
		}
		@Override
		public boolean hasNext() { //O(1)-> Coste constante
			//return true;
			if(cont == count) {
				return false;
			}
			return true;
				
		}

		@Override
		public T next() {  //O(1)-> Coste constante
			T data = actual.data;
			actual = actual.next;
			cont++;
			return data;
		}

	}

	public void visualizarNodos() {  //O(1)-> Coste constante
		System.out.println(this.toString());
	}

	@Override
	public String toString() {  //O(1)-> Coste constante
		String rdo = new String();
		Iterator<T> it = iterator();
		while (it.hasNext()) {
			T elem = it.next();
			rdo = rdo + "[" + elem.toString() + "] \n";
		}
		return "DoubleLinkedList " + rdo + "]";
	}

}
