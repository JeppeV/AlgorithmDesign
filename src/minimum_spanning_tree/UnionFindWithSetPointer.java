package minimum_spanning_tree;

import java.util.HashMap;
import java.util.Set;

public class UnionFindWithSetPointer {


    private class SetPointer {

        public SetPointer(int setID) {
            this.setID = setID;
        }

        public int setID;
        public SetPointer innerPointer = null;

    }

    private HashMap<Integer,SetPointer> datastructure;
    private int nextSetID;


    public UnionFindWithSetPointer(int n) {
        this.datastructure = new HashMap<>();
        this.nextSetID = 0;

        for(int v = 0; v < n; v++) {
            datastructure.put(v, new SetPointer(v));
            nextSetID++;
        }
    }


    public int find(int vertex) {
        SetPointer setPointer = datastructure.get(vertex);
        return getSetID(setPointer);
    }

    private int getSetID(SetPointer setPointer) {
        while(setPointer.innerPointer != null) {
            setPointer = setPointer.innerPointer;
        }
        return setPointer.setID;
    }

    public int unionByVertices(int vertex_1, int vertex_2) {
        return unionBySets(find(vertex_1), find(vertex_2));
    }

    public int unionBySets(int set1ID, int set2ID) {

        SetPointer set1Pointer = datastructure.get(set1ID);
        set1Pointer.innerPointer =  datastructure.get(set2ID);
        return set2ID;
    }

    public boolean allInSameSet() {
        int previousSetID = -1;
        for(SetPointer setPointer : datastructure.values()) {
            if(previousSetID == -1) {
                previousSetID = getSetID(setPointer);
            }else if (previousSetID != getSetID(setPointer)) {
                return false;
            }
        }
        return true;
    }



}
