package com.github.brotherlogic.graphplotter;

import com.github.brotherlogic.javaserver.JavaServer;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

import java.awt.Color;
import java.awt.Dimension;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import godiscogs.Godiscogs.Image;
import godiscogs.Godiscogs.Release;
import recordcollection.Recordcollection.ReleaseMetadata.Category;
import io.grpc.BindableService;
import recordgetter.Recordgetter.GetRecordResponse;

public class Runner extends JavaServer {

    MainDisplay mainDisplay;

    public static void main(String[] args) throws Exception {
        Option optionHost = OptionBuilder.withLongOpt("server").hasArg().withDescription("Hostname of server")
                .create("s");
        Options options = new Options();
        options.addOption(optionHost);

        CommandLineParser parser = new GnuParser();
        CommandLine line = parser.parse(options, args);

        String rServer = "192.168.86.42";
        if (line.hasOption("server"))
            rServer = line.getOptionValue("s");

        Runner r = new Runner();
        r.Serve(rServer);
    }

    @Override
    public String getServerName() {
        return "GraphPlotter";
    }

    @Override
    public List<BindableService> getServices() {
        return new LinkedList<BindableService>();
    }

    private void displayScreen() {
        if (mainDisplay == null) {
            mainDisplay = new MainDisplay(new Getter(getHost("recordgetter"), getPort("recordgetter")));
        }

        mainDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainDisplay.pack();
        mainDisplay.setSize(new Dimension(800, 480));
        mainDisplay.setLocationRelativeTo(null);
        mainDisplay.revalidate();
        mainDisplay.setVisible(true);
    }


    public void refreshDisplay() {
        // Do nothing
    }

    @Override
    public void localServe() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                displayScreen();
            }
        });

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                refreshDisplay();
            }
        });
        t.start();
    }
}
