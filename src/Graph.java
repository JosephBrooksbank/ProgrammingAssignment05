import java.util.ArrayList;

/**
 * Class for Graph objects, maintaining edges in an adjacency-list
 *
 * @author Joseph Brooksbank
 * @version 10/28/2018
 */
class Graph {

    /**
     * Array of real nodes used in most algorithms, contains all node data such as color and distance
     */
    nodeData[] vertices;
    /**
     * Number of Vertices
     */
    private int V;
    /**
     * ArrayList to handle the edges between vertices, the traditional "Adjacency List" as described in the textbook
     */
    private ArrayList<ArrayList<Integer>> adjArray;

    /**
     * Default constructor for a graph
     *
     * @param V The number of vertices in this graph
     */
    Graph(int V) {

        this.V = V;
        adjArray = new ArrayList<>();
        vertices = new nodeData[V];

        /* Constructing basic ArrayList and Array to hold the vertices */
        for (int i = 0; i < V; i++) {
            adjArray.add(i, new ArrayList<>());
            vertices[i] = new nodeData(i);
        }
    }

    /**
     * Constructor to make a Graph object out of the two array components of other graphs
     *
     * @param adjArray The adjacency-list of edges for this graph
     * @param vertices The list of vertex objects for this graph
     */
    private Graph(ArrayList<ArrayList<Integer>> adjArray, nodeData[] vertices) {
        this.V = vertices.length;
        this.adjArray = adjArray;
        this.vertices = vertices;
    }


    /**
     * Method which adds a "symbolic" edge between two vertices, stored in the adjArray ArrayList
     *
     * @param src  The source vertex of the edge
     * @param dest The destination vertex of the edge
     */
    void addEdge(int src, int dest) {
        // add edge from source to destination
        adjArray.get(src).add(dest);
    }

    /**
     * Method which bridges connection between adjArray and vertices array, converts "symbolic" edges into an array of
     * actual nodes
     *
     * @param i The value of vertex which is being accessed
     * @return An array containing the nodes which are adjacent
     */
    nodeData[] getAdj(int i) {

        /* Get number of adjacent notes from adjacency-list */
        int size = adjArray.get(i).size();
        /* Create array to hold this number of nodes */
        nodeData[] retVal = new nodeData[size];

        /* For each of the adjacent nodes, convert from integer as stored in adjacency-list to nodeData object */
        for (int j = 0; j < size; j++) {

            /* Get "value" of adjacent node from adjacency-list */
            int index = adjArray.get(i).get(j);
            /* Iterate through all nodes until the correct node is found, this has to be done as the vertices array
            is sorted as part of the SCC algorithm and the original index of the node may change. To make more efficient
            a sorting algorithm could be implemented in the Graph class which also maintains the adjacent array's position
            in regards to the current index of the vertex in the vertices array.
             */
            for (int k = 0; k < V; k++) {
                if (vertices[k].getVal() == index) {
                    retVal[j] = vertices[k];
                }
            }
        }
        return retVal;
    }

    /**
     * Method which makes a transpose graph of the current graph
     *
     * @return A transposed graph
     */
    Graph transposeGraph() {
        Graph transpose = new Graph(V);

        for (int i = 0; i < V; i++) {
            nodeData[] adjNodes = getAdj(i);
            for (nodeData adjNode : adjNodes) {
                transpose.addEdge(adjNode.getVal(), i);
            }
        }
        return new Graph(transpose.adjArray, this.vertices);
    }

    /**
     * Method to print the Adjacent nodes of all verticies of a graph, useful for debugging
     */
    void printAdjacents() {

        for (nodeData aNode : vertices) {
            System.out.println("Adjacents for vertex " + aNode.getVal() + ": ");
            System.out.println(adjArray.get(aNode.getVal()));
        }
    }

    /**
     * Method which prints the current graph values, useful for debugging
     */
    void printGraph() {
        for (nodeData aNode : vertices) {
            System.out.println(aNode);
        }
    }
}
