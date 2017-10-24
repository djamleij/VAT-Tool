package businessLogic.service;

import domain.Shape;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class DataOutputSV {

    public DataOutputSV(Shape shape) {
        try {
            new File("data").mkdir();
            boolean fileExists = new File("data/shapes.data").exists();
            FileOutputStream fileOutputStream = new FileOutputStream("data/shapes.data", true);
            ObjectOutputStream objectOutputStream = fileExists ?
                    new ObjectOutputStream(fileOutputStream) {
                        protected void writeStreamHeader() throws IOException {
                            reset();
                        }
                    } : new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(shape);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            System.out.println("Fout opgetreden in het bestand");
            e.printStackTrace();
        }
    }
}

