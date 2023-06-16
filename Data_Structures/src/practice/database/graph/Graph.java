package practice.database.graph;

import java.rmi.dgc.VMID;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Graph
 */

public class Graph {

    class Edge implements Comparable<Edge> {

        int u;
        int v;
        int w;

        public Edge(int u, int v) {
            this(u, v, 1);
        }

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
            return String.format("u=%s, v=%s, w=%s", this.u, this.v, this.w);
        }

    }

    final int[][] disjointMatrix;
    final LinkedList<Edge>[] disjointList;
    final int visited[];
    final int size;

    public Graph(int size) {

        this.size = size;
        this.disjointMatrix = new int[size][size];
        this.disjointList = new LinkedList[size];
        this.visited = new int[size];
        this.clear();

    }

    public void addUndirectedEdge(int u, int v) {
        this.addUndirectedEdge(u, v, 1);
    }

    public void addUndirectedEdge(int u, int v, int w) {
        this.addUndirectedMatrixEdge(u, v, w);
        this.addUndirectedListEdge(u, v, w);
    }

    public void addUndirectedMatrixEdge(int u, int v) {
        this.addUndirectedEdge(u, v, 1);
    }

    public void addUndirectedMatrixEdge(int u, int v, int w) {
        this.disjointMatrix[u][v] = w;
        this.disjointMatrix[v][u] = w;
    }

    public void addUndirectedListEdge(int u, int v) {
        this.addUndirectedListEdge(u, v, 1);
    }

    public void addUndirectedListEdge(int u, int v, int w) {
        this.disjointList[u].add(new Edge(u, v, w));
        this.disjointList[v].add(new Edge(v, u, w));
    }

    public void addDirectedEdge(int u, int v, int w) {
        this.addDirectedMatrixEdge(u, v, w);
        this.addDirectedListEdge(u, v, w);
    }

    public void addDirectedListEdge(int u, int v, int w) {
        this.disjointList[u].add(new Edge(u, v, w));
    }

    public void addDirectedMatrixEdge(int u, int v, int w) {
        this.disjointMatrix[u][v] = w;
    }

    public void clear() {
        Arrays.fill(this.visited, 0);
        this.clearMatrix();
        this.clearList();
    }

    public void clearMatrix() {
        for (int i = 0; i < this.size; i++) {
            Arrays.fill(this.disjointMatrix[i], 0);
            this.disjointMatrix[i][i] = 1;
        }
    }

    public void clearList() {
        Arrays.fill(disjointList, null);
        for (int i = 0; i < this.size; i++) {
            disjointList[i] = new LinkedList<>();
        }
    }

    public void dfsTraversal(int start) {

        System.out.println("---dfs Traversal---");
        dfsMatrix(start);

    }

    public void dfsMatrix(int vertex) {

        if (visited[vertex] == 1) {
            return;
        }
        this.visited[vertex] = 1;

        for (int i = 0; i < size; i++) {
            if (disjointMatrix[vertex][i] != 0 && visited[i] == 0) {
                System.out.println("visit=" + "(" + vertex + " ," + i + ")");
                this.dfsMatrix(i);
            }
        }
    }

    public void dfsList(int vertex) {

        if (visited[vertex] == 1) {
            return;
        }
        System.out.println("visit=" + "(" + vertex + ")");
        this.visited[vertex] = 1;

        for (Edge v : disjointList[vertex]) {

            if (visited[vertex] == 0) {
                dfsList(v.v);
            }

        }

    }

    public void bfsTraversal(int start) {

        System.out.println("---bfs Traversal---");
        bfsMatrix(start);

    }

    public void bfsMatrix(int vertex) {

        LinkedList<Integer> queue = new LinkedList<>();
        visited[vertex] = 1;
        queue.add(vertex);

        while (!queue.isEmpty()) {

            Integer v = queue.removeFirst();

            for (int i = 0; i < this.size; i++) {

                if (disjointMatrix[v][i] != 0 && visited[i] == 0) {
                    visited[i] = 1;
                    queue.add(i);
                    System.out.println("visit=" + "(" + v + " ," + i + ")");
                }

            }

        }

    }

    public void bfsList(int vertex) {

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        visited[vertex] = 1;
        queue.add(vertex);
        System.out.println("visit=" + "(" + vertex + ")");

        while (!queue.isEmpty()) {

            Integer v = queue.remove();

            for (Edge e : this.disjointList[v]) {

                if (visited[e.v] == 0) {
                    visited[e.v] = 1;
                    queue.add(e.v);
                    System.out.println("visit=" + "(" + e.v + ")");
                }

            }

        }

    }

    public static void main(String[] args) {

        Graph g = new Graph(6);

        g.addUndirectedEdge(1, 2);

        g.addUndirectedEdge(1, 0);

        g.addUndirectedEdge(1, 4);

        g.addUndirectedEdge(0, 2);

        g.addUndirectedEdge(2, 3);

        g.addUndirectedEdge(4, 5);
        g.dfsTraversal(1);

        g.clear();
        g.addUndirectedEdge(1, 2);

        g.addUndirectedEdge(1, 0);

        g.addUndirectedEdge(1, 4);

        g.addUndirectedEdge(0, 2);

        g.addUndirectedEdge(2, 3);

        g.addUndirectedEdge(4, 5);
        g.bfsTraversal(1);

    }

}
