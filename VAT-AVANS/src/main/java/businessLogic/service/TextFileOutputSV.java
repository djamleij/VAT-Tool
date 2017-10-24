package businessLogic.service;

import domain.Shape;

import java.io.FileWriter;
import java.io.IOException;

import static java.lang.System.out;

public class TextFileOutputSV {

    public TextFileOutputSV(Shape shape) {

        try {
            FileWriter fileWriter = new FileWriter("C:/Users/Dylan/Desktop/VAT-AVANS/data/shapes.txt", true);
            String output = shape.toString();
            fileWriter.write(output);
            fileWriter.write("\r\n");
            fileWriter.close();
        } catch (IOException e) {
            out.println("Fout met textbestand.");
            e.printStackTrace();
        }
        }
    }


