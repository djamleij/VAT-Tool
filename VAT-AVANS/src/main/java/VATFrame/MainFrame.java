package VATFrame;

import javax.swing.*;

import businessLogic.ShapeController;
import businessLogic.service.ConvertSV;

import java.awt.*;

public class MainFrame extends JFrame {
    public final ConvertSV convertSV;
    public final ShapeController shapeController;

    public MainFrame() throws HeadlessException{
        convertSV = new ConvertSV();
        shapeController = new ShapeController();
    }
}
