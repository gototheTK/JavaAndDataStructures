package practice.database;

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
        return this.w - o.w;
    }

    @Override
    public String toString() {
        return String.format("u=%s, v=%s, w=%s", u, v, w);
    }

}

public class PrimGraph {

    final LinkedList<Edge>[] graph;
    final boolean[] visited;
    final int size;

    public PrimGraph(int size) {
        this.size = size;
        this.visited = new boolean[size];
        this.graph = new LinkedList[size];
    }

    public void addDirectedEdge(int u, int v, int w) {
        this.graph[u].add(new Edge(u, v, w));
    }

    public void addUndirectedEdge(int u, int v, int w) {
        this.addDirectedEdge(u, v, w);
        this.addDirectedEdge(v, u, w);
    }

    public Set<Edge> getMst() {

        Set<Edge> safeSet = new HashSet<>();
        int vertex = 0;
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        while (!queue.isEmpty()) {

            visited[vertex] = true;

            for (Edge e : this.graph[vertex]) {
                if (!visited[e.v]) {
                    queue.add(e);
                }
            }

            while (!queue.isEmpty()) {

                Edge e = queue.remove();
                if (!visited[e.v]) {
                    vertex = e.v;
                    safeSet.add(e);
                    break;
                }

            }

        }

        return safeSet;
    }

}
