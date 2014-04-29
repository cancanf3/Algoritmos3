/**
 * 
 */

import java.util.*;

public class GrafoNoDirigido implements Grafo
{
    public GrafoNoDirigido() {
    }

    public boolean cargarGrafo(String dirArchivo) {
    }
    
    public int numeroDeVertices() {
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

    public boolean agregarArista(Arista a) {
    }

    public boolean agregarArista(String id, double peso, String u, String v) {
    }

    public boolean eliminarArista(String id) {
    }

    public Arista obtenerArista(String id) {
    }
}