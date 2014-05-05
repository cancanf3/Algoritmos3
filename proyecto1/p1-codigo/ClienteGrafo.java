/**
 *
 */

import java.util.*;

public class ClienteGrafo {
  public static void main(String [] args) {
  	Vertice v1 = new Vertice("1",1);
	Vertice v2 = new Vertice("2",2);
  	Arco hola = new Arco("123456",3,v1,v2);
	System.out.println(hola.getId());
	Digrafo g = new Digrafo();
  }
}
