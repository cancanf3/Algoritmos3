
import java.util.*;


public class VerticeNoDir
{
	Vertice v; 
	LinkedList<Arista> l;
	boolean visitado;
	int sucesores;
	
	public VerticeNoDir(Vertice v)
	{
		this.v = v;
		this.l = new LinkedList<Arista>();
		this.visitado = false;
	}
}
