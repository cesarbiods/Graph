package com.graphs;

import java.io.Serializable;

/**
 * Created by cesar on 4/16/17.
 */

/**
 * this object models the edges of every node and the nodes at the end
 * of such edges as well as the weight of the edge
 */
public class Edge implements Serializable{
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
