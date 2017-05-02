package com.graphs;


import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by graphs on 4/12/17.
 */
public class Main {
    public static void main(String[] args)  {
        String favorite = "/wiki/Metroid_Prime";
        Node test = new Node(favorite);
        Graph graph = new Graph();
        graph.prepare();
        graph.generateGraph(test, graph.DEPTH);
        graph.close();
        Collection<Node> temps = graph.getNodes().values();
        List<String> titles = new ArrayList<>();
        for (Node n : temps) {
            titles.add(n.getDoc().title());
        }
        long count = titles.stream()
                .distinct()
                .count();
        System.out.println("Unique wikis: " + count);
        System.out.println("Spanning tree nodes: " + graph.traverse());
    }
}