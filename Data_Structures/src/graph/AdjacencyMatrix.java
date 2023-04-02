package graph;

import java.util.LinkedList;
import java.util.List;

public class AdjacencyMatrix {

    // 행의 인덱스는 정점을 표현하고, 노드는 간선의 여부를 확인한다.
    final LinkedList<Edge>[] graph;

    @SuppressWarnings("unchecked")
    public AdjacencyMatrix(int size) {

        graph = new LinkedList[size];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new LinkedList<>();
        }
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
         * 굳이 정렬할 필요는 없으나 인접행렬과 정점출력 순서를 동일하게 하기 위해 삽입 순서에 상관없이 간선을 정렬한다.
         */
        graph[u].sort((o1, o2) -> o1.v - o2.v);
    }

    public void addUndirectEdge(int u, int v) {
        // TODO : 무방향 간선
        addUndirectedEdge(u, v, 1);
    }

    public void addUndirectedEdge(int u, int v, int w) {
        addEdge(u, v, w);
        addEdge(v, u, w);
    }

    public void addDirectEdge(int u, int v) {
        addDirectEdge(u, v, 1);
    }

    public void addDirectEdge(int u, int v, int w) {
        addEdge(u, v, w);
    }

    public void printEdge() {
        System.out.println("---List Edge---");
        for (List<Edge> edges : graph) {
            for (Edge edge : edges) {
                System.out.printf("(%d, %d, %d)", edge.u, edge.v, edge.w);
            }
        }
        System.out.println("\n-------------");
    }

}