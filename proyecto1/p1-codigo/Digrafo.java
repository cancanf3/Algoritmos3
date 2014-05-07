/**
 * 
 */

import java.util.*;

public class Digrafo implements Grafo
{
    private LinkedList<VerticeDir> lista;
	private int numArco;
    
    public Digrafo() {
        this.lista = new LinkedList<VerticeDir>() ;
		this.numArco = 0;
    }

	public Digrafo(LinkedList<VerticeDir>listaady, numArco) {							// Constructor opcional para Construir 
		this.numArco = numArco;															// un Grafo con una lista de adyasencias construida
        this.lista = listaady;
    }

    public boolean cargarGrafo(String dirArchivo) {
    }
    
    public int numeroDeVertices() {
        return lista.size();
    }

    public int numeroDeLados() {
		return numArco;
        
    }
   
    public boolean agregarVertice(Vertice v) {
		for ( VerticeDir ver : this.lista ) {
			if ( ver.v.getId().equals(v.getId()) ) {
				return false;
			}
		}
		
		this.lista.offer(new VerticeDir(v));
		return true;
    }

    public boolean agregarVertice(String id, double peso) {
		for ( VerticeDir ver : this.lista ) {
			if ( ver.v.getId().equals(v.getId()) ) {
				return false;
			}
		}
		Vertice v = new Vertice(id,peso);	
		this.lista.offer(new VerticeDir(v));
		return true;
    }
    
    public Vertice obtenerVertice(String id) {
		for ( VerticeDir ver : this.lista ) {
			if ( ver.v.getId().equals(id) ) {
				return ver.v
			}
		}
		throw new NoSuchElementException();
		
    }

    public boolean estaVertice(String id) {
		for ( VerticeDir ver : this.lista ) {
			if ( ver.v.getId().equals(id) ) {
				return true;
			}
		}
		return false;
    }

    public boolean estaLado(String u, String v){
		for	( VerticeDir ver : this.lista ) {
			if ( ver.v.getId().equals(u) ) {
				for ( Arco arc : this.lista.l1 ) {
					if ( ari.getextremoFinal().getId().equals(v) ) {
						return true;
					}
				}
			}
		}
		return false;
    }

    public boolean eliminarVertice(String id) {
		LinkedList<Vertice> aux1 = new LinkedList<Vertice>();
		LinkedList<Vertice> aux2 = new LinkedList<Vertice>();
		boolean esta = false;
		for ( VerticeDir ver : this.lista ) {
			if ( ver.v.getId().equals(id) ) {
				esta = true;
				for ( Arco arc : ver.l1 ) {
					if ( arc.getextremoInicial() == arc.getextremoFinal() ) {
						aux2.offer(arc.getextremoInicial() );
					}
					else {
						aux2.offer(arc.getextremoFinal());
						aux2.offer(arc.getextremoInicial());
						ver.l1.remove(ver.l1.indexOf(arc));
					}
				}	
			}
		}

		while ( aux2.isEmpty() ) {
			for ( Arco arc : ver.l2.get(ver.l2.indexOf(aux2.poll())) ) {
				if ( arc.getextremoFinal().getId().equals(id) ) { 
					arc.remove(arc.indexOf());
				}
				else if ( arc.getextremoInicial().getId().equals(id) ) {
					aux1.offer(arc.getextremoFinal());
					arc.remove(arc.indexOf());
				}
			}
		}

		while ( aux1.isEmpty() ) {
			for ( Arco arc : ver.l1.get(ver.l1.indexOf(aux1.poll())) ) {
				if ( arc.getextremoFinal().getId().equals(id) ) {
					arc.remove(arc.indexOf());
				}
			}
		}
		return esta;


    }

    public List<Vertice> vertices() {
		List<Vertice> listver = new LinkedList<Vertice>();
		for ( VerticeDir ver : this.lista ) { 
			listver.offer(ver.v);
		}
		return listver;
			
    }

    public List<Lado> lados() {
		List<Lado> listarc = new LinkedList<Arco>();
		for ( VerticeDir ver ; this.lista ) {
			for ( Arco arc ; this.lista ) {
				listarc.offer(arc);
			}
		}
		return listarc;
    }

    public int grado(String id) {
		int gout;
		int gin;
		boolean hay = false;

		for ( VerticeDir ver : this.lista ) {
			if ( ver.v.getId().equals(id) ) {
				hay = true;
				gout = ver.l1.size();
			}
		}

		for ( VerticeDir ver : this.lista ) {
			if ( ver.v.getId().equals(id) ) {
				gin = ver.l2.size();
			}
		}
		if ! hay {
			throw new NoSuchElementException();
		}
		return gin + gout;
    }

    public List<Vertice> adyacentes(String id) {
		List<Vertice> listaver = new LinkedList<Vertice>();
		boolean hay = false;	

		for ( VerticeDir ver : this.lista ) {
			if ( ver.v.getId().equals(id) ) {
				hay = true;
				for (Arco arc : ver.l1 ) {
					listaver.addLast(arc.getextremoFinal());
				}
			}
		}
		if ! hay {
			throw new NoSuchElementException();
		}
		return listaver;
    }
 
    public List<Lado> incidentes(String id) {
		List<Lado> listaari = new LinkedList<Arco>(); 
		boolean hay = false;
		for ( VerticeDir ver : this.lista ) {
			if ( ver.v.getId().equals(id) ) {
				hay = false;
				for ( Arco arc : ver.l2 ) {
					listaari.addLast(arc.getextremoFinal());
				}
			}
		}
		if ! hay {
			throw new NoSuchElementException();
	}
		return listaari;
    }

    public Object clone() {
		LinkedList<VerticeDir> listaclon = new LinkedList<VerticeDir>();
		Digrafo digrafoclon;
		int clonnumArco;
		for ( VerticeDir ver : this.lista ) {
			listaclon.offer(new VerticeDir(ver.v))
			for ( Arco arc : ver.l1 ) { 
				listaclon.getLast().l1.offer(new Arco(arc.getId(), 
											 arc.getpeso()));
			}

			for ( Arco arc : ver.l2 ) {
				listaclon.getLast().l2.offer(new Arco(arc.getId(), 
											 arc.getpeso()));
			}
		}
		clonnumArco = this.numArco;
		digrafoclon = new Digrafo(listaclon, clonnumArco);	
		return digrafoclon;
	}

    public String toString() {
    }

    public boolean agregarArco(Arco a) {
		boolean esta = false;
		for ( VerticeDir ver : this.lista ) {
			if ver.v.getId().equals(a.getextremoInicial().getId()) {
				for ( Arco arc : ver.l1 ) {
					if	( arc.getId().equals(a.getId()) ) {
						esta = true;
					}
				}
				if ! esta { 
					ver.l1.offer(a);
				}
				
			}
		}

		for ( VerticeDir ver : this.lista ) {
			if ( ver.v.getId().equals(a.getextremoFinal().getId()) ) {  
				ver.l2.offer(new Arco(a.getId(), a.getpeso(),
							a.getextremoFinal(), a.getextremoInicial() ));
			}
		}
		this.numArco ++;
		return esta;
    } 

    public boolean agregarArco(String id, double peso, String u, String v) { 
		Vertice v1;	
		Vertice v2;
		Arco arco1;
		Arco arco2;
		boolean esta = false;

		for ( VerticeDir ver : this.lista ) { 
			if ( ver.v.getId().equals(u) ) {
				v1 = new Vertice(ver.v.getId(), ver.v.getpeso());
			}
			if ( ver.v.getId().equals(v) ) {
				v2 = new Vertice(ver.v.getId(), ver.v.getpeso());
			}
		}
		arco1 = new Arco( id, peso, v1, v2);
		arco2 = new Arco( id, peso, v2, v1);
			
		for ( VerticeDir ver : this.lista ) {
			if ( ver.v.getId().equals(u) ) {
				for ( Arco arc : ver.l1 ) {
					if ( arc.getId().equals(id) ) {
						esta = true;
					}
				}
				if ! esta {
					ver.l1.offer(arco1);
				}
			}
		}

		for ( VerticeDir ver : this.lista ) {
			if ( ver.v.getId().equals(v) ) {
				ver.l2.offer(arco2);
			}
		}
		this.numArco ++;
		return esta;

    }

    public int gradoInterior(String id) {
		for ( VerticeDir ver : this.lista ) {
			if ( ver.v.getId().equals(id) ) {
				return ver.l2.size();
			}
		}
    }

    public int gradoExterior(String id) {
		for ( VerticeDir ver : this.lista ) {
			if ( ver.v.getId().equals(id) ) {
				return ver.l1.size();
			}
		}
    }

    public List<Vertice> sucesores(String id) {
		list<Vertice> listaver = new LinkedList<Vertice>;
		for ( VerticeDir ver : this.lista ) {
			if ( ver.v.getId().equals(id) ) {
				for ( Arco arc : ver.l1 ) {
					if ! ( listaver.contains(arc.getextremoFinal()) ) {
						listaver.offer(arc.getextremoFinal());
					}
				}
			}
		}
		return listaver;			
    }

    public List<Vertice> predecesores(String id) {
		list<Vertice> listaver = new LinkedList<Vertice>;
		for ( VerticeDir ver : this.lista ) {
			if ( ver.v.getId().equals(id) ) {
				for ( Arco arc : ver.l2 ) {
					if ! ( listaver.contains(arc.getextremoFinal()) ) {
						listaver.offer(arc.getextremoFinal());
					}
				}
			}
		}
		return listaver;			
    }

    public boolean eliminarArco(String id) {
		String verid;
		boolean eliminado = false;
		for ( VerticeDir ver : this.lista ) { 
			for ( Arco arc : ver.l1 ) {
				if ( arc.getId().equals(id) ) {
					verid = arc.getextremoFinal().getId();
					ver.l1.remove(ver.l1.indexOf(arc));		
				}
			}
		}

		for ( VerticeDir ver : this.lista ) {
			if ( ver.v.getId().equals(verid) ) {
				for ( Arco arc : ver.l2 ) {
					if ( arc.getId().equals(id) ) {
						ver.l2.remove(ver.l2.indexOf(arc));
						eliminado = true;
					}
				}
			}
		}
		return eliminado;

    }

    public Arco obtenerArco(String id) {
		for ( VerticeDir ver : this.lista ) {
			for ( Arco arc : ver.l1 ) {
				if ( arc.getId().equals(id) ) {
					return arc;
				}
			}
		}
    }	
}
