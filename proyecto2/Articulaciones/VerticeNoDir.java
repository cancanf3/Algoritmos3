/**
 * 
 */

import java.util.*;

class VerticeNoDir
{
	Vertice v;
	LinkedList<Arista> l;
	String padre;
	boolean articulacion;
	boolean visitado;
	int sucesores;
	int numbusq;
	int masbajo;
	
	public VerticeNoDir(Vertice v)
	{
    this.v = v;
    this.l = new LinkedList<Arista>();
    this.articulacion = false;
    this.visitado = false;
    this.padre = "";
	}
}