package com.graphs;

import org.deeplearning4j.ui.standalone.ClassPathResource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by graphs on 4/12/17.
 */
public class Main {
    public static void main(String[] args)  {
        Pq pq = new Pq();
        pq.add("Hi");
        pq.add("Hello");
        pq.add("Why hello there");
        pq.add("Ai");
        pq.add("fak fak");
        System.out.println(pq.poll().get());
        System.out.println(pq.poll().get());
        System.out.println(pq.poll().get());
        System.out.println(pq.poll().get());
        System.out.println(pq.poll().get());
//        String favorite = "/wiki/Asteroid_belt";
//        Node test = new Node(favorite);
//        Node test2 = new Node("/wiki/Italians");
//        System.out.println(test.findSimilarity(test2));
//        Graph graph = new Graph();
//        graph.prepare();
//        graph.generateGraph(test, graph.DEPTH);
//        graph.close();
//        Collection<Node> temps = graph.getNodes().values();
//        List<String> titles = new ArrayList<>();
//        for (Node n : temps) {
//            titles.add(n.getDoc().title());
//        }
//        long count = titles.stream()
//                .distinct()
//                .count();
//        System.out.println("Unique wikis: " + count);
//        System.out.println("Spanning tree nodes: " + graph.traverse());
    }
}