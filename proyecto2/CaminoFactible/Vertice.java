/**
 * 
 */

public class Vertice
{
  public  String id;
  public  double peso;
  public  int costoMin;
  
  public Vertice(String id, double peso) {											// Constructor de la clase
	this.id = id;																	// Pre: 
  	this.peso = peso;
  }

  public double getPeso() {
  return this.peso;
  }

  public String getId() {
  return this.id;
  }

  public String toString() { 
  	String peso = Double.toString(this.peso);
  	String repr = "Vertice id: " + this.id + " peso: " + peso;
  	return repr;
  }
}
