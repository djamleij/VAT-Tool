package main;

import javax.swing.*;

import VATFrame.*;
import panel.MainPanel;


public class VATMain extends JFrame {

    public static void main(String[] args) {
        MainFrame ShapeApplication = new MainFrame();

        ShapeApplication.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ShapeApplication.setTitle("Vorm Analyse Tool");
        ShapeApplication.setLocation(1000, 300);
        ShapeApplication.setSize(700, 700);
        ShapeApplication.setContentPane(new MainPanel(ShapeApplication));
        ShapeApplication.setVisible(true);
    }
}
