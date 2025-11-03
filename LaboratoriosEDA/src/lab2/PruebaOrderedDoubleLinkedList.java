package lab2;

public class PruebaOrderedDoubleLinkedList {	
		
		public static void main(String[] args)  {
			
			OrderedDoubleLinkedList<Integer> l = new OrderedDoubleLinkedList<Integer>();
			l.add(1);
			l.add(2);
			l.add(4);
			l.add(5);
			l.add(7);
			l.add(20);
			l.add(0);
			l.remove(new Integer(7));

			
			System.out.print(" Lista ...............");
			l.visualizarNodos();
			System.out.println(" Num elementos: " + l.size());
					
			
			System.out.println("Prueba Find ...............");
			System.out.println("20? " + l.find(20));
			System.out.println("7? " + l.find(9));
			System.out.println("6? " + l.find(9));
			System.out.println("0? " + l.find(0));
			System.out.println("7? " + l.find(7));
			
			
			
			OrderedDoubleLinkedList<Persona> l2 = new OrderedDoubleLinkedList<Persona>();
			l2.add(new Persona("Marta", "11"));
			l2.add(new Persona("Peio", "27"));
			l2.add(new Persona("Tomas", "37"));
			l2.add(new Persona("Sandra", "58"));
			l2.add(new Persona("Pedro", "22"));
			l2.add(new Persona("Jon", "45"));

			l2.remove(new Persona("", "58"));

			
			System.out.print(" Lista ...............");
			l2.visualizarNodos();
			System.out.println(" Num elementos: " + l2.size());
					
			
			System.out.println("Prueba Find ...............");
			System.out.println("22? " + l2.find(new Persona("", "22")));
			System.out.println("55? " + l2.find(new Persona("", "45")));
			System.out.println("27? " + l2.find(new Persona("", "27")));	
			System.out.println("58? " + l2.find(new Persona("", "58")));	
			
			
	}
	}

