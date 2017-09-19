package com.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cesar on 5/6/17.
 */

/**
 * This object is used for the priority queue and dijkstra to keep track of
 * a path's destination node, its total weight from all the edges it traversed,
 * as well as a list of all the nodes it visited to be returned in the end
 */
public class Path {
    private Node location;
    private double weight;
    private List<Node> wholePath;

    public Path(Node n) {
        location = n;
        wholePath = new ArrayList<>();
        wholePath.add(location);
        weight = 0;
    }

    public Path(Node l, double w, List<Node> ns) {
        location = l;
        weight += w;
        wholePath = new ArrayList<>(); //deep copy of wholePath
        wholePath.addAll(ns);
        wholePath.add(location);
    }

    public Node getDestination() {
        return location;
    }

    public void setDestination(Node location) {
        this.location = location;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public List<Node> getWholePath() {
        return wholePath;
    }

    public Node last() {
        return wholePath.get(wholePath.size() - 1);
    }
}
