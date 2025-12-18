package lab4;



public class Persona implements Comparable<Persona> {
	
	// atributos
	private String name;
    private String dni;
	
	public Persona(String pName, String pDni) { // Constructora
		name = pName;
		dni = pDni;
	}
	
	public String getName() { return name; }  //O(1)-> Coste constante

	public void setName(String name) { this.name = name; }  //O(1)-> Coste constante

	public String getDni() { return dni; }  //O(1)-> Coste constante

	public void setDni(String dni) { this.dni = dni; }  //O(1)-> Coste constante

	@Override
	public boolean equals(Object obj) {  //O(1)-> Coste constante
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}
			
	@Override
	public int compareTo(Persona arg0) {  //O(1)-> Coste constante
		return name.compareToIgnoreCase(arg0.name);
	}

	public String toString() {  //O(1)-> Coste constante
		return name + " " + dni;
	}
	
}
