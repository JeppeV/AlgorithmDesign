package minimum_spanning_tree;

import java.util.HashMap;
import java.util.Set;

public class UnionFind {

    private HashMap<Integer,Integer> datastructure;
    private int nextSetID;

    public UnionFind(Set<Integer> vertices) {
        this.datastructure = new HashMap<>();
        this.nextSetID = 0;

        for(Integer v : vertices) {
            datastructure.put(v, nextSetID);
            nextSetID++;
        }
    }


    public int find(int vertex) {
        return datastructure.get(vertex);
    }

    public int unionByVertices(int vertex_1, int vertex_2) {
        return unionBySets(find(vertex_1), find(vertex_2));
    }

    public int unionBySets(int set1ID, int set2ID) {

        for(int vertex : datastructure.keySet()) {
            if(datastructure.get(vertex) == set1ID || datastructure.get(vertex) == set2ID) {
                datastructure.put(vertex, nextSetID);
            }
        }

        nextSetID++;

        return nextSetID;
    }

    public boolean allInSameSet() {
        int previousSetID = -1;
        for(int setID : datastructure.values()) {
            if(previousSetID == -1) {
                previousSetID = setID;
            }else if (previousSetID != setID) {
                return false;
            }
        }
        return true;
    }



}
