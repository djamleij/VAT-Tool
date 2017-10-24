package domain;
import businessLogic.service.ConvertSV;

public class Rectangle extends Shape {
    private final double length;
    private final double width;

    public Rectangle(double length, double width){
        this.length = length;
        this.width = width;
        this.volume = ConvertSV.staticRound(this.calculate());
    }

    public double getLength(){return length;}
    public double getWidth(){return width;}

    @Override
    public String toString(){return "Rechthoek " + length + " " + width + " " + volume;}

    private double calculate (){return length * width;}

}
