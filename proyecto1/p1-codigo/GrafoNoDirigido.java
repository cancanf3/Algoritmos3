/**
 * 
 */

import java.util.*;

public class GrafoNoDirigido implements Grafo
{
	private LinkedList<Vertice> listaver;
	private LinkedList<LinkedList<Arista>> listaari;

    public GrafoNoDirigido() {
		listaver = new LinkedList<Vertice>() ;
		listaari = new LinkedList<LinkedList<Arista>>() ;
    }

    public boolean cargarGrafo(String dirArchivo) {
    }
    
    public int numeroDeVertices() {
		return this.listaver.size();
    }

    public int numeroDeLados() {
		int total = 0;
		for ( LinkedList<Arista> sublista : this.listaari ) {
			total = sublista.size();
		}
		return total;
    }
   
    public boolean agregarVertice(Vertice v) {
		for ( Vertice ver : this.listaver ) {
			if ( ver.getId().equals(v.getId()) ) { 
				return false;
			}
		}
		this.listaver.offer(v);
		return true;
		
    }

    public boolean agregarVertice(String id, double peso) {
		for ( Vertice ver : this.listaver ) {
			if ( ver.getId().equals(id) ) { 
				return false;
			}
		}
		Vertice v = new Vertice(id,peso);	
		this.listaver.offer(v);
		return true;
    }
    
    public Vertice obtenerVertice(String id) {
		for ( Vertice ver : this.listaver ) {
			if ( ver.getId().equals(id) ) { 
				return ver;
			}
		}
		throw new NoSuchElementException();
    }

    public boolean estaVertice(String id) {
		for ( Vertice ver : this.listaver ) {
			if ( ver.getId().equals(id) ) { 
				return true;
			}
		}
		return false;

    }

    public boolean estaLado(String u, String v){
    }

    public boolean eliminarVertice(String id) {
    }

    public List<Vertice> vertices() {
    }

    public List<Lado> lados() {
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

    public boolean agregarArista(Arista a) {
    }

    public boolean agregarArista(String id, double peso, String u, String v) {
    }

    public boolean eliminarArista(String id) {
    }

    public Arista obtenerArista(String id) {
    }
}
