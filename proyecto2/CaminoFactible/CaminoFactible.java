import java.util.*;
import java.io.*;

class comparador implements Comparator<VerticeNoDir> {
    @Override
    public int compare(VerticeNoDir x, VerticeNoDir y) {
		if (x.v.costoMin == -1 && y.v.costoMin == -1)
			return 0;
    		if (x.v.costoMin == -1)
    			return 1;
    		else if(y.v.costoMin == -1)
    			return -1;
        else
            return x.v.costoMin - y.v.costoMin;
    }
}

public class CaminoFactible {
	static void dijkstra (GrafoNoDirigido g, Vertice Inicio, Vertice Final,
															 int presupuesto) {
		int[] cmin = new int[g.numeroDeVertices()];
		
		comparador c = new comparador();
		PriorityQueue<VerticeNoDir> colaPrioridad = 
					new PriorityQueue<VerticeNoDir>(g.numeroDeVertices(), c);
		int i = 0;
		for ( VerticeNoDir aux : g.lista ) {
			cmin[i++] = -1; 
			if ( aux.v.equals(Inicio) ) {
				aux.v.costoMin = (int)aux.v.peso;
				colaPrioridad.offer(aux);
			}
			else
				aux.v.costoMin = -1;
		}
		VerticeNoDir aux;
		while (colaPrioridad.size() > 0) {
			aux = colaPrioridad.poll();
			for( Arista a : aux.l ) {
				if (a.v.costoMin == -1 || a.v.costoMin > aux.v.costoMin + 
												(int)a.peso + (int)a.v.peso) {
					a.v.costoMin = aux.v.costoMin + (int)a.peso + (int)a.v.peso;
					
					for ( i=0 ; i< g.numeroDeVertices() ; i++) {
						if (a.v.equals(g.lista.get(i).v)) {
							for ( int j=0 ; j<g.numeroDeVertices() ; j++) {
								if (aux.v.equals(g.lista.get(j).v)) {
									cmin[i] = j;
									colaPrioridad.offer(g.lista.get(i));
									break;
								}
							}
							break;
						}
					}
				}
			} 
		}
		ReconstruirCamino(cmin, g, Inicio, Final,presupuesto);
	}

	static void ReconstruirCamino(int[] cmin, GrafoNoDirigido g, Vertice Inicio, 
								 Vertice Final, int presupuesto) {
		int ini = 0,fin = 0;
		LinkedList<Vertice> camino = new LinkedList<Vertice>();
		LinkedList<Arista>  transporte = new LinkedList<Arista>();
		 
		for (int i=0; i< g.numeroDeVertices() ;i++) {
			if ( g.lista.get(i).v.equals(Inicio) )
				ini = i;
			else if ( g.lista.get(i).v.equals(Final) )
				fin = i;
		}	
		if ( g.lista.get(fin).v.costoMin > presupuesto ) {
			System.out.println ("No se encontro una ruta que se ajuste al presupuesto");
		}
		else {
			for (int i=0; i< g.numeroDeVertices() ;i++) {
				camino.addFirst(g.lista.get(fin).v);
				Arista trans = new Arista("",-1,Inicio,Final);
				for (Arista a : g.lista.get(fin).l) {
					if ( a.v.equals( g.lista.get(cmin[fin]).v ) && 
									(trans.peso == -1 || trans.peso > a.peso)) {
						trans = a;
					}
				}
				transporte.addFirst(trans);
				fin = cmin[fin];	
				if (fin == ini) {
					camino.addFirst(g.lista.get(fin).v);
					break;
				}
			}
			System.out.print(camino.getFirst().id+", ");
			for (int i=0; i< camino.size()-1 ;i++) {
				System.out.print(transporte.get(i).id+", ");	
				System.out.print(camino.get(i+1).id+", ");
			}
			System.out.println(presupuesto - camino.getLast().costoMin);
		}
	} 
	public static void main(String[] args) {
		GrafoNoDirigido g = new GrafoNoDirigido();
		g.cargarGrafo(args[4],Integer.parseInt(args[3]));
		dijkstra(g , g.obtenerVertice(args[0]) , g.obtenerVertice(args[1]) ,
												    Integer.parseInt(args[2]));			  
	}
}
