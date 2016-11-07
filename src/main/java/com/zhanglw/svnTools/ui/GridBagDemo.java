package com.zhanglw.svnTools.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by zhanglw on 2016/7/9.
 */


public class GridBagDemo extends JFrame {


    /**
     * constructor.
     */
    public GridBagDemo(){
        initGUI();
    }


    public void initGUI() {

        setTitle("");


        JPanel panel = new JPanel(new GridBagLayout());
        this.getContentPane().add(panel);


        String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};

        Object[][] data = {
                {"Kathy", "Smith",
                        "Snowboarding", new Integer(5), new Boolean(false)},
                {"John", "Doe",
                        "Rowing", new Integer(3), new Boolean(true)},
                {"Sue", "Black",
                        "Knitting", new Integer(2), new Boolean(false)},
                {"Jane", "White",
                        "Speed reading", new Integer(20), new Boolean(true)},
                {"Joe", "Brown",
                        "Pool", new Integer(10), new Boolean(false)}
        };

        JTable table = new JTable(data, columnNames);

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(200, 50));


        JLabel label = new JLabel("My Things");

        JPanel tableButtonPanel = new JPanel();
        tableButtonPanel.add(new JButton("Add Thing"));
        tableButtonPanel.add(new JButton("Delete Thing"));
        tableButtonPanel.add(new JButton("Modify Thing"));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JButton("Print"));
        buttonPanel.add(new JButton("History"));
        buttonPanel.add(new JButton("Preferences"));
        buttonPanel.add(new JButton("Another Button"));
        buttonPanel.add(new JButton("Add Another"));
        buttonPanel.add(new JButton("Yet Another"));

        JPanel detailsPanel = new JPanel();
        detailsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(label, gbc);


        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(tableScrollPane, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        gbc.weighty = 0;
        panel.add(tableButtonPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.NORTH;

        panel.add(createDetailsPanel(), gbc);

        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }

    private JPanel createDetailsPanel() {


        JPanel panel = new JPanel();

        JLabel thingNameLabel = new JLabel("Thing Name");
        JLabel anAttributeLabel = new JLabel("An Attribute");
        JLabel dateFieldLabel = new JLabel("Date Field");
        JLabel anAttLabel = new JLabel("An Att");
        JLabel anotherAttLabel = new JLabel("Another Att");
        JLabel anotherAtt2Label = new JLabel("Another Att");

        JTextField thingNameField = new JTextField("");
        JTextField anAttributeField = new JTextField("");
        JTextField dateFieldField = new JTextField("");
        JTextField anAttField = new JTextField("");
        JTextArea anotherAttField = new JTextArea(3, 1);
        JTextField anotherAtt2Field = new JTextField("", 10);

        anotherAtt2Field.setMinimumSize(anotherAtt2Field.getPreferredSize());


        JCheckBox checkbox1 = new JCheckBox("A Checkbox");
        JCheckBox checkbox2 = new JCheckBox("A Checkbox");


        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        int i=0;

        gbc.insets = new Insets(2,2,2,2);
        gbc.anchor = GridBagConstraints.NORTHEAST;

        gbc.gridx = 0;
        gbc.gridy = i;
        panel.add(thingNameLabel,  gbc);

        gbc.gridx = 1;
        gbc.gridy = i;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(thingNameField,  gbc);

        i++;

        gbc.gridx = 1;
        gbc.gridy = i;
        gbc.gridwidth = 2;
        panel.add(checkbox1,  gbc);

        i++;

        gbc.gridx = 0;
        gbc.gridy = i;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(anAttributeLabel,  gbc);

        gbc.gridx = 1;
        gbc.gridy = i;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(anAttributeField,  gbc);

        i++;

        gbc.gridx = 0;
        gbc.gridy = i;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(dateFieldLabel,  gbc);

        gbc.gridx = 1;
        gbc.gridy = i;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(dateFieldField,  gbc);

        i++;

        gbc.gridx = 0;
        gbc.gridy = i;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(anAttLabel,  gbc);

        gbc.gridx = 1;
        gbc.gridy = i;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(anAttField,  gbc);

        i++;

        gbc.gridx = 0;
        gbc.gridy = i;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(anotherAttLabel,  gbc);

        gbc.gridx = 1;
        gbc.gridy = i;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.weightx = 1.0;
//		gbc.weighty = 1.0;
        panel.add(new JScrollPane(anotherAttField),  gbc);

        i++;
        gbc.gridx = 0;
        gbc.gridy = i;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(anotherAtt2Label,  gbc);

        gbc.gridx = 1;
        gbc.gridy = i;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(anotherAtt2Field,  gbc);


        gbc.gridx = 2;
        gbc.gridy = i;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(checkbox2,  gbc);

        return panel;
    }


    public static void main(String[] args) {
        GridBagDemo frame = new GridBagDemo();


        frame.pack();
        frame.setVisible(true);
    }
}


