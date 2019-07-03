package com.github.brotherlogic.graphplotter;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class IssuePanel extends JPanel {
    String issue = "Current issue";
    JLabel label = new JLabel(issue);

    public IssuePanel() {
        this.add(label);
    }

    public void setIssue(String issue){
        label.setText(issue);
    }
}
