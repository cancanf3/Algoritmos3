/**
 *
 */
public class Arco extends Lado
{
  private Vertice extremoInicial;
  private Vertice extremoFinal;
  
  public Arco(String id, double peso, Vertice extremoInicial, Vertice extremoFinal) {
  	super(id,peso);
	this.extremoInicial = extremoInicial;
	this.extremoFinal = extremoFinal;
  }

  public Vertice getExtremoInicial() {
  	return this.extremoInicial;
  }

  public Vertice getExtremoFinall() {
  	return this.extremoFinal;
  }

  public String toString() {
 	String peso = Double.toString(getPeso());
	String repr = "Arco id: " + getId() + " peso: " + peso;
	return repr;
  }
}
