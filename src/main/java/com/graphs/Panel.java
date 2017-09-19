package com.graphs;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Optional;
import java.util.Set;

/**
 * Created by cesar on 2/23/17.
 */
public class Panel extends JPanel{
    private JButton b;
    private JComboBox<String> first;
    private JComboBox<String> second;

    public Panel() throws IOException {

        /**
         * This section of the application dezerializes the graph and initializes the
         * priority queue to be used for path finding
         */
        Graph graph = null;
        try {
            FileInputStream fileIn = new FileInputStream("graph.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            graph = (Graph) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException | ClassNotFoundException i) {
            i.printStackTrace();
            return;
        }
        Dijkstra paths = new Dijkstra();
        paths.getNodes(graph);

        /**
         * Here the GUI components are initialized and two defaults are
         * assigned to the dropdown menus
         */

        String[] patternExamples = paths.nodeMap.keySet().toArray(new String[0]);

        first = new JComboBox<>(patternExamples);
        second = new JComboBox<>(patternExamples);
        first.setSelectedItem("Computer - Wikipedia");
        second.setSelectedItem("Arithmetic - Wikipedia");
        first.setEditable(true);
        second.setEditable(true);
        b = new JButton("Find shortest path");

        first.addActionListener(event -> {
            JComboBox<String> selection = (JComboBox<String>) event.getSource();
        });
        second.addActionListener(event -> {
            JComboBox<String> selection = (JComboBox<String>) event.getSource();
        });

        /**
         * This section pulls two node selections from the drop downs and
         * when the user presses the button gets those nodes from the graph and calls the shortest
         * path method to print out the entire shortest path if it exists, otherwise it returns
         * an empty path message. Regardless of the path finding, it also runs cosine similarity on each node and every other node
         * in the whole graph so it always returns the most similar node to each selection
         * from the whole graph
         */

        b.addActionListener(event -> {
            String firstNode = (String) first.getSelectedItem();
            String secondNode = (String) second.getSelectedItem();
            String visualPath = "";
            Node node1 = paths.nodeMap.get(firstNode);
            Node node2 = paths.nodeMap.get(secondNode);
            Path start = new Path(node1);
            paths.pq.add(start);
            Optional<Path> path = paths.sP(node2);

            if (!path.isPresent()) {
                visualPath = "No such path exists";
            } else {
                for (Node node : path.get().getWholePath()) {
                    visualPath += node.name + "->";
                }
                visualPath = visualPath.substring(0, visualPath.lastIndexOf("->"));
                visualPath = visualPath.replace("- Wikipedia", "");
            }

            double similar1 = Double.MIN_VALUE;
            double similar2 = Double.MIN_VALUE;
            Node mostSimilar1 = null;
            Node mostSimilar2 = null;
            Set<String> keys = paths.nodeMap.keySet();
            for (String string : keys) {
                double temp1 = node1.findSimilarity(paths.nodeMap.get(string));
                if (temp1 > similar1 && !string.equals(firstNode)) {
                    similar1 = temp1;
                    mostSimilar1 = paths.nodeMap.get(string);
                }
                double temp2 = node2.findSimilarity(paths.nodeMap.get(string));
                if (temp2 > similar2 && !string.equals(secondNode)) {
                    similar2 = temp2;
                    mostSimilar2 = paths.nodeMap.get(string);
                }
            }
            JOptionPane.showMessageDialog(Panel.this,
                    "Shortest Path: " + visualPath + "\n" + "Most similar node to node 1: "
            + mostSimilar1.name + "\n" + "Most similar node to node 2: " + mostSimilar2.name);
        });
        add(first);
        add(second);
        add(b);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new GUI();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
