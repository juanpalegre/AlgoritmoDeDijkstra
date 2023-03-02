import java.util.*;

public class DijkstraAlgorithm {

 /*   Este código utiliza una matriz de adyacencia para representar el grafo y encuentra las distancias
    más cortas desde el vértice de origen a todos los demás vértices.*/

    public static void main(String[] args) {
        //Inicializamos un grafo con los respectivos pesos de sus aristas y luego aplicamos la función
        //dijkstra para determinarlos caminos más cortos a cada nodo.
        //Esta función recibe el grado y el nodo de origen. Se puede trabajar con todos los nodos.
        int[][] graph = {
                { 0, 7, 9, 0, 0, 14 },
                { 7, 0, 10, 15, 0, 0 },
                { 9, 10, 0, 11, 0, 2 },
                { 0, 15, 11, 0, 6, 0 },
                { 0, 0, 0, 6, 0, 9 },
                { 14, 0, 2, 0, 9, 0 }
        };
        int[] distances = dijkstra(graph, 3);
        System.out.println(Arrays.toString(distances));
    }

    public static int[] dijkstra(int[][] graph, int source) {
        //Guardamos la cantidad de nodos en una variable n
        int n = graph.length;
        //Inicializamos distances que contendrá la Distancia más corta desde el vértice de origen al vértice i
        int[] distances = new int[n];
        //Inicializamos un boolean que determinará si el nodo ya fue visitado
        boolean[] visited = new boolean[n];

        //Seteamos todos los nodos como No visitados y el valor de la distancia como un hipotético infinito
        for (int i = 0; i < n; i++) {
            distances[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        //Setamos la distancia del notodo de origen como 0.
        distances[source] = 0;

        /*En este momento tenemos los siguientes arrays(para el ejemplo con 6 nodos):
        distances = [99, 99, 99, 99, 99, 99]
        visited = [false, false, false, false, false, false]
        */
        //Vamos a setear la distancia más corta a cada uno de los nodos, partiendo desde el source(en este caso 0)
        for (int i = 0; i < n - 1; i++) {
            //Aplico la funcion minDistance sobre cada uno de los nodos,
            //me devolverá un entero con los valores de distance y visited siempre actualizado.
            int u = minDistance(distances, visited);
            //Una vez que el nodo fue visitado se establece la variable visited como true.
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                //Evaluamos las condiciones que setean la menor distancia desde el nodo de origen hasta el nodo en cuestion:
                if (
                    !visited[v]
                            &&
                    graph[u][v] != 0
                            &&
                    distances[u] != Integer.MAX_VALUE
                            &&
                    distances[u] + graph[u][v] < distances[v])
                         {
                            distances[v] = distances[u] + graph[u][v];
                         }
            }
        }

        return distances;
    }

    public static int minDistance(int[] distances, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < distances.length; i++) {
            if (!visited[i] && distances[i] <= min) {
                min = distances[i];
                minIndex = i;
            }
        }

        return minIndex;
    }
}
