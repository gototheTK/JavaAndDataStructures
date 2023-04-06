package graph;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class AdjacencyMatrixGraph {

    // 열과 행의 인덱스는 정점을 표현하고, 배열값은 간선을 여부를 표현한다.
    final int[][] graph;
    final int[] visit;

    public AdjacencyMatrixGraph(int size) {
        graph = new int[size][size];
        visit = new int[size];
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
            Arrays.fill(graph, 0);
        }
    }

    public void dfsTraversal(int start) {
        // TODO : start 매개변수는 탐색 시작의 정점이다.
        Arrays.fill(visit, 0);
        System.out.println("---Matrix dfs---");
        dfs(start);
        System.out.println("-----------------");
    }

    private void dfs(int u) {
        // TODO : 깊이 우선 탐색
        if (1 == visit[u]) {
            return;
        }

        visit(u);
        for (int i = 0; i < graph[u].length; i++) {
            if (0 != graph[u][i] && 0 == visit[u]) {
                dfs(i);
            }
        }
    }

    public void bfsTraversal(int start) {
        Arrays.fill(visit, 0);
        System.out.println("---Matrix bfs---");
        bfs(start);
        System.out.println("-----------------");
    }

    private void bfs(int start) {

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(start);
        visit(start);
        while (!queue.isEmpty()) {
            int vertex = queue.remove();
            for (int i = 0; i < graph[vertex].length; i++) {

                if (0 == visit[vertex] && 0 == graph[vertex][i]) {
                    queue.add(vertex);
                    visit(vertex);
                }
            }

        }

    }

    private void visit(int vertex) {
        System.out.println("vertex:%d" + vertex);
        visit[vertex] = 1;
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
