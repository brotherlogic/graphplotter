package com.github.brotherlogic.graphplotter;

import javax.swing.JPanel;
import java.awt.Graphics;

public class GraphPanel extends JPanel {

    public void paint(Graphics g) {
        int h = this.getHeight();
        int w = this.getWidth();

        // Draw the axes
        g.drawLine(h-10,5, h-10, w-10);
        g.drawLine(h-10,5,5,5);
    }
}
