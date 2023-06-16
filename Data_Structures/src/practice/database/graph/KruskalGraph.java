package practice.database.graph;

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

    @Override
    public String toString() {
        return String.format("u=%s, v=%s, w=%s", u, v, w);
    }

}

/**
 * KruskalGraph
 */
public class KruskalGraph {

    final PriorityQueue<Edge> priorityQueue;
    final int[] disjoint;
    final int size;

    public KruskalGraph(int size) {
        this.size = size;
        this.disjoint = new int[size];
        this.priorityQueue = new PriorityQueue<>();
    }

    public void addUndirectedEdge(int u, int v) {
        this.addUndirectedEdge(u, v, 1);
    }

    public void addUndirectedEdge(int u, int v, int w) {
        this.addDirectedEdge(u, v, w);
        this.addDirectedEdge(v, u, w);
    }

    public void addDirectedEdge(int u, int v) {
        this.addDirectedEdge(u, v, 1);
    }

    public void addDirectedEdge(int u, int v, int w) {
        this.priorityQueue.add(new Edge(u, v, w));
    }

    public void union(int u, int v) {

        u = this.find(u);
        v = this.find(v);

        if (u > v) {
            disjoint[u] = v;
        } else {
            disjoint[v] = u;
        }

    }

    public int find(int v) {

        if (this.disjoint[v] == v) {
            return v;
        } else {
            return this.disjoint[v] = find(this.disjoint[v]);
        }

    }

    public void clear() {
        priorityQueue.clear();
        for (int i = 0; i < size; i++) {
            this.disjoint[i] = i;
        }
    }

    public Set<Edge> getMST() {

        Set<Edge> safeSet = new HashSet<>();

        while (!priorityQueue.isEmpty()) {

            Edge edge = priorityQueue.remove();

            if (find(edge.u) != find(edge.v)) {
                union(edge.u, edge.v);
            }

        }

        return safeSet;
    }

}