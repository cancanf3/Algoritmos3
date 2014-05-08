/**
 *
 */

import java.util.*;

public class ClienteGrafo {

  public static void main(String [] args) {
  	Digrafo g= new Digrafo();
	g.cargarGrafo("archivo.txt");
	LinkedList<Aristas> l2 = new LinkedList<Arista>();	
	LinkedList<Vertice> l1 = new LinkedList<Vertice>();
	l1 = g.adyacentes("7");
	l2 = g.incidentes("7");

	for ( Vertice ver : l1 ) {
		System.out.print(ver.getId());
	}
	System.out.println();
	for ( Lado ver : l1 ) {
		System.out.print(ver.getId());
	}
	System.out.println();

	l1 = g.adyacentes("5");
	l2 = g.incidentes("5");

	for ( Vertice ver : l1 ) {
		System.out.print(ver.getId());
	}
	System.out.println();
	for ( Lado ver : l1 ) {
		System.out.print(ver.getId());
	}
	System.out.println();

	l1 = g.adyacentes("1");
	l2 = g.incidentes("1");

	for ( Vertice ver : l1 ) {
		System.out.print(ver.getId());
	}
	System.out.println();
	for ( Lado ver : l1 ) {
		System.out.print(ver.getId());
	}
	System.out.println();

	l1 = g.adyacentes("428");
	l2 = g.incidentes("428");

	
	l1 = g.adyacentes("579");
	l2 = g.incidentes("579");
	
	
	
  }
}
