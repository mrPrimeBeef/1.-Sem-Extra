package csg;

import org.abstractica.javacsg.Geometry2D;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;

public class Cross {
    private final double brickSize;
    private final double width;
    private final double height;


    public Cross(double brickSize, double width, double height) {
        this.brickSize = brickSize;
        this.width = width;
        this.height = height;
    }

    public Geometry3D getGeometry(JavaCSG csg) {
        Geometry2D cross = getFinalCross2D(csg);
        Geometry3D res = csg.linearExtrude(height,false, cross);
        return res;
    }

    private Geometry2D getArm(JavaCSG csg) {
        Geometry2D armA = csg.rectangle2D(Math.sqrt(2)*brickSize,width);
        //Geometry2D armB = csg.rotate2D(csg.rotations(0.5),armA);
        return armA;
    }

    private Geometry2D getCross(JavaCSG csg) {
        Geometry2D arm = getArm(csg);
        Geometry2D arm2 = csg.rotate2D(csg.degrees(90)).transform(arm);
        Geometry2D cross = csg.union2D(arm, arm2);
        return cross;
    }

    private Geometry2D getFinalCross2D(JavaCSG csg) {
        Geometry2D cross = getCross(csg);
        cross = csg.rotate2D(csg.degrees(45)).transform(cross);
        Geometry2D bound = csg.rectangle2D(brickSize, brickSize);
        cross = csg.intersection2D(cross, bound);
        return cross;
    }
}
