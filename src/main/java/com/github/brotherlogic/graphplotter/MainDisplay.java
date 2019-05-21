package com.github.brotherlogic.graphplotter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import godiscogs.Godiscogs.Release;
import godiscogs.Godiscogs.Label;

public class MainDisplay extends JFrame {

    GraphPanel mainPanel;

    public MainDisplay(Getter g) {
        mainPanel = new GraphPanel();
        mainPanel.addLine(new StraightLine(LocalDateTime.parse("2019-01-01T00:00:00").toEpochSecond(ZoneOffset.UTC),
                LocalDateTime.parse("2020-01-01T00:00:00").toEpochSecond(ZoneOffset.UTC),
                0,470));
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(800, 480));
        this.add(mainPanel, BorderLayout.CENTER);
    }
}
