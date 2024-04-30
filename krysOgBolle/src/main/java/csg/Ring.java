package csg;

import org.abstractica.javacsg.Geometry2D;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;

public class Ring {
    private final double brickSize;
    private final double height;

    public Ring(double brickSize, double height) {
        this.brickSize = brickSize;
        this.height = height;

    }

    public Geometry3D createRing(JavaCSG csg){
        Geometry2D ring2D = csg.ring2D(brickSize-10,brickSize,128);

        Geometry3D ring3D = csg.linearExtrude(height,false,ring2D);

        return ring3D;
    }


}
