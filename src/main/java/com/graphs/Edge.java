package com.graphs;

/**
 * Created by cesar on 4/16/17.
 */
public class Edge {
    private Node destination;
    private double weight;

    public Edge(Node n, double w) {
        destination = n;
        weight = w;
    }

    public Node getDestination() {
        return destination;
    }

    public void setDestination(Node destination) {
        this.destination = destination;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
