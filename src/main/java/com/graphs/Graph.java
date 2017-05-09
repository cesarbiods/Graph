package com.graphs;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by cesar on 4/16/17.
 */
public class Graph {
    public static final int DEPTH = 2;
    public static final int LENGTH = 3;

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
            if (!nodes.containsKey(n.getDoc().title()+"->"+n.getEdges().get(i).getDestination().getDoc().title())) {
                nodes.put(n.getDoc().title()+"->"+n.getEdges().get(i).getDestination().getDoc().title(), n.getEdges().get(i).getDestination());
                writer.println("\"" + n.getDoc().title() + "\"" + " -> " + "\"" + n.getEdges().get(i).getDestination().getDoc().title() + "\"" + ";");
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
            if (!uniques.contains(nodes.get(k).getDoc().title())) {
                uniques.add(nodes.get(k).getDoc().title());
            }
        }
        return uniques.size();
    }

    public HashMap<String, Node> getNodes() {
        return nodes;
    }
}