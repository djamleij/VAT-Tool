package domain;

import businessLogic.service.ConvertSV;

public class Cylinder extends Shape {
    private final double height;
    private final double radius;

    public Cylinder(double radius, double height) {
        this.height = height;
        this.radius = radius;
        this.volume = ConvertSV.staticRound(this.calculate());
    }

        public double getHeight() {
            return height;
        }

        public double getRadius() {
            return radius;
        }

        @Override
        public String toString() {
            return "Cilinder " + height + " " + radius + " " + volume;
        }

        private double calculate () {
            return pi * (radius * radius) * height;
        }
    }
