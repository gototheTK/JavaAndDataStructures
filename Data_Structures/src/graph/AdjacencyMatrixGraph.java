package graph;

import java.util.Arrays;

import javax.sound.midi.SysexMessage;

/**
 * AdjacencyMatrix
 */
public class AdjacencyMatrixGraph {

    // 열 과 행의 인덱스는 정점을 표현하고, 배열값은 간선을 여부를 표현한다.
    final int[][] graph;
    final int[] visit;

    public AdjacencyMatrixGraph(int size) {
        graph = new int[size][size];
        visit = new int[graph.length];
    }

    public void addUndirectedEdge(int u, int v) {
        // TODO : 무방향 간선
        this.addUndirectedEdge(u, v, 1);
    }

    public void addUndirectedEdge(int u, int v, int w) {
        // TODO : 무방향 간선
        graph[u][v] = w;
        graph[v][u] = w;
    }

    public void addDirectEdge(int u, int v) {
        this.addDirectEdge(u, v, 1);
    }

    public void addDirectEdge(int u, int v, int w) {
        graph[u][v] = w;
    }

    public void clear() {
        Arrays.fill(this.visit, 0);
        for (int i = 0; i < graph.length; i++) {
            Arrays.fill(graph[i], 0);
        }
    }

    void visit(int vertex) {
        System.out.printf("vertex:%d\n", vertex);
        visit[vertex] = 1;
    }

    public void printEdgd() {
        System.out.println("---Matrix Edge---");
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                if (0 != graph[i][j]) {
                    System.out.printf("(%d,%d,%d)", i, j, graph[i][j]);
                }
            }
        }
        System.out.println("\n---------------");
    }

}