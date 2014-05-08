/**
 *
 */

import java.util.*;

public class ClienteGrafo {

  public static void main(String [] args) {
  	Digrafo g= new Digrafo();
	Digrafo h = new Digrafo();
	g.cargarGrafo("ToyGraph.in");
	h = g.clone();
	System.out.println(g.numeroDeVertices());
	List<Vertice> l2 = new LinkedList<Vertice>();	
	List<Vertice> l1 = new LinkedList<Vertice>();
	g.eliminarArco("63");
	l1 = g.adyacentes("7");
	l2 = g.predecesores("7");

	for ( Vertice ver : l1 ) {
		System.out.print(ver.getId()+" ");
	}
	System.out.println();
	for ( Vertice ver : l2 ) {
		System.out.print(ver.getId()+" ");
	}
	System.out.println();

	l1 = g.adyacentes("1");
	l2 = g.predecesores("1");

	for ( Vertice ver : l1 ) {
		if ( ver == null ) {
			System.out.println("nulo");
			break;
		}
		System.out.print(ver.getId()+" ");
	}
	System.out.println();
	for ( Vertice ver : l2 ) {
		System.out.print(ver.getId()+" ");
	}
	System.out.println();

	l1 = g.adyacentes("5");
	l2 = g.predecesores("5");

	for ( Vertice ver : l1 ) {
		System.out.print(ver.getId()+" ");
	}
	System.out.println();
	for ( Vertice ver : l2 ) {
		System.out.print(ver.getId()+" ");
	}
	System.out.println();

	
  }
}
