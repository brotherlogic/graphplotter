package com.github.brotherlogic.graphplotter;

import javax.swing.JPanel;
import java.awt.Graphics;

public class GraphPanel extends JPanel {

    public void paint(Graphics g) {
        int h = this.getHeight();
        int w = this.getWidth();

        // Draw the axes
        g.drawLine(w-10,5, w-10, h-10);
        g.drawLine(w-10,5,5,5);
    }
}
