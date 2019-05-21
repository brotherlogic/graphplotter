package com.github.brotherlogic.graphplotter;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedList;
import java.util.List;

public class GraphPanel extends JPanel {

    List<Line> lines = new LinkedList<Line>();

    public void addLine(Line l){
        lines.add(l);
    }

    public void paint(Graphics g) {
        int h = this.getHeight();
        int w = this.getWidth();

        // Draw the axes
        g.drawLine(5,h-10, w-10, h-10);
        g.drawLine(5,h-10,5,5);

        //Paint all the lines
        for (Line l :lines){
           long startTime = LocalDateTime.parse("2019-01-01T00:00:00").toEpochSecond(ZoneOffset.UTC);
           long endTime = LocalDateTime.parse("2020-01-01T00:00:00").toEpochSecond(ZoneOffset.UTC);

           int lastPointY = 0;
           for(int x = 1; x < w-10; x++){
               long timeP = ((endTime-startTime)/(w-15))*x+startTime;
               int value = l.getValue(timeP);
               g.drawLine(x-1+5,lastPointY, x+5, value);
               lastPointY = value;
           }
        }
    }
}
