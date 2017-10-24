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

class SpherePanel extends MainPanel {
    private final JTextField radiusField;
    private final PopupFrame popupFrame;
    private final MainPanel panel;

    private final ConvertSV convertSV;
    private final ShapeController shapeController;

    public SpherePanel(MainFrame mainFrame, PopupFrame popupFrame, MainPanel mainPanel) {
        panel = mainPanel;
        this.popupFrame  = popupFrame;
        convertSV = mainFrame.convertSV;
        shapeController = mainFrame.shapeController;
        Border border = BorderFactory.createEmptyBorder(10,10,10,10);
        setBorder(border);
        Color Halloween  = new Color(255, 148, 86);
        setBackground(Halloween);
        setLayout(new GridLayout(3,1, 20, 20));

        JLabel radiusLabel = new JLabel("Straal:", SwingConstants.CENTER);
        radiusField = new JTextField();

        JButton saveButton = new JButton("Opslaan");
        saveButton.addActionListener(new SaveListener());

        add(radiusLabel);
        add(radiusField);

        add(saveButton);
        popupFrame.getRootPane().setDefaultButton(saveButton);
    }

    class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double radius;
            boolean shapeSaved = false;
            while (!shapeSaved) {
                radius = convertSV.convertStringToDouble(radiusField);
                shapeSaved = shapeController.saveSphere(radius);
            }
            panel.renewList();
            popupFrame.dispose();
        }
    }
}
