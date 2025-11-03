package lab2;

import java.util.Iterator;


import java.util.Iterator;


public class PruebaDoubleLinkedList {
	
	public static void visualizarNodos(UnorderedDoubleLinkedList<Integer> l) {
		Iterator<Integer> itr = l.iterator();
		System.out.println();
		while (itr.hasNext()) {
			Integer num = itr.next();
			System.out.println(num);
		}
	}
	
	
	public static void main(String[] args)  {
		
		UnorderedDoubleLinkedList<Integer> l = new UnorderedDoubleLinkedList<Integer>();
		l.addToRear(1);
		l.addToRear(20);
		l.addToRear(5);
		l.addToRear(8);
		l.addToRear(11);
		l.addToRear(0);
		l.addToRear(4);
		l.addToFront(6);
		l.remove(new Integer(8));

		
		System.out.print(" Lista ...............");
		visualizarNodos(l);
		System.out.println(" Num elementos: " + l.size());
				
		
		System.out.println("Prueba Find ...............");
		System.out.println("11? " + l.find(11));
		System.out.println("0? " + l.find(0);
		System.out.println("8? " + l.find(8));
		
}
}

