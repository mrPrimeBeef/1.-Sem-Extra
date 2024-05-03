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

        Geometry3D top = Top(csg);
        indentCylinder = csg.union3D(indentCylinder,top);

        Geometry3D magnet = magnetSpace(csg);
        indentCylinder = csg.difference3D(indentCylinder,magnet);

        Geometry3D hole = csg.flatRing3D(0,18,12,128,false);
        Geometry3D holeMoved = csg.translate3D(0,0,height-8).transform(hole);

        Geometry3D finalCylinder = csg.difference3D(indentCylinder,holeMoved);


        return finalCylinder;
    }


    public Geometry3D createCylinderPieceNoHole(JavaCSG csg, double height){
        Geometry3D cylinderePiece = csg.cylinder3D(brickSize,height,128,false);
        Geometry3D indent = createIndent(csg);

        Geometry3D finalCylinderPiece = csg.difference3D(cylinderePiece,indent);
        Geometry3D magnet = magnetSpace(csg);
        finalCylinderPiece = csg.difference3D(finalCylinderPiece,magnet);
        Geometry3D egde = Top(csg);

        finalCylinderPiece = csg.union3D(finalCylinderPiece,egde);

        return finalCylinderPiece;
    }

    public Geometry3D Top(JavaCSG csg){
        Geometry2D ring = csg.ring2D(0,4,128);
        Geometry2D ringMoved = csg.translate2D(brickSize/2-2,0).transform(ring);
        Geometry3D ring3D = csg.rotateExtrude(csg.rotations(1),128,ringMoved);

        Geometry2D ringFill = csg.ring2D(0,brickSize-4,128);
        Geometry3D ring3DFill = csg.linearExtrude(2,false,ringFill);

        Geometry3D finalEgde = csg.union3D(ring3D,ring3DFill);

       finalEgde = csg.translate3D(0,0,height).transform(finalEgde);

        return finalEgde;
    }

    public Geometry3D createIndent(JavaCSG csg){
        Geometry2D ring = csg.ring2D(0,5,128);
        Geometry2D ringMoved = csg.translate2D(brickSize/2,0).transform(ring);
        Geometry3D ring3D = csg.rotateExtrude(csg.rotations(1),128,ringMoved);
        Geometry3D ring3DMoved = csg.translate3D(0,0,indentHeight).transform(ring3D);

        return ring3DMoved;
    }

    public Geometry3D magnetSpace(JavaCSG csg){
        Geometry3D finalMagnet = csg.box3D(4.73,9.77,2.88,false);
        Geometry3D finalMagnet1 = csg.translate3D(0,0,-0.1).transform(finalMagnet);

        return finalMagnet1;
    }

}