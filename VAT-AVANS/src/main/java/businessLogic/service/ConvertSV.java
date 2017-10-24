package businessLogic.service;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConvertSV {

    public double convertStringToDouble(JTextField textField) {
        try {
            textField.setBackground(Color.WHITE);
            if (textField.getText().equals("")) {
                return 0;
            }
            return Double.parseDouble(textField.getText());
        } catch (NumberFormatException nfe) {
            textField.setBackground(Color.RED);
        }
        return 0;
    }

    /**
     * Rounds the value to a value with 2 decimals
     * @param value value to round
     * @return rounded value
     */
    public double round (double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Rounds the value to a value with 2 decimals in a static context
     * @param value value to round
     * @return rounded value
     */
    public static double staticRound(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}