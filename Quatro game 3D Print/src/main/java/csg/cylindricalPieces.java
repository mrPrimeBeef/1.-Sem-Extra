package csg;

import org.abstractica.javacsg.Geometry2D;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;

public class cylindricalPieces {
    private final double brickSize;
    private final double height;
    private final double indentHeight;

    public cylindricalPieces(double brickSize, double height, int indentHeight) {
        this.brickSize = brickSize;
        this.height = height;
        this.indentHeight = indentHeight;
    }

    public Geometry3D getSmallCylindricalPieceNoHole(JavaCSG csg){
        return createCylinderPieceNoHole(csg, height);
    }

    public Geometry3D getSmallCylinderPieceHole(JavaCSG csg){
        return createCylinderPieceHole(csg, this.height);
    }

    public Geometry3D getBigCylindricalPieceNoHole(JavaCSG csg){
        return createCylinderPieceNoHole(csg,this.height);
    }
    public Geometry3D getBigCylindricalPieceHole(JavaCSG csg){
        return createCylinderPieceHole(csg,this.height);
    }

    public Geometry3D createCylinderPieceHole(JavaCSG csg, double height){
        Geometry3D cicleCylinder = csg.cylinder3D(brickSize,height,128,false);
        Geometry3D indent = createIndent(csg);


        Geometry3D indentCylinder = csg.difference3D(cicleCylinder,indent);

        Geometry3D hole = csg.flatRing3D(0,18,10,128,false);
        Geometry3D holeMoved = csg.translate3D(0,0,height-8).transform(hole);
        Geometry3D finalSqaure = csg.difference3D(indentCylinder,holeMoved);
        return finalSqaure;
    }


    public Geometry3D createCylinderPieceNoHole(JavaCSG csg, double height){
        Geometry3D cylinderePiece = csg.cylinder3D(brickSize,height,128,false);
        Geometry3D indent = createIndent(csg);

        Geometry3D finalCylinderPiece = csg.difference3D(cylinderePiece,indent);
        return finalCylinderPiece;
    }

    public Geometry3D createIndent(JavaCSG csg){
        Geometry2D ring = csg.ring2D(0,5,128);
        Geometry2D ringMoved = csg.translate2D(brickSize/2,5+indentHeight).transform(ring);
        Geometry3D ring3D = csg.rotateExtrude(csg.rotations(1),128,ringMoved);



        return ring3D;
    }

}
