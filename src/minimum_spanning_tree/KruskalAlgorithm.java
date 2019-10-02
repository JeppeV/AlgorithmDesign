package minimum_spanning_tree;

import java.util.*;

public abstract class KruskalAlgorithm {

    private static class KruskalAlgorithmResult {

        public KruskalAlgorithmResult(int totalCost, Set<Edge> resultSet) {
            this.totalCost = totalCost;
            this.resultSet = resultSet;
        }

        public int totalCost;
        public Set<Edge> resultSet;

    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] sizes;

        sizes = scanner.nextLine().split(" ");
        int n = Integer.parseInt(sizes[0]), m = Integer.parseInt(sizes[1]);

        Graph currentGraph;

        while(!(n == 0 && m == 0)) {
            currentGraph = new Graph();

            String[] edgeInfo;
            int vertex_a, vertex_b, weight;


            int max_connected_vertex = 0;
            for(int i = 0; i < m; i++) {
                edgeInfo = scanner.nextLine().split(" ");
                vertex_a = Integer.parseInt(edgeInfo[0]);
                vertex_b = Integer.parseInt(edgeInfo[1]);
                weight = Integer.parseInt(edgeInfo[2]);
                Edge edge = new Edge(vertex_a, vertex_b, weight);
                currentGraph.addEdge(edge);

                max_connected_vertex = Math.max(Math.max(vertex_a, vertex_b), max_connected_vertex);
            }

            int max_input_vertex = n-1;

            if(max_connected_vertex != max_input_vertex) {
                System.out.println("Impossible");
            } else {
                KruskalAlgorithmResult kruskalAlgorithmResult = KruskalAlgorithm.getMST(currentGraph, n);
                print(kruskalAlgorithmResult);
            }

            if(!scanner.hasNext()) break;
            sizes = scanner.nextLine().split(" ");
            n = Integer.parseInt(sizes[0]);
            m = Integer.parseInt(sizes[1]);

        }






    }


    private static void print(KruskalAlgorithmResult kruskalAlgorithmResult) {
        if(kruskalAlgorithmResult.resultSet == null) {
            System.out.println("Impossible");
        } else {
            System.out.println(kruskalAlgorithmResult.totalCost);
            for(Edge edge : kruskalAlgorithmResult.resultSet) {
                System.out.println(edge.vertex_a + " " + edge.vertex_b);
            }
        }



    }

    public static KruskalAlgorithmResult getMST(Graph graph, int n) {

        /*LinkedList<Edge> sortedEdges = new LinkedList<>(graph.getEdges());
        Collections.sort(sortedEdges, new Comparator<Edge>() {
            @Override
            public int compare(Edge e1, Edge e2) {
                return e1.weight - e2.weight;
            }
        });*/

        int totalCost = 0;

        Set<Edge> resultSet = new TreeSet<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge edge1, Edge edge2) {
                if(edge1.vertex_a < edge2.vertex_a || (edge1.vertex_a == edge2.vertex_a && edge1.vertex_b < edge2.vertex_b)) return -1;
                if(edge1.vertex_a == edge2.vertex_a && edge1.vertex_b == edge2.vertex_b) return 0;
                return 1;
            }
        });

        UnionFindWithSetPointer unionFindDatastructure = new UnionFindWithSetPointer(n);

        for(Edge currentEdge : graph.getEdges()) {
            int vertex_a = currentEdge.vertex_a;
            int vertex_b = currentEdge.vertex_b;

            if(unionFindDatastructure.find(vertex_a) != unionFindDatastructure.find(vertex_b)) {
                totalCost += currentEdge.weight;
                resultSet.add(currentEdge);
                unionFindDatastructure.unionByVertices(vertex_a, vertex_b);
            }
        }



        //resultSet = unionFindDatastructure.allInSameSet() ? resultSet : null;


        return new KruskalAlgorithmResult(totalCost, resultSet);

    }


}
