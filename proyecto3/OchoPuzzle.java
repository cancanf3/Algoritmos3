

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.lang.System;
import java.lang.Math;
import java.io.File;
import java.io.IOException;

/**
 * Clase comparador de la clase Tablero.
 */
class ComparadorTablero implements Comparator<Tablero>
{
    /**
     * Compara dos classes Tablero segun su funcion estimativa
     *
     * @return			-1 	si estado1.f  < estado2.f
     *                   0  si estado1.f == estado2.f
     *                   1 	si estado1.f  > estado2.f
     * @precondition	True
     * @postcondition   True
     */
    public int compare(Tablero estado1, Tablero estado2)
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
 * Clase Tablero que representa un estado en el juego
 * de 9-Puzzle durante su resolucion.
 */
class Tablero
{
	int[]  				piezas;      // las 9 piezas del tablero.
	int     			posBlanco;   // posicion del blanco ("0") en el tablero.
    int     			f;           // funcion estimativa de la heuristica.
    LinkedList<Integer> camino;		 // Lista del camino desde el inicio.
    String  			heuristica;  // heuristica que se utiliza.
    
    
    
    /**
     * Constructor de la clase Tablero
     */
	public Tablero()
	{
		piezas     = new int[9];
        f          = 0;
		posBlanco  = 0;
        heuristica = "";
        camino     = new LinkedList<Integer>();
	}
    
    
    
    /**
     * Cargar valores
     *
     * @param file		string del nombre del archivo.
     * @precondition	True
     * @postcondition   True
     */
	public void cargarTablero(String file)
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
     * Imprime por consola el tablero.
     *
     * @precondition	True
     * @postcondition   True
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
     *
     * @return			True si el numero de inversiones es par, False si no.
     * @precondition	True
     * @postcondition   FORALL( i : 0 <= i < 8 : 
     *                         FORALL( j : 0 <= j <= 8-i :
     *                                (piezas[i] < piezas[j] && piezas[j] != 0)
     *								   => nInversiones = nInversiones' + 1 ))
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
     * Verifica si el estado actual es igual al estado meta.
     *
     * @return			FORALL( i : 0 <= i < 9 : piezas[i] == i+1 )
     * @precondition 	True
     * @postcondition 	True
     */
    public boolean esEstadoMeta()
	{
		for(int i = 1 ; i < 9 ; i++)
		{
			if( i != piezas[i - 1])
				return false;
		}
		return true;
	}
    
    
    
	/**
     * Calcula la heurista del estado, usando el atributo hueristica para
     * decidir cual de las cuatro heuristicas disponibles se calculara.
     *
     * @return			El valor de la hueristica estimada.
     * @precondition 	True
     * @postcondition 	True
     */
	public int calcularHeuristica()
	{
        
		int h = 0;
        
		if (heuristica.equals("m"))
		{
			for (int i = 0 ; i < 9 ; i++)
			{
				if (piezas[i] !=0)
				{
					int x1 = i % 3;
					int y1 = i / 3;
					int x2 = (piezas[i] - 1) % 3;
					int y2 = (piezas[i] - 1) / 3;
                    
					h += Math.abs(x1 - x2) + Math.abs(y1 - y2);
				}
			}
		}
		else if(heuristica.equals("d"))
		{
			for (int i = 0 ; i < 9 ; i++)
			{
				if (piezas[i] != 0 && piezas[i] == i + 1)
				{
					h++;
				}
			}
		}
		else if (heuristica.equals("b"))
		{
			int x1 = posBlanco % 3;
			int y1 = posBlanco / 3;
            
			h += Math.abs(x1 - 2) + Math.abs(y1 - 2);
		}
		return h;
	}
    
    
    
    /**
     * Modifica el tablero segun la accion dada.
     *
     * @param accion	Numero de la accion a tomar.
     * @return			True si se pudo realizar la accion, False si no.
     * @precondition	1 <= accion <= 4
     * @postcondition   Modifica el arreglo de piezas de acuerdo a la accion
     *					dada, si puede realizarze la misma.
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
        
		if (y-1 >= 0 && accion == 1)
		{
			piezas[posBlanco] = piezas[posBlanco - 3];
			piezas[posBlanco - 3] = 0;
			posBlanco -= 3;
			return true;
		}
		if (x+1 < 3  && accion == 2)
		{
			piezas[posBlanco] = piezas[posBlanco + 1];
			piezas[posBlanco + 1] = 0;
			posBlanco += 1;
			return true;
		}
		if (y+1 < 3  && accion == 3)
		{
			piezas[posBlanco] = piezas[posBlanco + 3];
			piezas[posBlanco + 3] = 0;
			posBlanco += 3;
			return true;
		}
		if (x-1 >= 0 && accion == 4)
		{
			piezas[posBlanco] = piezas[posBlanco - 1];
			piezas[posBlanco - 1] = 0;
			posBlanco -= 1;
			return true;
		}
        
		return false;
	}
    
    
    /**
     * Copia los atributos de otra clase Tablero.
     *
     * @param fuente   Tablero de cual se compiaran los atributos copiar.
     * @precondition:  fuente != null
     * @postcondition: FORALL(i : 0 <= i < 9 : piezas[i] == fuente.piezas[i])
     *                 && FORALL( i : i in fuente.camino :
     *                           FORALL( j : j in camino : i==j ))
     *                 && (heuristica == fuente.heuristica)  && (f == fuente.f)
     *                 && (posBlanco == fuente.posBlanco)
     */
    void copiar(Tablero fuente)
    {
        for (int i = 0 ; i < 9 ; i++)
        {
            piezas[i] = fuente.piezas[i];
        }
        f = fuente.f;
        heuristica = fuente.heuristica;
        posBlanco = fuente.posBlanco;
        for (Integer i : fuente.camino)
        {
           	camino.offer(i);
        }
    }
    
    
    
    /**
     * Resuelve un 9-Puzzle dado un estado inicial.
     *
     * @precondition  sePuedeResolver() == True
     * @postcondition (esEstadoMeta() == True) && (nEstado == |camino minimo|)
     */
	public void encontrarSolucion()
	{
        ComparadorTablero      comp     = new ComparadorTablero();
        PriorityQueue<Tablero> cola     = new PriorityQueue<Tablero>(1 , comp);
		Tablero                actual   = null;
        int 				   nEstados = 0;
        long				   tiempo   = 0;
        long 				   fin      = 0;
        long 				   inicio   = System.currentTimeMillis();

        camino.offer(-10);
        cola.offer(this);
        
        while (cola.size() > 0)
        {
			actual = cola.poll();
            if (actual.esEstadoMeta())
            {
                actual.camino.removeFirst();
                for (Integer i : actual.camino)
                {
                    	imprimir();
                    	accion(i);
                }
                imprimir();
                
                fin = System.currentTimeMillis();
                tiempo = fin - inicio;
                
				System.out.println("Numero de estados abiertos: "+(nEstados-1));
                System.out.println("Tiempo: " + tiempo + "ms");
                break;
            }
            
            for (int i = 1 ; i < 5 ; i++)
            {
                Tablero aux = new Tablero();
                aux.copiar(actual);
                
                if (aux.accion(i) && Math.abs(i - actual.camino.getLast()) != 2)
                {
                    // Si la accion es valida y no es la ultima accion tomada.
                    aux.f += aux.calcularHeuristica();
                    aux.camino.offer(i);
                    nEstados++;
                    cola.offer(aux);
                }
            }
        }
    }
}


public class OchoPuzzle
{
	static public void main(String args[])
	{
		Tablero puzzle = new Tablero();
        
		puzzle.cargarTablero(args[1]);
        puzzle.heuristica = args[0];
        
		if (! puzzle.sePuedeResolver())
		{
			System.out.println("No se puede resolver");
		}
		else
		{
        	puzzle.encontrarSolucion();
		}
	}
}
