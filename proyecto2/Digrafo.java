/**
 * 
 */

import java.util.*;
import java.io.*;

public class Digrafo implements Grafo
{
    private LinkedList<VerticeDir> lista;
	private int numArco;
    
    public Digrafo() {
        this.lista = new LinkedList<VerticeDir>() ;
		this.numArco = 0;
    }

	public Digrafo(LinkedList<VerticeDir>listaady, int numArco) {						// Constructor opcional para Construir 
		this.numArco = numArco;															// un Grafo con una lista de adyasencias construida
        this.lista = listaady;
    }

	public Digrafo(LinkedList<VerticeDir>listaady) {							// Constructor opcional para Construir 
																				// un Grafo con una lista de adyasencias construida
        this.lista = listaady;
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
				if  ( ! this.agregarVertice(str1[0],Integer.parseInt(str1[1])) )
					System.out.println("puta");
			}
			
			for (int i=0 ; i < ari ; i++) {
			
				str = Buffer.readLine();
				String[] str1 = str.split(" ");
				
				this.agregarArco(str1[0],Integer.parseInt(str1[3]),
									str1[1],str1[2]);
			}

	    }catch(IOException e) { 
	    		System.out.println(" Error en la lectura del archivo Archivo"); 
	    		return false;
	    		};
	    		return true;
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
			if ( ver.v.getId().equals(id) ) {
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
				return ver.v;
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
				for ( Arco arc : ver.l1 ) {
					if ( arc.getExtremoFinal().getId().equals(v) ) {
						return true;
					}
				}
			}
		}
		return false;
    }

    public boolean eliminarVertice(String id) {
		boolean esta = false;
		for ( VerticeDir ver : this.lista ) {
			if ( ver.v.getId().equals(id) ) {
				esta = true;
			}
			else if ( ! ver.l1.isEmpty() ) {
				Iterator<Arco> arc = ver.l1.iterator();
				while ( arc.hasNext() ) {
					if ( arc.next().getExtremoFinal().getId().equals(id) ) {
						arc.remove();
					}
				}
			}
		}

		for ( VerticeDir ver : this.lista ) {
			if ( ! ver.v.getId().equals(id) && ! ver.l2.isEmpty() ) {
				Iterator<Arco> arc = ver.l2.iterator();
				while ( arc.hasNext() ) {
					if ( arc.next().getExtremoFinal().getId().equals(id) ) {
						arc.remove();
					}
				}
			}
		}

		Iterator<VerticeDir> ver = this.lista.iterator();
		while ( ver.hasNext() ) {
			if ( ver.next().v.getId().equals(id) ) {
				ver.remove();
			}
		}
		return esta;


    }

    public List<Vertice> vertices() {
		LinkedList<Vertice> listver = new LinkedList<Vertice>();
		for ( VerticeDir ver : this.lista ) { 
			listver.offer(ver.v);
		}
		return listver;
			
    }

    public List<Lado> lados() {
		List<Lado> listarc = new LinkedList<Lado>();
		for ( VerticeDir ver : this.lista ) {
			for ( Lado arc : ver.l1 ) {
				listarc.add(arc);
			}
		}
		return listarc;
    }

    public int grado(String id) {
		int gout = 0;
		int gin = 0;
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
		if ( ! hay ) {
			throw new NoSuchElementException();
		}
		return gin + gout;
    }

    public List<Vertice> adyacentes(String id) {
		LinkedList<Vertice> listaver = new LinkedList<Vertice>();
		boolean hay = false;	

		for ( VerticeDir ver : this.lista ) {
			if ( ver.v.getId().equals(id) ) {
				hay = true;
				for (Arco arc : ver.l1 ) {
					listaver.offer(arc.getExtremoFinal());
				}
			}
		}
		if ( ! hay ) {
			throw new NoSuchElementException();
		}
		return listaver;
    }
 
    public List<Lado> incidentes(String id) {
		LinkedList<Lado> listaari = new LinkedList<Lado>(); 
		boolean hay = false;
		for ( VerticeDir ver : this.lista ) {
			if ( ver.v.getId().equals(id) ) {
				hay = true;
				for ( Lado arc : ver.l2 ) {
					listaari.offer(arc);
				}
			}
		}
		if ( ! hay ) {
			throw new NoSuchElementException();
	}
		return listaari;
    }

    public Digrafo clone() {
		LinkedList<VerticeDir> listaclon = new LinkedList<VerticeDir>();
		Digrafo digrafoclon;
		int clonnumArco;
		for ( VerticeDir ver : this.lista ) {
			listaclon.offer(new VerticeDir(ver.v));
			for ( Arco arc : ver.l1 ) { 
				listaclon.getLast().l1.offer(new Arco(arc.getId(),arc.getPeso(),
											 arc.getExtremoInicial(),
											 arc.getExtremoFinal()));
			}

			for ( Arco arc : ver.l2 ) {
				listaclon.getLast().l2.offer(new Arco(arc.getId(),arc.getPeso(),
											arc.getExtremoFinal(),
											arc.getExtremoInicial()));
			}
		}
		clonnumArco = this.numArco;
		digrafoclon = new Digrafo(listaclon, clonnumArco);	
		return digrafoclon;
	}

    public String toString() {
		return "hola";
    }

    public boolean agregarArco(Arco a) {
		boolean esta = false;
		for ( VerticeDir ver : this.lista ) {
			if  ( ver.v.getId().equals(a.getExtremoInicial().getId()) ) {
				for ( Arco arc : ver.l1 ) {
					if	( arc.getId().equals(a.getId()) ) {
						esta = true;
					}
				}
				if ( ! esta ) { 
					ver.l1.offer(a);
				}
				
			}
		}

		for ( VerticeDir ver : this.lista ) {
			if ( ver.v.getId().equals(a.getExtremoFinal().getId()) ) {  
				ver.l2.offer(new Arco(a.getId(), a.getPeso(),
							a.getExtremoFinal(), a.getExtremoInicial() ));
			}
		}
		this.numArco ++;
		return esta;
    } 

    public boolean agregarArco(String id, double peso, String u, String v) { 
		boolean esta = false;
		Vertice ver1 = null;
		Vertice ver2 = null;
		Arco arco1 = null;
		Arco arco2 = null;
		for ( VerticeDir ver : this.lista ) { 
			if ( ver.v.getId().equals(u) ) {
				ver1 = new Vertice(ver.v.getId(), ver.v.getPeso());
			}

			if ( ver.v.getId().equals(v) ) {
				ver2 = new Vertice(ver.v.getId(), ver.v.getPeso());
			}
		}
		arco1 = new Arco( id, peso, ver1, ver2);
		arco2 = new Arco( id, peso, ver2, ver1);

		for ( VerticeDir ver : this.lista ) {
			if ( ver.v.getId().equals(u) ) {
				for ( Arco arc : ver.l1 ) {
					if ( arc.getId().equals(id) ) {
						esta = true;
					}
				}
				if ( ! esta )  {
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
		throw new NoSuchElementException();
    }

    public int gradoExterior(String id) {
		for ( VerticeDir ver : this.lista ) {
			if ( ver.v.getId().equals(id) ) {
				return ver.l1.size();
			}
		}
		throw new NoSuchElementException();
    }

    public List<Vertice> sucesores(String id) {
		LinkedList<Vertice> listaver = new LinkedList<Vertice>();
		for ( VerticeDir ver : this.lista ) {
			if ( ver.v.getId().equals(id) ) {
				for ( Arco arc : ver.l1 ) {
					if ( ! listaver.contains(arc.getExtremoFinal()) ) {
						listaver.offer(arc.getExtremoFinal());
					}
				}
				return listaver;
			}
		}
		throw new NoSuchElementException();
    }

    public List<Vertice> predecesores(String id) {
		LinkedList<Vertice> listaver = new LinkedList<Vertice>();
		for ( VerticeDir ver : this.lista ) {
			if ( ver.v.getId().equals(id) ) {
				for ( Arco arc : ver.l2 ) {
					if ( ! listaver.contains(arc.getExtremoFinal()) ) {
						listaver.offer(arc.getExtremoFinal());
					}
				}
				return listaver;
			}
		}
		throw new NoSuchElementException();
    }

    public boolean eliminarArco(String id) {
		String verid = "";
		Arco arc1;
		boolean eliminado = false;
		for ( VerticeDir ver : this.lista ) { 
			Iterator<Arco> arc = ver.l1.iterator();
			while ( arc.hasNext() ) {
				arc1 = arc.next();
				if ( arc1.getId().equals(id) ) {
					verid = arc1.getExtremoFinal().getId(); 
					arc.remove();
					break;
				}
			}
		}

		for ( VerticeDir ver : this.lista ) {
			if ( ver.v.getId().equals(verid) ) {
				Iterator<Arco> arc = ver.l2.iterator();
				while ( arc.hasNext() ) {
					if ( arc.next().getId().equals(id) ) {
						arc.remove();
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
		throw new NoSuchElementException();

    }	
}
