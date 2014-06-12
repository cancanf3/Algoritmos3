
import java.util.*;
import java.io.*;
import java.math.*;

class Tablero
{
	int[] piezas; // las 9 piezas del tablero
	int posVacio; // posicion del vacio ( "0" ) en el tablero
	public Tablero()
	{
		piezas = new int[9];
		posVacio = 0;
	}

	public void cargarTablero(String file)
	{
		try{
			Scanner sn = new Scanner(new File(file));
			
			for (int i = 0;i < 9 ; i++)
			{
				piezas[i] = sn.nextInt();
				if (piezas[i] == 0)
					posVacio = i;
			}
		}catch(IOException e){}
	}
	
	public void imprimir()
	{
		System.out.println();
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
	public boolean estadoMeta()
	{
		for(int i=1; i<9 ;i++)
		{
			if( i != piezas[i-1])
				return false;
		}
		return true;
	}
	public int calcularHeuristica(String flag)
	{
		int heuristica = 0;
		if (flag == "m")
		{
			for (int i=0;i<8;i++)
			{
				if (piezas[i] !=0)
				{
					int x1 = i/3;
					int y1 = i%3;
					int x2 = piezas[i]/3;
					int y2 = piezas[i]%3;
					heuristica += Math.abs(x1-x2) + Math.abs(y1-y2); 
				}
			}
		}
		else if(flag == "d")
		{
			for (int i=0;i<9;i++)
			{
				if (piezas[i] != 0 && piezas[i] == i+1)
				{
					heuristica++;
				}
			}
		}
		else if (flag == "b")
		{
			int x1 = posVacio/3;
			int y1 = posVacio%3;
			heuristica += Math.abs(x1-2) + Math.abs(y1-2); 
		}
		return heuristica;
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
		else if (x+1 < 3 && accion == 2) // Mover el vacio hacia la derecha
		{
			piezas[posVacio] = piezas[posVacio+1];
			piezas[posVacio+1] = 0;
			posVacio +=1; 
			return true;
		}
		else if (y+1 < 3 && accion == 3) // Mover el vacio hacia abajo
		{
			piezas[posVacio] = piezas[posVacio+3];
			piezas[posVacio+3] = 0;
			posVacio +=3; 
			return true;
		}
		else if (x-1 >= 0 && accion == 4) // mover el vacio hacia la izquierda
		{
			piezas[posVacio] = piezas[posVacio-1];
			piezas[posVacio-1] = 0;
			posVacio -=1;
			return true; 
		}

		return false;
	}

	public int sucesor(String flag)
	{
		int estadosAbiertos = 0;
		int ultimaAccion = 0;
		while (!this.estadoMeta() && estadosAbiertos < 300000)
		{
			int aux1 = 0,aux2 = 0;
			int mejorAccion = 0;
			if (ultimaAccion != 3)
			{
				if(this.accion(1))
				{
					estadosAbiertos++;
					aux1 = this.calcularHeuristica(flag);
					if (aux2 == 0 || aux1 < aux2)
					{
						aux2 = aux1;
						mejorAccion = 1;
					}
					this.accion(3);
				}
			}
			if (ultimaAccion != 4)
			{
				if(this.accion(2))
				{
					estadosAbiertos++;
					aux1 = this.calcularHeuristica(flag);
					if (aux2 == 0 || aux1 < aux2)
					{
						aux2 = aux1;
						mejorAccion = 2;
					}
					this.accion(4);
				}
			}
			if (ultimaAccion != 1)
			{
				if(this.accion(3))
				{
					estadosAbiertos++;
					aux1 = this.calcularHeuristica(flag);
					if (aux2 == 0 || aux1 < aux2)
					{
						aux2 = aux1;
						mejorAccion = 3;
					}
					this.accion(1);
				}
			}
			if (ultimaAccion != 2)
			{
				if (this.accion(4))
				{
					estadosAbiertos++;
					aux1 = this.calcularHeuristica(flag);
					if (aux2 == 0 || aux1 < aux2)
					{
						aux2 = aux1;
						mejorAccion = 4;
					}
					this.accion(2);
				}
			}
			this.accion(mejorAccion);
			ultimaAccion = mejorAccion;
					imprimir();
		System.out.println(mejorAccion+"   ");
		}
		this.imprimir();
		return estadosAbiertos;
	}
}

public class Puzzle
{
	static public void main(String args[])
	{
		Tablero tab;
		tab = new Tablero();
		tab.cargarTablero(args[1]);
		if (! tab.sePuedeResolver())
		{
			System.out.println("No se puede resolver");
		}
		else
		{
			int estadosAbiertos = tab.sucesor(args[0]);

		}
	}
}
