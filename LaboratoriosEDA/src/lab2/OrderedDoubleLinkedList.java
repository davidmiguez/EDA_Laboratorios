package lab2;


public class OrderedDoubleLinkedList<T> extends DoubleLinkedList<T> implements OrderedListADT<T> {
	
	public void add(T elem){ //O(n)-> Coste lineal
		Node<T> nuevo = new Node<T>(elem); //O(1)
		if(last == null) { //O(1)
			last = nuevo; //O(1)
			nuevo.next = last; //O(1)
			nuevo.prev = last; //O(1)
			count++; //O(1)
		}else {
			boolean enc = false; //O(1)
			Node<T> actual = last.next; //O(1)
			do {
				if(((Comparable<T>)actual.data).compareTo(elem) > 0) { //O(1)
					enc = true; //O(1)
				}else {
					actual = actual.next; //O(1)
				}
			}while(!enc && actual.prev!=last);  //n x O(1) -> O(n) donde n es el número de elementos de las lista hasta encontrar el elemento
			if(enc) { //O(1)
				actual.prev.next = nuevo; //O(1)
				nuevo.prev = actual.prev; //O(1)
				actual.prev = nuevo; //O(1)
				nuevo.next = actual; //O(1)
			}else {
				nuevo.prev = last; //O(1)
				nuevo.next = last.next; //O(1)
				last.next.prev = nuevo; //O(1)
				last.next = nuevo; //O(1)
				last = nuevo; //O(1)
			}
			count++; //O(1)
		}
	}
	
	public OrderedDoubleLinkedList<T> intersection(OrderedDoubleLinkedList<T> lista){ //O(n)-> Coste lineal
		OrderedDoubleLinkedList<T> lnueva = new OrderedDoubleLinkedList<T>(); //O(1)
		lnueva.last = null; //O(1)
		Node<T> actual1 = this.last.next; //O(1)
		Node<T> actual2 = lista.last.next; //O(1)
		
		while(actual1 != this.last && actual2!=lista.last) { //n x O(1) -> O(n) donde n es el número de elementos de las listas
			if(actual1.data.equals(actual2.data)) { //O(1)
				lnueva.add(actual1.data); //O(1)
				actual1 = actual1.next; //O(1)
				actual2 = actual2.next; //O(1)
			}else if(((Comparable<T>)actual1.data).compareTo(actual2.data) < 0) { //O(1)
				actual1 = actual1.next; //O(1)
			}else {
				actual2 = actual2.next; //O(1)
			}
		}
		
		return lnueva;
	}

}
