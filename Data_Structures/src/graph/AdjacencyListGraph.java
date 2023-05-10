package graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class AdjacencyListGraph {
    // 행의 인덱스는 정점을 표현하고, 노드는 간선을 여부를 표현한다
    final LinkedList<Edge>[] graph;
    final int[] visit;

    @SuppressWarnings("unchecked")
    public AdjacencyListGraph(int size) {
        graph = new LinkedList[size];

        Set<Integer> sets = new HashSet<>();
        sets.size()

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new LinkedList<>();
        }
        visit = new int[size];
    }

    public void clear() {
        for (int i = 0; i < graph.length; i++) {
            graph[i].clear();
        }
    }

    private void addEdge(int u, int v, int w) {
        Edge edge = new Edge();
        edge.u = u;
        edge.v = v;
        edge.w = w;
        graph[u].add(edge);
        /**
         * 굳이 정렬할 필요는 없으나 인접행렬과 정점 출력 순서를 동일하게 하기 위해
         * 삽입 순서에 상관없이 간선을 정렬한다
         */
        graph[u].sort((o1, o2) -> o1.v - o2.v);
    }

    public void addUndirectedEdge(int u, int v) {
        // TODO : 무방향 간선
        addUndirectedEdge(u, v, 1);
    }

    public void addUndirectedEdge(int u, int v, int w) {
        // TODO : 무방향 간선
        addEdge(u, v, w);
        addEdge(v, u, w);
    }

    public void addDirectEdge(int u, int v) {
        // TODO : 방향 간선
        addDirectEdge(u, v, 1);
    }

    public void addDirectEdge(int u, int v, int w) {
        // TODO : 방향 간선
        addEdge(u, v, w);
    }

    public void dfsTraversal(int start) {
        System.out.println("---List dfs---");
        dfs(start);
        System.out.println("--------------");

    }

    private void dfs(int u) {

        if (1 == visit[u]) {
            return;
        }
        visit(u);
        for (Edge edge : graph[u]) {
            if (0 == visit[edge.v]) {
                dfs(u);0
            }
        }

    }

    public void bfsTraversal(int start) {
        System.out.println("---List bfs---");
        bfs(start);
        System.out.println("--------------");
    }

    private void bfs(int start) {

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {

            int u = queue.poll();
            visit(u);
            for (Edge edge : graph[u]) {

                if (0 == visit[edge.v]) {
                    queue.add(edge.v);
                    visit[edge.v] = 1;
                }

            }

        }

    }

    private void visit(int u) {

        System.out.println("visit:" + u);
        visit[u] = 1;

    }

    public void printEdge() {
        System.out.println("---List Edge---");
        for (List<Edge> edges : graph) {
            for (Edge edge : edges) {
                System.out.printf("(%d, %d, %d) ", edge.u, edge.v, edge.w);
            }
        }
        System.out.println("\n---------------");
    }

}
