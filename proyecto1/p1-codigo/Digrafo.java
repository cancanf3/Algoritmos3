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
    }

	public Digrafo(LinkedList<VerticeDir>listaady) {							// Constructor opcional para Construir 
																				// un Grafo con una lista de adyasencias construida
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
		digrafoclon = new Digrafo(listaclon);	
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
    } 

    public boolean agregarArco(String id, double peso){ 
    }

    public int gradoInterior(String id) {
    		return 0;
    }

    public int gradoExterior(String id) {
    }

    public List<Vertice> sucesores(String id) {
    }

    public List<Vertice> predecesores(String id) {
    }

    public boolean eliminarArco(String id) {
    }

    public Arco obtenerArco(String id) {
    }	
}
