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

	public Digrafo(LinkedList<VerticeDir>listaady, int numArco) {						// Constructor opcional para Construir 
		this.numArco = numArco;															// un Grafo con una lista de adyasencias construida
        this.lista = listaady;
    }

	public Digrafo(LinkedList<VerticeDir>listaady) {							// Constructor opcional para Construir 
																				// un Grafo con una lista de adyasencias construida
        this.lista = listaady;
    }

    public boolean cargarGrafo(String dirArchivo) {
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
		LinkedList<Vertice> aux1 = new LinkedList<Vertice>();
		LinkedList<Vertice> aux2 = new LinkedList<Vertice>();
		boolean esta = false;
		for ( VerticeDir ver : this.lista ) {
			if ( ver.v.getId().equals(id) ) {
				esta = true;
				for ( Arco arc : ver.l1 ) {
					if ( arc.getExtremoInicial() == arc.getExtremoFinal() ) {
						aux2.offer(arc.getExtremoInicial() );
					}
					else {
						aux2.offer(arc.getExtremoFinal());
						aux2.offer(arc.getExtremoInicial());
						ver.l1.remove(ver.l1.indexOf(arc));
					}
				}	
			}
		}

		while ( aux2.isEmpty() ) {
			LinkedList<Arco> arcox1 = this.lista.get(this.lista.indexOf(aux2.poll())).l2;
			for ( Arco arc :  arcox1 ) {
				if ( arc.getExtremoFinal().getId().equals(id) ) { 
					arcox1.remove(arcox1.indexOf(arc));
				}
				else if ( arc.getExtremoInicial().getId().equals(id) ) {
					aux1.offer(arc.getExtremoFinal());
					arcox1.remove(arcox1.indexOf(arc));
				}
			}
		}

		while ( aux1.isEmpty() ) {
			LinkedList<Arco> arcox1 = this.lista.get(this.lista.indexOf(aux1.poll())).l1;
			for ( Arco arc : arcox1 ) {
				if ( arc.getExtremoFinal().getId().equals(id) ) {
					arcox1.remove(arcox1.indexOf(arc));
				}
			}
		}
		return esta;


    }

    public List<Vertice> vertices() {
<<<<<<< HEAD
		List<Vertice> listver = new LinkedList<Vertice>();
=======
		LinkedList<Vertice> listver = new LinkedList<Vertice>();
>>>>>>> FETCH_HEAD
		for ( VerticeDir ver : this.lista ) { 
			listver.offer(ver.v);
		}
		return listver;
<<<<<<< HEAD

    }

    public List<Lado> lados() {
		List<Lado> listarc = new LinkedList<Arco>();
		for ( VerticeDir ver ; this.lista ) {
			for ( Arco arc ; this.lista ) {
				listarc.offer(arc);
=======
			
    }

    public List<Lado> lados() {
		List<Lado> listarc = new LinkedList<Lado>();
		for ( VerticeDir ver : this.lista ) {
			for ( Lado arc : ver.l1 ) {
				listarc.add(arc);
>>>>>>> FETCH_HEAD
			}
		}
		return listarc;
    }

    public int grado(String id) {
<<<<<<< HEAD
		int gout;
		int gin;
=======
		int gout = 0;
		int gin = 0;
>>>>>>> FETCH_HEAD
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
<<<<<<< HEAD
		if ! hay {
=======
		if ( ! hay ) {
>>>>>>> FETCH_HEAD
			throw new NoSuchElementException();
		}
		return gin + gout;
    }

    public List<Vertice> adyacentes(String id) {
<<<<<<< HEAD
		List<Vertice> listaver = new LinkedList<Vertice>();
=======
		LinkedList<Vertice> listaver = new LinkedList<Vertice>();
>>>>>>> FETCH_HEAD
		boolean hay = false;	

		for ( VerticeDir ver : this.lista ) {
			if ( ver.v.getId().equals(id) ) {
				hay = true;
				for (Arco arc : ver.l1 ) {
<<<<<<< HEAD
					listaver.addLast(arc.getextremoFinal());
				}
			}
		}
		if ! hay {
=======
					listaver.addLast(arc.getExtremoFinal());
				}
			}
		}
		if ( ! hay ) {
>>>>>>> FETCH_HEAD
			throw new NoSuchElementException();
		}
		return listaver;
    }
 
    public List<Lado> incidentes(String id) {
<<<<<<< HEAD
		List<Lado> listaari = new LinkedList<Arco>(); 
=======
		LinkedList<Lado> listaari = new LinkedList<Lado>(); 
>>>>>>> FETCH_HEAD
		boolean hay = false;
		for ( VerticeDir ver : this.lista ) {
			if ( ver.v.getId().equals(id) ) {
				hay = false;
<<<<<<< HEAD
				for ( Arco arc : ver.l2 ) {
					listaari.addLast(arc.getextremoFinal());
				}
			}
		}
		if ! hay {
=======
				for ( Lado arc : ver.l2 ) {
					listaari.offer(arc);
				}
			}
		}
		if ( ! hay ) {
>>>>>>> FETCH_HEAD
			throw new NoSuchElementException();
	}
		return listaari;
    }

    public Object clone() {
		LinkedList<VerticeDir> listaclon = new LinkedList<VerticeDir>();
<<<<<<< HEAD
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
=======
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
>>>>>>> FETCH_HEAD
		return digrafoclon;
	}

    public String toString() {
		return "hola";
    }

    public boolean agregarArco(Arco a) {
		boolean esta = false;
		for ( VerticeDir ver : this.lista ) {
<<<<<<< HEAD
			if ver.v.getId().equals(a.getextremoInicial().getId()) {
=======
			if  ( ver.v.getId().equals(a.getExtremoInicial().getId()) ) {
>>>>>>> FETCH_HEAD
				for ( Arco arc : ver.l1 ) {
					if	( arc.getId().equals(a.getId()) ) {
						esta = true;
					}
				}
<<<<<<< HEAD
				if ! esta { 
					ver.l1.offer(a);
				}
			}	
			}
=======
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
>>>>>>> FETCH_HEAD
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
		boolean eliminado = false;
		for ( VerticeDir ver : this.lista ) { 
			for ( Arco arc : ver.l1 ) {
				if ( arc.getId().equals(id) ) {
					verid = arc.getExtremoFinal().getId();
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
		throw new NoSuchElementException();

    }	
}
