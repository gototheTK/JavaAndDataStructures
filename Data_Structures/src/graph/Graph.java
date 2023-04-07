package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Set;

class Edge implements Comparable<Edge> {
    int u;
    int v;
    int w;

    public Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    @Override
    public int compareTo(Edge o) {
        // TODO Auto-generated method stub
        return this.w - o.w;
    }

    @Override
    public String toString() {
        return String.format("(u=%s, v=%s, w=%s)", u, v, w);
    }

}

public class Graph {

    private LinkedList<Edge>[] graph;
    private final PriorityQueue<Edge> priorityQueue; // 우선순위 큐(MinHeap)
    private final int[] disjointSet; // 서로소 집합
    private final boolean[] visit;

    @SuppressWarnings("unchecked")
    public Graph() {
        priorityQueue = new PriorityQueue<>();
        disjointSet = new int[6];
        visit = new boolean[6];
        graph = new LinkedList[6];

        for (int i = 0; i < disjointSet.length; i++) {
            disjointSet[i] = i;
        }

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new LinkedList<>();
        }

    }

    public void undirectedEdge(int u, int v, int w) {
        // TODO : 간선을 추가할 때 부터 가중치 순으로 정렬하기
        // 우선순위 큐를 사용한다.
        priorityQueue.add(new Edge(u, v, w));
        graph[u].add(new Edge(u, v, w));
        graph[v].add(new Edge(v, u, w));
    }

    private int find(int x) {
        // TODO : 해당 원소의 대표 원소갓을 찾는다.
        if (disjointSet[x] == x) {
            return x;
        } else {

            return disjointSet[x] = find(disjointSet[x]);
        }
    }

    private void union(int u, int v) {
        u = find(u);
        v = find(v);
        // TODO : 전달 받은 두 원소의 대표 원소중 작은 원소 값을 대표 원소로 덮어쓴다.
        if (u < v) {
            disjointSet[v] = u;
        } else {
            disjointSet[u] = v;
        }
    }

    public Set<Edge> getMST() {
        Set<Edge> safeEdgeSet = new HashSet<>();
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            if (find(edge.u) != find(edge.v)) {
                union(edge.u, edge.v);
                safeEdgeSet.add(edge);
            }
        }
        return safeEdgeSet;
    }

    /**
     * PRIM_MST() {
     * vertex = 방문할 정점
     * priorityQueue;
     * while 방문하지 않은 정점이 존재한다
     * visit(vertex)
     * for 정점에 부속된 간선을 priorityQueue에 저장한다
     * if 간선의 정점을 방문하지 않았다
     * 간선을 priorityQueue에 저장한다
     * 
     * while priorityQueue에 간선이 존재한다
     * edge = 큐에서 꺼낸 간선
     * if 간선의 정점을 방문하지 않았다
     * vertex = 간선의 도착지 정점
     * 해당 간선을 안전 간선으로 추가한다
     * break;
     * }
     * 
     * @return
     */

    public Set<Edge> getPrimMST() {

        Set<Edge> safEdgeSet = new HashSet<>();

        int vertex = 0;
        PriorityQueue<Edge> edgeQueue = new PriorityQueue<>();

        while (!visit[vertex]) {

            visit[vertex] = true;

            for (Edge edge : graph[vertex]) {

                if (!visit[edge.v]) {
                    edgeQueue.add(edge);
                }

            }

            while (!edgeQueue.isEmpty()) {

                Edge edge = edgeQueue.poll();

                if (!visit[edge.v]) {
                    vertex = edge.v;
                    safEdgeSet.add(edge);
                    break;
                }

            }

        }

        return safEdgeSet;

    }

    public void test() {

        undirectedEdge(1, 0, 3);
        undirectedEdge(3, 0, 2);
        undirectedEdge(1, 2, 1);
        undirectedEdge(2, 3, 3);
        undirectedEdge(4, 3, 2);
        undirectedEdge(2, 5, 3);

        System.out.println(getPrimMST());
    }

}
