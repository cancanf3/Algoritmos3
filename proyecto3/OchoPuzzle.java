
/**
 * Proyecto 3 8-Puzzle
 * Carlos Spaggiari      11-10987
 * Jose Peña             11-10775
 */


import java.util.Scanner;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.lang.System;
import java.lang.Math;
import java.io.File;
import java.io.IOException;

/**
 * Clase comparador de la clase Estado.
 */
class ComparadorEstado implements Comparator<Estado>
{
    /**
     * Compara dos classes Estado segun su funcion estimativa
     * Precondicion:	True
     * Postcondicion:   True
     *
     * @param estado1   primer estado a ser comparado
     * @param estado2   segundo estado a ser comparado
     * @return			-1 	si estado1.f  < estado2.f,
     *                   0  si estado1.f == estado2.f,
     *                   1 	si estado1.f  > estado2.f,
     */
    public int compare(Estado estado1, Estado estado2)
    {
        if (estado1.f < estado2.f)
        {
        	return -1;
        }
		else if (estado1.f == estado2.f)
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }
}

/**
 * Clase Estado que representa un nodo en el grafo para resolver 8-Puzzle
 */
class Estado
{
	int[]  				piezas;      // las 9 piezas del Estado.
	int     			posBlanco;   // posicion del blanco ("0") en el Estado.
    int     			f;           // funcion estimativa de la heuristica.
    int 				id;			 // Id de la configuracion del tablero
    LinkedList<Integer> camino;		 // Lista del camino desde el inicio.

    
    
    
    /**
     * Constructor por defecto de la clase Estado
     */
	public Estado()
	{
		piezas     = new int[9];
        f          = 0;
		posBlanco  = 0;
        camino     = new LinkedList<Integer>();
	}
    
    
    
    /**
     * Cargar valores
     * Precondicion:	True
     * Postcondicion:   True
     *
     * @param file	    string del nombre del archivo.
     */
	public void cargarEstado(String file)
	{
		try{
			Scanner sn = new Scanner(new File(file));
			
			for (int i = 0;i < 9 ; i++)
			{
				piezas[i] = sn.nextInt();
				if (piezas[i] == 0)
                {
					posBlanco = i;
                }
			}
		}
        catch(IOException e){}
	}
	
    
    
    /**
     * Imprime por consola el Estado.
     * Precondicion:	True
     * Postcondicion:   True
     */
	public void imprimir()
	{
		for(int i = 0 ; i < 3 ; i++)
		{
			for(int j = 0 ; j < 3 ; j++)
            {
				System.out.print(piezas[i * 3 + j] + " ");
			}
            System.out.println();
		}
		System.out.println();
	}
    
    
    
    /**
	 * Verifica si se puede resolver el estado actual.
     * Precondicion:	True
     * Postcondicion:   FORALL( i : 0 <= i < 8 :
     *                         FORALL( j : 0 <= j <= 8-i :
     *                                (piezas[i] < piezas[j] && piezas[j] != 0)
     *								   => nInversiones = nInversiones' + 1 ))
	 *
     * @return			True si el numero de inversiones es par, False si no.
	 */
    public boolean sePuedeResolver()
	{
		int nInversiones = 0;
        
		for(int i = 8 ; i >= 0 ; i--)
		{
			if (piezas[i] != 0)
			{
				for(int j = 0 ; j < i ; j++)
				{
					if ( piezas[i] < piezas[j] && piezas[j] != 0 )
					{
						nInversiones++;
					}
				}
			}
		}
        
		if (nInversiones % 2 != 0)
        {
			return false;
        }
		return true;
	}
	
    
    
    
	/**
     * Calcula la heurista del estado, usando el atributo hueristica para
     * decidir cual de las cuatro heuristicas disponibles se calculara.
     * Precondicion: 	True
     * Postcondicion: 	True
     *
     * @param h			String que define la heuristica que se utiliza
     * @return			El valor de la hueristica estimada.
     */
	public int calcularHeuristica(String h)
	{
        
		int heuristica = 0;
        
		if (h.equals("m"))
		{
			for (int i = 1 ; i < 9 ; i++)
			{
				if (piezas[i] !=0)
				{
					int x1 = i % 3;
					int y1 = i / 3;
					int x2 = (piezas[i] - 1) % 3;
					int y2 = (piezas[i] - 1) / 3;
                    
					heuristica += Math.abs(x1 - x2) + Math.abs(y1 - y2);
				}
			}
		}
		else if(h.equals("d"))
		{
			for (int i = 0 ; i < 9 ; i++)
			{
				if (piezas[i] != 0 && piezas[i] == i + 1)
				{
					heuristica++;
				}
			}
		}
		else if (h.equals("b"))
		{
			int x1 = posBlanco % 3;
			int y1 = posBlanco / 3;
            
			heuristica += Math.abs(x1 - 2) + Math.abs(y1 - 2);
		}
		return heuristica;
	}
    
    
    
    /**
     * Modifica el Estado segun la accion dada.
     * Precondicion:	1 <= accion <= 4
     * Postcondicion:   Modifica el arreglo de piezas de acuerdo a la accion
     *					dada, si puede realizarze la misma.
     *
     * @param accion	Numero que representa la accion a tomar.
     * @return			True si se pudo realizar la accion, False si no.
     */
	public boolean accion(int accion)
	{
		// Acciones
		// 1: mover arriba.
		// 2: mover derecha.
		// 3: mover abajo.
		// 4: mover izquierda.
		int y = posBlanco / 3;
		int x = posBlanco % 3;
        
		if (accion == 1 && y-1 >= 0)
		{
			piezas[posBlanco] = piezas[posBlanco - 3];
			piezas[posBlanco - 3] = 0;
			posBlanco -= 3;
			return true;
		}
		if (accion == 2 && x+1 < 3)
		{
			piezas[posBlanco] = piezas[posBlanco + 1];
			piezas[posBlanco + 1] = 0;
			posBlanco += 1;
			return true;
		}
		if (accion == 3 && y+1 < 3)
		{
			piezas[posBlanco] = piezas[posBlanco + 3];
			piezas[posBlanco + 3] = 0;
			posBlanco += 3;
			return true;
		}
		if (accion == 4 && x-1 >= 0)
		{
			piezas[posBlanco] = piezas[posBlanco - 1];
			piezas[posBlanco - 1] = 0;
			posBlanco -= 1;
			return true;
		}
        
		return false;
	}
    
    
    /**
     * Copia los atributos de otra clase Estado.
     * Precondicion::  fuente != null
     * Postcondicion:: FORALL(i : 0 <= i < 9 : piezas[i] == fuente.piezas[i])
     *                 && FORALL( i : i in fuente.camino :
     *                           FORALL( j : j in camino : i==j ))
     *                 && (heuristica == fuente.heuristica)  && (f == fuente.f)
     *                 && (posBlanco == fuente.posBlanco)
     *
     * @param fuente   Estado de cual se compiaran los atributos copiar.
     */
    void copiar(Estado fuente)
    {
        for (int i = 0 ; i < 9 ; i++)
        {
            piezas[i] = fuente.piezas[i];
        }
        posBlanco = fuente.posBlanco;
        for (Integer i : fuente.camino)
        {
           	camino.offer(i);
        }
    }
    
    
    
    /**
     * Metodo que establece una funcion inyectica entre los estados y los
     * numeros naturales, dando asi un ID unico a cada combinacion del puzzle.
     * Precondicion:	True
     * Postcondicion: 	id = SUM(i : 0 <= i < 9 : piezas[8-i] * 10^i)
     */
    public void calcularId()
    {
        id = piezas[0];
        for(int i = 1 ; i < 9 ; i++)
        {
            id = (id * 10) + piezas[i];
        }
    }
    
    
    /**
     * Resuelve un 8-Puzzle dado un estado inicial.
     * Precondicion:	sePuedeResolver() == True
     * Postcondicion: 	this.id == metaId
     *
     * @param h			string que define que heuristica se usara
     */
	public void encontrarSolucion(String h)
	{
        if (!sePuedeResolver())
        {
            System.out.println("No se puede resolver");
            return;
        }
        ComparadorEstado       comp      = new ComparadorEstado();
        PriorityQueue<Estado>  cola      = new PriorityQueue<Estado>(1 , comp);
        HashSet<Integer>	   visitados = new HashSet<Integer>();
		Estado                 actual    = null;
        int 				   metaId    = 123456780;
        int 				   nEstados  = 1;
        long				   tiempo    = 0;
        long 				   fin       = 0;
        long 				   inicio    = System.currentTimeMillis();

        calcularId();
        camino.offer(-10);
        cola.offer(this);
        visitados.add(this.id);
        
        while (cola.size() > 0)
        {
			actual = cola.poll();
            visitados.add(actual.id);
            
            for (int i = 1 ; i < 5 ; i++)
            {
                Estado aux = new Estado();
                aux.copiar(actual);
                boolean esUltimaAccion;
                esUltimaAccion = (Math.abs(i - actual.camino.getLast()) == 2);
                if (aux.accion(i) && !esUltimaAccion)
                {
					aux.calcularId();
                    if (!visitados.contains(aux.id))
                    {
                        aux.camino.offer(i);
                        aux.f = aux.camino.size() + aux.calcularHeuristica(h);
                        nEstados++;
                        if ( aux.id == metaId )
                        {
                            actual.camino.removeFirst();
                            int movimientos = aux.camino.size()-1;
                            for (Integer j : aux.camino)
                            {
                                accion(j);
                                imprimir();
                            }
                            
                            
                            fin = System.currentTimeMillis();
                            tiempo = fin - inicio;
                            
                            System.out.print  ("Numero de estados abiertos: ");
                            System.out.println(nEstados);
                            System.out.println("Tiempo: " + tiempo + "ms");
                            return;
                        }
                        cola.offer(aux);
                        visitados.add(aux.id);
					}
                }
            }
        }
    }
}


public class OchoPuzzle
{
	static public void main(String args[])
	{
		Estado inicial = new Estado();
        
		inicial.cargarEstado(args[1]);
        
        inicial.encontrarSolucion(args[0]);
	}
}
