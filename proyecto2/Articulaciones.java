/**
 * 
 */

import java.util.*;
import java.io.*;


public class Articulaciones implements Grafo
{
	private LinkedList<VerticeNoDir> lista;
	private int numArista;
	private int numVertice;
	LinkedList<String> articulados = new LinkedList<String>();
	private int cont = 0;
    public Articulaciones() {
		this.lista = new LinkedList<VerticeNoDir>();
		this.numArista = 0;
		this.numVertice = 0;
		
    }

	public static void main ( String [ ] args ) {
		try {
			  BufferedReader Buffer;
			  Buffer = new BufferedReader(new FileReader(new File(args[2])));
			  String str = " ";
			  int ver = Integer.parseInt(Buffer.readLine());
			  int ari =  Integer.parseInt(Buffer.readLine());
			  Articulaciones grafoarti = new Articulaciones();
			
			for (int i=0 ; i < ver ; i++) {
				str = Buffer.readLine();
				String[] str1 = str.split(" ");
				grafoarti.agregarVertice(str1[0],Integer.parseInt(str1[1]));
			}
			
			for (int i=0 ; i < ari ; i++) {
			
				str = Buffer.readLine();
				String[] str1 = str.split(" ");
				
				grafoarti.agregarArista(str1[0],Integer.parseInt(str1[3]),
									str1[1],str1[2]);
				System.out.println(" arista "+i);
			}

			System.out.println(grafoarti.numVertice);
			grafoarti.caminoarticulacion(args[0], args[1]);

	    }catch(IOException e) { 
	    		System.out.println(" Error en la lectura del archivo Archivo"); 
	    		};

	}

	public void caminoarticulacion ( String ini, String fin ) {	
		LinkedList<String> articulados = new LinkedList<String>();

		for ( VerticeNoDir ver : this.lista ) {
			if ( ! ver.visitado ) {
				dfs_articulaciones(ver);
			}
		}
			
		String str = ""; 	
		while ( ! this.articulados.isEmpty() ) {
			String arti = this.articulados.poll();
			for ( VerticeNoDir ver : this.lista ) {
				if ( ver.v.getId().equals(arti) ) {
					ver.articulacion = true;
				}
			}
			str = str + " " + arti;
		}
		System.out.println(str);

		dfs_camino(ini,fin);

	}

	private void  dfs_camino ( String ini, String fin) { 
		boolean valor = false;
		cont = 0;
		for ( VerticeNoDir ver : this.lista ) {
			ver.visitado = false;
		}
		for ( VerticeNoDir ver : this.lista ) {
			if ( ver.v.getId().equals(ini) ) {
				valor = dfs_camino_visitar(ver, fin, valor);
				if ( ! valor ) {
					System.out.println("No");
				}
			}
		}
	}

	private boolean dfs_camino_visitar ( VerticeNoDir ver, String fin, 
										boolean valor) {
		ver.visitado = true;
		cont ++;
		if ( ver.v.getId().equals(fin) ) {
			System.out.println("Si");
			valor = true;
			return valor;
			
		}
		else{
			for ( Arista ari : ver.l ) {
				String str = ari.getExtremo2().getId();
				for ( VerticeNoDir wer : this.lista ) {
					if ( ! wer.visitado && str.equals(wer.v.getId()) ) {
						if ( ! wer.articulacion || wer.v.getId().equals(fin) ) {
							valor = dfs_camino_visitar (wer, fin, valor);
							if ( valor ) {
								break;
							}
						}
					}
				}
			}
			return valor;
		}
	}

	private void dfs_articulaciones ( VerticeNoDir ver ) {
		ver.visitado = true;
		cont ++;
		ver.numbusq = cont;
		ver.masbajo = ver.numbusq;
		int novisit = ver.l.size();
		int apuntapadre = 0; 
		for ( Arista ari : ver.l ) {
			String str = ari.getExtremo2().getId();
			for ( VerticeNoDir wer : this.lista ) {
				if ( ( ! wer.visitado && str.equals(wer.v.getId()) ) ) {
					wer.padre = ver.v.getId();
					apuntapadre ++;
					novisit = novisit - 1;
					dfs_articulaciones(wer);
					System.out.println(ver.v.getId() + ver.numbusq + " = "  + wer.v.getId() + wer.masbajo);
					if ( ( wer.masbajo >= ver.numbusq && ! ver.padre.equals("")) 
						^ ( ver.padre.equals("") 
						&& ( apuntapadre > 1 ^ novisit > 0 ) ) ) {
						this.articulados.add(ver.v.getId());
					}

					ver.masbajo = Math.min ( ver.masbajo, wer.masbajo );
				}
				else if ( str.equals(wer.v.getId() ) ) {
				
					if ( ver.numbusq > wer.numbusq && 
						! ver.padre.equals(wer.v.getId() ) ) {
					System.out.println(ver.v.getId() + " " + ver.masbajo + " " + wer.numbusq );
					ver.masbajo = Math.min ( ver.masbajo, wer.numbusq );
					}	
				}
			}
		}

	}

	private void dfs_sucesores () {
		for ( VerticeNoDir ver : this.lista ) {
			if ( ver.visitado == false ) {
				dfs_sucesores_visitar(ver);
			}
		}

	}
	
	private void dfs_sucesores_visitar ( VerticeNoDir ver ) {
		ver.visitado = true;
		for ( Arista  ari : ver.l ) {
			String str =  ari.getExtremo2().getId();
			for ( VerticeNoDir wer : this.lista ) {
				if ( wer.v.getId().equals(str) && ! wer.visitado ) {
					wer.padre = ver.v.getId();
					ver.sucesores ++;
					dfs_sucesores_visitar(wer);
				}
			}
		}
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
				
				this.agregarArista(str1[0],Integer.parseInt(str1[3]),
									str1[1],str1[2]);
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
    		LinkedList<Vertice> listaVer = new LinkedList<Vertice>();
    		for ( VerticeNoDir ver : this.lista ) {
			listaVer.add(ver.v);

    		}
    		return listaVer;
    }

    public List<Lado> lados() {
    		LinkedList<Lado> listaari = new LinkedList<Lado>();

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
			if ( ver.v.getId().equals(id) ) {
				return ver.l.size();

			} 
		}
		throw new NoSuchElementException();
    }

    public List<Vertice> adyacentes(String id) {
    		LinkedList<Vertice> listaVer = new LinkedList<Vertice>();

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

		LinkedList<Lado> listaari = new LinkedList<Lado>();
    		for ( VerticeNoDir ver : this.lista ) {
			if ( ver.v.getId().equals(id) ) {
				if ( !ver.l.isEmpty() ) {
					for ( Lado ari : ver.l ) {
						listaari.offer(ari);
					}
					return listaari;
				}
				throw new NoSuchElementException();
			}
    		}
    		return listaari;
	}
    public Articulaciones clone() {
		Articulaciones grafoClon = new Articulaciones();
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
