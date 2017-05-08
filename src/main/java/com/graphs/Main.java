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
        String favorite = "/wiki/Asteroid_belt";
        Node test = new Node(favorite);
        Node test2 = new Node("/wiki/Italians");
        Node test3 = new Node("/wiki/Metroid");
        Path a = new Path(test);
        Path b = new Path(test2);
        Path c = new Path(test3);
        Path d = new Path(test);
        Path e = new Path(test);
        a.setWeight(2.3);
        b.setWeight(3.4);
        c.setWeight(5.6);
        d.setWeight(1.2);
        e.setWeight(10.4);
        Pq pq = new Pq();
        pq.add(a);
        pq.add(b);
        pq.add(c);
        pq.add(d);
        pq.add(e);
        System.out.println("done");
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