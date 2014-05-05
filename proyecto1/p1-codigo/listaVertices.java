
import java.util.*;

public class VerticeDir
{
	Vertice v;
	LinkedList<Arco> l; 

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
	
	public VerticeDir()
	{
		this.v = new Vertice();
		this.l = new LinkedList<Arista>();
	}
}
