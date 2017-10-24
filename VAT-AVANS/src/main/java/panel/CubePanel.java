package panel;


import VATFrame.MainFrame;
import VATFrame.PopupFrame;
import businessLogic.ShapeController;
import businessLogic.service.ConvertSV;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CubePanel extends MainPanel {
    private final JTextField lengthField;
    private final JTextField widthField;
    private final JTextField heightField;
    private final PopupFrame popupFrame;
    private final MainPanel panel;

    private final ConvertSV convertSV;
    private final ShapeController shapeController;

    public CubePanel (MainFrame mainFrame, PopupFrame popupFrame, MainPanel mainPanel) {
        panel = mainPanel;
        this.popupFrame = popupFrame;
        convertSV = mainFrame.convertSV;
        shapeController = mainFrame.shapeController;

        Border border = BorderFactory.createEmptyBorder(10,10,10,10);
        setBorder(border);
        Color Halloween  = new Color(255, 148, 86);
        setBackground(Halloween);
        setLayout(new GridLayout(3,3, 20, 20));

        JLabel lengthLabel = new JLabel("Lengte");
        lengthField = new JTextField();

        JLabel widthLabel = new JLabel("Breedte");
        widthField = new JTextField();

        JLabel heightLabel = new JLabel("Hoogte");
        heightField = new JTextField();

        JButton saveButton = new JButton("Opslaan");
        saveButton.addActionListener(new SaveListener());


        add(lengthLabel);
        add(widthLabel);
        add(heightLabel);
        add(lengthField);
        add(widthField);
        add(heightField);

        add(saveButton);

        popupFrame.getRootPane().setDefaultButton(saveButton);
    }

    class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double length, width, height;
            boolean shapeSaved = false;
            while (!shapeSaved) {
                length = convertSV.convertStringToDouble(lengthField);
                height = convertSV.convertStringToDouble(heightField);
                width = convertSV.convertStringToDouble(widthField);
                shapeSaved = shapeController.saveCube(length,height, width);
            }
            panel.renewList();
            popupFrame.dispose();
        }
    }
}
