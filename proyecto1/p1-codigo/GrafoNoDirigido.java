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
		return this.lista.size();
    }

    public int numeroDeLados() {
		return numArista;
    }
   
    public boolean agregarVertice(Vertice v) {
		for ( VerticeNoDir ver :this.lista ) {
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
				boolean esta=false;
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
    		Lista<Vertices> listaVer = new LinkedList<Vertices>;
    		for ( VerticeNoDir ver : this.lista ) {
			if ( ver.v.getId().equals(id) ) {
				for ( Arista ari : ver.l ) {
					listaVer.offer(ari.getExtremo2());
				}
			}
    		}
    		return listaVer;
    }
 
    public List<Lado> incidentes(String id) {
    		int[][] matriz = new int[this.lista.size()][this.lista.size()];
    		List<Lado> listaLados = new LinkedList<Arista>;
    		int i,j,k,posVerId;
    		Vertice verId;
    		for ( i = 0 ; i < this.lista.size() ; i++) {
    			for ( j = 0 ; j < this.lista.size() ; j++) {
    				if ( i == j ) {
					matriz[i][i] = 1;
    				}
    				else {
    					matriz[i][j] = 0;
				}
			}
		}
		
    		i=0;
    		j=0;
    		for ( VerticeNoDir ver : this.lista ) { 
    			if ( ver.v.getId().equals(id) ) {
				verId = ver.v;
				posVerId = i;
    			}
			for ( Arista  ari : this.lista.l ) {
				j = this.lista.indexOf(ari.getExtremo2());
				matriz[i][j] = 1;;	
			}
			i++;
    		}

    		for ( k = 0 ; k < this.lista.size() ; k++) {
	    		for ( i = 0 ; i < this.lista.size() ; i++) {			
				if ( k != i && matriz[i][k] == 1 ) {
    					for ( j = 0 ; j < this.lista.size() ; j++) {
						matriz[i][j] = matriz[i][j] + matriz[k][j];
    					}
    				}
    			}
		}

    	
    		for ( i = 0 ; i < this.lista.size() ; i++) {
				if ( matriz[posVerId][i] == 1 ) {
						Arista ari = new Arista( verId, this.lista.get(i) );
						listaLados.offer(ari);
				}
    		}
    				 
    		
    }

    public Object clone() {
		GrafoNoDirigida g2;
		
    		for (
    }

    public String toString() {
    		
    }

    public boolean agregarArista(Arista a) {
    		int v1,v2;
    		v1 = this.lista.indexOf(a.getExtremo1);
    		v2 = this.lista.indexOf(a.getExtremo2);
    		if ( v1 > 0 && v2 > 0 ) {
			Arista a1 = new Arista( a.getId(),a.getPeso(),
								   a.getExtremo2(),a.getExtremo2() );
		    this.lista.get(v1).l.offer(a);
			this.lista.get(v2).l.offer(a1);	
			return true;			
    		}
    		else {
			return false;
    		}
    }

    public boolean agregarArista(String id, double peso, String u, String v) {
      	int v1,v2;
      	boolean estaV,estaU;
      	Vertice v1,v2;
      	for ( Vertice ver : this.lista ) {
			if ( ver.v.getId().equals(u) ) {
				v1 = ver.v;
				estaU=true;
			}
			else if ( ver.v.getId().equals(v) ) {
				v2 = ver.v;
				estaV=true;
			}
			if ( estaU && estanV ) {
				break;
			}
      	}
    		
    		if ( estaU && estanV ) {
    			Arista 	a,a1;
    			a = new Arista(id,peso,v1,v2);
    			a1 = new Arista(id,peso,v2,v1);
		    this.lista.get(v1).l.offer(a);
			this.lista.get(v2).l.offer(a1);	
			return true;			
    		}
    		else {
			return false;
    		}
    
    }
    
    public boolean eliminarArista(String id) {
    		int cond = 0;
    		for ( VerticeNoDir ver : this.lista ) {
			for ( Arista a : ver.l ) {
				if (	 a.getId().equals(id) ) {
					this.lista.l.remove(a);
					i++;
				}
				else if ( i == 2 ) {
					return true;
				}
			}
    		}
    		return false;
    }

    public Arista obtenerArista(String id) {
    		for ( VerticeNoDir ver : this.lista ) {
			for ( Arista a : ver.l ) {
				if ( a.getId().equals(id) ) {
					return a;
				}
			}
    		}
    }
}
