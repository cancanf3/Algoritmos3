/**
 * 
 */

import java.util.*;
import java.io.*;


public class GrafoNoDirigido implements Grafo
{
	private LinkedList<VerticeNoDir> lista;
	private int numArista;
	private int numVertice;
	
    public GrafoNoDirigido() {
		this.lista = new LinkedList<VerticeNoDir>();
		this.numArista = 0;
		this.numVertice = 0;
		
    }

    public boolean cargarGrafo(String dirArchivo) {
	      try{		 
			  BufferedReader Buffer;
			  Buffer = new BufferedReader(new FileReader(new File(dirArchivo)));
			  String str = " ";
			  int ver = Integer.parseInt(Buffer.readLine());
			  int ari =  Integer.parseInt(Buffer.readLine());
			
			for (int i=0 ; i < ver ; i++) {
				str = Buffer.readLine();
				String[] str1 = str.split(" ");
				this.agregarVertice(str1[0],Integer.parseInt(str1[1]));
				mostrar();
			}
			
			for (int i=0 ; i < ari ; i++) {
			
				str = Buffer.readLine();
				String[] str1 = str.split(" ");
				
				this.agregarArista(str1[0],Integer.parseInt(str1[1])
												   ,str1[2],str1[3]);
				System.out.println(" arista "+i);
			}

			System.out.println(this.numVertice);
			mostrar();
	    }catch(IOException e) { 
	    		System.out.println(" Error en la lectura del archivo Archivo"); 
	    		return false;
	    		};
	    		return true;
	  }
    
    public int numeroDeVertices() {
<<<<<<< HEAD

=======
>>>>>>> FETCH_HEAD
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

		lista.offer(new VerticeNoDir(v));
		this.numVertice++;
		return true;
    }

    public boolean agregarVertice(String id, double peso) {
		for ( VerticeNoDir ver : this.lista ) {
			if ( ver.v.getId().equals(id) ) { 
				return false;
			}
		}
		Vertice v = new Vertice(id,peso);	
		this.lista.offer(new VerticeNoDir(v));
		this.numVertice++;
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
				for ( Arista ari : ver.l ) {
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
						if ( ver1.v.getId().equals(id1) ) {
							for ( Arista ari1 : ver1.l ) {
								if ( ari1.getExtremo2().getId().equals(id) ) {
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
<<<<<<< HEAD
    		List<Vertice> listaVer = new LinkedList<Vertice>();
    		for ( VerticeNoDir ver : this.lista ) {
			listaVer.add(ver.v);
=======
    		List<Vertice> listaVer = new List<Vertice>();
    		for ( Vertice ver : this.lista ) {
			listaVer.add(ver);
>>>>>>> FETCH_HEAD
    		}
    		return listaVer;
    }

    public List<Lado> lados() {
<<<<<<< HEAD
    		LinkedList<Lado> listaari = new LinkedList<Lado>();
=======
    		List<Lado> listaari = new LinkedList<Arista>();
>>>>>>> FETCH_HEAD
    		for ( VerticeNoDir ver : this.lista ) {
			for ( Arista ari : ver.l ) {
				boolean esta=false;
				for ( Lado ari1 : listaari ) {
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
<<<<<<< HEAD
			if ( ver.v.getId().equals(id) ) {
				return ver.l.size();
=======

			if ( ver.v.getId().equals(id) ) {
				return ver
>>>>>>> FETCH_HEAD
			} 
		}
		throw new NoSuchElementException();
    }

    public List<Vertice> adyacentes(String id) {
<<<<<<< HEAD
    		LinkedList<Vertice> listaVer = new LinkedList<Vertice>();
=======
    		Lista<Vertices> listaVer = new LinkedList<Vertices>();
>>>>>>> FETCH_HEAD
    		for ( VerticeNoDir ver : this.lista ) {
			if ( ver.v.getId().equals(id) ) {
				if ( !ver.l.isEmpty() ){
					for ( Arista ari : ver.l ) {
						listaVer.offer(ari.getExtremo2());
					}
					return listaVer;
				}
				throw new NoSuchElementException();
			}
    		}
    		return listaVer;
    }
 
    public List<Lado> incidentes(String id) {
<<<<<<< HEAD
		LinkedList<Lado> listaari = new LinkedList<Lado>();
    		for ( VerticeNoDir ver : this.lista ) {
			if ( ver.v.getId().equals(id) ) {
				if ( !ver.l.isEmpty() ){
					for ( Lado ari : ver.l ) {
						listaari.offer(ari);
					}
					return listaari;
=======
    		int[][] matriz = new int[this.lista.size()][this.lista.size()];
    		List<Lado> listaLados = new LinkedList<Arista>();
    		int i,j,k,posVerId;
    		Vertice verId;
    		for ( i = 0 ; i < this.lista.size() ; i++) {
    			for ( j = 0 ; j < this.lista.size() ; j++) {
    				if ( i == j ) {
					matriz[i][i] = 1;
    				}
    				else {
    					matriz[i][j] = 0;
>>>>>>> FETCH_HEAD
				}
				throw new NoSuchElementException();
			}
    		}
    		return listaari;
	}
    public Object clone() {
		GrafoNoDirigido grafoClon = new GrafoNoDirigido();
		for ( VerticeNoDir ver : this.lista ) {
			grafoClon.lista.offer(new VerticeNoDir(new Vertice(ver.v.getId(), 
														 ver.v.getPeso() )));
			for ( Arista a : ver.l ) {
			  grafoClon.lista.getLast().l.offer(new Arista(a.getId(),a.getPeso()
											,a.getExtremo1(),a.getExtremo2()));
			}
		}
		return grafoClon;	
    }

    public String toString() {
    		String str = " El grafo posee "+lista.size()+" vertices y "
    						+numArista+" aristas";
    		return str; 
    }

    public boolean agregarArista(Arista a) {
    		boolean estaV,estaU;
    		estaV=false;
    		estaU=false;
      	VerticeNoDir v1,v2;
      	v1 = null;
      	v2 = null;
      	
      	for ( VerticeNoDir ver : this.lista ) {
			if ( ver.v.getId().equals(a.getExtremo1()) ) {
				v1 = ver;
				estaU=true;
			}
			else if ( ver.v.getId().equals(a.getExtremo2()) ) {
				v2 = ver;
				estaV=true;
			}
			if ( estaU && estaV ) {
				Arista 	a1;
				a1 = new Arista(a.getId(),a.getPeso(),v2.v,v1.v);
				v1.l.offer(a);
				v2.l.offer(a1);
				this.numArista++;	
				return true;	
			}
      	}	
      	return false;
    }

    public boolean agregarArista(String id, double peso, String u, String v) {
      	boolean estaV,estaU;
      	VerticeNoDir v1,v2;		
      	v1 = null;
      	v2 = null;
      	estaV=false;
    		estaU=false;
      	for ( VerticeNoDir ver : this.lista ) {
			if ( ver.v.getId().equals(u) ) {
				v1 = ver;
				estaU=true;
			}
			else if ( ver.v.getId().equals(v) ) {
				v2 = ver;
				estaV=true;
			}
			if ( estaU && estaV ) {
				Arista 	a,a1;
				a = new Arista(id,peso,v1.v,v2.v);
				a1 = new Arista(id,peso,v2.v,v1.v);
				v1.l.offer(a);
				v2.l.offer(a1);
				this.numArista++;	
				return true;	
			}
      	}	
		return false;
    }
    
    public boolean eliminarArista(String id) {
    		int i = 0;
    		for ( VerticeNoDir ver : this.lista ) {
			for ( Arista a : ver.l ) {
				if (	 a.getId().equals(id) ) {
					ver.l.remove(a);
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
		throw new NoSuchElementException();
    }

    public void mostrar()
    {
    
    		System.out.println(this.lista.getLast().v.getId()+" "+this.lista.getLast().v.getPeso());
    }
    public void mostrarLado(Arista a)
    {
    		System.out.println(a.getId()+" "+a.getPeso());
    }
}
