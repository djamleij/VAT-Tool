package domain;

import businessLogic.service.ConvertSV;

public class Sphere extends Shape{
    private final double radius;
    private static final double CONSTANT = (double) 4/3;

    public Sphere(double radius){
        this.radius = radius;
        this.volume = ConvertSV.staticRound(this.calculate());
    }

    public double getRadius(){return radius;}

    @Override
    public String toString(){return "Bol " + radius + " " + volume;}

    private double calculate (){ return (CONSTANT*pi*(radius * 3));}

}
