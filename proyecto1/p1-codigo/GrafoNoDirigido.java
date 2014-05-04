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
		return listaver.size();
    }

    public int numeroDeLados() {
		int total = 0;
		for ( LinkedList<Arista> sublista : listaari ) {
			total = sublista.size();
		}
		return total;
    }
   
    public boolean agregarVertice(Vertice v) {
		for ( Vertice ver : listaver ) {
			if ( ver.getId().equals(v.getId()) ) { 
				return false;
			}
		}
			listaver.offer(v);
			return true;
		
    }

    public boolean agregarVertice(String id, double peso) {
    }
    
    public Vertice obtenerVertice(String id) {
    }

    public boolean estaVertice(String id) {
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
