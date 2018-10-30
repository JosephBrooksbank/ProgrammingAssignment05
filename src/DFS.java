import java.util.ArrayList;

/**
 * Class for Depth-First Search as seen in Intro to Algorithms
 *
 * @author Joseph Brooksbank
 * @version 10/28/2018
 */
class DFS {

    /* Constants for discover process */
    private final static String white = "WHITE";
    private final static String gray = "GRAY";
    private final static String black = "BLACK";

    /**
     * The current time of the search process
     */
    private int time;

    /**
     * The current "tree" in the forest of trees which is being searched
     */
    private ArrayList<nodeData> tempTree;

    /**
     * Method to start the searching process
     *
     * @param graph The graph of which to search
     * @return An Arraylist of every tree inside the forest of searched trees
     */
    ArrayList<ArrayList<nodeData>> start(Graph graph) {


        /* Initialization process */
        ArrayList<ArrayList<nodeData>> trees = new ArrayList<>();
        time = 0;
        for (nodeData aNode : graph.vertices) {
            aNode.setColor(white);
            aNode.setDiscovered(-1);
            aNode.setFinished(-1);
        }

        /* Initial search loop */
        for (nodeData u : graph.vertices) {
            if (u.color.equals(white)) {
                /* Creating first node of the tree */
                tempTree = new ArrayList<>();
                /* Adding "head" of tree */
                tempTree.add(u);
                /* Starting search process recursively */
                depthFirstVisit(u, graph);
                /* Adding finished tree to forest */
                trees.add(tempTree);

            }
        }
        return trees;
    }

    /**
     * Main recursive method for DFS
     *
     * @param u     The current node which is being looked at
     * @param graph The graph that is being searched
     */
    private void depthFirstVisit(nodeData u, Graph graph) {

        time++;
        u.setDiscovered(time);
        u.setColor(gray);
        nodeData[] adjNodes = graph.getAdj(u.getVal());

        /* Searching recursively on all adjacent nodes */
        for (nodeData v : adjNodes) {
            if (v.getColor().equals(white)) {
                /* Predecessor is never used in this project, but was described in the textbook so I'm maintaining that
                variable as well.
                 */
                v.setPred(u);
                /* Adding nodes to the tree */
                tempTree.add(v);
                depthFirstVisit(v, graph);
            }
        }
        u.setColor(black);
        time++;
        u.setFinished(time);
    }
}
