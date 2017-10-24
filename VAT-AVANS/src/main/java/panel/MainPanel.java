package panel;

import businessLogic.*;
import businessLogic.service.ConvertSV;
import VATFrame.*;
import domain.Shape;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;

public class MainPanel extends JPanel {
    private JButton loadButton;
    private JButton saveDBButton;
    private JTextField volumeField, totalField;
    private List shapeList;
    private Choice choices;
    private MainFrame frame;

    private ConvertSV convertSV;
    private ShapeController shapeController;

    MainPanel(){

    }

    public MainPanel(MainFrame mainFrame){
        frame = mainFrame;
        convertSV = frame.convertSV;
        shapeController = frame.shapeController;
        Border border = BorderFactory.createEmptyBorder(20,20,20,20);
        setBorder(border);
        Color Halloween  = new Color(255, 148, 86);
        setBackground(Halloween);
        setLayout(new GridLayout(8,2, 20, 20));

        JLabel saveLabel = new JLabel("Kies een vorm...", SwingConstants.LEFT);
        choices = new Choice();
        choices.add("Blok");
        choices.add("Bol");
        choices.add("Cilinder");
        choices.add("Ruit");
        choices.add("Rechthoek");

        choices.addItemListener(new ChoiceListener());

        JLabel volumeLabel = new JLabel("Inhoud:", SwingConstants.LEFT);
        volumeField = new JTextField("0.0");
        volumeField.setEditable(false);

        JLabel totalLabel = new JLabel("Totale inhoud:", SwingConstants.LEFT);
        totalField = new JTextField("0.0");
        totalField.setEditable(false);

        JButton loadButton = new JButton("Naar textbestand laden");
        loadButton.addActionListener(new ShowListListener());

        try {
            shapeController.getShapesFromDataFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        shapeList = shapeController.getShapesList();
        shapeList.addItemListener(new SelectedShapeListener());

        JButton totalButton = new JButton("Total inhoud");
        totalButton.addActionListener(new TotalVolumeListener());
        JButton deleteButton = new JButton("Verwijder");
        deleteButton.addActionListener(new RemoveShapeListener());

        JButton loadDBButton = new JButton("Laad in van database");
        loadDBButton.addActionListener(new GetFromDatabaseListener());

        saveDBButton = new JButton("Exporteer naar database");
        saveDBButton.addActionListener(new ExportToDatabaseListener());

        add(saveLabel);
        add(choices);
        add(volumeLabel);
        add(volumeField);
        add(totalLabel);
        add(totalField);
        add(new JLabel());
        add(totalButton);
        add(loadButton);
        add(shapeList);
        add(deleteButton);
        add(loadDBButton);
        add(saveDBButton);
    }

    class ExportToDatabaseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (shapeController.exportShapesToDatabase(shapeController.getShapeArrayList())) {
                saveDBButton.setBackground(Color.BLUE);
            } else {
                saveDBButton.setBackground(Color.GREEN);
            }
        }
    }

    class GetFromDatabaseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            shapeList.removeAll();
            ArrayList<Shape> shapes = shapeController.getShapesFromDatabase();
            for (Shape shape : shapes) {
                shapeList.add(shape.toString());
            }
        }
    }

    class SelectedShapeListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            Shape selectedShape = shapeController.getShapeArrayList().get(shapeList.getSelectedIndex());
            volumeField.setText(convertSV.round(selectedShape.getVolume())+"");
        }
    }

    class ChoiceListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            String choice = choices.getSelectedItem();
            createPopup(choice);
        }
    }



    class TotalVolumeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double volume = convertSV.round(shapeController.getTotalVolume());
            totalField.setText("" + volume);
        }
    }

    class ShowListListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            shapeList = shapeController.getShapesList();
        }
    }


    class RemoveShapeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            shapeController.removeShape(shapeList.getSelectedIndex());
            renewList();
        }
    }

    private void createPopup(String shapeName) {
        PopupFrame popupFrame = new PopupFrame();
        popupFrame.setLocation(1000, 300);
        popupFrame.setSize(400, 200);
        popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        switch (shapeName) {
            case "Bol":
                createSpherePanel(popupFrame);
                break;
            case "Blok":
                createCubePanel(popupFrame);
                break;
            case "Cilinder":
                createCylinderPanel(popupFrame);
                break;
            case "Ruit":
                createRhombusPanel(popupFrame);
                break;
            case "Rechthoek":
                createRectanglePanel(popupFrame);
                break;
        }
        popupFrame.setVisible(true);
    }

    private void createSpherePanel (PopupFrame popupFrame) {
        popupFrame.setTitle("Bol");
        popupFrame.setContentPane(new SpherePanel(frame, popupFrame, this));
    }

    private void createCubePanel (PopupFrame popupFrame) {
        popupFrame.setTitle("Blok");
        popupFrame.setContentPane(new CubePanel(frame, popupFrame, this));
    }
    private void createCylinderPanel(PopupFrame popupFrame) {
        popupFrame.setTitle("Cilinder");
        popupFrame.setContentPane(new CylinderPanel(frame, popupFrame, this));
    }

    private void createRhombusPanel (PopupFrame popupFrame) {
        popupFrame.setTitle("Ruit");
        popupFrame.setContentPane(new RhombusPanel(frame, popupFrame, this));
    }

    private void createRectanglePanel (PopupFrame popupFrame) {
        popupFrame.setTitle("Rechthoek");
        popupFrame.setContentPane(new RectanglePanel(frame, popupFrame, this));
    }

    void renewList () {
        loadButton.doClick();
    }
}


