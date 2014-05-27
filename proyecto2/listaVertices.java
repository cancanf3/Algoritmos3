
import java.util.*;

class VerticeDir
{
	Vertice v;
	LinkedList<Arco> l1; 
	LinkedList<Arco> l2;
	public VerticeDir(Vertice v)
	{
		this.v = v;
		this.l1 = new LinkedList<Arco>();
		this.l2 = new LinkedList<Arco>();
	}
}


class VerticeNoDir 
{
	Vertice v; 
	LinkedList<Arista> l;
	String padre;
	boolean articulacion;
	boolean visitado;
	int sucesores;
	
	public VerticeNoDir(Vertice v)
	{
		this.v = v;
		this.l = new LinkedList<Arista>();
		this.articulacion = false;
		this.visitado = false;
		this.padre = "";
	}
}
