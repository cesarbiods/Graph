package com.graphs;

import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cesar on 4/16/17.
 */
public class Graph {
    public static final int DEPTH = 3;
    public static final int LENGTH = 5;

    private List nodes;

    public Graph() {
        nodes = new ArrayList<Node>();
    }

    public void generateGraph(Node n, int remainingDepth) {
        n.generateEdges(LENGTH);
        for (int i = 0; i < n.getEdges().size(); i++) {
            nodes.add(n.getEdges().get(i).getDestination());
        }
        if (remainingDepth > 0) {
            remainingDepth--;
            for (int i = 0; i < n.getEdges().size(); i++) {
                generateGraph(n.getEdges().get(i).getDestination(), remainingDepth);
            }
        }
    }

    public List getNodes() {
        return nodes;
    }
}