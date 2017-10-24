package domain;

import businessLogic.service.ConvertSV;

public class Rhombus extends Shape {
    private final double length;
    private final double height;

    public Rhombus(double length, double height){
        this.length = length;
        this.height = height;
        this.volume = ConvertSV.staticRound(this.calculate());
    }

    public double getLength(){return length;}
    public double getHeight(){return height;}

    @Override
    public String toString(){return "Ruit " + length + " " + height + " " + volume;}

    double calculate(){return length * height;}

}
