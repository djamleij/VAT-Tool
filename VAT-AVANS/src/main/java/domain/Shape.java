package domain;

import java.io.Serializable;

public abstract class Shape implements Serializable {
    double volume;
    final double pi = Math.PI;

    public double getVolume(){return volume;}
}
