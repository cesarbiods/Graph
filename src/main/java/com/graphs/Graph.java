package com.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cesar on 4/16/17.
 */
public class Graph {
    private String baseurl;
    private Node first;
    private List nodes;

    public Graph() {
        nodes = new ArrayList<Node>();
    }

    public String getBaseurl() {
        return baseurl;
    }

    public void setBaseurl(String baseurl) {
        this.baseurl = baseurl;
    }

    public Node getFirst() {
        return first;
    }

    public void setFirst(Node first) {
        this.first = first;
    }

}