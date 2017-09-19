package com.graphs;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by cesar on 4/16/17.
 */
public class Graph implements Serializable{
    public final int DEPTH = 4;
    public final int LENGTH = 4;

    private HashMap<String, Node> nodes;

    public Graph() {
        nodes = new HashMap<>();
    }

    /**
     * The methods writer and close simply write nodes to a file
     * for use in a graph vizualization software. Used in the beginning
     * to test the graph but completely irrelevant now
     */

    PrintWriter writer;
    public void prepare() {
        try {
            writer = new PrintWriter("graph.dot", "UTF-8");
            writer.println("digraph G {");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        writer.println("}");
        writer.close();
    }

    /**
     * generates the graph from the root
     * <p>
     * The method gets the edges of the first node writes each the graph with a path key
     * and the node value. Then it checks the pedth counter and if > 0 it calls the method on
     * the edges of the edges of the current node until depth reaches zero
     *
     * @param  n  the node to be visited
     * @param  remainingDepth  the depth counter for when to stop recursing
     */

    public void generateGraph(Node n, int remainingDepth) {
        n.generateEdges(LENGTH);
        for (int i = 0; i < n.getEdges().size(); i++) {
            if (!nodes.containsKey(n.name+"->"+n.getEdges().get(i).getDestination().name)) {
                nodes.put(n.name+"->"+n.getEdges().get(i).getDestination().name, n.getEdges().get(i).getDestination());
                writer.println("\"" + n.name + "\"" + " -> " + "\"" + n.getEdges().get(i).getDestination().name + "\"" + ";");
            }
        }
        if (remainingDepth > 0) {
            remainingDepth--;
            for (int i = 0; i < n.getEdges().size(); i++) {
                generateGraph(n.getEdges().get(i).getDestination(), remainingDepth);
            }
        }
    }

    /**
     * Return the number of nodes in a spanning tree
     * <p>
     * The method gets the set of keys in the graph and adds only one instance of
     * every node to a new list, effectively traversing the entire graph and generating
     * a spannign tree, it then returns the number of nodes in the new list
     *
     * @return      the number of nodes on the spanning tree
     */

    public int traverse() {
        Set<String> keys = nodes.keySet();
        List<String> uniques = new ArrayList<>();
        for (String k : keys) {
            if (!uniques.contains(nodes.get(k).name)) {
                uniques.add(nodes.get(k).getDoc().title());
            }
        }
        return uniques.size();
    }

    private void writeObject(ObjectOutputStream stream)
            throws IOException {
        stream.writeObject(nodes);
    }

    private void readObject(ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        nodes = (HashMap<String, Node>) stream.readObject();
    }

    public HashMap<String, Node> getNodes() {
        return nodes;
    }
}