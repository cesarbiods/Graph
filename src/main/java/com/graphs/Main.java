package com.graphs;


import org.jsoup.nodes.Element;

/**
 * Created by graphs on 4/12/17.
 */
public class Main {
    public static void main(String[] args)  {
        String favorite = "/wiki/Metroid_Prime_3:_Corruption";
        Node test = new Node(favorite);
        test.generateEdges();
    }
}