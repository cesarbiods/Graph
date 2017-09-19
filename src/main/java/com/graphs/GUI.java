package com.graphs;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by cesar on 2/23/17.
 */
public class GUI {
    public GUI() throws IOException {
        initUI();
    }

    /**
     * Initiates the GUI
     * <p>
     * Initiates the GUI dimensions and default behavior.
     */

    private void initUI() throws IOException {
        JFrame frame = new JFrame("Graph");
        frame.setMinimumSize(new Dimension(1000, 90));
        //frame.setPreferredSize(new Dimension(100, 60));
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new Panel());
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
