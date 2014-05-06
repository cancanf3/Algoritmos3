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
		LinkedList<Vertice> aux1 = new LinkedList<Vertice>;
		LinkedList<Vertice> aux2 = new LinkedList<Vertice>;
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
		List<Vertice> listver = new LinkedList<Vertice>;
		for ( VerticeDir ver : this.lista ) { 
			listver.offer(ver.v);
		}
		return listver;
			
    }

    public List<Lado> lados() {
		List<Lado> listarc = new LinkedList<Arco>;
		for ( VerticeDir ver ; this.lista ) {
			for ( Arco arc ; this.lista ) {
				listarc.offer(arc);
			}
		}
		return listarc;
    }

    public int grado(String id) {
    }

    public List<Vertice> adyacentes(String id) {
    }
 
    public List<Lado> incidentes(String id) {
    }

    public Object clone() {
    }

    public String toString() {
    }

    public boolean agregarArco(Arco a) {
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
