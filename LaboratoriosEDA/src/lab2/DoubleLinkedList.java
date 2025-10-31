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

	public void setDescr(String nom) {
		descr = nom;
	}

	public String getDescr() {
		return descr;
	}

	public T removeFirst() {
		// Elimina el primer elemento de la lista
		// Precondici n:
		// COMPLETAR EL CODIGO Y CALCULAR EL COSTE
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

	public T removeLast() { //Coste O(1)
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

	public T remove(T elem) { // Cose O(n) donde n es el tamaño total de la lista en el peor de los casos
		//Pre: T puede o no estar en la lista. La lista puede estar vacia.
		// Post: Elimina un elemento concreto de la lista
		if(last==null) {
			return null;
		}
		Node<T> actual=last.next;//first
		T data = null;
		
		while(actual != last && !actual.data.equals(elem)) {
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

	public void removeAll(T elem) { //Coste O(n) donde n es tamaño de la lista.
		// Pre:
		// Post: elimina todas las apariciones del elemento T de la lista.
		if(last == null) {
			return;
		}
		Node<T> actual = last.next;
		Node<T> aux;
		boolean vuelta = false;
		
		while(last != null && !vuelta) {
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

	public T first() { //Coste O(1)
		// Da acceso al primer elemento de la lista
		T first = null;
		if(last != null) {
			first = last.next.data;
		}
		return first;
	}

	public T last() {//Coste O(1)
		// Da acceso al ultimo elemento de la lista
		T ult = null;
		if(last != null) {
			ult = last.data;
		}
		return ult;
	}

	public DoubleLinkedList<T> clone() {//Coste O(n) donde n es el tamaño de la lista original
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
	    while(actual!=this.last.next) {
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

	public boolean contains(T elem) { // O(n) donde n es el tamaño de la lista
		// Pre:
		// Post: determina si la lista contiene un elemento concreto
		if (isEmpty())
			return false;
		if(find(elem)==null)
			return false;
		return true;

	}

	public T find(T elem) { // O(n) donde n es, en el peor de los casos, el numero total de elementos de la lista.
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
		}while(!enc && actual.prev != last);
		if(enc) {
			data = actual.data;
		}
		return data;
	}

	public boolean isEmpty() { // O(1)
		// determina si la lista esta vacia
		return last==null;
	}

	public int size() { // O(1)
		// determina el tamaño de lal lista
		return count;
	}

	
	//ITERATOR
	public Iterator<T> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<T> {
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
		public boolean hasNext() {// O(1)
			//return true;
			if(cont == count) {
				return false;
			}
			return true;
				
		}

		@Override
		public T next() { // O(1)
			T data = actual.data;
			actual = actual.next;
			cont++;
			return data;
		}

	}

	public void visualizarNodos() {
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		String rdo = new String();
		Iterator<T> it = iterator();
		while (it.hasNext()) {
			T elem = it.next();
			rdo = rdo + "[" + elem.toString() + "] \n";
		}
		return "DoubleLinkedList " + rdo + "]";
	}

}
