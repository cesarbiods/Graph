package com.graphs;

import org.deeplearning4j.ui.standalone.ClassPathResource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by graphs on 4/12/17.
 */
public class Loader {
    public static void main(String[] args)  {
        String favorite = "/wiki/Metroid";
        Node test = new Node(favorite);
        Node test2 = new Node("/wiki/Japanese_language");
        Graph graph = new Graph();
        graph.prepare();
        graph.generateGraph(test, graph.DEPTH);
        if (graph.getNodes().containsValue(test)) {
            System.out.println("Metorid here");
        }
        if (graph.getNodes().containsValue(test2)) {
            System.out.println("Weebos here");
        }
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