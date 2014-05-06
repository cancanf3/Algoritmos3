/**
 * 
 */

import java.util.*;


public class GrafoNoDirigido implements Grafo
{
	private LinkedList<VerticeNoDir> lista;
	private int numArista;
	
    public GrafoNoDirigido() {
		lista = new LinkedList<VerticeNoDir>();
		
    }

    public boolean cargarGrafo(String dirArchivo) {
    }
    
    public int numeroDeVertices() {
		return lista.size();
    }

    public int numeroDeLados() {
		return numArista;
    }
   
    public boolean agregarVertice(Vertice v) {
		for ( VerticeNoDir ver : lista ) {
			if ( ver.v.getId().equals(v.getId()) ) { 
				return false;
			}
		}

		lista.offer(new VerticeNodir(v));
		return true;
    }

    public boolean agregarVertice(String id, double peso) {
		for ( VerticeNoDir ver : this.lista ) {
			if ( ver.v.getId().equals(id) ) { 
				return false;
			}
		}
		Vertice v = new Vertice(id,peso);	
		this.lista.offer(new VerticeNodir(v));
		return true;
    }
    
    public Vertice obtenerVertice(String id) {
		for ( VerticeNoDir ver : this.lista ) {
			if ( ver.v.getId().equals(id) ) { 
				return ver.v;
			}
		}
		throw new NoSuchElementException();
    }

    public boolean estaVertice(String id) {
		for ( VerticeNoDir ver : this.lista ) {
			if ( ver.v.getId().equals(id) ) { 
				return true;
			}
		}
		return false;

    }

    public boolean estaLado(String u, String v){
    		for ( VerticeNoDir ver : this.lista ){
			if ( ver.v.getId().equals(u) ){
				for ( Arista ari : this.lista.l ) {
					if ( ari.getExtremo2().getId().equals(v) ) {
						return true;
					}
				}
			}
    		}
    		return false;
    		
    }

    public boolean eliminarVertice(String id) {
    		for ( VerticeNoDir ver : this.lista ) {
			if ( ver.v.getId().equals(id) ) {
				for ( Arista ari : ver.l ) {
					String id1 = ari.getExtremo2().getId();
					for ( VerticeNoDir ver1 : this.lista ) {
						if ( ver1.getId().equals(id1) ) {
							for ( Arista ari1 : ver1.l ) {
								if ( ari1.getExtremo2.getId().equals(id) ) {
									ver1.l.remove(ver1.l.indexOf(ari1));	
								}
							}
								
						}			 
					}
				}
				this.lista.remove(lista.indexOf(ver));	
				return true;		
 	   		}
    		}
    		return false;
	}
    public List<Vertice> vertices() {
    		List<Vertice> listaVer = new List<Vertice>;
    		for ( Vertice ver : this.lista ) {
			listaVer.add(ver);
    		}
    		return listaVer();
    }

    public List<Lado> lados() {
    		List<Lado> listaari = new LinkedList<Arista>;
    		for ( VerticeNoDir ver : this.lista ) {
			for ( Arista ari : ver.l ) {
				bool esta=false;
				for ( Arista ari1 : listaari ) {
					if ( ari1.getId().equals(ari.getId()) ) {
						esta=true;
						break;
					}
				}
				if (!esta) {
					listaari.offer(ari);
				}
			}
		}
		return listaari;
    }

    public int grado(String id) {
    		for ( VerticeNoDir ver : this.lista ) {
			if ( ver.v.getId().equals(id) ) {
				return ver
			} 
    		}
    		
    }

    public List<Vertice> adyacentes(String id) {
    }
 
    public List<Lado> incidentes(String id) {
    }

    public Object clone() {
    }

    public String toString() {
    }

    public boolean agregarArista(Arista a) {
    }

    public boolean agregarArista(String id, double peso, String u, String v) {
    }

    public boolean eliminarArista(String id) {
    }

    public Arista obtenerArista(String id) {
    }
}
