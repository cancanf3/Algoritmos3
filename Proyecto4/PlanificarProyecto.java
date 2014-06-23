
import java.io.*;
import java.util.*;

class Arco
{
    Vertice inicio;
    Vertice fin;
    double  costo;
    
    public Arco(Vertice i,Vertice f, double c)
    {
        inicio = i;
        fin = f;
        costo = c;
    }
}
class Vertice
{
    String           id;
    LinkedList<Arco> sucesores;
    
    public Vertice(String id)
    {
        this.id = id;
        sucesores = new LinkedList<Arco>();
    }
}
class GrafoPrecedencia
{
	LinkedList<Vertice> V;
    int                 nVertice;
    int                 nArco;
    Vertice             actividadInicial;
    Vertice				actividadFinal;
    double[]			TAC;
    double[]            TEC;
    
    public GrafoPrecedencia()
    {
        V = new LinkedList<Vertice>();
        nVertice = 0;
        nArco = 0;
        actividadInicial = null;
        actividadFinal = null;
        TAC = new double[1];
        TEC = new double[1];
    }
    
    public boolean cargarGrafo(String file)
    {
        try{
            BufferedReader Buffer;
            Buffer = new BufferedReader(new FileReader(new File(file)));
            
            nVertice = Integer.parseInt(Buffer.readLine());
            nArco = Integer.parseInt(Buffer.readLine());
            TAC = new double[nVertice];
            TEC = new double[nVertice];
            
            String[] str  = Buffer.readLine().split(" ");
            String   str1 = str[0];
            String   str2 = str[1];
			
			for (int i=0 ; i < nArco ; i++) {
				str = Buffer.readLine().split(" ");
                agregarArco(str[0],str[1],Integer.parseInt(str[2]));
			}
            
            for (Vertice i : V)
            {
                if (i.id.equals(str1))
                	actividadInicial = i;
                else if (i.id.equals(str2))
                    actividadFinal = i;
            }

            
	    }catch(IOException e) {
            System.out.println(" Error en la lectura del archivo");
            return false;
        };
        return true;
    }
    
    public void agregarArco(String inicio, String fin, double costo)
    {
        for (Vertice i : V)
        {
            if (i.id.equals(inicio))
            {
                for (Vertice j : V)
                {
                    if (j.id.equals(fin))
                    {
                        i.sucesores.offer(new Arco(i,j,costo));
                        return;
                    }
                }
                V.offer(new Vertice(fin));
                i.sucesores.offer(new Arco(i,V.getLast(),costo));
                return;
            }
        }
        for (Vertice j : V)
        {
            if (j.id.equals(fin))
            {
                Vertice u = new Vertice(inicio);
                u.sucesores.offer(new Arco(u,j,costo));
                V.offer(u);
                return;
            }
        }
        Vertice u = new Vertice(inicio);
        Vertice v = new Vertice(fin);
        u.sucesores.offer(new Arco(u,v,costo));
        V.offer(u);
        V.offer(v);
    }
    
    public boolean tieneCiclo()
    {
        int[] color = new int[nVertice];

        for (Vertice i : V)
        {
            if(color[V.indexOf(i)] == 0)
            {
                if (DFS_visita(i,color))
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean DFS_visita(Vertice v, int[] color)
    {
        color[V.indexOf(v)] = 1;
        for (Arco i : v.sucesores)
        {
            if (color[V.indexOf(i.fin)] == 1)
            {
                return true;
            }
            else if (color[V.indexOf(i.fin)] == 0)
            {
               if(DFS_visita(i.fin,color))
                   return true;
            }
        }
        color[V.indexOf(v)] = 2;
        return false;
    }
    
    public void calcularTEC()
    {
        LinkedList<Vertice> cola = new LinkedList<Vertice>();
        cola.offer(actividadInicial);
        TEC[V.indexOf(actividadInicial)] = -1.0;
        while(cola.size() > 0)
        {
            Vertice v = cola.poll();
            for(Arco i : v.sucesores)
            {
                if(TEC[V.indexOf(i.fin)] < TEC[V.indexOf(v)] + i.costo)
        		{
                   TEC[V.indexOf(i.fin)] = TEC[V.indexOf(v)] + i.costo;
                }
                cola.offer(i.fin);
            }
        }
    }
    
    public void calcularTAC()
    {
        boolean[] visitado = new boolean[nVertice];
        for(int i = 0 ; i < nVertice ; i++)
        {
            TAC[i] = -1.0;
        }
        DFS_TAC(actividadInicial,visitado);
    }
    
    public void DFS_TAC(Vertice v, boolean[] visitado)
    {
        visitado[V.indexOf(v)] = true;
        if (v == actividadFinal)
        {
            TAC[V.indexOf(v)] = TEC[V.indexOf(v)];
        }
        else
        {
            for(Arco i : v.sucesores)
            {
                if(!visitado[V.indexOf(i.fin)])
                {
                    DFS_TAC(i.fin,visitado);
                }
            }
            
            for (Arco i : v.sucesores)
            {
                
                if (TAC[V.indexOf(i.fin)] != -1.0 && (TAC[V.indexOf(v)] == -1
                    || TAC[V.indexOf(v)] > TAC[V.indexOf(i.fin)] - i.costo ))
                {
                    TAC[V.indexOf(v)] = TAC[V.indexOf(i.fin)] - i.costo;
                }
            }
        }
    }
    
    
}



public class PlanificarProyecto
{
	static public void main(String[] args)
    {
        GrafoPrecedencia g;
        g = new GrafoPrecedencia();
        g.cargarGrafo(args[0]);

        if (g.tieneCiclo())
            System.out.println("El Grafo contiene un ciclo");
        else
    	{
            g.calcularTEC();
            g.calcularTAC();

            double   tiempoMin = -1;
            double[] holgura   = new double[g.nVertice];
            for(int i = 0; i < g.nVertice ; i++)
            {
                if (tiempoMin < g.TEC[i])
                    tiempoMin = g.TEC[i];
                holgura[i] = g.TAC[i] - g.TEC[i];
            }
            holgura[g.V.indexOf(g.actividadInicial)] = -1;
            System.out.print   ("Tiempo minimo de duracion del proyecto: ");
            System.out.println (tiempoMin);
            
            System.out.print   ("Ejecucion de las actividades criticas: ");
            for(int i=0; i<g.nVertice ; i++)
            {
                if (holgura[i]<= 0)
                    System.out.print(g.V.get(i).id + " ");
            }
            System.out.println();
            System.out.println("Actividad  Holgura");
            System.out.println("------------------");
            for(int i=0; i<g.nVertice ; i++)
            {
                if (holgura[i] == -1)
                    System.out.println(g.V.get(i).id + " - ");
                else
                    System.out.println(g.V.get(i).id + " " + holgura[i]);
            }
         
        }
    }
}


































