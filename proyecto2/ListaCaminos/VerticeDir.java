/**
 * 
 */

import java.util.*;

class VerticeDir
{
	Vertice v;
	LinkedList<Arco> l1;
	LinkedList<Arco> l2;
	int visitado;
	public VerticeDir(Vertice v)
	{
    this.v = v;
    this.l1 = new LinkedList<Arco>();
    this.l2 = new LinkedList<Arco>();
    this.visitado = 0;
	}
}