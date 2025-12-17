package lab4;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<T> implements ListADT<T> {

	// Atributos
	protected Node<T> last; // apuntador al �ltimo
	protected String descr; // descripci�n
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

	public T removeLast() {
		// Elimina el �ltimo elemento de la lista
		// Precondici�n:
		// COMPLETAR EL CODIGO Y CALCULAR EL COSTE
		T data = null;
		if(last!=null) {
			if(last.next == last){
				data = last.data;
				last = null;
			}else {
				data = last.data;
				last.prev.next = last.next;
				last.next.prev = last.prev;
				last = last.prev;
			}
			count--;
		}
		return data;
	}

	public T remove(T elem) {
		// Elimina un elemento concreto de la lista
		// COMPLETAR EL CODIGO Y CALCULAR EL COSTE
		T data = null;
		boolean enc = false;
		if(last!=null) {
			Node<T> actual = last;
			do {
				if(actual.data.equals(elem)) {
					enc = true;
				}else {
					actual = actual.next;
				}
			}while(!enc && actual!=last);
			if(enc) {
				if(actual.next==actual) {
					data = last.data;
					last = null;
				}else {
					actual.prev.next = actual.next;
					actual.next.prev = actual.prev;
					if(actual == last) {
						last = last.prev;
					}
				}
				count--;
			}	
		}
		return data;
	}

	public void removeAll(T elem) {
		// Elimina todas las apariciones de un elemento de la lista
		// COMPLETAR EL CODIGO Y CALCULAR EL COSTE
		if(last != null) {
			Node<T> actual = last.next;
			do {
				if(actual.data.equals(elem)) {
					if(actual.next==actual) {
						last = null;
					}else {
						actual.prev.next = actual.next;
						actual.next.prev = actual.prev;
						if(actual == last) {
							last = last.prev;
						}
					}
					count--;
				}else {
					actual = actual.next;
				}
			}while(actual.prev != last);
		}
		
	}

	public T first() {
		// Da acceso al primer elemento de la lista
		// COMPLETAR EL CODIGO Y CALCULAR EL COSTE
		T primero = null;
		if(last != null) {
			primero = last.next.data;
		}
		return primero;
	}

	public T last() {
		// Da acceso al �ltimo elemento de la lista
		// COMPLETAR EL CODIGO Y CALCULAR EL COSTE
		T ultimo = null;
		if(last != null) {
			ultimo = last.data;
		}
		return ultimo;
	}

	public DoubleLinkedList<T> clone() {
		// Devuelve una copia de la lista (no duplica el puntero)
		// COMPLETAR EL CODIGO Y CALCULAR EL COSTE
		Node<T> actual = last.next;
		DoubleLinkedList<T> l = new DoubleLinkedList<T>();
		l.last = null;
		do {
			Node<T> nuevo = new Node<T>(actual.data);
			if(l.last == null) {
				l.last = nuevo;
				nuevo.next = nuevo;
				nuevo.prev = nuevo;
			}else {
				nuevo.prev = l.last;
				l.last.next = nuevo;
				nuevo.next = l.last;
				l.last.prev = nuevo;
				l.last = nuevo;
			}
			l.count++;
			actual = actual.next;
		}while(actual.prev != last);
		return l;
	}

	public boolean contains(T elem) {
		// Determina si la lista contiene un elemento concreto
		if (isEmpty())
			return false;
		// COMPLETAR EL CODIGO Y CALCULAR EL COSTE
		if(find(elem)==null)
			return false;
		return true;

	}

	public T find(T elem) {
		// Determina si la lista contiene un elemento concreto, y develve su referencia,
		// null en caso de que no est�
		// COMPLETAR EL CODIGO Y CALCULAR EL COSTE
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

	public boolean isEmpty() {
		// Determina si la lista est� vac�a
		// COMPLETAR EL CODIGO Y CALCULAR EL COSTE
		return last==null;
	}

	public int size() {
		// Determina el n�mero de elementos de la lista
		// COMPLETAR EL CODIGO Y CALCULAR EL COSTE
		return count;
	}

	/** Return an iterator to the stack that iterates through the items . */
	public Iterator<T> iterator() {
		return new ListIterator();
	}

	// an iterator, doesn't implement remove() since it's optional
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
		public boolean hasNext() {
			//return true;
			if(cont == count) {
				return false;
			}
			return true;
				
		}

		@Override
		public T next() {
			T data = actual.data;
			actual = actual.next;
			cont++;
			return data;
		}

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
