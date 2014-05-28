/**
 *
 */

public class Arista extends Lado
{
  public Vertice u;
  public Vertice v;
  
  public Arista(String id, double peso, Vertice u, Vertice v) {
  	super(id,peso);
	this.u = u;
	this.v = v;
  }

  public Vertice getExtremo1() {
  	return this.u;
  }

  public Vertice getExtremo2() {
  	return this.v;
  }

  public String toString() {
  	String peso = Double.toString(getPeso());
	String repr = "Arista id: " + getId() + " peso: " + peso;
	return repr;
  }
}
