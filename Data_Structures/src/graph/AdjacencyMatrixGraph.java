package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * AdjacencyMatrix
 */
public class AdjacencyMatrixGraph {

    // 열과 행의 인덱스는 정점을 표현하고, 배열값은 간선의 여부를 표현한다.
    final int[][] graph;
    final int[] visit;

    public AdjacencyMatrixGraph(int size) {
        this.graph = new int[size][size];
        this.visit = new int[size];
    }

    public void addUndirectedEdge(int u, int v) {
        this.addUndirectedEdge(u, v, 1);
    }

    public void addUndirectedEdge(int u, int v, int w) {
        this.graph[u][v] = w;
        this.graph[v][u] = w;
    }

    public void addDirectEdge(int u, int v) {
        this.addDirectEdge(u, v, 1);
    }

    public void addDirectEdge(int u, int v, int w) {
        // TODO : 방향 간선
        this.graph[u][v] = w;
    }

    public void clear() {
        Arrays.fill(this.visit, 0);
        for (int i = 0; i < this.graph[0].length; i++) {
            Arrays.fill(this.graph[i], 0);
        }
    }

    void visit(int vertex) {
        System.out.println("vertext:" + vertex);
        visit[vertex] = 1;
    }

    public void bfsTraversal(int start) {
        // TODO : start 매개변수는 탐색 시점의 정점이다.
        Arrays.fill(visit, 0);
        System.out.println("---Matrix bfs---");
        bfs(start);
        System.out.println("----------------");
    }

    void bfs(int start) {
        // TODO : 너비 우선 탐색
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visit(start);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v = 0; v < graph.length; v++) {

                if (0 != graph[u][v] && 0 == visit[v]) {
                    queue.add(v);
                    visit(v);
                }

            }
        }
    }

}