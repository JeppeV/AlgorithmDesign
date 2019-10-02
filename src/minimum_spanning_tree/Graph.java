package minimum_spanning_tree;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Graph {

    private Set<Integer> vertices;
    private Set<Edge> edges;

    public Graph() {
        this.vertices = new HashSet<>();
        this.edges = new TreeSet<>(Comparator.comparingInt(edge -> edge.weight));
    }

    public void addVertex(int vertex) {
        vertices.add(vertex);
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public Set<Integer> getVertices(){
        return vertices;
    }

    public Set<Edge> getEdges() {
        return edges;
    }

}
