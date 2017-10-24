package businessLogic;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import businessLogic.database.SaveToDatabaseService;
import businessLogic.database.LoadFromDatabaseService;
import businessLogic.service.DataOutputSV;
import businessLogic.service.TextFileOutputSV;
import domain.*;
import domain.Rectangle;
import domain.Shape;


public class ShapeController {
    private ArrayList<Shape> shapeArrayList;
    private final List shapesList;
    private LoadFromDatabaseService loadFromDatabaseService;
    private SaveToDatabaseService saveToDatabaseService;

    public ShapeController () {
        shapeArrayList = new ArrayList<>();
        shapesList = new List();
        loadFromDatabaseService = null;
        saveToDatabaseService = null;
    }

    public ArrayList<Shape> getShapeArrayList() {
        return shapeArrayList;
    }

    public List getShapesList () {
        shapesList.removeAll();
        for (Shape shape : shapeArrayList) {
            shapesList.add(shape.toString());
        }
        return shapesList;
    }

    public void getShapesFromDataFile () throws IOException {
        ArrayList<Shape> shapes = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("data/shapes.data");
            ois = new ObjectInputStream(fis);
            while (fis.available() > 0) {
                shapes.add((Shape) ois.readObject());
            }
        } catch (EOFException eof) {
            // End of file reached
            System.out.println("End of file.");
        } catch (FileNotFoundException fne) {
            System.out.println("File not found.");
        }  catch (ClassNotFoundException | IOException cnf) {
            cnf.printStackTrace();
        } catch (ClassCastException cce) {
            System.out.println("Wrong input class");
            cce.printStackTrace();
        }
        // close resources and set the retrieved list as the currently used list in the application
        finally {
            if (fis != null) {
                fis.close();
            }
            if (ois != null) {
                ois.close();
            }
            setShapeArrayList(shapes);
        }
    }

    public ArrayList<Shape> getShapesFromDatabase () {
        loadFromDatabaseService = new LoadFromDatabaseService("root");
        return loadFromDatabaseService.getAllFromDatabase();
    }

    public boolean exportShapesToDatabase (ArrayList<Shape> shapes) {
        saveToDatabaseService = new SaveToDatabaseService("root");
        return saveToDatabaseService.saveAllToDatabase(shapes);
    }

    private void setShapeArrayList(ArrayList<Shape> shapeArrayList) {
        this.shapeArrayList = shapeArrayList;
    }

    public double getTotalVolume() {
        double total = 0;
        for (Shape shape : shapeArrayList) {
            total += shape.getVolume();
        }
        return total;
    }

    boolean addShape (Shape shape) {
        return shapeArrayList.add(shape);
    }

    public void removeShape (int index) {
        if (!shapeArrayList.isEmpty() && index >= 0) {
            shapeArrayList.remove(index);
        }
    }

    public Shape getShape (int index) {
        return shapeArrayList.get(index);
    }

    private boolean writeShape (Shape shape) {
        new DataOutputSV(shape);
        new TextFileOutputSV(shape);
        new TextFileOutputSV(shape);
        return true;
    }

    public boolean saveCube(double length, double height, double width) {
        if (length != 0 && height != 0 && width != 0) {
            Shape cube = new Cube(length, width, height);
            return addShape(cube) && writeShape(cube);
        }
        return false;
    }

    public boolean saveCylinder(double height, double radius) {
        if (radius != 0 && height != 0) {
            Shape cylinder = new Cylinder(radius, height);
            return addShape(cylinder) && writeShape(cylinder);
        }
        return false;
    }

    public boolean saveSphere (double radius) {
        if (radius != 0) {
            Shape sphere = new Sphere(radius);
            return addShape(sphere) && writeShape(sphere);
        }
        return false;
    }

    public boolean saveRectangle (double length, double width) {
        if (length != 0 && width != 0) {
            Shape rectangle = new Rectangle(length, width);
            return addShape(rectangle) && writeShape(rectangle);
        }
        return false;
    }

    public boolean saveRhombus (double length, double height) {
        if (length != 0 && height != 0) {
            Shape rhombus = new Rhombus(length, height);
            return addShape(rhombus) && writeShape(rhombus);
        }
        return false;
    }
}