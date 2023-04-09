package graph;

import java.util.HashSet;
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

public class KrukalGraph {

    private final PriorityQueue<Edge> priorityQueue;
    private final int[] disjointSet;

    public KrukalGraph() {
        priorityQueue = new PriorityQueue<>();
        disjointSet = new int[6];

        for (int i = 0; i < disjointSet.length; i++) {
            disjointSet[i] = i;
        }

    }

    public void undirectedEdge(int u, int v, int w) {
        priorityQueue.add(new Edge(u, v, w));
    }

    private int find(int x) {

        if (disjointSet[x] == x) {
            return x;
        }
        return disjointSet[x] = find(x);
    }

    private void union(int u, int v) {

        u = find(u);
        v = find(v);

        if (u > v) {
            disjointSet[v] = u;
        } else {
            disjointSet[u] = v;
        }

    }

    public Set<Edge> getMST() {
        Set<Edge> safeSet = new HashSet<>();

        while (!priorityQueue.isEmpty()) {

            Edge edge = priorityQueue.poll();

            if (find(edge.u) != find(edge.v)) {

                union(edge.u, edge.v);
                safeSet.add(edge);

            }

        }

        return safeSet;

    }

}
