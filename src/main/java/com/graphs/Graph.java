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