package com.graphs;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Created by cesar on 5/7/17.
 */
public class Dijkstra {
    public Pq pq;
    public HashMap<String, Node> nodeMap;

    public Dijkstra() {
        pq = new Pq();
    }

    /**
     * Gets all the nodes from the persistent graph
     *
     * @param  graph  the persistent graph with all the nodes
     */

    public void getNodes(Graph graph) {
        nodeMap = new HashMap<>();
        HashMap<String, Node> source = graph.getNodes();
        source.forEach((k, v) ->
                nodeMap.put(k.substring(k.indexOf("->") + 2), v)
        );
    }

    /**
     * Computes the shortest path between two nodes nodes
     * <p>
     * The method assumes you preload the first path to the priority queue.
     * so it that it's not empty. First it polls the first node off the queue and checks that
     * it has a value, if it doesn't it means it has nowhere else to go and no shortest path exists.
     * Otherwise it looks at all the edges of the node in it and checks to see if
     * it finds the destination node, in which case the method returns the shortest path. Otherwise
     * the method adds those nodes at the edges to the queue and keeps searching searching for
     * the destination
     *
     * @param  destination  the end node that we are trying to reach
     * @return      shortest path between the nodes, or empty if one doesn't exist
     */

    public Optional<Path> sP (Node destination)
    {
        Optional<Path> bestPath = Optional.empty();
        while (!pq.isEmpty())
        {
            Optional<Path> path = pq.poll();
            if (path.isPresent())
            {
                Path p = path.get();
                Node current = p.getDestination();
                for (Edge edge : current.getEdges())
                {
                    ArrayList<Node> list = new ArrayList<>();
                    list.addAll(p.getWholePath());
                    Path newPath = new Path(edge.getDestination(), edge.getWeight(), list);
                    if (edge.getDestination().name.equals(destination.name))
                        bestPath = Optional.of(newPath);
                    else
                        pq.add(newPath);
                    newPath.getWholePath().forEach(ele -> System.out.print(ele.name + ", "));
                    System.out.println();
                }
            }
        }
        return bestPath;
    }
}
