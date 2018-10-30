/**
 * Object data class for assignment05, a vertex object
 *
 * @author Joseph Brooksbank
 * @version 10/28/2018
 */
public class nodeData implements Comparable<nodeData> {
    /**
     * The color of the vertex in the graph, used for search algorithms
     */
    String color;
    /**
     * The value of the vertex, 0 thru (V-1)
     */
    private int val;
    /**
     * The time at which this vertex was last discovered
     */
    private int discovered;
    /**
     * The time at which this vertex was last accessed
     */
    private int finished;
    /**
     * The predecessor node from which the search algorithm accessed this vertex
     */
    private nodeData pred;

    /**
     * Constructor for a vertex
     *
     * @param val The value of the vertex
     */
    nodeData(int val) {
        this.val = val;
        color = "WHITE";
        discovered = -1;
        finished = -1;
        pred = null;
    }

    /**
     * Getter for Value
     */
    int getVal() {
        return val;
    }

    /**
     * Getter for predecessor
     */
    public nodeData getPred() {
        return pred;
    }

    /**
     * Setter for predecessor
     */
    void setPred(nodeData pred) {
        this.pred = pred;
    }

    /**
     * Getter for color
     */
    String getColor() {
        return color;
    }

    /**
     * Setter for color
     */
    void setColor(String color) {
        this.color = color;
    }

    /**
     * Getter for discovered time
     */
    public int getDiscovered() {
        return discovered;
    }

    /**
     * Setter for discovered time
     */
    void setDiscovered(int discovered) {
        this.discovered = discovered;
    }

    /**
     * Getter for finish time
     */
    public int getFinished() {
        return finished;
    }

    /**
     * Setter for finish time
     */
    void setFinished(int finished) {
        this.finished = finished;
    }

    /**
     * toString method, displays value, current color, and timings
     */
    public String toString() {
        return Integer.toString(val) + ", " + color + ", " + discovered + "/" + finished;
    }

    /**
     * Implements compareTo method for Comparable, used in sorting
     *
     * @return Standard comparison values {-1,0,1}
     */
    public int compareTo(nodeData o) {
        return Integer.compare(this.finished, o.finished);
    }
}
