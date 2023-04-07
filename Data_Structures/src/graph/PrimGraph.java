package graph;

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
        return w - o.w;
    }

}

public class PrimGraph {

    private final LinkedList<Edge>[] graph;
    private final PriorityQueue<Edge> queue;
    private final boolean[] visit;

    public PrimGraph() {

        this.graph = new LinkedList[6];
        this.queue = new PriorityQueue<>();
        this.visit = new boolean[6];

    }

    public void undirectedEdge(int u, int v, int w) {

        queue.add(new Edge(u, v, w));
        queue.add(new Edge(v, u, w));

    }

    private Set<Edge> getMST() {

        Set<Edge> safeEdgeSet = new HashSet<>();

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
                    safeEdgeSet.add(edge);
                    break;
                }
            }

        }

        return safeEdgeSet;

    }

}
