package minimum_spanning_tree;

public class Edge {

    public Edge(int vertex_a, int vertex_b, int weight) {
        this.vertex_a = vertex_a;
        this.vertex_b = vertex_b;
        this.weight = weight;
    }

    public boolean directed;
    public int weight;
    public int vertex_a;
    public int vertex_b;

}
