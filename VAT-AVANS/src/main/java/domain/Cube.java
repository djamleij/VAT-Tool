package domain;


import businessLogic.service.ConvertSV;

public class Cube extends Shape {
    private final double length;
    private final double width;
    private final double height;

    public Cube(double length, double width, double height){
        this.length = length;
        this.width = width;
        this.height = height;
        this.volume = ConvertSV.staticRound(this.calculate());
    }
    public double getLength() { return length;}

    public double getWidth(){ return width;}

    public double getHeight(){ return  height;}

    @Override
    public String toString(){return "Blok " + length + " " + width + " "+ height+ " " + volume; }

    private double calculate () { return length * width * height;}
}
