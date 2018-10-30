import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Main class of Programming Assignment 05, creating graphs and finding SCC areas within.
 *
 * @author Joseph Brooksbank
 * @version 10/28/2018
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner fileIn = new Scanner(new FileInputStream(args[0]));
        Writer fileOut = new FileWriter(args[1]);

        int V = fileIn.nextInt();
        Graph graph = new Graph(V);
        while (fileIn.hasNext()) {
            graph.addEdge(fileIn.nextInt(), fileIn.nextInt());
        }

        // Make search object
        DFS search = new DFS();

        // Search the graph the first time
        search.start(graph);


        // Make a transpose copy of the graph
        Graph transpose = graph.transposeGraph();

        // Sort the array of vertices based on finish time
        Arrays.sort(transpose.vertices, Collections.reverseOrder());

        // Search the sorted list, saving the tree data
        ArrayList<ArrayList<nodeData>> trees = search.start(transpose);

        // Print data to file specified in args[1]
        for (ArrayList<nodeData> anArray : trees) {
            for (nodeData aNode : anArray) {
                fileOut.write(aNode.getVal() + " ");
            }
            fileOut.write("\n");
        }


        fileOut.flush();
        fileOut.close();
        fileIn.close();


    }
}
