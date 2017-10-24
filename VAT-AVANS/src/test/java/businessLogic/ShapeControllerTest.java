package businessLogic;

import domain.*;
import domain.Shape;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ShapeControllerTest {
    private static final int NR_OF_SHAPES = 4;
    private static final double VOLUME_OF_SHAPES = 4480.09;

    private ShapeController shapeController;
    private Shape cylinder, cube, rhombus, sphere;
    private ArrayList<Shape> generatedArrayList, createdArrayList;
    private List generatedShapesList, createdShapesList;

    @Before
    public void setup () {
        cylinder = new Cylinder(10, 10);
        cube = new Cube(10, 10, 10);
        rhombus = new Rhombus(10, 15);
        sphere = new Sphere(15);
        shapeController = new ShapeController();
        createArrayListAndList();
        generateArrayListAndList();
    }

    @Test
    public void getShapesArrayListTest() {
        assertEquals(generatedArrayList.size(), NR_OF_SHAPES);
        assertEquals(createdArrayList, generatedArrayList);
        assertEquals(createdShapesList.getItems(), generatedShapesList.getItems());
    }

    @Test
    public void getTotalVolumeTest() {
        assertEquals(shapeController.getTotalVolume(), VOLUME_OF_SHAPES, 0);
    }

    @Test
    public void removeShapeTest() {
        int initialSize = generatedArrayList.size();
        assertEquals(initialSize, 4);
        shapeController.removeShape(0);
        shapeController.removeShape(0);
        int finalSize = generatedArrayList.size();
        assertEquals(finalSize, 2);
    }

    @Test
    public void saveEmptyCubeTest () {
        assertFalse(shapeController.saveCube(0,0,0));
    }

    @Test
    public void saveNewSphereTest () {
        assertTrue(shapeController.saveSphere(15));
        assertEquals(generatedArrayList.size(), 5);
        assertTrue(sphere.toString().equals(shapeController.getShape(4).toString()));
    }

    private void createArrayListAndList () {
        createdArrayList = new ArrayList<>();
        createdArrayList.add(cylinder);
        createdArrayList.add(cube);
        createdArrayList.add(rhombus);
        createdArrayList.add(sphere);

        createdShapesList = new List();
        for (Shape shape : createdArrayList) {
            createdShapesList.add(shape.toString());
        }
    }

    private void generateArrayListAndList () {
        shapeController.addShape(cylinder);
        shapeController.addShape(cube);
        shapeController.addShape(rhombus);
        shapeController.addShape(sphere);
        generatedArrayList = shapeController.getShapeArrayList();
        generatedShapesList = new List();
        generatedShapesList = shapeController.getShapesList();
    }
}