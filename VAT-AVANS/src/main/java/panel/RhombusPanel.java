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

class RhombusPanel extends MainPanel {
    private final JTextField lengthField;
    private final JTextField heigthField;
    private final PopupFrame popupFrame;
    private final MainPanel panel;

    private final ConvertSV convertSV;
    private final ShapeController shapeController;

    public RhombusPanel(MainFrame mainFrame, PopupFrame popupFrame, MainPanel mainPanel) {
        panel = mainPanel;
        this.popupFrame = popupFrame;
        convertSV = mainFrame.convertSV;
        shapeController = mainFrame.shapeController;

        Border border = BorderFactory.createEmptyBorder(10,10,10,10);
        setBorder(border);
        Color Halloween  = new Color(255, 148, 86);
        setBackground(Halloween);
        setLayout(new GridLayout(3,2, 20, 20));


        JLabel lengthLabel = new JLabel("Lengte:");
        lengthField = new JTextField();

        JLabel heigthLabel = new JLabel("Hoogte:");
        heigthField = new JTextField();

        JButton saveButton = new JButton("Opslaan");
        saveButton.addActionListener(new SaveListener());

        add(lengthLabel);
        add(lengthField);
        add(heigthLabel);
        add(heigthField);

        add(saveButton);
        popupFrame.getRootPane().setDefaultButton(saveButton);
    }

    class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double length, heigth;
            boolean shapeSaved = false;
            while (!shapeSaved) {
                length = convertSV.convertStringToDouble(lengthField);
                heigth = convertSV.convertStringToDouble(heigthField);
                shapeSaved = shapeController.saveRhombus(length, heigth);
            }
            panel.renewList();
            popupFrame.dispose();
        }
    }
}
