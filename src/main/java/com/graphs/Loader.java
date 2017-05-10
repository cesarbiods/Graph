package com.graphs;

import org.deeplearning4j.ui.standalone.ClassPathResource;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
        Graph graph = new Graph();
        graph.prepare();
        graph.generateGraph(test, graph.DEPTH);
        graph.close();

        Collection<Node> temps = graph.getNodes().values();
        List<String> titles = new ArrayList<>();
        for (Node n : temps) {
            titles.add(n.name);
        }
        long count = titles.stream()
                .distinct()
                .count();
        System.out.println("Regular graph nodes: " + count);
        System.out.println("Spanning tree nodes: " + graph.traverse());

        try {
            FileOutputStream fileOut =
                    new FileOutputStream("graph.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(graph);
            out.close();
            fileOut.close();
        }catch(IOException i) {
            i.printStackTrace();
        }
    }
}