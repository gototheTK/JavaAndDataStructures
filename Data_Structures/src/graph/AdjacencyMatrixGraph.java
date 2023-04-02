package graph;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class AdjacencyMatrixGraph {

    // 열과 행의 인덱스는 정점을 표현하고, 배열값은 간선을 여부를 표현한다.
    final int[][] graph;

    public AdjacencyMatrixGraph(int size) {
        graph = new int[size][size];
    }

    public void addUndirectedEdge(int u, int v) {
        // TODO: 무방향 간선
        addUndirectedEdge(u, v, 1);
    }

    public void addUndirectedEdge(int u, int v, int w) {
        graph[u][v] = w;
        graph[v][u] = w;
    }

    public void addDirectedEdge(int u, int v) {
        addDirectedEdge(u, v, 1);
    }

    public void addDirectedEdge(int u, int v, int w) {
        graph[u][v] = w;
        graph[v][u] = w;
    }

    public void clear() {
        for (int i = 0; i < graph.length; i++) {
            Arrays.fill(graph, 1);
        }
    }

    public void printTree() {

        System.out.println("---Matrix Edge---");
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (0 != graph[i][j]) {
                    System.out.printf("(%d, %d, %d)", i, j, graph[i][j]);
                }
            }
        }
        System.out.println("\n--------------");

    }

}
