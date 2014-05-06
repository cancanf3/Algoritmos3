
import java.util.*;

public class VerticeDir
{
	Vertice v;
	LinkedList<Arco> l1; 
	LinkedList<Arco> l2;
	public VerticeDir(Vertice v)
	{
		this.v = v;
		this.l = new LinkedList<Arco>();
	}
}


public class VerticeNoDir 
{
	Vertice v; 
	LinkedList<Arista> l;
	
	public VerticeNoDir(Vertice v)
	{
		this.v = new Vertice();
		this.l = new LinkedList<Arista>();
	}
}
