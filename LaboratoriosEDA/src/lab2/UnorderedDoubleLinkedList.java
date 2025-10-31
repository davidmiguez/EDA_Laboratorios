package lab2;

public class UnorderedDoubleLinkedList<T> extends DoubleLinkedList<T> implements UnorderedListADT<T> {
	
	public void addToFront(T elem) {  //O(1)-> Coste constante
		//Pre:
		//Post: añade un elemento al comienzo de la lista
		Node<T> nuevo = new Node<T>(elem);
		if(last == null){
			last = nuevo;
			nuevo.next = last;
			nuevo.prev = last;
		}else{
			last.next.prev = nuevo;
			nuevo.next = last.next;
			last.next = nuevo;
			nuevo.prev = last;
		}
		count++;
	}

	public void addToRear(T elem) {  //O(1)-> Coste constante
		// a ade un elemento al final 
		addToFront(elem);
		last = last.next;
	}
	
	public void addAfter(T elem, T target) { //O(n)-> Coste lineal
		// A ade elem detr s de otro elemento concreto, target,  que ya se encuentra en la lista
		boolean enc = false;
		Node<T> actual = last.next;
		do {
			if(actual.data.equals(target)) {
				enc = true;
			}else {
				actual = actual.next;
			}
		}while(!enc && actual.prev != last);  //n x O(1) -> O(n) donde n es el n�mero de elementoa de la lista 
		Node<T> nuevo = new Node<T>(elem);
		nuevo.next = actual.next;
		nuevo.prev = actual;
		actual.next.prev = nuevo;
		actual.next = nuevo;
		if(actual == last) {
			last = last.next;
		}
	}

}


