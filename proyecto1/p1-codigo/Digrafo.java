/**
 * 
 */

import java.util.*;

public class Digrafo implements Grafo
{
    private LinkedList<Vertice> listaver;
	private LinkedList<LinkedList<Arco>> listaarc;
    
    public Digrafo() {
        listaver = new LinkedList<Vertice>() ;
		listaarc = new LinkedList<LinkedList<Arco>>() ;
    }

    public boolean cargarGrafo(String dirArchivo) {
    }
    
    public int numeroDeVertices() {
        return listaver.size();
    }

    public int numeroDeLados() {
        
    }
   
    public boolean agregarVertice(Vertice v) {
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
