package graph;

import java.util.LinkedList;
import java.util.List;

class Edge {
    int u;// 출발지
    int v;// 도착지
    int w;// 가중치
}

/**
 * AdjacencyGraph
 */
public class AdjacencyMatrix {

    final int[][] graph;

    public AdjacencyMatrix(int size) {
        graph = new int[size][size];
    }

    private void addEdge(int u, int v, int w) {
        graph[u][v] = w;
    }

    public void addUndirectedEdge(int u, int v, int w) {
        addEdge(u, v, w);
        addEdge(v, u, w);
    }

    public void addUndirectedEdge(int u, int v) {
        addUndirectedEdge(u, v, 1);
    }

    public void addDirectedEdge(int u, int v, int w) {
        addEdge(u, v, w);
    }

    public void addDirectedEdge(int u, int v) {
        addDirectedEdge(u, v, 1);
    }

    public void printGraph() {

        System.out.println("---Matrix Edge---");
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {

                if (0 != graph[i][j]) {
                    System.out.printf("(%d, %d, %d)", i, j, graph[i][j]);
                }

            }
        }
        System.out.println("\n----------------");

    }

}