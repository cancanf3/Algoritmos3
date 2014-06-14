
import java.util.*;
import java.io.*;
import java.math.*;


class ComparadorTablero implements Comparator<Tablero>
{
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

class Tablero
{
	int[]  				piezas;      // las 9 piezas del tablero
	int     			posVacio;    // posicion del vacio ( "0" ) en el tablero
    int     			f;           // funcion de estimativa de la heuristica
    LinkedList<Integer> camino;		 // Lista del camino desde el inicio
    String  			heuristica;  // heuristica que se utiliza
    
    
	public Tablero()
	{
		piezas = new int[9];
        f = 0;
		posVacio = 0;
        heuristica = "";
        camino = new LinkedList<Integer>();
        camino.offer(-10);
	}
    
	public void cargarTablero(String h,String file)
	{
		try{
			Scanner sn = new Scanner(new File(file));
			
			for (int i = 0;i < 9 ; i++)
			{
				piezas[i] = sn.nextInt();
				if (piezas[i] == 0)
					posVacio = i;
			}
            heuristica = h;
		}catch(IOException e){}
	}
	
	public void imprimir()
	{
		for(int i=0; i<3 ;i++)
		{
			for(int j=0; j<3 ;j++)
				System.out.print(piezas[i*3+j]+" ");
			System.out.println();
		}
		System.out.println();
	}
    
	// Funcion que devuelve valor booleano si puede resolverse el puzzle dado
	public boolean sePuedeResolver()
	{
		int numInversiones = 0;
		for(int i=8;i>=0;i--)
		{
			if (piezas[i] != 0)
			{
				for(int j=0;j<i;j++)
				{
					if ( piezas[i] < piezas[j] && piezas[j] != 0 )
					{
						numInversiones++;
					}
				}
			}
		}
        
		if (numInversiones % 2 != 0)
			return false;
		return true;
	}
	
    
    public boolean esEstadoMeta()
	{
		for(int i=1; i<9 ;i++)
		{
			if( i != piezas[i-1])
				return false;
		}
		return true;
	}
    

	public int calcularHeuristica()
	{
        
		int h = 0;
		if (heuristica.equals("m"))
		{
			for (int i=0;i<9;i++)
			{
				if (piezas[i] !=0)
				{
					int x1 = i%3;
					int y1 = i/3;
					int x2 = (piezas[i]-1)%3;
					int y2 = (piezas[i]-1)/3;
					h += Math.abs(x1-x2) + Math.abs(y1-y2);
				}
			}
		}
		else if(heuristica.equals("d"))
		{
			for (int i=0;i<9;i++)
			{
				if (piezas[i] != 0 && piezas[i] == i+1)
				{
					h++;
				}
			}
		}
		else if (heuristica.equals("b"))
		{
			int x1 = posVacio%3;
			int y1 = posVacio/3;
			h += Math.abs(x1-2) + Math.abs(y1-2);
		}
		return h;
	}
	public boolean accion(int accion)
	{
		// Acciones
		// 1: mover arriba
		// 2: mover derecha
		// 3: mover abajo
		// 4: mover izquierda
		int y = posVacio/3;
		int x = posVacio%3;
        
		if (y-1 >= 0 && accion == 1) // Mover el vacio hacia arriba
		{
			piezas[posVacio] = piezas[posVacio-3];
			piezas[posVacio-3] = 0;
			posVacio-=3;
			return true;
		}
		if (x+1 < 3 && accion == 2) // Mover el vacio hacia la derecha
		{
			piezas[posVacio] = piezas[posVacio+1];
			piezas[posVacio+1] = 0;
			posVacio +=1;
			return true;
		}
		if (y+1 < 3 && accion == 3) // Mover el vacio hacia abajo
		{
			piezas[posVacio] = piezas[posVacio+3];
			piezas[posVacio+3] = 0;
			posVacio +=3;
			return true;
		}
		if (x-1 >= 0 && accion == 4) // mover el vacio hacia la izquierda
		{
			piezas[posVacio] = piezas[posVacio-1];
			piezas[posVacio-1] = 0;
			posVacio -=1;
			return true;
		}
        
		return false;
	}
    
    void copiar(Tablero fuente)
    {
        for (int i=0 ; i<9 ; i++)
        {
            piezas[i] = fuente.piezas[i];
        }
        f = fuente.f;
        heuristica = fuente.heuristica;
        posVacio = fuente.posVacio;
        for (Integer i : fuente.camino)
            camino.offer(i);
    }
    
	public void encontrarSolucion()
	{
        ComparadorTablero      comp     = new ComparadorTablero();
        PriorityQueue<Tablero> cola     = new PriorityQueue<Tablero>(1,comp);
		Tablero                actual   = null;
        int 				   nEstados = 1;
        cola.offer(this);
        
        while (cola.size() > 0)
        {
			actual = cola.poll();
            if (actual.esEstadoMeta())
            {
                actual.camino.removeFirst();

                for (Integer i : actual.camino)
                {
                    if ( i > 0)
                    {
                    	imprimir();
                    	accion(i);
                    }
                }
                actual.imprimir();
				System.out.println("Numero de estados abiertos: "+nEstados);
                break;
            }
            
            for (int i=1 ; i<5 ; i++)
            {
                Tablero aux = new Tablero();
                aux.copiar(actual);
                
                if (aux.accion(i) && Math.abs(i-actual.camino.getLast()) != 2)
                    // Si la accion es valida y no es la ultima accion tomada
                {
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
		Tablero puzzle;
		puzzle = new Tablero();
		puzzle.cargarTablero(args[0],args[1]);
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
