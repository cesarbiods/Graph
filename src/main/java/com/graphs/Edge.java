package com.graphs;

/**
 * Created by cesar on 4/16/17.
 */
public class Edge {
    private Node destination;
    private int weight;

    public Edge(Node n) {
        destination = n;
    }

    public Node getDestination() {
        return destination;
    }

    public void setDestination(Node destination) {
        this.destination = destination;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
